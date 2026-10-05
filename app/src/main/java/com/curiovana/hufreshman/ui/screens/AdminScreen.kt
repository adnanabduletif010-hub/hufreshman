package com.curiovana.hufreshman.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import coil.compose.AsyncImage
import com.curiovana.hufreshman.data.MemberRegistration
import com.curiovana.hufreshman.data.UniversityGuide
import com.curiovana.hufreshman.ui.theme.*
import com.curiovana.hufreshman.viewmodel.MainViewModel
import kotlinx.coroutines.delay

@Composable
fun AdminScreen(viewModel: MainViewModel) {
    val userProfile by viewModel.userProfile.collectAsState()
    val allQuestions by viewModel.allQuestions.collectAsState()
    val universities by viewModel.universities.collectAsState()
    val posts by viewModel.communityPosts.collectAsState()
    val reports by viewModel.reports.collectAsState()

    var showAdminLoginDialog by remember { mutableStateOf(false) }

    if (!userProfile.isAdmin) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.linearGradient(listOf(RoyalBlueDark.copy(alpha = 0.05f), Color.Transparent))
                )
                .padding(24.dp),
            contentAlignment = Alignment.Center
        ) {
            Card(
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(28.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier
                            .size(72.dp)
                            .background(RoseRed.copy(alpha = 0.12f), CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            Icons.Default.Lock,
                            contentDescription = null,
                            tint = RoseRed,
                            modifier = Modifier.size(36.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(18.dp))

                    Text(
                        text = "Administrator Access Required",
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 18.sp,
                        color = MaterialTheme.colorScheme.onSurface,
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "This area is strictly restricted to verified HU Freshman administrators. To access the control center, please log in with your authorized administrator mobile number.",
                        fontSize = 13.sp,
                        color = Slate700,
                        textAlign = TextAlign.Center,
                        lineHeight = 18.sp
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    Button(
                        onClick = { showAdminLoginDialog = true },
                        colors = ButtonDefaults.buttonColors(containerColor = RoyalBlue),
                        shape = RoundedCornerShape(14.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                    ) {
                        Icon(Icons.Default.Login, contentDescription = null)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Log In with Admin Account", fontWeight = FontWeight.Bold, fontSize = 14.sp)
                    }
                }
            }
        }

        if (showAdminLoginDialog) {
            LoginDialog(
                onDismiss = { showAdminLoginDialog = false },
                onLoginSubmit = { phone, secretOrKey ->
                    viewModel.login(phone, secretOrKey)
                },
                onNavigateToRegister = {
                    showAdminLoginDialog = false
                }
            )
        }
    } else {
        // Authenticated Admin Dashboard
        val context = LocalContext.current
        val registrations by viewModel.memberRegistrations.collectAsState()
        val isSyncing by viewModel.isSyncing.collectAsState()
        val syncStatusMessage by viewModel.syncStatusMessage.collectAsState()
        val sourcedQuestions by viewModel.sourcedQuestions.collectAsState()
        val pendingRegistrations = registrations.filter { !it.isApproved }
        var selectedAdminTab by remember { mutableIntStateOf(0) }
        var universityToEdit by remember { mutableStateOf<com.curiovana.hufreshman.data.UniversityGuide?>(null) }
        val adminTabs = listOf(
            "Approvals (${pendingRegistrations.size})",
            "All Users",
            "Add Question",
            "Edit Universities",
            "Post Notice",
            "Reports (${reports.size})",
            "HWU & Other Unis",
            "Settings"
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            // Animated gradient admin header
            val infiniteTransition = rememberInfiniteTransition(label = "header")
            val gradientShift by infiniteTransition.animateFloat(
                initialValue = 0f, targetValue = 1f,
                animationSpec = infiniteRepeatable(tween(4000, easing = LinearEasing), RepeatMode.Reverse),
                label = "gradient"
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        Brush.linearGradient(
                            colors = listOf(RoyalBlueDark, RoyalBlue, ElectricIndigo),
                            start = androidx.compose.ui.geometry.Offset(gradientShift * 200f, 0f),
                            end = androidx.compose.ui.geometry.Offset(800f, 200f)
                        )
                    )
                    .padding(16.dp)
            ) {
                Column {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(
                                modifier = Modifier
                                    .size(40.dp)
                                    .background(Color.White.copy(alpha = 0.2f), RoundedCornerShape(12.dp)),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(Icons.Default.Shield, contentDescription = null, tint = Color.White, modifier = Modifier.size(22.dp))
                            }
                            Spacer(modifier = Modifier.width(10.dp))
                            Column {
                                Text("Admin Control Center", color = Color.White, fontWeight = FontWeight.ExtraBold, fontSize = 16.sp)
                                Text("HU Freshman Verified Administrator", color = Color.White.copy(alpha = 0.8f), fontSize = 11.sp)
                            }
                        }

                        Row(verticalAlignment = Alignment.CenterVertically) {
                            TextButton(
                                onClick = {
                                    viewModel.refreshAdminRegistrations { _, msg ->
                                        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
                                    }
                                },
                                colors = ButtonDefaults.textButtonColors(contentColor = Color.White)
                            ) {
                                if (isSyncing) {
                                    CircularProgressIndicator(modifier = Modifier.size(14.dp), color = Color.White, strokeWidth = 2.dp)
                                } else {
                                    Icon(Icons.Default.Refresh, contentDescription = "Sync", modifier = Modifier.size(15.dp))
                                }
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(if (isSyncing) "Syncing" else "Refresh", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                            }

                            TextButton(
                                onClick = { viewModel.setAdminStatus(false) },
                                colors = ButtonDefaults.textButtonColors(contentColor = Color.White)
                            ) {
                                Icon(Icons.Default.Lock, contentDescription = null, modifier = Modifier.size(14.dp))
                                Spacer(modifier = Modifier.width(4.dp))
                                Text("Lock", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Animated stats row
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        StatCard(modifier = Modifier.weight(1f), title = "Pending", value = "${pendingRegistrations.size}", highlight = pendingRegistrations.isNotEmpty())
                        StatCard(modifier = Modifier.weight(1f), title = "Members", value = "${registrations.filter { it.isApproved }.size}")
                        StatCard(modifier = Modifier.weight(1f), title = "Questions", value = "${allQuestions.size}")
                        StatCard(modifier = Modifier.weight(1f), title = "Reports", value = "${reports.size}", highlight = reports.isNotEmpty())
                    }
                }
            }

            // Cloud Firestore warning banner if configuration or API needs activation
            if (syncStatusMessage != null) {
                Card(
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFFFECEE)),
                    border = androidx.compose.foundation.BorderStroke(1.dp, RoseRed.copy(alpha = 0.5f)),
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    Row(modifier = Modifier.padding(12.dp), verticalAlignment = Alignment.Top) {
                        Icon(Icons.Default.Warning, contentDescription = null, tint = RoseRed, modifier = Modifier.size(20.dp).padding(top = 1.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Column(modifier = Modifier.weight(1f)) {
                            Text("Cloud Sync Action Required", fontWeight = FontWeight.Bold, fontSize = 13.sp, color = RoseRed)
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(syncStatusMessage!!, fontSize = 11.sp, color = Slate900, lineHeight = 15.sp)
                        }
                    }
                }
            }

            // Scrollable tab row
            ScrollableTabRow(
                selectedTabIndex = selectedAdminTab,
                containerColor = MaterialTheme.colorScheme.surface,
                contentColor = RoyalBlue,
                edgePadding = 8.dp
            ) {
                adminTabs.forEachIndexed { index, title ->
                    Tab(
                        selected = selectedAdminTab == index,
                        onClick = { selectedAdminTab = index },
                        text = {
                            Text(
                                title,
                                fontWeight = if (selectedAdminTab == index) FontWeight.ExtraBold else FontWeight.Normal,
                                fontSize = 11.sp,
                                maxLines = 1
                            )
                        }
                    )
                }
            }

            // Tab Content
            when (selectedAdminTab) {
                0 -> MemberApprovalsTab(
                    registrations = registrations,
                    isSyncing = isSyncing,
                    onRefresh = {
                        viewModel.refreshAdminRegistrations { _, msg ->
                            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
                        }
                    },
                    onApprove = { viewModel.approveMemberRegistration(it) },
                    onReject = { id, reason -> viewModel.rejectMemberRegistration(id, reason) }
                )
                1 -> AllUsersTab(registrations = registrations)
                2 -> AddQuestionTab(viewModel = viewModel)
                3 -> ManageUniversitiesTab(
                    universities = universities,
                    onEditUniversity = { universityToEdit = it }
                )
                4 -> CreateOfficialPostTab(viewModel = viewModel)
                5 -> ReportsTab(reports = reports, onResolve = { viewModel.resolveReport(it) })
                6 -> SourcedQBankTab(sourcedQuestions = sourcedQuestions)
                7 -> AdminSettingsTab(viewModel = viewModel)
            }
        }

        if (universityToEdit != null) {
            EditUniversityDialog(
                university = universityToEdit!!,
                onDismiss = { universityToEdit = null },
                onSave = { updated ->
                    viewModel.updateUniversity(updated)
                    universityToEdit = null
                }
            )
        }
    }
}

@Composable
fun StatCard(modifier: Modifier, title: String, value: String, highlight: Boolean = false) {
    var visible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { visible = true }
    AnimatedVisibility(
        visible = visible,
        enter = fadeIn(tween(400)) + slideInVertically(spring(Spring.DampingRatioMediumBouncy)) { it }
    ) {
        Box(
            modifier = modifier
                .background(
                    if (highlight) AmberWarning.copy(alpha = 0.25f) else Color.White.copy(alpha = 0.15f),
                    RoundedCornerShape(12.dp)
                )
                .padding(vertical = 10.dp, horizontal = 6.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(value, color = Color.White, fontWeight = FontWeight.ExtraBold, fontSize = 18.sp)
                Text(title, color = Color.White.copy(alpha = 0.85f), fontSize = 10.sp)
            }
        }
    }
}

// ── All Users Tab ────────────────────────────────────────────────────────────

@Composable
fun AllUsersTab(registrations: List<MemberRegistration>) {
    val context = LocalContext.current
    var searchQuery by remember { mutableStateOf("") }
    var selectedFilter by remember { mutableStateOf("All") }

    val pending = registrations.filter { !it.isApproved && it.rejectionReason == null }
    val approved = registrations.filter { it.isApproved }
    val rejected = registrations.filter { !it.isApproved && it.rejectionReason != null }

    val filteredList = remember(registrations, selectedFilter, searchQuery) {
        val base = when (selectedFilter) {
            "Approved" -> approved
            "Pending" -> pending
            "Rejected" -> rejected
            else -> registrations
        }
        if (searchQuery.isBlank()) base
        else base.filter {
            it.fullName.contains(searchQuery, ignoreCase = true) ||
            it.universityName.contains(searchQuery, ignoreCase = true) ||
            it.phoneNumber.contains(searchQuery, ignoreCase = true) ||
            it.transactionId.contains(searchQuery, ignoreCase = true)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text("All Registered Users", fontWeight = FontWeight.ExtraBold, fontSize = 16.sp)
                Text("${registrations.size} total • ${approved.size} approved • ${pending.size} pending", fontSize = 11.sp, color = Slate700)
            }
        }

        // Filter chips
        Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
            listOf(
                "All" to registrations.size,
                "Approved" to approved.size,
                "Pending" to pending.size,
                "Rejected" to rejected.size
            ).forEach { (label, count) ->
                FilterChip(
                    selected = selectedFilter == label,
                    onClick = { selectedFilter = label },
                    label = { Text("$label ($count)", fontSize = 11.sp) },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = when (label) {
                            "Approved" -> EmeraldGreen
                            "Pending" -> AmberWarning
                            "Rejected" -> RoseRed
                            else -> RoyalBlue
                        },
                        selectedLabelColor = Color.White
                    )
                )
            }
        }

        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            placeholder = { Text("Search by name, phone, university...") },
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = null, tint = RoyalBlue) },
            trailingIcon = {
                if (searchQuery.isNotBlank()) {
                    IconButton(onClick = { searchQuery = "" }) {
                        Icon(Icons.Default.Clear, contentDescription = "Clear")
                    }
                }
            },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            singleLine = true
        )

        if (filteredList.isEmpty()) {
            Box(modifier = Modifier.fillMaxWidth().weight(1f), contentAlignment = Alignment.Center) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(Icons.Default.PeopleOutline, contentDescription = null, tint = Slate700, modifier = Modifier.size(52.dp))
                    Spacer(modifier = Modifier.height(10.dp))
                    Text("No users found", fontWeight = FontWeight.Bold, fontSize = 15.sp)
                    Text("Try changing the filter or search query.", fontSize = 12.sp, color = Slate700)
                }
            }
        } else {
            LazyColumn(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                itemsIndexed(filteredList, key = { _, reg -> reg.id }) { index, reg ->
                    var visible by remember { mutableStateOf(false) }
                    LaunchedEffect(Unit) {
                        delay((index * 50L).coerceAtMost(300L))
                        visible = true
                    }
                    AnimatedVisibility(
                        visible = visible,
                        enter = fadeIn(tween(250)) + slideInVertically(spring(Spring.DampingRatioMediumBouncy)) { it / 2 }
                    ) {
                        UserCard(reg = reg, context = context)
                    }
                }
            }
        }
    }
}

