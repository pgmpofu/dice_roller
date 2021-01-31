package com.ai.diceroller.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ai.diceroller.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editImage.setOnClickListener{
            var guessByText = Intent(this, GuessByText::class.java)
            startActivity(guessByText)
        }

        microphoneImage.setOnClickListener{
            var guessByVoice = Intent(this, GuessByVoice::class.java)
            startActivity(guessByVoice)
        }
    }


}