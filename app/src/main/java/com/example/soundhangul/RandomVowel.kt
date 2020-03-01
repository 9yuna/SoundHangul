package com.example.soundhangul

import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.speech.tts.TextToSpeech
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import kotlinx.android.synthetic.main.activity_ramdom_vowel.*
import java.util.*

class RandomVowel : AppCompatActivity() {
    var mo = arrayOf("ㅏ", "ㅑ", "ㅓ", "ㅕ", "ㅗ","ㅛ","ㅜ", "ㅠ", "ㅡ", "ㅣ", "ㅐ", "ㅒ", "ㅔ", "ㅖ", "ㅘ", "ㅙ", "ㅚ","ㅝ","ㅞ","ㅟ","ㅢ")
    var moStr = arrayOf("아", "야", "어", "여", "오","요","우", "유", "으", "이", "애", "얘", "에", "예", "와", "왜", "외","워","웨","위","의")
    var rnum = intArrayOf(0, 0, 0, 0)

    var tts: TextToSpeech? = null

    fun findImage(i: Int): Int{
        var s = "mo" + i.toString()
        var drawableResId = resources.getIdentifier(s, "drawable", this.getPackageName())

        return drawableResId
    }

    fun randomButton(hubo: Int){
        var rnd = Random()

        var cnt:Int = 0
        var isSame = false
        var yesMo = false

        while(true){
            if(cnt==4) break
            rnum[cnt] = rnd.nextInt(20)
            if(rnum[cnt] == hubo) yesMo = true

            for (j: Int in 0..cnt-1) {
                if (rnum[cnt] == rnum[j]) {
                    cnt--
                    isSame = true
                    break
                }
            }

            if(isSame == false)
                cnt++
            else
                isSame = false
        }

        if(yesMo == false){
            var r = rnd.nextInt(4)
            rnum[r] = hubo
        }


    }

    fun setNextMoum(bigMoum: ImageView, pronunciation: TextView, btn1 : Button, btn2 : Button, btn3 : Button, btn4 : Button, moNum : Int){
        bigMoum.setImageResource(findImage(moNum+1))
        pronunciation.setText(moStr[moNum])
        randomButton(moNum)
        btn1.setText(mo[rnum[0]])
        btn2.setText(mo[rnum[1]])
        btn3.setText(mo[rnum[2]])
        btn4.setText(mo[rnum[3]])
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ramdom_vowel)

        val width = resources.displayMetrics.widthPixels
        val height = resources.displayMetrics.heightPixels

        //버튼 사이즈 조절
        problemImg.setLayoutParams(ConstraintLayout.LayoutParams(width/4,height/3))
        Pronunciation.setLayoutParams(ConstraintLayout.LayoutParams(width/7,height/4))
        answer1Btn.setLayoutParams(ConstraintLayout.LayoutParams(width/7,height/6))
        answer2Btn.setLayoutParams(ConstraintLayout.LayoutParams(width/7,height/6))
        answer3Btn.setLayoutParams(ConstraintLayout.LayoutParams(width/7,height/6))
        answer4Btn.setLayoutParams(ConstraintLayout.LayoutParams(width/7,height/6))

        //버튼 위치 조정
        val problemParams = problemImg.layoutParams as ConstraintLayout.LayoutParams
        problemParams.topToTop = R.id.randomVowel
        problemParams.topMargin = height/10
        problemParams.startToStart = R.id.randomVowel
        problemParams.marginStart = width/5
        problemParams.bottomToTop = R.id.answer1Btn
        problemParams.endToStart = R.id.Pronunciation
        problemParams.marginEnd = width/13
        problemImg.requestLayout()

        val pronunciationParams = Pronunciation.layoutParams as ConstraintLayout.LayoutParams
        pronunciationParams.topToTop = R.id.randomVowel
        pronunciationParams.topMargin = height/8
        pronunciationParams.startToEnd = R.id.problemImg
        pronunciationParams.marginStart = width/13
        pronunciationParams.bottomToTop = R.id.answer3Btn
        pronunciationParams.bottomMargin = height/13
        pronunciationParams.endToEnd = R.id.randomVowel
        pronunciationParams.marginEnd = width/5
        Pronunciation.requestLayout()

        val answer1Params = answer1Btn.layoutParams as ConstraintLayout.LayoutParams
        answer1Params.topToBottom = R.id.problemImg
        answer1Params.topMargin = height/10
        answer1Params.startToStart = R.id.randomVowel
        answer1Params.marginStart = width/7
        answer1Params.bottomToBottom = R.id.randomVowel
        answer1Params.bottomMargin = height/8
        answer1Params.endToStart = R.id.answer2Btn
        answer1Params.marginEnd = width/20
        answer1Btn.requestLayout()

        val answer2Params = answer2Btn.layoutParams as ConstraintLayout.LayoutParams
        answer2Params.topToBottom = R.id.problemImg
        answer2Params.topMargin = height/10
        answer2Params.startToEnd = R.id.answer1Btn
        answer2Params.marginStart = width/20
        answer2Params.bottomToBottom = R.id.randomVowel
        answer2Params.bottomMargin = height/8
        answer2Params.endToStart = R.id.answer3Btn
        answer2Params.marginEnd = width/20
        answer2Btn.requestLayout()

