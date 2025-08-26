package com.example.wordleapp.screens.game

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.wordleapp.components.AttemptsGrid
import com.example.wordleapp.components.Keyboard

class GameViewModelFactory(
    private val category: String,
    private val maxAttempts: Int
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GameViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return GameViewModel(category, maxAttempts) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameScreen(category: String, maxAttempts: Int) {
    val viewModel: GameViewModel = viewModel(factory = GameViewModelFactory(category, maxAttempts))

    val gameState = viewModel.gameState
    val isGameActive = gameState == GameState.PLAYING
    val score = viewModel.score
    val elapsedTime = viewModel.elapsedTime

    fun formatTime(seconds: Int): String {
        val minutes = seconds / 60
        val remainingSeconds = seconds % 60
        return "%02d:%02d".format(minutes, remainingSeconds)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(category.uppercase()) },
                actions = {
                    Row(modifier = Modifier.padding(end = 16.dp)) {
                        Text(
                            text = formatTime(elapsedTime),
                            fontSize = 18.sp,
                            modifier = Modifier.padding(end = 16.dp)
                        )
                        Text(
                            text = "Score: $score",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            AttemptsGrid(
                attempts = viewModel.attempts,
                results = viewModel.results,
                currentWord = viewModel.currentWord,
                maxAttempts = maxAttempts
            )
            Spacer(modifier = Modifier.height(16.dp))
            Keyboard(
                onKeyPressed = { viewModel.onKeyPressed(it) },
                onRemove = { viewModel.onRemoveLetter() },
                isEnabled = isGameActive
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = { viewModel.onSubmit() },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary
                ),
                shape = MaterialTheme.shapes.medium,
                enabled = isGameActive
            ) {
                Text("Submit")
            }
        }
    }

    if (gameState != GameState.PLAYING) {
        GameResultDialog(
            gameState = gameState,
            solution = viewModel.solution,
            onPlayAgain = {
                viewModel.resetGame()
            }
        )
    }
}

@Composable
fun GameResultDialog(
    gameState: GameState,
    solution: String,
    onPlayAgain: () -> Unit
) {
    val title = if (gameState == GameState.WON) "¡Felicidades!" else "¡Fin del juego!"
    val message = if (gameState == GameState.WON) "¡Has adivinado la palabra!" else "La palabra correcta era: $solution"

    AlertDialog(
        onDismissRequest = { onPlayAgain() },
        title = { Text(text = title, fontSize = 24.sp, fontWeight = FontWeight.Bold) },
        text = { Text(text = message, fontSize = 18.sp) },
        confirmButton = {
            Button(onClick = onPlayAgain) {
                Text("Jugar de nuevo")
            }
        }
    )
}