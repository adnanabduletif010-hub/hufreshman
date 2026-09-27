package com.curiovana.hufreshman.ui.screens

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.curiovana.hufreshman.data.CloudinaryUploader
import com.curiovana.hufreshman.data.EthiopianPhoneUtils
import com.curiovana.hufreshman.data.LoginResult
import com.curiovana.hufreshman.data.UserProfile
import com.curiovana.hufreshman.ui.theme.*
import com.curiovana.hufreshman.viewmodel.MainViewModel
import kotlinx.coroutines.launch

enum class AuthMode {
    LOGIN,
    REGISTER
}

@Composable
fun AuthScreen(
    userProfile:   UserProfile,
    viewModel: MainViewModel
) {
    val context = LocalContext.current
    var mode by remember { mutableStateOf(AuthMode.LOGIN) }

    // Login state
    var loginPhone by remember { mutableStateOf("0") }
    var loginPassword by remember { mutableStateOf("") }
    var loginPasswordVisible by remember { mutableStateOf(false) }
    var loginError by remember { mutableStateOf("") }

    // Register state
    val scope = rememberCoroutineScope()
    var regStep by remember { mutableIntStateOf(1) }
    var regName by remember { mutableStateOf("") }
    var regUniversity by remember { mutableStateOf("Haramaya University") }
    var regAcademicYear by remember { mutableStateOf("2026/2027 Freshman") }
    var regPhone by remember { mutableStateOf("0") }
    var regPassword by remember { mutableStateOf("") }
    var regConfirmPassword by remember { mutableStateOf("") }
    var regPasswordVisible by remember { mutableStateOf(false) }
    var regSelectedPaymentMethod by remember { mutableStateOf("Telebirr") }
    var regTransactionId by remember { mutableStateOf("") }
    var regError by remember { mutableStateOf("") }
    var regCheckingPhone by remember { mutableStateOf(false) }
    // Screenshot upload state
    var regScreenshotUri by remember { mutableStateOf<Uri?>(null) }
    var regScreenshotUrl by remember { mutableStateOf("") }
    var regUploading by remember { mutableStateOf(false) }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        RoyalBlue.copy(alpha = 0.06f),
                        MaterialTheme.colorScheme.background,
                        MaterialTheme.colorScheme.background
                    )
                )
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .imePadding()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp, vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(10.dp))

            // App Brand Header — beautiful gradient card
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(26.dp))
                    .background(
                        Brush.linearGradient(listOf(Color(0xFF003EC4), RoyalBlue, ElectricIndigo))
                    )
                    .border(1.dp, Color.White.copy(alpha = 0.2f), RoundedCornerShape(26.dp))
                    .padding(vertical = 26.dp, horizontal = 22.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Box(
                        modifier = Modifier
                            .size(66.dp)
                            .background(
                                Color.White.copy(alpha = 0.18f),
                                shape = RoundedCornerShape(20.dp)
                            )
                            .border(1.dp, Color.White.copy(alpha = 0.35f), RoundedCornerShape(20.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            Icons.Default.School,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(38.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = "HU Freshman",
                        fontWeight = FontWeight.Black,
                        fontSize = 26.sp,
                        letterSpacing = (-0.5).sp,
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Ethiopian University Exam & Academic Hub",
                        fontSize = 12.sp,
                        color = Color.White.copy(alpha = 0.85f),
                        fontWeight = FontWeight.Medium,
                        textAlign = TextAlign.Center
                    )
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Access Gate Notice Banner
            Surface(
                shape = RoundedCornerShape(16.dp),
                color = Color(0xFFFFFBEB),
                border = androidx.compose.foundation.BorderStroke(1.dp, AmberWarning.copy(alpha = 0.3f)),
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 14.dp, vertical = 10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(30.dp)
                            .background(AmberWarning.copy(alpha = 0.15f), RoundedCornerShape(8.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            Icons.Default.Lock,
                            contentDescription = null,
                            tint = AmberWarning,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        text = "Member access is required. Please log in or register to use the app.",
                        fontSize = 11.5.sp,
                        lineHeight = 15.sp,
                        color = Slate800,
                        fontWeight = FontWeight.Medium
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Mode Selector (Log In / Create Account) — sleek segmented pill
            Surface(
                shape = RoundedCornerShape(50),
                color = Color(0xFFF1F5F9),
                border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFE2E8F0)),
                modifier = Modifier.fillMaxWidth().height(48.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxSize().padding(4.dp),
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    // Log In Button
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight()
                            .clip(RoundedCornerShape(50))
                            .background(
                                if (mode == AuthMode.LOGIN) Brush.linearGradient(listOf(RoyalBlue, ElectricIndigo))
                                else Brush.linearGradient(listOf(Color.Transparent, Color.Transparent))
                            )
                            .clickable {
                                mode = AuthMode.LOGIN
                                loginError = ""
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Log In",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold,
                            color = if (mode == AuthMode.LOGIN) Color.White else Slate700
                        )
                    }

                    // Create Account Button
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight()
                            .clip(RoundedCornerShape(50))
                            .background(
                                if (mode == AuthMode.REGISTER) Brush.linearGradient(listOf(RoyalBlue, ElectricIndigo))
                                else Brush.linearGradient(listOf(Color.Transparent, Color.Transparent))
                            )
                            .clickable {
                                mode = AuthMode.REGISTER
                                regError = ""
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Create Account",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold,
                            color = if (mode == AuthMode.REGISTER) Color.White else Slate700
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(18.dp))

            // Animated Mode Container
            AnimatedContent(
                targetState = mode,
                transitionSpec = { fadeIn() togetherWith fadeOut() },
                label = "AuthModeTransition"
            ) { targetMode ->
                if (targetMode == AuthMode.LOGIN) {
                    // ═════════════════════════════════════════
                    // ══ LOG IN FORM ══
                    // ═════════════════════════════════════════
                    Card(
                        shape = RoundedCornerShape(24.dp),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                        border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFE2E8F0)),
                        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(
                            modifier = Modifier.padding(22.dp),
                            verticalArrangement = Arrangement.spacedBy(15.dp)
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Box(
                                    modifier = Modifier
                                        .size(38.dp)
                                        .background(RoyalBlue.copy(alpha = 0.1f), RoundedCornerShape(10.dp)),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(
                                        Icons.Default.Login,
                                        contentDescription = null,
                                        tint = RoyalBlue,
                                        modifier = Modifier.size(20.dp)
                                    )
                                }
                                Spacer(modifier = Modifier.width(12.dp))
                                Column {
                                    Text(
                                        text = "Welcome Back",
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 17.sp,
                                        color = Slate900
                                    )
                                    Text(
                                        text = "Sign in to access your student hub",
                                        fontSize = 12.sp,
                                        color = Slate700
                                    )
                                }
                            }

                            if (loginError.isNotBlank()) {
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
                                            text = loginError,
                                            color = RoseRed,
                                            fontSize = 12.5.sp,
                                            fontWeight = FontWeight.Medium
                                        )
                                    }
                                }
                            }

                            OutlinedTextField(
                                value = loginPhone,
                                onValueChange = {
                                    loginPhone = EthiopianPhoneUtils.formatInput(it)
                                    loginError = ""
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
                                value = loginPassword,
                                onValueChange = {
                                    loginPassword = it
                                    loginError = ""
                                },
                                label = { Text("Password") },
                                placeholder = { Text("Your account password") },
                                leadingIcon = {
                                    Icon(Icons.Default.Lock, contentDescription = null, tint = RoyalBlue)
                                },
                                visualTransformation = if (loginPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                                trailingIcon = {
                                    IconButton(onClick = { loginPasswordVisible = !loginPasswordVisible }) {
                                        Icon(
                                            if (loginPasswordVisible) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                                            contentDescription = null,
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

                            Button(
                                onClick = {
                                    val phoneError = EthiopianPhoneUtils.getValidationError(loginPhone)
                                    if (phoneError != null && !viewModel.isAdminPhoneNumber(loginPhone)) {
                                        loginError = phoneError
                                        return@Button
                                    }
                                    if (loginPassword.isBlank()) {
                                        loginError = "Please enter your account password."
                                        return@Button
                                    }

                                    val result = viewModel.login(loginPhone, loginPassword)
                                    when (result) {
                                        is LoginResult.Success -> {
                                            if (result.isAdmin) {
                                                Toast.makeText(context, "Welcome Administrator!", Toast.LENGTH_SHORT).show()
                                            } else {
                                                Toast.makeText(context, "Logged in successfully!", Toast.LENGTH_SHORT).show()
                                            }
                                        }
                                        is LoginResult.Error -> {
                                            loginError = result.message
                                        }
                                    }
                                },
                                colors = ButtonDefaults.buttonColors(containerColor = RoyalBlue),
                                shape = RoundedCornerShape(14.dp),
                                elevation = ButtonDefaults.buttonElevation(defaultElevation = 3.dp),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(50.dp)
                            ) {
                                Icon(Icons.Default.Login, contentDescription = null, modifier = Modifier.size(18.dp))
                                Spacer(modifier = Modifier.width(8.dp))
                                Text("Log In", fontWeight = FontWeight.Bold, fontSize = 15.sp)
                            }

                            HorizontalDivider(color = Slate700.copy(alpha = 0.12f))

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text("Don't have an account?", fontSize = 12.5.sp, color = Slate700)
                                Spacer(modifier = Modifier.width(5.dp))
                                Text(
                                    text = "Create Account →",
                                    fontSize = 12.5.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = RoyalBlue,
                                    modifier = Modifier.clickable {
                                        mode = AuthMode.REGISTER
                                        regStep = 1
                                    }
                                )
                            }
                        }
                    }
                } else {
                    // ═════════════════════════════════════════
                    // ══ REGISTRATION WIZARD (2 STEPS) ══
                    // ═════════════════════════════════════════
                    Card(
                        shape = RoundedCornerShape(24.dp),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                        border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFE2E8F0)),
                        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(
                            modifier = Modifier.padding(22.dp),
                            verticalArrangement = Arrangement.spacedBy(14.dp)
                        ) {
                            // Step progress
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    if (regStep == 2) {
                                        IconButton(
                                            onClick = { regStep = 1; regError = "" },
                                            modifier = Modifier.size(32.dp)
                                        ) {
                                            Icon(
                                                Icons.AutoMirrored.Filled.ArrowBack,
                                                contentDescription = "Back",
                                                tint = RoyalBlue
                                            )
                                        }
                                        Spacer(modifier = Modifier.width(6.dp))
                                    }
                                    Text(
                                        text = if (regStep == 1) "Create Account" else "Membership Verification",
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 17.sp,
                                        color = Slate900
                                    )
                                }
                                Box(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(50))
                                        .background(RoyalBlue.copy(alpha = 0.1f))
                                        .padding(horizontal = 10.dp, vertical = 4.dp)
                                ) {
                                    Text(
                                        text = if (regStep == 1) "Step 1 of 2" else "Step 2 of 2",
                                        fontSize = 11.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = RoyalBlue
                                    )
                                }
                            }

                            // Step Progress Bar
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(6.dp)
                            ) {
                                Box(
                                    modifier = Modifier
                                        .weight(1f)
                                        .height(4.dp)
                                        .background(RoyalBlue, RoundedCornerShape(2.dp))
                                )
                                Box(
                                    modifier = Modifier
                                        .weight(1f)
                                        .height(4.dp)
                                        .background(
                                            if (regStep == 2) RoyalBlue else Slate700.copy(alpha = 0.18f),
                                            RoundedCornerShape(2.dp)
                                        )
                                )
                            }

                            if (regError.isNotBlank()) {
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
                                            text = regError,
                                            color = RoseRed,
                                            fontSize = 12.5.sp,
                                            fontWeight = FontWeight.Medium
                                        )
                                    }
                                }
                            }

                            if (regStep == 1) {
                                // ─── STEP 1: Personal Info ───
                                OutlinedTextField(
                                    value = regName,
                                    onValueChange = { regName = it; regError = "" },
                                    label = { Text("Full Name *") },
                                    placeholder = { Text("e.g. Dawit Kebede") },
                                    leadingIcon = { Icon(Icons.Default.Person, contentDescription = null, tint = RoyalBlue) },
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
                                    value = regUniversity,
                                    onValueChange = { regUniversity = it },
                                    label = { Text("University Name") },
                                    leadingIcon = { Icon(Icons.Default.School, contentDescription = null, tint = RoyalBlue) },
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
                                    value = regAcademicYear,
                                    onValueChange = { regAcademicYear = it },
                                    label = { Text("Academic Year") },
                                    leadingIcon = { Icon(Icons.Default.CalendarMonth, contentDescription = null, tint = RoyalBlue) },
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
                                    value = regPhone,
                                    onValueChange = {
                                        regPhone = EthiopianPhoneUtils.formatInput(it)
                                        regError = ""
                                    },
                                    label = { Text("Phone Number *") },
                                    placeholder = { Text("09... or 07...") },
                                    supportingText = {
                                        Text("Ethiopian format: starts with 09 or 07 (10 digits)", fontSize = 11.sp, color = Slate700)
                                    },
                                    leadingIcon = { Icon(Icons.Default.Phone, contentDescription = null, tint = RoyalBlue) },
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
                                    value = regPassword,
                                    onValueChange = { regPassword = it; regError = "" },
                                    label = { Text("Password *") },
                                    placeholder = { Text("Min. 6 characters") },
                                    leadingIcon = { Icon(Icons.Default.Lock, contentDescription = null, tint = RoyalBlue) },
                                    visualTransformation = if (regPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                                    trailingIcon = {
                                        IconButton(onClick = { regPasswordVisible = !regPasswordVisible }) {
                                            Icon(
                                                if (regPasswordVisible) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                                                contentDescription = null,
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

                                OutlinedTextField(
                                    value = regConfirmPassword,
                                    onValueChange = { regConfirmPassword = it; regError = "" },
                                    label = { Text("Confirm Password *") },
                                    placeholder = { Text("Re-enter password") },
                                    leadingIcon = { Icon(Icons.Default.LockOpen, contentDescription = null, tint = RoyalBlue) },
                                    visualTransformation = if (regPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                                    shape = RoundedCornerShape(14.dp),
                                    colors = OutlinedTextFieldDefaults.colors(
                                        focusedContainerColor = Color(0xFFF8FAFC),
                                        unfocusedContainerColor = Color(0xFFF8FAFC),
                                        focusedBorderColor = RoyalBlue,
                                        unfocusedBorderColor = Color(0xFFCBD5E1)
                                    ),
                                    modifier = Modifier.fillMaxWidth(),
                                    singleLine = true,
                                    isError = regConfirmPassword.isNotBlank() && regConfirmPassword != regPassword
                                )

                                Button(
                                    onClick = {
                                        val regPhoneError = EthiopianPhoneUtils.getValidationError(regPhone)
                                        when {
                                            regName.isBlank() -> regError = "Please enter your full name."
                                            regPhoneError != null -> regError = regPhoneError
                                            regPassword.length < 6 -> regError = "Password must be at least 6 characters."
                                            regPassword != regConfirmPassword -> regError = "Passwords do not match."
                                            else -> {
                                                regError = ""
                                                regCheckingPhone = true
                                                scope.launch {
                                                    try {
                                                        val alreadyRegistered = viewModel.isPhoneAlreadyRegistered(regPhone)
                                                        regCheckingPhone = false
                                                        if (alreadyRegistered) {
                                                            regError = "This phone number ($regPhone) is already registered. Please log in instead."
                                                        } else {
                                                            regStep = 2
                                                        }
                                                    } catch (e: Exception) {
                                                        regCheckingPhone = false
                                                        regStep = 2
                                                    }
                                                }
                                            }
                                        }
                                    },
                                    colors = ButtonDefaults.buttonColors(containerColor = RoyalBlue),
                                    shape = RoundedCornerShape(14.dp),
                                    elevation = ButtonDefaults.buttonElevation(defaultElevation = 3.dp),
                                    enabled = !regCheckingPhone,
                                    modifier = Modifier.fillMaxWidth().height(50.dp)
                                ) {
                                    if (regCheckingPhone) {
                                        CircularProgressIndicator(
                                            color = Color.White,
                                            modifier = Modifier.size(20.dp),
                                            strokeWidth = 2.dp
                                        )
                                        Spacer(modifier = Modifier.width(8.dp))
                                        Text("Verifying phone number...", fontWeight = FontWeight.Bold, fontSize = 14.sp)
                                    } else {
                                        Text("Next Step", fontWeight = FontWeight.Bold, fontSize = 15.sp)
                                        Spacer(modifier = Modifier.width(8.dp))
                                        Icon(Icons.AutoMirrored.Filled.ArrowForward, contentDescription = null, modifier = Modifier.size(18.dp))
                                    }
                                }
                            } else {
                                // ─── STEP 2: Send Screenshot ───
                                Text(
                                    text = "Please complete your membership payment, then send your screenshot below for verification.",
                                    fontSize = 12.sp,
                                    color = Slate700,
                                    lineHeight = 17.sp
                                )

                                // Payment info card
                                Card(
                                    shape = RoundedCornerShape(18.dp),
                                    colors = CardDefaults.cardColors(containerColor = Color(0xFFF0F7FF)),
                                    border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFBFDBFE)),
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Column(
                                        modifier = Modifier.padding(14.dp),
                                        verticalArrangement = Arrangement.spacedBy(10.dp)
                                    ) {
                                        Row(verticalAlignment = Alignment.CenterVertically) {
                                            Box(
                                                modifier = Modifier.size(38.dp).background(Color(0xFF0073E6), RoundedCornerShape(10.dp)),
                                                contentAlignment = Alignment.Center
                                            ) {
                                                Text("T", color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.ExtraBold)
                                            }
                                            Spacer(modifier = Modifier.width(10.dp))
                                            Column {
                                                Text("Telebirr: 0955903175", fontWeight = FontWeight.Bold, fontSize = 13.5.sp, color = Slate900)
                                                Text("Adnan", fontSize = 11.5.sp, color = Slate700)
                                            }
                                        }
                                        HorizontalDivider(color = Color(0xFFDBEAFE))
                                        Row(verticalAlignment = Alignment.CenterVertically) {
                                            Box(
                                                modifier = Modifier.size(38.dp).background(Color(0xFF800020), RoundedCornerShape(10.dp)),
                                                contentAlignment = Alignment.Center
                                            ) {
                                                Text("C", color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.ExtraBold)
                                            }
                                            Spacer(modifier = Modifier.width(10.dp))
                                            Column {
                                                Text("CBE Bank: 1000650901731", fontWeight = FontWeight.Bold, fontSize = 13.5.sp, color = Slate900)
                                                Text("Adnan", fontSize = 11.5.sp, color = Slate700)
                                            }
                                        }
                                        HorizontalDivider(color = Color(0xFFDBEAFE))
                                        Row(verticalAlignment = Alignment.CenterVertically) {
                                            Box(
                                                modifier = Modifier.size(38.dp).background(Color(0xFFFF6600), RoundedCornerShape(10.dp)),
                                                contentAlignment = Alignment.Center
                                            ) {
                                                Text("E", color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.ExtraBold)
                                            }
                                            Spacer(modifier = Modifier.width(10.dp))
                                            Column {
                                                Text("E-Birr: 0955903175", fontWeight = FontWeight.Bold, fontSize = 13.5.sp, color = Slate900)
                                                Text("Adnan", fontSize = 11.5.sp, color = Slate700)
                                            }
                                        }
                                    }
                                }

                                // ─── Screenshot Upload Section (Rectangular Zone) ───
                                Text(
                                    text = "Send your screenshot below:",
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    color = Slate900
                                )

                                ScreenshotUploadZone(
                                    screenshotUri = regScreenshotUri,
                                    screenshotUrl = regScreenshotUrl,
                                    isUploading = regUploading,
                                    phoneNumber = regPhone,
                                    onUploadStarted = {
                                        regUploading = true
                                        regError = ""
                                    },
                                    onUploadSuccess = { url, uri ->
                                        regScreenshotUrl = url
                                        regScreenshotUri = uri
                                        regUploading = false
                                        Toast.makeText(context, "Screenshot uploaded successfully!", Toast.LENGTH_SHORT).show()
                                    },
                                    onUploadError = { err ->
                                        regUploading = false
                                        regError = err
                                    }
                                )

                                // Support row
                                Surface(
                                    shape = RoundedCornerShape(12.dp),
                                    color = Color(0xFFF1F5F9),
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Row(
                                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 9.dp),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Text("Need help? ", fontSize = 11.5.sp, color = Slate700)
                                        Text(
                                            "Telegram @HUfreshman1",
                                            fontSize = 11.5.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = RoyalBlue,
                                            modifier = Modifier.clickable {
                                                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://t.me/HUfreshman1"))
                                                context.startActivity(intent)
                                            }
                                        )
                                        Text("  •  ", fontSize = 11.5.sp, color = Slate700)
                                        Text(
                                            "0955903175",
                                            fontSize = 11.5.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = RoyalBlue,
                                            modifier = Modifier.clickable {
                                                val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:0955903175"))
                                                context.startActivity(intent)
                                            }
                                        )
                                    }
                                }

                                Button(
                                    onClick = {
                                        when {
                                            regUploading -> regError = "Please wait for the screenshot to finish uploading."
                                            regScreenshotUrl.isEmpty() -> regError = "Please choose and upload your screenshot first."
                                            else -> {
                                                viewModel.registerMember(
                                                    regName,
                                                    regUniversity,
                                                    regAcademicYear,
                                                    regPhone,
                                                    regPassword,
                                                    regSelectedPaymentMethod,
                                                    regTransactionId,
                                                    regScreenshotUrl
                                                )
                                                Toast.makeText(context, "Registration submitted for verification!", Toast.LENGTH_LONG).show()
                                            }
                                        }
                                    },
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = if (regScreenshotUrl.isNotEmpty()) EmeraldGreen else RoyalBlue
                                    ),
                                    shape = RoundedCornerShape(14.dp),
                                    elevation = ButtonDefaults.buttonElevation(defaultElevation = 3.dp),
                                    modifier = Modifier.fillMaxWidth().height(50.dp),
                                    enabled = !regUploading
                                ) {
                                    Icon(Icons.Default.CheckCircle, contentDescription = null, modifier = Modifier.size(18.dp))
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text("Create Account", fontWeight = FontWeight.Bold, fontSize = 15.sp)
                                }
                            }

                            HorizontalDivider(color = Slate700.copy(alpha = 0.12f))

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text("Already have an account?", fontSize = 12.5.sp, color = Slate700)
                                Spacer(modifier = Modifier.width(5.dp))
                                Text(
                                    text = "Log In →",
                                    fontSize = 12.5.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = RoyalBlue,
                                    modifier = Modifier.clickable {
                                        mode = AuthMode.LOGIN
                                    }
                                )
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(30.dp))
        }
    }
}
