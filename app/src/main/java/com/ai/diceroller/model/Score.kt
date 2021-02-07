package com.ai.diceroller.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Score(
    @PrimaryKey(autoGenerate = true)
    val id: Int,

    @ColumnInfo(name = "mode")
    val mode : String?,

    @ColumnInfo(name = "value", defaultValue = 0.toString())
    var value: Int?
)