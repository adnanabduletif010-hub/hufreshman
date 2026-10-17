package com.curiovana.hufreshman.ui.screens

import android.content.Context
import androidx.activity.compose.BackHandler
import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.curiovana.hufreshman.ui.theme.*
import com.curiovana.hufreshman.viewmodel.MainViewModel

enum class UnderstandingCategory(
    val titleEn: String,
    val titleAm: String,
    val titleOr: String,
    val subtitleEn: String,
    val subtitleAm: String,
    val subtitleOr: String,
    val icon: ImageVector,
    val primaryColor: Color,
    val badgeEn: String,
    val badgeAm: String,
    val badgeOr: String
) {
    SLOW(
        titleEn = "Steady Student",
        titleAm = "በዝግታና በጥልቀት የሚረዱ ተማሪዎች",
        titleOr = "Barattoota Suuta Hubatan",
        subtitleEn = "Deep & Step-by-Step Understanding",
        subtitleAm = "ጥልቅ እና ደረጃ በደረጃ አረዳድ",
        subtitleOr = "Gadi Fageenyaan Hubatan",
        icon = Icons.Default.HourglassTop,
        primaryColor = AmberWarning,
        badgeEn = "Foundation Builder",
        badgeAm = "መሰረት ገንቢ",
        badgeOr = "Bu'uura Ijaaraa"
    ),
    MID(
        titleEn = "Moderate Student",
        titleAm = "መካከለኛ አረዳድ ያላቸው ተማሪዎች",
        titleOr = "Barattoota Giddu-galeessaan Hubatan",
        subtitleEn = "Balanced & Consistent Pace",
        subtitleAm = "ሚዛናዊ እና ወጥ አረዳድ",
        subtitleOr = "Madaalawaa fi Qajeelaa",
        icon = Icons.Default.Balance,
        primaryColor = RoyalBlue,
        badgeEn = "Consistent Achiever",
        badgeAm = "ወጥ ውጤታማ",
        badgeOr = "Milkaa'aa Dhaabbataa"
    ),
    FAST(
        titleEn = "Fast Student",
        titleAm = "ፈጣን አረዳድ ያላቸው ተማሪዎች",
        titleOr = "Barattoota Saffisaan Hubatan",
        subtitleEn = "Quick & High-Yield Grasping",
        subtitleAm = "ንቁ እና ፈጣን አረዳድ",
        subtitleOr = "Saffisaa fi Qaxalee",
        icon = Icons.Default.Bolt,
        primaryColor = EmeraldGreen,
        badgeEn = "Quick Master",
        badgeAm = "ፈጣን አዋቂ",
        badgeOr = "Abbaa Saffisaa"
    )
}

enum class PlanLanguage(val code: String, val displayName: String, val flag: String) {
    ENGLISH("EN", "English", "🇬🇧"),
    AMHARIC("አማ", "አማርኛ", "🇪🇹"),
    OROMO("AO", "Afaan Oromoo", "🇪🇹")
}

@Composable
fun MyPlanScreen(viewModel: MainViewModel) {
    val context = LocalContext.current
    val prefs = remember { context.getSharedPreferences("hufreshman_my_plan", Context.MODE_PRIVATE) }

    var selectedCategory by remember {
        val saved = prefs.getString("selected_category", UnderstandingCategory.MID.name)
        mutableStateOf(
            try { UnderstandingCategory.valueOf(saved ?: UnderstandingCategory.MID.name) }
            catch (e: Exception) { UnderstandingCategory.MID }
        )
    }

    var selectedLanguage by remember {
        val saved = prefs.getString("selected_language", PlanLanguage.ENGLISH.name)
        mutableStateOf(
            try { PlanLanguage.valueOf(saved ?: PlanLanguage.ENGLISH.name) }
            catch (e: Exception) { PlanLanguage.ENGLISH }
        )
    }

    var hasSelectedCategory by remember { mutableStateOf(false) }
    var showLanguageMenu by remember { mutableStateOf(false) }

    // Intercept mobile phone back button to go back and choose understanding style
    BackHandler(enabled = hasSelectedCategory) {
        hasSelectedCategory = false
    }

    fun updateCategory(cat: UnderstandingCategory) {
        selectedCategory = cat
        prefs.edit().putString("selected_category", cat.name).apply()
        hasSelectedCategory = true
    }

    fun updateLanguage(lang: PlanLanguage) {
        selectedLanguage = lang
        prefs.edit().putString("selected_language", lang.name).apply()
    }

    var activeTab by remember { mutableIntStateOf(0) } // 0 = Category Plan, 1 = Exam Strategy, 2 = Course Blueprint, 3 = Daily Routine

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        // Gradient Hero Header
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
                    Column(modifier = Modifier.weight(1f)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                Icons.Filled.TipsAndUpdates,
                                contentDescription = null,
                                tint = AmberWarning,
                                modifier = Modifier.size(22.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = when (selectedLanguage) {
                                    PlanLanguage.ENGLISH -> "My Plan & Exam Strategy"
                                    PlanLanguage.AMHARIC -> "የእኔ እቅድ እና የፈተና ስልት"
                                    PlanLanguage.OROMO -> "Karoora Kiyya fi Tooftaa Qormaataa"
                                },
                                fontSize = 18.sp,
                                fontWeight = FontWeight.ExtraBold,
                                color = Color.White
                            )
                        }
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = if (hasSelectedCategory) {
                                when (selectedLanguage) {
                                    PlanLanguage.ENGLISH -> "Tailored for ${selectedCategory.titleEn}"
                                    PlanLanguage.AMHARIC -> "ለ${selectedCategory.titleAm} የተዘጋጀ"
                                    PlanLanguage.OROMO -> "Akka ${selectedCategory.titleOr}tti qophaa'e"
                                }
                            } else {
                                when (selectedLanguage) {
                                    PlanLanguage.ENGLISH -> "Choose your learning profile to unlock tailored blueprints"
                                    PlanLanguage.AMHARIC -> "ለእርስዎ የሚስማማውን የአረዳድ ዘይቤ በመምረጥ እቅድዎን ይጀምሩ"
                                    PlanLanguage.OROMO -> "Karoora keessan argachuuf gosa hubannoo keessan filadhaa"
                                }
                            },
                            fontSize = 11.5.sp,
                            color = Color.White.copy(alpha = 0.85f)
                        )
                    }

                    // Top Action Controls: Language Selector
                    Box {
                        Surface(
                            color = Color.White.copy(alpha = 0.22f),
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier.clickable { showLanguageMenu = true }
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)
                            ) {
                                Icon(
                                    Icons.Default.Language,
                                    contentDescription = "Language",
                                    tint = Color.White,
                                    modifier = Modifier.size(16.dp)
                                )
                                Spacer(modifier = Modifier.width(5.dp))
                                Text(
                                    text = "${selectedLanguage.flag} ${selectedLanguage.code}",
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White
                                )
                                Spacer(modifier = Modifier.width(2.dp))
                                Icon(
                                    Icons.Default.ArrowDropDown,
                                    contentDescription = null,
                                    tint = Color.White,
                                    modifier = Modifier.size(16.dp)
                                )
                            }
                        }

                            DropdownMenu(
                                expanded = showLanguageMenu,
                                onDismissRequest = { showLanguageMenu = false },
                                modifier = Modifier.background(MaterialTheme.colorScheme.surface)
                            ) {
                                PlanLanguage.values().forEach { lang ->
                                    DropdownMenuItem(
                                        text = {
                                            Row(verticalAlignment = Alignment.CenterVertically) {
                                                Text(lang.flag, fontSize = 16.sp)
                                                Spacer(modifier = Modifier.width(8.dp))
                                                Text(
                                                    lang.displayName,
                                                    fontWeight = if (selectedLanguage == lang) FontWeight.Bold else FontWeight.Normal,
                                                    color = if (selectedLanguage == lang) RoyalBlue else MaterialTheme.colorScheme.onSurface
                                                )
                                            }
                                        },
                                        onClick = {
                                            updateLanguage(lang)
                                            showLanguageMenu = false
                                        },
                                        leadingIcon = if (selectedLanguage == lang) {
                                            { Icon(Icons.Default.Check, contentDescription = null, tint = RoyalBlue, modifier = Modifier.size(16.dp)) }
                                        } else null
                                    )
                                }
                            }
                        }
                    }

                // Section Navigation Tabs (shown once category is chosen)
                if (hasSelectedCategory) {
                    Spacer(modifier = Modifier.height(14.dp))

                    val tabTitles = when (selectedLanguage) {
                        PlanLanguage.ENGLISH -> listOf("Understanding Plan", "Exam Attack Room", "Course Guides", "Daily Checklist")
                        PlanLanguage.AMHARIC -> listOf("የአጠናን እቅድ", "የፈተና ስልት", "የኮርሶች መመሪያ", "የእለት ተግባራት")
                        PlanLanguage.OROMO -> listOf("Karoora Hubannoo", "Tooftaa Qormaataa", "Qajeelcha Koorsii", "Hojii Guyyaa")
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .horizontalScroll(rememberScrollState()),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        tabTitles.forEachIndexed { index, title ->
                            val isSelected = activeTab == index
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(20.dp))
                                    .background(
                                        if (isSelected) Color.White else Color.White.copy(alpha = 0.15f)
                                    )
                                    .clickable { activeTab = index }
                                    .padding(horizontal = 14.dp, vertical = 7.dp)
                            ) {
                                Text(
                                    text = title,
                                    color = if (isSelected) RoyalBlueDark else Color.White,
                                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                                    fontSize = 12.sp
                                )
                            }
                        }
                    }
                }
            }
        }

        // Main Body Content
        Box(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)
        ) {
            if (!hasSelectedCategory) {
                // First screen: 3 Choices placed VERTICALLY with modern attractive icons
                CategoryVerticalSelectionView(
                    selectedCategory = selectedCategory,
                    onSelectCategory = { updateCategory(it) },
                    selectedLanguage = selectedLanguage
                )
            } else {
                // Main Tab Content once chosen
                when (activeTab) {
                    0 -> UnderstandingCategoryTab(
                        selectedCategory = selectedCategory,
                        selectedLanguage = selectedLanguage,
                        onNext = { activeTab = 1 }
                    )
                    1 -> ExamStrategyTab(
                        selectedCategory = selectedCategory,
                        selectedLanguage = selectedLanguage,
                        onNext = { activeTab = 2 }
                    )
                    2 -> CourseBlueprintTab(
                        selectedCategory = selectedCategory,
                        selectedLanguage = selectedLanguage,
                        onNext = { activeTab = 3 }
                    )
                    3 -> DailyRoutineTab(
                        context = context,
                        selectedLanguage = selectedLanguage
                    )
                }
            }
        }
    }
}

// ─────────────────────────────────────────────────────────────────────────────
// VERTICAL CATEGORY SELECTION VIEW (Steady, Moderate, Fast)
// ─────────────────────────────────────────────────────────────────────────────

