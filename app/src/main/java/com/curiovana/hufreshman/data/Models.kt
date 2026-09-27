package com.curiovana.hufreshman.data

data class ExamQuestion(
    val id: String,
    val examId: String? = null,
    val course: String,
    val university: String,
    val year: String,
    val category: String = "Mid Exam",
    val question: String,
    val options: List<String>,
    val answer: Int,
    val explanation: String = "Detailed university solution provided.",
    val originalUniversity: String? = null
)

data class UniversityDetails(
    val locationTransport: String? = null,
    val weather: String? = null,
    val campusesAndFields: String? = null,
    val cafeFood: String? = null,
    val outsideFood: String? = null,
    val dormAndLockers: String? = null,
    val utilities: String? = null,
    val sanitation: String? = null,
    val safetyAdvice: String? = null
)

data class UniversityGuide(
    val id: String,
    val name: String,
    val amharicName: String? = null,
    val location: String,
    val website: String? = null,
    val telegram: String? = null,
    val image: String? = null,
    val description: String,
    val details: UniversityDetails? = null
)

data class Announcement(
    val id: String,
    val title: String,
    val university: String,
    val category: String,
    val date: String,
    val pinned: Boolean = false,
    val content: String,
    val author: String
)

data class Comment(
    val id: String,
    val author: String,
    val content: String,
    val date: String
)

data class CommunityPost(
    val id: String,
    val author: String,
    val role: String = "Student",
    val date: String,
    val content: String,
    val tag: String = "Academic",
    val imageUrl: String? = null,
    val videoUrl: String? = null,
    val youtubeUrl: String? = null,
    val likes: Int = 0,
    val isLiked: Boolean = false,
    val comments: List<Comment> = emptyList()
)

data class UserProfile(
    val name: String = "HU Freshman",
    val university: String = "Haramaya University",
    val stream: String = "Natural Science",
    val academicYear: String = "2026/2027 Academic Year",
    val phoneNumber: String = "",
    val password: String = "",
    val isAdmin: Boolean = false,
    val hasSubmittedRegistration: Boolean = false,
    val isApproved: Boolean = false,
    val isRegisteredMember: Boolean = false,
    val transactionId: String = "",
    val paymentMethod: String = "",
    val registrationDate: String = "",
    val rejectionReason: String? = null,
    // isGuest = true means the user is browsing without logging in
    val isGuest: Boolean = true
)

data class MemberRegistration(
    val id: String = java.util.UUID.randomUUID().toString(),
    val fullName: String = "",
    val universityName: String = "",
    val academicYear: String = "",
    val phoneNumber: String = "",
    val password: String = "",
    val paymentMethod: String = "",
    val transactionId: String = "",
    val screenshotUrl: String = "",
    val date: String = "",
    val isApproved: Boolean = false,
    val rejectionReason: String? = null
)

data class ReportItem(
    val id: String,
    val questionId: String,
    val questionSnippet: String,
    val reason: String,
    val date: String
)

data class SubjectCategory(
    val id: String,
    val name: String,
    val shortName: String,
    val description: String,
    val colorHex: Long,
    val imageUrl: String,
    val totalQuestions: Int = 0
)

enum class ExamPracticeMode {
    PRACTICE, // Instant answers, explanations
    TIMED     // Timed exam session with scoring
}

sealed class LoginResult {
    data class Success(val isAdmin: Boolean) : LoginResult()
    data class Error(val message: String) : LoginResult()
}
