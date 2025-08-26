package com.example.wordleapp.data


import kotlinx.coroutines.flow.Flow
import java.util.Date
class ScoreRepository(private val scoreDao: ScoreDao) {

    fun getAllScores(): Flow<List<ScoreEntity>> = scoreDao.getAllScores()

    /**
     * Esta función ahora crea un `ScoreEntity` usando los nombres de los parámetros
     * para asegurar que cada valor va al campo correcto.
     */
    suspend fun saveScore(
        name: String,
        score: Int,
        date: Date,
        solution: String,
        attempts: List<String>
    ) {
        // ✅ CAMBIO: Creamos el ScoreEntity especificando cada parámetro por su nombre.
        // Esto evita errores si el orden de los campos cambia y hace el código más claro.
        val scoreEntity = ScoreEntity(
            name = name,
            score = score,
            date = date,
            solution = solution,
            attempts = attempts
        )
        scoreDao.insertScore(scoreEntity)
    }
}