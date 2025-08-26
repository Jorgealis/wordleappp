package com.example.wordleapp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.wordleapp.screens.game.LetterStatus

@Composable
fun AttemptsGrid(
    attempts: List<String>,
    results: List<List<LetterStatus>>,
    currentWord: String,
    maxAttempts: Int // New parameter
) {
    Column {
        repeat(maxAttempts) { rowIndex -> // Use maxAttempts instead of hardcoded 6
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                val word = when {
                    rowIndex < attempts.size -> attempts[rowIndex]
                    rowIndex == attempts.size -> currentWord
                    else -> ""
                }

                val rowResults = results.getOrNull(rowIndex)

                repeat(5) { colIndex ->
                    val letter = word.getOrNull(colIndex)?.toString() ?: ""

                    val background = when (rowResults?.getOrNull(colIndex)) {
                        LetterStatus.CORRECT -> Color(0xFF6AAA64)
                        LetterStatus.PRESENT -> Color(0xFFC9B458)
                        LetterStatus.ABSENT -> Color(0xFF787C7E)
                        null -> Color.Black
                    }

                    Box(
                        modifier = Modifier
                            .padding(2.dp)
                            .size(48.dp)
                            .border(2.dp, Color.Gray)
                            .background(background),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = letter,
                            style = MaterialTheme.typography.bodyLarge,
                            color = Color.White
                        )
                    }
                }
            }
        }
    }
}