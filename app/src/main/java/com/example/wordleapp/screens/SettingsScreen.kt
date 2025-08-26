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
fun SettingsScreen(navController: NavController) {
    val attemptOptions = listOf(3, 6, 9)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Choose Attempts",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 32.dp)
        )

        attemptOptions.forEach { attempts ->
            Button(
                onClick = {
                    // Navigate to category selection, passing the number of attempts.
                    navController.navigate("${Screen.CategorySelection.route}/$attempts")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {
                Text("$attempts Attempts")
            }
        }
    }
}