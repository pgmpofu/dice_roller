package com.ai.diceroller.ui

import android.os.Bundle
import android.text.Editable
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.ai.diceroller.R
import com.ai.diceroller.database.AppDatabase
import com.ai.diceroller.database.ScoreDao
import kotlinx.android.synthetic.main.activity_guess_by_voice.*
import java.util.*

class   GuessByVoice : AppCompatActivity() {

    private lateinit var guessText: String
    private lateinit var scoreDb : AppDatabase
    private lateinit var scoreDao: ScoreDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guess_by_voice)

        guessText = savedInstanceState?.get("mGuessText").toString()
        scoreDb = Room.databaseBuilder(this, AppDatabase::class.java, "database-name").build()
        scoreDao = scoreDb.scoreDao()

        guess(guessText)

        doneButton.setOnClickListener {
            finish()
        }
    }

    private fun guess(guessText: String) {
            val randomNumber = Random().nextInt(6) + 1
            val drawableResource = when (randomNumber) {
                1 -> R.drawable.dice_1
                2 -> R.drawable.dice_2
                3 -> R.drawable.dice_3
                4 -> R.drawable.dice_4
                5 -> R.drawable.dice_5
                else -> R.drawable.dice_6
            }
            diceImage.setImageResource(drawableResource)

            if (randomNumber == guessText.toInt()) {
                Toast.makeText(applicationContext, R.string.correct_guess, Toast.LENGTH_LONG).show()
                scoreValue.text = Editable.Factory.getInstance().newEditable(
                        scoreValue.text.toString()
                                .toInt().plus(1).toString())
                var score = scoreDao.getAll()[0]
                score.value?.plus(1)
                scoreDao.update(score)
            } else {
                Toast.makeText(applicationContext, R.string.incorrect_guess, Toast.LENGTH_LONG).show()
            }
    }
}
