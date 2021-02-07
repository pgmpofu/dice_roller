package com.ai.diceroller.database

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Update
import com.ai.diceroller.model.Score

@Dao
interface ScoreDao {

    @Query("SELECT * FROM Score")
    fun getAll(): List<Score>

    @Update
    fun update(vararg score: Score)
}