@Composable
private fun CategoryVerticalSelectionView(
    selectedCategory: UnderstandingCategory,
    onSelectCategory: (UnderstandingCategory) -> Unit,
    selectedLanguage: PlanLanguage
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Column {
            Text(
                text = when (selectedLanguage) {
                    PlanLanguage.ENGLISH -> "CHOOSE YOUR UNDERSTANDING STYLE"
                    PlanLanguage.AMHARIC -> "የአረዳድ ዘይቤዎን ይምረጡ"
                    PlanLanguage.OROMO -> "GOSA HUBANNOO KEESSAN FILADHAA"
                },
                fontSize = 12.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Slate700,
                letterSpacing = 0.6.sp
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = when (selectedLanguage) {
                    PlanLanguage.ENGLISH -> "Select the learning pace that best describes how you grasp concepts:"
                    PlanLanguage.AMHARIC -> "ፅንሰ-ሀሳቦችን ለመረዳት ከእርስዎ ጋር የሚስማማውን የአጠናን ዘይቤ ይምረጡ፦"
                    PlanLanguage.OROMO -> "Tooftaa hubannoo keessaniif mijatu filachuun itti fufaa:"
                },
                fontSize = 13.sp,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.75f)
            )
        }

        UnderstandingCategory.values().forEach { cat ->
            val isCurrentSelection = selectedCategory == cat

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onSelectCategory(cat) },
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                ),
                border = androidx.compose.foundation.BorderStroke(
                    width = if (isCurrentSelection) 2.dp else 1.5.dp,
                    color = cat.primaryColor.copy(alpha = if (isCurrentSelection) 0.85f else 0.3f)
                ),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = if (isCurrentSelection) 4.dp else 2.dp,
                    pressedElevation = 6.dp
                )
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Modern Icon Container with Gradient & Elevation
                        Box(
                            modifier = Modifier
                                .size(52.dp)
                                .clip(RoundedCornerShape(16.dp))
                                .background(
                                    Brush.linearGradient(
                                        listOf(cat.primaryColor.copy(alpha = 0.82f), cat.primaryColor)
                                    )
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = cat.icon,
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier.size(28.dp)
                            )
                        }

                        Spacer(modifier = Modifier.width(14.dp))

                        Column(modifier = Modifier.weight(1f)) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Text(
                                    text = when (selectedLanguage) {
                                        PlanLanguage.ENGLISH -> cat.titleEn
                                        PlanLanguage.AMHARIC -> cat.titleAm
                                        PlanLanguage.OROMO -> cat.titleOr
                                    },
                                    fontWeight = FontWeight.ExtraBold,
                                    fontSize = 16.5.sp,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                            }

                            Spacer(modifier = Modifier.height(2.dp))

                            Text(
                                text = when (selectedLanguage) {
                                    PlanLanguage.ENGLISH -> cat.subtitleEn
                                    PlanLanguage.AMHARIC -> cat.subtitleAm
                                    PlanLanguage.OROMO -> cat.subtitleOr
                                },
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Medium,
                                color = Slate700
                            )
                        }

                        // Badge Pill at top right
                        Surface(
                            color = cat.primaryColor.copy(alpha = 0.12f),
                            shape = RoundedCornerShape(10.dp)
                        ) {
                            Text(
                                text = when (selectedLanguage) {
                                    PlanLanguage.ENGLISH -> cat.badgeEn
                                    PlanLanguage.AMHARIC -> cat.badgeAm
                                    PlanLanguage.OROMO -> cat.badgeOr
                                },
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                color = cat.primaryColor,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                            )
                        }
                    }

                    // Key Highlights for this category
                    val highlights = when (cat) {
                        UnderstandingCategory.SLOW -> when (selectedLanguage) {
                            PlanLanguage.ENGLISH -> listOf(
                                "Deep conceptual breakdown & root mastery",
                                "Step-by-step formula derivations & proofs",
                                "Builds high-confidence exam foundations"
                            )
                            PlanLanguage.AMHARIC -> listOf(
                                "ጥልቅ የፅንሰ-ሀሳብ ማብራሪያ እና የመሰረት ግንባታ",
                                "ደረጃ በደረጃ የፎርሙላዎች አመጣጥ ማብራሪያ",
                                "በራስ መተማመን ፈተናን ማለፍ የሚያስችል ዝግጅት"
                            )
                            PlanLanguage.OROMO -> listOf(
                                "Qabiyyee bu'uuraa gadi fageenyaan hubachuu",
                                "Madda foormulaa sadarkaa sadarkaan qabachuu",
                                "Qormaata ofitti amanamummaan dabruu"
                            )
                        }
                        UnderstandingCategory.MID -> when (selectedLanguage) {
                            PlanLanguage.ENGLISH -> listOf(
                                "Balanced pacing covering full syllabus",
                                "Focused high-frequency exam question drills",
                                "Consistent A-grade performance optimization"
                            )
                            PlanLanguage.AMHARIC -> listOf(
                                "ሙሉ ሲላበስ የሚሸፍን ሚዛናዊ የጥናት ፍጥነት",
                                "በፈተናዎች ተደጋጋሚ ጥያቄዎች ላይ ያተኮረ ልምምድ",
                                "ወጥ የሆነ ከፍተኛ ውጤት ማረጋገጫ"
                            )
                            PlanLanguage.OROMO -> listOf(
                                "Saffisa madaalawaa barnoota hunda qaqqabu",
                                "Shaakala gaaffilee qormaataa yeroo baay'ee dhufan",
                                "Qabxii olaanaa dhaabbataan mirkaneessuu"
                            )
                        }
                        UnderstandingCategory.FAST -> when (selectedLanguage) {
                            PlanLanguage.ENGLISH -> listOf(
                                "Rapid pattern recognition & instant recall",
                                "High-speed shortcuts & elimination tactics",
                                "Maximum exam score in minimum time"
                            )
                            PlanLanguage.AMHARIC -> listOf(
                                "ፈጣን የስልት ማስተዋል እና ፅንሰ-ሀሳብ የማስታወስ ችሎታ",
                                "የላቁ የፈተና አቋራጭ መንገዶችና ምርጫዎችን የማስወገድ ዘዴ",
                                "በአጭር ጊዜ የላቀ የፈተና ውጤት ማስመዝገብ"
                            )
                            PlanLanguage.OROMO -> listOf(
                                "Tooftaalee qormaataa saffisaan baruu",
                                "Qaxxaamuraa fi deebii sobaa dhabamsiisuu",
                                "Yeroo gabaabaa keessatti qabxii guddaa fiduu"
                            )
                        }
                    }

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                color = cat.primaryColor.copy(alpha = 0.05f),
                                shape = RoundedCornerShape(12.dp)
                            )
                            .padding(horizontal = 12.dp, vertical = 10.dp),
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        highlights.forEach { highlight ->
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Box(
                                    modifier = Modifier
                                        .size(5.dp)
                                        .clip(CircleShape)
                                        .background(cat.primaryColor)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = highlight,
                                    fontSize = 11.5.sp,
                                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.85f),
                                    fontWeight = FontWeight.Medium
                                )
                            }
                        }
                    }

                    // Action Button Row
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Surface(
                            color = cat.primaryColor,
                            shape = RoundedCornerShape(12.dp),
                            shadowElevation = 2.dp
                        ) {
                            Row(
                                modifier = Modifier.padding(horizontal = 14.dp, vertical = 8.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = when (selectedLanguage) {
                                        PlanLanguage.ENGLISH -> "Explore Plan"
                                        PlanLanguage.AMHARIC -> "እቅዱን ክፈት"
                                        PlanLanguage.OROMO -> "Karoora Bani"
                                    },
                                    fontSize = 12.5.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                Icon(
                                    imageVector = Icons.Default.ArrowForward,
                                    contentDescription = null,
                                    tint = Color.White,
                                    modifier = Modifier.size(15.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun NextPlanButton(
    onNext: () -> Unit,
    selectedLanguage: PlanLanguage
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp, bottom = 20.dp),
        horizontalArrangement = Arrangement.End
    ) {
        Surface(
            color = RoyalBlue,
            shape = RoundedCornerShape(14.dp),
            shadowElevation = 3.dp,
            modifier = Modifier.clickable { onNext() }
        ) {
            Row(
                modifier = Modifier.padding(horizontal = 22.dp, vertical = 11.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = when (selectedLanguage) {
                        PlanLanguage.ENGLISH -> "Next"
                        PlanLanguage.AMHARIC -> "ቀጣይ (Next)"
                        PlanLanguage.OROMO -> "Itti Aani (Next)"
                    },
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Spacer(modifier = Modifier.width(8.dp))
                Icon(
                    imageVector = Icons.Default.ArrowForward,
                    contentDescription = "Next",
                    tint = Color.White,
                    modifier = Modifier.size(18.dp)
                )
            }
        }
    }
}

// ─────────────────────────────────────────────────────────────────────────────
// TAB 0: Understanding Categories (Steady, Moderate, Fast)
// ─────────────────────────────────────────────────────────────────────────────

@Composable
private fun UnderstandingCategoryTab(
    selectedCategory: UnderstandingCategory,
    selectedLanguage: PlanLanguage,
    onNext: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Active Adaptive Mode Notice with blue time symbol and active plan title
        Surface(
            color = RoyalBlue.copy(alpha = 0.08f),
            shape = RoundedCornerShape(14.dp),
            border = androidx.compose.foundation.BorderStroke(1.dp, RoyalBlue.copy(alpha = 0.25f)),
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier.padding(horizontal = 14.dp, vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(RoyalBlue.copy(alpha = 0.12f)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.HourglassTop,
                        contentDescription = "Time",
                        tint = RoyalBlue,
                        modifier = Modifier.size(20.dp)
                    )
                }
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = when (selectedLanguage) {
                        PlanLanguage.ENGLISH -> "Active Plan: ${selectedCategory.titleEn}"
                        PlanLanguage.AMHARIC -> "የተመረጠ እቅድ፡ ${selectedCategory.titleAm}"
                        PlanLanguage.OROMO -> "Karoora Filatame: ${selectedCategory.titleOr}"
                    },
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    color = RoyalBlue
                )
            }
        }

        // Customized Strategy for Selected Understanding Category
        when (selectedCategory) {
            UnderstandingCategory.SLOW -> SlowlyUnderstandingPlan(selectedLanguage)
            UnderstandingCategory.MID -> MidUnderstandingPlan(selectedLanguage)
            UnderstandingCategory.FAST -> FastUnderstandingPlan(selectedLanguage)
        }

        // Next Plan Button
        NextPlanButton(onNext = onNext, selectedLanguage = selectedLanguage)
    }
}

// ─────────────────────────────────────────────────────────────────────────────
// SLOWLY UNDERSTANDING BLUEPRINT
// ─────────────────────────────────────────────────────────────────────────────

@Composable
private fun SlowlyUnderstandingPlan(selectedLanguage: PlanLanguage) {
    Card(
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        border = androidx.compose.foundation.BorderStroke(1.5.dp, AmberWarning.copy(alpha = 0.5f)),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(34.dp)
                        .background(AmberWarning.copy(alpha = 0.15f), CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(Icons.Default.HourglassTop, contentDescription = null, tint = AmberWarning, modifier = Modifier.size(18.dp))
                }
                Spacer(modifier = Modifier.width(10.dp))
                Column {
                    Text(
                        text = when (selectedLanguage) {
                            PlanLanguage.ENGLISH -> "The Deep Conceptual Master Strategy"
                            PlanLanguage.AMHARIC -> "ጥልቅ የፅንሰ-ሀሳብ ግንባታ ስልት"
                            PlanLanguage.OROMO -> "Tooftaa Bu'uura Yaadaa Cimsuu"
                        },
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 15.sp
                    )
                    Text(
                        text = when (selectedLanguage) {
                            PlanLanguage.ENGLISH -> "Designed for students who need more time to grasp concepts deeply"
                            PlanLanguage.AMHARIC -> "ፅንሰ-ሀሳቦችን ለመረዳት ተጨማሪ ጊዜ ለሚፈልጉ ተማሪዎች የተዘጋጀ"
                            PlanLanguage.OROMO -> "Barattoota yaada haaraa hubachuuf yeroo dabalataa barbaadaniif"
                        },
                        fontSize = 11.sp,
                        color = Slate700
                    )
                }
            }

            HorizontalDivider(color = Color(0xFFE2E8F0))

            PlanFeatureItem(
                icon = Icons.Default.Schedule,
                iconTint = AmberWarning,
                title = when (selectedLanguage) {
                    PlanLanguage.ENGLISH -> "1. Early Start Protocol (Never Wait for Exam Week)"
                    PlanLanguage.AMHARIC -> "1. ቀድሞ የመጀመር መመሪያ (የፈተና ሳምንትን ፈጽሞ አይጠብቁ)"
                    PlanLanguage.OROMO -> "1. Yeroon Jalqabuu (Torban Qormaataa Hin Eegin)"
                },
                description = when (selectedLanguage) {
                    PlanLanguage.ENGLISH -> "Slow learners absorb concepts very deeply, but require repeated exposure. Start studying from Week 1. Never try to cram 5 chapters in 3 nights. Dedicate 2.5 to 3.5 hours every day in 40-minute blocks with 10-minute rest breaks."
                    PlanLanguage.AMHARIC -> "በዝግታ የሚረዱ ተማሪዎች አንዴ ከተረዱት ፈጽሞ አይረሱትም። ነገር ግን ድግግሞሽ ይፈልጋሉ። ከአንደኛው ሳምንት ጀምሮ አጠናን ይጀምሩ። በቀን ከ 2.5 እስከ 3.5 ሰዓታት በ 40 ደቂቃ ከፍለው ያጥኑ።"
                    PlanLanguage.OROMO -> "Hubannoon suutaa yeroo dheeraaf sammuu keessatti tura, garuu irra deddeebii barbaada. Torban 1ffaa irraa eegalaa qo'adhaa. Guyyaatti sa'aatii 2.5 - 3.5 qo'adhaa."
                }
            )

            PlanFeatureItem(
                icon = Icons.Default.AccountTree,
                iconTint = AmberWarning,
                title = when (selectedLanguage) {
                    PlanLanguage.ENGLISH -> "2. The 'Foundational Breakdown' Technique"
                    PlanLanguage.AMHARIC -> "2. ፅንሰ-ሀሳብን በራስ ቋንቋ የመፃፍ ዘዴ"
                    PlanLanguage.OROMO -> "2. Tooftaa Yaada Caccabsuu fi Hubachuu"
                },
                description = when (selectedLanguage) {
                    PlanLanguage.ENGLISH -> "Do not jump straight into hard exam questions. First, read each definition and write it in your own words. Draw diagrams, flowcharts, and formula cards. Once you understand 'WHY' a formula works, you will never forget it."
                    PlanLanguage.AMHARIC -> "ወዲያውኑ ወደ ከባባድ ጥያቄዎች አይሂዱ። በመጀመሪያ እያንዳንዱን ፅንሰ-ሀሳብ በራስዎ አገላለጽ ይፃፉ። ፎርሙላው ለምን እንደሚሰራ ካወቁ ፈጽሞ አይሳሳቱም።"
                    PlanLanguage.OROMO -> "Kallattiin gara gaaffilee ulfaatootti hin darbiinaa. Jalqaba hiika isaanii afaan keessaniin barreessaa. 'Maaliif' foormulaan akka hojjetu yoo bartan hin dagattan."
                }
            )

            PlanFeatureItem(
                icon = Icons.Default.RecordVoiceOver,
                iconTint = AmberWarning,
                title = when (selectedLanguage) {
                    PlanLanguage.ENGLISH -> "3. Active Recall over Passive Re-reading"
                    PlanLanguage.AMHARIC -> "3. ገልጦ ከማንበብ ይልቅ ሳያዩ ራስን መጠየቅ (Active Recall)"
                    PlanLanguage.OROMO -> "3. Ija Cufatanii Of Gaafachuu (Active Recall)"
                },
                description = when (selectedLanguage) {
                    PlanLanguage.ENGLISH -> "Reading the same slide 4 times creates a false sense of security. Instead: read 1 page, close the book, and speak out loud explaining the concept as if teaching someone. If you get stuck, check the slide again."
                    PlanLanguage.AMHARIC -> "ተመሳሳይ ኖት ደጋግሞ ማንበብ ማወቂያ አይሆንም። ይልቁንም 1 ገጽ አንብበው ደብተሩን በመዝጋት በቃልዎ ለሌላ ሰው እንደሚያስረዱ ጮክ ብለው ይናገሩ።"
                    PlanLanguage.OROMO -> "Waraqaa tokko yeroo 4 irra deebi'anii dubbisuun hubachuu miti. Fuula tokko dubbisaatii kitaaba cufaa, akka waan nama biraatiif barsiisaniitti afaaniin himaa."
                }
            )

            PlanFeatureItem(
                icon = Icons.Default.Checklist,
                iconTint = AmberWarning,
                title = when (selectedLanguage) {
                    PlanLanguage.ENGLISH -> "4. Step-by-Step Problem Solving (Mathematics & Physics)"
                    PlanLanguage.AMHARIC -> "4. በቅደም-ተከተል መስራት (Mathematics እና General Physics)"
                    PlanLanguage.OROMO -> "4. Tartiibaan Hojjechuu (Mathematics fi Physics)"
                },
                description = when (selectedLanguage) {
                    PlanLanguage.ENGLISH -> "Write out: (1) Given data, (2) Unknown to find, (3) Formula required, (4) Unit conversions. Never skip steps in your scratch paper."
                    PlanLanguage.AMHARIC -> "ሁልጊዜም፡ (1) የተሰጠ (Given)፣ (2) የሚፈለግ (Required)፣ (3) ፎርሙላ (Formula)፣ (4) የዩኒት መቀየር ጽፈው ይስሩ።"
                    PlanLanguage.OROMO -> "Yeroo hunda: (1) Wanta kenname, (2) Wanta barbaadamu, (3) Foormulaa, (4) Jijjiirraa Yuuniitii tartiibaan barreessaa."
                }
            )
        }
    }
}

// ─────────────────────────────────────────────────────────────────────────────
// MID UNDERSTANDING BLUEPRINT
// ─────────────────────────────────────────────────────────────────────────────

