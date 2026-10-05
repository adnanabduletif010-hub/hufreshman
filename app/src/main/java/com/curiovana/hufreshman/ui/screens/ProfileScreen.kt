package com.curiovana.hufreshman.ui.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.ui.platform.LocalContext
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.curiovana.hufreshman.data.EthiopianPhoneUtils
import com.curiovana.hufreshman.data.ExamPracticeMode
import com.curiovana.hufreshman.ui.theme.*
import com.curiovana.hufreshman.viewmodel.MainViewModel
import kotlinx.coroutines.launch

@Composable
fun ProfileScreen(
    viewModel: MainViewModel,
    onNavigateToAdmin: () -> Unit
) {
    val userProfile by viewModel.userProfile.collectAsState()
    val bookmarks by viewModel.bookmarks.collectAsState()
    val allQuestions by viewModel.allQuestions.collectAsState()

    var showEditProfileDialog by remember { mutableStateOf(false) }
    var showBookmarksSheet by remember { mutableStateOf(false) }
    var showRegistrationDialog by remember { mutableStateOf(false) }
    var showLoginDialog by remember { mutableStateOf(false) }
    var showLogoutDialog by remember { mutableStateOf(false) }
    var showDeleteAccountDialog by remember { mutableStateOf(false) }
    var showPrivacyDialog by remember { mutableStateOf(false) }
    var showPaymentDialog by remember { mutableStateOf(false) }

    val bookmarkedQuestions = remember(bookmarks, allQuestions) {
        allQuestions.filter { bookmarks.contains(it.id) }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Profile Card
        Card(
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            elevation = CardDefaults.cardElevation(defaultElevation = 3.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(20.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    val displayName = if (userProfile.name.isNotBlank() && !userProfile.name.contains("HU Freshman", ignoreCase = true)) {
                        userProfile.name
                    } else if (userProfile.isGuest) {
                        "Guest Student"
                    } else {
                        "Student"
                    }

                    Box(
                        modifier = Modifier
                            .size(62.dp)
                            .background(
                                Brush.linearGradient(listOf(RoyalBlue, ElectricIndigo)),
                                CircleShape
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = displayName.firstOrNull()?.toString()?.uppercase() ?: "S",
                            color = Color.White,
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 24.sp
                        )
                    }

                    Spacer(modifier = Modifier.width(14.dp))

                    Column(modifier = Modifier.weight(1f)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = displayName,
                                fontWeight = FontWeight.Bold,
                                fontSize = 17.sp,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            if (userProfile.isRegisteredMember) {
                                Spacer(modifier = Modifier.width(6.dp))
                                Box(
                                    modifier = Modifier
                                        .background(EmeraldGreen, RoundedCornerShape(4.dp))
                                        .padding(horizontal = 6.dp, vertical = 2.dp)
                                ) {
                                    Text("✓ Member", color = Color.White, fontSize = 9.sp, fontWeight = FontWeight.Bold)
                                }
                            }
                            if (userProfile.isAdmin) {
                                // Admin gets a subtle shield icon instead of text badge
                                Spacer(modifier = Modifier.width(4.dp))
                                Icon(
                                    Icons.Default.Shield,
                                    contentDescription = null,
                                    tint = RoyalBlue,
                                    modifier = Modifier.size(14.dp)
                                )
                            }
                        }
                        Text(
                            text = userProfile.university,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Medium,
                            color = RoyalBlue
                        )
                        val displayStream = userProfile.stream.trim()
                        val displayYear = if (userProfile.academicYear.contains("admin", ignoreCase = true) || userProfile.academicYear.isBlank()) {
                            "2026/2027 Academic Year"
                        } else {
                            userProfile.academicYear
                        }
                        if (displayStream.isNotBlank()) {
                            Text(
                                text = "$displayStream • $displayYear",
                                fontSize = 11.sp,
                                color = Slate700
                            )
                        } else {
                            Text(
                                text = displayYear,
                                fontSize = 11.sp,
                                color = Slate700
                            )
                        }
                        if (userProfile.phoneNumber.isNotBlank()) {
                            Text(
                                text = "📱 ${userProfile.phoneNumber}",
                                fontSize = 11.sp,
                                color = Slate700
                            )
                        }
                    }

                    IconButton(onClick = { showEditProfileDialog = true }) {
                        Icon(Icons.Default.Edit, contentDescription = "Edit Profile", tint = Slate700)
                    }
                }
            }
        }

        // Study Statistics
        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Study Performance & Practice", fontWeight = FontWeight.Bold, fontSize = 15.sp)
                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    StudyStatBox(
                        modifier = Modifier.weight(1f),
                        icon = Icons.Outlined.School,
                        label = "Question Bank",
                        value = "${allQuestions.size}+",
                        color = RoyalBlue
                    )
                    StudyStatBox(
                        modifier = Modifier.weight(1f),
                        icon = Icons.Outlined.Bookmark,
                        label = "Saved Bookmarks",
                        value = "${bookmarks.size}",
                        color = AmberWarning,
                        onClick = { showBookmarksSheet = true }
                    )
                }
            }
        }

        // Quick Actions List
        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(8.dp)) {
                ListItem(
                    headlineContent = { Text("Saved Exam Questions", fontWeight = FontWeight.SemiBold) },
                    supportingContent = { Text("${bookmarks.size} questions bookmarked for revision", fontSize = 12.sp) },
                    leadingContent = {
                        Box(
                            modifier = Modifier
                                .size(36.dp)
                                .background(AmberWarning.copy(alpha = 0.15f), RoundedCornerShape(8.dp)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(Icons.Filled.Bookmark, contentDescription = null, tint = AmberWarning)
                        }
                    },
                    trailingContent = { Icon(Icons.Default.ChevronRight, contentDescription = null) },
                    modifier = Modifier.clickable { showBookmarksSheet = true }
                )

                HorizontalDivider(modifier = Modifier.padding(horizontal = 8.dp))

                if (userProfile.isAdmin) {
                    ListItem(
                        headlineContent = { Text("Admin Control Panel", fontWeight = FontWeight.SemiBold) },
                        supportingContent = {
                            Text("Unlocked • Manage university & member data", fontSize = 12.sp)
                        },
                        leadingContent = {
                            Box(
                                modifier = Modifier
                                    .size(36.dp)
                                    .background(RoyalBlue.copy(alpha = 0.15f), RoundedCornerShape(8.dp)),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(Icons.Outlined.AdminPanelSettings, contentDescription = null, tint = RoyalBlue)
                            }
                        },
                        trailingContent = { Icon(Icons.Default.ChevronRight, contentDescription = null) },
                        modifier = Modifier.clickable { onNavigateToAdmin() }
                    )

                    HorizontalDivider(modifier = Modifier.padding(horizontal = 8.dp))
                }

                ListItem(
                    headlineContent = { Text("Log In / Switch Account", fontWeight = FontWeight.SemiBold) },
                    supportingContent = { Text("Restore verified member profile with phone number", fontSize = 12.sp) },
                    leadingContent = {
                        Box(
                            modifier = Modifier
                                .size(36.dp)
                                .background(RoyalBlue.copy(alpha = 0.15f), RoundedCornerShape(8.dp)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(Icons.Default.Login, contentDescription = null, tint = RoyalBlue)
                        }
                    },
                    trailingContent = { Icon(Icons.Default.ChevronRight, contentDescription = null) },
                    modifier = Modifier.clickable { showLoginDialog = true }
                )

                HorizontalDivider(modifier = Modifier.padding(horizontal = 8.dp))

                ListItem(
                    headlineContent = { Text("Privacy Policy & Terms", fontWeight = FontWeight.SemiBold) },
                    supportingContent = { Text("Google Play data safety disclosure & user privacy", fontSize = 12.sp) },
                    leadingContent = {
                        Box(
                            modifier = Modifier
                                .size(36.dp)
                                .background(EmeraldGreen.copy(alpha = 0.15f), RoundedCornerShape(8.dp)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(Icons.Default.PrivacyTip, contentDescription = null, tint = EmeraldGreen)
                        }
                    },
                    trailingContent = { Icon(Icons.Default.ChevronRight, contentDescription = null) },
                    modifier = Modifier.clickable { showPrivacyDialog = true }
                )

                if (!userProfile.isApproved && !userProfile.isGuest && !userProfile.isAdmin) {
                    HorizontalDivider(modifier = Modifier.padding(horizontal = 8.dp))

                    ListItem(
                        headlineContent = { Text("Complete Membership Payment", fontWeight = FontWeight.SemiBold, color = RoyalBlue) },
                        supportingContent = { Text("Upload payment screenshot to unlock all exams and notes", fontSize = 12.sp) },
                        leadingContent = {
                            Box(
                                modifier = Modifier
                                    .size(36.dp)
                                    .background(AmberWarning.copy(alpha = 0.15f), RoundedCornerShape(8.dp)),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(Icons.Default.Payment, contentDescription = null, tint = AmberWarning)
                            }
                        },
                        trailingContent = { Icon(Icons.Default.ChevronRight, contentDescription = null) },
                        modifier = Modifier.clickable { showPaymentDialog = true }
                    )
                }

                if (userProfile.hasSubmittedRegistration || userProfile.isAdmin || userProfile.isApproved || !userProfile.isGuest) {
                    HorizontalDivider(modifier = Modifier.padding(horizontal = 8.dp))

                    ListItem(
                        headlineContent = { Text("Log Out", fontWeight = FontWeight.SemiBold, color = RoseRed) },
                        supportingContent = { Text("End current user session on this device", fontSize = 12.sp) },
                        leadingContent = {
                            Box(
                                modifier = Modifier
                                    .size(36.dp)
                                    .background(RoseRed.copy(alpha = 0.15f), RoundedCornerShape(8.dp)),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(Icons.AutoMirrored.Filled.Logout, contentDescription = null, tint = RoseRed)
                            }
                        },
                        trailingContent = { Icon(Icons.Default.ChevronRight, contentDescription = null) },
                        modifier = Modifier.clickable { showLogoutDialog = true }
                    )

                    HorizontalDivider(modifier = Modifier.padding(horizontal = 8.dp))

                    ListItem(
                        headlineContent = { Text("Delete Account & Data", fontWeight = FontWeight.SemiBold, color = RoseRed) },
                        supportingContent = { Text("Permanently erase registration records & data", fontSize = 12.sp) },
                        leadingContent = {
                            Box(
                                modifier = Modifier
                                    .size(36.dp)
                                    .background(RoseRed.copy(alpha = 0.15f), RoundedCornerShape(8.dp)),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(Icons.Default.DeleteForever, contentDescription = null, tint = RoseRed)
                            }
                        },
                        trailingContent = { Icon(Icons.Default.ChevronRight, contentDescription = null) },
                        modifier = Modifier.clickable { showDeleteAccountDialog = true }
                    )
                }
            }
        }

        // About / App Info
        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFEFF6FF)),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("HU Freshman Mobile App", fontWeight = FontWeight.Bold, fontSize = 14.sp, color = RoyalBlue)
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Native Android Edition • Version 1.0\nComprehensive Ethiopian university freshman past exam archive, verified solutions, campus directories, and student feeds.\nZero-data offline study engine powered by Jetpack Compose.",
                    fontSize = 12.sp,
                    lineHeight = 18.sp,
                    color = Slate800
                )
                Spacer(modifier = Modifier.height(10.dp))
                HorizontalDivider(color = Color(0xFFDBEAFE))
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("Official Support & Inquiries:", fontSize = 11.sp, color = Slate700, fontWeight = FontWeight.Medium)
                }
                Spacer(modifier = Modifier.height(6.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    val context = LocalContext.current
                    OutlinedButton(
                        onClick = {
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://t.me/HUfreshman1"))
                            context.startActivity(intent)
                        },
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier.weight(1f).height(38.dp),
                        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 4.dp)
                    ) {
                        Icon(Icons.Default.Send, contentDescription = null, modifier = Modifier.size(14.dp), tint = RoyalBlue)
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("@HUfreshman1", fontSize = 11.sp, color = RoyalBlue)
                    }
                    OutlinedButton(
                        onClick = {
                            val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:0955903175"))
                            context.startActivity(intent)
                        },
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier.weight(1f).height(38.dp),
                        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 4.dp)
                    ) {
                        Icon(Icons.Default.Phone, contentDescription = null, modifier = Modifier.size(14.dp), tint = Slate800)
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("0955903175", fontSize = 11.sp, color = Slate800)
                    }
                }
            }
        }
    }

    // Member Registration Dialog (Telebirr, CBE, E-Birr with Screenshot)
    if (showRegistrationDialog) {
        MemberRegistrationDialog(
            currentProfile = userProfile,
            onDismiss = { showRegistrationDialog = false },
            onSubmit = { name, univ, year, phone, password, paymentMethod, txnId, screenshotUrl ->
                viewModel.registerMember(name, univ, year, phone, password, paymentMethod, txnId, screenshotUrl)
                showRegistrationDialog = false
            },
            onNavigateToLogin = {
                showRegistrationDialog = false
                showLoginDialog = true
            },
            onCheckPhoneRegistered = { phone ->
                viewModel.isPhoneAlreadyRegistered(phone)
            }
        )
    }

    // Payment Verification Dialog
    if (showPaymentDialog) {
        PaymentVerificationDialog(
            phoneNumber = userProfile.phoneNumber,
            onDismiss = { showPaymentDialog = false },
            onPaymentSubmitted = { method, url ->
                viewModel.submitPaymentVerification(method, url)
                showPaymentDialog = false
            }
        )
    }

    // Login Dialog
    if (showLoginDialog) {
        LoginDialog(
            onDismiss = { showLoginDialog = false },
            onLoginSubmit = { phone, secretOrKey ->
                viewModel.login(phone, secretOrKey)
            },
            onNavigateToRegister = {
                showLoginDialog = false
                showRegistrationDialog = true
            }
        )
    }

    // Logout Confirmation Dialog
    if (showLogoutDialog) {
        LogoutConfirmDialog(
            onDismiss = { showLogoutDialog = false },
            onConfirmLogout = {
                viewModel.logout()
            }
        )
    }

    // Delete Account & Personal Data Dialog (Google Play Compliance)
    if (showDeleteAccountDialog) {
        DeleteAccountDialog(
            onDismiss = { showDeleteAccountDialog = false },
            onConfirmDelete = {
                viewModel.deleteAccount()
            }
        )
    }

    // Privacy Policy Dialog (Google Play Compliance)
    if (showPrivacyDialog) {
        PrivacyPolicyDialog(
            onDismiss = { showPrivacyDialog = false }
        )
    }

    // Edit Profile Dialog
    if (showEditProfileDialog) {
        var tempName by remember {
            mutableStateOf(
                if (userProfile.name.contains("HU Freshman", ignoreCase = true) || userProfile.isGuest) "" else userProfile.name
            )
        }
        var tempUniv by remember { mutableStateOf(userProfile.university) }
        var tempStream by remember { mutableStateOf(userProfile.stream) }
        var tempPhone by remember {
            mutableStateOf(
                if (userProfile.phoneNumber.isNotBlank()) EthiopianPhoneUtils.formatInput(userProfile.phoneNumber) else "0"
            )
        }

        AlertDialog(
            onDismissRequest = { showEditProfileDialog = false },
            properties = DialogProperties(decorFitsSystemWindows = false),
            modifier = Modifier.imePadding(),
            title = { Text("Edit Student Profile", fontWeight = FontWeight.Bold) },
            text = {
                Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    OutlinedTextField(
                        value = tempName,
                        onValueChange = { tempName = it },
                        label = { Text("Student Name") },
                        placeholder = { Text("e.g. Dawit Kebede", fontStyle = FontStyle.Italic, color = Color(0xFF94A3B8)) },
                        supportingText = { Text("e.g., Dawit Kebede (First & Father's Name)", fontSize = 11.sp, color = Slate700) },
                        keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Words, imeAction = ImeAction.Next),
                        modifier = Modifier.fillMaxWidth()
                    )
                    OutlinedTextField(value = tempUniv, onValueChange = { tempUniv = it }, label = { Text("University") }, modifier = Modifier.fillMaxWidth())
                    OutlinedTextField(
                        value = tempPhone,
                        onValueChange = { tempPhone = EthiopianPhoneUtils.formatInput(it) },
                        label = { Text("Phone Number") },
                        placeholder = { Text("09... or 07...") },
                        supportingText = {
                            Text("Ethiopian format: starts with 09 or 07 (10 digits)", fontSize = 11.sp, color = Slate700)
                        },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                        modifier = Modifier.fillMaxWidth()
                    )

                    Text("Academic Stream:", fontWeight = FontWeight.Bold, fontSize = 12.sp)
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        listOf("Natural Science", "Social Science").forEach { stream ->
                            FilterChip(
                                selected = tempStream == stream,
                                onClick = { tempStream = stream },
                                label = { Text(stream) }
                            )
                        }
                    }
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        viewModel.updateProfile(tempName, tempUniv, tempStream)
                        showEditProfileDialog = false
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = RoyalBlue)
                ) {
                    Text("Save Changes")
                }
            },
            dismissButton = {
                TextButton(onClick = { showEditProfileDialog = false }) {
                    Text("Cancel")
                }
            }
        )
    }

    // Bookmarked Questions Viewer
    if (showBookmarksSheet) {
        AlertDialog(
            onDismissRequest = { showBookmarksSheet = false },
            title = {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Filled.Bookmark, contentDescription = null, tint = AmberWarning)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Saved Questions (${bookmarkedQuestions.size})", fontWeight = FontWeight.Bold)
                }
            },
            text = {
                if (bookmarkedQuestions.isEmpty()) {
                    Box(modifier = Modifier.fillMaxWidth().height(140.dp), contentAlignment = Alignment.Center) {
                        Text("No bookmarked questions yet.\nTap the bookmark icon on any exam question to save it here!", color = Slate700, fontSize = 13.sp)
                    }
                } else {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(max = 400.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        items(bookmarkedQuestions, key = { it.id }) { q ->
                            QuestionCard(
                                index = 1,
                                question = q,
                                selectedAnswer = viewModel.userAnswers[q.id],
                                showSolution = viewModel.showExplanation[q.id] ?: false,
                                isBookmarked = true,
                                practiceMode = ExamPracticeMode.PRACTICE,
                                isSubmitted = false,
                                onSelectAnswer = { optIdx -> viewModel.selectAnswer(q.id, optIdx) },
                                onToggleSolution = { viewModel.toggleExplanation(q.id) },
                                onToggleBookmark = { viewModel.toggleBookmark(q.id) },
                                onReport = {}
                            )
                        }
                    }
                }
            },
            confirmButton = {
                Button(
                    onClick = { showBookmarksSheet = false },
                    colors = ButtonDefaults.buttonColors(containerColor = RoyalBlue)
                ) {
                    Text("Done")
                }
            }
        )
    }
}

