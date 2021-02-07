package com.ai.diceroller.ui

import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.ai.diceroller.R
import com.ai.diceroller.database.AppDatabase
import com.ai.diceroller.database.ScoreDao
import com.ai.diceroller.model.Score
import kotlinx.android.synthetic.main.activity_guess_by_voice.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var scoreDb : AppDatabase
    private lateinit var scoreDao: ScoreDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        scoreDb = Room.databaseBuilder(this, AppDatabase::class.java, "database-name").build()
        scoreDao = scoreDb.scoreDao()

        val currentScores : List<Score> = scoreDao.getAll()
        currentScores[0].toString().also { scoreValue.text = it }

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