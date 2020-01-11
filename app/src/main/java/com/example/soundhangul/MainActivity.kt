package com.example.soundhangul

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


    consonant.setOnClickListener{
    	var nextConsonant = Intent(this, PracticeConsonant::class.java)
	startActivity(nextConsonant)
    }
}
