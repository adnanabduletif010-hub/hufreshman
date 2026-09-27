package com.curiovana.hufreshman.ui.screens

import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.CloudUpload
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.curiovana.hufreshman.data.CloudinaryUploader
import com.curiovana.hufreshman.ui.theme.EmeraldGreen
import com.curiovana.hufreshman.ui.theme.RoyalBlue
import com.curiovana.hufreshman.ui.theme.Slate700
import com.curiovana.hufreshman.ui.theme.Slate900
import kotlinx.coroutines.launch

/**
 * Modern rectangular upload dropzone for verification screenshots.
 * Supports Android Photo Picker with automatic fallback to standard file/gallery picker.
 */
@Composable
fun ScreenshotUploadZone(
    screenshotUri: Uri?,
    screenshotUrl: String,
    isUploading: Boolean,
    phoneNumber: String,
    onUploadStarted: () -> Unit,
    onUploadSuccess: (url: String, uri: Uri) -> Unit,
    onUploadError: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    // Handler when user picks an image URI
    val handleSelectedUri: (Uri?) -> Unit = { uri ->
        if (uri != null) {
            onUploadStarted()
            scope.launch {
                val url = CloudinaryUploader.uploadImage(context, uri, phoneNumber)
                if (url != null) {
                    onUploadSuccess(url, uri)
                } else {
                    onUploadError("Upload failed. Please check internet connection and try again.")
                }
            }
        }
    }

    // 1. Android Photo Picker (Modern Android 11-15 standard)
    val photoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = handleSelectedUri
    )

    // 2. Fallback system picker for devices without Google Play Photo Picker
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

    val hasImage = screenshotUri != null || screenshotUrl.isNotBlank()

    Surface(
        shape = RoundedCornerShape(16.dp),
        color = if (hasImage) Color(0xFF0F172A) else Color(0xFFF8FAFC),
        border = androidx.compose.foundation.BorderStroke(
            width = if (hasImage) 2.dp else 1.5.dp,
            color = when {
                isUploading -> RoyalBlue
                hasImage -> EmeraldGreen
                else -> Color(0xFF93C5FD)
            }
        ),
        modifier = modifier
            .fillMaxWidth()
            .height(175.dp)
            .clip(RoundedCornerShape(16.dp))
            .clickable(enabled = !isUploading) { openPicker() }
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            if (isUploading) {
                // ── Uploading State ──
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.padding(16.dp)
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(36.dp),
                        color = RoyalBlue,
                        strokeWidth = 3.dp
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = "Uploading Screenshot…",
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        color = Slate900
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Optimizing image & saving to cloud",
                        fontSize = 11.5.sp,
                        color = Slate700
                    )
                }
            } else if (hasImage) {
                // ── Image Preview State ──
                val imageModel = screenshotUri ?: screenshotUrl
                AsyncImage(
                    model = imageModel,
                    contentDescription = "Uploaded screenshot",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )

                // Top-right status badge
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp),
                    contentAlignment = Alignment.TopEnd
                ) {
                    Surface(
                        shape = RoundedCornerShape(20.dp),
                        color = EmeraldGreen,
                        shadowElevation = 3.dp
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                Icons.Default.CheckCircle,
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier.size(14.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = "Attached",
                                color = Color.White,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }

                // Bottom bar: Tap to replace
                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.BottomCenter
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                Brush.verticalGradient(
                                    listOf(Color.Transparent, Color.Black.copy(alpha = 0.75f))
                                )
                            )
                            .padding(vertical = 8.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                Icons.Default.Edit,
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier.size(13.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "Tap anywhere to replace screenshot",
                                color = Color.White,
                                fontSize = 11.5.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }
                }
            } else {
                // ── Empty / Drag & Tap Zone ──
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.padding(16.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(50.dp)
                            .background(RoyalBlue.copy(alpha = 0.1f), RoundedCornerShape(14.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            Icons.Default.CloudUpload,
                            contentDescription = "Upload",
                            tint = RoyalBlue,
                            modifier = Modifier.size(28.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = "የተላከበትን ስክሪንሾት ይጫኑ (Upload Screenshot)",
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.5.sp,
                        color = Slate900
                    )
                    Spacer(modifier = Modifier.height(3.dp))
                    Text(
                        text = "ስክሪንሾቱን ከስልክዎ ለመምረጥ እዚህ ይጫኑ",
                        fontSize = 11.5.sp,
                        color = Slate700
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Surface(
                        shape = RoundedCornerShape(6.dp),
                        color = RoyalBlue.copy(alpha = 0.08f)
                    ) {
                        Text(
                            text = "PNG, JPG, or Screenshot",
                            color = RoyalBlue,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                        )
                    }
                }
            }
        }
    }
}
