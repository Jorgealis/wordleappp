package com.example.wordleapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.wordleapp.screens.CategorySelectionScreen
import com.example.wordleapp.screens.DifficultyScreen
import com.example.wordleapp.screens.HomeScreen
import com.example.wordleapp.screens.ScoreScreen
import com.example.wordleapp.screens.game.GameScreen

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Game : Screen("game")
    object Score : Screen("score")
    object CategorySelection : Screen("category_selection")
    object Difficulty : Screen("difficulty") // New screen
}

@Composable
fun AppNavigation(navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        startDestination = Screen.Difficulty.route // New starting destination
    ) {
        composable(Screen.Home.route) { HomeScreen(navController) }
        composable(Screen.Difficulty.route) { DifficultyScreen(navController) }

        composable(
            route = "${Screen.CategorySelection.route}/{attempts}",
            arguments = listOf(navArgument("attempts") { type = NavType.IntType })
        ) { backStackEntry ->
            val attempts = backStackEntry.arguments?.getInt("attempts") ?: 6 // Default to Easy
            CategorySelectionScreen(navController, attempts = attempts)
        }

        composable(
            route = "${Screen.Game.route}/{category}/{attempts}",
            arguments = listOf(
                navArgument("category") { type = NavType.StringType },
                navArgument("attempts") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val category = backStackEntry.arguments?.getString("category") ?: "General"
            val attempts = backStackEntry.arguments?.getInt("attempts") ?: 6 // Default to Easy
            GameScreen(category = category, maxAttempts = attempts)
        }

        composable(Screen.Score.route) { ScoreScreen(navController) }
    }
}