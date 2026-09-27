package com.curiovana.hufreshman.ui.screens

import android.content.Intent
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items as gridItems
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import coil.compose.AsyncImage
import com.curiovana.hufreshman.data.SubjectCategory
import com.curiovana.hufreshman.ui.theme.*
import com.curiovana.hufreshman.viewmodel.MainViewModel


@Composable
fun ShortNotesScreen(viewModel: MainViewModel) {
    var selectedSubject by remember { mutableStateOf<SubjectCategory?>(null) }
    var selectedUnit by remember { mutableStateOf<String?>(null) }
    var searchQuery by remember { mutableStateOf("") }

    // Intercept back button if drilled into a subject, unit, or actively searching
    BackHandler(enabled = selectedSubject != null || searchQuery.isNotBlank() || selectedUnit != null) {
        when {
            searchQuery.isNotBlank() -> searchQuery = ""
            selectedUnit != null -> selectedUnit = null
            else -> selectedSubject = null
        }
    }

    // Main Body: Level 0 (Subject Grid with Images) vs Level 1 (Subject Notes Detail)
    Box(modifier = Modifier.fillMaxSize()) {
        when {
            // Global search active
            searchQuery.isNotBlank() -> {
                val searchResults = sampleNotes.filter { note ->
                    note.title.contains(searchQuery, ignoreCase = true) ||
                    note.summary.contains(searchQuery, ignoreCase = true) ||
                    note.subject.contains(searchQuery, ignoreCase = true) ||
                    note.unit.contains(searchQuery, ignoreCase = true)
                }
                NotesListView(notes = searchResults)
            }

            // Level 0: Subject Grid with authentic images (identical to ExamBoardScreen structure)
            selectedSubject == null -> {
                SubjectNotesGridView(
                    subjects = viewModel.subjectCategories,
                    allNotes = sampleNotes,
                    onSelectSubject = { subject ->
                        selectedSubject = subject
                        selectedUnit = null
                    }
                )
            }

            // Level 1: Drilled into selected Subject
            else -> {
                SubjectNotesDetailView(
                    subject = selectedSubject!!,
                    selectedUnit = selectedUnit,
                    onSelectUnit = { selectedUnit = it },
                    allNotes = sampleNotes
                )
            }
        }
    }
}

// ------------------------------------------------------------
// LEVEL 0: Subject Grid with same images and layout as Exam Board
// ------------------------------------------------------------
@Composable
fun SubjectNotesGridView(
    subjects: List<SubjectCategory>,
    allNotes: List<ShortNote>,
    onSelectSubject: (SubjectCategory) -> Unit
) {
    val screen = rememberScreenDimensions()
    val columns = when {
        screen.isCompactWidth -> GridCells.Fixed(1)
        screen.widthClass == WindowWidthSizeClass.EXPANDED -> GridCells.Adaptive(minSize = 180.dp)
        else -> GridCells.Fixed(2)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = if (screen.isCompactWidth) 10.dp else 16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = "Freshman Short Notes",
                    fontSize = if (screen.isCompactWidth) 18.sp else 20.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = "Select a subject to read high-yield chapter notes",
                    fontSize = if (screen.isCompactWidth) 11.sp else 12.sp,
                    color = Slate700
                )
            }
            Box(
                modifier = Modifier
                    .background(RoyalBlue.copy(alpha = 0.1f), RoundedCornerShape(8.dp))
                    .padding(horizontal = 8.dp, vertical = 4.dp)
            ) {
                Text(
                    text = "${subjects.size} Courses",
                    color = RoyalBlue,
                    fontWeight = FontWeight.Bold,
                    fontSize = 11.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(4.dp))

        LazyVerticalGrid(
            columns = columns,
            horizontalArrangement = Arrangement.spacedBy(if (screen.isCompactWidth) 8.dp else 12.dp),
            verticalArrangement = Arrangement.spacedBy(if (screen.isCompactWidth) 10.dp else 14.dp),
            contentPadding = PaddingValues(bottom = 24.dp)
        ) {
            gridItems(subjects, key = { it.id }) { subject ->
                val notesCount = remember(allNotes, subject.id, subject.name, subject.shortName) {
                    allNotes.count {
                        it.subjectId == subject.id ||
                        it.subject.contains(subject.name, ignoreCase = true) ||
                        it.subject.contains(subject.shortName, ignoreCase = true)
                    }
                }
                SubjectNoteCard(
                    subject = subject,
                    notesCount = if (notesCount > 0) notesCount else 5,
                    onClick = { onSelectSubject(subject) }
                )
            }
        }
    }
}

