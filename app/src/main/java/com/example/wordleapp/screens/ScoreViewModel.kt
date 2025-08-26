package com.example.wordleapp.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.wordleapp.data.ScoreEntity
import com.example.wordleapp.data.ScoreRepository
import kotlinx.coroutines.flow.Flow

class ScoreViewModel(private val repository: ScoreRepository) : ViewModel() {
    // Obtenemos todos los puntajes como un Flow. La UI se actualizará automáticamente.
    val allScores: Flow<List<ScoreEntity>> = repository.getAllScores()
}

class ScoreViewModelFactory(private val repository: ScoreRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ScoreViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ScoreViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}