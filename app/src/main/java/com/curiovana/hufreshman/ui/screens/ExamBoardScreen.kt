package com.curiovana.hufreshman.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items as gridItems
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.Bookmark
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material.icons.outlined.Flag
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import coil.compose.AsyncImage
import com.curiovana.hufreshman.data.ExamPracticeMode
import com.curiovana.hufreshman.data.ExamQuestion
import com.curiovana.hufreshman.data.SubjectCategory
import com.curiovana.hufreshman.ui.theme.*
import com.curiovana.hufreshman.viewmodel.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExamBoardScreen(
    viewModel: MainViewModel,
    onNavigateToAdmin: () -> Unit,
    // Returns true if action is allowed, false if blocked (e.g. guest user)
    onYearSelectedGate: () -> Boolean = { true }
) {
    val activeSubject by viewModel.activeSubject.collectAsState()
    val activeExamType by viewModel.activeExamType.collectAsState()
    val activeYear by viewModel.activeYear.collectAsState()
    val availableYears by viewModel.availableExamYears.collectAsState()
    val questions by viewModel.filteredQuestions.collectAsState()
    val allQuestions by viewModel.allQuestions.collectAsState()
    val searchQuery by viewModel.searchQuery.collectAsState()
    val practiceMode by viewModel.practiceMode.collectAsState()
    val bookmarks by viewModel.bookmarks.collectAsState()

    val timerSeconds by viewModel.timerSecondsLeft.collectAsState()
    val timeSpentSeconds by viewModel.timeSpentSeconds.collectAsState()
    val totalExamSeconds by viewModel.totalExamSeconds.collectAsState()
    val isTimerRunning by viewModel.isTimerRunning.collectAsState()
    val examSubmitted by viewModel.examSubmitted.collectAsState()
    val examScore by viewModel.examScore.collectAsState()

    var showScoreDialog by remember { mutableStateOf(false) }

    LaunchedEffect(examSubmitted, examScore) {
        if (examSubmitted && examScore != null) {
            showScoreDialog = true
        }
    }

    var reportingQuestion by remember { mutableStateOf<ExamQuestion?>(null) }
    var reportReason by remember { mutableStateOf("") }

    val hasExamBack = activeSubject != null || activeExamType != null || activeYear != null || searchQuery.isNotBlank() || reportingQuestion != null || showScoreDialog
    BackHandler(enabled = hasExamBack) {
        when {
            reportingQuestion != null -> reportingQuestion = null
            showScoreDialog -> showScoreDialog = false
            searchQuery.isNotBlank() -> viewModel.searchQuery.value = ""
            else -> viewModel.navigateBackInExamFlow()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        // Navigation Breadcrumb Bar if drilled down
        if (activeSubject != null || searchQuery.isNotBlank()) {
            Surface(
                color = RoyalBlue,
                shadowElevation = 3.dp
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp, vertical = 10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(
                        onClick = {
                            if (searchQuery.isNotBlank()) {
                                viewModel.searchQuery.value = ""
                            } else {
                                viewModel.navigateBackInExamFlow()
                            }
                        },
                        modifier = Modifier.size(34.dp)
                    ) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = Color.White)
                    }

                    Spacer(modifier = Modifier.width(6.dp))

                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = buildString {
                                if (searchQuery.isNotBlank()) {
                                    append("Search: \"$searchQuery\"")
                                } else {
                                    append(activeSubject?.shortName ?: "Exams")
                                    if (activeExamType != null) append(" • $activeExamType")
                                    if (activeYear != null) append(" • $activeYear")
                                }
                            },
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                        Text(
                            text = if (searchQuery.isNotBlank()) "${questions.size} matching questions"
                            else if (activeYear != null) "${questions.size} questions • Tap back to switch year"
                            else if (activeExamType != null) "Choose year from the list below"
                            else "Select Mid or Final exam",
                            color = Color.White.copy(alpha = 0.85f),
                            fontSize = 11.sp
                        )
                    }
                }
            }
        }

        // Search Bar (Shown on level 0 or level 3)
        if (activeSubject == null || activeYear != null) {
            val searchCountText = remember(allQuestions.size) {
                if (allQuestions.isNotEmpty()) "${java.text.NumberFormat.getIntegerInstance().format(allQuestions.size)}+" else "2,000+"
            }
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { viewModel.searchQuery.value = it },
                placeholder = { Text("Search $searchCountText questions by topic, formula, or course...", fontSize = 13.sp) },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = null, tint = RoyalBlue) },
                trailingIcon = {
                    if (searchQuery.isNotEmpty()) {
                        IconButton(onClick = { viewModel.searchQuery.value = "" }) {
                            Icon(Icons.Default.Close, contentDescription = "Clear")
                        }
                    }
                },
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                singleLine = true
            )
        }

        // Main Body based on drill-down state
        Box(modifier = Modifier.weight(1f)) {
            when {
                // If user is searching globally, show matching questions list directly
                searchQuery.isNotBlank() -> {
                    ExamQuestionsListView(
                        questions = questions,
                        viewModel = viewModel,
                        practiceMode = practiceMode,
                        examSubmitted = examSubmitted,
                        timerSeconds = timerSeconds,
                        bookmarks = bookmarks,
                        onReport = { reportingQuestion = it }
                    )
                }

                // Level 0: Subject List with respective images
                activeSubject == null -> {
                    SubjectListView(
                        subjects = viewModel.subjectCategories,
                        allQuestions = allQuestions,
                        onSelectSubject = { viewModel.selectSubject(it) }
                    )
                }

                // Level 1: Choice between Mid Exam and Final Exam
                activeExamType == null -> {
                    ExamTypeChoiceView(
                        subject = activeSubject!!,
                        allQuestions = allQuestions,
                        onSelectExamType = { viewModel.selectExamType(it) }
                    )
                }

                // Level 2: List of Exam Years
                activeYear == null -> {
                    ExamYearsListView(
                        subject = activeSubject!!,
                        examType = activeExamType!!,
                        availableYears = availableYears,
                        allQuestions = allQuestions,
                        onSelectYear = { year ->
                            if (onYearSelectedGate()) {
                                viewModel.selectExamYear(year)
                            }
                        }
                    )
                }

                // Level 3: Exam Questions Display
                else -> {
                    ExamQuestionsListView(
                        questions = questions,
                        viewModel = viewModel,
                        practiceMode = practiceMode,
                        examSubmitted = examSubmitted,
                        timerSeconds = timerSeconds,
                        bookmarks = bookmarks,
                        onReport = { reportingQuestion = it }
                    )
                }
            }
        }
    }

    // Report Dialog
    if (reportingQuestion != null) {
        AlertDialog(
            onDismissRequest = { reportingQuestion = null },
            properties = DialogProperties(decorFitsSystemWindows = false),
            modifier = Modifier.imePadding(),
            title = { Text("Report Question #${reportingQuestion?.id}", fontWeight = FontWeight.Bold) },
            text = {
                Column {
                    Text(reportingQuestion?.question ?: "", maxLines = 2, overflow = TextOverflow.Ellipsis, fontSize = 12.sp)
                    Spacer(modifier = Modifier.height(10.dp))
                    OutlinedTextField(
                        value = reportReason,
                        onValueChange = { reportReason = it },
                        placeholder = { Text("What is wrong with this question or solution?") },
                        modifier = Modifier.fillMaxWidth(),
                        maxLines = 3
                    )
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        val q = reportingQuestion
                        if (q != null && reportReason.isNotBlank()) {
                            viewModel.reportQuestion(q.id, q.question.take(60), reportReason)
                        }
                        reportReason = ""
                        reportingQuestion = null
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = RoyalBlue)
                ) {
                    Text("Submit Report")
                }
            },
            dismissButton = {
                TextButton(onClick = { reportingQuestion = null }) {
                    Text("Cancel")
                }
            }
        )
    }

    // Exam Score Result Dialog
    if (showScoreDialog && examScore != null) {
        val (correct, total) = examScore!!
        val percentage = if (total > 0) (correct * 100) / total else 0
        val spentMinutes = timeSpentSeconds / 60
        val spentSeconds = timeSpentSeconds % 60
        AlertDialog(
            onDismissRequest = { showScoreDialog = false },
            title = {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        if (percentage >= 50) Icons.Default.EmojiEvents else Icons.Default.AutoStories,
                        contentDescription = null,
                        tint = if (percentage >= 50) EmeraldGreen else AmberWarning,
                        modifier = Modifier.size(28.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Exam Completed!", fontWeight = FontWeight.Bold)
                }
            },
            text = {
                Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = "$percentage%",
                        fontSize = 44.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = if (percentage >= 50) EmeraldGreen else RoseRed
                    )
                    Text(
                        text = "Score: $correct out of $total correct",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 15.sp
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Surface(
                        color = RoyalBlue.copy(alpha = 0.08f),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(Icons.Default.Timer, contentDescription = null, tint = RoyalBlue, modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "Time Taken: ${spentMinutes}m ${spentSeconds}s (Timer Held)",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = RoyalBlue
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = when {
                            percentage >= 85 -> "Outstanding! You are well-prepared for Ethiopian University freshman exams."
                            percentage >= 65 -> "Great job! Keep practicing past questions to achieve an A grade."
                            percentage >= 50 -> "Good effort! Review the step-by-step solutions below to clear doubts."
                            else -> "Keep studying! Review your incorrect answers and try practice mode."
                        },
                        fontSize = 13.sp,
                        color = Slate700,
                        textAlign = TextAlign.Center
                    )
                }
            },
            confirmButton = {
                Button(
                    onClick = { showScoreDialog = false },
                    colors = ButtonDefaults.buttonColors(containerColor = RoyalBlue)
                ) {
                    Text("Review Solutions")
                }
            },
            dismissButton = {
                TextButton(onClick = {
                    showScoreDialog = false
                    viewModel.resetExam()
                }) {
                    Text("Retake / Practice")
                }
            }
        )
    }
}

