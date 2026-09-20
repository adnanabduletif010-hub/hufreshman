package com.curiovana.hufreshman.ui.screens

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.curiovana.hufreshman.data.AppRepository
import com.curiovana.hufreshman.data.LoginResult
import com.curiovana.hufreshman.data.UserProfile
import com.curiovana.hufreshman.ui.theme.*
import com.curiovana.hufreshman.viewmodel.MainViewModel

enum class AuthMode {
    LOGIN,
    REGISTER
}

@Composable
fun AuthScreen(
    userProfile: UserProfile,
    viewModel: MainViewModel
) {
    val context = LocalContext.current
    var mode by remember { mutableStateOf(AuthMode.LOGIN) }

    // Login state
    var loginPhone by remember { mutableStateOf("") }
    var loginPassword by remember { mutableStateOf("") }
    var loginPasswordVisible by remember { mutableStateOf(false) }
    var loginError by remember { mutableStateOf("") }

    // Register state
    var regStep by remember { mutableIntStateOf(1) }
    var regName by remember { mutableStateOf("") }
    var regUniversity by remember { mutableStateOf("Haramaya University") }
    var regAcademicYear by remember { mutableStateOf("2026/2027 Freshman") }
    var regPhone by remember { mutableStateOf("") }
    var regPassword by remember { mutableStateOf("") }
    var regConfirmPassword by remember { mutableStateOf("") }
    var regPasswordVisible by remember { mutableStateOf(false) }
    var regSelectedPaymentMethod by remember { mutableStateOf("Telebirr") }
    var regTransactionId by remember { mutableStateOf("") }
    var regError by remember { mutableStateOf("") }

    val cleanPhone = loginPhone.replace(Regex("[^0-9]"), "")
    val isAdminPhone = AppRepository.ADMIN_PHONE_NUMBERS.any { adminNum ->
        val cleanAdmin = adminNum.replace(Regex("[^0-9]"), "")
        cleanPhone == cleanAdmin || (cleanPhone.length >= 9 && cleanPhone.endsWith(cleanAdmin.takeLast(9)))
    }

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
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp, vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(10.dp))

            // App Brand Header
            Box(
                modifier = Modifier
                    .size(68.dp)
                    .background(
                        Brush.linearGradient(listOf(RoyalBlue, RoyalBlueDark)),
                        shape = RoundedCornerShape(18.dp)
                    ),
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
                fontSize = 24.sp,
                color = Slate900
            )

            Text(
                text = "Ethiopian University Exam & Academic Hub",
                fontSize = 12.sp,
                color = Slate700,
                fontWeight = FontWeight.Medium
            )

            Spacer(modifier = Modifier.height(14.dp))

            // Access Gate Notice Banner
            Surface(
                shape = RoundedCornerShape(12.dp),
                color = AmberWarning.copy(alpha = 0.12f),
                border = androidx.compose.foundation.BorderStroke(1.dp, AmberWarning.copy(alpha = 0.35f)),
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        Icons.Default.Lock,
                        contentDescription = null,
                        tint = AmberWarning,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = "Member access is required. Please log in or register to use the app.",
                        fontSize = 11.sp,
                        lineHeight = 15.sp,
                        color = Slate800,
                        fontWeight = FontWeight.Medium
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Mode Selector (Log In / Create Account)
            Surface(
                shape = RoundedCornerShape(14.dp),
                color = Slate700.copy(alpha = 0.08f),
                modifier = Modifier.fillMaxWidth().height(46.dp)
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
                            .clip(RoundedCornerShape(10.dp))
                            .background(if (mode == AuthMode.LOGIN) RoyalBlue else Color.Transparent)
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
                            .clip(RoundedCornerShape(10.dp))
                            .background(if (mode == AuthMode.REGISTER) RoyalBlue else Color.Transparent)
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
                        shape = RoundedCornerShape(18.dp),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(
                            modifier = Modifier.padding(20.dp),
                            verticalArrangement = Arrangement.spacedBy(14.dp)
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    if (isAdminPhone) Icons.Default.AdminPanelSettings else Icons.Default.Login,
                                    contentDescription = null,
                                    tint = RoyalBlue,
                                    modifier = Modifier.size(22.dp)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = if (isAdminPhone) "Admin Login" else "Welcome Back",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 16.sp,
                                    color = Slate900
                                )
                            }

                            Text(
                                text = if (isAdminPhone) {
                                    "Administrator phone recognized. Enter your administrator passcode:"
                                } else {
                                    "Enter your registered phone number and password to log in:"
                                },
                                fontSize = 12.sp,
                                color = Slate700
                            )

                            if (loginError.isNotBlank()) {
                                Surface(
                                    color = RoseRed.copy(alpha = 0.1f),
                                    shape = RoundedCornerShape(8.dp),
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Text(
                                        text = loginError,
                                        color = RoseRed,
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.Medium,
                                        modifier = Modifier.padding(10.dp)
                                    )
                                }
                            }

                            OutlinedTextField(
                                value = loginPhone,
                                onValueChange = {
                                    loginPhone = it
                                    loginError = ""
                                },
                                label = { Text("Phone Number") },
                                placeholder = { Text("09... or 07...") },
                                leadingIcon = {
                                    Icon(Icons.Default.Phone, contentDescription = null, tint = RoyalBlue)
                                },
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                                modifier = Modifier.fillMaxWidth(),
                                singleLine = true
                            )

                            if (isAdminPhone) {
                                OutlinedTextField(
                                    value = loginPassword,
                                    onValueChange = {
                                        loginPassword = it
                                        loginError = ""
                                    },
                                    label = { Text("Admin Passcode") },
                                    placeholder = { Text("Enter passcode") },
                                    leadingIcon = {
                                        Icon(Icons.Default.VpnKey, contentDescription = null, tint = RoyalBlue)
                                    },
                                    visualTransformation = PasswordVisualTransformation(),
                                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
                                    modifier = Modifier.fillMaxWidth(),
                                    singleLine = true
                                )
                            } else {
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
                                                contentDescription = null
                                            )
                                        }
                                    },
                                    modifier = Modifier.fillMaxWidth(),
                                    singleLine = true
                                )
                            }

                            Button(
                                onClick = {
                                    if (loginPhone.isBlank()) {
                                        loginError = "Please enter your phone number."
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
                                shape = RoundedCornerShape(12.dp),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(48.dp)
                            ) {
                                Icon(Icons.Default.Login, contentDescription = null)
                                Spacer(modifier = Modifier.width(8.dp))
                                Text("Log In", fontWeight = FontWeight.Bold, fontSize = 14.sp)
                            }

                            HorizontalDivider(color = Slate700.copy(alpha = 0.12f))

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text("Don't have an account?", fontSize = 12.sp, color = Slate700)
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(
                                    text = "Register Now →",
                                    fontSize = 12.sp,
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
                        shape = RoundedCornerShape(18.dp),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(
                            modifier = Modifier.padding(20.dp),
                            verticalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            // Step progress
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    if (regStep == 2) {
                                        IconButton(onClick = { regStep = 1; regError = "" }) {
                                            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = RoyalBlue)
                                        }
                                    }
                                    Text(
                                        text = if (regStep == 1) "Create Account" else "Membership Verification",
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 16.sp
                                    )
                                }
                                Text(
                                    text = if (regStep == 1) "Step 1 of 2" else "Step 2 of 2",
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    color = RoyalBlue
                                )
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
                                    color = RoseRed.copy(alpha = 0.1f),
                                    shape = RoundedCornerShape(8.dp),
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Text(
                                        text = regError,
                                        color = RoseRed,
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.Medium,
                                        modifier = Modifier.padding(10.dp)
                                    )
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
                                    modifier = Modifier.fillMaxWidth(),
                                    singleLine = true
                                )

                                OutlinedTextField(
                                    value = regUniversity,
                                    onValueChange = { regUniversity = it },
                                    label = { Text("University Name") },
                                    leadingIcon = { Icon(Icons.Default.School, contentDescription = null, tint = RoyalBlue) },
                                    modifier = Modifier.fillMaxWidth(),
                                    singleLine = true
                                )

                                OutlinedTextField(
                                    value = regAcademicYear,
                                    onValueChange = { regAcademicYear = it },
                                    label = { Text("Academic Year") },
                                    leadingIcon = { Icon(Icons.Default.CalendarMonth, contentDescription = null, tint = RoyalBlue) },
                                    modifier = Modifier.fillMaxWidth(),
                                    singleLine = true
                                )

                                OutlinedTextField(
                                    value = regPhone,
                                    onValueChange = { regPhone = it; regError = "" },
                                    label = { Text("Phone Number *") },
                                    placeholder = { Text("09... or 07...") },
                                    leadingIcon = { Icon(Icons.Default.Phone, contentDescription = null, tint = RoyalBlue) },
                                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
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
                                                contentDescription = null
                                            )
                                        }
                                    },
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
                                    modifier = Modifier.fillMaxWidth(),
                                    singleLine = true,
                                    isError = regConfirmPassword.isNotBlank() && regConfirmPassword != regPassword
                                )

                                Button(
                                    onClick = {
                                        when {
                                            regName.isBlank() || regPhone.isBlank() -> regError = "Please enter your name and phone number."
                                            regPassword.length < 6 -> regError = "Password must be at least 6 characters."
                                            regPassword != regConfirmPassword -> regError = "Passwords do not match."
                                            else -> {
                                                regError = ""
                                                regStep = 2
                                            }
                                        }
                                    },
                                    colors = ButtonDefaults.buttonColors(containerColor = RoyalBlue),
                                    shape = RoundedCornerShape(12.dp),
                                    modifier = Modifier.fillMaxWidth().height(48.dp)
                                ) {
                                    Text("Next", fontWeight = FontWeight.Bold, fontSize = 14.sp)
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Icon(Icons.AutoMirrored.Filled.ArrowForward, contentDescription = null)
                                }
                            } else {
                                // ─── STEP 2: Membership Verification ───
                                Text(
                                    text = "To verify membership, please provide a transaction reference from any official account below. This reference is for membership enrollment verification.",
                                    fontSize = 12.sp,
                                    color = Slate700,
                                    lineHeight = 17.sp
                                )

                                Card(
                                    shape = RoundedCornerShape(14.dp),
                                    colors = CardDefaults.cardColors(containerColor = Color(0xFFEFF6FF)),
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Column(
                                        modifier = Modifier.padding(12.dp),
                                        verticalArrangement = Arrangement.spacedBy(10.dp)
                                    ) {
                                        // Telebirr
                                        Row(verticalAlignment = Alignment.CenterVertically) {
                                            Box(
                                                modifier = Modifier.size(36.dp).background(Color(0xFF0073E6), RoundedCornerShape(8.dp)),
                                                contentAlignment = Alignment.Center
                                            ) {
                                                Text("T", color = Color.White, fontSize = 15.sp, fontWeight = FontWeight.ExtraBold)
                                            }
                                            Spacer(modifier = Modifier.width(10.dp))
                                            Column {
                                                Text("Telebirr: 0955903175", fontWeight = FontWeight.Bold, fontSize = 13.sp, color = Slate900)
                                                Text("Adnan", fontSize = 11.sp, color = Slate700)
                                            }
                                        }
                                        HorizontalDivider(color = Color(0xFFDBEAFE))
                                        // CBE Bank
                                        Row(verticalAlignment = Alignment.CenterVertically) {
                                            Box(
                                                modifier = Modifier.size(36.dp).background(Color(0xFF800020), RoundedCornerShape(8.dp)),
                                                contentAlignment = Alignment.Center
                                            ) {
                                                Text("C", color = Color.White, fontSize = 15.sp, fontWeight = FontWeight.ExtraBold)
                                            }
                                            Spacer(modifier = Modifier.width(10.dp))
                                            Column {
                                                Text("CBE Bank: 1000650901731", fontWeight = FontWeight.Bold, fontSize = 13.sp, color = Slate900)
                                                Text("Adnan", fontSize = 11.sp, color = Slate700)
                                            }
                                        }
                                        HorizontalDivider(color = Color(0xFFDBEAFE))
                                        // E-Birr
                                        Row(verticalAlignment = Alignment.CenterVertically) {
                                            Box(
                                                modifier = Modifier.size(36.dp).background(Color(0xFFFF6600), RoundedCornerShape(8.dp)),
                                                contentAlignment = Alignment.Center
                                            ) {
                                                Text("E", color = Color.White, fontSize = 15.sp, fontWeight = FontWeight.ExtraBold)
                                            }
                                            Spacer(modifier = Modifier.width(10.dp))
                                            Column {
                                                Text("E-Birr: 0955903175", fontWeight = FontWeight.Bold, fontSize = 13.sp, color = Slate900)
                                                Text("Adnan", fontSize = 11.sp, color = Slate700)
                                            }
                                        }
                                    }
                                }

                                Text("Membership fee channel:", fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                                ) {
                                    listOf("Telebirr", "CBE Bank", "E-Birr").forEach { method ->
                                        FilterChip(
                                            selected = regSelectedPaymentMethod == method,
                                            onClick = { regSelectedPaymentMethod = method },
                                            label = { Text(method, fontSize = 11.sp) },
                                            colors = FilterChipDefaults.filterChipColors(
                                                selectedContainerColor = RoyalBlue,
                                                selectedLabelColor = Color.White
                                            )
                                        )
                                    }
                                }

                                OutlinedTextField(
                                    value = regTransactionId,
                                    onValueChange = { regTransactionId = it; regError = "" },
                                    label = { Text("Transaction / Reference ID *") },
                                    placeholder = { Text("e.g. FT240825ABCD or TXN123456") },
                                    leadingIcon = { Icon(Icons.Default.Receipt, contentDescription = null, tint = EmeraldGreen) },
                                    modifier = Modifier.fillMaxWidth(),
                                    singleLine = true
                                )

                                // Support Help Row
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text("Need help? ", fontSize = 11.sp, color = Slate700)
                                    Text(
                                        "Telegram @HUfreshman1",
                                        fontSize = 11.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = RoyalBlue,
                                        modifier = Modifier.clickable {
                                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://t.me/HUfreshman1"))
                                            context.startActivity(intent)
                                        }
                                    )
                                    Text("  •  ", fontSize = 11.sp, color = Slate700)
                                    Text(
                                        "0955903175",
                                        fontSize = 11.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = RoyalBlue,
                                        modifier = Modifier.clickable {
                                            val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:0955903175"))
                                            context.startActivity(intent)
                                        }
                                    )
                                }

                                Button(
                                    onClick = {
                                        if (regTransactionId.isBlank()) {
                                            regError = "Please enter the Transaction / Reference ID from your membership fee receipt."
                                        } else {
                                            viewModel.registerMember(
                                                regName,
                                                regUniversity,
                                                regAcademicYear,
                                                regPhone,
                                                regPassword,
                                                regSelectedPaymentMethod,
                                                regTransactionId
                                            )
                                            Toast.makeText(context, "Registration submitted for verification!", Toast.LENGTH_LONG).show()
                                        }
                                    },
                                    colors = ButtonDefaults.buttonColors(containerColor = EmeraldGreen),
                                    shape = RoundedCornerShape(12.dp),
                                    modifier = Modifier.fillMaxWidth().height(48.dp)
                                ) {
                                    Icon(Icons.Default.CheckCircle, contentDescription = null)
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text("Submit Registration", fontWeight = FontWeight.Bold, fontSize = 14.sp)
                                }
                            }

                            HorizontalDivider(color = Slate700.copy(alpha = 0.12f))

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text("Already have an account?", fontSize = 12.sp, color = Slate700)
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(
                                    text = "Log In →",
                                    fontSize = 12.sp,
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