@Composable
fun UserCard(reg: MemberRegistration, context: Context) {
    val statusColor = when {
        reg.isApproved -> EmeraldGreen
        reg.rejectionReason != null -> RoseRed
        else -> AmberWarning
    }
    val statusLabel = when {
        reg.isApproved -> "✓ APPROVED"
        reg.rejectionReason != null -> "✗ REJECTED"
        else -> "⏳ PENDING"
    }

    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        border = androidx.compose.foundation.BorderStroke(1.dp, statusColor.copy(alpha = 0.3f)),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .background(statusColor.copy(alpha = 0.15f), CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            reg.fullName.firstOrNull()?.toString() ?: "?",
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 16.sp,
                            color = statusColor
                        )
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    Column {
                        Text(reg.fullName, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                        Text(reg.universityName, fontSize = 11.sp, color = RoyalBlue, fontWeight = FontWeight.Medium)
                    }
                }
                Box(
                    modifier = Modifier
                        .background(statusColor.copy(alpha = 0.12f), RoundedCornerShape(8.dp))
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Text(statusLabel, color = statusColor, fontWeight = FontWeight.ExtraBold, fontSize = 9.sp)
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(14.dp)) {
                Column(modifier = Modifier.weight(1f)) {
                    Text("Academic Year", fontSize = 10.sp, color = Slate700)
                    Text(reg.academicYear, fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
                }
                Column(modifier = Modifier.weight(1f)) {
                    Text("Phone", fontSize = 10.sp, color = Slate700)
                    Text(reg.phoneNumber, fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
                }
                Column(modifier = Modifier.weight(1f)) {
                    Text("Channel", fontSize = 10.sp, color = Slate700)
                    Text(reg.paymentMethod.ifBlank { "—" }, fontSize = 12.sp, fontWeight = FontWeight.SemiBold, color = RoyalBlue)
                }
            }

            if (reg.transactionId.isNotBlank()) {
                Spacer(modifier = Modifier.height(8.dp))
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = Color(0xFFF1F5F9),
                    border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFCBD5E1)),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 10.dp, vertical = 6.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text("Transaction ID:", fontSize = 10.sp, color = Slate700)
                            Text(
                                text = reg.transactionId,
                                fontWeight = FontWeight.Bold,
                                fontFamily = FontFamily.Monospace,
                                fontSize = 12.sp,
                                color = RoyalBlueDark
                            )
                        }
                        IconButton(
                            onClick = {
                                val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                                val clip = ClipData.newPlainText("Transaction ID", reg.transactionId)
                                clipboard.setPrimaryClip(clip)
                                Toast.makeText(context, "Copied!", Toast.LENGTH_SHORT).show()
                            },
                            modifier = Modifier.size(28.dp)
                        ) {
                            Icon(Icons.Default.ContentCopy, contentDescription = "Copy", tint = Slate700, modifier = Modifier.size(15.dp))
                        }
                    }
                }
            }

            if (!reg.rejectionReason.isNullOrBlank()) {
                Spacer(modifier = Modifier.height(6.dp))
                Surface(color = RoseRed.copy(alpha = 0.08f), shape = RoundedCornerShape(8.dp)) {
                    Text(
                        "Rejection: ${reg.rejectionReason}",
                        fontSize = 11.sp,
                        color = RoseRed,
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(6.dp))
            Text("Registered: ${reg.date}", fontSize = 10.sp, color = Slate700)
        }
    }
}

