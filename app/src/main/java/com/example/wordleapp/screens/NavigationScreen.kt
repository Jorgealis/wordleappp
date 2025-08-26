package com.example.wordleapp.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.wordleapp.navigation.Screen

@Composable
fun NavigationScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Wordle Navigation",
            style = MaterialTheme.typography.headlineMedium,
            color = Color.White
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = { navController.navigate(Screen.Home.route) },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6AAA64)),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Go to Home", color = Color.White)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { navController.navigate(Screen.Game.route) },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6AAA64)),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Go to Game", color = Color.White)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { navController.navigate(Screen.Score.route) },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6AAA64)),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Go to Leaderboard", color = Color.White)
        }
    }
}