@Composable
private fun MidUnderstandingPlan(selectedLanguage: PlanLanguage) {
    Card(
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        border = androidx.compose.foundation.BorderStroke(1.5.dp, RoyalBlue.copy(alpha = 0.5f)),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(34.dp)
                        .background(RoyalBlue.copy(alpha = 0.15f), CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(Icons.Default.Balance, contentDescription = null, tint = RoyalBlue, modifier = Modifier.size(18.dp))
                }
                Spacer(modifier = Modifier.width(10.dp))
                Column {
                    Text(
                        text = when (selectedLanguage) {
                            PlanLanguage.ENGLISH -> "The Steady High-Performance Strategy"
                            PlanLanguage.AMHARIC -> "ሚዛናዊ የከፍተኛ ውጤት ስልት"
                            PlanLanguage.OROMO -> "Tooftaa Qabxii Ol'aanaa Madaalawaa"
                        },
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 15.sp
                    )
                    Text(
                        text = when (selectedLanguage) {
                            PlanLanguage.ENGLISH -> "Balanced approach turning consistent study into top distinction grades"
                            PlanLanguage.AMHARIC -> "የተረጋጋ አጠናንን ወደ ከፍተኛ A/A+ ውጤት የሚቀይር ስልት"
                            PlanLanguage.OROMO -> "Qo'annoo dhaabbataa gara qabxii A/A+ geessu"
                        },
                        fontSize = 11.sp,
                        color = Slate700
                    )
                }
            }

            HorizontalDivider(color = Color(0xFFE2E8F0))

            PlanFeatureItem(
                icon = Icons.Default.SyncAlt,
                iconTint = RoyalBlue,
                title = when (selectedLanguage) {
                    PlanLanguage.ENGLISH -> "1. The 50 / 50 Dual Engine Rule"
                    PlanLanguage.AMHARIC -> "1. የ 50/50 እኩል ክፍፍል ደንብ (ንባብ እና ጥያቄ)"
                    PlanLanguage.OROMO -> "1. Seera 50/50 (Dubbisuu fi Gaaffii Hojjechuu)"
                },
                description = when (selectedLanguage) {
                    PlanLanguage.ENGLISH -> "Divide your study time equally: 50% reviewing lecture slides, and 50% immediately solving past university exam questions from the HU Freshman exam bank. Theory without questions leads to exam hall shock."
                    PlanLanguage.AMHARIC -> "የአጠናን ጊዜዎን በእኩል ይክፈሉት፡ 50% የክፍል ማስታወሻዎችን ማንበብ፣ 50% ደግሞ ያለፉ የዩኒቨርሲቲ ፈተናዎችን በ HU Freshman መተግበሪያ መስራት።"
                    PlanLanguage.OROMO -> "Yeroo qo'annoo keessan qixxee qoodaa: %50 nootii dubbisuu, %50 immoo gaaffilee darban appilikeeshinii HU Freshman irraa hojjechuu."
                }
            )

            PlanFeatureItem(
                icon = Icons.Default.Update,
                iconTint = RoyalBlue,
                title = when (selectedLanguage) {
                    PlanLanguage.ENGLISH -> "2. Spaced Repetition (The 1-3-7 Schedule)"
                    PlanLanguage.AMHARIC -> "2. በጊዜ ክፍተት መከለስ (የ 1-3-7 ቀናት ደንብ)"
                    PlanLanguage.OROMO -> "2. Irra Deebii Yeroo (Guyyaa 1-3-7)"
                },
                description = when (selectedLanguage) {
                    PlanLanguage.ENGLISH -> "Review new lecture material within 24 hours (10 mins). Revisit on Day 3 (solve 3 questions). Revisit on Day 7 (take a mini-quiz). This moves knowledge permanently into long-term memory."
                    PlanLanguage.AMHARIC -> "የተማሩትን ትምህርት በ 24 ሰዓት ውስጥ ይከልሱ (10 ደቂቃ)። በ 3ኛው ቀን 3 ጥያቄዎችን ይስሩ። በ 7ኛው ቀን አጭር ፈተና ይውሰዱ። ይህ መረጃውን በቋሚነት በአእምሮ ውስጥ ያስቀምጣል።"
                    PlanLanguage.OROMO -> "Barnoota barattan sa'aatii 24 keessatti irra deebi'aa. Guyyaa 3ffaa irratti gaaffilee 3 hojjedhaa. Guyyaa 7ffaa irratti of qoraa."
                }
            )

            PlanFeatureItem(
                icon = Icons.Default.Groups,
                iconTint = RoyalBlue,
                title = when (selectedLanguage) {
                    PlanLanguage.ENGLISH -> "3. Strategic 2-Person Peer Quizzing"
                    PlanLanguage.AMHARIC -> "3. ከታማኝ አጥኚ ጓደኛ ጋር ጥያቄና መልስ ማድረግ"
                    PlanLanguage.OROMO -> "3. Hiriyaa Tokko Wajjin Wal Gaafachuu"
                },
                description = when (selectedLanguage) {
                    PlanLanguage.ENGLISH -> "Find one serious study partner. After completing a chapter, quiz each other on the 5 trickiest points. Teaching each other cements 90% of the material."
                    PlanLanguage.AMHARIC -> "አንድ ትጉህ የጥናት አጋር ይያዙ። ምዕራፉ ሲያልቅ 5 አስቸጋሪ ጥያቄዎችን እርስ በርስ ተጠያየቁ። ለሰው ማስረዳት ትምህርቱን 90% በአእምሮ ይቀርጻል።"
                    PlanLanguage.OROMO -> "Hiriyaa tokko qabadhaatii boqonnaan yoo xumuramu wal gaafadhaa. Namaaf ibsuun dandeettii sammuu %90n dabala."
                }
            )
        }
    }
}

// ─────────────────────────────────────────────────────────────────────────────
// FAST UNDERSTANDING BLUEPRINT
// ─────────────────────────────────────────────────────────────────────────────

@Composable
private fun FastUnderstandingPlan(selectedLanguage: PlanLanguage) {
    Card(
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        border = androidx.compose.foundation.BorderStroke(1.5.dp, EmeraldGreen.copy(alpha = 0.5f)),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(34.dp)
                        .background(EmeraldGreen.copy(alpha = 0.15f), CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(Icons.Default.Bolt, contentDescription = null, tint = EmeraldGreen, modifier = Modifier.size(18.dp))
                }
                Spacer(modifier = Modifier.width(10.dp))
                Column {
                    Text(
                        text = when (selectedLanguage) {
                            PlanLanguage.ENGLISH -> "The High-Yield Precision Strategy"
                            PlanLanguage.AMHARIC -> "ስህተት-አልባ የፈጣን ተማሪዎች ስልት"
                            PlanLanguage.OROMO -> "Tooftaa Qulqullinaa fi Saffisaa"
                        },
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 15.sp
                    )
                    Text(
                        text = when (selectedLanguage) {
                            PlanLanguage.ENGLISH -> "Eliminates careless mistakes & unlocks a 3.8 - 4.0 GPA"
                            PlanLanguage.AMHARIC -> "ግድየለሽነትን በማስወገድ ወደ 4.0 GPA የሚያደርስ"
                            PlanLanguage.OROMO -> "Dogoggora gowwummaa dhabamsiisuun gara 4.0 GPAtti geessa"
                        },
                        fontSize = 11.sp,
                        color = Slate700
                    )
                }
            }

            HorizontalDivider(color = Color(0xFFE2E8F0))

            PlanFeatureItem(
                icon = Icons.Default.WarningAmber,
                iconTint = EmeraldGreen,
                title = when (selectedLanguage) {
                    PlanLanguage.ENGLISH -> "1. Beware the 'Illusion of Competence'"
                    PlanLanguage.AMHARIC -> "1. 'አውቀዋለሁ' ከሚል የተሳሳተ እምነት መጠንቀቅ"
                    PlanLanguage.OROMO -> "1. 'Beekeera' Jechuu Irraa Of Eeggachuu"
                },
                description = when (selectedLanguage) {
                    PlanLanguage.ENGLISH -> "Fast understanding students often look at a slide and think 'I already know this'. But reading is not knowing! Test yourself under strict exam time pressure with zero notes open."
                    PlanLanguage.AMHARIC -> "ፈጣን ተማሪዎች ኖቱን ሲያዩት 'ቀላል ነው አውቀዋለሁ' ብለው ያልፋሉ። ነገር ግን ማየትና ማወቅ ይለያያሉ። ኖት ሳይመለከቱ በፈተና ሰዓት ራስዎን ይፈትሹ።"
                    PlanLanguage.OROMO -> "Barattoonni saffisaan hubatan nootii ilaaluun 'beekeera' jedhu. Garuu ilaaluun beekuu miti! Nootii cufaatii sa'aatii eeggattanii of qoraa."
                }
            )

            PlanFeatureItem(
                icon = Icons.Default.Quiz,
                iconTint = EmeraldGreen,
                title = when (selectedLanguage) {
                    PlanLanguage.ENGLISH -> "2. Hardest Past Exams First"
                    PlanLanguage.AMHARIC -> "2. ከባባድ ያለፉ የዩኒቨርሲቲ ፈተናዎችን በቀጥታ መስራት"
                    PlanLanguage.OROMO -> "2. Gaaffilee Darban Warra Cimaa Hojjechuu"
                },
                description = when (selectedLanguage) {
                    PlanLanguage.ENGLISH -> "Don't waste time re-reading textbook definitions you already understand. Go directly to previous years' mid and final exams from HU Freshman to practice tricky distractors."
                    PlanLanguage.AMHARIC -> "የተረዱትን ትርጓሜ ደጋግሞ በማንበብ ጊዜ አያባክኑ። በቀጥታ ወደ ከባባድ የፈተና ጥያቄዎች በመሄድ አሳሳች አማራጮችን ይለዩ።"
                    PlanLanguage.OROMO -> "Hiika beektan irra deebi'uun yeroo hin gubinaa. Kallattiin gara gaaffilee qormaataa darban warra xaxamaatti darbaa."
                }
            )

            PlanFeatureItem(
                icon = Icons.Default.Timer,
                iconTint = EmeraldGreen,
                title = when (selectedLanguage) {
                    PlanLanguage.ENGLISH -> "3. The Compulsory Audit Rule (Never Submit Early)"
                    PlanLanguage.AMHARIC -> "3. ፈተናን ፈጥኖ ያለማስረከብ እና የመከለስ ደንብ"
                    PlanLanguage.OROMO -> "3. Qormaata Yeroon Duratti Kennuu Dhiisuu"
                },
                description = when (selectedLanguage) {
                    PlanLanguage.ENGLISH -> "Never submit your exam early, even if you finish in 40 minutes! Spend the remaining time recalculating every numerical question in reverse to catch sign errors and inverted fractions."
                    PlanLanguage.AMHARIC -> "በ 40 ደቂቃ ብትጨርሱም ፈተናውን ቶሎ አያስረክቡ! የቀረውን ሰዓት ሒሳባዊ ጥያቄዎችን በሌላ መንገድ እንደገና በማስላት ጥቃቅን ምልክቶችን (+ / -) ያረጋግጡ።"
                    PlanLanguage.OROMO -> "Daqiiqaa 40 keessatti yoo xumurtanis qormaata yeroon duratti hin kenninaa! Yeroo hafe hundatti herrega keessan irra deebi'aa mirkaneeffadhaa."
                }
            )
        }
    }
}

@Composable
private fun PlanFeatureItem(
    icon: ImageVector,
    iconTint: Color,
    title: String,
    description: String
) {
    Row(modifier = Modifier.fillMaxWidth()) {
        Box(
            modifier = Modifier
                .size(28.dp)
                .background(iconTint.copy(alpha = 0.12f), CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(icon, contentDescription = null, tint = iconTint, modifier = Modifier.size(15.dp))
        }
        Spacer(modifier = Modifier.width(10.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(title, fontWeight = FontWeight.Bold, fontSize = 13.sp, color = Slate900)
            Spacer(modifier = Modifier.height(3.dp))
            Text(description, fontSize = 12.sp, color = Slate700, lineHeight = 17.5.sp)
        }
    }
}

// ─────────────────────────────────────────────────────────────────────────────
// TAB 1: Exam Attack Room (Customized for Slow, Mid, Fast!)
// ─────────────────────────────────────────────────────────────────────────────

@Composable
private fun ExamStrategyTab(
    selectedCategory: UnderstandingCategory,
    selectedLanguage: PlanLanguage,
    onNext: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        // Mode Header Tag
        Surface(
            color = selectedCategory.primaryColor.copy(alpha = 0.1f),
            shape = RoundedCornerShape(10.dp),
            border = androidx.compose.foundation.BorderStroke(1.dp, selectedCategory.primaryColor.copy(alpha = 0.35f))
        ) {
            Row(
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    selectedCategory.icon,
                    contentDescription = null,
                    tint = selectedCategory.primaryColor,
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = when (selectedLanguage) {
                        PlanLanguage.ENGLISH -> "Exam Attack Room for: ${selectedCategory.titleEn}"
                        PlanLanguage.AMHARIC -> "የፈተና አሰራር ስልት ለ፡ ${selectedCategory.titleAm}"
                        PlanLanguage.OROMO -> "Tooftaa Qormaataa kan: ${selectedCategory.titleOr}"
                    },
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp,
                    color = selectedCategory.primaryColor
                )
            }
        }

        when (selectedCategory) {
            UnderstandingCategory.SLOW -> SlowlyExamAttackPlan(selectedLanguage)
            UnderstandingCategory.MID -> MidExamAttackPlan(selectedLanguage)
            UnderstandingCategory.FAST -> FastExamAttackPlan(selectedLanguage)
        }

        // Multiple Choice Elimination Secrets (Shared Foundation)
        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFE2E8F0)),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Psychology, contentDescription = null, tint = ElectricIndigo, modifier = Modifier.size(20.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = when (selectedLanguage) {
                            PlanLanguage.ENGLISH -> "Multiple Choice Elimination Secrets"
                            PlanLanguage.AMHARIC -> "የምርጫ ጥያቄዎችን የመለየት ምስጢሮች"
                            PlanLanguage.OROMO -> "Iccitii Filannoo Qormaataa Keessaa Baasuu"
                        },
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 15.sp
                    )
                }

                SecretItem(
                    title = when (selectedLanguage) {
                        PlanLanguage.ENGLISH -> "1. The Absolute Word Trap"
                        PlanLanguage.AMHARIC -> "1. ፍፁማዊ ቃላቶች ወጥመድ (ALWAYS, NEVER, ALL)"
                        PlanLanguage.OROMO -> "1. Kiyyoo Jechoota Guutuu (ALWAYS, NEVER, ALL)"
                    },
                    description = when (selectedLanguage) {
                        PlanLanguage.ENGLISH -> "Options containing absolute words like 'ALWAYS', 'NEVER', 'ALL', 'ONLY' are typically FALSE in General Psychology, Logic, and Geography. Options with 'USUALLY', 'MAY', 'GENERALLY', 'TENDS TO' are significantly more likely to be correct."
                        PlanLanguage.AMHARIC -> "በስነ-ልቦና፣ ሎጂክ እና ጂኦግራፊ 'ALWAYS'፣ 'NEVER'፣ 'ALL' የሚሉ አማራጮች አብዛኛውን ጊዜ የተሳሳቱ ናቸው። 'USUALLY' ወይም 'MAY' ያሉባቸው አማራጮች የበለጠ ትክክል ይሆናሉ።"
                        PlanLanguage.OROMO -> "Qormaata Psychology, Logic fi Geography keessatti jechoonni akka 'ALWAYS', 'NEVER', 'ALL' jedhan baay'inaan soba ta'u. Jechoonni akka 'MAY', 'USUALLY' qaban caalaatti sirrii ta'u."
                    }
                )

                SecretItem(
                    title = when (selectedLanguage) {
                        PlanLanguage.ENGLISH -> "2. The Twin Distractor Rule"
                        PlanLanguage.AMHARIC -> "2. የተመሳሳይ ወይም የተቃራኒ ምርጫዎች ደንብ"
                        PlanLanguage.OROMO -> "2. Seera Filannoowwan Wal Fakkaatan Lamaa"
                    },
                    description = when (selectedLanguage) {
                        PlanLanguage.ENGLISH -> "If two options have almost identical wording or are polar opposites (e.g. A: 'Increases linearly' and B: 'Decreases linearly'), the correct answer is almost always one of these two."
                        PlanLanguage.AMHARIC -> "ሁለት ምርጫዎች ተመሳሳይ ሆነው ጥቃቅን ልዩነት ካላቸው ወይም ተቃራኒ ከሆኑ መልሱ በእርግጠኝነት ከሁለቱ አንዱ ነው።"
                        PlanLanguage.OROMO -> "Filannoowwan lama yoo wal faallessan yookiin wal fakkaatan, deebiin sirrii isaaniin keessaa tokko ta'uun isaa baay'ee mirkanaa'aadha."
                    }
                )

                SecretItem(
                    title = when (selectedLanguage) {
                        PlanLanguage.ENGLISH -> "3. Formula Brain-Dump at Minute Zero"
                        PlanLanguage.AMHARIC -> "3. ፈተናው እንደተጀመረ ፎርሙላዎችን ረቂቅ ወረቀት ላይ ማስፈር"
                        PlanLanguage.OROMO -> "3. Akkuma Qormaanni Eegaleen Foormulaa Barreessuu"
                    },
                    description = when (selectedLanguage) {
                        PlanLanguage.ENGLISH -> "The moment the exam begins, flip to the scratch paper and write down all General Physics and Mathematics formulas you memorized before looking at Question 1."
                        PlanLanguage.AMHARIC -> "ፈተናው ሲጀመር ጥያቄ 1ን ከማንበብዎ በፊት በቃላቸው የያዟቸውን የፊዚክስ እና ሒሳብ ፎርሙላዎች ረቂቅ ወረቀት ላይ ወዲያውኑ ይፃፉ።"
                        PlanLanguage.OROMO -> "Akkuma qormaanni eegalameen gaaffii 1ffaa ilaaluun dura foormulaa sammuu keessatti qabattan waraqaa ragaa irratti barreessaa."
                    }
                )
            }
        }

        // Next Plan Button
        NextPlanButton(onNext = onNext, selectedLanguage = selectedLanguage)
    }
}

