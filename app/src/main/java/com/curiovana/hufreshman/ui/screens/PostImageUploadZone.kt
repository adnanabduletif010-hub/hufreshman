package com.curiovana.hufreshman.ui.screens

import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.curiovana.hufreshman.data.CloudinaryUploader
import com.curiovana.hufreshman.ui.theme.*
import kotlinx.coroutines.launch

/**
 * Dropzone for admins to upload actual images to Cloudinary for community/official posts,
 * in addition to the existing image URL and video URL inputs.
 * Images are uploaded and stored directly in Cloudinary (not Firebase Storage),
 * ensuring 0 effect on the Firebase Spark quota.
 */
@Composable
fun PostImageUploadZone(
    imageUrl: String,
    onImageUrlChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    uploaderTag: String = "admin_post"
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    var isUploading by remember { mutableStateOf(false) }
    var uploadError by remember { mutableStateOf<String?>(null) }
    var localUri by remember { mutableStateOf<Uri?>(null) }
    var showUrlInput by remember { mutableStateOf(false) }

    val handleSelectedUri: (Uri?) -> Unit = { uri ->
        if (uri != null) {
            localUri = uri
            isUploading = true
            uploadError = null
            scope.launch {
                val url = CloudinaryUploader.uploadImage(
                    context = context,
                    imageUri = uri,
                    phoneNumber = uploaderTag,
                    folder = "hufreshman-posts"
                )
                isUploading = false
                if (url != null) {
                    onImageUrlChange(url)
                    Toast.makeText(context, "Image uploaded to Cloudinary successfully!", Toast.LENGTH_SHORT).show()
                } else {
                    uploadError = "Upload failed. Please check internet connection and try again."
                    Toast.makeText(context, "Upload failed. Please try again.", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    val photoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = handleSelectedUri
    )

    val getContentLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = handleSelectedUri
    )

    val openPicker = {
        try {
            photoPickerLauncher.launch(
                PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
            )
        } catch (e: Exception) {
            try {
                getContentLauncher.launch("image/*")
            } catch (e2: Exception) {
                Toast.makeText(context, "Could not open gallery on this device.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    Column(modifier = modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(8.dp)) {
        val hasImage = imageUrl.isNotBlank() || localUri != null

        if (hasImage) {
            // Preview Card with Controls
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .clip(RoundedCornerShape(14.dp))
                    .background(Color(0xFF0F172A))
                    .border(1.5.dp, if (isUploading) RoyalBlue else EmeraldGreen, RoundedCornerShape(14.dp))
            ) {
                AsyncImage(
                    model = if (imageUrl.isNotBlank()) imageUrl else localUri,
                    contentDescription = "Post image preview",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )

                if (isUploading) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.Black.copy(alpha = 0.65f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            CircularProgressIndicator(color = Color.White, strokeWidth = 3.dp, modifier = Modifier.size(36.dp))
                            Spacer(modifier = Modifier.height(10.dp))
                            Text(
                                "Uploading to Cloudinary...",
                                color = Color.White,
                                fontWeight = FontWeight.Bold,
                                fontSize = 12.sp
                            )
                        }
                    }
                } else {
                    // Badge: Stored in Cloudinary
                    Surface(
                        color = EmeraldGreen.copy(alpha = 0.9f),
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier
                            .align(Alignment.TopStart)
                            .padding(8.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                        ) {
                            Icon(Icons.Default.CloudDone, contentDescription = null, tint = Color.White, modifier = Modifier.size(13.dp))
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                if (imageUrl.contains("cloudinary")) "Cloudinary Image" else "Image Attached",
                                color = Color.White,
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }

                    // Top-right actions: Change & Remove
                    Row(
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(8.dp),
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        IconButton(
                            onClick = openPicker,
                            modifier = Modifier
                                .size(32.dp)
                                .background(Color.Black.copy(alpha = 0.65f), CircleShape)
                        ) {
                            Icon(Icons.Default.Edit, contentDescription = "Change", tint = Color.White, modifier = Modifier.size(16.dp))
                        }
                        IconButton(
                            onClick = {
                                localUri = null
                                onImageUrlChange("")
                            },
                            modifier = Modifier
                                .size(32.dp)
                                .background(Color.Black.copy(alpha = 0.65f), CircleShape)
                        ) {
                            Icon(Icons.Default.Delete, contentDescription = "Remove", tint = Color.White, modifier = Modifier.size(16.dp))
                        }
                    }
                }
            }
        } else {
            // Upload Dropzone
            Surface(
                shape = RoundedCornerShape(14.dp),
                color = Color(0xFFF8FAFC),
                border = androidx.compose.foundation.BorderStroke(1.5.dp, Color(0xFF93C5FD)),
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable(enabled = !isUploading) { openPicker() }
            ) {
                Column(
                    modifier = Modifier.padding(18.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    if (isUploading) {
                        CircularProgressIndicator(color = RoyalBlue, modifier = Modifier.size(32.dp))
                        Spacer(modifier = Modifier.height(8.dp))
                        Text("Uploading image to Cloudinary...", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = RoyalBlue)
                    } else {
                        Box(
                            modifier = Modifier
                                .size(44.dp)
                                .background(RoyalBlue.copy(alpha = 0.12f), CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(Icons.Default.AddPhotoAlternate, contentDescription = null, tint = RoyalBlue, modifier = Modifier.size(24.dp))
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Text("Upload Actual Image (Cloudinary)", fontWeight = FontWeight.Bold, fontSize = 13.sp, color = RoyalBlue)
                        Spacer(modifier = Modifier.height(2.dp))
                        Text("Tap to select from gallery • Stored in Cloudinary (0 Firebase consumption)", fontSize = 10.5.sp, color = Slate700)
                    }
                }
            }
        }

        if (uploadError != null) {
            Text(uploadError ?: "", color = RoseRed, fontSize = 11.sp, fontWeight = FontWeight.Medium)
        }

        // Toggle to enter direct image URL instead / or see the uploaded URL
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextButton(onClick = { showUrlInput = !showUrlInput }) {
                Icon(
                    if (showUrlInput) Icons.Default.KeyboardArrowUp else Icons.Default.Link,
                    contentDescription = null,
                    modifier = Modifier.size(14.dp),
                    tint = Slate700
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    if (showUrlInput) "Hide URL link input" else "Or paste Image URL manually",
                    fontSize = 11.sp,
                    color = Slate700
                )
            }

            if (imageUrl.isNotBlank()) {
                Text(
                    text = "✓ Image ready",
                    fontSize = 11.sp,
                    color = EmeraldGreen,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        if (showUrlInput) {
            OutlinedTextField(
                value = imageUrl,
                onValueChange = onImageUrlChange,
                label = { Text("Direct Image URL", fontSize = 12.sp) },
                placeholder = { Text("https://res.cloudinary.com/...", fontSize = 11.sp) },
                leadingIcon = { Icon(Icons.Default.Image, contentDescription = null, tint = RoyalBlue, modifier = Modifier.size(16.dp)) },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                shape = RoundedCornerShape(12.dp)
            )
        }
    }
}
