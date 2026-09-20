package com.curiovana.hufreshman

import android.os.Bundle
import androidx.activity.ComponentActivity
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
import com.curiovana.hufreshman.ui.screens.*
import com.curiovana.hufreshman.ui.theme.HuFreshmanTheme
import com.curiovana.hufreshman.ui.theme.RoyalBlue
import com.curiovana.hufreshman.ui.theme.RoyalBlueDark
import com.curiovana.hufreshman.ui.theme.EmeraldGreen
import com.curiovana.hufreshman.ui.theme.AmberWarning
import com.curiovana.hufreshman.viewmodel.MainViewModel

enum class NavigationTab(val title: String, val iconSelected: androidx.compose.ui.graphics.vector.ImageVector, val iconUnselected: androidx.compose.ui.graphics.vector.ImageVector) {
    EXAMS("Exams", Icons.Filled.School, Icons.Outlined.School),
    UNIVERSITIES("Universities", Icons.Filled.AccountBalance, Icons.Outlined.AccountBalance),
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
        setContent {
            HuFreshmanTheme {
                val isLoading by viewModel.isLoading.collectAsState()
                val userProfile by viewModel.userProfile.collectAsState()
                var currentTab by remember { mutableStateOf(NavigationTab.EXAMS) }
                var showRegistrationPrompt by remember { mutableStateOf(false) }
                var showLoginPrompt by remember { mutableStateOf(false) }

                val isWaitingApproval = !userProfile.isAdmin && userProfile.hasSubmittedRegistration && !userProfile.isApproved
                val isUnregistered = !userProfile.isAdmin && !userProfile.isApproved && !userProfile.hasSubmittedRegistration

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
                                            .size(36.dp)
                                            .background(Color.White, RoundedCornerShape(10.dp)),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Icon(
                                            Icons.Default.School,
                                            contentDescription = null,
                                            tint = RoyalBlue,
                                            modifier = Modifier.size(22.dp)
                                        )
                                    }

                                    Spacer(modifier = Modifier.width(10.dp))

                                    Column {
                                        Row(verticalAlignment = Alignment.CenterVertically) {
                                            Text(
                                                text = "HU Freshman",
                                                fontWeight = FontWeight.Black,
                                                fontSize = 18.sp,
                                                color = Color.White
                                            )
                                            if (userProfile.isAdmin) {
                                                Spacer(modifier = Modifier.width(6.dp))
                                                Box(
                                                    modifier = Modifier
                                                        .background(Color.White.copy(alpha = 0.25f), RoundedCornerShape(6.dp))
                                                        .padding(horizontal = 6.dp, vertical = 2.dp)
                                                ) {
                                                    Text(
                                                        text = "ADMIN",
                                                        fontSize = 9.sp,
                                                        fontWeight = FontWeight.Bold,
                                                        color = Color.White
                                                    )
                                                }
                                            } else if (userProfile.isApproved) {
                                                Spacer(modifier = Modifier.width(6.dp))
                                                Box(
                                                    modifier = Modifier
                                                        .background(EmeraldGreen, RoundedCornerShape(6.dp))
                                                        .padding(horizontal = 6.dp, vertical = 2.dp)
                                                ) {
                                                    Text(
                                                        text = "MEMBER",
                                                        fontSize = 9.sp,
                                                        fontWeight = FontWeight.Bold,
                                                        color = Color.White
                                                    )
                                                }
                                            } else if (isWaitingApproval) {
                                                Spacer(modifier = Modifier.width(6.dp))
                                                Box(
                                                    modifier = Modifier
                                                        .clip(RoundedCornerShape(6.dp))
                                                        .background(AmberWarning)
                                                        .clickable { viewModel.refreshUserProfile() }
                                                        .padding(horizontal = 6.dp, vertical = 2.dp)
                                                ) {
                                                    Text(
                                                        text = "⏳ PENDING",
                                                        fontSize = 9.sp,
                                                        fontWeight = FontWeight.Bold,
                                                        color = Color.White
                                                    )
                                                }
                                            } else {
                                                Spacer(modifier = Modifier.width(6.dp))
                                                Box(
                                                    modifier = Modifier
                                                        .clip(RoundedCornerShape(6.dp))
                                                        .background(Color.White.copy(alpha = 0.25f))
                                                        .padding(horizontal = 6.dp, vertical = 2.dp)
                                                ) {
                                                    Text(
                                                        text = "LOGIN REQUIRED",
                                                        fontSize = 9.sp,
                                                        fontWeight = FontWeight.Bold,
                                                        color = Color.White
                                                    )
                                                }
                                            }
                                        }
                                        Text(
                                            text = if (isWaitingApproval) "Verification In Progress" else if (isUnregistered) "Authentication Required" else "Ethiopian University Exam Hub",
                                            fontSize = 10.sp,
                                            color = Color.White.copy(alpha = 0.85f),
                                            fontWeight = FontWeight.Medium
                                        )
                                    }
                                }
                            },
                            actions = {
                                if (!isWaitingApproval && !isUnregistered) {
                                    IconButton(onClick = { currentTab = NavigationTab.PROFILE }) {
                                        Box(
                                            modifier = Modifier
                                                .size(32.dp)
                                                .background(Color.White.copy(alpha = 0.2f), CircleShape),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            Text(
                                                text = userProfile.name.firstOrNull()?.toString() ?: "U",
                                                color = Color.White,
                                                fontWeight = FontWeight.Bold,
                                                fontSize = 14.sp
                                            )
                                        }
                                    }
                                } else if (isWaitingApproval) {
                                    IconButton(onClick = { viewModel.refreshUserProfile() }) {
                                        Icon(Icons.Default.Refresh, contentDescription = "Refresh Status", tint = Color.White)
                                    }
                                }
                            }
                        )
                    },
                    bottomBar = {
                        // Until user registers and gets approved (or admin), bottom navigation is completely hidden
                        if (!isWaitingApproval && !isUnregistered) {
                            NavigationBar(
                                containerColor = MaterialTheme.colorScheme.surface,
                                tonalElevation = 8.dp
                            ) {
                                val tabs = if (userProfile.isAdmin) {
                                    listOf(NavigationTab.EXAMS, NavigationTab.UNIVERSITIES, NavigationTab.COMMUNITY, NavigationTab.ADMIN, NavigationTab.PROFILE)
                                } else {
                                    listOf(NavigationTab.EXAMS, NavigationTab.UNIVERSITIES, NavigationTab.COMMUNITY, NavigationTab.PROFILE)
                                }

                                tabs.forEach { tab ->
                                    val selected = currentTab == tab
                                    NavigationBarItem(
                                        selected = selected,
                                        onClick = { currentTab = tab },
                                        icon = {
                                            Icon(
                                                if (selected) tab.iconSelected else tab.iconUnselected,
                                                contentDescription = tab.title,
                                                modifier = Modifier.size(22.dp)
                                            )
                                        },
                                        label = {
                                            Text(
                                                text = tab.title,
                                                fontSize = 11.sp,
                                                fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal
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
                        }
                    },
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    ) {
                        if (isLoading) {
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                    CircularProgressIndicator(color = RoyalBlue)
                                    Spacer(modifier = Modifier.height(14.dp))
                                    Text(
                                        text = "Loading 1,958+ Past Exams & Guides...",
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
                        } else if (isWaitingApproval) {
                            // User cant see anything until admin approves, just waiting screen
                            WaitingApprovalScreen(
                                userProfile = userProfile,
                                viewModel = viewModel,
                                onEditRegistration = {
                                    showRegistrationPrompt = true
                                }
                            )
                        } else if (isUnregistered) {
                            // Mandatory Login / Registration Gate - users cannot access the app without logging in/registering
                            AuthScreen(
                                userProfile = userProfile,
                                viewModel = viewModel
                            )
                        } else {
                            Crossfade(targetState = currentTab, label = "TabCrossfade") { tab ->
                                when (tab) {
                                    NavigationTab.EXAMS -> ExamBoardScreen(
                                        viewModel = viewModel,
                                        onNavigateToAdmin = { currentTab = NavigationTab.ADMIN }
                                    )
                                    NavigationTab.UNIVERSITIES -> UniversitiesScreen(viewModel = viewModel)
                                    NavigationTab.COMMUNITY -> CommunityScreen(viewModel = viewModel)
                                    NavigationTab.ADMIN -> AdminScreen(viewModel = viewModel)
                                    NavigationTab.PROFILE -> ProfileScreen(
                                        viewModel = viewModel,
                                        onNavigateToAdmin = { currentTab = NavigationTab.ADMIN }
                                    )
                                }
                            }
                        }

                        // Mandatory/First-Launch Member Registration Gate
                        if (showRegistrationPrompt && !userProfile.isApproved && !userProfile.isAdmin) {
                            MemberRegistrationDialog(
                                currentProfile = userProfile,
                                onDismiss = { showRegistrationPrompt = false },
                                onSubmit = { name, univ, year, phone, password, paymentMethod, txnId ->
                                    viewModel.registerMember(name, univ, year, phone, password, paymentMethod, txnId)
                                    showRegistrationPrompt = false
                                },
                                onNavigateToLogin = {
                                    showRegistrationPrompt = false
                                    showLoginPrompt = true
                                }
                            )
                        }

                        if (showLoginPrompt && !userProfile.isApproved && !userProfile.isAdmin) {
                            LoginDialog(
                                onDismiss = { showLoginPrompt = false },
                                onLoginSubmit = { phone, secretOrKey ->
                                    viewModel.login(phone, secretOrKey)
                                },
                                onNavigateToRegister = {
                                    showLoginPrompt = false
                                    showRegistrationPrompt = true
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}