@Composable
private fun SlowlyExamAttackPlan(selectedLanguage: PlanLanguage) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        border = androidx.compose.foundation.BorderStroke(1.5.dp, AmberWarning.copy(alpha = 0.4f)),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.HourglassTop, contentDescription = null, tint = AmberWarning, modifier = Modifier.size(20.dp))
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = when (selectedLanguage) {
                        PlanLanguage.ENGLISH -> "Slowly Learner: Low-Stress Step-by-Step Attack"
                        PlanLanguage.AMHARIC -> "ለዝግታ ተማሪዎች፡ ረጋ ያለና እርግጠኛ የፈተና አሰራር"
                        PlanLanguage.OROMO -> "Hubannoo Suutaa: Tooftaa Qormaataa Nageenyaa fi Qajeelaa"
                    },
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 14.5.sp
                )
            }

            PassStepItem(
                title = when (selectedLanguage) {
                    PlanLanguage.ENGLISH -> "Phase 1 (First 45 Mins): Guaranteed Direct Points"
                    PlanLanguage.AMHARIC -> "ደረጃ 1 (የመጀመሪያ 45 ደቂቃ)፡ ቀላልና ቀጥተኛ ጥያቄዎች"
                    PlanLanguage.OROMO -> "Sadarkaa 1 (Daqiiqaa 45 duraa): Gaaffilee Salphaa fi Qajeeloo"
                },
                description = when (selectedLanguage) {
                    PlanLanguage.ENGLISH -> "Go through the exam and solve ONLY definitions, True/False, and single-step theory questions. Skip anything with complex math. This secures 40-50% of marks early and eliminates panic."
                    PlanLanguage.AMHARIC -> "በመጀመሪያው ዙር ትርጓሜዎችን፣ እውነት/ሀሰት እና አጫጭር የንድፈ-ሀሳብ ጥያቄዎችን ብቻ ይመልሱ። ረጅም ስሌቶችን ይለፉ። ይህ ፍርሃትን ያጠፋል።"
                    PlanLanguage.OROMO -> "Gaaffilee hiikaa, Dhugaa/Soba fi yaada gabaabaa qofa hojjedhaa. Herrega xaxamaa dhiisaa. Kun sodaa balleessa."
                },
                color = AmberWarning
            )

            PassStepItem(
                title = when (selectedLanguage) {
                    PlanLanguage.ENGLISH -> "Phase 2 (Next 35 Mins): Methodical Calculations"
                    PlanLanguage.AMHARIC -> "ደረጃ 2 (ቀጣይ 35 ደቂቃ)፡ የሒሳብ እና ፊዚክስ ስሌቶች"
                    PlanLanguage.OROMO -> "Sadarkaa 2 (Daqiiqaa 35 itti aanu): Herrega fi Fiiziksii Hojjechuu"
                },
                description = when (selectedLanguage) {
                    PlanLanguage.ENGLISH -> "Tackle Mathematics and Physics problems one at a time. Write Given/Formula clearly. Cross out two obvious wrong choices to reduce stress."
                    PlanLanguage.AMHARIC -> "የሒሳብ እና ፊዚክስ ጥያቄዎችን ረጋ ብለው በደረጃ ይስሩ። ሁለቱን ግልፅ የተሳሳቱ ምርጫዎች በመሰረዝ እድልዎን ወደ 50% ያሳድጉ።"
                    PlanLanguage.OROMO -> "Gaaffilee Herregaa fi Fiiziksii suuta hojjedhaa. Filannoo dogoggora ta'an lama haquun carraa keessan dabalaa."
                },
                color = RoyalBlue
            )

            PassStepItem(
                title = when (selectedLanguage) {
                    PlanLanguage.ENGLISH -> "Phase 3 (Final 10 Mins): Zero Blanks & Alignment Check"
                    PlanLanguage.AMHARIC -> "ደረጃ 3 (የመጨረሻ 10 ደቂቃ)፡ ባዶ አለመተውን ማረጋገጥ"
                    PlanLanguage.OROMO -> "Sadarkaa 3 (Daqiiqaa 10 dhumaa): Duwwaa Dhiisuu Dhabuu"
                },
                description = when (selectedLanguage) {
                    PlanLanguage.ENGLISH -> "Never leave any answer blank (no negative marking!). For remaining unsolved questions, make an educated guess. Verify question numbers match your answer sheet bubbles."
                    PlanLanguage.AMHARIC -> "የተሳሳተ መልስ ቅጣት ስለሌለው ባዶ ጥያቄ ፈጽሞ አይተዉ! የጥያቄ ቁጥሮች ከማረፊያ ወረቀቱ ጋር መግጠማቸውን ያረጋግጡ።"
                    PlanLanguage.OROMO -> "Qormaata keessatti dogoggorri qabxii hin hir'isu waan ta'eef duwwaa hin dhiisinaa! Lakkoofsa gaaffii mirkaneeffadhaa."
                },
                color = EmeraldGreen
            )
        }
    }
}

@Composable
private fun MidExamAttackPlan(selectedLanguage: PlanLanguage) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        border = androidx.compose.foundation.BorderStroke(1.5.dp, RoyalBlue.copy(alpha = 0.4f)),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.Balance, contentDescription = null, tint = RoyalBlue, modifier = Modifier.size(20.dp))
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = when (selectedLanguage) {
                        PlanLanguage.ENGLISH -> "Mid Learner: The Dynamic 3-Pass High-Yield Attack"
                        PlanLanguage.AMHARIC -> "ለመካከለኛ ተማሪዎች፡ የ 3 ዙር ፈጣን የውጤት ማሰባሰብ"
                        PlanLanguage.OROMO -> "Hubannoo Giddu-galeessaa: Marsaa 3 Qabxii Ol'aanaa Argachuu"
                    },
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 14.5.sp
                )
            }

            PassStepItem(
                title = when (selectedLanguage) {
                    PlanLanguage.ENGLISH -> "Pass 1 (0 - 30 Mins): Instant Blitz"
                    PlanLanguage.AMHARIC -> "ዙር 1 (0 - 30 ደቂቃ)፡ ፈጣን እርግጠኛ መልሶች"
                    PlanLanguage.OROMO -> "Marsaa 1 (Daqiiqaa 0 - 30): Deebiiwwan Battalaa"
                },
                description = when (selectedLanguage) {
                    PlanLanguage.ENGLISH -> "Answer only questions you are 90%+ certain of in under 20 seconds. If a question needs calculation or makes you pause, circle the number and skip immediately."
                    PlanLanguage.AMHARIC -> "በ 20 ሰከንድ ውስጥ መልሱን እርግጠኛ የሆናችሁባቸውን ጥያቄዎች ብቻ መልሱ። የሚያጠራጥር ወይም ስሌት የሚፈልግ ከሆነ ወዲያውኑ ይዝለሉት።"
                    PlanLanguage.OROMO -> "Gaaffilee %90 ol beektan daqiiqaa muraasa keessatti deebisaa. Kan isin rakkisu yoo ta'e battaluma sanatti bira darbaa."
                },
                color = EmeraldGreen
            )

            PassStepItem(
                title = when (selectedLanguage) {
                    PlanLanguage.ENGLISH -> "Pass 2 (30 - 75 Mins): Calculation & Logic Attack"
                    PlanLanguage.AMHARIC -> "ዙር 2 (30 - 75 ደቂቃ)፡ የሒሳብ፣ ፊዚክስ እና ሎጂክ ስሌቶች"
                    PlanLanguage.OROMO -> "Marsaa 2 (Daqiiqaa 30 - 75): Herrega, Fiiziksii fi Loojikii"
                },
                description = when (selectedLanguage) {
                    PlanLanguage.ENGLISH -> "Return to circled questions. Dedicate 2-3 minutes per problem. Watch out for negative words like 'EXCEPT', 'NOT APPLICABLE', and double negatives."
                    PlanLanguage.AMHARIC -> "ወደ ተዘለሉት ጥያቄዎች ይመለሱ። 'EXCEPT' እና 'NOT' የሚሉ አሳሳች ቃላትን በጥንቃቄ በማስተዋል ይስሩ።"
                    PlanLanguage.OROMO -> "Gara gaaffilee dhiistanii deebi'aa. Jechoota kiyyoo akka 'EXCEPT', 'NOT' jedhan hubannoon ilaalaatii hojjedhaa."
                },
                color = RoyalBlue
            )

            PassStepItem(
                title = when (selectedLanguage) {
                    PlanLanguage.ENGLISH -> "Pass 3 (75 - 90 Mins): Audit & Strategic Guessing"
                    PlanLanguage.AMHARIC -> "ዙር 3 (75 - 90 ደቂቃ)፡ ማረጋገጥ እና የመጨረሻ ምርጫ"
                    PlanLanguage.OROMO -> "Marsaa 3 (Daqiiqaa 75 - 90): Mirkaneeffannaa Dhumaa"
                },
                description = when (selectedLanguage) {
                    PlanLanguage.ENGLISH -> "Verify answers. For tough remaining questions, eliminate the two worst choices and select the most qualified option (options with 'usually' or 'may')."
                    PlanLanguage.AMHARIC -> "መልሶቻችሁን አረጋግጡ። ላልተመለሱ ጥያቄዎች ሁለቱን ከንቱ አማራጮች ሰርዛችሁ የተሻለውን ምረጡ።"
                    PlanLanguage.OROMO -> "Deebii keessan mirkaneeffadhaa. Gaaffilee hafan keessaa filannoo dadhaboo lama haquun isa gaarii filadhaa."
                },
                color = AmberWarning
            )
        }
    }
}

@Composable
private fun FastExamAttackPlan(selectedLanguage: PlanLanguage) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        border = androidx.compose.foundation.BorderStroke(1.5.dp, EmeraldGreen.copy(alpha = 0.4f)),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.Bolt, contentDescription = null, tint = EmeraldGreen, modifier = Modifier.size(20.dp))
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = when (selectedLanguage) {
                        PlanLanguage.ENGLISH -> "Fast Learner: Precision Audit & Error-Proofing"
                        PlanLanguage.AMHARIC -> "ለፈጣን ተማሪዎች፡ ጥልቅ ክለሳ እና ስህተት መከላከያ"
                        PlanLanguage.OROMO -> "Hubannoo Saffisaa: Dogoggora Hambisuu fi Sakatta'uu"
                    },
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 14.5.sp
                )
            }

            PassStepItem(
                title = when (selectedLanguage) {
                    PlanLanguage.ENGLISH -> "Phase 1 (0 - 45 Mins): High-Velocity First Run"
                    PlanLanguage.AMHARIC -> "ዙር 1 (0 - 45 ደቂቃ)፡ ፈጣን የመጀመሪያ ምዕራፍ"
                    PlanLanguage.OROMO -> "Marsaa 1 (Daqiiqaa 0 - 45): Marsaa Jalqabaa Saffisaa"
                },
                description = when (selectedLanguage) {
                    PlanLanguage.ENGLISH -> "Complete all questions efficiently. As you solve, put a star next to questions involving multi-step algebra or physics vector signs (+/-)."
                    PlanLanguage.AMHARIC -> "ሁሉንም ጥያቄዎች በንቃት ይጨርሱ። ውስብስብ ስሌት እና የምልክት (+/-) ጥንቃቄ የሚፈልጉትን በኮከብ ምልክት ያድርጉባቸው።"
                    PlanLanguage.OROMO -> "Gaaffilee hunda saffisaan xumuraa. Gaaffilee mallattoo (+/-) barbaadan mallattoo urjiin adda baasaa."
                },
                color = EmeraldGreen
            )

            PassStepItem(
                title = when (selectedLanguage) {
                    PlanLanguage.ENGLISH -> "Phase 2 (45 - 75 Mins): Mandatory Reverse Recalculation"
                    PlanLanguage.AMHARIC -> "ዙር 2 (45 - 75 ደቂቃ)፡ ግዴታ የሆነ የተገላቢጦሽ ስሌት"
                    PlanLanguage.OROMO -> "Marsaa 2 (Daqiiqaa 45 - 75): Irra Deebiin Faallaan Hojjechuu"
                },
                description = when (selectedLanguage) {
                    PlanLanguage.ENGLISH -> "Do not just look at your previous work (your eyes will fool you). Cover your previous steps with scratch paper and recalculate the answer using an alternative formula."
                    PlanLanguage.AMHARIC -> "የሰራችሁትን ወረቀት ብቻ አትመልከቱ (አይን ያታልላል)። የሰራችሁትን ደብቃችሁ በሌላ ፎርሙላ አረጋግጡ።"
                    PlanLanguage.OROMO -> "Wanta hojjettan qofa hin ilaalinaa. Hojii keessan haguugaatii foormulaa biraatiin irra deebi'aa hojjedhaa."
                },
                color = RoyalBlue
            )

            PassStepItem(
                title = when (selectedLanguage) {
                    PlanLanguage.ENGLISH -> "Phase 3 (75 - 90 Mins): Subtle Trap Inspection"
                    PlanLanguage.AMHARIC -> "ዙር 3 (75 - 90 ደቂቃ)፡ የተደበቁ አሳሳች ወጥመዶችን መመርመር"
                    PlanLanguage.OROMO -> "Marsaa 3 (Daqiiqaa 75 - 90): Kiyyoowwan Dhokatan Sakatta'uu"
                },
                description = when (selectedLanguage) {
                    PlanLanguage.ENGLISH -> "Inspect questions that seemed 'too easy'. University exams deliberately include deceptive questions where common misinterpretations lead to Option A."
                    PlanLanguage.AMHARIC -> "'በጣም ቀላል' የሚመስሉ ጥያቄዎችን ደግመው ይመርምሩ። ዩኒቨርሲቲዎች አውቀው የተሳሳተ ግንዛቤን ወደ ምርጫ 'A' የሚያስቀምጡባቸው ጥያቄዎች አሉ።"
                    PlanLanguage.OROMO -> "Gaaffilee 'baay'ee salphaa' fakkaatan irra deebi'aa ilaalaa. Qormaata keessatti dogoggorri filannoo 'A' ta'ee qophaa'uu danda'a."
                },
                color = AmberWarning
            )
        }
    }
}

