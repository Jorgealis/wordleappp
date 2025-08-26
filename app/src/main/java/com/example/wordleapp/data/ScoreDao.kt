package com.example.wordleapp.data
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ScoreDao {
    @Query("SELECT * FROM scores")
    fun getAllScores(): Flow<List<ScoreEntity>>

    @Insert
    suspend fun insertScore(score: ScoreEntity)

}