package com.curiovana.hufreshman

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.curiovana.hufreshman.data.RemoteConfigManager
import com.curiovana.hufreshman.ui.screens.*
import com.curiovana.hufreshman.ui.theme.HuFreshmanTheme
import com.curiovana.hufreshman.ui.theme.rememberScreenDimensions
import com.curiovana.hufreshman.ui.theme.RoyalBlue
import com.curiovana.hufreshman.ui.theme.RoyalBlueDark
import com.curiovana.hufreshman.ui.theme.EmeraldGreen
import com.curiovana.hufreshman.ui.theme.AmberWarning
import com.curiovana.hufreshman.viewmodel.MainViewModel

enum class NavigationTab(val title: String, val iconSelected: androidx.compose.ui.graphics.vector.ImageVector, val iconUnselected: androidx.compose.ui.graphics.vector.ImageVector) {
    EXAMS("Exams", Icons.Filled.School, Icons.Outlined.School),
    UNIVERSITIES("Universities", Icons.Filled.AccountBalance, Icons.Outlined.AccountBalance),
    SHORT_NOTES("Short Notes", Icons.Filled.AutoStories, Icons.Outlined.AutoStories),
    MY_PLAN("My Plan", Icons.Filled.TipsAndUpdates, Icons.Outlined.TipsAndUpdates),
    COMMUNITY("Community", Icons.Filled.Forum, Icons.Outlined.Forum),
    ADMIN("Admin", Icons.Filled.AdminPanelSettings, Icons.Outlined.AdminPanelSettings),
    PROFILE("Profile", Icons.Filled.Person, Icons.Outlined.Person)
}

