package com.curiovana.hufreshman.ui.screens

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Login
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.curiovana.hufreshman.data.EthiopianPhoneUtils
import com.curiovana.hufreshman.ui.theme.*

@Composable
fun LoginDialog(
    onDismiss: () -> Unit,
    onLoginSubmit: (phone: String, secretOrKey: String) -> com.curiovana.hufreshman.data.LoginResult,
    onNavigateToRegister: () -> Unit
) {
    val context = LocalContext.current
    var phone by remember { mutableStateOf("0") }
    var secretOrKey by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }

    val cleanPhone = phone.replace(Regex("[^0-9]"), "")
    val isAdminPhone = com.curiovana.hufreshman.data.AppRepository.ADMIN_PHONE_NUMBERS.any { adminNum ->
        val cleanAdmin = adminNum.replace(Regex("[^0-9]"), "")
        cleanPhone == cleanAdmin || (cleanPhone.length >= 9 && cleanPhone.endsWith(cleanAdmin.takeLast(9)))
    }

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
                    .widthIn(max = 460.dp)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null
                    ) {} // Consume clicks inside card
                    .clip(RoundedCornerShape(26.dp)),
                shape = RoundedCornerShape(26.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFE2E8F0)),
                elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(22.dp)
                ) {
                    // ── Header Row ──────────────────────────────────────
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(44.dp)
                                .clip(RoundedCornerShape(14.dp))
                                .background(
                                    Brush.linearGradient(listOf(Color(0xFF003EC4), RoyalBlue, ElectricIndigo))
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.Login,
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier.size(22.dp)
                            )
                        }

                        Spacer(modifier = Modifier.width(12.dp))

                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = "Log In to HU Freshman",
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.5.sp,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            Text(
                                text = "Enter phone and password to continue",
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

                    Spacer(modifier = Modifier.height(18.dp))

                    // ── Error Banner ────────────────────────────────────
                    if (errorMessage.isNotBlank()) {
                        Surface(
                            color = RoseRed.copy(alpha = 0.08f),
                            shape = RoundedCornerShape(12.dp),
                            border = androidx.compose.foundation.BorderStroke(1.dp, RoseRed.copy(alpha = 0.25f)),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Row(
                                modifier = Modifier.padding(12.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    Icons.Default.ErrorOutline,
                                    contentDescription = null,
                                    tint = RoseRed,
                                    modifier = Modifier.size(18.dp)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = errorMessage,
                                    color = RoseRed,
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Medium
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(12.dp))
                    }

                    // ── Form Inputs ─────────────────────────────────────
                    Column(verticalArrangement = Arrangement.spacedBy(14.dp)) {
                        OutlinedTextField(
                            value = phone,
                            onValueChange = {
                                phone = EthiopianPhoneUtils.formatInput(it)
                                errorMessage = ""
                            },
                            label = { Text("Phone Number") },
                            placeholder = { Text("09... or 07...") },
                            supportingText = {
                                Text("Ethiopian format: starts with 09 or 07 (10 digits)", fontSize = 11.sp, color = Slate700)
                            },
                            leadingIcon = {
                                Icon(Icons.Default.Phone, contentDescription = null, tint = RoyalBlue)
                            },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                            shape = RoundedCornerShape(14.dp),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedContainerColor = Color(0xFFF8FAFC),
                                unfocusedContainerColor = Color(0xFFF8FAFC),
                                focusedBorderColor = RoyalBlue,
                                unfocusedBorderColor = Color(0xFFCBD5E1)
                            ),
                            modifier = Modifier.fillMaxWidth(),
                            singleLine = true
                        )

                        OutlinedTextField(
                            value = secretOrKey,
                            onValueChange = {
                                secretOrKey = it
                                errorMessage = ""
                            },
                            label = { Text(if (isAdminPhone) "Admin Passcode" else "Password") },
                            placeholder = { Text(if (isAdminPhone) "Enter your admin passcode" else "Enter your password") },
                            leadingIcon = {
                                Icon(Icons.Default.Lock, contentDescription = null, tint = RoyalBlue)
                            },
                            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                            trailingIcon = {
                                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                                    Icon(
                                        if (passwordVisible) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                                        contentDescription = if (passwordVisible) "Hide password" else "Show password",
                                        tint = Slate700
                                    )
                                }
                            },
                            shape = RoundedCornerShape(14.dp),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedContainerColor = Color(0xFFF8FAFC),
                                unfocusedContainerColor = Color(0xFFF8FAFC),
                                focusedBorderColor = RoyalBlue,
                                unfocusedBorderColor = Color(0xFFCBD5E1)
                            ),
                            modifier = Modifier.fillMaxWidth(),
                            singleLine = true
                        )

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.End,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Don't have an account?",
                                fontSize = 12.sp,
                                color = Slate700
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = "Register →",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = RoyalBlue,
                                modifier = Modifier.clickable {
                                    onDismiss()
                                    onNavigateToRegister()
                                }
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(18.dp))
                    HorizontalDivider(color = Color(0xFFF1F5F9), thickness = 1.dp)
                    Spacer(modifier = Modifier.height(14.dp))

                    // ── Actions Row ─────────────────────────────────────
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

                        Button(
                            onClick = {
                                val phoneError = EthiopianPhoneUtils.getValidationError(phone)
                                if (phoneError != null && !isAdminPhone) {
                                    errorMessage = phoneError
                                } else if (secretOrKey.isBlank()) {
                                    errorMessage = if (isAdminPhone) "Please enter your administrator passcode." else "Please enter your password."
                                } else {
                                    when (val result = onLoginSubmit(phone, secretOrKey)) {
                                        is com.curiovana.hufreshman.data.LoginResult.Success -> {
                                            if (result.isAdmin) {
                                                Toast.makeText(context, "Welcome Administrator! Admin Panel unlocked.", Toast.LENGTH_LONG).show()
                                            } else {
                                                Toast.makeText(context, "Logged in successfully!", Toast.LENGTH_SHORT).show()
                                            }
                                            onDismiss()
                                        }
                                        is com.curiovana.hufreshman.data.LoginResult.Error -> {
                                            errorMessage = if (result.message == "ADMIN_PASSCODE_REQUIRED") {
                                                "Please enter your administrator passcode."
                                            } else {
                                                result.message
                                            }
                                        }
                                    }
                                }
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = RoyalBlue),
                            shape = RoundedCornerShape(50),
                            elevation = ButtonDefaults.buttonElevation(defaultElevation = 3.dp),
                            contentPadding = PaddingValues(horizontal = 24.dp, vertical = 12.dp)
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.Login,
                                contentDescription = null,
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = if (isAdminPhone) "Unlock Admin" else "Log In",
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
fun LogoutConfirmDialog(
    onDismiss: () -> Unit,
    onConfirmLogout: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.AutoMirrored.Filled.Logout, contentDescription = null, tint = RoseRed)
                Spacer(modifier = Modifier.width(8.dp))
                Text("Log Out", fontWeight = FontWeight.Bold)
            }
        },
        text = {
            Text("Are you sure you want to log out of HU Freshman? You can log back in at any time with your registered phone number.", fontSize = 13.sp)
        },
        confirmButton = {
            Button(
                onClick = {
                    onConfirmLogout()
                    onDismiss()
                },
                colors = ButtonDefaults.buttonColors(containerColor = RoseRed)
            ) {
                Text("Log Out")
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
fun DeleteAccountDialog(
    onDismiss: () -> Unit,
    onConfirmDelete: () -> Unit
) {
    val context = LocalContext.current
    var confirmText by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(decorFitsSystemWindows = false),
        modifier = Modifier.imePadding(),
        title = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.DeleteForever, contentDescription = null, tint = RoseRed)
                Spacer(modifier = Modifier.width(8.dp))
                Text("Delete Account & Data", fontWeight = FontWeight.Bold)
            }
        },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                Text(
                    text = "In compliance with Google Play Store User Data & Account Deletion policy, this action will permanently delete:",
                    fontSize = 12.sp,
                    color = Slate700
                )

                Text(
                    text = "• Your student registration records\n• Submitted membership transaction reference\n• Bookmarks and exam revision history\n• Local profile credentials",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium,
                    color = Slate900
                )

                Text(
                    text = "Type DELETE to confirm account erasure:",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = RoseRed
                )

                OutlinedTextField(
                    value = confirmText,
                    onValueChange = { confirmText = it },
                    placeholder = { Text("DELETE") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    if (confirmText.trim().equals("DELETE", ignoreCase = false)) {
                        onConfirmDelete()
                        onDismiss()
                        Toast.makeText(context, "Account and personal data erased.", Toast.LENGTH_LONG).show()
                    }
                },
                enabled = confirmText.trim() == "DELETE",
                colors = ButtonDefaults.buttonColors(containerColor = RoseRed)
            ) {
                Text("Erase Account & Data")
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
fun PrivacyPolicyDialog(
    onDismiss: () -> Unit
) {
    val context = LocalContext.current

    Dialog(onDismissRequest = onDismiss) {
        Surface(
            shape = RoundedCornerShape(20.dp),
            color = MaterialTheme.colorScheme.surface,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.85f)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Security, contentDescription = null, tint = RoyalBlue, modifier = Modifier.size(24.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Privacy Policy & Terms", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                    }
                    IconButton(onClick = onDismiss) {
                        Icon(Icons.Default.Close, contentDescription = "Close")
                    }
                }

                HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))

                Column(
                    modifier = Modifier
                        .weight(1f)
                        .verticalScroll(rememberScrollState()),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Text("Last updated: September 2026", fontSize = 11.sp, color = Slate700)

                    Text("1. Academic Services & Campus Membership", fontWeight = FontWeight.Bold, fontSize = 13.sp, color = RoyalBlue)
                    Text(
                        text = "HU Freshman is an academic companion application for Ethiopian higher education freshman students, providing past exam preparation materials, academic questions, and campus directories. Registration is utilized for student campus organization membership, directory verification, and student community support.",
                        fontSize = 12.sp,
                        lineHeight = 17.sp,
                        color = Slate800
                    )

                    Text("2. Information We Collect", fontWeight = FontWeight.Bold, fontSize = 13.sp, color = RoyalBlue)
                    Text(
                        text = "• Student Registration: When creating an account, we collect your Full Name, University Name, Academic Year, and Phone Number.\n• Community Content: Text posts and comments submitted to the community discussion board.\n• Local Usage Data: Bookmarked questions and test results are stored locally on your device for revision.",
                        fontSize = 12.sp,
                        lineHeight = 17.sp,
                        color = Slate800
                    )

                    Text("3. Data Sharing & Service Providers", fontWeight = FontWeight.Bold, fontSize = 13.sp, color = RoyalBlue)
                    Text(
                        text = "We do NOT sell, rent, license, or monetize any student personal data with advertisers or data brokers. Data is securely processed using Google Firebase cloud services solely for authentication, database hosting, and service delivery.",
                        fontSize = 12.sp,
                        lineHeight = 17.sp,
                        color = Slate800
                    )

                    Text("4. Account & Data Deletion Rights", fontWeight = FontWeight.Bold, fontSize = 13.sp, color = RoyalBlue)
                    Text(
                        text = "In accordance with Google Play User Data policies, users can permanently delete their account and wipe all stored data at any time directly through the 'Delete Account & Data' button in the Profile tab, or by emailing our support team (processed within 30 days).",
                        fontSize = 12.sp,
                        lineHeight = 17.sp,
                        color = Slate800
                    )

                    Text("5. Contact & Support", fontWeight = FontWeight.Bold, fontSize = 13.sp, color = RoyalBlue)
                    Text(
                        text = "For privacy inquiries, support, or manual data requests, contact:\nEmail: cruad67@gmail.com\nPhone: +251 955 903 175\nTelegram: @HUfreshman1",
                        fontSize = 12.sp,
                        lineHeight = 17.sp,
                        color = Slate800
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                Button(
                    onClick = onDismiss,
                    colors = ButtonDefaults.buttonColors(containerColor = RoyalBlue),
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Close Privacy Policy")
                }
            }
        }
    }
}
