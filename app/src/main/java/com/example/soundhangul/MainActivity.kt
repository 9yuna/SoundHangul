package com.example.soundhangul

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var go_consonant = findViewById(R.id.consonant) as Button

        go_consonant.setOnClickListener{
            var nextConsonant = Intent(this, PracticeConsonant::class.java)
            startActivity(nextConsonant)
        }

	var go_vowel = findViewById(R.id.vowel) as Button
	
	go_vowel.setOnClickListener{
		var nextVowel = Intent(this, PracticeVowel::class.java)
		startActivity(nextVowel)
	}

    }



}
