package com.example.ui.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Layers
import androidx.compose.material.icons.filled.SwapHoriz
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.Language

@Composable
fun LanguageSelectorBar(
    sourceLanguage: Language,
    targetLanguage: Language,
    downloadedPackCodes: Set<String>,
    onSourceLanguageSelected: (Language) -> Unit,
    onTargetLanguageSelected: (Language) -> Unit,
    onSwapLanguages: () -> Unit,
    modifier: Modifier = Modifier
) {
    var rotationAngle by remember { mutableFloatStateOf(0f) }
    val animatedRotation by animateFloatAsState(
        targetValue = rotationAngle,
        animationSpec = tween(durationMillis = 300),
        label = "swap_rotation"
    )

    var showBottomSheet by remember { mutableStateOf(false) }
    var bottomSheetInitialIsSource by remember { mutableStateOf(true) }

    Column(modifier = modifier.fillMaxWidth()) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(20.dp)),
            color = MaterialTheme.colorScheme.surfaceVariant,
            tonalElevation = 2.dp
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Source Language Dropdown Menu
                LanguageDropdownSelector(
                    selectedLanguage = sourceLanguage,
                    oppositeLanguage = targetLanguage,
                    label = "From",
                    downloadedPackCodes = downloadedPackCodes,
                    onLanguageSelected = onSourceLanguageSelected,
                    testTag = "source_language_dropdown",
                    onOpenBottomSheet = {
                        bottomSheetInitialIsSource = true
                        showBottomSheet = true
                    },
                    modifier = Modifier.weight(1f)
                )

                // Central Controls: Swap Button & Full Sheet Launcher Button
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.padding(horizontal = 2.dp)
                ) {
                    // Swap Button
                    IconButton(
                        onClick = {
                            rotationAngle += 180f
                            onSwapLanguages()
                        },
                        modifier = Modifier
                            .size(42.dp)
                            .rotate(animatedRotation)
                            .testTag("swap_languages_button")
                    ) {
                        Icon(
                            imageVector = Icons.Default.SwapHoriz,
                            contentDescription = "Swap Languages",
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(26.dp)
                        )
                    }

                    // Quick Sheet Trigger Button
                    IconButton(
                        onClick = {
                            bottomSheetInitialIsSource = false
                            showBottomSheet = true
                        },
                        modifier = Modifier
                            .size(24.dp)
                            .testTag("open_bottom_sheet_selector")
                    ) {
                        Icon(
                            imageVector = Icons.Default.Layers,
                            contentDescription = "Open Language Bottom Sheet",
                            tint = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f),
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }

                // Target Language Dropdown Menu
                LanguageDropdownSelector(
                    selectedLanguage = targetLanguage,
                    oppositeLanguage = sourceLanguage,
                    label = "To",
                    downloadedPackCodes = downloadedPackCodes,
                    onLanguageSelected = onTargetLanguageSelected,
                    testTag = "target_language_dropdown",
                    onOpenBottomSheet = {
                        bottomSheetInitialIsSource = false
                        showBottomSheet = true
                    },
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }

    // Modal Bottom Sheet Language Selector
    if (showBottomSheet) {
        LanguagePickerBottomSheet(
            sourceLanguage = sourceLanguage,
            targetLanguage = targetLanguage,
            downloadedPackCodes = downloadedPackCodes,
            initialIsSourceSelection = bottomSheetInitialIsSource,
            onSourceLanguageSelected = onSourceLanguageSelected,
            onTargetLanguageSelected = onTargetLanguageSelected,
            onSwapLanguages = onSwapLanguages,
            onDismissRequest = { showBottomSheet = false }
        )
    }
}