@Composable
private fun PassStepItem(title: String, description: String, color: Color) {
    Row(modifier = Modifier.fillMaxWidth()) {
        Box(
            modifier = Modifier
                .size(10.dp)
                .background(color, CircleShape)
                .align(Alignment.Top)
        )
        Spacer(modifier = Modifier.width(10.dp))
        Column {
            Text(title, fontWeight = FontWeight.Bold, fontSize = 12.5.sp, color = color)
            Spacer(modifier = Modifier.height(2.dp))
            Text(description, fontSize = 11.5.sp, color = Slate700, lineHeight = 16.sp)
        }
    }
}

@Composable
private fun SecretItem(title: String, description: String) {
    Column {
        Text(title, fontWeight = FontWeight.Bold, fontSize = 12.5.sp, color = Slate900)
        Spacer(modifier = Modifier.height(2.dp))
        Text(description, fontSize = 11.5.sp, color = Slate700, lineHeight = 16.5.sp)
    }
}

// ─────────────────────────────────────────────────────────────────────────────
// TAB 2: Course-by-Course Guides (Follows Slowly, Mid, Fast!)
// ─────────────────────────────────────────────────────────────────────────────

@Composable
private fun CourseBlueprintTab(
    selectedCategory: UnderstandingCategory,
    selectedLanguage: PlanLanguage,
    onNext: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        // Mode Header Tag
        Surface(
            color = selectedCategory.primaryColor.copy(alpha = 0.1f),
            shape = RoundedCornerShape(10.dp),
            border = androidx.compose.foundation.BorderStroke(1.dp, selectedCategory.primaryColor.copy(alpha = 0.35f))
        ) {
            Row(
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    selectedCategory.icon,
                    contentDescription = null,
                    tint = selectedCategory.primaryColor,
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = when (selectedLanguage) {
                        PlanLanguage.ENGLISH -> "Course Blueprints Tailored For: ${selectedCategory.titleEn}"
                        PlanLanguage.AMHARIC -> "ለ ${selectedCategory.titleAm} የተዘጋጁ የኮርሶች መመሪያ"
                        PlanLanguage.OROMO -> "Qajeelcha Koorsii kan: ${selectedCategory.titleOr}"
                    },
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp,
                    color = selectedCategory.primaryColor
                )
            }
        }

        // 1. Mathematics (Explicitly Freshman Mathematics curriculum based on Mid & Final exams)
        CourseCard(
            courseName = when (selectedLanguage) {
                PlanLanguage.ENGLISH -> "Mathematics"
                PlanLanguage.AMHARIC -> "ሒሳብ (Mathematics)"
                PlanLanguage.OROMO -> "Herrega (Mathematics)"
            },
            credits = when (selectedLanguage) {
                PlanLanguage.ENGLISH -> "4 Credits (High GPA Impact)"
                PlanLanguage.AMHARIC -> "4 ክሬዲት (ለ GPA ከፍተኛ ተፅዕኖ)"
                PlanLanguage.OROMO -> "Kireeditii 4 (GPA irratti dhiibbaa guddaa)"
            },
            color = RoyalBlue,
            tips = when (selectedCategory) {
                UnderstandingCategory.SLOW -> when (selectedLanguage) {
                    PlanLanguage.ENGLISH -> listOf(
                        "Master Truth Tables, Sets, and Logic: Learn 2^n subset rules and practice truth values of compound propositions (~p v ~q => p ^ ~r) to secure early points.",
                        "Rational & Polynomial Functions: Always find domain restrictions first (denominator != 0) and practice synthetic division with the Factor Theorem (x - c).",
                        "Complex Numbers & Roots: Practice converting a + bi to polar form r(cos θ + i sin θ), and calculate modulus |z| and conjugate z̄ step-by-step.",
                        "Lines and Circles: Memorize the distance formula from a point to a line (|ax₀ + by₀ + c| / √(a² + b²)) and standard circle form (x-h)² + (y-k)² = r²."
                    )
                    PlanLanguage.AMHARIC -> listOf(
                        "የሎጂክ እና የሴት (Sets) ህጎችን ያካኑ፡ የ 2^n ሰብስቴት ቀመርን እና የኮምፓውንድ ፕሮፖዚሽን የእውነት ሰንጠረዦችን (~p v ~q => p ^ ~r) በማጥናት ቀላል ነጥቦችን ቀድመው ይሰብስቡ።",
                        "ፖሊኖሚያል እና ራሽናል ፈንክሽንስ፡ በመጀመሪያ የዶሜይን ገደቦችን (የታችኛው ክፍል != 0) ያግኙ፤ እንዲሁም በፋክተር ቴዎረም ሲንቴቲክ ዲቪዥን መስራትን ይለማመዱ።",
                        "ኮምፕሌክስ ነበሮች፡ a + bi ቅርፅን ወደ ፖላር ፎርም r(cos θ + i sin θ) መቀየርን፣ ሞጁለስን |z| እና ኮንጁጌትን z̄ በደረጃ ማስላትን ይለማመዱ።",
                        "የመስመር እና የክብ (Circle) ቀመሮች፡ ከነጥብ ወደ መስመር ያለውን የርቀት ቀመር (|ax₀ + by₀ + c| / √(a² + b²)) እና የክብ ስታንዳርድ ቀመርን (x-h)² + (y-k)² = r² በቃላቸው ይያዙ።"
                    )
                    PlanLanguage.OROMO -> listOf(
                        "Loojikii fi Seera Tuuta (Sets) qabadhaa: Seera subset 2^n fi gabatee dhugaa himoota walxaxaa (~p v ~q => p ^ ~r) qo'achuun qabxii salphaa dursaa sassaabbadhaa.",
                        "Faankishinii Polynomial fi Rational: Yeroo hunda daangaa domain (hirmaan != 0) dursaa barbaadaa; Synthetic division fi Factor Theorem shaakalaa.",
                        "Lakkoofsa Complex fi Hundee: a + bi gara bifa polar r(cos θ + i sin θ) jijjiiruu, modulus |z| fi conjugate z̄ suuta hojjechuu qabadhaa.",
                        "Sarara fi Geengoo (Circle): Fageenya tuqaa irraa gara sararaatti jiru (|ax₀ + by₀ + c| / √(a² + b²)) fi bifa geengoo (x-h)² + (y-k)² = r² sammuutti qabadhaa."
                    )
                }
                UnderstandingCategory.MID -> when (selectedLanguage) {
                    PlanLanguage.ENGLISH -> listOf(
                        "Logarithmic & Exponential Equations: Solve log equations by combining terms (log A + log B = log(AB)) and check for extraneous solutions where arguments are <= 0.",
                        "Rational Asymptotes & Graphs: Master finding vertical asymptotes (zeros of denominator), horizontal asymptotes (degree comparison), and slant/oblique asymptotes.",
                        "Hyperbolic & Trigonometric Identities: Practice evaluating hyperbolic functions (cosh(ln 3), tanh x) and identities cosh² x - sinh² x = 1 and amplitude/period (2π/|b|).",
                        "Matrices & Vectors: Calculate 2x2 and 3x3 determinants quickly and verify orthogonality of vectors using the dot product (u · v = 0)."
                    )
                    PlanLanguage.AMHARIC -> listOf(
                        "የሎጋሪዝም እና ኤክስፖኔንሺያል እኩልታዎች፡ ሎጎችን በማጣመር (log A + log B = log(AB)) ይፍቱ፤ ከዜሮ በታች የሆኑ የተሳሳቱ (extraneous) መልሶችን ውድቅ ማድረግዎን ያረጋግጡ።",
                        "የራሽናል ፈንክሽን አሲምፕቶቶች፡ ቨርቲካል አሲምፕቶትን (የታችኛው ክፍል ዜሮዎች)፣ ሆሪዞንታል አሲምፕቶትን (የዲግሪ ንጽጽር) እና ስላንት አሲምፕቶትን መፈለግን ያካኑ።",
                        "ሃይፐርቦሊክ እና ትሪጎኖሜትሪክ ቀመሮች፡ የሃይፐርቦሊክ ፈንክሽን ዋጋዎችን ማስላት (cosh(ln 3), tanh x)፣ cosh² x - sinh² x = 1 እና አምፕሊቲዩድ/ፒሬድ (2π/|b|) ይለማመዱ።",
                        "ማትሪክስ እና ቬክተሮች፡ የ 2x2 እና 3x3 ዲተርሚናንትን በፍጥነት ያግኙ፤ እንዲሁም ሁለት ቬክተሮች ኦርቶጎናል (ቀጤ-ነክ) መሆናቸውን በዶት ፕሮዳክት (u · v = 0) ያረጋግጡ።"
                    )
                    PlanLanguage.OROMO -> listOf(
                        "Walqixxee Logarithm fi Exponential: Logarithm walitti fiduun (log A + log B = log(AB)) hojjedhaa; deebii dogoggoraa (argument <= 0) ta'an kuffisaa.",
                        "Asymptote Faankishinii Rational: Vertical asymptote (hirmaan = 0), horizontal asymptote (degree wal bira qabuun) fi slant asymptote adda baasaa.",
                        "Kallattii Hyperbolic fi Trigonometric: Bu'aa faankishinii hyperbolic (cosh(ln 3), tanh x), cosh² x - sinh² x = 1 fi amplitude/period (2π/|b|) shaakalaa.",
                        "Matrix fi Vector: Determinant 2x2 fi 3x3 saffisaan herreegaa; orthogonal ta'uu vector dot product (u · v = 0) fayyadamuun mirkaneeffadhaa."
                    )
                }
                UnderstandingCategory.FAST -> when (selectedLanguage) {
                    PlanLanguage.ENGLISH -> listOf(
                        "Complex Roots & De Moivre's Theorem: Instantly solve complex polynomial roots and equations like z^(3/2) = 8i or find all roots of unity using trigonometric polar form.",
                        "Inverse & Composite Functions: Rapidly solve (f ∘ g)(x) = (g ∘ f)(x) for unknown constants and determine the restricted domain and range of g(f(x)).",
                        "Circle Tangents & Perpendicular Lines: Solve circle equations given tangent lines (y = mx + b) and points by combining radius slope and normal line equations.",
                        "Fundamental Trig Limits & Continuity: Solve lim(x->0) [sin(kx)/x] = k and piecewise function continuity parameters (a and b) without hesitation."
                    )
                    PlanLanguage.AMHARIC -> listOf(
                        "ኮምፕሌክስ ሩትስ እና ዲ ሞይቭርስ ቴዎረም፡ እንደ z^(3/2) = 8i ያሉ ውስብስብ የኮምፕሌክስ ነበሮች ጥያቄዎችን በፖላር ፎርም እና በዲ ሞይቭርስ ህግ በፍጥነት ይፍቱ።",
                        "ኢንቨርስ እና ኮምፖዚት ፈንክሽንስ፡ ያልታወቁ ቋሚዎችን ለማግኘት (f ∘ g)(x) = (g ∘ f)(x) እኩልታዎችን እና የ g(f(x)) ጥብቅ ዶሜይንና ሬንጅ በፍጥነት ይተንትኑ።",
                        "የክብ ታንጀንት እና ፐርፔንዲኩላር መስመሮች፡ ታንጀንት መስመር (y = mx + b) እና ነጥብ የተሰጣቸውን የክብ ቀመሮች በራዲየስ ስሎፕ እና ኖርማል መስመር ህጎች ይስሩ።",
                        "መሰረታዊ የትሪጎኖሜትሪ ሊሚትስ፡ lim(x->0) [sin(kx)/x] = k እና የፒስዋይዝ ፈንክሽን ኮንቲኒውቲ ተለዋዋጮችን (a እና b) ያለምንም መዘግየት ይፍቱ።"
                    )
                    PlanLanguage.OROMO -> listOf(
                        "Hundee Complex fi Seera De Moivre: Gaaffilee xaxamoo akka z^(3/2) = 8i bifa polar fi seera De Moivre fayyadamuun saffisaan xumuraa.",
                        "Inverse fi Faankishinii Walxaxaa: Walqixxee (f ∘ g)(x) = (g ∘ f)(x) herreeguun gatii k argadhaa; domain fi range g(f(x)) daangaa qabu adda baasaa.",
                        "Sarara Tangent Geengoo: Sarara tangent (y = mx + b) fi tuqaalee kenname irraa bu'uureffachuun walqixxee geengoo saffisaan argadhaa.",
                        "Limit Bu'uuraa Trigonometric: lim(x->0) [sin(kx)/x] = k fi parameters continuity kan faankishinii piecewise (a fi b) saffisaan hojjedhaa."
                    )
                }
            }
        )

        // 2. General Physics
        CourseCard(
            courseName = when (selectedLanguage) {
                PlanLanguage.ENGLISH -> "General Physics"
                PlanLanguage.AMHARIC -> "አጠቃላይ ፊዚክስ (General Physics)"
                PlanLanguage.OROMO -> "Fiiziksii Waliigalaa (General Physics)"
            },
            credits = when (selectedLanguage) {
                PlanLanguage.ENGLISH -> "4 Credits (High GPA Impact)"
                PlanLanguage.AMHARIC -> "4 ክሬዲት (ለ GPA ከፍተኛ ተፅዕኖ)"
                PlanLanguage.OROMO -> "Kireeditii 4 (GPA irratti dhiibbaa guddaa)"
            },
            color = Color(0xFFD97706),
            tips = when (selectedCategory) {
                UnderstandingCategory.SLOW -> when (selectedLanguage) {
                    PlanLanguage.ENGLISH -> listOf(
                        "Always draw a physical sketch and Free-Body Diagram (FBD) for every single problem before touching formulas.",
                        "Create a 4-column table on scratch paper: (1) Given, (2) Unknown, (3) Formula, (4) Unit conversions.",
                        "Master converting km/h to m/s (divide by 3.6) and grams to kilograms before calculating.",
                        "Solidify 1D kinematics and Newton's three laws before touching circular motion and rotational dynamics."
                    )
                    PlanLanguage.AMHARIC -> listOf(
                        "ወደ ፎርሙላ ከመግባትዎ በፊት ለእያንዳንዱ ጥያቄ ነፃ የስዕል ንድፍ (Free-Body Diagram) ይሳሉ።",
                        "በረቂቅ ወረቀት ላይ ባለ 4 ረድፍ ሰንጠረዥ ያዘጋጁ፡ (1) የተሰጠ፣ (2) የሚፈለግ፣ (3) ፎርሙላ፣ (4) የዩኒት ልወጣ።",
                        "ስሌት ከመጀመርዎ በፊት km/h ወደ m/s (ለ 3.6 በማካፈል) እና ግራም ወደ ኪሎግራም መቀየርን ያካኑ።",
                        "ሰርኩላር ሞሽንና ሮቴሽናል ዳይናሚክስን ከመጀመርዎ በፊት የአንድ አቅጣጫ ኪነማቲክስን እና የኒውተንን 3 ህጎች ያጠናክሩ።"
                    )
                    PlanLanguage.OROMO -> listOf(
                        "Foormulaa fayyadamuun dura yeroo hunda fakkii qaama bilisaa (Free-Body Diagram) kaasaa.",
                        "Waraqaa ragaa irratti gabatee sarara 4 qabu qopheessaa: (1) Kenname, (2) Barbaadamu, (3) Foormulaa, (4) Jijjiirraa Yuuniitii.",
                        "Hojjechuun dura km/h gara m/s tti (3.6f qooduun) fi giraama gara kiiloo giraamaatti jijjiiruu qabaadhaa.",
                        "Sochii geengoo fi rotational dynamics eegaluun dura kinematics fi seera Niiwutan sadan sirriitti jabeessaa."
                    )
                }
                UnderstandingCategory.MID -> when (selectedLanguage) {
                    PlanLanguage.ENGLISH -> listOf(
                        "Identify the missing kinematic variable (t, v, u, s, a) to pick the correct formula instantly in under 15 seconds.",
                        "Solve at least 25 numerical problems from previous freshman mid exams in HU Freshman.",
                        "Memorize standard constants (g = 9.8 m/s², Coulomb's constant k, electron charge).",
                        "Distinguish work done by conservative vs. non-conservative forces."
                    )
                    PlanLanguage.AMHARIC -> listOf(
                        "የጎደለውን የኪነማቲክስ ተለዋዋጭ (t, v, u, s, a) በመለየት ትክክለኛውን ፎርሙላ በ15 ሰከንድ ውስጥ ይምረጡ።",
                        "ከ HU Freshman ውስጥ ካሉ ያለፉ የፍሬሽማን ሚድ ፈተናዎች ቢያንስ 25 የስሌት ጥያቄዎችን ይስሩ።",
                        "ቋሚ ቁጥሮችን (ኮንስታንት) በቃላቸው ይያዙ (g = 9.8 m/s²፣ የኮሎምብ ኮንስታንት k፣ የኤሌክትሮን ቻርጅ)።",
                        "በኮንሰርቫቲቭ እና በኖን-ኮንሰርቫቲቭ ሃይሎች የሚሰራውን ስራ (Work) በግልፅ ይለዩ።"
                    )
                    PlanLanguage.OROMO -> listOf(
                        "Jijjiiramaa kinematics kan hir'ate (t, v, u, s, a) adda baasuun foormulaa sirrii daqiiqaa 15 gadi keessatti filadhaa.",
                        "Qormaata freshman mid darbe HU Freshman irraa yoo xiqqaate gaaffilee herregaa 25 hojjedhaa.",
                        "Dhaabbattoota beekamoo sammuutti qabadhaa (g = 9.8 m/s², Coulomb constant k, chaarjii elektiroonii).",
                        "Hojii humnoota conservative fi non-conservative gidduu jiru adda baasaa."
                    )
                }
                UnderstandingCategory.FAST -> when (selectedLanguage) {
                    PlanLanguage.ENGLISH -> listOf(
                        "Derive rotational dynamics and moment of inertia formulas from translational analogues.",
                        "Tackle combined multi-concept problems (e.g., conservation of energy coupled with projectile motion).",
                        "Use dimensional analysis to eliminate wrong multiple choice options in under 5 seconds.",
                        "Watch out for friction direction traps on inclined planes."
                    )
                    PlanLanguage.AMHARIC -> listOf(
                        "የሮቴሽናል ዳይናሚክስ እና የሞመንት ኦፍ ኢነርሺያ ፎርሙላዎችን ከትራንስሌሽናል አምሳያቸው በማነጻጸር ያፍልቁ።",
                        "የተጣመሩ ባለብዙ ፅንሰ-ሀሳብ ጥያቄዎችን (ለምሳሌ፡ የሃይል ቁጠባ ህግ ከፕሮጀክትይል ሞሽን ጋር) ይስሩ።",
                        "በ 5 ሰከንድ ውስጥ የተሳሳቱ የምርጫ መልሶችን ለማጥፋት የዳይሜንሽናል አናሊሲስን (Dimensional Analysis) ይጠቀሙ።",
                        "በተዳፋት ወለል (inclined planes) ላይ የፍሪክሽን አቅጣጫ ወጥመዶችን ይጠንቀቁ።"
                    )
                    PlanLanguage.OROMO -> listOf(
                        "Foormulaalee rotational dynamics fi moment of inertia translational irraa wal bira qabuun hubadhaa.",
                        "Gaaffilee xaxamoo yaada hedduu walitti qaban (fkn, eegumsa anniisaa fi projectile motion) hojjedhaa.",
                        "Filannoowwan dogoggora ta'an sekoondii 5 keessatti balleessuuf xiinxala daayimeenshinii (dimensional analysis) fayyadamaa.",
                        "Dallaa rarra'aa (inclined plane) irratti kallattii humna rigataa (friction) sirriitti hubadhaa."
                    )
                }
            }
        )

        // 3. Logic and Critical Thinking
        CourseCard(
            courseName = when (selectedLanguage) {
                PlanLanguage.ENGLISH -> "Logic and Critical Thinking"
                PlanLanguage.AMHARIC -> "ሎጂክ እና ሂሳዊ አስተሳሰብ (Logic)"
                PlanLanguage.OROMO -> "Loojikii fi Yaada Qorannoo (Logic)"
            },
            credits = when (selectedLanguage) {
                PlanLanguage.ENGLISH -> "3 Credits"
                PlanLanguage.AMHARIC -> "3 ክሬዲት"
                PlanLanguage.OROMO -> "Kireeditii 3"
            },
            color = ElectricIndigo,
            tips = when (selectedCategory) {
                UnderstandingCategory.SLOW -> when (selectedLanguage) {
                    PlanLanguage.ENGLISH -> listOf(
                        "Write down the 15 common informal fallacies with simple real-life everyday examples in your own mother tongue.",
                        "Identify premise indicator words ('because', 'since', 'for') vs. conclusion indicator words ('therefore', 'thus').",
                        "Understand the difference between truth (factual) and validity (structural) step-by-step.",
                        "Make flashcards for inductive vs. deductive argument definitions."
                    )
                    PlanLanguage.AMHARIC -> listOf(
                        "15ቱን የተለመዱ የማሳሳቻ ስህተቶች (fallacies) ከዕለት ተዕለት ህይወት ጋር በማያያዝ በራስዎ ቋንቋ ፅፈው ይያዙ።",
                        "የቅድመ-መነሻ ምልክት ቃላቶችን ('because', 'since', 'for') ከመደምደሚያ ምልክት ቃላት ('therefore', 'thus') ይለዩ።",
                        "የእውነትነት (Truth - እውነታን የሚመለከት) እና የትክክለኛነትን (Validity - መዋቅርን የሚመለከት) ልዩነት በጥልቀት ይረዱ።",
                        "ለኢንዳክቲቭ (inductive) እና ዲዳክቲቭ (deductive) ክርክሮች አጫጭር የማስታወሻ ካርዶችን ያዘጋጁ።"
                    )
                    PlanLanguage.OROMO -> listOf(
                        "Dogoggoroota yaadaa (fallacies) beekamoo 15 fakkeenya jireenya guyyaa guyyaa wajjin afaan dhalootaatiin barreessaa.",
                        "Jechoota premise agarsiisan ('because', 'since') fi conclusion agarsiisan ('therefore', 'thus') adda baasaa.",
                        "Garaagarummaa Dhugummaa (Truth - qabatamaa) fi Seerummaa (Validity - caasaa) sirriitti hubadhaa.",
                        "Hiika ragaa inductive fi deductive tiif kaardii yaadannoo gabaabaa qopheessaa."
                    )
                }
                UnderstandingCategory.MID -> when (selectedLanguage) {
                    PlanLanguage.ENGLISH -> listOf(
                        "Master the four argument combinations: Valid + Sound, Valid + Unsound, Invalid + Unsound.",
                        "Solve 30 fallacy identification questions in HU Freshman past exam bank.",
                        "Watch out for subtle fallacy traps: Straw Man vs. Red Herring, and Slippery Slope vs. False Cause.",
                        "Practice truth table evaluations for conditional (->) and biconditional (<->) statements."
                    )
                    PlanLanguage.AMHARIC -> listOf(
                        "አራቱን የክርክር ጥንዶች ያካኑ፡ Valid + Sound, Valid + Unsound, Invalid + Unsound።",
                        "በ HU Freshman የፈተና ባንክ ውስጥ ቢያንስ 30 የማሳሳቻ ስህተት (fallacy) መለያ ጥያቄዎችን ይስሩ።",
                        "ተመሳሳይ የሚመስሉ የማሳሳቻ ወጥመዶችን ይጠንቀቁ፡ Straw Man vs. Red Herring፣ እንዲሁም Slippery Slope vs. False Cause።",
                        "የኮንዲሽናል (->) እና ባይኮንዲሽናል (<->) አረፍተ ነገሮችን የ Truth Table ስራዎችን ይለማመዱ።"
                    )
                    PlanLanguage.OROMO -> listOf(
                        "Walgitiinsa yaadaa arfan sirriitti qabadhaa: Valid + Sound, Valid + Unsound, Invalid + Unsound.",
                        "Kuusaa qormaata HU Freshman keessatti gaaffilee fallacy adda baasuu 30 hojjedhaa.",
                        "Kiyyoowwan wal fakkaatan eeggadhaa: Straw Man vs. Red Herring, fi Slippery Slope vs. False Cause.",
                        "Gabatee dhugaa (truth table) kan conditional (->) fi biconditional (<->) shaakalaa."
                    )
                }
                UnderstandingCategory.FAST -> when (selectedLanguage) {
                    PlanLanguage.ENGLISH -> listOf(
                        "Instantly analyze syllogistic mood and figure without drawing long Euler or Venn diagrams.",
                        "Rapidly dissect long, confusing reading passages into premise-conclusion structures.",
                        "Spot subtle fallacies of relevance hidden inside seemingly logical university exam questions.",
                        "Master predicate and propositional symbolic translations."
                    )
                    PlanLanguage.AMHARIC -> listOf(
                        "ረጅም የቬን ዲያግራም ሳይስሉ የሲሎጂዝም ሙድ (mood) እና ፊገር (figure) በፍጥነት መተንተን ይልመዱ።",
                        "ረዣዥም እና ግራ የሚያጋቡ የንባብ ምንባቦችን ወደ ቅድመ-መነሻና መደምደሚያ መዋቅር በፍጥነት ይተንትኑ።",
                        "በፈተና ውስጥ ምክንያታዊ በሚመስሉ አረፍተ ነገሮች ስር የተደበቁትን ረቂቅ የማሳሳቻ ስህተቶች (Fallacies of Relevance) ይለዩ።",
                        "የፕሮፖዚሽናል እና ፕሪዲኬት ሎጂክ ምልክታዊ ትርጉሞችን (Symbolic Logic) ጠንቅቀው ይያዙ።"
                    )
                    PlanLanguage.OROMO -> listOf(
                        "Fakkii Venn dheeraa utuu hin kaasne mood fi figure syllogism saffisaan xiinxaluu danda'aa.",
                        "Barreeffamoota xaxamoo fi dheeraa gara caasaa premise fi conclusion tti saffisaan qoqqoodaa.",
                        "Gaaffilee qormaataa yaada qajeelaa fakkaatan keessatti fallacy dhokatan daftaanii adda baasaa.",
                        "Hiika mallattoo (symbolic translations) kan predicate fi propositional sirriitti qabadhaa."
                    )
                }
            }
        )

        // 4. General Psychology
        CourseCard(
            courseName = when (selectedLanguage) {
                PlanLanguage.ENGLISH -> "General Psychology"
                PlanLanguage.AMHARIC -> "አጠቃላይ ስነ-ልቦና (General Psychology)"
                PlanLanguage.OROMO -> "Saayikoolojii Waliigalaa (General Psychology)"
            },
            credits = when (selectedLanguage) {
                PlanLanguage.ENGLISH -> "3 Credits"
                PlanLanguage.AMHARIC -> "3 ክሬዲት"
                PlanLanguage.OROMO -> "Kireeditii 3"
            },
            color = EmeraldGreen,
            tips = when (selectedCategory) {
                UnderstandingCategory.SLOW -> when (selectedLanguage) {
                    PlanLanguage.ENGLISH -> listOf(
                        "Build a memory table connecting theorists to concepts: Pavlov (Classical Conditioning), Skinner (Operant), Freud (Id/Ego/Superego), Piaget (Cognitive stages).",
                        "Study in short 25-minute bursts with clear visual diagrams of the brain lobes.",
                        "Learn sensory memory, short-term memory (STM), and long-term memory (LTM) through personal daily examples.",
                        "Focus on basic definitions before tackling situational application questions."
                    )
                    PlanLanguage.AMHARIC -> listOf(
                        "አሳቢዎችን ከፅንሰ-ሀሳቦቻቸው ጋር የሚያገናኝ ሰንጠረዥ ይስሩ፡ ፓቭሎቭ (Classical Conditioning)፣ ስኪነር (Operant)፣ ፍሮይድ (Id/Ego/Superego)፣ ፒያጄት (Cognitive stages)።",
                        "የአዕምሮ ክፍሎችን (brain lobes) በስዕላዊ መግለጫ እያዩ በየ 25 ደቂቃው እረፍት እየወሰዱ ያጥኑ።",
                        "ሴንሰሪ ሜሞሪን፣ የአጭር ጊዜ (STM) እና የረጅም ጊዜ (LTM) ትውስታን ከዕለት ተዕለት ህይወትዎ ምሳሌዎች ጋር አያይዘው ይረዱ።",
                        "ወደ ሁኔታዊ የተግባር ጥያቄዎች ከመግባትዎ በፊት በመሰረታዊ ትርጓሜዎች ላይ ያተኩሩ።"
                    )
                    PlanLanguage.OROMO -> listOf(
                        "Beektota fi yaada isaanii gabateedhaan wal qabsiisaa: Pavlov (Classical), Skinner (Operant), Freud (Id/Ego/Superego), Piaget (Cognitive).",
                        "Caasaa sammuu (brain lobes) fakkii wajjin daqiiqaa 25f boqonnaa fudhachaa qo'adhaa.",
                        "Sensory memory, STM fi LTM fakkeenya jireenya keessanii wajjin wal qabsiisaatii hubadhaa.",
                        "Gaaffilee qabatamaa hojjechuun dura hiikaalee bu'uuraa irratti xiyyeeffadhaa."
                    )
                }
                UnderstandingCategory.MID -> when (selectedLanguage) {
                    PlanLanguage.ENGLISH -> listOf(
                        "Application questions rule: University exams rarely ask pure definitions; they describe a student's behavior and ask for the psychological term.",
                        "Compare conflicting theories: Behaviorist vs. Humanistic vs. Cognitive perspectives.",
                        "Review positive reinforcement vs. negative reinforcement vs. punishment distinctions.",
                        "Solve past mid exam psychology scenario questions."
                    )
                    PlanLanguage.AMHARIC -> listOf(
                        "የተግባር ጥያቄዎች ደንብ፡ የዩኒቨርሲቲ ፈተናዎች ቀጥተኛ ትርጓሜ ብዙም አይጠይቁም፤ የተማሪን ባህሪ ገልጸው የስነ-ልቦናዊ ፅንሰ-ሀሳቡን ስም ይጠይቃሉ።",
                        "ተፎካካሪ አመለካከቶችን ያነጻጽሩ፡ የቢሄቪየሪስት፣ ሂውማኒስቲክ እና ኮግኒቲቭ እይታዎች።",
                        "የፖዘቲቭ ሪኢንፎርስመንት፣ ኔጌቲቭ ሪኢንፎርስመንት እና ቅጣትን (Punishment) ልዩነቶች በሚገባ ይከልሱ።",
                        "ያለፉ የሚድ ፈተና የስነ-ልቦና ሁኔታዊ (scenario) ጥያቄዎችን ይስሩ።"
                    )
                    PlanLanguage.OROMO -> listOf(
                        "Seera gaaffilee qabatamaa: Qormaanni yuunivarsiitii hiika qofa hin gaafatu; amala namaa ibsee jecha saayikoolojii gaafata.",
                        "Ilaalcha wal morkatan wal bira qabaa: Behaviorist, Humanistic fi Cognitive perspectives.",
                        "Garaagarummaa positive reinforcement, negative reinforcement fi punishment sirriitti hubadhaa.",
                        "Qormaata darbe irraa gaaffilee scenario saayikoolojii hojjedhaa."
                    )
                }
                UnderstandingCategory.FAST -> when (selectedLanguage) {
                    PlanLanguage.ENGLISH -> listOf(
                        "Deep-dive into physiological psychology: neurotransmitters (dopamine, serotonin, acetylcholine) and sympathetic vs. parasympathetic systems.",
                        "Identify independent vs. dependent variables in tricky psychological research designs.",
                        "Look out for distractor options containing extreme words ('always', 'never').",
                        "Connect memory retrieval failures (decay, interference, repression) to cognitive experiments."
                    )
                    PlanLanguage.AMHARIC -> listOf(
                        "በፊዚዮሎጂካል ሳይኮሎጂ ላይ ጠለቅ ይበሉ፡ የነርቭ አስተላላፊዎች (ዶፓሚን፣ ሴሮቶኒን) እና ሲምፓተቲክ ከፓራሲምፓተቲክ ስርዓቶች ጋር።",
                        "በምርምር ጥናት ጥያቄዎች ውስጥ ኢንዲፔንደንት እና ዲፔንደንት ተለዋዋጮችን በቀላሉ ይለዩ።",
                        "ጽንፈኛ ቃላቶች ('always', 'never') ያሉባቸውን አሳሳች የምርጫ መልሶች ይለዩ።",
                        "የትውስታ ክፍተቶችን (decay, interference, repression) ከታወቁ ሙከራዎች ጋር አያይዘው ይረዱ።"
                    )
                    PlanLanguage.OROMO -> listOf(
                        "Saayikoolojii qaamaa (neurotransmitters - dopamine, serotonin) fi sympathetic/parasympathetic sirriitti qoradhaa.",
                        "Qorannoo keessatti garaagarummaa independent fi dependent variables saffisaan adda baasaa.",
                        "Filannoowwan jechoota cimaa ('always', 'never') qaban kiyyoo ta'uu isaanii beekaa.",
                        "Sababoota yaadannoo dhabuu (decay, interference, repression) qorannoowwan cognitive wajjin wal qabsiisaa."
                    )
                }
            }
        )

        // 5. Communicative English
        CourseCard(
            courseName = when (selectedLanguage) {
                PlanLanguage.ENGLISH -> "Communicative English"
                PlanLanguage.AMHARIC -> "ኮሚዩኒኬቲቭ እንግሊዝኛ (Communicative English)"
                PlanLanguage.OROMO -> "Afaan Ingiliffaa (Communicative English)"
            },
            credits = when (selectedLanguage) {
                PlanLanguage.ENGLISH -> "3 Credits"
                PlanLanguage.AMHARIC -> "3 ክሬዲት"
                PlanLanguage.OROMO -> "Kireeditii 3"
            },
            color = Color(0xFF0284C7),
            tips = when (selectedCategory) {
                UnderstandingCategory.SLOW -> when (selectedLanguage) {
                    PlanLanguage.ENGLISH -> listOf(
                        "In reading comprehension, ALWAYS read the questions first before reading the passage to know exactly what keywords to look for.",
                        "Review basic subject-verb agreement rules and standard verb tenses.",
                        "Highlight transition signal words (However, In addition, Consequently, On the other hand) in every text you read.",
                        "Practice reading 1 page of university English text daily out loud."
                    )
                    PlanLanguage.AMHARIC -> listOf(
                        "በምንባብ ጥያቄዎች ወቅት፡ ምንባቡን ከማንበብዎ በፊት ሁልጊዜ ጥያቄዎቹን ቀድመው ያንብቡ፤ በዚህም ቁልፍ ቃላቱን በቀላሉ ያገኛሉ።",
                        "መሰረታዊ የባለቤትና የግስ ስምምነትን (Subject-Verb Agreement) እና የግስ ጊዜያትን ይከልሱ።",
                        "በምታነቡት ጽሑፍ ሁሉ ውስጥ የማሸጋገሪያ ቃላትን (However, In addition, Consequently) ምልክት ያድርጉባቸው።",
                        "በየቀኑ 1 ገፅ የዩኒቨርሲቲ የእንግሊዝኛ ጽሑፍ ጮክ ብለው ያንብቡ።"
                    )
                    PlanLanguage.OROMO -> listOf(
                        "Gaaffilee dubbisuu keessatti, keeyyata dubbisuun dura yeroo hunda jalqaba gaaffilee dubbisaa; kun jechoota furtuu akka argattan gargaara.",
                        "Seera subject-verb agreement fi tenses bu'uuraa irra deebi'aa ilaalaa.",
                        "Jechoota ce'umsaa (However, In addition, Consequently) barreeffama hunda keessatti sararaa.",
                        "Guyyaa hunda fuula 1 barreeffama Ingiliffaa yuunivarsiitii sagalee ol kaasanii dubbisaa."
                    )
                }
                UnderstandingCategory.MID -> when (selectedLanguage) {
                    PlanLanguage.ENGLISH -> listOf(
                        "Master conditional sentence patterns: Type 1 (probable), Type 2 (unreal present), Type 3 (unreal past).",
                        "Practice active to passive voice transformations for scientific lab reporting questions.",
                        "Solve past exam vocabulary-in-context questions to deduce unfamiliar words from surrounding sentences.",
                        "Focus on paragraph organization (Topic sentence, Supporting details, Concluding sentence)."
                    )
                    PlanLanguage.AMHARIC -> listOf(
                        "የኮንዲሽናል አረፍተ ነገር አይነቶችን ያካኑ፡ Type 1 (probable)፣ Type 2 (unreal present)፣ Type 3 (unreal past)።",
                        "ለሳይንሳዊ ሪፖርት ጥያቄዎች አክቲቭ ወደ ፓሲቭ (Active to Passive Voice) መቀየርን ይለማመዱ።",
                        "የማያውቋቸውን ቃላት ፍቺ ከአካባቢያቸው አረፍተ ነገሮች ለመረዳት ያለፉ ፈተናዎችን ይስሩ።",
                        "በአንቀጽ መዋቅር ላይ ያተኩሩ (Topic sentence፣ Supporting details፣ Concluding sentence)።"
                    )
                    PlanLanguage.OROMO -> listOf(
                        "Caasaa conditional sentences qabadhaa: Gosa 1 (probable), Gosa 2 (unreal present), Gosa 3 (unreal past).",
                        "Gaaffilee gabaasa laabii saayinsiitiif active gara passive tti jijjiiruu shaakalaa.",
                        "Hiika jechoota haaraa keeyyata keessaa hubachuuf qormaata darbe hojjedhaa.",
                        "Caasaa keeyyataa irratti xiyyeeffadhaa (Topic sentence, Supporting details, Concluding sentence)."
                    )
                }
                UnderstandingCategory.FAST -> when (selectedLanguage) {
                    PlanLanguage.ENGLISH -> listOf(
                        "Skim and scan 500-word reading passages in under 2 minutes.",
                        "Spot subtle grammatical errors in sentence correction items (dangling modifiers, faulty parallelism).",
                        "Identify subtle tone, author purpose, and inference in reading comprehension passages.",
                        "Master precise punctuation rules for complex compound sentences."
                    )
                    PlanLanguage.AMHARIC -> listOf(
                        "የ 500 ቃላት ምንባብን ከ 2 ደቂቃ ባነሰ ጊዜ ውስጥ አንብበው ዋና ፍሬ ነገሩን የመረዳት (Skim and Scan) ልምድ ያሳድጉ።",
                        "በአረፍተ ነገር ማስተካከል ጥያቄዎች ውስጥ ረቂቅ ስህተቶችን (dangling modifiers, faulty parallelism) በቀላሉ ያግኙ።",
                        "በምንባቦች ውስጥ የጸሐፊውን ድምፀት (Tone)፣ ዓላማ እና ድብቅ መልእክት የመረዳት ችሎታን ያሳድጉ።",
                        "ለውስብስብ አረፍተ ነገሮች ትክክለኛ የስርዓተ-ነጥብ ህጎችን ጠንቅቀው ይረዱ።"
                    )
                    PlanLanguage.OROMO -> listOf(
                        "Keeyyata jechoota 500 qabu daqiiqaa 2 gadi keessatti skim fi scan gochuun hubadhaa.",
                        "Dogoggora caaslugaa dhokataa (dangling modifiers, faulty parallelism) daftaanii adda baasaa.",
                        "Ilaalcha barreessaa (tone), kaayyoo fi ergaa dhokataa keeyyataa hubadhaa.",
                        "Seera sirna tuqaalee (punctuation) himoota xaxamoof gargaaran sirriitti qabadhaa."
                    )
                }
            }
        )

        // 6. Geography of Ethiopia and the Horn
        CourseCard(
            courseName = when (selectedLanguage) {
                PlanLanguage.ENGLISH -> "Geography of Ethiopia and the Horn"
                PlanLanguage.AMHARIC -> "የኢትዮጵያና የአፍሪካ ቀንድ ጂኦግራፊ (Geography)"
                PlanLanguage.OROMO -> "Jiograafii Itoophiyaa fi Gaanfa Afrikaa"
            },
            credits = when (selectedLanguage) {
                PlanLanguage.ENGLISH -> "3 Credits"
                PlanLanguage.AMHARIC -> "3 ክሬዲት"
                PlanLanguage.OROMO -> "Kireeditii 3"
            },
            color = Color(0xFF2563EB),
            tips = when (selectedCategory) {
                UnderstandingCategory.SLOW -> when (selectedLanguage) {
                    PlanLanguage.ENGLISH -> listOf(
                        "Study relief and physical maps of Ethiopia: clearly locate Western Highlands, Great Rift Valley, and Eastern Highlands.",
                        "Memorize Ethiopian traditional agro-ecological zones: Bereha, Kolla, Woina-Dega, Dega, and Wurch.",
                        "Group drainage basins into 3 systems: Mediterranean, Indian Ocean, and Inland drainage.",
                        "Create simple flashcards for Ethiopian geological eras (Precambrian to Cenozoic)."
                    )
                    PlanLanguage.AMHARIC -> listOf(
                        "የኢትዮጵያን መልክዓ ምድራዊ ካርታ ያጥኑ፡ ምዕራባዊ ከፍተኛ ቦታዎችን፣ ስምጥ ሸለቆን እና ምስራቃዊ ከፍተኛ ቦታዎችን በግልፅ ይለዩ።",
                        "የኢትዮጵያን ባህላዊ የአየር ንብረት ዞኖች በቃላቸው ይያዙ፡ በረሃ፣ ቆላ፣ ወይና-ደጋ፣ ደጋ እና ውርጭ።",
                        "የወንዞች ተፋሰሶችን በ 3 ይክፈሉ፡ ሜዲትራኒያን፣ የህንድ ውቅያኖስ እና የውስጥ ተፋሰስ።",
                        "ለኢትዮጵያ ጂኦሎጂካል ዘመናት (Precambrian እስከ Cenozoic) የማስታወሻ ካርዶችን ያዘጋጁ።"
                    )
                    PlanLanguage.OROMO -> listOf(
                        "Kaartaa uumama Itoophiyaa qo'adhaa: Dachee olka'aa Lixaa, Sulula Qilee (Rift Valley) fi Dachee olka'aa Bahaa adda baasaa.",
                        "Kutaalee qilleensaa aadaa Itoophiyaa sammuutti qabadhaa: Gammoojjii (Bereha, Kolla), Baddaa-dare (Woina-Dega), Baddaa (Dega) fi Qorra (Wurch).",
                        "Lolaa lagaa bakka 3tti qoodaa: Meeditiraaniyaan, Garba Hindii fi kan keessaa (Inland).",
                        "Bara jiolojii Itoophiyaa (Precambrian hanga Cenozoic) tiif kaardii yaadannoo qopheessaa."
                    )
                }
                UnderstandingCategory.MID -> when (selectedLanguage) {
                    PlanLanguage.ENGLISH -> listOf(
                        "Connect geological events to current Ethiopian topography (e.g., Cenozoic rifting, Mesozoic transgression).",
                        "Memorize major river tributaries (Abbay, Baro-Akobo, Tekeze, Awash, Genale-Dawa).",
                        "Review population distribution factors: environmental, historical, and socio-economic drivers.",
                        "Solve past geography mid exam multiple choice items."
                    )
                    PlanLanguage.AMHARIC -> listOf(
                        "የጂኦሎጂካል ክስተቶችን አሁን ካለው የኢትዮጵያ ገጽታ ጋር ያዛምዱ (ለምሳሌ፡ ሴኖዞይክ ሪፍቲንግ፣ ሜሶዞይክ ትራንስግሬሽን)።",
                        "ዋና ዋና የወንዝ ገባሮችን በቃላቸው ይያዙ (አባይ፣ ባሮ-አኮቦ፣ ተከዜ፣ አዋሽ፣ ገናሌ-ዳዋ)።",
                        "የህዝብ ስርጭት መወሰኛ ምክንያቶችን ይከልሱ፡ አካባቢያዊ፣ ታሪካዊ እና ማህበራዊ-ኢኮኖሚያዊ ሁኔታዎች።",
                        "ያለፉ የጂኦግራፊ ሚድ ፈተና የምርጫ ጥያቄዎችን ይስሩ።"
                    )
                    PlanLanguage.OROMO -> listOf(
                        "Ta'iinsa jiolojii bocaa lafa Itoophiyaa ammaa wajjin wal qabsiisaa (fkn, babal'achuu Cenozoic, bishaan Mesozoic).",
                        "Madda lageen gurguddoo sammuutti qabadhaa (Abbaayyaa, Baaroo-Akooboo, Takazee, Hawaash, Ganaalee-Daawaa).",
                        "Sababoota faffaca'iinsa uummataa irra deebi'aa ilaalaa: naannoo, seenaa fi hawaas-dinagdee.",
                        "Qormaata mid jiograafii darbe irraa gaaffilee filannoo hojjedhaa."
                    )
                }
                UnderstandingCategory.FAST -> when (selectedLanguage) {
                    PlanLanguage.ENGLISH -> listOf(
                        "Analyze demographic transition models and spatial population density formulas in Ethiopia.",
                        "Correlate climate regimes with ITCZ (Inter-Tropical Convergence Zone) seasonal shifts.",
                        "Tackle data-driven and map-interpretation questions under timed pressure.",
                        "Evaluate regional agricultural systems and soil degradation challenges."
                    )
                    PlanLanguage.AMHARIC -> listOf(
                        "በኢትዮጵያ ያለውን የዴሞግራፊክ ሽግግር ሞዴል እና የህዝብ ጥግግት ስሌቶችን ይተንትኑ።",
                        "የአየር ንብረት ለውጦችን ከ ITCZ (Inter-Tropical Convergence Zone) ወቅታዊ እንቅስቃሴ ጋር ያዛምዱ።",
                        "የመረጃ ሰንጠረዦችና የካርታ ትንተና ጥያቄዎችን በጊዜ ገደብ ውስጥ ሰርተው ይጨርሱ።",
                        "የክልላዊ ግብርና ስርዓቶችን እና የአፈር መሸርሸር ችግሮችን በጥልቀት ይገምግሙ።"
                    )
                    PlanLanguage.OROMO -> listOf(
                        "Moodeela jijjiirama uummataa fi foormulaa rukkina uummataa Itoophiyaa xiinxalaa.",
                        "Haala qilleensaa sochii ITCZ (Inter-Tropical Convergence Zone) wajjin wal qabsiisaa.",
                        "Gaaffilee ragaa fi kaartaa irratti hundaa'an yeroo qabame keessatti hojjedhaa.",
                        "Sirna qonnaa naannolee fi qormaata dhiqama biyyootti jiru madaalaa."
                    )
                }
            }
        )

        // 7. Introduction to Economics
        CourseCard(
            courseName = when (selectedLanguage) {
                PlanLanguage.ENGLISH -> "Introduction to Economics"
                PlanLanguage.AMHARIC -> "የኢኮኖሚክስ መግቢያ (Economics)"
                PlanLanguage.OROMO -> "Seensa Ikonoomiksii (Economics)"
            },
            credits = when (selectedLanguage) {
                PlanLanguage.ENGLISH -> "3 Credits"
                PlanLanguage.AMHARIC -> "3 ክሬዲት"
                PlanLanguage.OROMO -> "Kireeditii 3"
            },
            color = Color(0xFF16A34A),
            tips = when (selectedCategory) {
                UnderstandingCategory.SLOW -> when (selectedLanguage) {
                    PlanLanguage.ENGLISH -> listOf(
                        "Draw supply and demand curves with your own hand until shifts vs. movements along the curve become second nature.",
                        "Understand opportunity cost using the Production Possibilities Frontier (PPF).",
                        "Master the fundamental economic questions: What to produce, How to produce, For whom to produce.",
                        "Define Microeconomics (individual consumers/firms) vs. Macroeconomics (national economy)."
                    )
                    PlanLanguage.AMHARIC -> listOf(
                        "የፍላጎት እና የአቅርቦት (Demand & Supply) ከርቮችን በእጅዎ ደጋግመው ይሳሉ፤ ከርቭ ላይ መንሸራተት እና የከርቭ መቀየርን በደንብ ይለዩ።",
                        "የዕድል ዋጋን (Opportunity Cost) በምርት አማራጮች ድንበር (PPF) አማካኝነት ይረዱ።",
                        "መሰረታዊ የኢኮኖሚክስ ጥያቄዎችን ይረዱ፡ ምን ይመረት፣ እንዴት ይመረት፣ ለማን ይመረት።",
                        "የማይክሮ-ኢኮኖሚክስ (የግለሰብ/ድርጅት) እና የማክሮ-ኢኮኖሚክስ (የሀገር አቀፍ ኢኮኖሚ) ልዩነትን ይያዙ።"
                    )
                    PlanLanguage.OROMO -> listOf(
                        "Sarara fedhii fi dhiyeessii (demand & supply) harka keessaniin deddeebisaa kaasaa; sochiilee sarara irrattii fi jijjiirama sararaa adda baasaa.",
                        "Baasii carraa (opportunity cost) Production Possibilities Frontier (PPF) fayyadamuun hubadhaa.",
                        "Gaaffilee bu'uuraa ikonoomiksii qabadhaa: Maaltu oomishama, Akkamitti oomishama, Eenyuuf oomishama.",
                        "Garaagarummaa Maayikiroo-ikonoomiksii (dhuunfaa/dhaabbata) fi Maakiroo-ikonoomiksii (biyyoolessaa) adda baasaa."
                    )
                }
                UnderstandingCategory.MID -> when (selectedLanguage) {
                    PlanLanguage.ENGLISH -> listOf(
                        "Practice calculating price elasticity of demand (Ed) using the midpoint formula.",
                        "Master cost formulas: Total Cost (TC), Marginal Cost (MC), Average Variable Cost (AVC), Average Total Cost (ATC).",
                        "Distinguish four market structures: Perfect Competition, Monopolistic Competition, Oligopoly, and Monopoly.",
                        "Review GDP calculation approaches (Expenditure vs. Income method)."
                    )
                    PlanLanguage.AMHARIC -> listOf(
                        "የዋጋ ፍላጎት ልስላሴን (Elasticity of Demand - Ed) በሚድፖይንት ፎርሙላ ማስላትን ይለማመዱ።",
                        "የወጪ ፎርሙላዎችን ያካኑ፡ ጠቅላላ ወጪ (TC)፣ ተጨማሪ ወጪ (MC)፣ አማካይ ተለዋዋጭ ወጪ (AVC)፣ አማካይ ጠቅላላ ወጪ (ATC)።",
                        "አራቱን የገበያ መዋቅሮች ይለዩ፡ Perfect Competition, Monopolistic Competition, Oligopoly, and Monopoly።",
                        "የሀገር ውስጥ አጠቃላይ ምርት (GDP) ማስያ መንገዶችን (Expenditure vs. Income) ይከልሱ።"
                    )
                    PlanLanguage.OROMO -> listOf(
                        "Saffisa jijjiirama fedhii gatii (elasticity of demand - Ed) foormulaa midpoint tiin hojjechuu shaakalaa.",
                        "Foormulaawwan baasii qabadhaa: Total Cost (TC), Marginal Cost (MC), Average Variable Cost (AVC), Average Total Cost (ATC).",
                        "Caasaa gabaa arfan adda baasaa: Perfect Competition, Monopolistic Competition, Oligopoly, Monopoly.",
                        "Tooftaalee herrega GDP (Baasii fi Galii) irra deebi'aa ilaalaa."
                    )
                }
                UnderstandingCategory.FAST -> when (selectedLanguage) {
                    PlanLanguage.ENGLISH -> listOf(
                        "Solve consumer utility maximization equations using marginal utility per birr (MUx / Px = MUy / Py).",
                        "Analyze macro policy shifts: Expansionary vs. Contractionary Fiscal & Monetary policies.",
                        "Solve price ceiling and price floor deadweight loss calculation problems.",
                        "Evaluate real vs. nominal GDP deflator nuances under inflation."
                    )
                    PlanLanguage.AMHARIC -> listOf(
                        "የተጠቃሚ እርካታ ማብዛትን (Utility Maximization) በብር ማርጂናል እርካታ (MUx / Px = MUy / Py) አስልተው ይፍቱ።",
                        "የማክሮ ፖሊሲ ለውጦችን ይተንትኑ፡ የፊስካል እና ሞኔተሪ ፖሊሲዎች (Expansionary vs. Contractionary)።",
                        "የዋጋ ጣሪያ (Price Ceiling) እና የዋጋ ወለል (Price Floor) የፈጠሩትን ኪሳራ (Deadweight Loss) ስሌቶችን ይስሩ።",
                        "በዋጋ ግሽበት ወቅት በሪል እና ኖሚናል GDP ዲፍሌተር መካከል ያለውን ረቂቅ ልዩነት ይገምግሙ።"
                    )
                    PlanLanguage.OROMO -> listOf(
                        "Gaaffilee quubsa fayyadamaa (utility maximization) kan MUx / Px = MUy / Py fayyadamuun hojjedhaa.",
                        "Imaammata gurguddoo dinagdee xiinxalaa: Imaammata Maallaqaa fi Taaksii (Expansionary vs Contractionary).",
                        "Miidhaa gatii ol'aanaa (price ceiling) fi gatii gadi'aanaa (price floor) irraa dhufu (deadweight loss) herreegaa.",
                        "Garaagarummaa real GDP fi nominal GDP deflator gidduu jiru ifatti madaalaa."
                    )
                }
            }
        )

        // Next Plan Button
        NextPlanButton(onNext = onNext, selectedLanguage = selectedLanguage)
    }
}

