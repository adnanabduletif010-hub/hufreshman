package com.curiovana.hufreshman.ui.screens

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
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
import com.curiovana.hufreshman.ui.theme.*

@Composable
fun LoginDialog(
    onDismiss: () -> Unit,
    onLoginSubmit: (phone: String, secretOrKey: String) -> com.curiovana.hufreshman.data.LoginResult,
    onNavigateToRegister: () -> Unit
) {
    val context = LocalContext.current
    var phone by remember { mutableStateOf("") }
    var secretOrKey by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }

    val cleanPhone = phone.replace(Regex("[^0-9]"), "")
    val isAdminPhone = com.curiovana.hufreshman.data.AppRepository.ADMIN_PHONE_NUMBERS.any { adminNum ->
        val cleanAdmin = adminNum.replace(Regex("[^0-9]"), "")
        cleanPhone == cleanAdmin || (cleanPhone.length >= 9 && cleanPhone.endsWith(cleanAdmin.takeLast(9)))
    }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    if (isAdminPhone) Icons.Default.AdminPanelSettings else Icons.Default.Login,
                    contentDescription = null,
                    tint = RoyalBlue
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    if (isAdminPhone) "Admin Authentication" else "Log In to HU Freshman",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            }
        },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                Text(
                    text = if (isAdminPhone) {
                        "Authorized Administrator account recognized. Enter your private passcode to access the Admin Control Center:"
                    } else {
                        "Enter your registered phone number and password to log back in:"
                    },
                    fontSize = 12.sp,
                    color = Slate700
                )

                if (errorMessage.isNotBlank()) {
                    Surface(
                        color = RoseRed.copy(alpha = 0.1f),
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = errorMessage,
                            color = RoseRed,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Medium,
                            modifier = Modifier.padding(8.dp)
                        )
                    }
                }

                OutlinedTextField(
                    value = phone,
                    onValueChange = {
                        phone = it
                        errorMessage = ""
                    },
                    label = { Text("Phone Number") },
                    placeholder = { Text("09... or 07...") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )

                if (isAdminPhone) {
                    OutlinedTextField(
                        value = secretOrKey,
                        onValueChange = {
                            secretOrKey = it
                            errorMessage = ""
                        },
                        label = { Text("Admin Passcode") },
                        placeholder = { Text("Enter passcode") },
                        visualTransformation = PasswordVisualTransformation(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true
                    )
                } else {
                    OutlinedTextField(
                        value = secretOrKey,
                        onValueChange = {
                            secretOrKey = it
                            errorMessage = ""
                        },
                        label = { Text("Password") },
                        placeholder = { Text("Your account password") },
                        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        trailingIcon = {
                            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                                Icon(
                                    if (passwordVisible) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                                    contentDescription = if (passwordVisible) "Hide" else "Show"
                                )
                            }
                        },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true
                    )
                }

                TextButton(
                    onClick = {
                        onDismiss()
                        onNavigateToRegister()
                    },
                    modifier = Modifier.align(Alignment.End)
                ) {
                    Text("Don't have an account? Register", fontSize = 11.sp, color = RoyalBlue)
                }
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    if (phone.isBlank()) {
                        errorMessage = "Please enter your phone number."
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
                colors = ButtonDefaults.buttonColors(containerColor = RoyalBlue)
            ) {
                Text(if (isAdminPhone) "Unlock Admin" else "Log In")
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

                    Text("1. Free App & Campus Membership", fontWeight = FontWeight.Bold, fontSize = 13.sp, color = RoyalBlue)
                    Text(
                        text = "HU Freshman is a free academic companion application for Ethiopian higher education freshman students. The app does not sell digital in-app content, premium files, or digital subscriptions. Registration is strictly for optional Student Campus Organization Membership, directory verification, and student community support.",
                        fontSize = 12.sp,
                        lineHeight = 17.sp,
                        color = Slate800
                    )

                    Text("2. Information We Collect", fontWeight = FontWeight.Bold, fontSize = 13.sp, color = RoyalBlue)
                    Text(
                        text = "• Student Membership Registration: When registering for campus club membership, we collect your Full Name, University Name, Academic Year, and Phone Number.\n• Membership Verification Reference: A transaction reference ID from official channels (Telebirr, CBE, or E-Birr) is used solely for manual verification of your physical campus club membership enrollment — not to grant access to in-app content.\n• Local Usage Data: Bookmarked questions and test results are stored locally on your device for revision.",
                        fontSize = 12.sp,
                        lineHeight = 17.sp,
                        color = Slate800
                    )

                    Text("3. Zero Third-Party Sharing", fontWeight = FontWeight.Bold, fontSize = 13.sp, color = RoyalBlue)
                    Text(
                        text = "We do NOT sell, rent, license, or monetize any student data with third-party advertisers, data brokers, or marketing networks. All collected information is strictly utilized to authenticate membership and maintain academic platform integrity.",
                        fontSize = 12.sp,
                        lineHeight = 17.sp,
                        color = Slate800
                    )

                    Text("4. Account & Data Deletion Rights", fontWeight = FontWeight.Bold, fontSize = 13.sp, color = RoyalBlue)
                    Text(
                        text = "In accordance with Google Play User Data policies, users can permanently delete their account and wipe all stored data at any time directly through the 'Delete Account & Data' button in the Profile tab, or by emailing our support team.",
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
