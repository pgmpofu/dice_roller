package com.ai.diceroller.ui

import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import android.widget.Toast
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
            val voiceIntent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
            startActivityForResult(voiceIntent, REQUEST_CODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(resultCode){
            RESULT_CANCELED -> Toast.makeText(this,"Could not catch that. Please try again", Toast.LENGTH_LONG).show()
            RESULT_OK -> {
                val stringArrayExtra = data?.getStringArrayExtra(RecognizerIntent.EXTRA_RESULTS)?.get(0)
                val guessByVoice = Intent(this, GuessByVoice::class.java)
                        .putExtra("mGuessText", stringArrayExtra)
                startActivity(guessByVoice)
            }
        }
    }

    companion object {
        const val REQUEST_CODE = 0
    }

}