@Composable
private fun CourseCard(courseName: String, credits: String, color: Color, tips: List<String>) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        border = androidx.compose.foundation.BorderStroke(1.dp, color.copy(alpha = 0.35f)),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(14.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(courseName, fontWeight = FontWeight.Bold, fontSize = 14.sp, color = Slate900)
                Surface(
                    color = color.copy(alpha = 0.12f),
                    shape = RoundedCornerShape(6.dp)
                ) {
                    Text(
                        credits,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        color = color,
                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                    )
                }
            }

            HorizontalDivider(color = Color(0xFFE2E8F0))

            tips.forEach { tip ->
                Row(modifier = Modifier.fillMaxWidth()) {
                    Text("•", color = color, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(tip, fontSize = 12.sp, color = Slate700, lineHeight = 17.sp)
                }
            }
        }
    }
}

// ─────────────────────────────────────────────────────────────────────────────
// TAB 3: Daily Habit & Study Routine Checklist
// ─────────────────────────────────────────────────────────────────────────────

@Composable
private fun DailyRoutineTab(
    context: Context,
    selectedLanguage: PlanLanguage
) {
    val prefs = remember { context.getSharedPreferences("hufreshman_habits", Context.MODE_PRIVATE) }
    val todayKey = remember {
        val cal = java.util.Calendar.getInstance()
        "${cal.get(java.util.Calendar.YEAR)}_${cal.get(java.util.Calendar.DAY_OF_YEAR)}"
    }

    val tasks = when (selectedLanguage) {
        PlanLanguage.ENGLISH -> listOf(
            "Reviewed today's university lecture notes within 24h",
            "Solved 5 past exam questions from HU Freshman",
            "Practiced active recall (explained a concept without looking)",
            "Reviewed high-yield General Physics & Mathematics formulas",
            "Organized tomorrow's study goal & slept 7+ hours"
        )
        PlanLanguage.AMHARIC -> listOf(
            "የዛሬውን የክፍል ማስታወሻ በ 24 ሰዓት ውስጥ መከለስ",
            "5 ያለፉ የፈተና ጥያቄዎችን በ HU Freshman መስራት",
            "ማስታወሻ ሳይመለከቱ ፅንሰ-ሀሳብን በራስ ቃል ማስረዳት",
            "የ General Physics እና Mathematics ፎርሙላዎችን መከለስ",
            "የነገውን የጥናት እቅድ ማውጣት እና 7+ ሰዓታት መተኛት"
        )
        PlanLanguage.OROMO -> listOf(
            "Nootii har'aa sa'aatii 24 keessatti irra deebi'uu",
            "Gaaffilee qormaataa darban 5 HU Freshman irraa hojjechuu",
            "Nootii utuu hin ilaalin yaada barnootaa afaaniin ibsuu",
            "Foormulaa General Physics fi Mathematics irra deebi'uu",
            "Karoora boruu qopheeffachuu fi sa'aatii 7+ rafuu"
        )
    }

    var checkedStates by remember {
        mutableStateOf(
            tasks.map { task ->
                prefs.getBoolean("habit_${todayKey}_$task", false)
            }
        )
    }

    val completedCount = checkedStates.count { it }
    val progress = if (tasks.isNotEmpty()) completedCount.toFloat() / tasks.size else 0f

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        Text(
            text = when (selectedLanguage) {
                PlanLanguage.ENGLISH -> "DAILY STUDY DISCIPLINE TRACKER"
                PlanLanguage.AMHARIC -> "የእለት የጥናት ክትትል"
                PlanLanguage.OROMO -> "HORDODDII QO'ANNOO GUYYAA"
            },
            fontSize = 11.5.sp,
            fontWeight = FontWeight.ExtraBold,
            color = Slate700,
            letterSpacing = 0.5.sp
        )

        // Progress Card
        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            border = androidx.compose.foundation.BorderStroke(1.dp, RoyalBlue.copy(alpha = 0.3f)),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = when (selectedLanguage) {
                                PlanLanguage.ENGLISH -> "Today's Consistency Score"
                                PlanLanguage.AMHARIC -> "የዛሬው ውጤታማነት"
                                PlanLanguage.OROMO -> "Qabxii Ciminna Har'aa"
                            },
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp
                        )
                        Text(
                            text = "$completedCount / ${tasks.size} " + when (selectedLanguage) {
                                PlanLanguage.ENGLISH -> "habits completed"
                                PlanLanguage.AMHARIC -> "ተግባራት ተጠናቀዋል"
                                PlanLanguage.OROMO -> "xumurameera"
                            },
                            fontSize = 11.sp,
                            color = Slate700
                        )
                    }
                    Text(
                        "${(progress * 100).toInt()}%",
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 20.sp,
                        color = if (progress >= 0.8f) EmeraldGreen else RoyalBlue
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                LinearProgressIndicator(
                    progress = { progress },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(8.dp)
                        .clip(RoundedCornerShape(4.dp)),
                    color = if (progress >= 0.8f) EmeraldGreen else RoyalBlue,
                    trackColor = Color(0xFFE2E8F0)
                )
            }
        }

        Text(
            text = when (selectedLanguage) {
                PlanLanguage.ENGLISH -> "CHECKLIST FOR TODAY"
                PlanLanguage.AMHARIC -> "የዛሬ የድርጊት ዝርዝር"
                PlanLanguage.OROMO -> "HOJII HAR'AA"
            },
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            color = Slate700
        )

        tasks.forEachIndexed { index, task ->
            val isChecked = checkedStates[index]
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        val newStates = checkedStates.toMutableList()
                        newStates[index] = !isChecked
                        checkedStates = newStates
                        prefs.edit().putBoolean("habit_${todayKey}_$task", !isChecked).apply()
                    },
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(
                    containerColor = if (isChecked) EmeraldGreen.copy(alpha = 0.08f) else MaterialTheme.colorScheme.surface
                ),
                border = androidx.compose.foundation.BorderStroke(
                    1.dp,
                    if (isChecked) EmeraldGreen.copy(alpha = 0.4f) else Color(0xFFE2E8F0)
                )
            ) {
                Row(
                    modifier = Modifier.padding(14.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = isChecked,
                        onCheckedChange = { checked ->
                            val newStates = checkedStates.toMutableList()
                            newStates[index] = checked
                            checkedStates = newStates
                            prefs.edit().putBoolean("habit_${todayKey}_$task", checked).apply()
                        },
                        colors = CheckboxDefaults.colors(checkedColor = EmeraldGreen)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = task,
                        fontSize = 13.sp,
                        fontWeight = if (isChecked) FontWeight.SemiBold else FontWeight.Normal,
                        color = if (isChecked) Slate900 else Slate700
                    )
                }
            }
        }

        // Quote Card
        Card(
            shape = RoundedCornerShape(14.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFF1F5F9)),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(14.dp)) {
                Text(
                    text = when (selectedLanguage) {
                        PlanLanguage.ENGLISH -> "💡 \"Excellence is not an accident; it is the repeated daily habit of active practice. What you do today determines your GPA tomorrow.\""
                        PlanLanguage.AMHARIC -> "💡 \"ውጤታማነት በአጋጣሚ የሚመጣ ሳይሆን በየእለቱ በሚደረግ ተከታታይ ጥረት ነው። ዛሬ የምታደርጉት የነገ ውጤታችሁን ይወስነዋል።\""
                        PlanLanguage.OROMO -> "💡 \"Milkaa'inni tasa kan dhufu miti; carraaqqii guyyaa guyyaan godhamuudha. Wanti har'a hojjettan qabxii boruu murteessa.\""
                    },
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium,
                    color = Slate800,
                    lineHeight = 18.sp
                )
            }
        }
    }
}