// ── Create Official Post Tab (replaces BroadcastAnnouncementTab) ─────────────

@Composable
fun CreateOfficialPostTab(viewModel: MainViewModel) {
    var content by remember { mutableStateOf("") }
    var selectedTag by remember { mutableStateOf("Official") }
    var showSuccess by remember { mutableStateOf(false) }
    var imageUrl by remember { mutableStateOf("") }
    var videoUrl by remember { mutableStateOf("") }

    val tags = listOf("Official", "Academic", "Ask Anyone", "Campus Life", "Exams")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(Icons.Default.Campaign, contentDescription = null, tint = RoyalBlue, modifier = Modifier.size(22.dp))
            Spacer(modifier = Modifier.width(8.dp))
            Text("Post Official Campus Notice", fontWeight = FontWeight.ExtraBold, fontSize = 16.sp)
        }

        Text(
            "This post appears directly in the community feed with an 📢 OFFICIAL CAMPUS NOTICE banner. No separate announcements section is needed.",
            fontSize = 12.sp,
            color = Slate700,
            lineHeight = 18.sp
        )

        AnimatedVisibility(visible = showSuccess) {
            Surface(
                color = EmeraldGreen.copy(alpha = 0.1f),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(modifier = Modifier.padding(12.dp), verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.CheckCircle, contentDescription = null, tint = EmeraldGreen, modifier = Modifier.size(20.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("✓ Official notice published to the community feed!", color = EmeraldGreen, fontWeight = FontWeight.Bold, fontSize = 13.sp)
                }
            }
        }

        Text("Select Category:", fontWeight = FontWeight.SemiBold, fontSize = 13.sp)
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            tags.forEach { tag ->
                FilterChip(
                    selected = selectedTag == tag,
                    onClick = { selectedTag = tag },
                    label = { Text(tag, fontSize = 11.sp) },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = RoyalBlue,
                        selectedLabelColor = Color.White
                    )
                )
            }
        }

        OutlinedTextField(
            value = content,
            onValueChange = {
                content = it
                showSuccess = false
            },
            label = { Text("Official Notice Content *") },
            placeholder = { Text("Write the campus announcement or official notice here...") },
            modifier = Modifier.fillMaxWidth(),
            minLines = 4,
            shape = RoundedCornerShape(14.dp)
        )

        // ── Media Attachments Section ──
        Text(
            text = "ATTACH IMAGE (CLOUDINARY)",
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            color = RoyalBlue,
            letterSpacing = 0.5.sp
        )

        PostImageUploadZone(
            imageUrl = imageUrl,
            onImageUrlChange = { imageUrl = it },
            uploaderTag = "admin_official_post"
        )

        OutlinedTextField(
            value = videoUrl,
            onValueChange = { videoUrl = it },
            label = { Text("Video / YouTube Link (Optional)", fontSize = 12.sp) },
            placeholder = { Text("https://youtube.com/watch?v=... or .mp4 link", fontSize = 11.sp) },
            leadingIcon = { Icon(Icons.Default.VideoLibrary, contentDescription = null, tint = RoyalBlue, modifier = Modifier.size(18.dp)) },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            shape = RoundedCornerShape(12.dp)
        )

        // Preview card
        if (content.isNotBlank() || imageUrl.isNotBlank()) {
            Text("Preview:", fontWeight = FontWeight.Bold, fontSize = 12.sp, color = Slate700)
            Surface(
                shape = RoundedCornerShape(12.dp),
                color = Color(0xFFF0F4FF),
                border = androidx.compose.foundation.BorderStroke(1.dp, RoyalBlue.copy(alpha = 0.3f)),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(12.dp)) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Brush.linearGradient(listOf(RoyalBlue, ElectricIndigo)), RoundedCornerShape(8.dp))
                            .padding(horizontal = 10.dp, vertical = 5.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(Icons.Default.Campaign, contentDescription = null, tint = Color.White, modifier = Modifier.size(12.dp))
                        Spacer(modifier = Modifier.width(5.dp))
                        Text("📢 OFFICIAL CAMPUS NOTICE", color = Color.White, fontWeight = FontWeight.ExtraBold, fontSize = 9.sp)
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    if (content.isNotBlank()) {
                        Text(content, fontSize = 13.sp, color = Slate900, lineHeight = 19.sp)
                    }
                    if (imageUrl.isNotBlank()) {
                        Spacer(modifier = Modifier.height(10.dp))
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(160.dp)
                                .clip(RoundedCornerShape(10.dp))
                        ) {
                            AsyncImage(
                                model = imageUrl,
                                contentDescription = "Preview",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier.fillMaxSize()
                            )
                        }
                    }
                    if (videoUrl.isNotBlank()) {
                        Spacer(modifier = Modifier.height(8.dp))
                        Surface(color = Color(0xFF0F172A), shape = RoundedCornerShape(8.dp)) {
                            Row(
                                modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(Icons.Default.PlayCircle, contentDescription = null, tint = Color.White, modifier = Modifier.size(16.dp))
                                Spacer(modifier = Modifier.width(6.dp))
                                Text("Video Attached: $videoUrl", color = Color.White, fontSize = 11.sp, maxLines = 1, overflow = TextOverflow.Ellipsis)
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(selectedTag, fontSize = 10.sp, color = RoyalBlue, fontWeight = FontWeight.Bold)
                }
            }
        }

        Button(
            onClick = {
                if (content.isNotBlank() || imageUrl.isNotBlank()) {
                    viewModel.createPost(
                        content = content.ifBlank { "Attached Notice Image" },
                        tag = selectedTag,
                        imageUrl = imageUrl.takeIf { it.isNotBlank() },
                        videoUrl = videoUrl.takeIf { it.isNotBlank() },
                        author = "HU Freshman"
                    )
                    imageUrl = ""
                    videoUrl = ""
                    content = ""
                    showSuccess = true
                }
            },
            enabled = content.isNotBlank() || imageUrl.isNotBlank(),
            colors = ButtonDefaults.buttonColors(containerColor = RoyalBlue),
            shape = RoundedCornerShape(14.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp)
        ) {
            Icon(Icons.Default.Campaign, contentDescription = null)
            Spacer(modifier = Modifier.width(8.dp))
            Text("Publish to Community Feed", fontWeight = FontWeight.Bold, fontSize = 15.sp)
        }
    }
}