// ------------------------------------------------------------
// Subject Card with same image and overlay as Exam SubjectCard
// ------------------------------------------------------------
@Composable
fun SubjectNoteCard(
    subject: SubjectCategory,
    notesCount: Int,
    onClick: () -> Unit
) {
    val screen = rememberScreenDimensions()
    val imageHeight = if (screen.isCompactWidth) 130.dp else 115.dp
    val isSecondSemester = subject.id in listOf("c8", "c9", "c10") ||
            subject.description.contains("Second Semester", ignoreCase = true)

    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp),
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
    ) {
        Column {
            // Subject Image with Overlay
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(imageHeight)
                    .background(Color(subject.colorHex))
            ) {
                AsyncImage(
                    model = subject.imageUrl,
                    contentDescription = subject.name,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )

                // Dark gradient overlay for contrast
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.68f)),
                                startY = 35f
                            )
                        )
                )

                // Top Badge: Units count or Semester 2
                Box(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(8.dp)
                        .background(
                            if (isSecondSemester) RoyalBlue.copy(alpha = 0.85f) else Color.Black.copy(alpha = 0.55f),
                            RoundedCornerShape(6.dp)
                        )
                        .padding(horizontal = 6.dp, vertical = 2.dp)
                ) {
                    Text(
                        text = if (isSecondSemester) "Semester 2" else "$notesCount Units",
                        color = Color.White,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                // Bottom Subject Name inside image
                Text(
                    text = subject.shortName,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 13.sp,
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(8.dp),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }

            // Subject details underneath
            Column(modifier = Modifier.padding(10.dp)) {
                Text(
                    text = subject.name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 13.sp,
                    color = MaterialTheme.colorScheme.onSurface,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = subject.description,
                    fontSize = 11.sp,
                    color = if (isSecondSemester) RoyalBlue else Slate700,
                    fontWeight = if (isSecondSemester) FontWeight.SemiBold else FontWeight.Normal,
                    lineHeight = 15.sp,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

// ------------------------------------------------------------
// LEVEL 1: Subject Notes Detail View (Hero Image Banner + Unit Chips + Notes List)
// ------------------------------------------------------------
@Composable
fun SubjectNotesDetailView(
    subject: SubjectCategory,
    selectedUnit: String?,
    onSelectUnit: (String?) -> Unit,
    allNotes: List<ShortNote>
) {
    val subjectAllNotes = remember(allNotes, subject.id, subject.name, subject.shortName) {
        allNotes.filter { note ->
            note.subjectId == subject.id ||
            note.subject.contains(subject.name, ignoreCase = true) ||
            note.subject.contains(subject.shortName, ignoreCase = true)
        }
    }

    val units = remember(subjectAllNotes) {
        val distinctUnits = subjectAllNotes.map { it.unit }.distinct()
        if (distinctUnits.isNotEmpty()) distinctUnits else listOf("Unit 1", "Unit 2", "Unit 3", "Unit 4", "Unit 5")
    }

    val subjectNotes = remember(subjectAllNotes, selectedUnit) {
        if (selectedUnit == null) subjectAllNotes
        else subjectAllNotes.filter { it.unit.equals(selectedUnit, ignoreCase = true) }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        // Hero Subject Image Banner (identical to ExamBoardScreen SubjectHeaderBanner)
        Box(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
            Card(
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(130.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Box(modifier = Modifier.fillMaxSize()) {
                    AsyncImage(
                        model = subject.imageUrl,
                        contentDescription = subject.name,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                Brush.verticalGradient(
                                    colors = listOf(
                                        Color.Black.copy(alpha = 0.20f),
                                        Color.Black.copy(alpha = 0.85f)
                                    )
                                )
                            )
                    )
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(14.dp),
                        verticalArrangement = Arrangement.Bottom
                    ) {
                        Box(
                            modifier = Modifier
                                .background(Color(subject.colorHex), RoundedCornerShape(6.dp))
                                .padding(horizontal = 8.dp, vertical = 2.dp)
                        ) {
                            Text(
                                text = subject.shortName,
                                color = Color.White,
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = subject.name,
                            color = Color.White,
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 17.sp,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                        Text(
                            text = "Comprehensive Chapter Study Notes (${subjectAllNotes.size} Units)",
                            color = Color.White.copy(alpha = 0.85f),
                            fontSize = 11.sp,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
            }
        }

        // Unit selector chips
        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(vertical = 4.dp)
        ) {
            item {
                ElevatedFilterChip(
                    selected = selectedUnit == null,
                    onClick = { onSelectUnit(null) },
                    label = { Text("All Units", fontSize = 12.sp, fontWeight = FontWeight.Bold) },
                    colors = FilterChipDefaults.elevatedFilterChipColors(
                        selectedContainerColor = RoyalBlue,
                        selectedLabelColor = Color.White
                    )
                )
            }
            items(units) { unitName ->
                val isSelected = selectedUnit == unitName
                ElevatedFilterChip(
                    selected = isSelected,
                    onClick = { onSelectUnit(if (isSelected) null else unitName) },
                    label = { Text(unitName, fontSize = 12.sp, fontWeight = FontWeight.SemiBold) },
                    colors = FilterChipDefaults.elevatedFilterChipColors(
                        selectedContainerColor = Color(subject.colorHex),
                        selectedLabelColor = Color.White
                    )
                )
            }
        }

        Spacer(modifier = Modifier.height(6.dp))

        // Notes List
        NotesListView(notes = subjectNotes)
    }
}

// ------------------------------------------------------------
// List of Note Cards
// ------------------------------------------------------------
@Composable
fun NotesListView(notes: List<ShortNote>) {
    if (notes.isEmpty()) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Icon(
                    Icons.Default.SearchOff,
                    contentDescription = null,
                    tint = Slate700,
                    modifier = Modifier.size(54.dp)
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "No notes found",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Try switching to 'All Units' or choosing another subject.",
                    fontSize = 12.sp,
                    color = Slate700
                )
            }
        }
    } else {
        LazyColumn(
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 6.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(notes, key = { it.id }) { note ->
                NoteCard(note = note)
            }
            item { Spacer(modifier = Modifier.height(28.dp)) }
        }
    }
}

// ------------------------------------------------------------
// Individual Clean Note Card
// ------------------------------------------------------------
@Composable
fun NoteCard(note: ShortNote) {
    var expanded by remember { mutableStateOf(false) }
    var showReaderDialog by remember { mutableStateOf(false) }
    val accentColor = Color(note.colorHex)
    val context = LocalContext.current
    val clipboardManager = LocalClipboardManager.current

    if (showReaderDialog) {
        FullNoteReaderDialog(
            note = note,
            onDismiss = { showReaderDialog = false }
        )
    }

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // Badges row: Subject + Unit + Estimated reading time
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(6.dp))
                            .background(accentColor.copy(alpha = 0.12f))
                            .padding(horizontal = 8.dp, vertical = 3.dp)
                    ) {
                        Text(
                            text = note.subject,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = accentColor
                        )
                    }

                    Spacer(modifier = Modifier.width(6.dp))

                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(6.dp))
                            .background(Color.Gray.copy(alpha = 0.12f))
                            .padding(horizontal = 7.dp, vertical = 3.dp)
                    ) {
                        Text(
                            text = note.unit,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }

                    Spacer(modifier = Modifier.width(6.dp))

                    Text(
                        text = "⏱ ~3-4 min",
                        fontSize = 10.sp,
                        color = Slate700,
                        fontWeight = FontWeight.Medium
                    )
                }

                // Expand indicator button
                IconButton(
                    onClick = { expanded = !expanded },
                    modifier = Modifier.size(30.dp)
                ) {
                    Icon(
                        if (expanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                        contentDescription = if (expanded) "Collapse" else "Expand",
                        tint = accentColor
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Title
            Text(
                text = note.title,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 15.sp,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.clickable { expanded = !expanded }
            )

            // Content preview or full text
            Spacer(modifier = Modifier.height(6.dp))
            if (expanded) {
                HorizontalDivider(color = accentColor.copy(alpha = 0.2f))
                Spacer(modifier = Modifier.height(8.dp))

                // Action Bar: Copy Note & Fullscreen Reader
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TextButton(
                        onClick = {
                            clipboardManager.setText(AnnotatedString("${note.subject} - ${note.unit}: ${note.title}\n\n${note.summary}"))
                            Toast.makeText(context, "Note copied to clipboard!", Toast.LENGTH_SHORT).show()
                        },
                        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 2.dp)
                    ) {
                        Icon(Icons.Default.ContentCopy, contentDescription = null, modifier = Modifier.size(15.dp), tint = accentColor)
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("Copy", fontSize = 11.sp, color = accentColor, fontWeight = FontWeight.Bold)
                    }

                    Spacer(modifier = Modifier.width(4.dp))

                    TextButton(
                        onClick = { showReaderDialog = true },
                        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 2.dp)
                    ) {
                        Icon(Icons.AutoMirrored.Filled.MenuBook, contentDescription = null, modifier = Modifier.size(16.dp), tint = accentColor)
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("Full Reader", fontSize = 11.sp, color = accentColor, fontWeight = FontWeight.Bold)
                    }
                }

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = note.summary,
                    fontSize = 13.sp,
                    lineHeight = 20.sp,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.90f)
                )
            } else {
                Text(
                    text = note.summary.lines().firstOrNull { it.isNotBlank() && !it.startsWith("📌") } ?: note.summary.take(120),
                    fontSize = 12.sp,
                    lineHeight = 17.sp,
                    maxLines = 2,
                    color = Slate700,
                    modifier = Modifier.clickable { expanded = true }
                )
                Spacer(modifier = Modifier.height(6.dp))
                Row(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .background(accentColor.copy(alpha = 0.08f))
                        .clickable { expanded = true }
                        .padding(horizontal = 10.dp, vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        Icons.AutoMirrored.Filled.MenuBook,
                        contentDescription = null,
                        tint = accentColor,
                        modifier = Modifier.size(13.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "Tap to read broad study guide",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = accentColor
                    )
                }
            }
        }
    }
}

