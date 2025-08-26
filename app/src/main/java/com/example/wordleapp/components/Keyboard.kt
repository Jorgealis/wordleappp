package com.example.wordleapp.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun Keyboard(
    onKeyPressed: (Char) -> Unit,
    onRemove: () -> Unit,
    isEnabled: Boolean
) {
    val rows = listOf(
        "QWERTYUIOP",
        "ASDFGHJKLÑ",
        "ZXCVBNM"
    )

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        rows.forEach { row ->
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                row.forEach { letter ->
                    Button(
                        onClick = { onKeyPressed(letter) },
                        modifier = Modifier
                            .padding(2.dp)
                            .width(36.dp)
                            .height(48.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF818384),
                            contentColor = Color.White
                        ),
                        shape = RoundedCornerShape(6.dp),
                        contentPadding = PaddingValues(0.dp),
                        enabled = isEnabled
                    ) {
                        Text(letter.toString())
                    }
                }
            }
            Spacer(modifier = Modifier.height(4.dp))
        }

        Button(
            onClick = { onRemove() },
            modifier = Modifier
                .padding(2.dp)
                .height(48.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF818384),
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(6.dp),
            enabled = isEnabled
        ) {
            Text("⌫")
        }
    }
}