// ── Add Question Tab ─────────────────────────────────────────────────────────

@Composable
fun AddQuestionTab(viewModel: MainViewModel) {
    var course by remember { mutableStateOf("General Physics") }
    var university by remember { mutableStateOf("Haramaya University") }
    var year by remember { mutableStateOf("2026 Exam") }
    var category by remember { mutableStateOf("Mid Exam") }
    var questionText by remember { mutableStateOf("") }
    var optA by remember { mutableStateOf("") }
    var optB by remember { mutableStateOf("") }
    var optC by remember { mutableStateOf("") }
    var optD by remember { mutableStateOf("") }
    var correctIndex by remember { mutableIntStateOf(0) }
    var explanation by remember { mutableStateOf("") }
    var showSuccess by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(Icons.Default.AddCircle, contentDescription = null, tint = RoyalBlue, modifier = Modifier.size(22.dp))
            Spacer(modifier = Modifier.width(8.dp))
            Text("Add New Exam Question", fontWeight = FontWeight.ExtraBold, fontSize = 16.sp)
        }

        AnimatedVisibility(visible = showSuccess) {
            Surface(
                color = EmeraldGreen.copy(alpha = 0.1f),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(modifier = Modifier.padding(12.dp), verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.CheckCircle, contentDescription = null, tint = EmeraldGreen, modifier = Modifier.size(20.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("✓ Question added to the HU Freshman question bank!", color = EmeraldGreen, fontWeight = FontWeight.Bold, fontSize = 13.sp)
                }
            }
        }

        OutlinedTextField(value = course, onValueChange = { course = it }, label = { Text("Course Name") }, modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(12.dp))
        OutlinedTextField(value = university, onValueChange = { university = it }, label = { Text("University") }, modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(12.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            OutlinedTextField(value = year, onValueChange = { year = it }, label = { Text("Year") }, modifier = Modifier.weight(1f), shape = RoundedCornerShape(12.dp))
            OutlinedTextField(value = category, onValueChange = { category = it }, label = { Text("Mid / Final") }, modifier = Modifier.weight(1f), shape = RoundedCornerShape(12.dp))
        }

        OutlinedTextField(
            value = questionText,
            onValueChange = { questionText = it; showSuccess = false },
            label = { Text("Question Statement *") },
            modifier = Modifier.fillMaxWidth(),
            minLines = 3,
            shape = RoundedCornerShape(12.dp)
        )

        Text("Multiple Choice Options", fontWeight = FontWeight.Bold, fontSize = 13.sp)
        OutlinedTextField(value = optA, onValueChange = { optA = it }, label = { Text("Option A") }, modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(12.dp))
        OutlinedTextField(value = optB, onValueChange = { optB = it }, label = { Text("Option B") }, modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(12.dp))
        OutlinedTextField(value = optC, onValueChange = { optC = it }, label = { Text("Option C") }, modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(12.dp))
        OutlinedTextField(value = optD, onValueChange = { optD = it }, label = { Text("Option D") }, modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(12.dp))

        Text("Select Correct Option:", fontWeight = FontWeight.Bold, fontSize = 13.sp)
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            listOf("A", "B", "C", "D").forEachIndexed { idx, label ->
                FilterChip(
                    selected = correctIndex == idx,
                    onClick = { correctIndex = idx },
                    label = { Text("Option $label") },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = EmeraldGreen,
                        selectedLabelColor = Color.White
                    )
                )
            }
        }

        OutlinedTextField(
            value = explanation,
            onValueChange = { explanation = it },
            label = { Text("Step-by-Step Verified Solution") },
            modifier = Modifier.fillMaxWidth(),
            minLines = 3,
            shape = RoundedCornerShape(12.dp)
        )

        Button(
            onClick = {
                if (questionText.isNotBlank() && optA.isNotBlank() && optB.isNotBlank()) {
                    viewModel.addCustomQuestion(
                        course = course,
                        university = university,
                        year = year,
                        category = category,
                        questionText = questionText,
                        options = listOf(optA, optB, optC, optD),
                        answerIndex = correctIndex,
                        explanation = if (explanation.isBlank()) "Detailed solution provided." else explanation
                    )
                    questionText = ""
                    optA = ""; optB = ""; optC = ""; optD = ""
                    explanation = ""
                    showSuccess = true
                }
            },
            colors = ButtonDefaults.buttonColors(containerColor = RoyalBlue),
            shape = RoundedCornerShape(14.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp)
        ) {
            Icon(Icons.Default.AddCircle, contentDescription = null)
            Spacer(modifier = Modifier.width(8.dp))
            Text("Publish Question to Repository", fontWeight = FontWeight.Bold, fontSize = 15.sp)
        }
    }
}

// ── Reports Tab ──────────────────────────────────────────────────────────────

