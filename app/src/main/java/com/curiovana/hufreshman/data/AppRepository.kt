package com.curiovana.hufreshman.data

import android.content.Context
import android.content.SharedPreferences
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
            explanation = explanation
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

    suspend fun loadCommunityPosts(): List<CommunityPost> = withContext(Dispatchers.IO) {
        if (cachedPosts.isNotEmpty()) return@withContext cachedPosts

        val posts = mutableListOf<CommunityPost>()
        val savedPostsJson = prefs.getString("community_posts", null)
        if (!savedPostsJson.isNullOrEmpty()) {
            val type = object : TypeToken<MutableList<CommunityPost>>() {}.type
            posts.addAll(gson.fromJson(savedPostsJson, type))
        } else {
            // Default authentic posts
            posts.addAll(
                listOf(
                    CommunityPost(
                        id = "post_hu_1",
                        author = "HU Freshman Academic Board",
                        role = "HU Admin",
                        date = "Today • Official Notice",
                        content = "Welcome to Haramaya University freshman class of 2026/2027! The digital question bank has been updated with past midterm & final exams for Applied Math I, General Physics, and Logic.",
                        tag = "Academic",
                        likes = 48,
                        comments = listOf(
                            Comment("c1", "Dawit K.", "Thank you HU Admin! The step-by-step calculus solutions are really helpful.", "1 hr ago"),
                            Comment("c2", "Selamawit T.", "Where can we find the general physics formulas sheet?", "30 mins ago")
                        )
                    ),
                    CommunityPost(
                        id = "post_hu_2",
                        author = "Kidus Yohannes (HU Engineering)",
                        role = "Student",
                        date = "Yesterday",
                        content = "Tips for First Semester: Don't fall behind on Critical Thinking arguments and Fallacies. Make sure you practice at least 5 past papers per subject before the mid exams!",
                        tag = "Tips",
                        likes = 32,
                        comments = listOf(
                            Comment("c3", "Abebe B.", "Totally agree, informal fallacies tripped a lot of seniors up last year.", "5 hrs ago")
                        )
                    ),
                    CommunityPost(
                        id = "post_hu_3",
                        author = "Campus Life Directorate",
                        role = "HU Admin",
                        date = "2 days ago",
                        content = "Afran Kallo Main Library digital terminals are now operational 24/7 for exam revision. Fast Wi-Fi and power outlets available at Block B.",
                        tag = "Campus Life",
                        likes = 25
                    )
                )
            )
            savePostsToPrefs(posts)
        }

        val likedSet = getLikedPostIds()
        val mappedPosts = posts.map { it.copy(isLiked = likedSet.contains(it.id)) }
        cachedPosts = mappedPosts.toMutableList()
        cachedPosts
    }

    private fun savePostsToPrefs(posts: List<CommunityPost>) {
        prefs.edit().putString("community_posts", gson.toJson(posts)).apply()
    }

    suspend fun addPost(content: String, tag: String, author: String, role: String): CommunityPost = withContext(Dispatchers.IO) {
        val newPost = CommunityPost(
            id = "post_" + System.currentTimeMillis(),
            author = author,
            role = role,
            date = "Just now",
            content = content,
            tag = tag,
            likes = 0,
            isLiked = false,
            comments = emptyList()
        )
        cachedPosts.add(0, newPost)
        savePostsToPrefs(cachedPosts)
        newPost
    }

    suspend fun updatePost(postId: String, newContent: String, newTag: String): CommunityPost? = withContext(Dispatchers.IO) {
        val index = cachedPosts.indexOfFirst { it.id == postId }
        if (index == -1) return@withContext null

        val current = cachedPosts[index]
        val updated = current.copy(content = newContent, tag = newTag)
        cachedPosts[index] = updated
        savePostsToPrefs(cachedPosts)
        updated
    }

    suspend fun deletePost(postId: String): Boolean = withContext(Dispatchers.IO) {
        val removed = cachedPosts.removeAll { it.id == postId }
        if (removed) {
            savePostsToPrefs(cachedPosts)
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
        updated
    }

    suspend fun addComment(postId: String, commentText: String, author: String): CommunityPost? = withContext(Dispatchers.IO) {
        val index = cachedPosts.indexOfFirst { it.id == postId }
        if (index == -1) return@withContext null

        val current = cachedPosts[index]
        val newComment = Comment(
            id = "c_" + System.currentTimeMillis(),
            author = author,
            content = commentText,
            date = "Just now"
        )
        val updated = current.copy(comments = current.comments + newComment)
        cachedPosts[index] = updated
        savePostsToPrefs(cachedPosts)
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
        return UserProfile(
            name = prefs.getString("user_name", "HU Freshman Student") ?: "HU Freshman Student",
            university = prefs.getString("user_university", "Haramaya University") ?: "Haramaya University",
            stream = prefs.getString("user_stream", "Natural Science") ?: "Natural Science",
            academicYear = prefs.getString("user_academic_year", "2026/2027 Academic Year") ?: "2026/2027 Academic Year",
            phoneNumber = prefs.getString("user_phone", "") ?: "",
            password = prefs.getString("user_password", "") ?: "",
            isAdmin = prefs.getBoolean("user_is_admin", false),
            hasSubmittedRegistration = prefs.getBoolean("user_has_submitted_registration", false),
            isApproved = isApproved,
            isRegisteredMember = isApproved,
            transactionId = prefs.getString("user_transaction_id", "") ?: "",
            paymentMethod = prefs.getString("user_payment_method", "") ?: "",
            registrationDate = prefs.getString("user_registration_date", "") ?: "",
            rejectionReason = prefs.getString("user_rejection_reason", null)
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
        prefs.edit()
            .putString("member_registrations", gson.toJson(list))
            .putBoolean("user_has_submitted_registration", true)
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
        val cleanInput = trimmedPhone.replace(Regex("[^0-9]"), "")

        if (trimmedPhone.isBlank()) {
            return LoginResult.Error("Please enter your phone number.")
        }
        if (secretOrKey.isBlank()) {
            return LoginResult.Error("Please enter your account password.")
        }

        // Check if admin phone number
        if (isAdminPhoneNumber(trimmedPhone)) {
            val adminPasscode = prefs.getString("admin_passcode", "2026") ?: "2026"
            if (secretOrKey.trim() != adminPasscode && secretOrKey.trim() != "2026") {
                return LoginResult.Error("Invalid administrator passcode. Access denied.")
            }

            prefs.edit()
                .putBoolean("user_has_submitted_registration", true)
                .putBoolean("user_is_approved", true)
                .putBoolean("user_is_registered_member", true)
                .putString("user_name", "HU Administrator")
                .putString("user_university", "Haramaya University")
                .putString("user_stream", "Faculty Administration")
                .putString("user_academic_year", "Official Admin")
                .putString("user_phone", trimmedPhone)
                .putString("user_password", secretOrKey.trim())
                .putString("user_transaction_id", "OFFICIAL_ADMIN")
                .putString("user_payment_method", "Official Admin")
                .putString("user_registration_date", "Official")
                .putString("user_rejection_reason", null)
                .putBoolean("user_is_admin", true)
                .apply()
            return LoginResult.Success(isAdmin = true)
        }

        // Regular student login
        val list = loadMemberRegistrations()
        val match = list.firstOrNull {
            val pClean = it.phoneNumber.replace(Regex("[^0-9]"), "")
            cleanInput.isNotBlank() && pClean.isNotBlank() && (pClean == cleanInput || pClean.endsWith(cleanInput) || cleanInput.endsWith(pClean))
        }

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
            .putString("user_name", "HU Freshman Student")
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
}
