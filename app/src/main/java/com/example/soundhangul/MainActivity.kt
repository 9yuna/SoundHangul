package com.example.soundhangul

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val width = resources.displayMetrics.widthPixels
        val height = resources.displayMetrics.heightPixels

        //버튼 사이즈 조절
        consonant.setLayoutParams(ConstraintLayout.LayoutParams(width/5,height/4))
        vowel.setLayoutParams(ConstraintLayout.LayoutParams(width/5,height/4))
        soundcard.setLayoutParams(ConstraintLayout.LayoutParams(width/5,height/4))
        makeWord.setLayoutParams(ConstraintLayout.LayoutParams(width/5,height/4))

        //버튼 위치 조정
        val consonantParams = consonant.layoutParams as ConstraintLayout.LayoutParams
        consonantParams.topToTop = R.id.main
        consonantParams.topMargin = height/4
        consonantParams.startToStart = R.id.main
        consonantParams.marginStart = width/5
        consonantParams.bottomToTop = R.id.soundcard
        consonantParams.bottomMargin = height/10
        consonantParams.endToStart = R.id.vowel
        consonantParams.marginEnd = width/11
        consonant.requestLayout()

        val vowelParams = vowel.layoutParams as ConstraintLayout.LayoutParams
        vowelParams.topToTop = R.id.main
        vowelParams.topMargin = height/4
        vowelParams.endToEnd = R.id.main
        vowelParams.marginEnd = width/5
        vowelParams.startToEnd = R.id.consonant
        vowelParams.marginStart = width/11
        vowelParams.bottomToTop = R.id.makeWord
        vowelParams.bottomMargin = height/10
        vowel.requestLayout()

        val soundCardParams = soundcard.layoutParams as ConstraintLayout.LayoutParams
        soundCardParams.startToStart = R.id.main
        soundCardParams.marginStart = width/5
        soundCardParams.bottomToBottom = R.id.main
        soundCardParams.bottomMargin = height/4
        soundCardParams.topToBottom = R.id.consonant
        soundCardParams.topMargin = height/10
        soundCardParams.endToStart = R.id.makeWord
        soundCardParams.marginEnd = width/11
        soundcard.requestLayout()

        val makeWordParams = makeWord.layoutParams as ConstraintLayout.LayoutParams
        makeWordParams.startToEnd = R.id.soundcard
        makeWordParams.marginStart = width/11
        makeWordParams.topToBottom = R.id.vowel
        makeWordParams.topMargin = height/10
        makeWordParams.bottomToBottom = R.id.main
        makeWordParams.bottomMargin = height/4
        makeWordParams.endToEnd = R.id.main
        makeWordParams.marginEnd = width/5
        vowel.requestLayout()


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
