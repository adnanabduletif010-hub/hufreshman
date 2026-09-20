package com.curiovana.hufreshman.ui.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import coil.compose.AsyncImage
import com.curiovana.hufreshman.data.UniversityDetails
import com.curiovana.hufreshman.data.UniversityGuide
import com.curiovana.hufreshman.ui.theme.*
import com.curiovana.hufreshman.viewmodel.MainViewModel

@Composable
fun UniversitiesScreen(viewModel: MainViewModel) {
    val universities by viewModel.universities.collectAsState()
    val userProfile by viewModel.userProfile.collectAsState()
    var searchQuery by remember { mutableStateOf("") }
    var selectedUniversity by remember { mutableStateOf<UniversityGuide?>(null) }
    var editingUniversity by remember { mutableStateOf<UniversityGuide?>(null) }
    val context = LocalContext.current

    val filteredList = remember(universities, searchQuery) {
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
            .background(MaterialTheme.colorScheme.background)
    ) {
        // Search & Header
        Surface(
            color = MaterialTheme.colorScheme.surface,
            shadowElevation = 2.dp
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "Ethiopian Universities Directory",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Text(
                            text = "Campus life guide, dorms, cafes, and freshman cut-offs",
                            fontSize = 12.sp,
                            color = Slate700
                        )
                    }
                    if (userProfile.isAdmin) {
                        Box(
                            modifier = Modifier
                                .background(RoyalBlue, RoundedCornerShape(6.dp))
                                .padding(horizontal = 8.dp, vertical = 4.dp)
                        ) {
                            Text("Admin Mode", color = Color.White, fontSize = 11.sp, fontWeight = FontWeight.Bold)
                        }
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    placeholder = { Text("Search university or location...", fontSize = 13.sp) },
                    leadingIcon = { Icon(Icons.Default.Search, contentDescription = null, tint = RoyalBlue) },
                    trailingIcon = {
                        if (searchQuery.isNotEmpty()) {
                            IconButton(onClick = { searchQuery = "" }) {
                                Icon(Icons.Default.Close, contentDescription = "Clear")
                            }
                        }
                    },
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )
            }
        }

        // List
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            items(filteredList, key = { it.id }) { univ ->
                UniversityCard(
                    university = univ,
                    isFlagship = univ.id == "univ_hu",
                    isAdmin = userProfile.isAdmin,
                    onClick = { selectedUniversity = univ },
                    onEdit = { editingUniversity = univ }
                )
            }
        }
    }

    // Detail Dialog
    if (selectedUniversity != null) {
        val u = selectedUniversity!!
        Dialog(onDismissRequest = { selectedUniversity = null }) {
            Surface(
                shape = RoundedCornerShape(20.dp),
                color = MaterialTheme.colorScheme.surface,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.92f)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(20.dp)
                ) {
                    // Header
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            if (u.id == "univ_hu") {
                                Box(
                                    modifier = Modifier
                                        .background(RoyalBlue, RoundedCornerShape(6.dp))
                                        .padding(horizontal = 8.dp, vertical = 2.dp)
                                ) {
                                    Text("HU Flagship Campus", color = Color.White, fontSize = 10.sp, fontWeight = FontWeight.Bold)
                                }
                                Spacer(modifier = Modifier.height(4.dp))
                            }
                            Text(
                                text = u.name,
                                fontWeight = FontWeight.Bold,
                                fontSize = 18.sp,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            if (!u.amharicName.isNullOrBlank()) {
                                Text(
                                    text = u.amharicName,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Medium,
                                    color = RoyalBlue
                                )
                            }
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(Icons.Default.LocationOn, contentDescription = null, tint = Slate700, modifier = Modifier.size(14.dp))
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(u.location, fontSize = 12.sp, color = Slate700)
                            }
                        }
                        IconButton(onClick = { selectedUniversity = null }) {
                            Icon(Icons.Default.Close, contentDescription = "Close")
                        }
                    }

                    HorizontalDivider(modifier = Modifier.padding(vertical = 12.dp))

                    // Scrollable details content
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .verticalScroll(rememberScrollState()),
                        verticalArrangement = Arrangement.spacedBy(14.dp)
                    ) {
                        // Campus image
                        if (!u.image.isNullOrBlank()) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(150.dp)
                                    .clip(RoundedCornerShape(12.dp))
                            ) {
                                AsyncImage(
                                    model = u.image,
                                    contentDescription = u.name,
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier.fillMaxSize()
                                )
                            }
                        }

                        Text(
                            text = u.description,
                            fontSize = 13.sp,
                            lineHeight = 19.sp,
                            color = Slate800
                        )

                        val d = u.details
                        if (d != null) {
                            if (!d.campusesAndFields.isNullOrBlank()) {
                                GuideSectionCard(
                                    icon = Icons.Default.AccountBalance,
                                    title = "Campuses & Academic Fields",
                                    content = d.campusesAndFields,
                                    color = RoyalBlue
                                )
                            }
                            if (!d.locationTransport.isNullOrBlank()) {
                                GuideSectionCard(
                                    icon = Icons.Default.DirectionsBus,
                                    title = "Location & Transport",
                                    content = d.locationTransport,
                                    color = ElectricIndigo
                                )
                            }
                            if (!d.weather.isNullOrBlank()) {
                                GuideSectionCard(
                                    icon = Icons.Default.WbSunny,
                                    title = "Campus Climate & Weather",
                                    content = d.weather,
                                    color = AmberWarning
                                )
                            }
                            if (!d.cafeFood.isNullOrBlank() || !d.outsideFood.isNullOrBlank()) {
                                val foodInfo = buildString {
                                    if (!d.cafeFood.isNullOrBlank()) append("🍽️ Student Cafe:\n${d.cafeFood}\n\n")
                                    if (!d.outsideFood.isNullOrBlank()) append("☕ Outside Food & Prices:\n${d.outsideFood}")
                                }
                                GuideSectionCard(
                                    icon = Icons.Default.Restaurant,
                                    title = "Food, Cafe & Living Costs",
                                    content = foodInfo,
                                    color = EmeraldGreen
                                )
                            }
                            if (!d.dormAndLockers.isNullOrBlank()) {
                                GuideSectionCard(
                                    icon = Icons.Default.Bed,
                                    title = "Dorms & Lockers",
                                    content = d.dormAndLockers,
                                    color = Slate800
                                )
                            }
                            if (!d.utilities.isNullOrBlank()) {
                                GuideSectionCard(
                                    icon = Icons.Default.Wifi,
                                    title = "Utilities (Water, Power & Wi-Fi)",
                                    content = d.utilities,
                                    color = RoyalBlueLight
                                )
                            }
                            if (!d.safetyAdvice.isNullOrBlank()) {
                                GuideSectionCard(
                                    icon = Icons.Default.Shield,
                                    title = "Safety & Freshman Advice",
                                    content = d.safetyAdvice,
                                    color = RoseRed
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    // Admin Edit Button in detail view
                    if (userProfile.isAdmin) {
                        Button(
                            onClick = {
                                editingUniversity = u
                                selectedUniversity = null
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = AmberWarning),
                            shape = RoundedCornerShape(10.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Icon(Icons.Default.Edit, contentDescription = null, modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(6.dp))
                            Text("Edit This University Info", fontWeight = FontWeight.Bold, color = Color.White)
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                    }

                    // Action buttons
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        if (!u.telegram.isNullOrBlank()) {
                            Button(
                                onClick = {
                                    try {
                                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(u.telegram))
                                        context.startActivity(intent)
                                    } catch (e: Exception) {
                                        e.printStackTrace()
                                    }
                                },
                                modifier = Modifier.weight(1f),
                                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF229ED9)),
                                shape = RoundedCornerShape(10.dp)
                            ) {
                                Icon(Icons.AutoMirrored.Filled.Send, contentDescription = null, modifier = Modifier.size(16.dp))
                                Spacer(modifier = Modifier.width(6.dp))
                                Text("Telegram", fontSize = 12.sp)
                            }
                        }

                        if (!u.website.isNullOrBlank()) {
                            OutlinedButton(
                                onClick = {
                                    try {
                                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(u.website))
                                        context.startActivity(intent)
                                    } catch (e: Exception) {
                                        e.printStackTrace()
                                    }
                                },
                                modifier = Modifier.weight(1f),
                                shape = RoundedCornerShape(10.dp)
                            ) {
                                Icon(Icons.Default.Language, contentDescription = null, modifier = Modifier.size(16.dp))
                                Spacer(modifier = Modifier.width(6.dp))
                                Text("Website", fontSize = 12.sp)
                            }
                        }
                    }
                }
            }
        }
    }

    // Admin Edit University Dialog
    if (editingUniversity != null) {
        EditUniversityDialog(
            university = editingUniversity!!,
            onDismiss = { editingUniversity = null },
            onSave = { updated ->
                viewModel.updateUniversity(updated)
                editingUniversity = null
            }
        )
    }
}

