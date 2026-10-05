package com.curiovana.hufreshman.ui.screens

import android.content.Intent
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.ui.platform.LocalContext
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.ChatBubbleOutline
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import android.net.Uri
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import coil.compose.AsyncImage
import com.curiovana.hufreshman.data.CommunityPost
import com.curiovana.hufreshman.ui.theme.*
import com.curiovana.hufreshman.viewmodel.MainViewModel

@Composable
fun CommunityScreen(
    viewModel: MainViewModel,
    // Returns true if action is allowed, false if blocked (e.g. guest user)
    onPostGate: () -> Boolean = { true }
) {
    val posts by viewModel.communityPosts.collectAsState()
    val userProfile by viewModel.userProfile.collectAsState()
    val isSyncing by viewModel.isCommunitySyncing.collectAsState()
    val context = LocalContext.current
    var selectedTag by remember { mutableStateOf("All") }
    var showCreatePostDialog by remember { mutableStateOf(false) }
    var postToEdit by remember { mutableStateOf<CommunityPost?>(null) }
    var postToDelete by remember { mutableStateOf<CommunityPost?>(null) }

    val tags = listOf("All", "Academic", "Ask Anyone", "Tips", "Campus Life", "Exams", "Official")

    val filteredPosts = remember(posts, selectedTag) {
        if (selectedTag == "All") posts
        else posts.filter { it.tag.equals(selectedTag, ignoreCase = true) }
    }

    BackHandler(enabled = selectedTag != "All") {
        selectedTag = "All"
    }

    Scaffold(
        floatingActionButton = {
            // Show FAB for admin and approved regular users (guests = no phoneNumber)
            val canPost = userProfile.isAdmin || userProfile.phoneNumber.isNotBlank()
            if (canPost) {
                ExtendedFloatingActionButton(
                    onClick = { showCreatePostDialog = true },
                    containerColor = RoyalBlue,
                    contentColor = Color.White,
                    icon = { Icon(Icons.Default.Add, contentDescription = "Create Post") },
                    text = { Text("Create Post", fontWeight = FontWeight.Bold) }
                )
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(MaterialTheme.colorScheme.background)
        ) {
            // Gradient Header
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        Brush.linearGradient(listOf(RoyalBlueDark, RoyalBlue, ElectricIndigo))
                    )
                    .padding(horizontal = 16.dp, vertical = 14.dp)
            ) {
                Column {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(
                                text = "Campus Community",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.ExtraBold,
                                color = Color.White
                            )
                            Text(
                                text = "${posts.size} posts • Freshman updates & discussions",
                                fontSize = 12.sp,
                                color = Color.White.copy(alpha = 0.8f)
                            )
                        }
                        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            IconButton(
                                onClick = {
                                    viewModel.refreshCommunityPosts { success, msg ->
                                        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
                                    }
                                },
                                modifier = Modifier
                                    .size(36.dp)
                                    .background(Color.White.copy(alpha = 0.15f), CircleShape)
                            ) {
                                if (isSyncing) {
                                    CircularProgressIndicator(
                                        modifier = Modifier.size(16.dp),
                                        color = Color.White,
                                        strokeWidth = 2.dp
                                    )
                                } else {
                                    Icon(
                                        Icons.Default.Refresh,
                                        contentDescription = "Sync Posts",
                                        tint = Color.White,
                                        modifier = Modifier.size(18.dp)
                                    )
                                }
                            }
                            Box(
                                modifier = Modifier
                                    .size(40.dp)
                                    .background(Color.White.copy(alpha = 0.15f), CircleShape),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(Icons.Default.Forum, contentDescription = null, tint = Color.White, modifier = Modifier.size(22.dp))
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    // Tags filter row
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .horizontalScroll(rememberScrollState()),
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        tags.forEach { tag ->
                            val isSelected = selectedTag == tag
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(20.dp))
                                    .background(
                                        if (isSelected) Color.White else Color.White.copy(alpha = 0.18f)
                                    )
                                    .clickable { selectedTag = tag }
                                    .padding(horizontal = 14.dp, vertical = 6.dp)
                            ) {
                                Text(
                                    text = tag,
                                    fontSize = 12.sp,
                                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                                    color = if (isSelected) RoyalBlue else Color.White
                                )
                            }
                        }
                    }
                }
            }

            // Posts List — Announcements are gone; all posts including official ones live here
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(horizontal = 14.dp, vertical = 12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                if (filteredPosts.isEmpty()) {
                    item {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 60.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Icon(Icons.Default.Forum, contentDescription = null, tint = Slate700, modifier = Modifier.size(52.dp))
                                Spacer(modifier = Modifier.height(12.dp))
                                Text("No posts yet", fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Slate900)
                                Text("Be the first to share a post in this category.", fontSize = 13.sp, color = Slate700)
                            }
                        }
                    }
                }

                itemsIndexed(filteredPosts, key = { _, post -> post.id }) { index, post ->
                    var visible by remember { mutableStateOf(false) }
                    LaunchedEffect(Unit) {
                        kotlinx.coroutines.delay((index * 60L).coerceAtMost(400L))
                        visible = true
                    }
                    AnimatedVisibility(
                        visible = visible,
                        enter = fadeIn(tween(300)) + slideInVertically(
                            animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy),
                            initialOffsetY = { it / 3 }
                        )
                    ) {
                        CommunityPostCard(
                            post = post,
                            isAdmin = userProfile.isAdmin,
                            isAuthor = userProfile.name.isNotBlank() && userProfile.name.equals(post.author, ignoreCase = true),
                            onToggleLike = { viewModel.toggleLike(post.id) },
                            onAddComment = { commentText -> viewModel.addComment(post.id, commentText) },
                            onEditClick = { postToEdit = post },
                            onDeleteClick = { postToDelete = post }
                        )
                    }
                }
            }
        }
    }

    // Create Post Dialog — admin gets image/video fields; users get text-only
    if (showCreatePostDialog) {
        CreatePostDialog(
            isAdmin = userProfile.isAdmin,
            userName = userProfile.name,
            onDismiss = { showCreatePostDialog = false },
            onSubmit = { content, tag, imageUrl, videoUrl, author ->
                viewModel.createPost(
                    content = content,
                    tag = tag,
                    imageUrl = imageUrl,
                    videoUrl = videoUrl,
                    author = author
                )
                showCreatePostDialog = false
            }
        )
    }

    // Edit Post Dialog for Admin or Post Author
    val isCurrentUserAuthorOfEdit = postToEdit != null &&
        userProfile.name.isNotBlank() &&
        userProfile.name.equals(postToEdit!!.author, ignoreCase = true)
    if (postToEdit != null && (userProfile.isAdmin || isCurrentUserAuthorOfEdit)) {
        EditPostDialog(
            post = postToEdit!!,
            onDismiss = { postToEdit = null },
            onSubmit = { newContent, newTag, newImageUrl, newVideoUrl ->
                viewModel.editPost(postToEdit!!.id, newContent, newTag, newImageUrl, newVideoUrl)
                postToEdit = null
            }
        )
    }

    // Delete Post Confirmation Dialog for Admin
    if (postToDelete != null) {
        AlertDialog(
            onDismissRequest = { postToDelete = null },
            title = {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Delete, contentDescription = null, tint = RoseRed)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Delete Post?", fontWeight = FontWeight.Bold)
                }
            },
            text = {
                Text("Are you sure you want to permanently delete this post from the community feed?")
            },
            confirmButton = {
                Button(
                    onClick = {
                        viewModel.deletePost(postToDelete!!.id)
                        postToDelete = null
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = RoseRed)
                ) {
                    Text("Delete", color = Color.White)
                }
            },
            dismissButton = {
                TextButton(onClick = { postToDelete = null }) {
                    Text("Cancel")
                }
            }
        )
    }
}

