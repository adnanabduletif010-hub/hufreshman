package com.curiovana.hufreshman.viewmodel

import android.app.Application
import androidx.compose.runtime.mutableStateMapOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.curiovana.hufreshman.data.*
import com.google.firebase.firestore.ListenerRegistration
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = AppRepository(application)

    private var registrationsListener: ListenerRegistration? = null
    private var userApprovalListener: ListenerRegistration? = null

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _allQuestions = MutableStateFlow<List<ExamQuestion>>(emptyList())
    val allQuestions: StateFlow<List<ExamQuestion>> = _allQuestions.asStateFlow()

    private val _universities = MutableStateFlow<List<UniversityGuide>>(emptyList())
    val universities: StateFlow<List<UniversityGuide>> = _universities.asStateFlow()

    private val _announcements = MutableStateFlow<List<Announcement>>(emptyList())
    val announcements: StateFlow<List<Announcement>> = _announcements.asStateFlow()

    private val _communityPosts = MutableStateFlow<List<CommunityPost>>(emptyList())
    val communityPosts: StateFlow<List<CommunityPost>> = _communityPosts.asStateFlow()

    private val _bookmarks = MutableStateFlow<Set<String>>(emptySet())
    val bookmarks: StateFlow<Set<String>> = _bookmarks.asStateFlow()

    private val _userProfile = MutableStateFlow(repository.getUserProfile())
    val userProfile: StateFlow<UserProfile> = _userProfile.asStateFlow()

    private val _memberRegistrations = MutableStateFlow<List<MemberRegistration>>(emptyList())
    val memberRegistrations: StateFlow<List<MemberRegistration>> = _memberRegistrations.asStateFlow()

    val isSyncing = MutableStateFlow(false)
    val syncStatusMessage = MutableStateFlow<String?>(null)

    private val _reports = MutableStateFlow<List<ReportItem>>(emptyList())
    val reports: StateFlow<List<ReportItem>> = _reports.asStateFlow()

    // Filters for Question Board
    val selectedCourse = MutableStateFlow<String?>(null)
    val selectedUniversity = MutableStateFlow<String?>(null)
    val selectedCategory = MutableStateFlow<String?>(null)
    val searchQuery = MutableStateFlow("")

    val practiceMode = MutableStateFlow(ExamPracticeMode.PRACTICE)

    // Interactive Exam State
    val userAnswers = mutableStateMapOf<String, Int>()
    val showExplanation = mutableStateMapOf<String, Boolean>()

    val timerSecondsLeft = MutableStateFlow(1800)
    val isTimerRunning = MutableStateFlow(false)
    val examSubmitted = MutableStateFlow(false)
    val examScore = MutableStateFlow<Pair<Int, Int>?>(null)

    private var timerJob: Job? = null

    val selectedUniversityForDetail = MutableStateFlow<UniversityGuide?>(null)

    // Drill-down navigation states for Exam flow:
    // Level 0: Subject List -> Level 1: Choice (Mid vs Final) -> Level 2: Year List -> Level 3: Questions
    val activeSubject = MutableStateFlow<SubjectCategory?>(null)
    val activeExamType = MutableStateFlow<String?>(null) // "Mid Exam" or "Final Exam"
    val activeYear = MutableStateFlow<String?>(null)

    // Subject Categories with authentic images & color palettes
    val subjectCategories = listOf(
        SubjectCategory(
            id = "c1",
            name = "General Physics",
            shortName = "Physics",
            description = "Mechanics, Vectors, Optics & Thermodynamics",
            colorHex = 0xFF0052FE,
            imageUrl = "https://images.unsplash.com/photo-1636466497217-26a8cbeaf0aa?w=800&auto=format&fit=crop&q=80"
        ),
        SubjectCategory(
            id = "c2",
            name = "Applied Mathematics I",
            shortName = "Applied Math I",
            description = "Limits, Differential & Integral Calculus",
            colorHex = 0xFF4F46E5,
            imageUrl = "https://images.unsplash.com/photo-1635070041078-e363dbe005cb?w=800&auto=format&fit=crop&q=80"
        ),
        SubjectCategory(
            id = "c3",
            name = "General Psychology",
            shortName = "General Psychology",
            description = "Cognitive psychology, Memory & Personality",
            colorHex = 0xFF0D9488,
            imageUrl = "https://images.unsplash.com/photo-1507413245164-6160d8298b31?w=800&auto=format&fit=crop&q=80"
        ),
        SubjectCategory(
            id = "c4",
            name = "Logic and Critical Thinking",
            shortName = "Logic & CT",
            description = "Fallacies, Syllogisms & Reasoning",
            colorHex = 0xFFD97706,
            imageUrl = "https://images.unsplash.com/photo-1529699211952-734e80c4d42b?w=800&auto=format&fit=crop&q=80"
        ),
        SubjectCategory(
            id = "c5",
            name = "Communicative English",
            shortName = "Communicative English",
            description = "Grammar, Reading & Academic Writing",
            colorHex = 0xFF059669,
            imageUrl = "https://images.unsplash.com/photo-1456513080510-7bf3a84b82f8?w=800&auto=format&fit=crop&q=80"
        ),
        SubjectCategory(
            id = "c6",
            name = "Geography of Ethiopia and the Horn",
            shortName = "Geography of Ethiopia",
            description = "Topography, Climate & Demographics",
            colorHex = 0xFF2563EB,
            imageUrl = "https://images.unsplash.com/photo-1524661135-423995f22d0b?w=800&auto=format&fit=crop&q=80"
        ),
        SubjectCategory(
            id = "c7",
            name = "Freshman COC",
            shortName = "Freshman COC",
            description = "Comprehensive Stream Certification Practice",
            colorHex = 0xFF7C3AED,
            imageUrl = "https://images.unsplash.com/photo-1523240795612-9a054b0db644?w=800&auto=format&fit=crop&q=80"
        ),
        SubjectCategory(
            id = "c8",
            name = "Emerging Technologies",
            shortName = "Emerging Tech",
            description = "AI, Cloud, IoT & Blockchain Fundamentals",
            colorHex = 0xFF6366F1,
            imageUrl = "https://images.unsplash.com/photo-1618005182384-a83a8bd57fbe?w=800&auto=format&fit=crop&q=80"
        ),
        SubjectCategory(
            id = "c9",
            name = "Moral and Civics Education",
            shortName = "Civics & Ethics",
            description = "Ethics, Constitution & Social Morality",
            colorHex = 0xFFE11D48,
            imageUrl = "https://images.unsplash.com/photo-1589829545856-d10d557cf95f?w=800&auto=format&fit=crop&q=80"
        ),
        SubjectCategory(
            id = "c10",
            name = "General Law",
            shortName = "General Law",
            description = "Legal concepts, jurisprudence & constitutional law",
            colorHex = 0xFF475569,
            imageUrl = "https://images.unsplash.com/photo-1505664194779-8beaceb93744?w=800&auto=format&fit=crop&q=80"
        )
    )

    // Available years for the selected subject and exam type
    val availableExamYears: StateFlow<List<String>> = combine(
        _allQuestions,
        activeSubject,
        activeExamType
    ) { all, sub, type ->
        if (sub == null || type == null) {
            emptyList()
        } else {
            val matching = all.filter { q ->
                q.course.contains(sub.name, ignoreCase = true) &&
                q.category.contains(type, ignoreCase = true)
            }.map { it.year }.distinct()
            if (matching.isNotEmpty()) matching.sortedDescending()
            else listOf("2024 Exam", "2023 Exam", "2022 Exam")
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    // Filtered questions flow based on drill-down and search
    val filteredQuestions: StateFlow<List<ExamQuestion>> = combine(
        _allQuestions,
        combine(activeSubject, activeExamType, activeYear) { sub, type, yr -> Triple(sub, type, yr) },
        selectedUniversity,
        searchQuery
    ) { all, (sub, examType, year), univ, query ->
        all.filter { q ->
            val matchSub = sub == null || q.course.contains(sub.name, ignoreCase = true)
            val matchType = examType == null || q.category.contains(examType, ignoreCase = true)
            val matchYear = year == null || q.year.contains(year, ignoreCase = true)
            val matchUniv = univ == null || q.university.contains(univ, ignoreCase = true)
            val matchQuery = query.isBlank() ||
                    q.question.contains(query, ignoreCase = true) ||
                    q.course.contains(query, ignoreCase = true) ||
                    q.university.contains(query, ignoreCase = true)
            matchSub && matchType && matchYear && matchUniv && matchQuery
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun selectSubject(subject: SubjectCategory) {
        activeSubject.value = subject
        activeExamType.value = null
        activeYear.value = null
    }

    fun selectExamType(type: String) {
        activeExamType.value = type
        activeYear.value = null
    }

    fun selectExamYear(year: String) {
        activeYear.value = year
    }

    fun navigateBackInExamFlow(): Boolean {
        return when {
            activeYear.value != null -> {
                activeYear.value = null
                true
            }
            activeExamType.value != null -> {
                activeExamType.value = null
                true
            }
            activeSubject.value != null -> {
                activeSubject.value = null
                true
            }
            else -> false
        }
    }

    fun registerMember(
        fullName: String,
        universityName: String,
        academicYear: String,
        phoneNumber: String,
        password: String,
        paymentMethod: String,
        transactionId: String
    ) {
        viewModelScope.launch {
            val reg = MemberRegistration(
                fullName = fullName,
                universityName = universityName,
                academicYear = academicYear,
                phoneNumber = phoneNumber,
                password = password,
                paymentMethod = paymentMethod,
                transactionId = transactionId,
                date = "Sep 19, 2026",
                isApproved = false
            )
            repository.saveMemberRegistration(reg)
            _userProfile.value = repository.getUserProfile()
            _memberRegistrations.value = repository.loadMemberRegistrations()
            observeCurrentUserApproval(phoneNumber)
        }
    }

    fun approveMemberRegistration(regId: String) {
        viewModelScope.launch {
            repository.approveMemberRegistration(regId)
            _userProfile.value = repository.getUserProfile()
            _memberRegistrations.value = repository.loadMemberRegistrations()
        }
    }

    fun rejectMemberRegistration(regId: String, reason: String) {
        viewModelScope.launch {
            repository.rejectMemberRegistration(regId, reason)
            _userProfile.value = repository.getUserProfile()
            _memberRegistrations.value = repository.loadMemberRegistrations()
        }
    }

    fun refreshUserProfile() {
        viewModelScope.launch {
            val phone = repository.getUserProfile().phoneNumber
            if (phone.isNotBlank()) {
                repository.syncCurrentUserFromFirestore(phone)
            }
            _userProfile.value = repository.getUserProfile()
            _memberRegistrations.value = repository.loadMemberRegistrations()
        }
    }

    fun refreshAdminRegistrations(onResult: (Boolean, String) -> Unit = { _, _ -> }) {
        viewModelScope.launch {
            isSyncing.value = true
            val result = repository.fetchMemberRegistrationsFromFirestore()
            isSyncing.value = false
            result.onSuccess { list ->
                _memberRegistrations.value = list
                val msg = "Synced ${list.size} registrations from Cloud Firestore"
                syncStatusMessage.value = null
                onResult(true, msg)
            }.onFailure { err ->
                val errorMsg = when {
                    err.message?.contains("SERVICE_DISABLED", ignoreCase = true) == true ||
                    err.message?.contains("has not been used", ignoreCase = true) == true ->
                        "Cloud Firestore is not enabled in Firebase Console for hu-freshman1. Please create the database in Firebase Console."
                    err.message?.contains("PERMISSION_DENIED", ignoreCase = true) == true ->
                        "Firebase Permission Denied. Please set Firestore Rules to allow read, write in Firebase Console."
                    else -> "Cloud Sync error: ${err.localizedMessage ?: "Unknown error"}"
                }
                syncStatusMessage.value = errorMsg
                onResult(false, errorMsg)
            }
        }
    }

    fun isAdminPhoneNumber(phone: String): Boolean = repository.isAdminPhoneNumber(phone)

    fun login(phone: String, secretOrKey: String = ""): LoginResult {
        val result = repository.loginUser(phone, secretOrKey)
        if (result is LoginResult.Success) {
            _userProfile.value = repository.getUserProfile()
            _memberRegistrations.value = repository.loadMemberRegistrations()
            val userPhone = repository.getUserProfile().phoneNumber
            if (userPhone.isNotBlank()) {
                observeCurrentUserApproval(userPhone)
            }
        }
        return result
    }

    fun logout() {
        repository.logoutUser()
        userApprovalListener?.remove()
        userApprovalListener = null
        _userProfile.value = repository.getUserProfile()
    }

    fun deleteAccount() {
        repository.deleteUserAccount()
        userApprovalListener?.remove()
        userApprovalListener = null
        _userProfile.value = repository.getUserProfile()
        _memberRegistrations.value = repository.loadMemberRegistrations()
    }

    init {
        loadInitialData()
        startRealtimeSync()
    }

    private fun startRealtimeSync() {
        registrationsListener?.remove()
        registrationsListener = repository.listenToMemberRegistrations { updatedList ->
            _memberRegistrations.value = updatedList
            val currentPhone = repository.getUserProfile().phoneNumber
            if (currentPhone.isNotBlank()) {
                val clean = repository.cleanPhone(currentPhone)
                val myMatch = updatedList.firstOrNull { repository.cleanPhone(it.phoneNumber) == clean }
                if (myMatch != null && myMatch.isApproved != _userProfile.value.isApproved) {
                    _userProfile.value = repository.getUserProfile()
                }
            }
        }

        val currentPhone = repository.getUserProfile().phoneNumber
        if (currentPhone.isNotBlank()) {
            observeCurrentUserApproval(currentPhone)
        }
    }

    fun observeCurrentUserApproval(phone: String) {
        userApprovalListener?.remove()
        userApprovalListener = repository.listenToUserApproval(phone) { _, _ ->
            _userProfile.value = repository.getUserProfile()
        }
    }

    private fun loadInitialData() {
        viewModelScope.launch {
            _isLoading.value = true
            _bookmarks.value = repository.getBookmarks()
            _userProfile.value = repository.getUserProfile()
            _memberRegistrations.value = repository.loadMemberRegistrations()

            val q = repository.loadQuestions()
            _allQuestions.value = q

            val u = repository.loadUniversities()
            _universities.value = u

            val a = repository.loadAnnouncements()
            _announcements.value = a

            val p = repository.loadCommunityPosts()
            _communityPosts.value = p

            val r = repository.loadReports()
            _reports.value = r

            _isLoading.value = false
        }
    }

    fun selectAnswer(questionId: String, optionIndex: Int) {
        userAnswers[questionId] = optionIndex
    }

    fun toggleExplanation(questionId: String) {
        showExplanation[questionId] = !(showExplanation[questionId] ?: false)
    }

    fun toggleBookmark(questionId: String) {
        val isNowBookmarked = repository.toggleBookmark(questionId)
        _bookmarks.value = repository.getBookmarks()
    }

    fun startTimedExam(durationSeconds: Int = 1800) {
        practiceMode.value = ExamPracticeMode.TIMED
        timerSecondsLeft.value = durationSeconds
        isTimerRunning.value = true
        examSubmitted.value = false
        examScore.value = null
        userAnswers.clear()
        showExplanation.clear()

        timerJob?.cancel()
        timerJob = viewModelScope.launch {
            while (timerSecondsLeft.value > 0 && isTimerRunning.value) {
                delay(1000)
                timerSecondsLeft.value -= 1
            }
            if (timerSecondsLeft.value <= 0) {
                submitExam()
            }
        }
    }

    fun submitExam() {
        timerJob?.cancel()
        isTimerRunning.value = false
        examSubmitted.value = true

        val currentList = filteredQuestions.value
        var correct = 0
        currentList.forEach { q ->
            if (userAnswers[q.id] == q.answer) {
                correct++
            }
        }
        val score = Pair(correct, currentList.size)
        examScore.value = score

        repository.incrementCompletedExams()
        repository.recordAnswerStats(currentList.size, correct)
    }

    fun resetExam() {
        timerJob?.cancel()
        isTimerRunning.value = false
        examSubmitted.value = false
        examScore.value = null
        userAnswers.clear()
        showExplanation.clear()
        practiceMode.value = ExamPracticeMode.PRACTICE
    }

    fun toggleLike(postId: String) {
        viewModelScope.launch {
            repository.toggleLike(postId)
            _communityPosts.value = repository.loadCommunityPosts()
        }
    }

    fun addComment(postId: String, commentText: String) {
        if (commentText.isBlank()) return
        viewModelScope.launch {
            val author = _userProfile.value.name
            repository.addComment(postId, commentText, author)
            _communityPosts.value = repository.loadCommunityPosts()
        }
    }

    fun createPost(content: String, tag: String) {
        if (content.isBlank()) return
        viewModelScope.launch {
            val author = _userProfile.value.name
            val role = if (_userProfile.value.isAdmin) "HU Admin" else "Student"
            repository.addPost(content, tag, author, role)
            _communityPosts.value = repository.loadCommunityPosts()
        }
    }

    fun editPost(postId: String, newContent: String, newTag: String) {
        if (newContent.isBlank()) return
        viewModelScope.launch {
            repository.updatePost(postId, newContent, newTag)
            _communityPosts.value = repository.loadCommunityPosts()
        }
    }

    fun deletePost(postId: String) {
        viewModelScope.launch {
            repository.deletePost(postId)
            _communityPosts.value = repository.loadCommunityPosts()
        }
    }

    fun reportQuestion(questionId: String, snippet: String, reason: String) {
        viewModelScope.launch {
            repository.addReport(questionId, snippet, reason)
            _reports.value = repository.loadReports()
        }
    }

    fun verifyAdminPin(pin: String): Boolean {
        if (pin.trim() == "2026") {
            val updated = _userProfile.value.copy(isAdmin = true)
            _userProfile.value = updated
            repository.saveUserProfile(updated)
            return true
        }
        return false
    }

    fun setAdminStatus(isAdmin: Boolean) {
        val updated = _userProfile.value.copy(isAdmin = isAdmin)
        _userProfile.value = updated
        repository.saveUserProfile(updated)
    }

    fun updateProfile(name: String, university: String, stream: String) {
        val updated = _userProfile.value.copy(name = name, university = university, stream = stream)
        _userProfile.value = updated
        repository.saveUserProfile(updated)
    }

    fun addCustomQuestion(
        course: String,
        university: String,
        year: String,
        category: String,
        questionText: String,
        options: List<String>,
        answerIndex: Int,
        explanation: String
    ) {
        viewModelScope.launch {
            val newQ = ExamQuestion(
                id = "admin_q_" + System.currentTimeMillis(),
                course = course,
                university = university,
                year = year,
                category = category,
                question = questionText,
                options = options,
                answer = answerIndex,
                explanation = explanation
            )
            repository.addCustomQuestion(newQ)
            _allQuestions.value = repository.loadQuestions()
        }
    }

    fun resolveReport(reportId: String) {
        viewModelScope.launch {
            repository.resolveReport(reportId)
            _reports.value = repository.loadReports()
        }
    }

    fun updateUniversity(updated: UniversityGuide) {
        viewModelScope.launch {
            repository.updateUniversity(updated)
            _universities.value = repository.loadUniversities()
            if (selectedUniversityForDetail.value?.id == updated.id) {
                selectedUniversityForDetail.value = updated
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        timerJob?.cancel()
        registrationsListener?.remove()
        userApprovalListener?.remove()
    }
}