@Composable
fun ReportsTab(
    reports: List<com.curiovana.hufreshman.data.ReportItem>,
    onResolve: (String) -> Unit
) {
    if (reports.isEmpty()) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Icon(Icons.Default.DoneAll, contentDescription = null, tint = EmeraldGreen, modifier = Modifier.size(52.dp))
                Spacer(modifier = Modifier.height(10.dp))
                Text("No pending reports", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                Text("All questions are verified and active.", fontSize = 13.sp, color = Slate700)
            }
        }
    } else {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(reports, key = { it.id }) { item ->
                Card(
                    shape = RoundedCornerShape(14.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    border = androidx.compose.foundation.BorderStroke(1.dp, RoseRed.copy(alpha = 0.25f)),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text("Question ID: ${item.questionId}", fontWeight = FontWeight.Bold, fontSize = 12.sp, color = RoyalBlue)
                            Text(item.date, fontSize = 10.sp, color = Slate700)
                        }
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(item.questionSnippet, fontSize = 13.sp, fontWeight = FontWeight.Medium)
                        Spacer(modifier = Modifier.height(6.dp))
                        Surface(color = RoseRed.copy(alpha = 0.08f), shape = RoundedCornerShape(8.dp)) {
                            Text("Reason: ${item.reason}", fontSize = 12.sp, color = RoseRed, modifier = Modifier.padding(8.dp))
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                            Button(
                                onClick = { onResolve(item.id) },
                                colors = ButtonDefaults.buttonColors(containerColor = EmeraldGreen),
                                shape = RoundedCornerShape(10.dp)
                            ) {
                                Icon(Icons.Default.CheckCircle, contentDescription = null, modifier = Modifier.size(15.dp))
                                Spacer(modifier = Modifier.width(4.dp))
                                Text("Mark Resolved", fontSize = 12.sp)
                            }
                        }
                    }
                }
            }
        }
    }
}

// ── Manage Universities Tab ──────────────────────────────────────────────────

@Composable
fun ManageUniversitiesTab(
    universities: List<UniversityGuide>,
    onEditUniversity: (UniversityGuide) -> Unit
) {
    var searchQuery by remember { mutableStateOf("") }
    val filtered = remember(universities, searchQuery) {
        if (searchQuery.isBlank()) universities
        else universities.filter {
            it.name.contains(searchQuery, ignoreCase = true) ||
            (it.amharicName?.contains(searchQuery, ignoreCase = true) == true) ||
            it.location.contains(searchQuery, ignoreCase = true)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = "Manage University Info",
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 16.sp
                )
                Text(
                    text = "Select any university to edit details, campus images & guides",
                    fontSize = 11.sp,
                    color = Slate700
                )
            }
            Badge(containerColor = RoyalBlue) {
                Text("${filtered.size}", color = Color.White, modifier = Modifier.padding(4.dp), fontSize = 10.sp)
            }
        }

        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            placeholder = { Text("Search university to edit...") },
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = null, tint = RoyalBlue) },
            trailingIcon = {
                if (searchQuery.isNotBlank()) {
                    IconButton(onClick = { searchQuery = "" }) { Icon(Icons.Default.Clear, contentDescription = "Clear") }
                }
            },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            singleLine = true
        )

        LazyColumn(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(filtered, key = { it.id }) { uni ->
                Card(
                    shape = RoundedCornerShape(14.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(54.dp)
                                .clip(RoundedCornerShape(10.dp))
                                .background(RoyalBlueDark),
                            contentAlignment = Alignment.Center
                        ) {
                            if (!uni.image.isNullOrBlank()) {
                                AsyncImage(
                                    model = uni.image,
                                    contentDescription = uni.name,
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier.fillMaxSize()
                                )
                            } else {
                                Icon(Icons.Default.AccountBalance, contentDescription = null, tint = Color.White, modifier = Modifier.size(26.dp))
                            }
                        }

                        Spacer(modifier = Modifier.width(12.dp))

                        Column(modifier = Modifier.weight(1f)) {
                            Text(uni.name, fontWeight = FontWeight.Bold, fontSize = 14.sp, maxLines = 1)
                            if (!uni.amharicName.isNullOrBlank()) {
                                Text(uni.amharicName, fontSize = 11.sp, color = RoyalBlue, fontWeight = FontWeight.Medium)
                            }
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(Icons.Default.LocationOn, contentDescription = null, tint = Slate700, modifier = Modifier.size(12.dp))
                                Spacer(modifier = Modifier.width(3.dp))
                                Text(uni.location, fontSize = 11.sp, color = Slate700)
                            }
                        }

                        Spacer(modifier = Modifier.width(8.dp))

                        Button(
                            onClick = { onEditUniversity(uni) },
                            colors = ButtonDefaults.buttonColors(containerColor = RoyalBlue),
                            shape = RoundedCornerShape(10.dp),
                            contentPadding = PaddingValues(horizontal = 12.dp, vertical = 6.dp)
                        ) {
                            Icon(Icons.Default.Edit, contentDescription = null, modifier = Modifier.size(14.dp))
                            Spacer(modifier = Modifier.width(4.dp))
                            Text("Edit", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }
        }
    }
}

// ── Reusable Screenshot Preview Dialog ─────────────────────────────────────────

@Composable
fun ScreenshotPreviewDialog(
    url: String,
    onDismiss: () -> Unit
) {
    val context = LocalContext.current
    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.94f))
                .clickable { onDismiss() },
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    FilledTonalButton(
                        onClick = {
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                            context.startActivity(intent)
                        },
                        colors = ButtonDefaults.filledTonalButtonColors(containerColor = Color.White.copy(alpha = 0.2f))
                    ) {
                        Text("Open in Browser ↗", color = Color.White, fontSize = 12.sp)
                    }
                    IconButton(
                        onClick = onDismiss,
                        modifier = Modifier.background(Color.White.copy(alpha = 0.2f), CircleShape)
                    ) {
                        Icon(Icons.Default.Close, contentDescription = "Close", tint = Color.White)
                    }
                }
                Spacer(modifier = Modifier.height(12.dp))
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    AsyncImage(
                        model = url,
                        contentDescription = "Full Screenshot Preview",
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(RoundedCornerShape(8.dp))
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text("Tap background or ✕ to close", color = Color.White.copy(alpha = 0.7f), fontSize = 11.sp)
            }
        }
    }
}

// ── Member Approvals Tab ─────────────────────────────────────────────────────

