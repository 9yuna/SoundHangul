package com.example.soundhangul

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import kotlinx.android.synthetic.main.activity_practice_consonant.*

class PracticeConsonant : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_practice_consonant)

        val width = resources.displayMetrics.widthPixels
        val height = resources.displayMetrics.heightPixels

        //버튼 사이즈 조절
        sequence.setLayoutParams(ConstraintLayout.LayoutParams(width/5,height/4))
        random.setLayoutParams(ConstraintLayout.LayoutParams(width/5,height/4))

        //버튼 위치 조정
        val sequenceParams = sequence.layoutParams as ConstraintLayout.LayoutParams
        sequenceParams.topToTop = R.id.practiceConsonant
        sequenceParams.topMargin = height/3
        sequenceParams.startToStart = R.id.practiceConsonant
        sequenceParams.marginStart = width/4
        sequenceParams.bottomToBottom = R.id.practiceConsonant
        sequenceParams.bottomMargin = height/3
        sequenceParams.endToStart = R.id.random
        sequenceParams.marginEnd = width/8
        sequence.requestLayout()

        val randomParams = random.layoutParams as ConstraintLayout.LayoutParams
        randomParams.topToTop = R.id.practiceConsonant
        randomParams.topMargin = height/3
        randomParams.startToEnd = R.id.sequence
        randomParams.marginStart = width/8
        randomParams.bottomToBottom = R.id.practiceConsonant
        randomParams.bottomMargin = height/3
        randomParams.endToEnd = R.id.practiceConsonant
        randomParams.marginEnd = width/4
        random.requestLayout()

        sequence.setOnClickListener {
            var nextSequence = Intent(this, SequenceConsonant::class.java)
            startActivity(nextSequence)
        }

        random.setOnClickListener {
            var nextRandom = Intent(this, RandomConsonant::class.java)
            startActivity(nextRandom)
        }
    }

}
