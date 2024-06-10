package com.dam2jms.wordle.states

import androidx.compose.ui.graphics.Color

data class UiState(
    val wordToGuess: String = generateRandomWord(),
    val currentGuess: String = "",
    val guesses: List<String> = List(5) { "" },
    val colors: List<List<Color>> = List(5) { List(5) { Color.Gray } },
    val puntuacion: Int = 0,
    val gameOver: Boolean = false,
    val revealWord: Boolean = false
)

fun generateRandomWord(): String {
    val words = listOf("apple", "grape", "berry", "lemon", "melon")
    return words.random()
}