// LEVEL 0: Subject list with descriptive images
@Composable
fun SubjectListView(
    subjects: List<SubjectCategory>,
    allQuestions: List<ExamQuestion>,
    onSelectSubject: (SubjectCategory) -> Unit
) {
    val screenDimensions = rememberScreenDimensions()
    val columns = when {
        screenDimensions.isCompactWidth -> GridCells.Fixed(1)
        screenDimensions.widthClass == WindowWidthSizeClass.EXPANDED -> GridCells.Adaptive(minSize = 180.dp)
        else -> GridCells.Fixed(2)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = if (screenDimensions.isCompactWidth) 12.dp else 16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f, fill = false)) {
                Text(
                    text = "Freshman Exam Courses",
                    fontSize = if (screenDimensions.isCompactWidth) 18.sp else 20.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = "Select a subject to practice past Midterm & Final exams",
                    fontSize = if (screenDimensions.isCompactWidth) 11.sp else 12.sp,
                    color = Slate700
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Box(
                modifier = Modifier
                    .background(RoyalBlue.copy(alpha = 0.1f), RoundedCornerShape(8.dp))
                    .padding(horizontal = 8.dp, vertical = 4.dp)
            ) {
                Text(
                    text = "${allQuestions.size}+ Questions",
                    color = RoyalBlue,
                    fontWeight = FontWeight.Bold,
                    fontSize = 11.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(4.dp))

        LazyVerticalGrid(
            columns = columns,
            horizontalArrangement = Arrangement.spacedBy(if (screenDimensions.isCompactWidth) 8.dp else 12.dp),
            verticalArrangement = Arrangement.spacedBy(if (screenDimensions.isCompactWidth) 10.dp else 14.dp),
            contentPadding = PaddingValues(bottom = 24.dp)
        ) {
            gridItems(subjects, key = { it.id }) { subject ->
                val qCount = remember(allQuestions, subject.name) {
                    allQuestions.count { it.course.contains(subject.name, ignoreCase = true) }
                }
                SubjectCard(
                    subject = subject,
                    questionCount = if (qCount > 0) qCount else 150,
                    isCompact = screenDimensions.isCompactWidth,
                    onClick = { onSelectSubject(subject) }
                )
            }
        }
    }
}

@Composable
fun SubjectCard(
    subject: SubjectCategory,
    questionCount: Int,
    isCompact: Boolean = false,
    onClick: () -> Unit
) {
    val isSecondSemester = subject.id in listOf("c8", "c9", "c10") ||
            subject.description.contains("Second Semester", ignoreCase = true)

    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp),
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
    ) {
        Column {
            // Subject Image with Overlay
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(if (isCompact) 130.dp else 115.dp)
                    .background(Color(subject.colorHex))
            ) {
                AsyncImage(
                    model = subject.imageUrl,
                    contentDescription = subject.name,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )

                // Dark gradient overlay for contrast
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.65f)),
                                startY = 40f
                            )
                        )
                )

                // Top Course Badge
                Box(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(8.dp)
                        .background(
                            if (isSecondSemester) RoyalBlue.copy(alpha = 0.85f) else Color.Black.copy(alpha = 0.5f),
                            RoundedCornerShape(6.dp)
                        )
                        .padding(horizontal = 6.dp, vertical = 2.dp)
                ) {
                    Text(
                        text = if (isSecondSemester) "Semester 2" else "$questionCount Qs",
                        color = Color.White,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                // Bottom Subject Name inside image
                Text(
                    text = subject.shortName,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 13.sp,
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(8.dp),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }

            // Subject details underneath
            Column(modifier = Modifier.padding(10.dp)) {
                Text(
                    text = subject.name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 13.sp,
                    color = MaterialTheme.colorScheme.onSurface,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = subject.description,
                    fontSize = 11.sp,
                    color = if (isSecondSemester) RoyalBlue else Slate700,
                    fontWeight = if (isSecondSemester) FontWeight.SemiBold else FontWeight.Normal,
                    lineHeight = 15.sp,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Composable
fun SubjectHeaderBanner(
    subject: SubjectCategory,
    subtitle: String? = null
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(130.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            AsyncImage(
                model = subject.imageUrl,
                contentDescription = subject.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color.Black.copy(alpha = 0.20f),
                                Color.Black.copy(alpha = 0.85f)
                            )
                        )
                    )
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(14.dp),
                verticalArrangement = Arrangement.Bottom
            ) {
                Box(
                    modifier = Modifier
                        .background(Color(subject.colorHex), RoundedCornerShape(6.dp))
                        .padding(horizontal = 8.dp, vertical = 2.dp)
                ) {
                    Text(
                        text = subject.shortName,
                        color = Color.White,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = subject.name,
                    color = Color.White,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 17.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = subtitle ?: subject.description,
                    color = Color.White.copy(alpha = 0.85f),
                    fontSize = 11.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

// LEVEL 1: Choice between Mid Exam and Final Exam
@Composable
fun ExamTypeChoiceView(
    subject: SubjectCategory,
    allQuestions: List<ExamQuestion>,
    onSelectExamType: (String) -> Unit
) {
    val midCount = remember(allQuestions, subject.name) {
        allQuestions.count { it.course.contains(subject.name, ignoreCase = true) && it.category.contains("Mid", ignoreCase = true) }
    }
    val finalCount = remember(allQuestions, subject.name) {
        allQuestions.count { it.course.contains(subject.name, ignoreCase = true) && it.category.contains("Final", ignoreCase = true) }
    }

    val isSecondSemester = subject.id in listOf("c8", "c9", "c10") ||
            subject.description.contains("Second Semester", ignoreCase = true)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        // Prominent Subject Image Banner
        SubjectHeaderBanner(
            subject = subject,
            subtitle = if (isSecondSemester) "Coming in the Second Semester" else "Practice Midterm & Final past exams"
        )

        if (isSecondSemester) {
            Surface(
                color = RoyalBlue.copy(alpha = 0.08f),
                shape = RoundedCornerShape(14.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.padding(14.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(Icons.Default.Info, contentDescription = null, tint = RoyalBlue, modifier = Modifier.size(24.dp))
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = "This course is offered in the second semester. Past exam papers and questions will be uploaded as the semester progresses.",
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.onSurface,
                        lineHeight = 16.sp
                    )
                }
            }
        }

        Text(
            text = "Choose Exam Category",
            fontSize = 18.sp,
            fontWeight = FontWeight.ExtraBold,
            color = MaterialTheme.colorScheme.onSurface
        )

        // Mid Exam Option Card
        Card(
            shape = RoundedCornerShape(18.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            elevation = CardDefaults.cardElevation(defaultElevation = 3.dp),
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onSelectExamType("Mid Exam") }
                .border(1.5.dp, RoyalBlue.copy(alpha = 0.4f), RoundedCornerShape(18.dp))
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(54.dp)
                        .background(RoyalBlue, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(Icons.Default.MenuBook, contentDescription = null, tint = Color.White, modifier = Modifier.size(28.dp))
                }

                Spacer(modifier = Modifier.width(16.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "Mid Exam",
                        fontWeight = FontWeight.Black,
                        fontSize = 18.sp,
                        color = RoyalBlue
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Covers Chapters 1-3 • Real University Midterms",
                        fontSize = 12.sp,
                        color = Slate700
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = "${if (midCount > 0) midCount else 80}+ Questions Available",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = EmeraldGreen
                    )
                }

                Icon(Icons.Default.ChevronRight, contentDescription = null, tint = RoyalBlue)
            }
        }

        // Final Exam Option Card
        Card(
            shape = RoundedCornerShape(18.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            elevation = CardDefaults.cardElevation(defaultElevation = 3.dp),
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onSelectExamType("Final Exam") }
                .border(1.5.dp, ElectricIndigo.copy(alpha = 0.4f), RoundedCornerShape(18.dp))
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(54.dp)
                        .background(ElectricIndigo, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(Icons.Default.School, contentDescription = null, tint = Color.White, modifier = Modifier.size(28.dp))
                }

                Spacer(modifier = Modifier.width(16.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "Final Exam",
                        fontWeight = FontWeight.Black,
                        fontSize = 18.sp,
                        color = ElectricIndigo
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Comprehensive Semester Coverage • Past Finals",
                        fontSize = 12.sp,
                        color = Slate700
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = "${if (finalCount > 0) finalCount else 100}+ Questions Available",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = EmeraldGreen
                    )
                }

                Icon(Icons.Default.ChevronRight, contentDescription = null, tint = ElectricIndigo)
            }
        }
    }
}

// LEVEL 2: List of Exam Years
@Composable
fun ExamYearsListView(
    subject: SubjectCategory,
    examType: String,
    availableYears: List<String>,
    allQuestions: List<ExamQuestion>,
    onSelectYear: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Prominent Subject Image Banner
        SubjectHeaderBanner(
            subject = subject,
            subtitle = "$examType • Select past exam paper"
        )

        Spacer(modifier = Modifier.height(14.dp))

        Text(
            text = "Select Exam Year",
            fontSize = 18.sp,
            fontWeight = FontWeight.ExtraBold,
            color = MaterialTheme.colorScheme.onSurface
        )

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(availableYears) { year: String ->
                val countInYear = remember(allQuestions, subject.name, examType, year) {
                    allQuestions.count {
                        it.course.contains(subject.name, ignoreCase = true) &&
                        it.category.contains(examType, ignoreCase = true) &&
                        it.year.contains(year, ignoreCase = true)
                    }
                }

                Card(
                    shape = RoundedCornerShape(14.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onSelectYear(year) }
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(44.dp)
                                .background(RoyalBlue.copy(alpha = 0.1f), RoundedCornerShape(10.dp)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(Icons.Default.CalendarMonth, contentDescription = null, tint = RoyalBlue)
                        }

                        Spacer(modifier = Modifier.width(14.dp))

                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = year,
                                fontWeight = FontWeight.Bold,
                                fontSize = 15.sp,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            Text(
                                text = "Official Haramaya University Exam Papers",
                                fontSize = 11.sp,
                                color = Slate700
                            )
                        }

                        Box(
                            modifier = Modifier
                                .background(EmeraldGreen.copy(alpha = 0.12f), RoundedCornerShape(8.dp))
                                .padding(horizontal = 8.dp, vertical = 4.dp)
                        ) {
                            Text(
                                text = "${if (countInYear > 0) countInYear else 25} Qs",
                                color = EmeraldGreen,
                                fontWeight = FontWeight.Bold,
                                fontSize = 11.sp
                            )
                        }

                        Spacer(modifier = Modifier.width(8.dp))

                        Icon(Icons.Default.ArrowForward, contentDescription = null, tint = Slate700, modifier = Modifier.size(18.dp))
                    }
                }
            }
        }
    }
}

