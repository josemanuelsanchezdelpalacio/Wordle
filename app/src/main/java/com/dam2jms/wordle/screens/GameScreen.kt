package com.dam2jms.wordle.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.dam2jms.wordle.models.ViewModel
import com.dam2jms.wordle.navigation.AppScreens
import com.dam2jms.wordle.states.UiState

@Composable
fun GameScreen(navController: NavController, mvvm: ViewModel) {
    val uiState by mvvm.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text("Wordle", style = MaterialTheme.typography.bodyLarge)

        uiState.guessedWords.forEach { guessedWord ->
            WordRow(word = guessedWord, correctWord = uiState.currentWord)
        }

        if (uiState.gameWon) {
            Text("You won!", color = Color.Green)
        } else if (uiState.gameLost) {
            Text("You lost! The word was ${uiState.currentWord}", color = Color.Red)
        } else {
            OutlinedTextField(
                value = uiState.currentGuess,
                onValueChange = { mvvm.updateCurrentGuess(it) },
                label = { Text("Enter your guess") },
                modifier = Modifier.fillMaxWidth()
            )
            Button(
                onClick = { mvvm.makeGuess(uiState.currentGuess) },
                enabled = uiState.currentGuess.length == uiState.currentWord.length
            ) {
                Text("Submit")
            }
        }
    }
}

@Composable
fun WordRow(word: String, correctWord: String) {
    Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
        word.forEachIndexed { index, char ->
            val color = when {
                char.equals(correctWord[index], ignoreCase = true) -> Color.Green
                correctWord.contains(char, ignoreCase = true) -> Color.Yellow
                else -> Color.Gray
            }
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(color)
                    .border(1.dp, Color.Black),
                contentAlignment = Alignment.Center
            ) {
                Text(char.toString(), color = Color.White)
            }
        }
    }
}