        val answer3Params = answer3Btn.layoutParams as ConstraintLayout.LayoutParams
        answer3Params.topToBottom = R.id.problemImg
        answer3Params.topMargin = height/10
        answer3Params.startToEnd = R.id.answer2Btn
        answer3Params.marginStart = width/20
        answer3Params.bottomToBottom = R.id.randomVowel
        answer3Params.bottomMargin = height/8
        answer3Params.endToStart = R.id.answer4Btn
        answer3Params.marginEnd = width/20
        answer3Btn.requestLayout()

        val answer4Params = answer4Btn.layoutParams as ConstraintLayout.LayoutParams
        answer4Params.topToBottom = R.id.problemImg
        answer4Params.topMargin = height/10
        answer4Params.startToEnd = R.id.answer3Btn
        answer4Params.marginStart = width/20
        answer4Params.bottomToBottom = R.id.randomVowel
        answer4Params.bottomMargin = height/8
        answer4Params.endToEnd = R.id.randomVowel
        answer4Params.marginEnd = width/7
        answer4Btn.requestLayout()


        var rnd = Random()
        var moNum:Int = rnd.nextInt(20)
        var nextmo:Int = rnd.nextInt(20)

        tts = TextToSpeech(this, TextToSpeech.OnInitListener{
            if(it == TextToSpeech.SUCCESS) {
                val result: Int = tts!!.setLanguage(Locale.KOREA)
                if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                    Toast.makeText(this, "이 언어는 지원하지 않습니다.", Toast.LENGTH_SHORT).show()
                } else {
                    tts!!.setPitch(0.6f)
                    tts!!.setSpeechRate(1.0f)
                }
            }
        })

        setNextMoum(problemImg, Pronunciation, answer1Btn, answer2Btn, answer3Btn, answer4Btn, moNum)
        tts!!.speak(moStr[moNum], TextToSpeech.QUEUE_FLUSH, null,null)

        answer1Btn.setOnClickListener{
            if(moNum == rnum[0]){
                tts!!.speak(moStr[rnum[0]], TextToSpeech.QUEUE_FLUSH, null,null)
                nextmo = rnd.nextInt(20)
                while(nextmo == moNum){
                    nextmo = rnd.nextInt(20)
                }
                moNum = nextmo
                Thread.sleep(1000L)
                setNextMoum(problemImg, Pronunciation, answer1Btn, answer2Btn, answer3Btn, answer4Btn, nextmo)
                Thread.sleep(1000L)
                tts!!.speak(moStr[moNum], TextToSpeech.QUEUE_FLUSH, null,null)
            }else{
                answer1Btn.setTextColor(Color.RED)
                tts!!.speak(moStr[rnum[0]], TextToSpeech.QUEUE_FLUSH, null,null)
                Handler().postDelayed({
                    answer1Btn.setTextColor(Color.BLACK)
                }, 200)
            }
        }

        answer2Btn.setOnClickListener{
            if(moNum == rnum[1]){
                tts!!.speak(moStr[rnum[1]], TextToSpeech.QUEUE_FLUSH, null,null)
                nextmo = rnd.nextInt(20)
                while(nextmo == moNum){
                    nextmo = rnd.nextInt(20)
                }
                moNum = nextmo
                Thread.sleep(1000L)
                setNextMoum(problemImg, Pronunciation, answer1Btn, answer2Btn, answer3Btn, answer4Btn, nextmo)
                Thread.sleep(1000L)
                tts!!.speak(moStr[moNum], TextToSpeech.QUEUE_FLUSH, null,null)
            }else{
                answer2Btn.setTextColor(Color.RED)
                tts!!.speak(moStr[rnum[1]], TextToSpeech.QUEUE_FLUSH, null,null)
                Handler().postDelayed({
                    answer2Btn.setTextColor(Color.BLACK)
                }, 200)
            }
        }
        answer3Btn.setOnClickListener{
            if(moNum == rnum[2]){
                tts!!.speak(moStr[rnum[2]], TextToSpeech.QUEUE_FLUSH, null,null)
                nextmo = rnd.nextInt(20)
                while(nextmo == moNum){
                    nextmo = rnd.nextInt(20)
                }
                moNum = nextmo
                Thread.sleep(1000L)
                setNextMoum(problemImg, Pronunciation, answer1Btn, answer2Btn, answer3Btn, answer4Btn, nextmo)
                Thread.sleep(1000L)
                tts!!.speak(moStr[moNum], TextToSpeech.QUEUE_FLUSH, null,null)
            }else{
                answer3Btn.setTextColor(Color.RED)
                tts!!.speak(moStr[rnum[2]], TextToSpeech.QUEUE_FLUSH, null,null)
                Handler().postDelayed({
                    answer3Btn.setTextColor(Color.BLACK)
                }, 200)
            }
        }
        answer4Btn.setOnClickListener{
            if(moNum == rnum[3]){
                tts!!.speak(moStr[rnum[3]], TextToSpeech.QUEUE_FLUSH, null,null)
                nextmo = rnd.nextInt(20)
                while(nextmo == moNum){
                    nextmo = rnd.nextInt(20)
                }
                moNum = nextmo
                Thread.sleep(1000L)
                setNextMoum(problemImg, Pronunciation, answer1Btn, answer2Btn, answer3Btn, answer4Btn, nextmo)
                Thread.sleep(1000L)
                tts!!.speak(moStr[moNum], TextToSpeech.QUEUE_FLUSH, null,null)
            }else{
                answer4Btn.setTextColor(Color.RED)
                tts!!.speak(moStr[rnum[3]], TextToSpeech.QUEUE_FLUSH, null,null)
                Handler().postDelayed({
                    answer4Btn.setTextColor(Color.BLACK)
                }, 200)
            }
        }

    }
}