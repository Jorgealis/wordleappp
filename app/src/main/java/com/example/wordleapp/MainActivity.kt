package com.example.wordleapp
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.wordleapp.navigation.AppNavigation
import com.example.wordleapp.ui.theme.WordleAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WordleAppTheme {
                AppNavigation()
            }
        }
    }
}