@Composable
fun MemberApprovalsTab(
    registrations: List<MemberRegistration>,
    isSyncing: Boolean = false,
    onRefresh: () -> Unit = {},
    onApprove: (String) -> Unit,
    onReject: (String, String) -> Unit
) {
    val context = LocalContext.current
    var selectedFilter by remember { mutableStateOf("Pending") }
    var searchQuery by remember { mutableStateOf("") }
    var rejectingReg by remember { mutableStateOf<MemberRegistration?>(null) }
    var rejectReason by remember { mutableStateOf("Screenshot / confirmation could not be verified.") }

    val pending = registrations.filter { !it.isApproved && it.rejectionReason == null }
    val approved = registrations.filter { it.isApproved }

    val filteredList = remember(registrations, selectedFilter, searchQuery) {
        val base = when (selectedFilter) {
            "Pending" -> pending
            "Approved" -> approved
            else -> registrations
        }
        if (searchQuery.isBlank()) base
        else base.filter {
            it.fullName.contains(searchQuery, ignoreCase = true) ||
            it.phoneNumber.contains(searchQuery, ignoreCase = true) ||
            it.transactionId.contains(searchQuery, ignoreCase = true)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text("Member Verification Approvals", fontWeight = FontWeight.ExtraBold, fontSize = 16.sp)
                Text("Verify member screenshots & verification details", fontSize = 11.sp, color = Slate700)
            }
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                FilledTonalButton(
                    onClick = onRefresh,
                    shape = RoundedCornerShape(10.dp),
                    contentPadding = PaddingValues(horizontal = 10.dp, vertical = 6.dp)
                ) {
                    if (isSyncing) {
                        CircularProgressIndicator(modifier = Modifier.size(14.dp), strokeWidth = 2.dp)
                    } else {
                        Icon(Icons.Default.Refresh, contentDescription = "Refresh", modifier = Modifier.size(14.dp))
                    }
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(if (isSyncing) "Syncing..." else "Refresh", fontSize = 11.sp, fontWeight = FontWeight.Bold)
                }

                if (pending.isNotEmpty()) {
                    Box(
                        modifier = Modifier
                            .background(AmberWarning, RoundedCornerShape(10.dp))
                            .padding(horizontal = 8.dp, vertical = 6.dp)
                    ) {
                        Text("${pending.size} Pending", color = Color.White, fontWeight = FontWeight.ExtraBold, fontSize = 11.sp)
                    }
                }
            }
        }

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            listOf("Pending (${pending.size})", "All (${registrations.size})", "Approved (${approved.size})").forEach { label ->
                val key = when {
                    label.startsWith("Pending") -> "Pending"
                    label.startsWith("Approved") -> "Approved"
                    else -> "All"
                }
                FilterChip(
                    selected = selectedFilter == key,
                    onClick = { selectedFilter = key },
                    label = { Text(label, fontSize = 11.sp) },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = if (key == "Approved") EmeraldGreen else RoyalBlue,
                        selectedLabelColor = Color.White
                    )
                )
            }
        }

        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            placeholder = { Text("Search by name, phone, or screenshot...") },
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = null, tint = RoyalBlue) },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            singleLine = true
        )

        if (filteredList.isEmpty()) {
            Box(modifier = Modifier.fillMaxWidth().weight(1f), contentAlignment = Alignment.Center) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(Icons.Default.DoneAll, contentDescription = null, tint = EmeraldGreen, modifier = Modifier.size(48.dp))
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        if (selectedFilter == "Pending") "No pending approvals!" else "No registrations found",
                        fontWeight = FontWeight.Bold, fontSize = 14.sp
                    )
                    Text("All student registrations have been reviewed.", fontSize = 12.sp, color = Slate700)
                }
            }
        } else {
            LazyColumn(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(filteredList, key = { it.id }) { reg ->
                    Card(
                        shape = RoundedCornerShape(14.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = if (reg.isApproved) Color(0xFFF0FDF4) else MaterialTheme.colorScheme.surface
                        ),
                        border = if (!reg.isApproved) androidx.compose.foundation.BorderStroke(1.dp, AmberWarning.copy(alpha = 0.5f)) else null,
                        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(modifier = Modifier.padding(14.dp)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(reg.fullName, fontWeight = FontWeight.Bold, fontSize = 15.sp)
                                Box(
                                    modifier = Modifier
                                        .background(
                                            if (reg.isApproved) EmeraldGreen.copy(alpha = 0.12f) else AmberWarning.copy(alpha = 0.12f),
                                            RoundedCornerShape(6.dp)
                                        )
                                        .padding(horizontal = 8.dp, vertical = 3.dp)
                                ) {
                                    Text(
                                        if (reg.isApproved) "✓ APPROVED" else "⏳ PENDING",
                                        color = if (reg.isApproved) EmeraldGreen else AmberWarning,
                                        fontWeight = FontWeight.ExtraBold, fontSize = 10.sp
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.height(4.dp))
                            Text("${reg.universityName} • ${reg.academicYear}", fontSize = 12.sp, color = Slate700)
                            Spacer(modifier = Modifier.height(6.dp))

                            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(Icons.Default.Phone, contentDescription = null, tint = RoyalBlue, modifier = Modifier.size(13.dp))
                                    Spacer(modifier = Modifier.width(4.dp))
                                    Text(reg.phoneNumber, fontSize = 12.sp, fontWeight = FontWeight.Medium)
                                }
                                Surface(shape = RoundedCornerShape(6.dp), color = RoyalBlue.copy(alpha = 0.1f)) {
                                    Text(reg.paymentMethod, color = RoyalBlue, fontSize = 11.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp))
                                }
                            }

                            Spacer(modifier = Modifier.height(8.dp))

                            // Screenshot viewer
                            if (reg.screenshotUrl.isNotEmpty()) {
                                Text(
                                    "Screenshot:",
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    color = Slate700
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Card(
                                    shape = RoundedCornerShape(10.dp),
                                    elevation = CardDefaults.cardElevation(2.dp),
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clickable {
                                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(reg.screenshotUrl))
                                            context.startActivity(intent)
                                        }
                                ) {
                                    AsyncImage(
                                        model = reg.screenshotUrl,
                                        contentDescription = "Screenshot",
                                        contentScale = ContentScale.FillWidth,
                                        modifier = Modifier.fillMaxWidth()
                                    )
                                }
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    "Tap image to open full size",
                                    fontSize = 10.sp,
                                    color = RoyalBlue
                                )
                            } else if (reg.transactionId.isNotBlank()) {
                                Surface(
                                    shape = RoundedCornerShape(8.dp),
                                    color = Color(0xFFF1F5F9),
                                    border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFCBD5E1)),
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(horizontal = 10.dp, vertical = 6.dp),
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Column {
                                            Text("Screenshot / Reference:", fontSize = 10.sp, color = Slate700)
                                            Text(reg.transactionId, fontWeight = FontWeight.Bold, fontFamily = FontFamily.Monospace, fontSize = 13.sp, color = RoyalBlueDark)
                                        }
                                        IconButton(
                                            onClick = {
                                                val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                                                val clip = ClipData.newPlainText("Screenshot Reference", reg.transactionId)
                                                clipboard.setPrimaryClip(clip)
                                                Toast.makeText(context, "Copied Reference!", Toast.LENGTH_SHORT).show()
                                            },
                                            modifier = Modifier.size(28.dp)
                                        ) {
                                            Icon(Icons.Default.ContentCopy, contentDescription = "Copy", tint = Slate700, modifier = Modifier.size(15.dp))
                                        }
                                    }
                                }
                            }

                            if (!reg.rejectionReason.isNullOrBlank()) {
                                Spacer(modifier = Modifier.height(6.dp))
                                Text("Rejection reason: ${reg.rejectionReason}", fontSize = 11.sp, color = RoseRed)
                            }

                            Spacer(modifier = Modifier.height(10.dp))

                            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End, verticalAlignment = Alignment.CenterVertically) {
                                if (!reg.isApproved) {
                                    OutlinedButton(
                                        onClick = { rejectingReg = reg },
                                        colors = ButtonDefaults.outlinedButtonColors(contentColor = RoseRed),
                                        shape = RoundedCornerShape(8.dp),
                                        contentPadding = PaddingValues(horizontal = 10.dp, vertical = 4.dp),
                                        modifier = Modifier.height(36.dp)
                                    ) {
                                        Text("Reject", fontSize = 11.sp)
                                    }
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Button(
                                        onClick = {
                                            onApprove(reg.id)
                                            Toast.makeText(context, "✓ Approved ${reg.fullName}! App unlocked.", Toast.LENGTH_SHORT).show()
                                        },
                                        colors = ButtonDefaults.buttonColors(containerColor = EmeraldGreen),
                                        shape = RoundedCornerShape(8.dp),
                                        contentPadding = PaddingValues(horizontal = 12.dp, vertical = 4.dp),
                                        modifier = Modifier.height(36.dp)
                                    ) {
                                        Icon(Icons.Default.CheckCircle, contentDescription = null, modifier = Modifier.size(14.dp))
                                        Spacer(modifier = Modifier.width(4.dp))
                                        Text("Approve & Unlock", fontSize = 11.sp, fontWeight = FontWeight.Bold)
                                    }
                                } else {
                                    Text("✓ Membership active", fontSize = 11.sp, color = EmeraldGreen, fontWeight = FontWeight.Medium)
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    if (rejectingReg != null) {
        AlertDialog(
            onDismissRequest = { rejectingReg = null },
            title = { Text("Reject Registration", fontWeight = FontWeight.Bold) },
            text = {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text("Enter rejection reason for ${rejectingReg!!.fullName}:", fontSize = 12.sp)
                    OutlinedTextField(
                        value = rejectReason,
                        onValueChange = { rejectReason = it },
                        modifier = Modifier.fillMaxWidth(),
                        minLines = 2,
                        shape = RoundedCornerShape(10.dp)
                    )
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        onReject(rejectingReg!!.id, rejectReason)
                        rejectingReg = null
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = RoseRed)
                ) { Text("Confirm Reject") }
            },
            dismissButton = {
                TextButton(onClick = { rejectingReg = null }) { Text("Cancel") }
            }
        )
    }
}

