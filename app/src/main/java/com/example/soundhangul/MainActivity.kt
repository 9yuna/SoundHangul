package com.example.soundhangul

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        consonant.setOnClickListener{
            val nextConsonant = Intent(this@MainActivity, PracticeConsonant::class.java)
            startActivity(nextConsonant)
        }

        vowel.setOnClickListener{
            val nextVowel = Intent(this@MainActivity, PracticeVowel::class.java)
            startActivity(nextVowel)
        }

        soundcard.setOnClickListener {
            val nextReadSound = Intent(this@MainActivity, SoundCard::class.java)
            startActivity(nextReadSound)
        }

        makeWord.setOnClickListener {
            val nextMakeWord = Intent(this@MainActivity, MakeWord::class.java)
            startActivity(nextMakeWord)
        }
        
    }
}
