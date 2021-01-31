package com.ai.diceroller.ui

import android.os.Bundle
import android.text.Editable
import android.widget.Toast.*
import androidx.appcompat.app.AppCompatActivity
import com.ai.diceroller.R
import kotlinx.android.synthetic.main.activity_guess_by_text.*
import java.lang.NumberFormatException
import java.util.*

class GuessByText : AppCompatActivity() {
//    var validInput:Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guess_by_text)
        scoreValue.text = 0.toString()
        guessButton.setOnClickListener {
                guess()
        }
    }

    private fun guess() {
        try {
        if (isValidInput()) {
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

            if (randomNumber == textInput.text.toString().toInt()) {
                makeText(applicationContext, R.string.correct_guess, LENGTH_LONG).show()
                scoreValue.text = Editable.Factory.getInstance().newEditable(
                    scoreValue.text.toString()
                        .toInt().plus(1).toString()
                )
            } else {
                makeText(applicationContext, R.string.incorrect_guess, LENGTH_LONG).show()
            }

        }
    } catch (e: NumberFormatException) {
        textInput.error = resources.getString(R.string.enter_number)
    }

    }

    private fun isValidInput(): Boolean {
        var input = textInput.text.toString().toInt()
        if(input < 0 || input > 6) {
            textInput.error = resources.getString(R.string.enter_number)
            return false
        }
        return true
    }
}