@Composable
fun MemberRegistrationDialog(
    currentProfile: com.curiovana.hufreshman.data.UserProfile,
    onDismiss: () -> Unit,
    onSubmit: (name: String, university: String, academicYear: String, phone: String, password: String, paymentMethod: String, transactionId: String, screenshotUrl: String) -> Unit,
    onNavigateToLogin: (() -> Unit)? = null,
    onCheckPhoneRegistered: (suspend (String) -> Boolean)? = null
) {
    val regScope = rememberCoroutineScope()
    var isCheckingPhone by remember { mutableStateOf(false) }
    var name by remember {
        mutableStateOf(
            if (currentProfile.name.contains("HU Freshman", ignoreCase = true) || currentProfile.isGuest) "" else currentProfile.name
        )
    }
    var university by remember { mutableStateOf(currentProfile.university) }
    var academicYear by remember { mutableStateOf(currentProfile.academicYear) }
    var phoneNumber by remember {
        mutableStateOf(
            if (currentProfile.phoneNumber.isNotBlank()) EthiopianPhoneUtils.formatInput(currentProfile.phoneNumber) else "0"
        )
    }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false, decorFitsSystemWindows = false)
    ) {
        Surface(
            shape = RoundedCornerShape(20.dp),
            color = MaterialTheme.colorScheme.surface,
            modifier = Modifier
                .fillMaxWidth(0.95f)
                .fillMaxHeight(0.92f)
                .imePadding()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp)
            ) {
                // ── Header ──
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.AppRegistration, contentDescription = null, tint = RoyalBlue, modifier = Modifier.size(24.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Create Account",
                            fontWeight = FontWeight.Bold, fontSize = 17.sp
                        )
                    }
                    IconButton(onClick = onDismiss) {
                        Icon(Icons.Default.Close, contentDescription = "Close")
                    }
                }
                Spacer(modifier = Modifier.height(10.dp))

                // ══ Account Info Form ══
                    // Already have an account? Log In  (shown at very top of step 1)
                    if (onNavigateToLogin != null) {
                        Surface(
                            color = RoyalBlue.copy(alpha = 0.08f),
                            shape = RoundedCornerShape(10.dp),
                            modifier = Modifier.fillMaxWidth().clickable { onDismiss(); onNavigateToLogin() }
                        ) {
                            Row(
                                modifier = Modifier.padding(horizontal = 14.dp, vertical = 10.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(Icons.Default.Login, contentDescription = null, tint = RoyalBlue, modifier = Modifier.size(18.dp))
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text("Already have an account?", fontSize = 13.sp, color = Slate800, fontWeight = FontWeight.Medium)
                                }
                                Text("Log In →", fontSize = 13.sp, color = RoyalBlue, fontWeight = FontWeight.Bold)
                            }
                        }
                        Spacer(modifier = Modifier.height(12.dp))
                    }

                    Column(
                        modifier = Modifier.weight(1f).verticalScroll(rememberScrollState()),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        OutlinedTextField(
                            value = name, onValueChange = { name = it; errorMessage = "" },
                            label = { Text("Full Name *") },
                            placeholder = { Text("e.g. Dawit Kebede", fontStyle = FontStyle.Italic, color = Color(0xFF94A3B8)) },
                            supportingText = {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                                ) {
                                    Text(
                                        text = "Example:",
                                        fontSize = 11.sp,
                                        fontWeight = FontWeight.SemiBold,
                                        color = RoyalBlue
                                    )
                                    Text(
                                        text = "Dawit Kebede (First & Father's Name)",
                                        fontSize = 11.sp,
                                        color = Slate700
                                    )
                                }
                            },
                            leadingIcon = { Icon(Icons.Default.Person, contentDescription = null, tint = RoyalBlue) },
                            keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Words, imeAction = ImeAction.Next),
                            modifier = Modifier.fillMaxWidth(), singleLine = true,
                            isError = errorMessage.isNotBlank() && name.isBlank()
                        )
                        OutlinedTextField(
                            value = university, onValueChange = { university = it },
                            label = { Text("University Name") },
                            placeholder = { Text("e.g. Haramaya University", fontStyle = FontStyle.Italic, color = Color(0xFF94A3B8)) },
                            leadingIcon = { Icon(Icons.Default.School, contentDescription = null, tint = RoyalBlue) },
                            keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Words, imeAction = ImeAction.Next),
                            modifier = Modifier.fillMaxWidth(), singleLine = true
                        )
                        OutlinedTextField(
                            value = academicYear, onValueChange = { academicYear = it },
                            label = { Text("Academic Year") },
                            placeholder = { Text("e.g. 2026/2027 Freshman", fontStyle = FontStyle.Italic, color = Color(0xFF94A3B8)) },
                            leadingIcon = { Icon(Icons.Default.CalendarMonth, contentDescription = null, tint = RoyalBlue) },
                            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                            modifier = Modifier.fillMaxWidth(), singleLine = true
                        )
                        OutlinedTextField(
                            value = phoneNumber,
                            onValueChange = {
                                phoneNumber = EthiopianPhoneUtils.formatInput(it)
                                errorMessage = ""
                            },
                            label = { Text("Phone Number *") },
                            placeholder = { Text("09... or 07...") },
                            supportingText = {
                                Text("Ethiopian format: starts with 09 or 07", fontSize = 11.sp, color = Slate700)
                            },
                            leadingIcon = { Icon(Icons.Default.Phone, contentDescription = null, tint = RoyalBlue) },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                            modifier = Modifier.fillMaxWidth(),
                            singleLine = true,
                            isError = errorMessage.isNotBlank() && phoneNumber.isBlank()
                        )
                        OutlinedTextField(
                            value = password, onValueChange = { password = it; errorMessage = "" },
                            label = { Text("Password *") }, placeholder = { Text("Min. 6 characters") },
                            leadingIcon = { Icon(Icons.Default.Lock, contentDescription = null, tint = RoyalBlue) },
                            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                            trailingIcon = {
                                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                                    Icon(if (passwordVisible) Icons.Default.VisibilityOff else Icons.Default.Visibility, contentDescription = null)
                                }
                            },
                            modifier = Modifier.fillMaxWidth(), singleLine = true,
                            isError = errorMessage.isNotBlank() && password.length < 6
                        )
                        OutlinedTextField(
                            value = confirmPassword, onValueChange = { confirmPassword = it; errorMessage = "" },
                            label = { Text("Confirm Password *") }, placeholder = { Text("Re-enter password") },
                            leadingIcon = { Icon(Icons.Default.LockOpen, contentDescription = null, tint = RoyalBlue) },
                            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                            modifier = Modifier.fillMaxWidth(), singleLine = true,
                            isError = confirmPassword.isNotBlank() && confirmPassword != password
                        )
                        if (confirmPassword.isNotBlank() && confirmPassword != password) {
                            Text("Passwords do not match.", color = RoseRed, fontSize = 11.sp)
                        }
                        if (errorMessage.isNotBlank()) {
                            Surface(color = RoseRed.copy(alpha = 0.08f), shape = RoundedCornerShape(8.dp), modifier = Modifier.fillMaxWidth()) {
                                Text(errorMessage, color = RoseRed, fontSize = 12.sp, fontWeight = FontWeight.Medium, modifier = Modifier.padding(10.dp))
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Button(
                        onClick = {
                            val phoneErr = EthiopianPhoneUtils.getValidationError(phoneNumber)
                            when {
                                name.isBlank() -> errorMessage = "Please enter your name."
                                phoneErr != null -> errorMessage = phoneErr
                                password.length < 6 -> errorMessage = "Password must be at least 6 characters."
                                password != confirmPassword -> errorMessage = "Passwords do not match."
                                else -> {
                                    errorMessage = ""
                                    if (onCheckPhoneRegistered != null) {
                                        isCheckingPhone = true
                                        regScope.launch {
                                            try {
                                                val alreadyRegistered = onCheckPhoneRegistered(phoneNumber)
                                                isCheckingPhone = false
                                                if (alreadyRegistered) {
                                                    errorMessage = "This phone number ($phoneNumber) is already registered. Please log in instead."
                                                } else {
                                                    onSubmit(name, university, academicYear, phoneNumber, password, "", "", "")
                                                }
                                            } catch (e: Exception) {
                                                isCheckingPhone = false
                                                onSubmit(name, university, academicYear, phoneNumber, password, "", "", "")
                                            }
                                        }
                                    } else {
                                        onSubmit(name, university, academicYear, phoneNumber, password, "", "", "")
                                    }
                                }
                            }
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = RoyalBlue),
                        shape = RoundedCornerShape(12.dp),
                        enabled = !isCheckingPhone,
                        modifier = Modifier.fillMaxWidth().height(50.dp)
                    ) {
                        if (isCheckingPhone) {
                            CircularProgressIndicator(
                                color = Color.White,
                                modifier = Modifier.size(20.dp),
                                strokeWidth = 2.dp
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("Creating account...", fontWeight = FontWeight.Bold, fontSize = 14.sp)
                        } else {
                            Icon(Icons.Default.CheckCircle, contentDescription = null, modifier = Modifier.size(18.dp))
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("Create Account", fontWeight = FontWeight.Bold, fontSize = 15.sp)
                        }
                    }
                }
            }
        }
    }

