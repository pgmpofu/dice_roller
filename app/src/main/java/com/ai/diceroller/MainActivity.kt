package com.ai.diceroller

import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        guessButton.setOnClickListener {
            guess();
        }
        microphone.setOnClickListener{
            guessAudio();
        }
        userInput.addTextChangedListener{
            guessText()
        }
    }

    private fun guessText() {
        microphone.isVisible = false
        var input = findViewById<EditText>(R.id.userInput);
        when {
            input !is Number -> {
                Toast.makeText(applicationContext, "Input must be a valid number", Toast.LENGTH_LONG);
            }
            input.toInt() <= 0 -> {
                Toast.makeText(applicationContext, "Number must be greater than 0", Toast.LENGTH_LONG);
            }
            input.toInt() > 6 -> {
                Toast.makeText(applicationContext, "Number must be 6 or less", Toast.LENGTH_LONG);
            }
        }
    }

    private fun guessAudio() {
        userInput.isVisible = false
    }

    private fun guess() {
        val drawableResource = when (Random().nextInt(6)+1) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }
        diceImage.setImageResource(drawableResource)
    }
}