package com.curiovana.hufreshman.ui.screens

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.HelpOutline
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.curiovana.hufreshman.data.UserProfile
import com.curiovana.hufreshman.ui.theme.*
import com.curiovana.hufreshman.viewmodel.MainViewModel

@Composable
fun WaitingApprovalScreen(
    userProfile: UserProfile,
    viewModel: MainViewModel,
    onEditRegistration: () -> Unit
) {
    val context = LocalContext.current
    var isChecking by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(rememberScrollState())
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        // Large Status Icon / Pulse
        Box(
            modifier = Modifier
                .size(90.dp)
                .background(
                    brush = Brush.radialGradient(
                        colors = listOf(
                            AmberWarning.copy(alpha = 0.25f),
                            AmberWarning.copy(alpha = 0.05f),
                            Color.Transparent
                        )
                    ),
                    shape = CircleShape
                ),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .size(64.dp)
                    .background(AmberWarning.copy(alpha = 0.15f), CircleShape)
                    .border(2.dp, AmberWarning, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    Icons.Default.HourglassTop,
                    contentDescription = "Pending Approval",
                    tint = AmberWarning,
                    modifier = Modifier.size(34.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(18.dp))

        Text(
            text = "Verification In Progress",
            fontWeight = FontWeight.Black,
            fontSize = 22.sp,
            color = MaterialTheme.colorScheme.onBackground,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = "ክፍያዎ በማረጋገጥ ላይ ይገኛል",
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            color = RoyalBlue,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = "Your account is currently waiting for admin transaction confirmation.",
            fontSize = 13.sp,
            color = Slate700,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))

        // Status Card
        Card(
            shape = RoundedCornerShape(18.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            elevation = CardDefaults.cardElevation(defaultElevation = 3.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(18.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Submitted Transaction",
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Box(
                        modifier = Modifier
                            .background(AmberWarning.copy(alpha = 0.15f), RoundedCornerShape(8.dp))
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                    ) {
                        Text(
                            text = "⏳ PENDING APPROVAL",
                            fontSize = 10.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = AmberWarning
                        )
                    }
                }

                HorizontalDivider(modifier = Modifier.padding(vertical = 12.dp))

                InfoRow(label = "Student Name", value = userProfile.name)
                InfoRow(label = "University", value = userProfile.university)
                InfoRow(label = "Academic Year", value = userProfile.academicYear)
                InfoRow(label = "Phone Number", value = userProfile.phoneNumber.ifBlank { "Not provided" })
                InfoRow(label = "Payment Method", value = userProfile.paymentMethod.ifBlank { "Telebirr" })

                Spacer(modifier = Modifier.height(6.dp))

                // Transaction Number with Copy
                Text(
                    text = "Screenshot / Reference Number:",
                    fontSize = 12.sp,
                    color = Slate700,
                    fontWeight = FontWeight.Medium
                )
                Spacer(modifier = Modifier.height(4.dp))
                Surface(
                    shape = RoundedCornerShape(10.dp),
                    color = Color(0xFFF1F5F9),
                    border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFCBD5E1)),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 12.dp, vertical = 8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = userProfile.transactionId.ifBlank { "N/A" },
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily.Monospace,
                            fontSize = 14.sp,
                            color = RoyalBlueDark
                        )
                        IconButton(
                            onClick = {
                                if (userProfile.transactionId.isNotBlank()) {
                                    val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                                    val clip = ClipData.newPlainText("Transaction ID", userProfile.transactionId)
                                    clipboard.setPrimaryClip(clip)
                                    Toast.makeText(context, "Transaction ID copied!", Toast.LENGTH_SHORT).show()
                                }
                            },
                            modifier = Modifier.size(28.dp)
                        ) {
                            Icon(Icons.Default.ContentCopy, contentDescription = "Copy", tint = Slate700, modifier = Modifier.size(16.dp))
                        }
                    }
                }
            }
        }

        // Rejection / Error alert if any
        if (!userProfile.rejectionReason.isNullOrBlank()) {
            Spacer(modifier = Modifier.height(14.dp))
            Card(
                shape = RoundedCornerShape(14.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFFFECEE)),
                border = androidx.compose.foundation.BorderStroke(1.dp, RoseRed.copy(alpha = 0.4f)),
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(modifier = Modifier.padding(14.dp), verticalAlignment = Alignment.Top) {
                    Icon(Icons.Default.ErrorOutline, contentDescription = null, tint = RoseRed, modifier = Modifier.size(22.dp))
                    Spacer(modifier = Modifier.width(10.dp))
                    Column {
                        Text("Notice from Admin", fontWeight = FontWeight.Bold, fontSize = 13.sp, color = RoseRed)
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(userProfile.rejectionReason, fontSize = 12.sp, color = Slate900)
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Explanation / Policy Card
        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFEFF6FF)),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(14.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.AutoMirrored.Filled.HelpOutline, contentDescription = null, tint = RoyalBlue, modifier = Modifier.size(18.dp))
                    Spacer(modifier = Modifier.width(6.dp))
                    Text("Why is this required?", fontWeight = FontWeight.Bold, fontSize = 13.sp, color = RoyalBlue)
                }
                HorizontalDivider(color = RoyalBlue.copy(alpha = 0.15f))
                Row(verticalAlignment = Alignment.Top) {
                    Icon(Icons.Default.HourglassTop, contentDescription = null, tint = AmberWarning, modifier = Modifier.size(15.dp).padding(top = 1.dp))
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "Your payment is under review.",
                        fontSize = 12.sp, lineHeight = 17.sp, color = Slate800, fontWeight = FontWeight.SemiBold
                    )
                }
                Row(verticalAlignment = Alignment.Top) {
                    Icon(Icons.Default.AdminPanelSettings, contentDescription = null, tint = RoyalBlue, modifier = Modifier.size(15.dp).padding(top = 1.dp))
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "Admin will approve your membership soon.",
                        fontSize = 12.sp, lineHeight = 17.sp, color = Slate800
                    )
                }
                Row(verticalAlignment = Alignment.Top) {
                    Icon(Icons.Default.CheckCircleOutline, contentDescription = null, tint = EmeraldGreen, modifier = Modifier.size(15.dp).padding(top = 1.dp))
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "You will be a member soon — please wait a moment.",
                        fontSize = 12.sp, lineHeight = 17.sp, color = Slate800
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Action Buttons
        Button(
            onClick = {
                isChecking = true
                viewModel.refreshUserProfile()
                Toast.makeText(context, "Checking approval status...", Toast.LENGTH_SHORT).show()
            },
            colors = ButtonDefaults.buttonColors(containerColor = RoyalBlue),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
        ) {
            Icon(Icons.Default.Refresh, contentDescription = null, modifier = Modifier.size(18.dp))
            Spacer(modifier = Modifier.width(8.dp))
            Text("Check Approval Status", fontWeight = FontWeight.Bold, fontSize = 14.sp)
        }

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedButton(
            onClick = onEditRegistration,
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(46.dp)
        ) {
            Icon(Icons.Default.Edit, contentDescription = null, modifier = Modifier.size(16.dp))
            Spacer(modifier = Modifier.width(8.dp))
            Text("Edit / Re-enter Transaction ID", fontWeight = FontWeight.SemiBold, fontSize = 13.sp)
        }

        Spacer(modifier = Modifier.height(10.dp))

        // Contact Admin
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            OutlinedButton(
                onClick = {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://t.me/HUfreshman1"))
                    context.startActivity(intent)
                },
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.weight(1f).height(44.dp)
            ) {
                Icon(Icons.AutoMirrored.Filled.Send, contentDescription = null, modifier = Modifier.size(14.dp))
                Spacer(modifier = Modifier.width(4.dp))
                Text("Telegram (@HUfreshman1)", fontSize = 12.sp)
            }

            OutlinedButton(
                onClick = {
                    val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:0955903175"))
                    context.startActivity(intent)
                },
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.weight(1f).height(44.dp)
            ) {
                Icon(Icons.Default.Phone, contentDescription = null, modifier = Modifier.size(14.dp))
                Spacer(modifier = Modifier.width(4.dp))
                Text("Call 0955903175", fontSize = 12.sp)
            }
        }

        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
private fun InfoRow(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = label, fontSize = 12.sp, color = Slate700)
        Text(text = value, fontSize = 12.sp, fontWeight = FontWeight.SemiBold, color = Slate900)
    }
}