class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        // Subscribe to admin-post notifications on every launch (idempotent & offline-safe)
        HuFreshmanMessagingService.subscribeToAdminTopic()
        // Initialize Firebase Remote Config to check for mandatory force updates
        RemoteConfigManager.init(this)
        setContent {
            HuFreshmanTheme(darkTheme = false, dynamicColor = false) {
                val screenDimensions = rememberScreenDimensions()
                val updateInfo by RemoteConfigManager.updateInfo.collectAsState()
                val isLoading by viewModel.isLoading.collectAsState()
                val userProfile by viewModel.userProfile.collectAsState()
                val paymentConfig by viewModel.paymentConfig.collectAsState()
                val allQuestions by viewModel.allQuestions.collectAsState()
                var currentTab by remember { mutableStateOf(NavigationTab.EXAMS) }
                val tabHistory = remember { mutableStateListOf(NavigationTab.EXAMS) }

                val navigateToTab: (NavigationTab) -> Unit = { tab ->
                    if (tab == NavigationTab.ADMIN && !userProfile.isAdmin) {
                        // Strictly block non-admins from opening Admin tab
                    } else if (currentTab != tab) {
                        tabHistory.remove(tab)
                        tabHistory.add(tab)
                        currentTab = tab
                    }
                }

                // Sync FCM admin subscription based on userProfile.isAdmin
                LaunchedEffect(userProfile.isAdmin) {
                    HuFreshmanMessagingService.syncAdminSubscription(userProfile.isAdmin)
                }

                // Handle push notification tap intent targeting Admin tab (strictly for verified admins)
                LaunchedEffect(userProfile.isAdmin) {
                    if (intent?.getStringExtra("navigate_to") == "ADMIN") {
                        if (userProfile.isAdmin) {
                            navigateToTab(NavigationTab.ADMIN)
                        }
                        intent?.removeExtra("navigate_to")
                    }
                }

                // Phone back arrow navigation: Return to previous tab or to Exams (Home) instead of exiting the app
                BackHandler(enabled = currentTab != NavigationTab.EXAMS || tabHistory.size > 1) {
                    if (tabHistory.size > 1) {
                        tabHistory.removeAt(tabHistory.lastIndex)
                        currentTab = tabHistory.lastOrNull() ?: NavigationTab.EXAMS
                    } else {
                        currentTab = NavigationTab.EXAMS
                    }
                }

                // Auth dialog state Ã¢â‚¬â€ shown as overlays, not full-screen gate
                var showLoginDialog by remember { mutableStateOf(false) }
                var showRegistrationDialog by remember { mutableStateOf(false) }
                var showPaymentDialog by remember { mutableStateOf(false) }

                val isWaitingApproval = !userProfile.isAdmin && userProfile.hasSubmittedRegistration && !userProfile.isApproved
                val isLoggedIn = !userProfile.isGuest

                // Callback used by any screen that needs login
                val requestLogin: () -> Unit = {
                    if (userProfile.isGuest) {
                        showLoginDialog = true
                    }
                }

                Scaffold(
                    topBar = {
                        TopAppBar(
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = RoyalBlue,
                                titleContentColor = Color.White,
                                actionIconContentColor = Color.White
                            ),
                            title = {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Box(
                                        modifier = Modifier
                                            .size(if (screenDimensions.isCompactWidth) 30.dp else 36.dp)
                                            .background(Color.White, RoundedCornerShape(10.dp)),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Icon(
                                            Icons.Default.School,
                                            contentDescription = null,
                                            tint = RoyalBlue,
                                            modifier = Modifier.size(if (screenDimensions.isCompactWidth) 18.dp else 22.dp)
                                        )
                                    }

                                    Spacer(modifier = Modifier.width(if (screenDimensions.isCompactWidth) 6.dp else 10.dp))

                                    Column {
                                        Row(verticalAlignment = Alignment.CenterVertically) {
                                            Text(
                                                text = "HU Freshman",
                                                fontWeight = FontWeight.Black,
                                                fontSize = if (screenDimensions.isCompactWidth) 15.sp else 18.sp,
                                                color = Color.White
                                            )
                                            Spacer(modifier = Modifier.width(5.dp))
                                            Box(
                                                modifier = Modifier
                                                    .background(
                                                        when {
                                                            userProfile.isAdmin -> Color.White.copy(alpha = 0.25f)
                                                            userProfile.isApproved -> EmeraldGreen
                                                            isWaitingApproval -> AmberWarning
                                                            userProfile.isGuest -> Color.White.copy(alpha = 0.20f)
                                                            else -> Color.White.copy(alpha = 0.25f)
                                                        },
                                                        RoundedCornerShape(6.dp)
                                                    )
                                                    .then(
                                                        if (isWaitingApproval) Modifier.clip(RoundedCornerShape(6.dp)).clickable { viewModel.refreshUserProfile() }
                                                        else Modifier
                                                    )
                                                    .padding(horizontal = 5.dp, vertical = 2.dp)
                                            ) {
                                                Text(
                                                    text = when {
                                                        userProfile.isAdmin -> "ADMIN"
                                                        userProfile.isApproved -> "MEMBER"
                                                        isWaitingApproval -> "PENDING"
                                                        userProfile.isGuest -> "GUEST"
                                                        else -> "LOGIN"
                                                    },
                                                    fontSize = if (screenDimensions.isCompactWidth) 8.sp else 9.sp,
                                                    fontWeight = FontWeight.Bold,
                                                    color = Color.White
                                                )
                                            }
                                        }
                                        Text(
                                            text = when {
                                                isWaitingApproval -> "Verification In Progress"
                                                userProfile.isGuest -> "Haramaya University Freshman"
                                                else -> "Haramaya University Freshman"
                                            },
                                            fontSize = if (screenDimensions.isCompactWidth) 9.sp else 10.sp,
                                            color = Color.White.copy(alpha = 0.85f),
                                            fontWeight = FontWeight.Medium,
                                            maxLines = 1,
                                            overflow = androidx.compose.ui.text.style.TextOverflow.Ellipsis
                                        )
                                    }
                                }
                            },
                            actions = {
                                IconButton(onClick = { navigateToTab(NavigationTab.PROFILE) }) {
                                    Box(
                                        modifier = Modifier
                                            .size(34.dp)
                                            .background(Color.White.copy(alpha = 0.22f), CircleShape),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Icon(
                                            imageVector = Icons.Filled.Person,
                                            contentDescription = "Profile",
                                            tint = Color.White,
                                            modifier = Modifier.size(20.dp)
                                        )
                                    }
                                }
                            }
                        )
                    },
                    bottomBar = {
                        // Show bottom nav always (guests and registered members can browse)
                        NavigationBar(
                            containerColor = MaterialTheme.colorScheme.surface,
                            tonalElevation = 8.dp
                        ) {
                                val tabs = when {
                                    userProfile.isAdmin -> listOf(
                                        NavigationTab.EXAMS, NavigationTab.UNIVERSITIES,
                                        NavigationTab.SHORT_NOTES, NavigationTab.MY_PLAN, NavigationTab.COMMUNITY,
                                        NavigationTab.ADMIN
                                    )
                                    isLoggedIn -> listOf(
                                        NavigationTab.EXAMS, NavigationTab.UNIVERSITIES,
                                        NavigationTab.SHORT_NOTES, NavigationTab.MY_PLAN, NavigationTab.COMMUNITY
                                    )
                                    else -> listOf(
                                        // Guests see all except Admin & Profile
                                        NavigationTab.EXAMS, NavigationTab.UNIVERSITIES,
                                        NavigationTab.SHORT_NOTES, NavigationTab.MY_PLAN, NavigationTab.COMMUNITY
                                    )
                                }

                                tabs.forEach { tab ->
                                    val selected = currentTab == tab
                                    val labelText = if (screenDimensions.isCompactWidth) {
                                        when (tab) {
                                            NavigationTab.UNIVERSITIES -> "Unis"
                                            NavigationTab.SHORT_NOTES -> "Notes"
                                            NavigationTab.MY_PLAN -> "Plan"
                                            NavigationTab.COMMUNITY -> "Forum"
                                            else -> tab.title
                                        }
                                    } else {
                                        tab.title
                                    }

                                    NavigationBarItem(
                                        selected = selected,
                                        onClick = { navigateToTab(tab) },
                                        alwaysShowLabel = !screenDimensions.isCompactWidth || tabs.size <= 4,
                                        icon = {
                                            Icon(
                                                if (selected) tab.iconSelected else tab.iconUnselected,
                                                contentDescription = tab.title,
                                                modifier = Modifier.size(if (screenDimensions.isCompactWidth) 20.dp else 22.dp)
                                            )
                                        },
                                        label = {
                                            Text(
                                                text = labelText,
                                                fontSize = if (screenDimensions.isCompactWidth) 9.5.sp else 11.sp,
                                                fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal,
                                                maxLines = 1,
                                                overflow = androidx.compose.ui.text.style.TextOverflow.Ellipsis
                                            )
                                        },
                                        colors = NavigationBarItemDefaults.colors(
                                            selectedIconColor = RoyalBlue,
                                            selectedTextColor = RoyalBlue,
                                            indicatorColor = RoyalBlue.copy(alpha = 0.12f)
                                        )
                                    )
                                }
                            }
                    },
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .widthIn(max = 1100.dp)
                                .align(Alignment.TopCenter)
                        ) {

                        if (isLoading) {
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                    CircularProgressIndicator(color = RoyalBlue)
                                    Spacer(modifier = Modifier.height(14.dp))
                                    val loadingCount = if (allQuestions.isNotEmpty()) "${java.text.NumberFormat.getIntegerInstance().format(allQuestions.size)}+" else "2,000+"
                                    Text(
                                        text = "Loading $loadingCount Past Exams & Guides...",
                                        fontWeight = FontWeight.SemiBold,
                                        fontSize = 14.sp
                                    )
                                    Text(
                                        text = "Preparing offline question bank",
                                        fontSize = 12.sp,
                                        color = Color.Gray
                                    )
                                }
                            }
                        } else {
                            // Main content Ã¢â‚¬â€ users can browse Exams list, Universities list, Notes, Community posts
                            Crossfade(targetState = currentTab, label = "TabCrossfade") { tab ->
                                when (tab) {
                                    NavigationTab.EXAMS -> ExamBoardScreen(
                                        viewModel = viewModel,
                                        // Gated when clicking an exam year Ã¢â‚¬â€ first year of first subject is free for everyone
                                        onYearSelectedGate = { isFirstItem ->
                                            when {
                                                isFirstItem -> true  // free preview: first year of the first subject
                                                userProfile.isGuest -> {
                                                    showLoginDialog = true
                                                    false
                                                }
                                                !userProfile.isApproved && !userProfile.isAdmin -> {
                                                    showPaymentDialog = true
                                                    false
                                                }
                                                else -> true
                                            }
                                        }
                                    )

                                    NavigationTab.UNIVERSITIES -> UniversitiesScreen(
                                        viewModel = viewModel,
                                        // First university is free for everyone; rest requires login+approval
                                        onUniversitySelectedGate = { isFirstItem ->
                                            when {
                                                isFirstItem -> true
                                                userProfile.isGuest -> {
                                                    showLoginDialog = true
                                                    false
                                                }
                                                !userProfile.isApproved && !userProfile.isAdmin -> {
                                                    showPaymentDialog = true
                                                    false
                                                }
                                                else -> true
                                            }
                                        }
                                    )
                                    NavigationTab.SHORT_NOTES -> ShortNotesScreen(
                                        viewModel = viewModel,
                                        // Gated when clicking into short notes content Ã¢â‚¬â€ first note of the first subject is free
                                        onContentGate = { isFirstItem ->
                                            when {
                                                isFirstItem -> true  // free preview: first note of the first subject
                                                userProfile.isGuest -> {
                                                    showLoginDialog = true
                                                    false
                                                }
                                                !userProfile.isApproved && !userProfile.isAdmin -> {
                                                    showPaymentDialog = true
                                                    false
                                                }
                                                else -> true
                                            }
                                        }
                                    )
                                    NavigationTab.MY_PLAN -> MyPlanScreen(viewModel = viewModel)
                                    NavigationTab.COMMUNITY -> CommunityScreen(
                                        viewModel = viewModel,
                                        // Gated when creating a post
                                        onPostGate = {
                                            when {
                                                userProfile.isGuest -> {
                                                    showLoginDialog = true
                                                    false
                                                }
                                                !userProfile.isApproved && !userProfile.isAdmin -> {
                                                    showPaymentDialog = true
                                                    false
                                                }
                                                else -> true
                                            }
                                        }
                                    )
                                    NavigationTab.ADMIN -> {
                                        if (userProfile.isAdmin) {
                                            AdminScreen(viewModel = viewModel)
                                        } else {
                                            LaunchedEffect(Unit) {
                                                currentTab = NavigationTab.EXAMS
                                            }
                                        }
                                    }
                                    NavigationTab.PROFILE -> ProfileScreen(
                                        viewModel = viewModel,
                                        onNavigateToAdmin = {
                                            if (userProfile.isAdmin) navigateToTab(NavigationTab.ADMIN)
                                        }
                                    )
                                }
                            }
                        }

                        // Registration dialog
                        if (showRegistrationDialog && !userProfile.isApproved && !userProfile.isAdmin) {
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

                        // Payment Verification Dialog (shown when unapproved user clicks premium content)
                        if (showPaymentDialog && !userProfile.isApproved && !userProfile.isGuest && !userProfile.isAdmin) {
                            val submissionCount = viewModel.getScreenshotSubmissionCount()
                            PaymentVerificationDialog(
                                phoneNumber = userProfile.phoneNumber,
                                paymentConfig = paymentConfig,
                                submissionCount = submissionCount,
                                hasSubmitted = userProfile.hasSubmittedRegistration,
                                rejectionReason = userProfile.rejectionReason,
                                onDismiss = { showPaymentDialog = false },
                                onPaymentSubmitted = { method, url ->
                                    viewModel.submitPaymentVerification(method, url)
                                }
                            )
                        }

                        // Login dialog (shown when guest tries a restricted action)
                        if (showLoginDialog && userProfile.isGuest) {
                            LoginDialog(
                                onDismiss = { showLoginDialog = false },
                                onLoginSubmit = { phone, secretOrKey ->
                                    val result = viewModel.login(phone, secretOrKey)
                                    showLoginDialog = false
                                    result
                                },
                                onNavigateToRegister = {
                                    showLoginDialog = false
                                    showRegistrationDialog = true
                                }
                            )
                        }

                        // Mandatory Force Update Overlay (controlled via Firebase Remote Config)
                        if (updateInfo.isUpdateRequired) {
                            ForceUpdateDialog(updateInfo = updateInfo)
                        }
                    }
                }
            }
        }
    }
}

    override fun onResume() {
        super.onResume()
        RemoteConfigManager.checkUpdate(this)
    }
}
