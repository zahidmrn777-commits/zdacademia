package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material.icons.automirrored.filled.VolumeUp
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.Forum
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Translate
import androidx.compose.material.icons.outlined.DarkMode
import androidx.compose.material.icons.outlined.LightMode
import androidx.compose.material.icons.outlined.RateReview
import androidx.compose.material.icons.outlined.SettingsBrightness
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.TranslatorViewModel
import com.example.ui.components.InAppReviewPrompt
import com.example.ui.screens.ConversationScreen
import com.example.ui.screens.HistoryScreen
import com.example.ui.screens.LanguagePacksScreen
import com.example.ui.screens.PhrasebookScreen
import com.example.ui.screens.TranslateScreen
import com.example.ui.theme.AppThemeMode
import com.example.ui.theme.MyApplicationTheme

enum class TranslatorNavTab(val title: String, val icon: ImageVector, val tag: String) {
    TRANSLATE("Translate", Icons.Default.Translate, "tab_translate"),
    CONVERSATION("Conversation", Icons.Default.Forum, "tab_conversation"),
    PHRASEBOOK("Phrasebook", Icons.AutoMirrored.Filled.MenuBook, "tab_phrasebook"),
    HISTORY("Saved", Icons.Default.History, "tab_history"),
    PACKS("Offline Packs", Icons.Default.Download, "tab_packs")
}

class MainActivity : ComponentActivity() {

    private val viewModel: TranslatorViewModel by viewModels()

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val uiState by viewModel.uiState.collectAsState()

            MyApplicationTheme(themeMode = uiState.themeMode) {
                var selectedTab by rememberSaveable { mutableIntStateOf(0) }

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        CenterAlignedTopAppBar(
                            title = {
                                Text(
                                    text = "Offline Translator",
                                    style = MaterialTheme.typography.titleLarge.copy(
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 20.sp
                                    ),
                                    color = MaterialTheme.colorScheme.onBackground
                                )
                            },
                            actions = {
                                // Manual Rate / Feedback Action
                                IconButton(
                                    onClick = { viewModel.triggerReviewPromptManually() },
                                    modifier = Modifier.testTag("review_menu_button")
                                ) {
                                    Icon(
                                        imageVector = Icons.Outlined.RateReview,
                                        contentDescription = "Rate and Feedback",
                                        tint = MaterialTheme.colorScheme.onBackground
                                    )
                                }

                                // Theme Toggle
                                IconButton(
                                    onClick = {
                                        val nextMode = when (uiState.themeMode) {
                                            AppThemeMode.DARK -> AppThemeMode.LIGHT
                                            AppThemeMode.LIGHT -> AppThemeMode.SYSTEM
                                            AppThemeMode.SYSTEM -> AppThemeMode.DARK
                                        }
                                        viewModel.setThemeMode(nextMode)
                                    },
                                    modifier = Modifier.testTag("theme_toggle_button")
                                ) {
                                    val icon = when (uiState.themeMode) {
                                        AppThemeMode.DARK -> Icons.Outlined.DarkMode
                                        AppThemeMode.LIGHT -> Icons.Outlined.LightMode
                                        AppThemeMode.SYSTEM -> Icons.Outlined.SettingsBrightness
                                    }
                                    Icon(
                                        imageVector = icon,
                                        contentDescription = "Toggle Theme (${uiState.themeMode.name})",
                                        tint = MaterialTheme.colorScheme.onBackground
                                    )
                                }
                            },
                            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                                containerColor = MaterialTheme.colorScheme.background
                            )
                        )
                    },
                    bottomBar = {
                        NavigationBar(
                            containerColor = MaterialTheme.colorScheme.surface,
                            tonalElevation = 6.dp,
                            modifier = Modifier.testTag("main_bottom_nav")
                        ) {
                            TranslatorNavTab.values().forEachIndexed { index, tab ->
                                val isSelected = selectedTab == index
                                NavigationBarItem(
                                    selected = isSelected,
                                    onClick = { selectedTab = index },
                                    icon = {
                                        Icon(
                                            imageVector = tab.icon,
                                            contentDescription = tab.title,
                                            modifier = Modifier.size(22.dp)
                                        )
                                    },
                                    label = {
                                        Text(
                                            text = tab.title,
                                            style = MaterialTheme.typography.labelSmall.copy(
                                                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                                            )
                                        )
                                    },
                                    colors = NavigationBarItemDefaults.colors(
                                        selectedIconColor = MaterialTheme.colorScheme.primary,
                                        selectedTextColor = MaterialTheme.colorScheme.primary,
                                        indicatorColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.6f),
                                        unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                                        unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant
                                    ),
                                    modifier = Modifier.testTag(tab.tag)
                                )
                            }
                        }
                    }
                ) { innerPadding ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    ) {
                        when (selectedTab) {
                            0 -> TranslateScreen(viewModel = viewModel)
                            1 -> ConversationScreen(viewModel = viewModel)
                            2 -> PhrasebookScreen(
                                viewModel = viewModel,
                                onNavigateToTranslate = { selectedTab = 0 }
                            )
                            3 -> HistoryScreen(
                                viewModel = viewModel,
                                onSelectTranslation = { item ->
                                    viewModel.selectHistoryItem(item)
                                    selectedTab = 0
                                }
                            )
                            4 -> LanguagePacksScreen(viewModel = viewModel)
                        }

                        // In-App Review Prompt Dialog triggered on milestone
                        if (uiState.showReviewPrompt) {
                            InAppReviewPrompt(
                                translationCount = uiState.translationMilestoneCount,
                                onDismiss = { viewModel.dismissReviewPrompt() },
                                onSubmitReview = { rating, feedback ->
                                    viewModel.submitReview(rating, feedback)
                                },
                                onDeclinePermanently = {
                                    viewModel.declineReviewPermanently()
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}
