package com.curiovana.hufreshman.data

import android.content.Context
import android.content.SharedPreferences
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.SetOptions
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.InputStreamReader

class AppRepository(private val context: Context) {
    private val prefs: SharedPreferences =
        context.getSharedPreferences("hu_freshman_prefs", Context.MODE_PRIVATE)
    private val gson = Gson()
    private val firestore: FirebaseFirestore by lazy {
        FirebaseFirestore.getInstance()
    }

    fun cleanPhone(phone: String): String = phone.replace(Regex("[^0-9]"), "")


    private var cachedQuestions: List<ExamQuestion> = emptyList()
    private var cachedUniversities: List<UniversityGuide> = emptyList()
    private var cachedAnnouncements: List<Announcement> = emptyList()
    private var cachedPosts: MutableList<CommunityPost> = mutableListOf()
    private var cachedReports: MutableList<ReportItem> = mutableListOf()

    suspend fun loadQuestions(): List<ExamQuestion> = withContext(Dispatchers.IO) {
        if (cachedQuestions.isNotEmpty()) return@withContext cachedQuestions

        val questions = mutableListOf<ExamQuestion>()

        // 1. Load bundled questions from assets
        try {
            context.assets.open("data/exams.json").use { inputStream ->
                val reader = InputStreamReader(inputStream, "UTF-8")
                val jsonArray = gson.fromJson(reader, JsonArray::class.java)

                jsonArray?.forEachIndexed { index, element ->
                    if (element.isJsonObject) {
                        val obj = element.asJsonObject
                        if (obj.has("questions") && obj.get("questions").isJsonArray) {
                            val subArray = obj.getAsJsonArray("questions")
                            val examId = obj.get("id")?.asString ?: "exam_$index"
                            val course = obj.get("course")?.asString ?: "Freshman Course"
                            val university = obj.get("university")?.asString ?: "Haramaya University"
                            val year = obj.get("year")?.asString ?: "2024 Exam"
                            val cat = obj.get("category")?.asString ?: "Mid Exam"

                            subArray.forEachIndexed { qIdx, qElem ->
                                if (qElem.isJsonObject) {
                                    parseSingleQuestion(qElem.asJsonObject, "${examId}_q$qIdx", examId, course, university, year, cat)?.let {
                                        questions.add(it)
                                    }
                                }
                            }
                        } else if (obj.has("question")) {
                            parseSingleQuestion(obj, obj.get("id")?.asString ?: "q_$index")?.let {
                                questions.add(it)
                            }
                        }
                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        // 2. Load custom admin-created questions from SharedPreferences
        try {
            val customJson = prefs.getString("custom_questions", null)
            if (!customJson.isNullOrEmpty()) {
                val type = object : TypeToken<List<ExamQuestion>>() {}.type
                val customList: List<ExamQuestion> = gson.fromJson(customJson, type)
                questions.addAll(0, customList)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        // 3. Ensure Economics past questions exist
        if (questions.none { it.course.contains("Economics", ignoreCase = true) }) {
            questions.addAll(getEconomicsQuestions())
        }

        cachedQuestions = questions
        questions
    }

    private fun parseSingleQuestion(
        obj: JsonObject,
        defaultId: String,
        fallbackExamId: String? = null,
        fallbackCourse: String = "Freshman Course",
        fallbackUniversity: String = "Haramaya University",
        fallbackYear: String = "2024 Exam",
        fallbackCategory: String = "Mid Exam"
    ): ExamQuestion? {
        val qText = obj.get("question")?.asString ?: return null
        val id = obj.get("id")?.asString ?: defaultId
        val course = obj.get("course")?.asString ?: fallbackCourse
        val university = obj.get("university")?.asString ?: fallbackUniversity
        val year = obj.get("year")?.asString ?: fallbackYear
        val category = obj.get("category")?.asString ?: fallbackCategory
        val explanation = obj.get("explanation")?.asString ?: "Detailed step-by-step solution provided."

        val optionsList = mutableListOf<String>()
        if (obj.has("options") && obj.get("options").isJsonArray) {
            obj.getAsJsonArray("options").forEach { optElem ->
                if (optElem.isJsonPrimitive) {
                    optionsList.add(optElem.asString)
                } else if (optElem.isJsonObject && optElem.asJsonObject.has("text")) {
                    optionsList.add(optElem.asJsonObject.get("text").asString)
                }
            }
        }

        var answerIdx = 0
        if (obj.has("answer") && obj.get("answer").isJsonPrimitive) {
            val primitive = obj.get("answer").asJsonPrimitive
            if (primitive.isNumber) {
                answerIdx = primitive.asInt
            } else {
                val str = primitive.asString.uppercase().trim()
                answerIdx = when (str) {
                    "B", "1" -> 1
                    "C", "2" -> 2
                    "D", "3" -> 3
                    else -> 0
                }
            }
        } else if (obj.has("correctOption")) {
            answerIdx = when (obj.get("correctOption").asString.uppercase().trim()) {
                "B" -> 1
                "C" -> 2
                "D" -> 3
                else -> 0
            }
        }

        val originalUniv = obj.get("originalUniversity")?.asString

        return ExamQuestion(
            id = id,
            examId = obj.get("examId")?.asString ?: fallbackExamId,
            course = course,
            university = university,
            year = if (year.contains("Exam", ignoreCase = true)) year else "$year Exam",
            category = category,
            question = qText,
            options = if (optionsList.isNotEmpty()) optionsList else listOf("Option A", "Option B", "Option C", "Option D"),
            answer = answerIdx.coerceIn(0, (optionsList.size - 1).coerceAtLeast(0)),
            explanation = explanation,
            originalUniversity = originalUniv
        )
    }

    private fun getEconomicsQuestions(): List<ExamQuestion> {
        return listOf(
            ExamQuestion(
                id = "econ-aau-2024-mid-01",
                examId = "econ_2024_mid",
                course = "Introduction to Economics",
                university = "Addis Ababa University",
                year = "2024 Exam",
                category = "Mid Exam",
                question = "Which of the following best defines opportunity cost in economics?",
                options = listOf(
                    "The monetary price paid for a good or service",
                    "The value of the next best alternative forgone when a choice is made",
                    "The total cost of all production factors combined",
                    "The sunken expenditure that cannot be recovered"
                ),
                answer = 1,
                explanation = "Opportunity cost is fundamentally defined as the value of the next best alternative sacrificed or forgone when making an economic decision under scarcity."
            ),
            ExamQuestion(
                id = "econ-aau-2024-mid-02",
                examId = "econ_2024_mid",
                course = "Introduction to Economics",
                university = "Addis Ababa University",
                year = "2024 Exam",
                category = "Mid Exam",
                question = "According to the Law of Demand, when the price of a normal good increases, ceteris paribus:",
                options = listOf(
                    "Quantity demanded decreases",
                    "Demand curve shifts outward to the right",
                    "Quantity supplied decreases",
                    "Quantity demanded increases"
                ),
                answer = 0,
                explanation = "The Law of Demand states that there is an inverse relationship between the price of a good and its quantity demanded, holding all other determinants constant (ceteris paribus)."
            ),
            ExamQuestion(
                id = "econ-hu-2024-mid-03",
                examId = "econ_2024_mid",
                course = "Introduction to Economics",
                university = "Haramaya University",
                year = "2024 Exam",
                category = "Mid Exam",
                question = "If two goods X and Y have a negative Cross-Price Elasticity of Demand (E_xy < 0), they are:",
                options = listOf(
                    "Substitute goods",
                    "Complementary goods",
                    "Inferior goods",
                    "Giffen goods"
                ),
                answer = 1,
                explanation = "A negative cross-price elasticity indicates that as the price of good Y rises, the quantity demanded of good X falls, meaning the two goods are consumed together as complements (e.g. coffee and sugar)."
            ),
            ExamQuestion(
                id = "econ-ju-2023-mid-01",
                examId = "econ_2023_mid",
                course = "Introduction to Economics",
                university = "Jimma University",
                year = "2023 Exam",
                category = "Mid Exam",
                question = "The Production Possibility Frontier (PPF) is typically bowed outward (concave to the origin) due to:",
                options = listOf(
                    "Decreasing opportunity costs",
                    "Constant returns to scale",
                    "The law of increasing opportunity costs",
                    "Technological stagnation"
                ),
                answer = 2,
                explanation = "The PPF is bowed outward because resources are not perfectly adaptable to all types of production. Moving resources from one industry to another incurs increasing opportunity costs."
            ),
            ExamQuestion(
                id = "econ-bdu-2023-mid-02",
                examId = "econ_2023_mid",
                course = "Introduction to Economics",
                university = "Bahir Dar University",
                year = "2023 Exam",
                category = "Mid Exam",
                question = "When demand is price elastic (|Ed| > 1), a decrease in the price of the good will cause total revenue to:",
                options = listOf(
                    "Increase",
                    "Decrease",
                    "Remain unchanged",
                    "Drop to zero"
                ),
                answer = 0,
                explanation = "When demand is price elastic, the percentage increase in quantity demanded is greater than the percentage decrease in price, leading to an overall increase in total revenue (P × Q)."
            ),
            ExamQuestion(
                id = "econ-hu-2022-mid-01",
                examId = "econ_2022_mid",
                course = "Introduction to Economics",
                university = "Haramaya University",
                year = "2022 Exam",
                category = "Mid Exam",
                question = "In the short run, when the Marginal Product (MP) of labor is greater than the Average Product (AP):",
                options = listOf(
                    "Average Product must be increasing",
                    "Average Product must be decreasing",
                    "Total Product is at its maximum",
                    "Marginal Cost is at its maximum"
                ),
                answer = 0,
                explanation = "Whenever the marginal value is greater than the average value (MP > AP), it pulls the average value upward, causing Average Product to rise."
            ),
            ExamQuestion(
                id = "econ-aau-2024-final-01",
                examId = "econ_2024_final",
                course = "Introduction to Economics",
                university = "Addis Ababa University",
                year = "2024 Exam",
                category = "Final Exam",
                question = "In a perfectly competitive market in long-run equilibrium, a firm produces where:",
                options = listOf(
                    "Price = Marginal Cost = Average Total Cost",
                    "Price > Marginal Cost",
                    "Marginal Revenue > Price",
                    "Economic profits are permanently positive"
                ),
                answer = 0,
                explanation = "In long-run competitive equilibrium, free entry and exit drive economic profits to zero where P = MR = MC = minimum ATC."
            ),
            ExamQuestion(
                id = "econ-hu-2024-final-02",
                examId = "econ_2024_final",
                course = "Introduction to Economics",
                university = "Haramaya University",
                year = "2024 Exam",
                category = "Final Exam",
                question = "Gross Domestic Product (GDP) measured using the expenditure approach is calculated as:",
                options = listOf(
                    "GDP = Wages + Rent + Interest + Profits",
                    "GDP = C + I + G + (X - M)",
                    "GDP = C + S + T",
                    "GDP = National Income + Depreciation"
                ),
                answer = 1,
                explanation = "The expenditure approach calculates GDP as the sum of Consumption (C), Gross Investment (I), Government Purchases (G), and Net Exports (X - M)."
            ),
            ExamQuestion(
                id = "econ-ju-2023-final-01",
                examId = "econ_2023_final",
                course = "Introduction to Economics",
                university = "Jimma University",
                year = "2023 Exam",
                category = "Final Exam",
                question = "Inflation resulting from an increase in aggregate demand beyond full-employment output is known as:",
                options = listOf(
                    "Cost-push inflation",
                    "Demand-pull inflation",
                    "Structural inflation",
                    "Hyperinflation"
                ),
                answer = 1,
                explanation = "Demand-pull inflation occurs when aggregate demand for goods and services outpaces aggregate supply, often described as 'too much money chasing too few goods'."
            ),
            ExamQuestion(
                id = "econ-bdu-2022-final-01",
                examId = "econ_2022_final",
                course = "Introduction to Economics",
                university = "Bahir Dar University",
                year = "2022 Exam",
                category = "Final Exam",
                question = "Which of the following is a primary monetary policy tool used by central banks (such as the National Bank of Ethiopia) to control inflation?",
                options = listOf(
                    "Increasing government capital expenditure",
                    "Raising reserve requirements or policy interest rates",
                    "Lowering personal income tax rates",
                    "Imposing price ceilings on consumer staples"
                ),
                answer = 1,
                explanation = "Central banks use monetary policy tools like open market operations, reserve requirements, and policy interest rates to regulate money supply and tame inflationary pressure."
            )
        )
    }

    suspend fun addCustomQuestion(question: ExamQuestion): Boolean = withContext(Dispatchers.IO) {
        val customJson = prefs.getString("custom_questions", "[]")
        val type = object : TypeToken<MutableList<ExamQuestion>>() {}.type
        val customList: MutableList<ExamQuestion> = gson.fromJson(customJson, type) ?: mutableListOf()
        customList.add(0, question)
        prefs.edit().putString("custom_questions", gson.toJson(customList)).apply()

        // Update in-memory
        cachedQuestions = listOf(question) + cachedQuestions
        true
    }

    suspend fun loadUniversities(): List<UniversityGuide> = withContext(Dispatchers.IO) {
        if (cachedUniversities.isNotEmpty()) return@withContext cachedUniversities

        val customJson = prefs.getString("custom_universities", null)
        if (!customJson.isNullOrEmpty()) {
            try {
                val type = object : TypeToken<List<UniversityGuide>>() {}.type
                val customList: List<UniversityGuide> = gson.fromJson(customJson, type) ?: emptyList()
                if (customList.isNotEmpty()) {
                    cachedUniversities = customList
                    return@withContext customList
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        try {
            context.assets.open("data/university_guides.json").use { inputStream ->
                val reader = InputStreamReader(inputStream, "UTF-8")
                val type = object : TypeToken<List<UniversityGuide>>() {}.type
                val list: List<UniversityGuide> = gson.fromJson(reader, type)
                cachedUniversities = list
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        cachedUniversities
    }

    suspend fun updateUniversity(updated: UniversityGuide): Boolean = withContext(Dispatchers.IO) {
        val currentList = loadUniversities().toMutableList()
        val index = currentList.indexOfFirst { it.id == updated.id }
        if (index != -1) {
            currentList[index] = updated
        } else {
            currentList.add(0, updated)
        }
        cachedUniversities = currentList
        prefs.edit().putString("custom_universities", gson.toJson(currentList)).apply()
        true
    }

    suspend fun loadAnnouncements(): List<Announcement> = withContext(Dispatchers.IO) {
        if (cachedAnnouncements.isNotEmpty()) return@withContext cachedAnnouncements

        val list = mutableListOf<Announcement>()
        try {
            context.assets.open("data/announcements.json").use { inputStream ->
                val reader = InputStreamReader(inputStream, "UTF-8")
                val type = object : TypeToken<List<Announcement>>() {}.type
                val fromJson: List<Announcement> = gson.fromJson(reader, type)
                list.addAll(fromJson)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        // Custom announcements created by admin
        val customAnnJson = prefs.getString("custom_announcements", null)
        if (!customAnnJson.isNullOrEmpty()) {
            val type = object : TypeToken<List<Announcement>>() {}.type
            val customAnn: List<Announcement> = gson.fromJson(customAnnJson, type)
            list.addAll(0, customAnn)
        }

        cachedAnnouncements = list
        list
    }

    suspend fun addAnnouncement(ann: Announcement): Boolean = withContext(Dispatchers.IO) {
        val customAnnJson = prefs.getString("custom_announcements", "[]")
        val type = object : TypeToken<MutableList<Announcement>>() {}.type
        val customList: MutableList<Announcement> = gson.fromJson(customAnnJson, type) ?: mutableListOf()
        customList.add(0, ann)
        prefs.edit().putString("custom_announcements", gson.toJson(customList)).apply()

        cachedAnnouncements = listOf(ann) + cachedAnnouncements
        true
    }

    private fun sortPostsDescending(posts: List<CommunityPost>): List<CommunityPost> {
        return posts.sortedWith(
            compareByDescending<CommunityPost> { post ->
                if (post.timestamp > 0L) post.timestamp
                else post.id.removePrefix("post_").toLongOrNull() ?: 0L
            }
        )
    }

    fun getCachedCommunityPosts(): List<CommunityPost> {
        val likedSet = getLikedPostIds()
        return sortPostsDescending(cachedPosts).map { it.copy(isLiked = likedSet.contains(it.id)) }
    }

    private fun isDemoPost(id: String): Boolean {
        return id.startsWith("post_hu_") || id == "post_hu_1" || id == "post_hu_2" || id == "post_hu_3"
    }

    suspend fun loadCommunityPosts(forceRefresh: Boolean = false): List<CommunityPost> = withContext(Dispatchers.IO) {
        val likedSet = getLikedPostIds()

        // Purge any legacy demo posts from in-memory cache
        cachedPosts.removeAll { isDemoPost(it.id) }

        // 1. If in-memory cache is present and not force-refreshing, return immediately (0 network calls)
        if (cachedPosts.isNotEmpty() && !forceRefresh) {
            return@withContext getCachedCommunityPosts()
        }

        // 2. Read local phone storage (SharedPreferences)
        val savedPostsJson = prefs.getString("community_posts", null)
        val localPosts = mutableListOf<CommunityPost>()
        if (!savedPostsJson.isNullOrEmpty()) {
            try {
                val type = object : TypeToken<MutableList<CommunityPost>>() {}.type
                val parsed: List<CommunityPost> = gson.fromJson(savedPostsJson, type) ?: emptyList()
                val realPosts = parsed.filterNot { isDemoPost(it.id) }
                if (realPosts.size != parsed.size) {
                    savePostsToPrefs(realPosts)
                }
                localPosts.addAll(realPosts)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        // 3. If we have real local posts stored on the phone and not force-refreshing, use local phone storage!
        // This directly fulfills: "once it loads it must stay on local phone that always read from firebase it eats spark plan"
        if (localPosts.isNotEmpty() && !forceRefresh) {
            val mapped = localPosts.map { it.copy(isLiked = likedSet.contains(it.id)) }
            cachedPosts = mapped.toMutableList()
            return@withContext getCachedCommunityPosts()
        }

        // 4. Either local storage has no real posts OR user explicitly requested refresh:
        // Check daily read quota first — silently fall back to cache when quota is exhausted.
        if (!canReadFromFirebase()) {
            // Quota used up for today — serve whatever real posts we have without hitting Firebase
            if (localPosts.isNotEmpty()) {
                cachedPosts = localPosts.map { it.copy(isLiked = likedSet.contains(it.id)) }.toMutableList()
            }
            return@withContext getCachedCommunityPosts()
        }

        // Count this as a Firebase read
        incrementFirebaseReadCount()

        // Query Firebase Firestore — exact content from database
        try {
            val snapshot = com.google.android.gms.tasks.Tasks.await(
                firestore.collection("community_posts").get(),
                10,
                java.util.concurrent.TimeUnit.SECONDS
            )

            val remotePosts = mutableListOf<CommunityPost>()
            for (doc in snapshot.documents) {
                try {
                    val id = doc.getString("id") ?: doc.id
                    // If an old demo post was ever seeded into Firestore, delete it from Firestore and do not show it
                    if (isDemoPost(id) || isDemoPost(doc.id)) {
                        try {
                            firestore.collection("community_posts").document(doc.id).delete()
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                        continue
                    }

                    val author = doc.getString("author") ?: "HU Freshman"
                    val role = doc.getString("role") ?: "Student"
                    val date = doc.getString("date") ?: "Recently"
                    val content = doc.getString("content") ?: ""
                    val tag = doc.getString("tag") ?: "Academic"
                    val imageUrl = doc.getString("imageUrl")
                    val videoUrl = doc.getString("videoUrl") ?: doc.getString("youtubeUrl")
                    val youtubeUrl = doc.getString("youtubeUrl") ?: videoUrl
                    val likes = (doc.getLong("likes") ?: 0L).toInt()
                    val timestamp = doc.getLong("timestamp") ?: id.removePrefix("post_").toLongOrNull() ?: 0L

                    val commentsList = mutableListOf<Comment>()
                    val rawComments = doc.get("comments") as? List<Map<String, Any>>
                    rawComments?.forEach { cMap ->
                        commentsList.add(
                            Comment(
                                id = cMap["id"] as? String ?: java.util.UUID.randomUUID().toString(),
                                author = cMap["author"] as? String ?: "Student",
                                content = cMap["content"] as? String ?: "",
                                date = cMap["date"] as? String ?: "Recently"
                            )
                        )
                    }

                    if (content.isNotBlank()) {
                        remotePosts.add(
                            CommunityPost(
                                id = id,
                                author = author,
                                role = role,
                                date = date,
                                content = content,
                                tag = tag,
                                imageUrl = imageUrl,
                                videoUrl = videoUrl,
                                youtubeUrl = youtubeUrl,
                                likes = likes,
                                isLiked = likedSet.contains(id),
                                comments = commentsList,
                                timestamp = timestamp
                            )
                        )
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }

            // Exactly what is in database (mirrors database, sorted with newest at top)
            cachedPosts = sortPostsDescending(remotePosts).toMutableList()
            savePostsToPrefs(cachedPosts)
            return@withContext getCachedCommunityPosts()
        } catch (e: Exception) {
            e.printStackTrace()
        }

        // If network failed but real local posts exist, stay on local phone
        if (localPosts.isNotEmpty()) {
            cachedPosts = localPosts.map { it.copy(isLiked = likedSet.contains(it.id)) }.toMutableList()
            return@withContext getCachedCommunityPosts()
        }

        // No real posts available in database or local
        cachedPosts = mutableListOf()
        getCachedCommunityPosts()
    }

    private fun savePostToFirestore(post: CommunityPost) {
        val postMap = hashMapOf(
            "id" to post.id,
            "author" to post.author,
            "role" to post.role,
            "date" to post.date,
            "content" to post.content,
            "tag" to post.tag,
            "likes" to post.likes.toLong(),
            "comments" to post.comments.map { c ->
                mapOf(
                    "id" to c.id,
                    "author" to c.author,
                    "content" to c.content,
                    "date" to c.date
                )
            },
            "timestamp" to System.currentTimeMillis()
        )
        if (!post.imageUrl.isNullOrBlank()) {
            postMap["imageUrl"] = post.imageUrl
        }
        if (!post.videoUrl.isNullOrBlank()) {
            postMap["videoUrl"] = post.videoUrl
        }
        if (!post.youtubeUrl.isNullOrBlank()) {
            postMap["youtubeUrl"] = post.youtubeUrl
        }
        firestore.collection("community_posts").document(post.id).set(postMap, SetOptions.merge())
    }

    private fun savePostsToPrefs(posts: List<CommunityPost>) {
        prefs.edit().putString("community_posts", gson.toJson(posts)).apply()
    }

    // ── Daily Quota Helpers ──────────────────────────────────────────────────
    // All quota tracking is local (SharedPreferences) — zero extra Firebase reads.

    private fun todayKey(): String {
        val cal = java.util.Calendar.getInstance()
        return "${cal.get(java.util.Calendar.YEAR)}_${cal.get(java.util.Calendar.DAY_OF_YEAR)}"
    }

    /** Returns true if a regular user is still allowed to post (max 2/day). */
    fun canUserPost(): Boolean {
        val key = "user_post_count_${todayKey()}"
        return prefs.getInt(key, 0) < 2
    }

    /** Increments the user's daily post counter. Call after a successful post. */
    fun incrementUserPostCount() {
        val key = "user_post_count_${todayKey()}"
        prefs.edit().putInt(key, prefs.getInt(key, 0) + 1).apply()
    }

    /** Returns true if the community feed can be freshly fetched from Firebase (max 10/day). */
    fun canReadFromFirebase(): Boolean {
        val key = "community_read_count_${todayKey()}"
        return prefs.getInt(key, 0) < 10
    }

    /** Increments the daily Firebase read counter. Call just before each actual network fetch. */
    fun incrementFirebaseReadCount() {
        val key = "community_read_count_${todayKey()}"
        prefs.edit().putInt(key, prefs.getInt(key, 0) + 1).apply()
    }
    // ────────────────────────────────────────────────────────────────────────


    suspend fun addPost(
        content: String,
        tag: String,
        author: String,
        role: String,
        imageUrl: String? = null,
        videoUrl: String? = null
    ): CommunityPost = withContext(Dispatchers.IO) {
        val now = System.currentTimeMillis()
        val newPost = CommunityPost(
            id = "post_$now",
            author = author,
            role = role,
            date = "Just now",
            content = content,
            tag = tag,
            imageUrl = imageUrl?.takeIf { it.isNotBlank() },
            videoUrl = videoUrl?.takeIf { it.isNotBlank() },
            youtubeUrl = videoUrl?.takeIf { it.isNotBlank() },
            likes = 0,
            isLiked = false,
            comments = emptyList(),
            timestamp = now
        )
        // 1. Instant local update
        cachedPosts.add(0, newPost)
        savePostsToPrefs(cachedPosts)

        // 2. Targeted Firestore write (1 write, 0 reads to protect Spark quota)
        try {
            savePostToFirestore(newPost)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        newPost
    }

    suspend fun updatePost(
        postId: String,
        newContent: String,
        newTag: String,
        newImageUrl: String? = null,
        newVideoUrl: String? = null
    ): CommunityPost? = withContext(Dispatchers.IO) {
        val index = cachedPosts.indexOfFirst { it.id == postId }
        if (index == -1) return@withContext null

        val current = cachedPosts[index]
        val updated = current.copy(
            content = newContent,
            tag = newTag,
            imageUrl = newImageUrl?.takeIf { it.isNotBlank() },
            videoUrl = newVideoUrl?.takeIf { it.isNotBlank() },
            youtubeUrl = newVideoUrl?.takeIf { it.isNotBlank() }
        )
        cachedPosts[index] = updated
        savePostsToPrefs(cachedPosts)

        // Targeted Firestore update (1 write, 0 reads)
        try {
            val updateMap = mutableMapOf<String, Any>(
                "content" to newContent,
                "tag" to newTag
            )
            if (!newImageUrl.isNullOrBlank()) updateMap["imageUrl"] = newImageUrl
            if (!newVideoUrl.isNullOrBlank()) {
                updateMap["videoUrl"] = newVideoUrl
                updateMap["youtubeUrl"] = newVideoUrl
            }
            firestore.collection("community_posts").document(postId).update(updateMap)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        updated
    }

    suspend fun deletePost(postId: String): Boolean = withContext(Dispatchers.IO) {
        val removed = cachedPosts.removeAll { it.id == postId }
        if (removed) {
            savePostsToPrefs(cachedPosts)
        }

        // Targeted Firestore delete (1 write, 0 reads)
        try {
            firestore.collection("community_posts").document(postId).delete()
        } catch (e: Exception) {
            e.printStackTrace()
        }

        removed
    }

    suspend fun toggleLike(postId: String): CommunityPost? = withContext(Dispatchers.IO) {
        val index = cachedPosts.indexOfFirst { it.id == postId }
        if (index == -1) return@withContext null

        val current = cachedPosts[index]
        val isLiked = !current.isLiked
        val newLikes = if (isLiked) current.likes + 1 else (current.likes - 1).coerceAtLeast(0)
        val updated = current.copy(isLiked = isLiked, likes = newLikes)
        cachedPosts[index] = updated

        val likedSet = getLikedPostIds().toMutableSet()
        if (isLiked) likedSet.add(postId) else likedSet.remove(postId)
        prefs.edit().putStringSet("liked_posts", likedSet).apply()

        savePostsToPrefs(cachedPosts)

        // Targeted Firestore update (1 write, 0 reads)
        try {
            firestore.collection("community_posts").document(postId).update("likes", newLikes.toLong())
        } catch (e: Exception) {
            e.printStackTrace()
        }

        updated
    }

    suspend fun addComment(postId: String, commentText: String, author: String): CommunityPost? = withContext(Dispatchers.IO) {
        val index = cachedPosts.indexOfFirst { it.id == postId }
        if (index == -1) return@withContext null

        val newComment = Comment(
            id = "c_" + System.currentTimeMillis(),
            author = author,
            content = commentText,
            date = "Just now"
        )
        val current = cachedPosts[index]
        val updated = current.copy(comments = current.comments + newComment)
        cachedPosts[index] = updated
        savePostsToPrefs(cachedPosts)

        // Targeted Firestore update (1 write, 0 reads)
        try {
            val commentMap = mapOf(
                "id" to newComment.id,
                "author" to newComment.author,
                "content" to newComment.content,
                "date" to newComment.date
            )
            firestore.collection("community_posts").document(postId).update(
                "comments", FieldValue.arrayUnion(commentMap)
            )
        } catch (e: Exception) {
            e.printStackTrace()
        }

        updated
    }

    private fun getLikedPostIds(): Set<String> {
        return prefs.getStringSet("liked_posts", emptySet()) ?: emptySet()
    }

    // Bookmarks
    fun getBookmarks(): Set<String> {
        return prefs.getStringSet("bookmarks", emptySet()) ?: emptySet()
    }

    fun toggleBookmark(questionId: String): Boolean {
        val current = getBookmarks().toMutableSet()
        val isBookmarked: Boolean
        if (current.contains(questionId)) {
            current.remove(questionId)
            isBookmarked = false
        } else {
            current.add(questionId)
            isBookmarked = true
        }
        prefs.edit().putStringSet("bookmarks", current).apply()
        return isBookmarked
    }

    // User Profile
    fun getUserProfile(): UserProfile {
        val isApproved = prefs.getBoolean("user_is_approved", false)
        val savedPhone = prefs.getString("user_phone", "") ?: ""
        // A user is a guest if they have never logged in (no phone stored)
        val isGuest = savedPhone.isBlank()
        return UserProfile(
            name = prefs.getString("user_name", "")?.takeIf {
                it.isNotBlank() && !it.contains("HU Freshman", ignoreCase = true)
            } ?: "",
            university = prefs.getString("user_university", "Haramaya University") ?: "Haramaya University",
            stream = prefs.getString("user_stream", "Natural Science") ?: "Natural Science",
            academicYear = prefs.getString("user_academic_year", "2026/2027 Academic Year") ?: "2026/2027 Academic Year",
            phoneNumber = savedPhone,
            password = prefs.getString("user_password", "") ?: "",
            isAdmin = prefs.getBoolean("user_is_admin", false),
            hasSubmittedRegistration = prefs.getBoolean("user_has_submitted_registration", false),
            isApproved = isApproved,
            isRegisteredMember = isApproved,
            transactionId = prefs.getString("user_transaction_id", "") ?: "",
            paymentMethod = prefs.getString("user_payment_method", "") ?: "",
            registrationDate = prefs.getString("user_registration_date", "") ?: "",
            rejectionReason = prefs.getString("user_rejection_reason", null),
            isGuest = isGuest
        )
    }


    fun saveUserProfile(profile: UserProfile) {
        prefs.edit()
            .putString("user_name", profile.name)
            .putString("user_university", profile.university)
            .putString("user_stream", profile.stream)
            .putString("user_academic_year", profile.academicYear)
            .putString("user_phone", profile.phoneNumber)
            .putString("user_password", profile.password)
            .putBoolean("user_is_admin", profile.isAdmin)
            .putBoolean("user_has_submitted_registration", profile.hasSubmittedRegistration)
            .putBoolean("user_is_approved", profile.isApproved)
            .putBoolean("user_is_registered_member", profile.isApproved)
            .putString("user_transaction_id", profile.transactionId)
            .putString("user_payment_method", profile.paymentMethod)
            .putString("user_registration_date", profile.registrationDate)
            .putString("user_rejection_reason", profile.rejectionReason)
            .apply()
    }

    fun loadMemberRegistrations(): List<MemberRegistration> {
        val listJson = prefs.getString("member_registrations", "[]")
        val type = object : TypeToken<MutableList<MemberRegistration>>() {}.type
        return gson.fromJson(listJson, type) ?: mutableListOf()
    }

    fun saveMemberRegistration(reg: MemberRegistration) {
        val list = loadMemberRegistrations().toMutableList()
        // Check if exists or update
        val existingIndex = list.indexOfFirst { it.transactionId == reg.transactionId || it.phoneNumber == reg.phoneNumber }
        if (existingIndex >= 0) {
            list[existingIndex] = reg
        } else {
            list.add(0, reg)
        }
        val hasSubmittedPayment = reg.screenshotUrl.isNotBlank() || reg.paymentMethod.isNotBlank()
        prefs.edit()
            .putString("member_registrations", gson.toJson(list))
            .putBoolean("user_has_submitted_registration", hasSubmittedPayment)
            .putBoolean("user_is_approved", false)
            .putBoolean("user_is_registered_member", false)
            .putString("user_transaction_id", reg.transactionId)
            .putString("user_payment_method", reg.paymentMethod)
            .putString("user_phone", reg.phoneNumber)
            .putString("user_password", reg.password)
            .putString("user_name", reg.fullName)
            .putString("user_university", reg.universityName)
            .putString("user_academic_year", reg.academicYear)
            .putString("user_registration_date", reg.date)
            .putString("user_rejection_reason", null)
            .apply()

        // Sync to Cloud Firestore in background so admin sees it immediately on any device
        try {
            val docId = cleanPhone(reg.phoneNumber).ifBlank { reg.id }
            val data = hashMapOf(
                "id" to reg.id,
                "fullName" to reg.fullName,
                "universityName" to reg.universityName,
                "academicYear" to reg.academicYear,
                "phoneNumber" to reg.phoneNumber,
                "password" to reg.password,
                "paymentMethod" to reg.paymentMethod,
                "transactionId" to reg.transactionId,
                "screenshotUrl" to reg.screenshotUrl,
                "date" to reg.date,
                "isApproved" to false,
                "rejectionReason" to null,
                "timestamp" to System.currentTimeMillis()
            )
            firestore.collection("member_registrations")
                .document(docId)
                .set(data, SetOptions.merge())
                .addOnSuccessListener {
                    android.util.Log.d("AppRepository", "Registration synced to Firestore for doc: $docId")
                }
                .addOnFailureListener { e ->
                    android.util.Log.e("AppRepository", "Failed to sync registration to Firestore", e)
                }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun updatePaymentVerification(paymentMethod: String, screenshotUrl: String) {
        val currentPhone = prefs.getString("user_phone", "") ?: ""
        val list = loadMemberRegistrations().toMutableList()
        val index = list.indexOfFirst { it.phoneNumber == currentPhone }
        if (index >= 0) {
            val updated = list[index].copy(
                paymentMethod = paymentMethod,
                screenshotUrl = screenshotUrl,
                isApproved = false,
                rejectionReason = null
            )
            list[index] = updated
            prefs.edit().putString("member_registrations", gson.toJson(list)).apply()
        }
        prefs.edit()
            .putString("user_payment_method", paymentMethod)
            .putBoolean("user_has_submitted_registration", true)
            .apply()

        // Sync to Cloud Firestore
        try {
            val docId = cleanPhone(currentPhone)
            if (docId.isNotBlank()) {
                val updates = hashMapOf<String, Any?>(
                    "paymentMethod" to paymentMethod,
                    "screenshotUrl" to screenshotUrl,
                    "isApproved" to false,
                    "rejectionReason" to null,
                    "paymentSubmittedAt" to System.currentTimeMillis()
                )
                firestore.collection("member_registrations")
                    .document(docId)
                    .set(updates, SetOptions.merge())
                    .addOnSuccessListener {
                        android.util.Log.d("AppRepository", "Payment verification updated in Firestore for doc: $docId")
                    }
                    .addOnFailureListener { e ->
                        android.util.Log.e("AppRepository", "Failed to update payment verification in Firestore", e)
                    }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun approveMemberRegistration(regId: String) {
        val list = loadMemberRegistrations().toMutableList()
        val index = list.indexOfFirst { it.id == regId || it.transactionId == regId || it.phoneNumber == regId }
        val reg = if (index >= 0) list[index] else null
        val targetPhone = reg?.phoneNumber ?: regId
        val docId = cleanPhone(targetPhone).ifBlank { regId }

        if (index >= 0) {
            val updated = list[index].copy(isApproved = true, rejectionReason = null)
            list[index] = updated
            prefs.edit().putString("member_registrations", gson.toJson(list)).apply()

            val curTxn = prefs.getString("user_transaction_id", "") ?: ""
            val curPhone = prefs.getString("user_phone", "") ?: ""
            if (curTxn == updated.transactionId || curPhone == updated.phoneNumber || curTxn.isEmpty()) {
                prefs.edit()
                    .putBoolean("user_is_approved", true)
                    .putBoolean("user_is_registered_member", true)
                    .putString("user_rejection_reason", null)
                    .apply()
            }
        }

        // Push approval update to Cloud Firestore
        try {
            val updates = mapOf<String, Any?>(
                "isApproved" to true,
                "rejectionReason" to null
            )
            firestore.collection("member_registrations")
                .document(docId)
                .set(updates, SetOptions.merge())
                .addOnSuccessListener {
                    android.util.Log.d("AppRepository", "Approved in Firestore: $docId")
                }
                .addOnFailureListener { e ->
                    android.util.Log.e("AppRepository", "Firestore approve failed", e)
                }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun rejectMemberRegistration(regId: String, reason: String) {
        val list = loadMemberRegistrations().toMutableList()
        val index = list.indexOfFirst { it.id == regId || it.transactionId == regId || it.phoneNumber == regId }
        val reg = if (index >= 0) list[index] else null
        val targetPhone = reg?.phoneNumber ?: regId
        val docId = cleanPhone(targetPhone).ifBlank { regId }

        if (index >= 0) {
            val updated = list[index].copy(isApproved = false, rejectionReason = reason)
            list[index] = updated
            prefs.edit().putString("member_registrations", gson.toJson(list)).apply()

            val curTxn = prefs.getString("user_transaction_id", "") ?: ""
            val curPhone = prefs.getString("user_phone", "") ?: ""
            if (curTxn == updated.transactionId || curPhone == updated.phoneNumber) {
                prefs.edit()
                    .putBoolean("user_is_approved", false)
                    .putBoolean("user_is_registered_member", false)
                    .putString("user_rejection_reason", reason)
                    .apply()
            }
        }

        // Push rejection update to Cloud Firestore
        try {
            val updates = mapOf<String, Any?>(
                "isApproved" to false,
                "rejectionReason" to reason
            )
            firestore.collection("member_registrations")
                .document(docId)
                .set(updates, SetOptions.merge())
                .addOnSuccessListener {
                    android.util.Log.d("AppRepository", "Rejected in Firestore: $docId")
                }
                .addOnFailureListener { e ->
                    android.util.Log.e("AppRepository", "Firestore reject failed", e)
                }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun listenToMemberRegistrations(onUpdate: (List<MemberRegistration>) -> Unit): ListenerRegistration? {
        return try {
            firestore.collection("member_registrations")
                .addSnapshotListener { snapshot, error ->
                    if (error != null) {
                        android.util.Log.e("AppRepository", "Firestore listener error", error)
                        return@addSnapshotListener
                    }
                    if (snapshot != null) {
                        val list = mutableListOf<MemberRegistration>()
                        for (doc in snapshot.documents) {
                            try {
                                val id = doc.getString("id") ?: doc.id
                                val fullName = doc.getString("fullName") ?: ""
                                val universityName = doc.getString("universityName") ?: ""
                                val academicYear = doc.getString("academicYear") ?: ""
                                val phoneNumber = doc.getString("phoneNumber") ?: doc.id
                                val password = doc.getString("password") ?: ""
                                val paymentMethod = doc.getString("paymentMethod") ?: ""
                                val transactionId = doc.getString("transactionId") ?: ""
                                val screenshotUrl = doc.getString("screenshotUrl") ?: ""
                                val date = doc.getString("date") ?: ""
                                val isApproved = doc.getBoolean("isApproved") ?: false
                                val rejectionReason = doc.getString("rejectionReason")
                                if (fullName.isNotBlank() || transactionId.isNotBlank() || phoneNumber.isNotBlank()) {
                                    list.add(
                                        MemberRegistration(
                                            id = id,
                                            fullName = fullName,
                                            universityName = universityName,
                                            academicYear = academicYear,
                                            phoneNumber = phoneNumber,
                                            password = password,
                                            paymentMethod = paymentMethod,
                                            transactionId = transactionId,
                                            screenshotUrl = screenshotUrl,
                                            date = date,
                                            isApproved = isApproved,
                                            rejectionReason = rejectionReason
                                        )
                                    )
                                }
                            } catch (e: Exception) {
                                e.printStackTrace()
                            }
                        }
                        if (list.isNotEmpty()) {
                            prefs.edit().putString("member_registrations", gson.toJson(list)).apply()
                        }
                        onUpdate(list)
                    }
                }
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    fun listenToUserApproval(phone: String, onStatusChanged: (isApproved: Boolean, rejectionReason: String?) -> Unit): ListenerRegistration? {
        val docId = cleanPhone(phone)
        if (docId.isBlank()) return null
        return try {
            firestore.collection("member_registrations")
                .document(docId)
                .addSnapshotListener { snapshot, error ->
                    if (error != null || snapshot == null || !snapshot.exists()) return@addSnapshotListener
                    val isApproved = snapshot.getBoolean("isApproved") ?: false
                    val reason = snapshot.getString("rejectionReason")
                    prefs.edit()
                        .putBoolean("user_is_approved", isApproved)
                        .putBoolean("user_is_registered_member", isApproved)
                        .putString("user_rejection_reason", reason)
                        .apply()
                    onStatusChanged(isApproved, reason)
                }
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    suspend fun syncCurrentUserFromFirestore(phone: String): Boolean = withContext(Dispatchers.IO) {
        val docId = cleanPhone(phone)
        if (docId.isBlank()) return@withContext false
        return@withContext try {
            val doc = com.google.android.gms.tasks.Tasks.await(
                firestore.collection("member_registrations").document(docId).get()
            )
            if (doc != null && doc.exists()) {
                val isApproved = doc.getBoolean("isApproved") ?: false
                val reason = doc.getString("rejectionReason")
                val name = doc.getString("fullName")
                val uni = doc.getString("universityName")
                val yr = doc.getString("academicYear")
                val txn = doc.getString("transactionId")
                val method = doc.getString("paymentMethod")
                val date = doc.getString("date")

                val editor = prefs.edit()
                    .putBoolean("user_has_submitted_registration", true)
                    .putBoolean("user_is_approved", isApproved)
                    .putBoolean("user_is_registered_member", isApproved)
                    .putString("user_rejection_reason", reason)

                if (!name.isNullOrBlank()) editor.putString("user_name", name)
                if (!uni.isNullOrBlank()) editor.putString("user_university", uni)
                if (!yr.isNullOrBlank()) editor.putString("user_academic_year", yr)
                if (!txn.isNullOrBlank()) editor.putString("user_transaction_id", txn)
                if (!method.isNullOrBlank()) editor.putString("user_payment_method", method)
                if (!date.isNullOrBlank()) editor.putString("user_registration_date", date)

                editor.apply()
                true
            } else {
                false
            }
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    suspend fun isPhoneAlreadyRegistered(phone: String): Boolean = withContext(Dispatchers.IO) {
        val normalizedPhone = EthiopianPhoneUtils.formatInput(phone)
        val clean = normalizedPhone.ifBlank { phone.replace(Regex("[^0-9]"), "") }
        if (clean.isBlank()) return@withContext false

        // 1. Check local registrations cache
        val localList = loadMemberRegistrations()
        val localMatch = localList.any { reg ->
            val regClean = cleanPhone(reg.phoneNumber)
            regClean == clean || (regClean.length >= 9 && clean.length >= 9 && regClean.takeLast(9) == clean.takeLast(9))
        }
        if (localMatch) return@withContext true

        // 2. Check local user profile
        val currentProfilePhone = cleanPhone(getUserProfile().phoneNumber)
        if (currentProfilePhone.isNotBlank() && (currentProfilePhone == clean || (currentProfilePhone.length >= 9 && clean.length >= 9 && currentProfilePhone.takeLast(9) == clean.takeLast(9)))) {
            if (getUserProfile().hasSubmittedRegistration) return@withContext true
        }

        // 3. Check Firestore document existence
        return@withContext try {
            // Direct document check with clean/normalized phone
            val doc1 = com.google.android.gms.tasks.Tasks.await(
                firestore.collection("member_registrations").document(clean).get()
            )
            if (doc1 != null && doc1.exists()) return@withContext true

            // 9-digit variant check (e.g. without leading 0)
            if (clean.startsWith("0")) {
                val shortClean = clean.substring(1)
                val doc2 = com.google.android.gms.tasks.Tasks.await(
                    firestore.collection("member_registrations").document(shortClean).get()
                )
                if (doc2 != null && doc2.exists()) return@withContext true
            }

            // Query by phoneNumber field
            val querySnapshot = com.google.android.gms.tasks.Tasks.await(
                firestore.collection("member_registrations")
                    .whereEqualTo("phoneNumber", normalizedPhone)
                    .limit(1)
                    .get()
            )
            if (querySnapshot != null && !querySnapshot.isEmpty) return@withContext true

            false
        } catch (e: Exception) {
            android.util.Log.w("AppRepository", "Error checking phone registration: ${e.localizedMessage}")
            false
        }
    }

    suspend fun fetchMemberRegistrationsFromFirestore(): Result<List<MemberRegistration>> = withContext(Dispatchers.IO) {
        return@withContext try {
            val snapshot = com.google.android.gms.tasks.Tasks.await(
                firestore.collection("member_registrations").get()
            )
            val list = mutableListOf<MemberRegistration>()
            for (doc in snapshot.documents) {
                try {
                    val id = doc.getString("id") ?: doc.id
                    val fullName = doc.getString("fullName") ?: ""
                    val universityName = doc.getString("universityName") ?: ""
                    val academicYear = doc.getString("academicYear") ?: ""
                    val phoneNumber = doc.getString("phoneNumber") ?: doc.id
                    val password = doc.getString("password") ?: ""
                    val paymentMethod = doc.getString("paymentMethod") ?: ""
                    val transactionId = doc.getString("transactionId") ?: ""
                    val screenshotUrl = doc.getString("screenshotUrl") ?: ""
                    val date = doc.getString("date") ?: ""
                    val isApproved = doc.getBoolean("isApproved") ?: false
                    val rejectionReason = doc.getString("rejectionReason")
                    if (fullName.isNotBlank() || transactionId.isNotBlank() || phoneNumber.isNotBlank()) {
                        list.add(
                            MemberRegistration(
                                id = id,
                                fullName = fullName,
                                universityName = universityName,
                                academicYear = academicYear,
                                phoneNumber = phoneNumber,
                                password = password,
                                paymentMethod = paymentMethod,
                                transactionId = transactionId,
                                screenshotUrl = screenshotUrl,
                                date = date,
                                isApproved = isApproved,
                                rejectionReason = rejectionReason
                            )
                        )
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
            if (list.isNotEmpty()) {
                prefs.edit().putString("member_registrations", gson.toJson(list)).apply()
            }
            Result.success(list)
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure(e)
        }
    }

    companion object {
        val ADMIN_PHONE_NUMBERS = listOf("0953522315", "0955903175")
    }

    fun isAdminPhoneNumber(phone: String): Boolean {
        val clean = phone.replace(Regex("[^0-9]"), "")
        return ADMIN_PHONE_NUMBERS.any { adminPhone ->
            val cleanAdmin = adminPhone.replace(Regex("[^0-9]"), "")
            clean == cleanAdmin || (clean.length >= 9 && cleanAdmin.length >= 9 && clean.endsWith(cleanAdmin.takeLast(9)))
        }
    }

    fun loginUser(phone: String, secretOrKey: String = ""): LoginResult {
        val trimmedPhone = phone.trim()
        val normalizedPhone = EthiopianPhoneUtils.formatInput(trimmedPhone)
        val cleanInput = normalizedPhone.ifBlank { trimmedPhone.replace(Regex("[^0-9]"), "") }

        if (trimmedPhone.isBlank() || cleanInput.isBlank()) {
            return LoginResult.Error("Please enter your phone number.")
        }
        if (secretOrKey.isBlank()) {
            return LoginResult.Error("Please enter your account password.")
        }

        // Validate Ethiopian phone number (must start with 09 or 07, exactly 10 digits)
        if (!isAdminPhoneNumber(trimmedPhone) && !isAdminPhoneNumber(normalizedPhone)) {
            val phoneError = EthiopianPhoneUtils.getValidationError(normalizedPhone)
            if (phoneError != null) {
                return LoginResult.Error(phoneError)
            }
        }

        // Check if admin phone number
        if (isAdminPhoneNumber(trimmedPhone) || isAdminPhoneNumber(normalizedPhone)) {
            // Fetch passcode from Firebase Firestore; fallback to local SharedPrefs
            var adminPasscode = prefs.getString("admin_passcode", "202642434342") ?: "202642434342"
            try {
                val configDoc = com.google.android.gms.tasks.Tasks.await(
                    firestore.collection("app_config").document("admin").get()
                )
                val firebasePasscode = configDoc?.getString("passcode")
                if (!firebasePasscode.isNullOrBlank()) {
                    adminPasscode = firebasePasscode
                    // Cache locally for offline use
                    prefs.edit().putString("admin_passcode", adminPasscode).apply()
                }
            } catch (_: Exception) {
                // Firestore unavailable; use cached/default passcode
            }
            if (secretOrKey.trim() != adminPasscode) {
                return LoginResult.Error("Invalid administrator passcode. Access denied.")
            }

            prefs.edit()
                .putBoolean("user_has_submitted_registration", true)
                .putBoolean("user_is_approved", true)
                .putBoolean("user_is_registered_member", true)
                .putString("user_name", "HU Administrator")
                .putString("user_university", "Haramaya University")
                .putString("user_stream", "")
                .putString("user_academic_year", "2026/2027 Academic Year")
                .putString("user_phone", trimmedPhone)
                .putString("user_password", secretOrKey.trim())
                .putString("user_transaction_id", "")
                .putString("user_payment_method", "")
                .putString("user_registration_date", "")
                .putString("user_rejection_reason", null)
                .putBoolean("user_is_admin", true)
                .apply()
            return LoginResult.Success(isAdmin = true)
        }

        // --- Firebase Firestore cross-device login ---
        // Try to find the user in Firestore using their phone number as document ID
        val docId = cleanInput.ifBlank { return LoginResult.Error("Invalid phone number.") }
        var firestoreMatch: MemberRegistration? = null
        try {
            val doc = com.google.android.gms.tasks.Tasks.await(
                firestore.collection("member_registrations").document(docId).get()
            )
            if (doc != null && doc.exists()) {
                val fullName = doc.getString("fullName") ?: ""
                val universityName = doc.getString("universityName") ?: ""
                val academicYear = doc.getString("academicYear") ?: ""
                val phoneNumber = doc.getString("phoneNumber") ?: trimmedPhone
                val password = doc.getString("password") ?: ""
                val paymentMethod = doc.getString("paymentMethod") ?: ""
                val transactionId = doc.getString("transactionId") ?: ""
                val screenshotUrl = doc.getString("screenshotUrl") ?: ""
                val date = doc.getString("date") ?: ""
                val isApproved = doc.getBoolean("isApproved") ?: false
                val rejectionReason = doc.getString("rejectionReason")
                firestoreMatch = MemberRegistration(
                    id = doc.getString("id") ?: doc.id,
                    fullName = fullName,
                    universityName = universityName,
                    academicYear = academicYear,
                    phoneNumber = phoneNumber,
                    password = password,
                    paymentMethod = paymentMethod,
                    transactionId = transactionId,
                    screenshotUrl = screenshotUrl,
                    date = date,
                    isApproved = isApproved,
                    rejectionReason = rejectionReason
                )
            }
        } catch (e: Exception) {
            // No internet – fall through to local cache
            android.util.Log.w("AppRepository", "Firestore login fetch failed, using local cache: ${e.localizedMessage}")
        }

        // Also check local cache as fallback
        val localList = loadMemberRegistrations()
        val localMatch = localList.firstOrNull {
            val pClean = it.phoneNumber.replace(Regex("[^0-9]"), "")
            cleanInput.isNotBlank() && pClean.isNotBlank() && (pClean == cleanInput || pClean.endsWith(cleanInput) || cleanInput.endsWith(pClean))
        }

        val match = firestoreMatch ?: localMatch

        if (match != null) {
            // Strict password check
            if (match.password.isNotBlank() && match.password.trim() != secretOrKey.trim()) {
                return LoginResult.Error("Incorrect password. Please check your password and try again.")
            }

            prefs.edit()
                .putBoolean("user_has_submitted_registration", true)
                .putBoolean("user_is_approved", match.isApproved)
                .putBoolean("user_is_registered_member", match.isApproved)
                .putString("user_name", match.fullName)
                .putString("user_university", match.universityName)
                .putString("user_academic_year", match.academicYear)
                .putString("user_phone", match.phoneNumber)
                .putString("user_password", match.password.ifBlank { secretOrKey.trim() })
                .putString("user_transaction_id", match.transactionId)
                .putString("user_payment_method", match.paymentMethod)
                .putString("user_registration_date", match.date)
                .putString("user_rejection_reason", match.rejectionReason)
                .putBoolean("user_is_admin", false)
                .apply()

            // Cache the registration locally for offline use
            val updatedList = localList.toMutableList()
            val existingIdx = updatedList.indexOfFirst {
                it.phoneNumber.replace(Regex("[^0-9]"), "") == cleanInput
            }
            if (existingIdx >= 0) updatedList[existingIdx] = match else updatedList.add(0, match)
            prefs.edit().putString("member_registrations", com.google.gson.Gson().toJson(updatedList)).apply()

            return LoginResult.Success(isAdmin = false)
        }

        return LoginResult.Error("No registered member account found with phone: $phone. Please register as a member first.")
    }

    fun logoutUser() {
        prefs.edit()
            .putBoolean("user_has_submitted_registration", false)
            .putBoolean("user_is_approved", false)
            .putBoolean("user_is_registered_member", false)
            .putBoolean("user_is_admin", false)
            .putString("user_name", "")
            .putString("user_transaction_id", "")
            .putString("user_phone", "")
            .putString("user_password", "")
            .putString("user_rejection_reason", null)
            .apply()
    }

    fun deleteUserAccount() {
        val curTxn = prefs.getString("user_transaction_id", "") ?: ""
        val curPhone = prefs.getString("user_phone", "") ?: ""

        val list = loadMemberRegistrations().toMutableList()
        list.removeAll { (curTxn.isNotBlank() && it.transactionId == curTxn) || (curPhone.isNotBlank() && it.phoneNumber == curPhone) }
        prefs.edit().putString("member_registrations", gson.toJson(list)).apply()

        logoutUser()
    }

    // Reports
    suspend fun loadReports(): List<ReportItem> = withContext(Dispatchers.IO) {
        if (cachedReports.isNotEmpty()) return@withContext cachedReports
        val json = prefs.getString("reported_questions", "[]")
        val type = object : TypeToken<MutableList<ReportItem>>() {}.type
        val list: MutableList<ReportItem> = gson.fromJson(json, type) ?: mutableListOf()
        cachedReports = list
        list
    }

    suspend fun addReport(questionId: String, snippet: String, reason: String): Boolean = withContext(Dispatchers.IO) {
        val reports = loadReports().toMutableList()
        val item = ReportItem(
            id = "rep_" + System.currentTimeMillis(),
            questionId = questionId,
            questionSnippet = snippet,
            reason = reason,
            date = "Just now"
        )
        reports.add(0, item)
        cachedReports = reports
        prefs.edit().putString("reported_questions", gson.toJson(reports)).apply()
        true
    }

    suspend fun resolveReport(reportId: String) = withContext(Dispatchers.IO) {
        cachedReports.removeAll { it.id == reportId }
        prefs.edit().putString("reported_questions", gson.toJson(cachedReports)).apply()
    }

    // Exam statistics
    fun getCompletedExamsCount(): Int = prefs.getInt("stat_completed_exams", 0)
    fun incrementCompletedExams() {
        prefs.edit().putInt("stat_completed_exams", getCompletedExamsCount() + 1).apply()
    }

    fun getTotalQuestionsAnswered(): Int = prefs.getInt("stat_total_answered", 0)
    fun recordAnswerStats(totalAnswered: Int, totalCorrect: Int) {
        val curAnswered = getTotalQuestionsAnswered()
        val curCorrect = prefs.getInt("stat_total_correct", 0)
        prefs.edit()
            .putInt("stat_total_answered", curAnswered + totalAnswered)
            .putInt("stat_total_correct", curCorrect + totalCorrect)
            .apply()
    }

    suspend fun updateAdminPasscodeInFirebase(newPasscode: String): Boolean =
        withContext(Dispatchers.IO) {
            try {
                val data = mapOf("passcode" to newPasscode)
                com.google.android.gms.tasks.Tasks.await(
                    firestore.collection("app_config").document("admin")
                        .set(data, SetOptions.merge())
                )
                // Cache locally
                prefs.edit().putString("admin_passcode", newPasscode).apply()
                true
            } catch (e: Exception) {
                e.printStackTrace()
                false
            }
        }
}