@Composable
fun UniversityCard(
    university: UniversityGuide,
    isFlagship: Boolean,
    isAdmin: Boolean,
    onClick: () -> Unit,
    onEdit: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (isFlagship) Color(0xFFEFF6FF) else MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = if (isFlagship) 4.dp else 2.dp),
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .then(
                if (isFlagship) Modifier.border(1.5.dp, RoyalBlue, RoundedCornerShape(18.dp))
                else Modifier
            )
    ) {
        Column {
            // Campus Image Header
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(135.dp)
                    .background(RoyalBlueDark)
            ) {
                if (!university.image.isNullOrBlank()) {
                    AsyncImage(
                        model = university.image,
                        contentDescription = university.name,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                }

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.7f)),
                                startY = 40f
                            )
                        )
                )

                if (isFlagship) {
                    Box(
                        modifier = Modifier
                            .align(Alignment.TopStart)
                            .padding(10.dp)
                            .background(RoyalBlue, RoundedCornerShape(6.dp))
                            .padding(horizontal = 8.dp, vertical = 3.dp)
                    ) {
                        Text("★ HU Flagship Campus", color = Color.White, fontSize = 10.sp, fontWeight = FontWeight.Bold)
                    }
                }

                if (isAdmin) {
                    IconButton(
                        onClick = onEdit,
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(6.dp)
                            .background(Color.Black.copy(alpha = 0.5f), CircleShape)
                            .size(32.dp)
                    ) {
                        Icon(Icons.Default.Edit, contentDescription = "Edit University", tint = Color.White, modifier = Modifier.size(16.dp))
                    }
                }

                Row(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(Icons.Default.LocationOn, contentDescription = null, tint = Color.White, modifier = Modifier.size(14.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = university.location,
                        color = Color.White,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }

            Column(modifier = Modifier.padding(16.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = university.name,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        if (!university.amharicName.isNullOrBlank()) {
                            Text(
                                text = university.amharicName,
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Medium,
                                color = RoyalBlue
                            )
                        }
                    }

                    Box(
                        modifier = Modifier
                            .background(RoyalBlue.copy(alpha = 0.1f), RoundedCornerShape(12.dp))
                            .padding(10.dp)
                    ) {
                        Icon(Icons.Default.School, contentDescription = null, tint = RoyalBlue)
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.LocationOn, contentDescription = null, tint = Slate700, modifier = Modifier.size(14.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = university.location,
                        fontSize = 12.sp,
                        color = Slate700
                    )
                }

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = university.description,
                    fontSize = 13.sp,
                    lineHeight = 18.sp,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    color = Slate800
                )

                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (isAdmin) {
                        TextButton(
                            onClick = onEdit,
                            contentPadding = PaddingValues(0.dp)
                        ) {
                            Icon(Icons.Default.Edit, contentDescription = null, modifier = Modifier.size(14.dp), tint = AmberWarning)
                            Spacer(modifier = Modifier.width(4.dp))
                            Text("Edit Details", color = AmberWarning, fontWeight = FontWeight.Bold, fontSize = 12.sp)
                        }
                    } else {
                        Spacer(modifier = Modifier.weight(1f))
                    }

                    Text(
                        text = "View Full Guide & Life →",
                        color = RoyalBlue,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

@Composable
fun EditUniversityDialog(
    university: UniversityGuide,
    onDismiss: () -> Unit,
    onSave: (UniversityGuide) -> Unit
) {
    var name by remember { mutableStateOf(university.name) }
    var amharicName by remember { mutableStateOf(university.amharicName ?: "") }
    var location by remember { mutableStateOf(university.location) }
    var website by remember { mutableStateOf(university.website ?: "") }
    var telegram by remember { mutableStateOf(university.telegram ?: "") }
    var imageUrl by remember { mutableStateOf(university.image ?: "") }
    var description by remember { mutableStateOf(university.description) }

    val details = university.details ?: UniversityDetails()
    var campuses by remember { mutableStateOf(details.campusesAndFields ?: "") }
    var transport by remember { mutableStateOf(details.locationTransport ?: "") }
    var weather by remember { mutableStateOf(details.weather ?: "") }
    var cafeFood by remember { mutableStateOf(details.cafeFood ?: "") }
    var outsideFood by remember { mutableStateOf(details.outsideFood ?: "") }
    var dorms by remember { mutableStateOf(details.dormAndLockers ?: "") }
    var utilities by remember { mutableStateOf(details.utilities ?: "") }
    var safetyAdvice by remember { mutableStateOf(details.safetyAdvice ?: "") }

    Dialog(onDismissRequest = onDismiss) {
        Surface(
            shape = RoundedCornerShape(20.dp),
            color = MaterialTheme.colorScheme.surface,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.92f)
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
                        Icon(Icons.Default.EditNote, contentDescription = null, tint = RoyalBlue, modifier = Modifier.size(24.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Edit University Info", fontWeight = FontWeight.Bold, fontSize = 16.sp)
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
                    OutlinedTextField(value = name, onValueChange = { name = it }, label = { Text("University Name (English)") }, modifier = Modifier.fillMaxWidth())
                    OutlinedTextField(value = amharicName, onValueChange = { amharicName = it }, label = { Text("University Name (Amharic)") }, modifier = Modifier.fillMaxWidth())
                    OutlinedTextField(value = location, onValueChange = { location = it }, label = { Text("Location") }, modifier = Modifier.fillMaxWidth())
                    OutlinedTextField(value = imageUrl, onValueChange = { imageUrl = it }, label = { Text("Campus Cover Image URL") }, modifier = Modifier.fillMaxWidth())
                    OutlinedTextField(value = description, onValueChange = { description = it }, label = { Text("Overview Description") }, modifier = Modifier.fillMaxWidth(), minLines = 2)

                    Text("Official Links", fontWeight = FontWeight.Bold, fontSize = 12.sp, color = RoyalBlue)
                    OutlinedTextField(value = website, onValueChange = { website = it }, label = { Text("Website URL") }, modifier = Modifier.fillMaxWidth())
                    OutlinedTextField(value = telegram, onValueChange = { telegram = it }, label = { Text("Telegram Channel Link") }, modifier = Modifier.fillMaxWidth())

                    Text("Detailed Campus Life Guides", fontWeight = FontWeight.Bold, fontSize = 12.sp, color = RoyalBlue)
                    OutlinedTextField(value = campuses, onValueChange = { campuses = it }, label = { Text("Campuses & Academic Fields") }, modifier = Modifier.fillMaxWidth(), minLines = 2)
                    OutlinedTextField(value = transport, onValueChange = { transport = it }, label = { Text("Location & Transport Instructions") }, modifier = Modifier.fillMaxWidth(), minLines = 2)
                    OutlinedTextField(value = weather, onValueChange = { weather = it }, label = { Text("Campus Weather & Climate") }, modifier = Modifier.fillMaxWidth())
                    OutlinedTextField(value = cafeFood, onValueChange = { cafeFood = it }, label = { Text("Student Cafe Meals") }, modifier = Modifier.fillMaxWidth(), minLines = 2)
                    OutlinedTextField(value = outsideFood, onValueChange = { outsideFood = it }, label = { Text("Outside Food & Cafeteria Prices") }, modifier = Modifier.fillMaxWidth(), minLines = 2)
                    OutlinedTextField(value = dorms, onValueChange = { dorms = it }, label = { Text("Dormitories & Lockers") }, modifier = Modifier.fillMaxWidth())
                    OutlinedTextField(value = utilities, onValueChange = { utilities = it }, label = { Text("Utilities (Water, Power, Wi-Fi)") }, modifier = Modifier.fillMaxWidth())
                    OutlinedTextField(value = safetyAdvice, onValueChange = { safetyAdvice = it }, label = { Text("Safety & Freshmen Advice") }, modifier = Modifier.fillMaxWidth(), minLines = 2)
                }

                Spacer(modifier = Modifier.height(10.dp))

                Button(
                    onClick = {
                        val updated = university.copy(
                            name = name,
                            amharicName = amharicName.ifBlank { null },
                            location = location,
                            website = website.ifBlank { null },
                            telegram = telegram.ifBlank { null },
                            image = imageUrl.ifBlank { null },
                            description = description,
                            details = UniversityDetails(
                                campusesAndFields = campuses.ifBlank { null },
                                locationTransport = transport.ifBlank { null },
                                weather = weather.ifBlank { null },
                                cafeFood = cafeFood.ifBlank { null },
                                outsideFood = outsideFood.ifBlank { null },
                                dormAndLockers = dorms.ifBlank { null },
                                utilities = utilities.ifBlank { null },
                                sanitation = details.sanitation,
                                safetyAdvice = safetyAdvice.ifBlank { null }
                            )
                        )
                        onSave(updated)
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = EmeraldGreen),
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                ) {
                    Icon(Icons.Default.Save, contentDescription = null)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Save University Changes", fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

@Composable
fun GuideSectionCard(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    title: String,
    content: String,
    color: Color
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(color.copy(alpha = 0.07f), RoundedCornerShape(12.dp))
            .border(1.dp, color.copy(alpha = 0.2f), RoundedCornerShape(12.dp))
            .padding(14.dp)
    ) {
        Column {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(icon, contentDescription = null, tint = color, modifier = Modifier.size(18.dp))
                Spacer(modifier = Modifier.width(6.dp))
                Text(title, fontWeight = FontWeight.Bold, fontSize = 13.sp, color = color)
            }
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = content,
                fontSize = 12.sp,
                lineHeight = 18.sp,
                color = Slate900
            )
        }
    }
}
