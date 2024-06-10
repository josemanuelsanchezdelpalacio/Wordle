package com.dam2jms.wordle.models

import android.content.Context
import android.widget.Toast
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.dam2jms.wordle.states.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class WordleState(
    val currentWord: String,
    val guessedWords: List<String> = emptyList(),
    val currentGuess: String = "",
    val gameWon: Boolean = false,
    val gameLost: Boolean = false,
    val maxAttempts: Int = 6
)

class ViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(WordleState(currentWord = "KOTLIN"))
    val uiState: StateFlow<WordleState> = _uiState.asStateFlow()

    fun makeGuess(guess: String) {
        if (guess.length != _uiState.value.currentWord.length) return

        val newGuessedWords = _uiState.value.guessedWords + guess
        val gameWon = guess.equals(_uiState.value.currentWord, ignoreCase = true)
        val gameLost = newGuessedWords.size >= _uiState.value.maxAttempts && !gameWon

        _uiState.value = _uiState.value.copy(
            guessedWords = newGuessedWords,
            currentGuess = "",
            gameWon = gameWon,
            gameLost = gameLost
        )
    }

    fun updateCurrentGuess(guess: String) {
        _uiState.value = _uiState.value.copy(currentGuess = guess)
    }
}
