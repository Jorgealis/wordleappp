package com.example.wordleapp.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.wordleapp.navigation.Screen

data class Difficulty(val name: String, val attempts: Int)

@Composable
fun DifficultyScreen(navController: NavController) {
    val difficulties = listOf(
        Difficulty("Easy", 6),
        Difficulty("Medium", 4),
        Difficulty("Hard", 3)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Select Difficulty",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 32.dp)
        )

        difficulties.forEach { difficulty ->
            Button(
                onClick = {
                    // Navigate to category selection, passing the number of attempts.
                    navController.navigate("${Screen.CategorySelection.route}/${difficulty.attempts}")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {
                Text("${difficulty.name} (${difficulty.attempts} Attempts)")
            }
        }
    }
}