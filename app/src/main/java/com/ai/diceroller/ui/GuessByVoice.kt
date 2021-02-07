package com.ai.diceroller.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.widget.Toast
import com.ai.diceroller.R
import kotlinx.android.synthetic.main.activity_guess_by_text.*
import kotlinx.android.synthetic.main.activity_guess_by_voice.*
import kotlinx.android.synthetic.main.activity_guess_by_voice.diceImage
import kotlinx.android.synthetic.main.activity_guess_by_voice.scoreValue
import java.lang.NumberFormatException
import java.util.*

class   GuessByVoice : AppCompatActivity() {

    lateinit var guessText: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guess_by_voice)

        guessText = savedInstanceState?.get("mGuessText").toString()

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
                                .toInt().plus(1).toString()
                )
            } else {
                Toast.makeText(applicationContext, R.string.incorrect_guess, Toast.LENGTH_LONG).show()
            }
    }
}
