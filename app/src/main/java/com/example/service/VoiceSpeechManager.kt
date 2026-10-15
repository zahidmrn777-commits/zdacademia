package com.example.service

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.Locale

sealed class VoiceInputState {
    object Idle : VoiceInputState()
    data class Listening(val rmsDb: Float = 0f) : VoiceInputState()
    object Processing : VoiceInputState()
    data class Success(val recognizedText: String) : VoiceInputState()
    data class Error(val errorMessage: String) : VoiceInputState()
}

class VoiceSpeechManager(private val context: Context) {

    private var speechRecognizer: SpeechRecognizer? = null
    private val _voiceState = MutableStateFlow<VoiceInputState>(VoiceInputState.Idle)
    val voiceState: StateFlow<VoiceInputState> = _voiceState.asStateFlow()

    fun isRecognitionAvailable(): Boolean {
        return SpeechRecognizer.isRecognitionAvailable(context)
    }

    fun startListening(speechLanguageTag: String, onResult: (String) -> Unit) {
        stopListening()

        if (!isRecognitionAvailable()) {
            _voiceState.value = VoiceInputState.Error("Speech recognition not available on this device.")
            return
        }

        try {
            speechRecognizer = SpeechRecognizer.createSpeechRecognizer(context).apply {
                setRecognitionListener(object : RecognitionListener {
                    override fun onReadyForSpeech(params: Bundle?) {
                        _voiceState.value = VoiceInputState.Listening(0f)
                    }

                    override fun onBeginningOfSpeech() {
                        _voiceState.value = VoiceInputState.Listening(0.5f)
                    }

                    override fun onRmsChanged(rmsdB: Float) {
                        if (_voiceState.value is VoiceInputState.Listening) {
                            // Normalize rmsdB for wave animation (usually -2 to 10 dB)
                            val normalized = ((rmsdB + 2f) / 12f).coerceIn(0f, 1f)
                            _voiceState.value = VoiceInputState.Listening(normalized)
                        }
                    }

                    override fun onBufferReceived(buffer: ByteArray?) {}

                    override fun onEndOfSpeech() {
                        _voiceState.value = VoiceInputState.Processing
                    }

                    override fun onError(error: Int) {
                        val message = when (error) {
                            SpeechRecognizer.ERROR_AUDIO -> "Audio recording error"
                            SpeechRecognizer.ERROR_CLIENT -> "Client error"
                            SpeechRecognizer.ERROR_INSUFFICIENT_PERMISSIONS -> "Microphone permission required"
                            SpeechRecognizer.ERROR_NETWORK -> "Network required for speech recognition"
                            SpeechRecognizer.ERROR_NETWORK_TIMEOUT -> "Network timeout"
                            SpeechRecognizer.ERROR_NO_MATCH -> "No speech recognized. Please try again."
                            SpeechRecognizer.ERROR_RECOGNIZER_BUSY -> "Voice recognizer is busy"
                            SpeechRecognizer.ERROR_SERVER -> "Server error"
                            SpeechRecognizer.ERROR_SPEECH_TIMEOUT -> "No speech detected"
                            else -> "Recognition error ($error)"
                        }
                        _voiceState.value = VoiceInputState.Error(message)
                    }

                    override fun onResults(results: Bundle?) {
                        val matches = results?.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
                        val text = matches?.firstOrNull() ?: ""
                        if (text.isNotBlank()) {
                            _voiceState.value = VoiceInputState.Success(text)
                            onResult(text)
                        } else {
                            _voiceState.value = VoiceInputState.Error("Could not recognize speech")
                        }
                    }

                    override fun onPartialResults(partialResults: Bundle?) {
                        val matches = partialResults?.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
                        val text = matches?.firstOrNull()
                        if (!text.isNullOrBlank()) {
                            // Keep listening state with text hint
                        }
                    }

                    override fun onEvent(eventType: Int, params: Bundle?) {}
                })
            }

            val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
                putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
                putExtra(RecognizerIntent.EXTRA_LANGUAGE, speechLanguageTag)
                putExtra(RecognizerIntent.EXTRA_LANGUAGE_PREFERENCE, speechLanguageTag)
                putExtra(RecognizerIntent.EXTRA_ONLY_RETURN_LANGUAGE_PREFERENCE, speechLanguageTag)
                putExtra(RecognizerIntent.EXTRA_PARTIAL_RESULTS, true)
                putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 1)
            }

            speechRecognizer?.startListening(intent)
            _voiceState.value = VoiceInputState.Listening(0f)
        } catch (e: Exception) {
            _voiceState.value = VoiceInputState.Error("Failed to start speech recognition: ${e.localizedMessage}")
        }
    }

    fun stopListening() {
        try {
            speechRecognizer?.stopListening()
            speechRecognizer?.destroy()
        } catch (e: Exception) {
            // ignore
        } finally {
            speechRecognizer = null
            if (_voiceState.value is VoiceInputState.Listening || _voiceState.value is VoiceInputState.Processing) {
                _voiceState.value = VoiceInputState.Idle
            }
        }
    }

    fun resetState() {
        _voiceState.value = VoiceInputState.Idle
    }

    fun createFallbackIntent(speechLanguageTag: String): Intent {
        return Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
            putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
            putExtra(RecognizerIntent.EXTRA_LANGUAGE, speechLanguageTag)
            putExtra(RecognizerIntent.EXTRA_PROMPT, "Speak now to translate...")
        }
    }
}