// LEVEL 3: Exam Questions List View
@Composable
fun ExamQuestionsListView(
    questions: List<ExamQuestion>,
    viewModel: MainViewModel,
    practiceMode: ExamPracticeMode,
    examSubmitted: Boolean,
    timerSeconds: Int,
    bookmarks: Set<String>,
    onReport: (ExamQuestion) -> Unit
) {
    // Dynamic timer: 2 minutes (120 seconds) allocated per question for the whole exam
    val examDurationSeconds = maxOf(1, questions.size) * 120
    val examDurationMinutes = examDurationSeconds / 60
    // Hoist here so it is valid inside LazyColumn items block
    val userProfile by viewModel.userProfile.collectAsState()

    Column(modifier = Modifier.fillMaxSize()) {
        // Mode & Timer Bar
        Surface(
            color = if (practiceMode == ExamPracticeMode.TIMED) RoyalBlueDark else MaterialTheme.colorScheme.surface,
            shadowElevation = 2.dp
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    FilterChip(
                        selected = practiceMode == ExamPracticeMode.PRACTICE,
                        onClick = { viewModel.resetExam() },
                        label = { Text("Practice Mode (No Timer)", fontSize = 12.sp, fontWeight = FontWeight.SemiBold) },
                        leadingIcon = { Icon(Icons.Default.MenuBook, contentDescription = null, modifier = Modifier.size(16.dp)) },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = RoyalBlue,
                            selectedLabelColor = Color.White
                        )
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    FilterChip(
                        selected = practiceMode == ExamPracticeMode.TIMED,
                        onClick = { viewModel.startTimedExam(examDurationSeconds) },
                        label = { Text("Timed Exam ($examDurationMinutes min)", fontSize = 12.sp, fontWeight = FontWeight.SemiBold) },
                        leadingIcon = { Icon(Icons.Default.Timer, contentDescription = null, modifier = Modifier.size(16.dp)) },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = ElectricIndigo,
                            selectedLabelColor = Color.White
                        )
                    )
                }

                // In Practice Mode: no timer is needed or shown.
                // In Timed Exam: countdown timer runs, and holds upon submission.
                if (practiceMode == ExamPracticeMode.TIMED) {
                    val minutes = timerSeconds / 60
                    val seconds = timerSeconds % 60
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .background(
                                if (examSubmitted) EmeraldGreen.copy(alpha = 0.25f) else Color.White.copy(alpha = 0.2f),
                                RoundedCornerShape(16.dp)
                            )
                            .padding(horizontal = 10.dp, vertical = 4.dp)
                    ) {
                        Icon(
                            if (examSubmitted) Icons.Default.LockClock else Icons.Default.HourglassBottom,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = if (examSubmitted) {
                                "Held (${String.format("%02d:%02d", minutes, seconds)})"
                            } else {
                                String.format("%02d:%02d", minutes, seconds)
                            },
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 12.sp
                        )
                    }
                }
            }
        }

        if (questions.isEmpty()) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(Icons.Default.SearchOff, contentDescription = null, modifier = Modifier.size(54.dp), tint = Slate700)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("No questions found for this selection", fontWeight = FontWeight.Bold, color = Slate700)
                }
            }
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                item {
                    val currentSubject by viewModel.activeSubject.collectAsState()
                    currentSubject?.let { sub ->
                        Card(
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(64.dp),
                            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                        ) {
                            Box(modifier = Modifier.fillMaxSize()) {
                                AsyncImage(
                                    model = sub.imageUrl,
                                    contentDescription = sub.name,
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier.fillMaxSize()
                                )
                                Box(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .background(Color.Black.copy(alpha = 0.58f))
                                )
                                Row(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(horizontal = 14.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .size(10.dp)
                                            .background(Color(sub.colorHex), CircleShape)
                                    )
                                    Spacer(modifier = Modifier.width(10.dp))
                                    Column {
                                        Text(
                                            text = sub.name,
                                            color = Color.White,
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 13.sp
                                        )
                                        Text(
                                            text = "${sub.shortName} • Practice Questions & Solutions",
                                            color = Color.White.copy(alpha = 0.82f),
                                            fontSize = 11.sp
                                        )
                                    }
                                }
                            }
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(
                                text = "Showing ${questions.size} Questions",
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Bold,
                                color = Slate700
                            )
                            if (practiceMode == ExamPracticeMode.TIMED) {
                                Text(
                                    text = "2 minutes/question ($examDurationMinutes min total)",
                                    fontSize = 11.sp,
                                    color = Slate700
                                )
                            }
                        }
                        if (practiceMode == ExamPracticeMode.TIMED) {
                            if (!examSubmitted) {
                                Button(
                                    onClick = { viewModel.submitExam() },
                                    colors = ButtonDefaults.buttonColors(containerColor = EmeraldGreen),
                                    shape = RoundedCornerShape(8.dp),
                                    contentPadding = PaddingValues(horizontal = 12.dp, vertical = 4.dp)
                                ) {
                                    Icon(Icons.Default.CheckCircle, contentDescription = null, modifier = Modifier.size(16.dp))
                                    Spacer(modifier = Modifier.width(4.dp))
                                    Text("Submit Exam", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                                }
                            } else {
                                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                    OutlinedButton(
                                        onClick = { viewModel.startTimedExam(examDurationSeconds) },
                                        shape = RoundedCornerShape(8.dp),
                                        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 4.dp)
                                    ) {
                                        Icon(Icons.Default.Replay, contentDescription = null, modifier = Modifier.size(14.dp))
                                        Spacer(modifier = Modifier.width(4.dp))
                                        Text("Retake", fontSize = 11.sp)
                                    }
                                    Button(
                                        onClick = { viewModel.submitExam() },
                                        colors = ButtonDefaults.buttonColors(containerColor = RoyalBlue),
                                        shape = RoundedCornerShape(8.dp),
                                        contentPadding = PaddingValues(horizontal = 10.dp, vertical = 4.dp)
                                    ) {
                                        Icon(Icons.Default.EmojiEvents, contentDescription = null, modifier = Modifier.size(14.dp))
                                        Spacer(modifier = Modifier.width(4.dp))
                                        Text("Score", fontSize = 11.sp, fontWeight = FontWeight.Bold)
                                    }
                                }
                            }
                        }
                    }
                }

                itemsIndexed(questions, key = { _, q -> q.id }) { index, question ->
                    QuestionCard(
                        index = index + 1,
                        question = question,
                        selectedAnswer = viewModel.userAnswers[question.id],
                        showSolution = viewModel.showExplanation[question.id] ?: false,
                        isBookmarked = bookmarks.contains(question.id),
                        practiceMode = practiceMode,
                        isSubmitted = examSubmitted,
                        isAdmin = userProfile.isAdmin,
                        onSelectAnswer = { optIdx -> viewModel.selectAnswer(question.id, optIdx) },
                        onToggleSolution = { viewModel.toggleExplanation(question.id) },
                        onToggleBookmark = { viewModel.toggleBookmark(question.id) },
                        onReport = { onReport(question) }
                    )
                }
            }
        }
    }
}

