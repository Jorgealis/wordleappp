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

@Composable
fun CategorySelectionScreen(navController: NavController, attempts: Int) {
    val categories = listOf("Animals", "Colors", "Sports", "General")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Select a Category",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 32.dp)
        )

        categories.forEach { category ->
            Button(
                onClick = {
                    // Navigate to the game, passing both category and attempts.
                    navController.navigate("${Screen.Game.route}/$category/$attempts")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {
                Text(category)
            }
        }
    }
}