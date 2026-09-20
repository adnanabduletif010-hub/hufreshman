package com.curiovana.hufreshman.ui.screens

import androidx.compose.animation.AnimatedVisibility
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
import com.curiovana.hufreshman.data.CommunityPost
import com.curiovana.hufreshman.ui.theme.*
import com.curiovana.hufreshman.viewmodel.MainViewModel

@Composable
fun CommunityScreen(viewModel: MainViewModel) {
    val posts by viewModel.communityPosts.collectAsState()
    val userProfile by viewModel.userProfile.collectAsState()
    var selectedTag by remember { mutableStateOf("All") }
    var showCreatePostDialog by remember { mutableStateOf(false) }
    var postToEdit by remember { mutableStateOf<CommunityPost?>(null) }
    var postToDelete by remember { mutableStateOf<CommunityPost?>(null) }

    val tags = listOf("All", "Academic", "Tips", "Campus Life", "Exams", "Official")

    val filteredPosts = remember(posts, selectedTag) {
        if (selectedTag == "All") posts
        else posts.filter { it.tag.equals(selectedTag, ignoreCase = true) }
    }

    Scaffold(
        floatingActionButton = {
            // Admin sees a prominent "Official Post" FAB; all approved members can post
            if (userProfile.isAdmin) {
                ExtendedFloatingActionButton(
                    onClick = { showCreatePostDialog = true },
                    containerColor = RoyalBlue,
                    contentColor = Color.White,
                    icon = { Icon(Icons.Default.Campaign, contentDescription = null) },
                    text = { Text("Official Post", fontWeight = FontWeight.Bold) }
                )
            } else if (userProfile.isApproved) {
                FloatingActionButton(
                    onClick = { showCreatePostDialog = true },
                    containerColor = EmeraldGreen,
                    contentColor = Color.White
                ) {
                    Icon(Icons.Default.Edit, contentDescription = "Create Post")
                }
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
                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .background(Color.White.copy(alpha = 0.15f), CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(Icons.Default.Forum, contentDescription = null, tint = Color.White, modifier = Modifier.size(22.dp))
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

    // Create Post Dialog — admin posts get tagged "Official", student posts get normal tags
    if (showCreatePostDialog) {
        CreatePostDialog(
            isAdmin = userProfile.isAdmin,
            onDismiss = { showCreatePostDialog = false },
            onSubmit = { content, tag ->
                viewModel.createPost(content, tag)
                showCreatePostDialog = false
            }
        )
    }

    // Edit Post Dialog for Admin
    if (postToEdit != null) {
        EditPostDialog(
            post = postToEdit!!,
            onDismiss = { postToEdit = null },
            onSubmit = { newContent, newTag ->
                viewModel.editPost(postToEdit!!.id, newContent, newTag)
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

@Composable
fun CommunityPostCard(
    post: CommunityPost,
    isAdmin: Boolean = false,
    onToggleLike: () -> Unit,
    onAddComment: (String) -> Unit,
    onEditClick: (() -> Unit)? = null,
    onDeleteClick: (() -> Unit)? = null
) {
    var showComments by remember { mutableStateOf(false) }
    var commentInput by remember { mutableStateOf("") }

    val isOfficial = post.role.contains("Admin", ignoreCase = true) ||
                     post.tag.equals("Official", ignoreCase = true)

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
                        text = "📢 OFFICIAL CAMPUS NOTICE",
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
                        text = post.author.firstOrNull()?.toString() ?: "U",
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
                        if (isOfficial) {
                            Spacer(modifier = Modifier.width(5.dp))
                            Box(
                                modifier = Modifier
                                    .background(RoyalBlue, RoundedCornerShape(4.dp))
                                    .padding(horizontal = 5.dp, vertical = 2.dp)
                            ) {
                                Text("Admin", color = Color.White, fontSize = 8.sp, fontWeight = FontWeight.ExtraBold)
                            }
                        }
                    }
                    Text(
                        text = "${post.date} • ${post.tag}",
                        fontSize = 11.sp,
                        color = Slate700
                    )
                }

                if (isAdmin) {
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

            Spacer(modifier = Modifier.height(12.dp))

            // Tag chip
            Box(
                modifier = Modifier
                    .background(
                        if (isOfficial) RoyalBlue.copy(alpha = 0.12f) else Slate700.copy(alpha = 0.08f),
                        RoundedCornerShape(6.dp)
                    )
                    .padding(horizontal = 8.dp, vertical = 3.dp)
            ) {
                Text(post.tag, fontSize = 10.sp, color = if (isOfficial) RoyalBlue else Slate700, fontWeight = FontWeight.Bold)
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Actions row
            HorizontalDivider(color = Color(0xFFF1F5F9))
            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
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
                        .padding(horizontal = 10.dp, vertical = 6.dp)
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

                Spacer(modifier = Modifier.width(10.dp))

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
                        .padding(horizontal = 10.dp, vertical = 6.dp)
                ) {
                    Icon(
                        Icons.Outlined.ChatBubbleOutline,
                        contentDescription = "Comments",
                        tint = if (showComments) RoyalBlue else Slate700,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "${post.comments.size} comment${if (post.comments.size != 1) "s" else ""}",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = if (showComments) RoyalBlue else Slate700
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
    isAdmin: Boolean,
    onDismiss: () -> Unit,
    onSubmit: (String, String) -> Unit
) {
    var content by remember { mutableStateOf("") }
    // Admin defaults to "Official" tag; students get normal tags
    var selectedTag by remember { mutableStateOf(if (isAdmin) "Official" else "Academic") }
    val tags = if (isAdmin) {
        listOf("Official", "Academic", "Tips", "Campus Life", "Exams")
    } else {
        listOf("Academic", "Tips", "Campus Life", "Exams")
    }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    if (isAdmin) Icons.Default.Campaign else Icons.Default.Edit,
                    contentDescription = null,
                    tint = if (isAdmin) RoyalBlue else EmeraldGreen
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    if (isAdmin) "Post Official Campus Notice" else "Create Community Post",
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp
                )
            }
        },
        text = {
            Column {
                Text(
                    text = if (isAdmin)
                        "This post will appear with an 📢 OFFICIAL CAMPUS NOTICE banner in the community feed."
                    else
                        "Share freshman advice, exam tips, or discussion topics with the community.",
                    fontSize = 12.sp,
                    color = Slate700,
                    lineHeight = 17.sp
                )
                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState()),
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    tags.forEach { tag ->
                        FilterChip(
                            selected = selectedTag == tag,
                            onClick = { selectedTag = tag },
                            label = { Text(tag, fontSize = 11.sp) },
                            colors = FilterChipDefaults.filterChipColors(
                                selectedContainerColor = if (isAdmin) RoyalBlue else EmeraldGreen,
                                selectedLabelColor = Color.White
                            )
                        )
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField(
                    value = content,
                    onValueChange = { content = it },
                    placeholder = {
                        Text(
                            if (isAdmin) "Write the official notice or announcement..." else "Write your post or question here..."
                        )
                    },
                    modifier = Modifier.fillMaxWidth(),
                    minLines = 4,
                    maxLines = 8,
                    shape = RoundedCornerShape(12.dp)
                )
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    if (content.isNotBlank()) {
                        onSubmit(content, selectedTag)
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isAdmin) RoyalBlue else EmeraldGreen
                ),
                enabled = content.isNotBlank()
            ) {
                Icon(if (isAdmin) Icons.Default.Campaign else Icons.Default.Send, contentDescription = null, modifier = Modifier.size(16.dp))
                Spacer(modifier = Modifier.width(6.dp))
                Text(if (isAdmin) "Publish Official Notice" else "Publish Post")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}

@Composable
fun EditPostDialog(
    post: CommunityPost,
    onDismiss: () -> Unit,
    onSubmit: (content: String, tag: String) -> Unit
) {
    var content by remember { mutableStateOf(post.content) }
    var selectedTag by remember { mutableStateOf(post.tag) }
    val tags = listOf("Official", "Academic", "Tips", "Campus Life", "Exams")

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.Edit, contentDescription = null, tint = RoyalBlue)
                Spacer(modifier = Modifier.width(8.dp))
                Text("Edit Community Post", fontWeight = FontWeight.Bold, fontSize = 16.sp)
            }
        },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                Text(
                    text = "Author: ${post.author} • Original: ${post.date}",
                    fontSize = 11.sp,
                    color = Slate700
                )

                Text("Post Tag / Category:", fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState()),
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    tags.forEach { tag ->
                        FilterChip(
                            selected = selectedTag.equals(tag, ignoreCase = true),
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
                    onValueChange = { content = it },
                    label = { Text("Post Content") },
                    modifier = Modifier.fillMaxWidth(),
                    minLines = 4,
                    maxLines = 8,
                    shape = RoundedCornerShape(12.dp)
                )
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    if (content.isNotBlank()) {
                        onSubmit(content, selectedTag)
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = RoyalBlue),
                enabled = content.isNotBlank()
            ) {
                Icon(Icons.Default.Check, contentDescription = null, modifier = Modifier.size(16.dp))
                Spacer(modifier = Modifier.width(6.dp))
                Text("Save Changes")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}