// ── Admin Settings Tab ──────────────────────────────────────────────────────

@Composable
fun AdminSettingsTab(viewModel: MainViewModel) {
    val context = LocalContext.current
    var newPasscode by remember { mutableStateOf("") }
    var confirmPasscode by remember { mutableStateOf("") }
    var isUpdating by remember { mutableStateOf(false) }
    var resultMessage by remember { mutableStateOf<Pair<Boolean, String>?>(null) }
    var passcodeVisible by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("Admin Settings", fontWeight = FontWeight.ExtraBold, fontSize = 20.sp)

        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(20.dp), verticalArrangement = Arrangement.spacedBy(14.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .background(RoyalBlue.copy(alpha = 0.12f), RoundedCornerShape(10.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(Icons.Default.VpnKey, contentDescription = null, tint = RoyalBlue, modifier = Modifier.size(22.dp))
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Column {
                        Text("Admin Passcode", fontWeight = FontWeight.Bold, fontSize = 15.sp)
                        Text(
                            "Stored securely in Firebase. Synced across all admin devices.",
                            fontSize = 11.sp, color = Slate700, lineHeight = 15.sp
                        )
                    }
                }

                HorizontalDivider(color = Slate700.copy(alpha = 0.1f))

                Surface(
                    shape = RoundedCornerShape(10.dp),
                    color = RoyalBlue.copy(alpha = 0.06f),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(modifier = Modifier.padding(12.dp), verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Info, contentDescription = null, tint = RoyalBlue, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "The passcode is fetched from Firebase on each login. Changing it here updates all devices instantly.",
                            fontSize = 11.sp, color = Slate800, lineHeight = 15.sp
                        )
                    }
                }

                androidx.compose.material3.OutlinedTextField(
                    value = newPasscode,
                    onValueChange = { newPasscode = it; resultMessage = null },
                    label = { Text("New Passcode") },
                    placeholder = { Text("Enter new passcode") },
                    leadingIcon = { Icon(Icons.Default.Lock, contentDescription = null, tint = RoyalBlue) },
                    visualTransformation = if (passcodeVisible) androidx.compose.ui.text.input.VisualTransformation.None else androidx.compose.ui.text.input.PasswordVisualTransformation(),
                    trailingIcon = {
                        IconButton(onClick = { passcodeVisible = !passcodeVisible }) {
                            Icon(
                                if (passcodeVisible) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                                contentDescription = null
                            )
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )

                androidx.compose.material3.OutlinedTextField(
                    value = confirmPasscode,
                    onValueChange = { confirmPasscode = it; resultMessage = null },
                    label = { Text("Confirm Passcode") },
                    placeholder = { Text("Re-enter passcode") },
                    leadingIcon = { Icon(Icons.Default.LockOpen, contentDescription = null, tint = RoyalBlue) },
                    visualTransformation = if (passcodeVisible) androidx.compose.ui.text.input.VisualTransformation.None else androidx.compose.ui.text.input.PasswordVisualTransformation(),
                    isError = confirmPasscode.isNotBlank() && confirmPasscode != newPasscode,
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )

                resultMessage?.let { (success, msg) ->
                    Surface(
                        color = if (success) EmeraldGreen.copy(alpha = 0.1f) else RoseRed.copy(alpha = 0.1f),
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(modifier = Modifier.padding(10.dp), verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                if (success) Icons.Default.CheckCircle else Icons.Default.Error,
                                contentDescription = null,
                                tint = if (success) EmeraldGreen else RoseRed,
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(msg, fontSize = 12.sp, color = if (success) EmeraldGreen else RoseRed, fontWeight = FontWeight.Medium)
                        }
                    }
                }

                Button(
                    onClick = {
                        when {
                            newPasscode.length < 6 -> {
                                resultMessage = false to "Passcode must be at least 6 characters."
                            }
                            newPasscode != confirmPasscode -> {
                                resultMessage = false to "Passcodes do not match."
                            }
                            else -> {
                                isUpdating = true
                                viewModel.updateAdminPasscode(newPasscode) { success, msg ->
                                    isUpdating = false
                                    resultMessage = success to msg
                                    if (success) {
                                        newPasscode = ""
                                        confirmPasscode = ""
                                        Toast.makeText(context, "Passcode updated in Firebase!", Toast.LENGTH_SHORT).show()
                                    }
                                }
                            }
                        }
                    },
                    enabled = !isUpdating,
                    colors = ButtonDefaults.buttonColors(containerColor = RoyalBlue),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.fillMaxWidth().height(48.dp)
                ) {
                    if (isUpdating) {
                        CircularProgressIndicator(modifier = Modifier.size(18.dp), color = Color.White, strokeWidth = 2.dp)
                        Spacer(modifier = Modifier.width(8.dp))
                    } else {
                        Icon(Icons.Default.Save, contentDescription = null)
                        Spacer(modifier = Modifier.width(8.dp))
                    }
                    Text(if (isUpdating) "Updating..." else "Update Passcode in Firebase", fontWeight = FontWeight.Bold, fontSize = 14.sp)
                }
            }
        }
    }
}

// ── Source Q-Bank Tab (Admin Only) ──────────────────────────────────────────
// Shows questions sourced from non-Haramaya universities, grouped by institution.
// These questions are COMPLETELY HIDDEN from regular students.

