package com.ai.diceroller.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ai.diceroller.model.Score

@Database(entities = [Score::class], version = 1)
abstract class AppDatabase: RoomDatabase() {

    abstract fun scoreDao(): ScoreDao
}