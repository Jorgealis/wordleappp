package com.example.wordleapp.screens.game

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wordleapp.data.getRandomWord
import com.example.wordleapp.data.isValidWord
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class GameViewModel(
    private val category: String,
    val maxAttempts: Int
) : ViewModel() {

    companion object {
        private const val STARTING_SCORE = 1000
        private const val ABSENT_PENALTY = 50
        private const val PRESENT_PENALTY = 25
    }

    var solution by mutableStateOf("")
        private set
    var currentWord by mutableStateOf("")
        private set
    var attempts by mutableStateOf(listOf<String>())
        private set
    var results by mutableStateOf(listOf<List<LetterStatus>>())
        private set
    var gameState by mutableStateOf(GameState.PLAYING)
        private set
    var score by mutableStateOf(STARTING_SCORE)
        private set
    var elapsedTime by mutableStateOf(0)
        private set
    private var timerJob: Job? = null

    init {
        resetGame()
    }

    fun onKeyPressed(letter: Char) {
        if (currentWord.length < 5 && gameState == GameState.PLAYING) {
            currentWord += letter
        }
    }

    fun onRemoveLetter() {
        if (currentWord.isNotEmpty()) {
            currentWord = currentWord.dropLast(1)
        }
    }

    fun onSubmit() {
        if (currentWord.length == 5 && isValidWord(currentWord)) {
            val submittedWord = currentWord
            val newResult = evaluateWord(submittedWord)
            attempts = attempts + submittedWord
            results = results + listOf(newResult)
            updateScore(newResult)
            currentWord = ""

            if (submittedWord == solution) {
                gameState = GameState.WON
                stopTimer()
            } else if (attempts.size == maxAttempts) {
                gameState = GameState.LOST
                stopTimer()
            }
        }
    }

    fun resetGame() {
        solution = getRandomWord(category)
        attempts = emptyList()
        results = emptyList()
        currentWord = ""
        gameState = GameState.PLAYING
        score = STARTING_SCORE
        startTimer()
    }

    private fun startTimer() {
        timerJob?.cancel()
        elapsedTime = 0
        timerJob = viewModelScope.launch {
            while (true) {
                delay(1000)
                elapsedTime++
            }
        }
    }

    private fun stopTimer() {
        timerJob?.cancel()
    }

    private fun updateScore(result: List<LetterStatus>) {
        var newScore = score
        result.forEach { status ->
            when (status) {
                LetterStatus.ABSENT -> newScore -= ABSENT_PENALTY
                LetterStatus.PRESENT -> newScore -= PRESENT_PENALTY
                LetterStatus.CORRECT -> { /* No points deducted */ }
            }
        }
        score = newScore.coerceAtLeast(0)
    }

    private fun evaluateWord(word: String): List<LetterStatus> {
        val statuses = MutableList<LetterStatus?>(5) { null }
        val solutionLetters = solution.toMutableList()
        val wordLetters = word.toMutableList()

        for (i in 0 until 5) {
            if (wordLetters[i] == solutionLetters[i]) {
                statuses[i] = LetterStatus.CORRECT
                solutionLetters[i] = ' '
                wordLetters[i] = ' '
            }
        }

        for (i in 0 until 5) {
            if (wordLetters[i] != ' ' && wordLetters[i] in solutionLetters) {
                statuses[i] = LetterStatus.PRESENT
                solutionLetters[solutionLetters.indexOf(wordLetters[i])] = ' '
            }
        }
        return statuses.map { it ?: LetterStatus.ABSENT }
    }
}