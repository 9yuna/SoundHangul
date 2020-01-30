package com.example.soundhangul

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_practice_vowel.*
import java.util.*


class PracticeVowel : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_practice_vowel)

        var go_sequence = findViewById(R.id.sequence) as Button
        go_sequence.setOnClickListener {
            var nextSequence = Intent(this, SequenceVowel::class.java)
            startActivity(nextSequence)
        }

        var go_random = findViewById(R.id.random) as Button
        go_random.setOnClickListener {
            var nextRandom = Intent(this, SequenceVowel::class.java)
            startActivity(nextRandom)
        }
    }
}