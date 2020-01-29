package com.example.soundhangul

import android.content.Intent
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class PracticeConsonant : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_practice_consonant)

        var go_sequence = findViewById(R.id.sequence) as Button
        go_sequence.setOnClickListener {
            var nextSequence = Intent(this, SequenceConsonant::class.java)
            startActivity(nextSequence)
        }

        var go_random = findViewById(R.id.random) as Button
        go_random.setOnClickListener {
            var nextRandom = Intent(this, SequenceConsonant::class.java)
            startActivity(nextRandom)
        }
    }

}