// ─────────────────────────────────────────────────────────
// Payment Verification Dialog — shown when a logged-in but
// unapproved user tries to access premium content
// ─────────────────────────────────────────────────────────
@Composable
fun PaymentVerificationDialog(
    phoneNumber: String,
    onDismiss: () -> Unit,
    onPaymentSubmitted: (paymentMethod: String, screenshotUrl: String) -> Unit
) {
    val context = LocalContext.current
    var selectedPaymentMethod by remember { mutableStateOf("Telebirr") }
    var screenshotUri by remember { mutableStateOf<Uri?>(null) }
    var screenshotUrl by remember { mutableStateOf("") }
    var isUploading by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false, decorFitsSystemWindows = false)
    ) {
        Surface(
            shape = RoundedCornerShape(24.dp),
            color = MaterialTheme.colorScheme.surface,
            modifier = Modifier
                .fillMaxWidth(0.95f)
                .wrapContentHeight()
                .imePadding()
        ) {
            Column(
                modifier = Modifier
                    .padding(22.dp)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Header
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .background(
                                    androidx.compose.ui.graphics.Brush.linearGradient(
                                        listOf(Color(0xFFFFD700), Color(0xFFFFA500))
                                    ),
                                    RoundedCornerShape(12.dp)
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(Icons.Default.Star, contentDescription = null, tint = Color.White, modifier = Modifier.size(22.dp))
                        }
                        Spacer(modifier = Modifier.width(10.dp))
                        Column {
                            Text("Unlock Full Access", fontWeight = FontWeight.Bold, fontSize = 17.sp, color = Slate900)
                            Text("One-time membership payment", fontSize = 11.sp, color = Slate700)
                        }
                    }
                    IconButton(onClick = onDismiss) {
                        Icon(Icons.Default.Close, contentDescription = "Close", tint = Slate700)
                    }
                }

                // Info text
                Surface(
                    shape = RoundedCornerShape(14.dp),
                    color = Color(0xFFFFFBEB),
                    border = androidx.compose.foundation.BorderStroke(1.dp, AmberWarning.copy(alpha = 0.3f)),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier.padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(Icons.Default.Lock, contentDescription = null, tint = AmberWarning, modifier = Modifier.size(18.dp))
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = "የአባልነት ክፍያዎን ከፈጸሙ በኋላ የተላከበትን ስክሪንሾት ከታች ይላኩ፤ አድሚኑ እንዳረጋገጠ ሙሉ አገልግሎቱ ይከፈታል።",
                            fontSize = 11.5.sp,
                            lineHeight = 16.sp,
                            color = Slate800
                        )
                    }
                }

                // Payment accounts card
                Card(
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFEFF6FF)),
                    border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFBFDBFE)),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(14.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                        Text("የክፍያ አማራጮች", fontWeight = FontWeight.Bold, fontSize = 13.sp, color = Slate900)
                        HorizontalDivider(color = Color(0xFFDBEAFE))
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(
                                modifier = Modifier.size(38.dp).background(Color(0xFF0073E6), RoundedCornerShape(10.dp)),
                                contentAlignment = Alignment.Center
                            ) { Text("T", color = Color.White, fontSize = 15.sp, fontWeight = FontWeight.ExtraBold) }
                            Spacer(modifier = Modifier.width(10.dp))
                            Column {
                                Text("Telebirr: 0955903175", fontWeight = FontWeight.Bold, fontSize = 13.sp, color = Slate900)
                                Text("Account Name: Adnan", fontSize = 11.sp, color = Slate700)
                            }
                        }
                        HorizontalDivider(color = Color(0xFFDBEAFE))
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(
                                modifier = Modifier.size(38.dp).background(Color(0xFF800020), RoundedCornerShape(10.dp)),
                                contentAlignment = Alignment.Center
                            ) { Text("C", color = Color.White, fontSize = 15.sp, fontWeight = FontWeight.ExtraBold) }
                            Spacer(modifier = Modifier.width(10.dp))
                            Column {
                                Text("CBE Bank: 1000650901731", fontWeight = FontWeight.Bold, fontSize = 13.sp, color = Slate900)
                                Text("Account Name: Adnan", fontSize = 11.sp, color = Slate700)
                            }
                        }
                        HorizontalDivider(color = Color(0xFFDBEAFE))
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(
                                modifier = Modifier.size(38.dp).background(Color(0xFFFF6600), RoundedCornerShape(10.dp)),
                                contentAlignment = Alignment.Center
                            ) { Text("E", color = Color.White, fontSize = 15.sp, fontWeight = FontWeight.ExtraBold) }
                            Spacer(modifier = Modifier.width(10.dp))
                            Column {
                                Text("E-Birr: 0955903175", fontWeight = FontWeight.Bold, fontSize = 13.sp, color = Slate900)
                                Text("Account Name: Adnan", fontSize = 11.sp, color = Slate700)
                            }
                        }
                    }
                }

                // Screenshot upload
                Text(
                    text = "የተላከበትን ስክሪንሾት ከታች ያስገቡ (Send Screenshot):",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Slate900
                )

                ScreenshotUploadZone(
                    screenshotUri = screenshotUri,
                    screenshotUrl = screenshotUrl,
                    isUploading = isUploading,
                    phoneNumber = phoneNumber,
                    onUploadStarted = {
                        isUploading = true
                        errorMessage = ""
                    },
                    onUploadSuccess = { url, uri ->
                        screenshotUrl = url
                        screenshotUri = uri
                        isUploading = false
                    },
                    onUploadError = { err ->
                        isUploading = false
                        errorMessage = err
                    }
                )

                // Help row
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("Need help? ", fontSize = 11.sp, color = Slate700)
                    Text(
                        "@HUfreshman1",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = RoyalBlue,
                        modifier = Modifier.clickable {
                            context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://t.me/HUfreshman1")))
                        }
                    )
                    Text("  •  ", fontSize = 11.sp, color = Slate700)
                    Text(
                        "0955903175",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = RoyalBlue,
                        modifier = Modifier.clickable {
                            context.startActivity(Intent(Intent.ACTION_DIAL, Uri.parse("tel:0955903175")))
                        }
                    )
                }

                if (errorMessage.isNotBlank()) {
                    Surface(color = RoseRed.copy(alpha = 0.08f), shape = RoundedCornerShape(10.dp), modifier = Modifier.fillMaxWidth()) {
                        Text(errorMessage, color = RoseRed, fontSize = 12.sp, fontWeight = FontWeight.Medium, modifier = Modifier.padding(12.dp))
                    }
                }

                // Submit button
                Button(
                    onClick = {
                        when {
                            isUploading -> errorMessage = "Please wait for the screenshot to finish uploading."
                            screenshotUrl.isBlank() -> errorMessage = "Please upload your payment screenshot first."
                            else -> onPaymentSubmitted(selectedPaymentMethod, screenshotUrl)
                        }
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (screenshotUrl.isNotEmpty()) EmeraldGreen else RoyalBlue
                    ),
                    shape = RoundedCornerShape(14.dp),
                    elevation = ButtonDefaults.buttonElevation(defaultElevation = 3.dp),
                    modifier = Modifier.fillMaxWidth().height(50.dp),
                    enabled = !isUploading
                ) {
                    Icon(Icons.Default.CheckCircle, contentDescription = null, modifier = Modifier.size(18.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Submit Payment", fontWeight = FontWeight.Bold, fontSize = 15.sp)
                }
            }
        }
    }
}

@Composable
fun StudyStatBox(
    modifier: Modifier,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    label: String,
    value: String,
    color: Color,
    onClick: (() -> Unit)? = null
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .background(color.copy(alpha = 0.08f))
            .border(1.dp, color.copy(alpha = 0.2f), RoundedCornerShape(12.dp))
            .then(if (onClick != null) Modifier.clickable { onClick() } else Modifier)
            .padding(14.dp)
    ) {
        Column {
            Icon(icon, contentDescription = null, tint = color, modifier = Modifier.size(20.dp))
            Spacer(modifier = Modifier.height(6.dp))
            Text(value, fontWeight = FontWeight.ExtraBold, fontSize = 20.sp, color = color)
            Text(label, fontSize = 11.sp, color = Slate700)
        }
    }
}