@Composable
fun SourcedQBankTab(sourcedQuestions: Map<String, List<com.curiovana.hufreshman.data.ExamQuestion>>) {
    val expandedUniversity = remember { mutableStateOf<String?>(null) }
    val expandedQuestion = remember { mutableStateOf<String?>(null) }

    val universityColors = mapOf(
        "Hawassa University" to Color(0xFF0077B6),
        "Addis Ababa University" to Color(0xFFD62828),
        "Jimma University" to Color(0xFF6A0572),
        "Arba Minch University" to Color(0xFF1B4332),
        "Bahir Dar University" to Color(0xFFE85D04),
        "ASTU" to Color(0xFF003049),
        "AASTU" to Color(0xFF780000)
    )

    if (sourcedQuestions.isEmpty()) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Icon(Icons.Default.School, contentDescription = null, modifier = Modifier.size(56.dp), tint = Slate700)
                Spacer(modifier = Modifier.height(8.dp))
                Text("No sourced questions found.", fontWeight = FontWeight.Bold, color = Slate700)
                Text("Questions from other universities will appear here.", fontSize = 12.sp, color = Slate700, textAlign = TextAlign.Center)
            }
        }
        return
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(vertical = 16.dp)
    ) {
        item {
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFFFF3CD)),
                border = androidx.compose.foundation.BorderStroke(1.dp, AmberWarning.copy(alpha = 0.5f)),
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(modifier = Modifier.padding(14.dp), verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.AdminPanelSettings, contentDescription = null, tint = AmberWarning, modifier = Modifier.size(28.dp))
                    Spacer(modifier = Modifier.width(10.dp))
                    Column {
                        Text("Admin-Only HWU & Sourced Question Bank", fontWeight = FontWeight.ExtraBold, fontSize = 14.sp, color = Color(0xFF856404))
                        Text(
                            "Hawassa University (HWU) & other non-HU questions — 100% separated and invisible to regular students",
                            fontSize = 11.sp, color = Color(0xFF856404)
                        )
                    }
                }
            }
        }

        sourcedQuestions.entries.sortedByDescending { it.value.size }.forEach { (uniName, questions) ->
            val uniColor = universityColors[uniName] ?: RoyalBlue
            val isExpanded = expandedUniversity.value == uniName

            item(key = uniName) {
                Card(
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    elevation = CardDefaults.cardElevation(defaultElevation = 3.dp),
                    modifier = Modifier.fillMaxWidth().clickable {
                        expandedUniversity.value = if (isExpanded) null else uniName
                        expandedQuestion.value = null
                    }
                ) {
                    Column {
                        Row(
                            modifier = Modifier.fillMaxWidth().background(uniColor.copy(alpha = 0.08f)).padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier.size(44.dp).background(uniColor, RoundedCornerShape(12.dp)),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(uniName.take(3).uppercase(), color = Color.White, fontWeight = FontWeight.ExtraBold, fontSize = 12.sp)
                            }
                            Spacer(modifier = Modifier.width(12.dp))
                            Column(modifier = Modifier.weight(1f)) {
                                Text(uniName, fontWeight = FontWeight.Bold, fontSize = 15.sp, color = MaterialTheme.colorScheme.onSurface)
                                val courses = questions.map { it.course }.distinct()
                                Text("${questions.size} questions • ${courses.size} course${if (courses.size > 1) "s" else ""}", fontSize = 11.sp, color = Slate700)
                                Text(courses.joinToString(", "), fontSize = 10.sp, color = Slate700, maxLines = 1, overflow = TextOverflow.Ellipsis)
                            }
                            Icon(if (isExpanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore, contentDescription = null, tint = uniColor)
                        }

                        if (isExpanded) {
                            HorizontalDivider(color = uniColor.copy(alpha = 0.2f))
                            questions.forEachIndexed { idx, q ->
                                val isQExpanded = expandedQuestion.value == q.id
                                Column(
                                    modifier = Modifier.fillMaxWidth()
                                        .clickable { expandedQuestion.value = if (isQExpanded) null else q.id }
                                        .padding(horizontal = 16.dp, vertical = 10.dp)
                                ) {
                                    Row(verticalAlignment = Alignment.Top) {
                                        Box(
                                            modifier = Modifier.size(24.dp).background(uniColor.copy(alpha = 0.12f), CircleShape),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            Text("${idx + 1}", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = uniColor)
                                        }
                                        Spacer(modifier = Modifier.width(10.dp))
                                        Column(modifier = Modifier.weight(1f)) {
                                            Row {
                                                val isMid = q.category.contains("Mid", ignoreCase = true)
                                                Box(
                                                    modifier = Modifier.background(
                                                        if (isMid) EmeraldGreen.copy(alpha = 0.12f) else RoyalBlue.copy(alpha = 0.12f),
                                                        RoundedCornerShape(4.dp)
                                                    ).padding(horizontal = 5.dp, vertical = 2.dp)
                                                ) {
                                                    Text(if (isMid) "Mid" else "Final", fontSize = 9.sp, fontWeight = FontWeight.Bold, color = if (isMid) EmeraldGreen else RoyalBlue)
                                                }
                                                Spacer(modifier = Modifier.width(4.dp))
                                                Text(q.year, fontSize = 9.sp, color = Slate700)
                                                Spacer(modifier = Modifier.width(4.dp))
                                                Text(q.course, fontSize = 9.sp, color = Slate700, maxLines = 1, overflow = TextOverflow.Ellipsis, modifier = Modifier.weight(1f))
                                            }
                                            Spacer(modifier = Modifier.height(4.dp))
                                            Text(
                                                q.question, fontSize = 12.sp, fontWeight = FontWeight.Medium,
                                                color = MaterialTheme.colorScheme.onSurface,
                                                maxLines = if (isQExpanded) Int.MAX_VALUE else 2,
                                                overflow = if (isQExpanded) TextOverflow.Visible else TextOverflow.Ellipsis
                                            )
                                            if (isQExpanded) {
                                                Spacer(modifier = Modifier.height(8.dp))
                                                q.options.forEachIndexed { optIdx, opt ->
                                                    Row(modifier = Modifier.padding(vertical = 2.dp), verticalAlignment = Alignment.Top) {
                                                        val isCorrect = optIdx == q.answer
                                                        Box(
                                                            modifier = Modifier.size(18.dp).background(
                                                                if (isCorrect) EmeraldGreen else MaterialTheme.colorScheme.surfaceVariant, CircleShape
                                                            ),
                                                            contentAlignment = Alignment.Center
                                                        ) {
                                                            Text(('A' + optIdx).toString(), fontSize = 8.sp, fontWeight = FontWeight.Bold, color = if (isCorrect) Color.White else Slate700)
                                                        }
                                                        Spacer(modifier = Modifier.width(6.dp))
                                                        Text(opt, fontSize = 11.sp, color = if (isCorrect) EmeraldGreen else MaterialTheme.colorScheme.onSurface, fontWeight = if (isCorrect) FontWeight.Bold else FontWeight.Normal)
                                                    }
                                                }
                                                Spacer(modifier = Modifier.height(6.dp))
                                                Surface(shape = RoundedCornerShape(8.dp), color = EmeraldGreen.copy(alpha = 0.07f), modifier = Modifier.fillMaxWidth()) {
                                                    Text("💡 ${q.explanation}", fontSize = 11.sp, color = MaterialTheme.colorScheme.onSurface, modifier = Modifier.padding(10.dp), lineHeight = 15.sp)
                                                }
                                            }
                                        }
                                    }
                                }
                                if (idx < questions.lastIndex) {
                                    HorizontalDivider(modifier = Modifier.padding(horizontal = 16.dp), color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f))
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
