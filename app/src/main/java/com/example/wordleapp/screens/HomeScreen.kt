package com.example.wordleapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.wordleapp.navigation.Screen

@Composable
fun LetterBox(letter: String, background: Color) {
    Box(
        modifier = Modifier
            .size(45.dp)
            .background(background),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = letter,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("WORDLE", color = Color.White) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF121213)
                )
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF121213))
                .padding(padding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Encabezado WORDLE
            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                modifier = Modifier.padding(top = 16.dp, bottom = 24.dp)
            ) {
                LetterBox("W", Color(0xFF6AAA64)) // verde
                LetterBox("O", Color(0xFF787C7E)) // gris
                LetterBox("R", Color(0xFF6AAA64))
                LetterBox("D", Color(0xFFC9B458)) // amarillo
                LetterBox("L", Color(0xFF6AAA64))
                LetterBox("E", Color(0xFF787C7E))
            }

            // Título How to play
            Text(
                text = "How to play",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = Color.White,
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(bottom = 8.dp)
            )

            // Instrucciones
            Text(
                text = "Guess the Wordle in 6 tries",
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(bottom = 8.dp)
            )

            Text(
                text = "• Each guess must be a valid 5-letter word.\n" +
                        "• The color of the tiles will change to show how close your guess was to the word.",
                color = Color.White,
                fontSize = 14.sp,
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(bottom = 16.dp)
            )

            // Ejemplos
            Text(
                text = "Examples",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = Color.White,
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(bottom = 8.dp)
            )

            // WORDY Example
            Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                LetterBox("W", Color(0xFF6AAA64))
                LetterBox("O", Color(0xFF121213))
                LetterBox("R", Color(0xFF121213))
                LetterBox("D", Color(0xFF121213))
                LetterBox("Y", Color(0xFF121213))
            }
            Text(
                text = "W is in the word and in the correct spot.",
                color = Color.White,
                fontSize = 14.sp,
                modifier = Modifier.align(Alignment.Start).padding(bottom = 12.dp)
            )

            // LIGHT Example
            Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                LetterBox("L", Color(0xFF121213))
                LetterBox("I", Color(0xFFC9B458))
                LetterBox("G", Color(0xFF121213))
                LetterBox("H", Color(0xFF121213))
                LetterBox("T", Color(0xFF121213))
            }
            Text(
                text = "I is in the word but in the wrong spot.",
                color = Color.White,
                fontSize = 14.sp,
                modifier = Modifier.align(Alignment.Start).padding(bottom = 12.dp)
            )

            // ROGUE Example
            Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                LetterBox("R", Color(0xFF121213))
                LetterBox("O", Color(0xFF121213))
                LetterBox("G", Color(0xFF121213))
                LetterBox("U", Color(0xFF787C7E))
                LetterBox("E", Color(0xFF121213))
            }
            Text(
                text = "U is not in the word in any spot.",
                color = Color.White,
                fontSize = 14.sp,
                modifier = Modifier.align(Alignment.Start).padding(bottom = 24.dp)
            )

            Spacer(modifier = Modifier.weight(1f))

            // Botón: Let's play
            Button(
                onClick = { navController.navigate(Screen.Game.route) },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6AAA64)),
                modifier = Modifier.fillMaxWidth().padding(bottom = 12.dp)
            ) {
                Text("Let's play!", color = Color.White, fontSize = 18.sp)
            }

            // Botón: Leaderboard
            Button(
                onClick = { navController.navigate(Screen.Score.route) },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6AAA64)),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Leaderboard", color = Color.White, fontSize = 18.sp)
            }
        }
    }
}