fun formatPostRelativeTime(post: CommunityPost): String {
    val postTime = if (post.timestamp > 0L) {
        post.timestamp
    } else {
        post.id.removePrefix("post_").toLongOrNull() ?: 0L
    }

    if (postTime <= 0L) {
        return post.date.ifBlank { "Recently" }
    }

    val now = System.currentTimeMillis()
    val diff = now - postTime
    if (diff < 0L) return "Just now"

    val seconds = diff / 1000
    val minutes = seconds / 60
    val hours = minutes / 60
    val days = hours / 24
    val weeks = days / 7
    val months = days / 30

    return when {
        seconds < 60 -> "Just now"
        minutes < 60 -> "${minutes}m ago"
        hours == 1L -> "1 hour ago"
        hours < 24 -> "${hours} hours ago"
        days == 1L -> "Yesterday"
        days < 7 -> "${days} days ago"
        weeks < 4 -> "${weeks}w ago"
        months < 12 -> "${months}mo ago"
        else -> {
            val sdf = java.text.SimpleDateFormat("MMM d, yyyy", java.util.Locale.getDefault())
            sdf.format(java.util.Date(postTime))
        }
    }
}

@Composable
fun CommunityPostCard(
    post: CommunityPost,
    isAdmin: Boolean = false,
    isAuthor: Boolean = false,
    onToggleLike: () -> Unit,
    onAddComment: (String) -> Unit,
    onEditClick: (() -> Unit)? = null,
    onDeleteClick: (() -> Unit)? = null
) {
    var showComments by remember { mutableStateOf(false) }
    var commentInput by remember { mutableStateOf("") }

    val isOfficial = post.tag.equals("Official", ignoreCase = true) ||
                     post.role.contains("Freshman", ignoreCase = true) ||
                     post.author.contains("Freshman", ignoreCase = true)

    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (isOfficial) Color(0xFFF0F4FF) else MaterialTheme.colorScheme.surface
        ),
        border = if (isOfficial) androidx.compose.foundation.BorderStroke(1.5.dp, RoyalBlue.copy(alpha = 0.35f)) else null,
        elevation = CardDefaults.cardElevation(defaultElevation = if (isOfficial) 4.dp else 2.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {

            // Official notice stripe
            if (isOfficial) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            Brush.linearGradient(listOf(RoyalBlue, ElectricIndigo)),
                            RoundedCornerShape(8.dp)
                        )
                        .padding(horizontal = 10.dp, vertical = 5.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(Icons.Default.Campaign, contentDescription = null, tint = Color.White, modifier = Modifier.size(14.dp))
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "📢 HU FRESHMAN COMMUNITY UPDATE",
                        color = Color.White,
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 10.sp,
                        letterSpacing = 0.5.sp
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
            }

            // Author row
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(38.dp)
                        .background(
                            if (isOfficial) Brush.linearGradient(listOf(RoyalBlue, ElectricIndigo))
                            else Brush.linearGradient(listOf(ElectricIndigo, EmeraldGreen)),
                            CircleShape
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = post.author.firstOrNull()?.toString() ?: "H",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                }

                Spacer(modifier = Modifier.width(10.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = post.author,
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        if (isOfficial || post.author.contains("Freshman", ignoreCase = true)) {
                            Spacer(modifier = Modifier.width(6.dp))
                            Box(
                                modifier = Modifier
                                    .background(RoyalBlue, RoundedCornerShape(4.dp))
                                    .padding(horizontal = 6.dp, vertical = 2.dp)
                            ) {
                                Text("HU Freshman", color = Color.White, fontSize = 8.5.sp, fontWeight = FontWeight.ExtraBold)
                            }
                        }
                    }
                    Text(
                        text = "${formatPostRelativeTime(post)} • ${post.tag}",
                        fontSize = 11.sp,
                        color = Slate700
                    )
                }

                if (isAdmin || isAuthor) {
                    var showMenu by remember { mutableStateOf(false) }
                    Box {
                        IconButton(onClick = { showMenu = true }) {
                            Icon(Icons.Default.MoreVert, contentDescription = "Post options", tint = Slate700)
                        }
                        DropdownMenu(
                            expanded = showMenu,
                            onDismissRequest = { showMenu = false }
                        ) {
                            DropdownMenuItem(
                                text = { Text("Edit Post", fontWeight = FontWeight.Medium) },
                                onClick = {
                                    showMenu = false
                                    onEditClick?.invoke()
                                },
                                leadingIcon = {
                                    Icon(Icons.Default.Edit, contentDescription = null, tint = RoyalBlue, modifier = Modifier.size(18.dp))
                                }
                            )
                            DropdownMenuItem(
                                text = { Text("Delete Post", color = RoseRed, fontWeight = FontWeight.Medium) },
                                onClick = {
                                    showMenu = false
                                    onDeleteClick?.invoke()
                                },
                                leadingIcon = {
                                    Icon(Icons.Default.Delete, contentDescription = null, tint = RoseRed, modifier = Modifier.size(18.dp))
                                }
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Content
            Text(
                text = post.content,
                fontSize = 14.sp,
                lineHeight = 21.sp,
                color = Slate900
            )

            // Attached Image Display
            if (!post.imageUrl.isNullOrBlank()) {
                Spacer(modifier = Modifier.height(10.dp))
                Card(
                    shape = RoundedCornerShape(12.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp))
                ) {
                    AsyncImage(
                        model = post.imageUrl,
                        contentDescription = "Post image",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(min = 140.dp, max = 280.dp)
                    )
                }
            }

            // Attached Video Display
            val videoLink = post.videoUrl ?: post.youtubeUrl
            if (!videoLink.isNullOrBlank()) {
                val context = LocalContext.current
                Spacer(modifier = Modifier.height(10.dp))
                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = Color(0xFF0F172A),
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp))
                        .clickable {
                            try {
                                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(videoLink))
                                context.startActivity(intent)
                            } catch (e: Exception) {
                                Toast.makeText(context, "Cannot open video link", Toast.LENGTH_SHORT).show()
                            }
                        }
                ) {
                    Row(
                        modifier = Modifier.padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(42.dp)
                                .background(RoseRed, CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                Icons.Default.PlayArrow,
                                contentDescription = "Play Video",
                                tint = Color.White,
                                modifier = Modifier.size(24.dp)
                            )
                        }
                        Spacer(modifier = Modifier.width(12.dp))
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = "Watch Video",
                                color = Color.White,
                                fontWeight = FontWeight.Bold,
                                fontSize = 13.sp
                            )
                            Text(
                                text = videoLink,
                                color = Color.White.copy(alpha = 0.7f),
                                fontSize = 11.sp,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                        Icon(
                            Icons.Default.OpenInNew,
                            contentDescription = null,
                            tint = Color.White.copy(alpha = 0.7f),
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Actions row
            HorizontalDivider(color = Color(0xFFF1F5F9))
            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                // Like button
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .clickable { onToggleLike() }
                        .background(
                            if (post.isLiked) RoseRed.copy(alpha = 0.08f) else Color.Transparent,
                            RoundedCornerShape(8.dp)
                        )
                        .padding(horizontal = 8.dp, vertical = 6.dp)
                ) {
                    Icon(
                        if (post.isLiked) Icons.Default.Favorite else Icons.Outlined.FavoriteBorder,
                        contentDescription = "Like",
                        tint = if (post.isLiked) RoseRed else Slate700,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "${post.likes}",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = if (post.isLiked) RoseRed else Slate700
                    )
                }

                Spacer(modifier = Modifier.width(8.dp))

                // Comment toggle
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .clickable { showComments = !showComments }
                        .background(
                            if (showComments) RoyalBlue.copy(alpha = 0.08f) else Color.Transparent,
                            RoundedCornerShape(8.dp)
                        )
                        .padding(horizontal = 8.dp, vertical = 6.dp)
                ) {
                    Icon(
                        Icons.Outlined.ChatBubbleOutline,
                        contentDescription = "Comments",
                        tint = if (showComments) RoyalBlue else Slate700,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "${post.comments.size}",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = if (showComments) RoyalBlue else Slate700
                    )
                }

                Spacer(modifier = Modifier.width(8.dp))

                // Share button
                val shareContext = LocalContext.current
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .clickable {
                            val sendIntent = Intent(Intent.ACTION_SEND).apply {
                                putExtra(
                                    Intent.EXTRA_TEXT,
                                    "${post.author} on HU Freshman Community:\n\n${post.content}\n\n#HUFreshman #${post.tag}"
                                )
                                type = "text/plain"
                            }
                            val shareIntent = Intent.createChooser(sendIntent, "Share post via")
                            shareContext.startActivity(shareIntent)
                        }
                        .padding(horizontal = 8.dp, vertical = 6.dp)
                ) {
                    Icon(
                        Icons.Default.Share,
                        contentDescription = "Share",
                        tint = Slate700,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "Share",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Slate700
                    )
                }
            }

            // Animated comments section
            AnimatedVisibility(
                visible = showComments,
                enter = fadeIn(tween(200)) + slideInVertically(initialOffsetY = { -it / 4 })
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp)
                ) {
                    if (post.comments.isNotEmpty()) {
                        post.comments.forEach { comment ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 4.dp)
                                    .background(Color(0xFFF8FAFC), RoundedCornerShape(10.dp))
                                    .padding(10.dp),
                                verticalAlignment = Alignment.Top
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(28.dp)
                                        .background(ElectricIndigo.copy(alpha = 0.15f), CircleShape),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        comment.author.firstOrNull()?.toString() ?: "?",
                                        fontSize = 11.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = ElectricIndigo
                                    )
                                }
                                Spacer(modifier = Modifier.width(8.dp))
                                Column(modifier = Modifier.weight(1f)) {
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween
                                    ) {
                                        Text(comment.author, fontWeight = FontWeight.Bold, fontSize = 11.sp, color = RoyalBlue)
                                        Text(comment.date, fontSize = 10.sp, color = Slate700)
                                    }
                                    Text(comment.content, fontSize = 12.sp, color = Slate900, lineHeight = 17.sp)
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        OutlinedTextField(
                            value = commentInput,
                            onValueChange = { commentInput = it },
                            placeholder = { Text("Write a comment...", fontSize = 12.sp) },
                            modifier = Modifier.weight(1f),
                            singleLine = true,
                            shape = RoundedCornerShape(12.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        IconButton(
                            onClick = {
                                if (commentInput.isNotBlank()) {
                                    onAddComment(commentInput)
                                    commentInput = ""
                                }
                            },
                            modifier = Modifier
                                .size(44.dp)
                                .background(RoyalBlue, CircleShape)
                        ) {
                            Icon(Icons.Default.Send, contentDescription = "Send", tint = Color.White, modifier = Modifier.size(18.dp))
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CreatePostDialog(
    isAdmin: Boolean = false,
    userName: String = "Student",
    onDismiss: () -> Unit,
    onSubmit: (content: String, tag: String, imageUrl: String?, videoUrl: String?, author: String) -> Unit
) {
    var content by remember { mutableStateOf("") }
    var selectedTag by remember { mutableStateOf(if (isAdmin) "Official" else "Academic") }
    var imageUrlText by remember { mutableStateOf("") }
    var videoUrlText by remember { mutableStateOf("") }
    val author = if (isAdmin) "HU Freshman" else userName.ifBlank { "Student" }
    val tags = if (isAdmin) {
        listOf("Official", "Academic", "Ask Anyone", "Tips", "Campus Life", "Exams")
    } else {
        listOf("Academic", "Ask Anyone", "Tips", "Campus Life", "Exams")
    }
    val scrollState = rememberScrollState()

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(
            usePlatformDefaultWidth = false,
            decorFitsSystemWindows = false
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.55f))
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                    onClick = onDismiss
                )
                .padding(horizontal = 16.dp, vertical = 20.dp)
                .imePadding(),
            contentAlignment = Alignment.Center
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .widthIn(max = 520.dp)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null
                    ) {}
                    .clip(RoundedCornerShape(24.dp)),
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                ) {
                    // ── Header ──────────────────────────────────────────
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(42.dp)
                                .clip(CircleShape)
                                .background(
                                    if (isAdmin) Brush.linearGradient(listOf(RoyalBlue, ElectricIndigo))
                                    else Brush.linearGradient(listOf(ElectricIndigo, EmeraldGreen))
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = author.firstOrNull()?.uppercase() ?: "H",
                                color = Color.White,
                                fontWeight = FontWeight.Bold,
                                fontSize = 17.sp
                            )
                        }

                        Spacer(modifier = Modifier.width(12.dp))

                        Column(modifier = Modifier.weight(1f)) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(
                                    text = author,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 15.sp,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                                if (isAdmin) {
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Box(
                                        modifier = Modifier
                                            .background(RoyalBlue, RoundedCornerShape(4.dp))
                                            .padding(horizontal = 6.dp, vertical = 2.dp)
                                    ) {
                                        Text(
                                            text = "HU Freshman",
                                            color = Color.White,
                                            fontSize = 9.sp,
                                            fontWeight = FontWeight.ExtraBold
                                        )
                                    }
                                }
                            }
                            Text(
                                text = if (isAdmin) "Publish announcement to freshman community" else "Share with freshman community",
                                fontSize = 11.5.sp,
                                color = Slate700
                            )
                        }

                        IconButton(
                            onClick = onDismiss,
                            modifier = Modifier
                                .size(32.dp)
                                .clip(CircleShape)
                                .background(Slate100)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = "Close",
                                tint = Slate700,
                                modifier = Modifier.size(18.dp)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    // ── Category Pills ──────────────────────────────────
                    Text(
                        text = "CATEGORY",
                        fontSize = 10.5.sp,
                        fontWeight = FontWeight.Bold,
                        color = Slate700,
                        letterSpacing = 0.5.sp
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .horizontalScroll(rememberScrollState()),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        tags.forEach { tag ->
                            val isSelected = selectedTag == tag
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(50))
                                    .background(
                                        if (isSelected) Brush.linearGradient(listOf(RoyalBlue, ElectricIndigo))
                                        else Brush.linearGradient(listOf(Slate100, Slate100))
                                    )
                                    .clickable { selectedTag = tag }
                                    .padding(horizontal = 14.dp, vertical = 7.dp)
                            ) {
                                Text(
                                    text = if (tag == "Official") "📢 $tag" else "# $tag",
                                    color = if (isSelected) Color.White else Slate700,
                                    fontSize = 12.sp,
                                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    // ── Scrollable Body ─────────────────────────────────
                    Column(
                        modifier = Modifier
                            .weight(1f, fill = false)
                            .verticalScroll(scrollState),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        // Modern text field container
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(16.dp))
                                .background(Color(0xFFF8FAFC))
                                .border(1.dp, Color(0xFFE2E8F0), RoundedCornerShape(16.dp))
                                .padding(12.dp)
                        ) {
                            Column {
                                OutlinedTextField(
                                    value = content,
                                    onValueChange = { content = it },
                                    placeholder = {
                                        Text(
                                            text = if (isAdmin) "Write your announcement, update, or notice here..."
                                            else "What's on your mind? Share tips, questions, or helpful notes...",
                                            fontSize = 14.sp,
                                            color = Color(0xFF94A3B8)
                                        )
                                    },
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .defaultMinSize(minHeight = 120.dp),
                                    colors = OutlinedTextFieldDefaults.colors(
                                        focusedBorderColor = Color.Transparent,
                                        unfocusedBorderColor = Color.Transparent,
                                        focusedContainerColor = Color.Transparent,
                                        unfocusedContainerColor = Color.Transparent
                                    ),
                                    maxLines = 10
                                )

                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.End
                                ) {
                                    Text(
                                        text = "${content.trim().length} chars",
                                        fontSize = 10.5.sp,
                                        color = Color(0xFF94A3B8),
                                        fontWeight = FontWeight.Medium
                                    )
                                }
                            }
                        }

                        // Admin Media Section
                        if (isAdmin) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clip(RoundedCornerShape(16.dp))
                                    .background(Color(0xFFF1F5F9))
                                    .padding(12.dp),
                                verticalArrangement = Arrangement.spacedBy(10.dp)
                            ) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(
                                        Icons.Default.Attachment,
                                        contentDescription = null,
                                        tint = RoyalBlue,
                                        modifier = Modifier.size(16.dp)
                                    )
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Text(
                                        text = "MEDIA ATTACHMENTS (OPTIONAL)",
                                        fontSize = 10.5.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = RoyalBlue,
                                        letterSpacing = 0.5.sp
                                    )
                                }

                                OutlinedTextField(
                                    value = imageUrlText,
                                    onValueChange = { imageUrlText = it },
                                    label = { Text("Image URL", fontSize = 12.sp) },
                                    placeholder = { Text("https://example.com/banner.jpg", fontSize = 12.sp) },
                                    leadingIcon = {
                                        Icon(
                                            Icons.Default.Image,
                                            contentDescription = null,
                                            tint = RoyalBlue,
                                            modifier = Modifier.size(18.dp)
                                        )
                                    },
                                    modifier = Modifier.fillMaxWidth(),
                                    singleLine = true,
                                    shape = RoundedCornerShape(12.dp),
                                    colors = OutlinedTextFieldDefaults.colors(
                                        focusedContainerColor = Color.White,
                                        unfocusedContainerColor = Color.White,
                                        focusedBorderColor = RoyalBlue,
                                        unfocusedBorderColor = Color(0xFFCBD5E1)
                                    )
                                )

                                if (imageUrlText.isNotBlank()) {
                                    Box(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .height(150.dp)
                                            .clip(RoundedCornerShape(12.dp))
                                            .border(1.dp, RoyalBlue.copy(alpha = 0.3f), RoundedCornerShape(12.dp))
                                    ) {
                                        AsyncImage(
                                            model = imageUrlText.trim(),
                                            contentDescription = "Image preview",
                                            contentScale = ContentScale.Crop,
                                            modifier = Modifier.fillMaxSize()
                                        )
                                        Box(
                                            modifier = Modifier
                                                .align(Alignment.BottomStart)
                                                .padding(8.dp)
                                                .background(Color.Black.copy(alpha = 0.65f), RoundedCornerShape(6.dp))
                                                .padding(horizontal = 8.dp, vertical = 3.dp)
                                        ) {
                                            Text(
                                                text = "Preview",
                                                color = Color.White,
                                                fontSize = 10.sp,
                                                fontWeight = FontWeight.Bold
                                            )
                                        }
                                    }
                                }

                                OutlinedTextField(
                                    value = videoUrlText,
                                    onValueChange = { videoUrlText = it },
                                    label = { Text("Video URL", fontSize = 12.sp) },
                                    placeholder = { Text("https://youtube.com/watch?v=...", fontSize = 12.sp) },
                                    leadingIcon = {
                                        Icon(
                                            Icons.Default.PlayCircle,
                                            contentDescription = null,
                                            tint = RoyalBlue,
                                            modifier = Modifier.size(18.dp)
                                        )
                                    },
                                    modifier = Modifier.fillMaxWidth(),
                                    singleLine = true,
                                    shape = RoundedCornerShape(12.dp),
                                    colors = OutlinedTextFieldDefaults.colors(
                                        focusedContainerColor = Color.White,
                                        unfocusedContainerColor = Color.White,
                                        focusedBorderColor = RoyalBlue,
                                        unfocusedBorderColor = Color(0xFFCBD5E1)
                                    )
                                )

                                if (videoUrlText.isNotBlank()) {
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .clip(RoundedCornerShape(10.dp))
                                            .background(RoyalBlue.copy(alpha = 0.1f))
                                            .padding(10.dp),
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                                    ) {
                                        Icon(
                                            Icons.Default.PlayCircle,
                                            contentDescription = null,
                                            tint = RoyalBlue,
                                            modifier = Modifier.size(20.dp)
                                        )
                                        Text(
                                            text = "Video preview will be playable on post",
                                            fontSize = 12.sp,
                                            color = RoyalBlue,
                                            fontWeight = FontWeight.SemiBold
                                        )
                                    }
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // ── Bottom Action Row ───────────────────────────────
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        TextButton(
                            onClick = onDismiss,
                            colors = ButtonDefaults.textButtonColors(contentColor = Slate700)
                        ) {
                            Text("Cancel", fontWeight = FontWeight.SemiBold, fontSize = 14.sp)
                        }

                        val canSubmit = content.isNotBlank()
                        Button(
                            onClick = {
                                if (canSubmit) {
                                    onSubmit(
                                        content,
                                        selectedTag,
                                        if (isAdmin) imageUrlText.trim().ifBlank { null } else null,
                                        if (isAdmin) videoUrlText.trim().ifBlank { null } else null,
                                        author
                                    )
                                }
                            },
                            enabled = canSubmit,
                            shape = RoundedCornerShape(50),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = RoyalBlue,
                                disabledContainerColor = Slate100
                            ),
                            contentPadding = PaddingValues(horizontal = 24.dp, vertical = 12.dp),
                            elevation = ButtonDefaults.buttonElevation(defaultElevation = if (canSubmit) 3.dp else 0.dp)
                        ) {
                            Icon(
                                imageVector = if (isAdmin) Icons.Default.Campaign else Icons.AutoMirrored.Filled.Send,
                                contentDescription = null,
                                tint = if (canSubmit) Color.White else Slate700.copy(alpha = 0.4f),
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "Publish Post",
                                color = if (canSubmit) Color.White else Slate700.copy(alpha = 0.4f),
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp
                            )
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun EditPostDialog(
    post: CommunityPost,
    onDismiss: () -> Unit,
    onSubmit: (content: String, tag: String, imageUrl: String?, videoUrl: String?) -> Unit
) {
    var content by remember { mutableStateOf(post.content) }
    var selectedTag by remember { mutableStateOf(post.tag) }
    var imageUrlText by remember { mutableStateOf(post.imageUrl ?: "") }
    var videoUrlText by remember { mutableStateOf(post.videoUrl ?: "") }
    val tags = listOf("Official", "Academic", "Ask Anyone", "Tips", "Campus Life", "Exams")
    val scrollState = rememberScrollState()

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(
            usePlatformDefaultWidth = false,
            decorFitsSystemWindows = false
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.55f))
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                    onClick = onDismiss
                )
                .padding(horizontal = 16.dp, vertical = 20.dp)
                .imePadding(),
            contentAlignment = Alignment.Center
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .widthIn(max = 520.dp)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null
                    ) {}
                    .clip(RoundedCornerShape(24.dp)),
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                ) {
                    // ── Header ──────────────────────────────────────────
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(42.dp)
                                .clip(CircleShape)
                                .background(Brush.linearGradient(listOf(RoyalBlue, ElectricIndigo))),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                Icons.Default.Edit,
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier.size(20.dp)
                            )
                        }

                        Spacer(modifier = Modifier.width(12.dp))

                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = "Edit Post",
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            Text(
                                text = "Posted: ${formatPostRelativeTime(post)}",
                                fontSize = 11.5.sp,
                                color = Slate700
                            )
                        }

                        IconButton(
                            onClick = onDismiss,
                            modifier = Modifier
                                .size(32.dp)
                                .clip(CircleShape)
                                .background(Slate100)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = "Close",
                                tint = Slate700,
                                modifier = Modifier.size(18.dp)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    // ── Category Pills ──────────────────────────────────
                    Text(
                        text = "CATEGORY",
                        fontSize = 10.5.sp,
                        fontWeight = FontWeight.Bold,
                        color = Slate700,
                        letterSpacing = 0.5.sp
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .horizontalScroll(rememberScrollState()),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        tags.forEach { tag ->
                            val isSelected = selectedTag.equals(tag, ignoreCase = true)
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(50))
                                    .background(
                                        if (isSelected) Brush.linearGradient(listOf(RoyalBlue, ElectricIndigo))
                                        else Brush.linearGradient(listOf(Slate100, Slate100))
                                    )
                                    .clickable { selectedTag = tag }
                                    .padding(horizontal = 14.dp, vertical = 7.dp)
                            ) {
                                Text(
                                    text = if (tag == "Official") "📢 $tag" else "# $tag",
                                    color = if (isSelected) Color.White else Slate700,
                                    fontSize = 12.sp,
                                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    // ── Scrollable Body ─────────────────────────────────
                    Column(
                        modifier = Modifier
                            .weight(1f, fill = false)
                            .verticalScroll(scrollState),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        // Text Area Container
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(16.dp))
                                .background(Color(0xFFF8FAFC))
                                .border(1.dp, Color(0xFFE2E8F0), RoundedCornerShape(16.dp))
                                .padding(12.dp)
                        ) {
                            Column {
                                OutlinedTextField(
                                    value = content,
                                    onValueChange = { content = it },
                                    placeholder = {
                                        Text("Write your post content here...", fontSize = 14.sp, color = Color(0xFF94A3B8))
                                    },
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .defaultMinSize(minHeight = 120.dp),
                                    colors = OutlinedTextFieldDefaults.colors(
                                        focusedBorderColor = Color.Transparent,
                                        unfocusedBorderColor = Color.Transparent,
                                        focusedContainerColor = Color.Transparent,
                                        unfocusedContainerColor = Color.Transparent
                                    ),
                                    maxLines = 10
                                )

                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.End
                                ) {
                                    Text(
                                        text = "${content.trim().length} chars",
                                        fontSize = 10.5.sp,
                                        color = Color(0xFF94A3B8),
                                        fontWeight = FontWeight.Medium
                                    )
                                }
                            }
                        }

                        // Media Section
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(16.dp))
                                .background(Color(0xFFF1F5F9))
                                .padding(12.dp),
                            verticalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    Icons.Default.Attachment,
                                    contentDescription = null,
                                    tint = RoyalBlue,
                                    modifier = Modifier.size(16.dp)
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(
                                    text = "MEDIA ATTACHMENTS (OPTIONAL)",
                                    fontSize = 10.5.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = RoyalBlue,
                                    letterSpacing = 0.5.sp
                                )
                            }

                            // Image URL
                            OutlinedTextField(
                                value = imageUrlText,
                                onValueChange = { imageUrlText = it },
                                label = { Text("Image URL", fontSize = 12.sp) },
                                placeholder = { Text("https://example.com/image.jpg", fontSize = 12.sp) },
                                leadingIcon = {
                                    Icon(Icons.Default.Image, contentDescription = null, tint = RoyalBlue, modifier = Modifier.size(18.dp))
                                },
                                modifier = Modifier.fillMaxWidth(),
                                singleLine = true,
                                shape = RoundedCornerShape(12.dp),
                                colors = OutlinedTextFieldDefaults.colors(
                                    focusedContainerColor = Color.White,
                                    unfocusedContainerColor = Color.White,
                                    focusedBorderColor = RoyalBlue,
                                    unfocusedBorderColor = Color(0xFFCBD5E1)
                                )
                            )

                            if (imageUrlText.isNotBlank()) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(150.dp)
                                        .clip(RoundedCornerShape(12.dp))
                                        .border(1.dp, RoyalBlue.copy(alpha = 0.3f), RoundedCornerShape(12.dp))
                                ) {
                                    AsyncImage(
                                        model = imageUrlText.trim(),
                                        contentDescription = "Image preview",
                                        contentScale = ContentScale.Crop,
                                        modifier = Modifier.fillMaxSize()
                                    )
                                    Box(
                                        modifier = Modifier
                                            .align(Alignment.BottomStart)
                                            .padding(8.dp)
                                            .background(Color.Black.copy(alpha = 0.65f), RoundedCornerShape(6.dp))
                                            .padding(horizontal = 8.dp, vertical = 3.dp)
                                    ) {
                                        Text(
                                            text = "Preview",
                                            color = Color.White,
                                            fontSize = 10.sp,
                                            fontWeight = FontWeight.Bold
                                        )
                                    }
                                }
                            }

                            // Video URL
                            OutlinedTextField(
                                value = videoUrlText,
                                onValueChange = { videoUrlText = it },
                                label = { Text("Video URL", fontSize = 12.sp) },
                                placeholder = { Text("https://youtube.com/watch?v=...", fontSize = 12.sp) },
                                leadingIcon = {
                                    Icon(Icons.Default.PlayCircle, contentDescription = null, tint = RoyalBlue, modifier = Modifier.size(18.dp))
                                },
                                modifier = Modifier.fillMaxWidth(),
                                singleLine = true,
                                shape = RoundedCornerShape(12.dp),
                                colors = OutlinedTextFieldDefaults.colors(
                                    focusedContainerColor = Color.White,
                                    unfocusedContainerColor = Color.White,
                                    focusedBorderColor = RoyalBlue,
                                    unfocusedBorderColor = Color(0xFFCBD5E1)
                                )
                            )

                            if (videoUrlText.isNotBlank()) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clip(RoundedCornerShape(10.dp))
                                        .background(RoyalBlue.copy(alpha = 0.1f))
                                        .padding(10.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                                ) {
                                    Icon(Icons.Default.PlayCircle, contentDescription = null, tint = RoyalBlue, modifier = Modifier.size(20.dp))
                                    Text(
                                        text = "Video preview will be playable on post",
                                        fontSize = 12.sp,
                                        color = RoyalBlue,
                                        fontWeight = FontWeight.SemiBold
                                    )
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // ── Bottom Action Row ───────────────────────────────
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        TextButton(
                            onClick = onDismiss,
                            colors = ButtonDefaults.textButtonColors(contentColor = Slate700)
                        ) {
                            Text("Cancel", fontWeight = FontWeight.SemiBold, fontSize = 14.sp)
                        }

                        val canSubmit = content.isNotBlank()
                        Button(
                            onClick = {
                                if (canSubmit) {
                                    onSubmit(
                                        content,
                                        selectedTag,
                                        imageUrlText.trim().ifBlank { null },
                                        videoUrlText.trim().ifBlank { null }
                                    )
                                }
                            },
                            enabled = canSubmit,
                            shape = RoundedCornerShape(50),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = RoyalBlue,
                                disabledContainerColor = Slate100
                            ),
                            contentPadding = PaddingValues(horizontal = 24.dp, vertical = 12.dp),
                            elevation = ButtonDefaults.buttonElevation(defaultElevation = if (canSubmit) 3.dp else 0.dp)
                        ) {
                            Icon(
                                Icons.Default.Check,
                                contentDescription = null,
                                tint = if (canSubmit) Color.White else Slate700.copy(alpha = 0.4f),
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "Save Changes",
                                color = if (canSubmit) Color.White else Slate700.copy(alpha = 0.4f),
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp
                            )
                        }
                    }
                }
            }
        }
    }
}