// ------------------------------------------------------------
// Full Screen Reading Mode Dialog
// ------------------------------------------------------------
@Composable
fun FullNoteReaderDialog(
    note: ShortNote,
    onDismiss: () -> Unit
) {
    val accentColor = Color(note.colorHex)
    val context = LocalContext.current
    val clipboardManager = LocalClipboardManager.current
    val scrollState = rememberScrollState()

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            shape = RoundedCornerShape(20.dp),
            color = MaterialTheme.colorScheme.surface,
            tonalElevation = 6.dp
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                // Header Bar
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(accentColor.copy(alpha = 0.12f))
                        .padding(horizontal = 16.dp, vertical = 12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = note.subject,
                                fontWeight = FontWeight.Bold,
                                fontSize = 12.sp,
                                color = accentColor
                            )
                            Text(
                                text = " • ${note.unit}",
                                fontSize = 12.sp,
                                color = Slate700,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                        Text(
                            text = note.title,
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 16.sp,
                            color = MaterialTheme.colorScheme.onSurface,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        IconButton(
                            onClick = {
                                clipboardManager.setText(AnnotatedString("${note.subject} - ${note.unit}: ${note.title}\n\n${note.summary}"))
                                Toast.makeText(context, "Note copied to clipboard!", Toast.LENGTH_SHORT).show()
                            },
                            modifier = Modifier.size(36.dp)
                        ) {
                            Icon(Icons.Default.ContentCopy, contentDescription = "Copy", tint = accentColor)
                        }

                        IconButton(
                            onClick = onDismiss,
                            modifier = Modifier.size(36.dp)
                        ) {
                            Icon(Icons.Default.Close, contentDescription = "Close", tint = MaterialTheme.colorScheme.onSurface)
                        }
                    }
                }

                HorizontalDivider(color = accentColor.copy(alpha = 0.2f))

                // Scrollable Broad Note Body
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(scrollState)
                        .padding(20.dp)
                ) {
                    Text(
                        text = note.summary,
                        fontSize = 14.sp,
                        lineHeight = 22.sp,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.95f)
                    )
                    Spacer(modifier = Modifier.height(28.dp))
                }
            }
        }
    }
}