@Composable
fun QuestionCard(
    index: Int,
    question: ExamQuestion,
    selectedAnswer: Int?,
    showSolution: Boolean,
    isBookmarked: Boolean,
    practiceMode: ExamPracticeMode,
    isSubmitted: Boolean,
    isAdmin: Boolean = false,
    onSelectAnswer: (Int) -> Unit,
    onToggleSolution: () -> Unit,
    onToggleBookmark: () -> Unit,
    onReport: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // Badges row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.weight(1f)) {
                    // Course Badge
                    Box(
                        modifier = Modifier
                            .background(RoyalBlue.copy(alpha = 0.1f), RoundedCornerShape(6.dp))
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                    ) {
                        Text(
                            text = question.course,
                            color = RoyalBlue,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }

                    Spacer(modifier = Modifier.width(6.dp))

                    // Dedicated Exam Type Badge: Mid Exam vs Final Exam
                    val isMid = question.category.contains("Mid", ignoreCase = true)
                    val catBadgeColor = if (isMid) EmeraldGreen else RoyalBlue
                    val catBadgeBg = if (isMid) EmeraldGreen.copy(alpha = 0.12f) else RoyalBlue.copy(alpha = 0.12f)
                    val catLabel = if (isMid) "Mid Exam" else if (question.category.contains("Final", ignoreCase = true)) "Final Exam" else question.category

                    Box(
                        modifier = Modifier
                            .background(catBadgeBg, RoundedCornerShape(6.dp))
                            .padding(horizontal = 7.dp, vertical = 4.dp)
                    ) {
                        Text(
                            text = catLabel,
                            color = catBadgeColor,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.ExtraBold
                        )
                    }

                    Spacer(modifier = Modifier.width(6.dp))

                    // Always brand as Haramaya University for students
                    Text(
                        text = "Haramaya University • ${question.year.ifBlank { catLabel }}",
                        fontSize = 11.sp,
                        color = Slate700,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )

                    // Admin-only: source university if from another university
                    if (isAdmin && !question.originalUniversity.isNullOrBlank() && question.originalUniversity != "Haramaya University") {
                        Spacer(modifier = Modifier.width(4.dp))
                        Box(
                            modifier = Modifier
                                .background(AmberWarning.copy(alpha = 0.15f), RoundedCornerShape(4.dp))
                                .padding(horizontal = 5.dp, vertical = 2.dp)
                        ) {
                            Text(
                                text = "Admin: ${question.originalUniversity}",
                                color = AmberWarning,
                                fontSize = 9.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }

                Row {
                    IconButton(onClick = onToggleBookmark, modifier = Modifier.size(28.dp)) {
                        Icon(
                            if (isBookmarked) Icons.Filled.Bookmark else Icons.Outlined.BookmarkBorder,
                            contentDescription = "Bookmark",
                            tint = if (isBookmarked) AmberWarning else Slate700
                        )
                    }
                    IconButton(onClick = onReport, modifier = Modifier.size(28.dp)) {
                        Icon(Icons.Outlined.Flag, contentDescription = "Report", tint = Slate700)
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Question statement
            Text(
                text = "$index. ${question.question}",
                fontWeight = FontWeight.SemiBold,
                fontSize = 15.sp,
                lineHeight = 22.sp,
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Options
            val optionLetters = listOf("A", "B", "C", "D", "E", "F")
            question.options.forEachIndexed { optIdx, optText ->
                val isSelected = selectedAnswer == optIdx
                val isCorrectOption = optIdx == question.answer

                val (optBgColor, optBorderColor, optTextColor) = when {
                    isSubmitted -> {
                        when {
                            isSelected && isCorrectOption -> Triple(EmeraldGreen.copy(alpha = 0.15f), EmeraldGreen, EmeraldGreen)
                            isSelected && !isCorrectOption -> Triple(RoseRed.copy(alpha = 0.15f), RoseRed, RoseRed)
                            isCorrectOption -> Triple(EmeraldGreen.copy(alpha = 0.1f), EmeraldGreen, EmeraldGreen)
                            else -> Triple(Color.Transparent, Color(0xFFE2E8F0), MaterialTheme.colorScheme.onSurface)
                        }
                    }
                    practiceMode == ExamPracticeMode.PRACTICE && isSelected -> {
                        if (isCorrectOption) {
                            Triple(EmeraldGreen.copy(alpha = 0.12f), EmeraldGreen, EmeraldGreen)
                        } else {
                            Triple(RoseRed.copy(alpha = 0.12f), RoseRed, RoseRed)
                        }
                    }
                    practiceMode == ExamPracticeMode.PRACTICE && selectedAnswer != null && isCorrectOption -> {
                        Triple(EmeraldGreen.copy(alpha = 0.1f), EmeraldGreen, EmeraldGreen)
                    }
                    practiceMode == ExamPracticeMode.TIMED && isSelected -> {
                        Triple(RoyalBlue.copy(alpha = 0.12f), RoyalBlue, RoyalBlue)
                    }
                    else -> {
                        Triple(Color.Transparent, Color(0xFFE2E8F0), MaterialTheme.colorScheme.onSurface)
                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(optBgColor)
                        .border(1.dp, optBorderColor, RoundedCornerShape(10.dp))
                        .clickable(enabled = !isSubmitted) { onSelectAnswer(optIdx) }
                        .padding(horizontal = 12.dp, vertical = 10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(26.dp)
                            .background(
                                if (isSelected) optBorderColor else Color(0xFFF1F5F9),
                                CircleShape
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = optionLetters.getOrElse(optIdx) { "?" },
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = if (isSelected) Color.White else Slate700
                        )
                    }

                    Spacer(modifier = Modifier.width(10.dp))

                    Text(
                        text = optText,
                        fontSize = 14.sp,
                        color = optTextColor,
                        modifier = Modifier.weight(1f)
                    )

                    if ((practiceMode == ExamPracticeMode.PRACTICE && selectedAnswer != null) || isSubmitted) {
                        if (isCorrectOption) {
                            Icon(Icons.Default.CheckCircle, contentDescription = "Correct", tint = EmeraldGreen, modifier = Modifier.size(18.dp))
                        } else if (isSelected) {
                            Icon(Icons.Default.Cancel, contentDescription = "Incorrect", tint = RoseRed, modifier = Modifier.size(18.dp))
                        }
                    }
                }
            }

            // Solution Accordion (Practice mode or after Timed submission)
            if (practiceMode == ExamPracticeMode.PRACTICE || isSubmitted) {
                Spacer(modifier = Modifier.height(10.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onToggleSolution() }
                        .padding(vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        if (showSolution) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                        contentDescription = null,
                        tint = RoyalBlue
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = if (showSolution) "Hide Verified Solution" else "Show Verified Solution & Steps",
                        color = RoyalBlue,
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp
                    )
                }

                AnimatedVisibility(visible = showSolution) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 6.dp)
                            .background(RoyalBlue.copy(alpha = 0.06f), RoundedCornerShape(10.dp))
                            .border(1.dp, RoyalBlue.copy(alpha = 0.2f), RoundedCornerShape(10.dp))
                            .padding(12.dp)
                    ) {
                        Column {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(Icons.Default.Verified, contentDescription = null, tint = RoyalBlue, modifier = Modifier.size(16.dp))
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(
                                    text = "Correct Answer: Option ${optionLetters.getOrElse(question.answer) { "?" }}",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 13.sp,
                                    color = RoyalBlue
                                )
                            }
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = question.explanation,
                                fontSize = 13.sp,
                                lineHeight = 19.sp,
                                color = Slate800
                            )
                        }
                    }
                }
            }
        }
    }
}
