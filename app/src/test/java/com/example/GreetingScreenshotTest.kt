package com.example

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onRoot
import com.example.data.local.TranslationEntity
import com.example.data.model.Language
import com.example.ui.components.TranslationResultCard
import com.example.ui.theme.AppThemeMode
import com.example.ui.theme.MyApplicationTheme
import com.github.takahirom.roborazzi.RobolectricDeviceQualifiers
import com.github.takahirom.roborazzi.captureRoboImage
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

@RunWith(RobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(qualifiers = RobolectricDeviceQualifiers.Pixel8, sdk = [34])
class GreetingScreenshotTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun translation_card_screenshot_dark() {
        composeTestRule.setContent {
            MyApplicationTheme(themeMode = AppThemeMode.DARK) {
                Surface(modifier = Modifier.fillMaxSize()) {
                    TranslationResultCard(
                        translation = TranslationEntity(
                            sourceText = "Where is the airport?",
                            translatedText = "¿Dónde está el aeropuerto?",
                            sourceLanguageCode = "en",
                            targetLanguageCode = "es",
                            isOffline = true,
                            phonetic = "DON-deh ehs-TAH ehl ah-eh-roh-PWEHR-toh"
                        ),
                        isTranslating = false,
                        targetLanguage = Language.findByCode("es"),
                        onSpeak = {},
                        onToggleFavorite = { _, _ -> },
                        onShowFullscreen = {}
                    )
                }
            }
        }

        composeTestRule.onRoot().captureRoboImage(filePath = "src/test/screenshots/translation_card_dark.png")
    }
}
