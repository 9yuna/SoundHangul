package com.example.soundhangul

import android.graphics.Color.BLACK
import android.graphics.Color.RED
import android.os.Bundle
import android.os.Handler
import android.speech.tts.TextToSpeech
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class SequenceVowel : AppCompatActivity() {
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

    fun setNextMoum(bigMoum: ImageView, pronunciation: TextView, btn1 :Button, btn2 :Button, btn3 :Button, btn4 :Button, moNum : Int){
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
        setContentView(R.layout.activity_sequence_vowel)

        var btn1 = findViewById(R.id.answer1_btn) as Button
        var btn2 = findViewById(R.id.answer2_btn) as Button
        var btn3 = findViewById(R.id.answer3_btn) as Button
        var btn4 = findViewById(R.id.answer4_btn) as Button
        var bigMoum = findViewById(R.id.problem_img) as ImageView
        var pronunciation = findViewById(R.id.Pronunciation) as TextView
        var moNum:Int = 0

        tts = TextToSpeech(this, TextToSpeech.OnInitListener{
            if(it == TextToSpeech.SUCCESS) {
                val result: Int = tts!!.setLanguage(Locale.KOREA)
                if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                    Toast.makeText(this, "이 언어는 지원하지 않습니다.", Toast.LENGTH_SHORT).show()
                } else {
                    tts!!.setPitch(0.7f)
                    tts!!.setSpeechRate(1.2f)
                }
            }
        })

        setNextMoum(bigMoum, pronunciation, btn1, btn2, btn3, btn4, moNum)
        tts!!.speak(moStr[moNum], TextToSpeech.QUEUE_FLUSH, null,null)

        btn1.setOnClickListener{
            if(moNum == rnum[0]){
                tts!!.speak(moStr[rnum[0]], TextToSpeech.QUEUE_FLUSH, null,null)
                moNum++
                if(moNum == 21) moNum = 0
                Thread.sleep(1000L)
                setNextMoum(bigMoum, pronunciation, btn1, btn2, btn3, btn4, moNum)
                Thread.sleep(1000L)
                tts!!.speak(moStr[moNum], TextToSpeech.QUEUE_FLUSH, null,null)
            }else{
                btn1.setTextColor(RED)
                tts!!.speak(moStr[rnum[0]], TextToSpeech.QUEUE_FLUSH, null,null)
                Handler().postDelayed({
                    btn1.setTextColor(BLACK)
                }, 200)
            }
        }

        btn2.setOnClickListener{
            if(moNum == rnum[1]){
                tts!!.speak(moStr[rnum[1]], TextToSpeech.QUEUE_FLUSH, null,null)
                moNum++
                if(moNum == 21) moNum = 0
                Thread.sleep(1000L)
                setNextMoum(bigMoum, pronunciation, btn1, btn2, btn3, btn4, moNum)
                Thread.sleep(1000L)
                tts!!.speak(moStr[moNum], TextToSpeech.QUEUE_FLUSH, null,null)
            }else{
                btn2.setTextColor(RED)
                tts!!.speak(moStr[rnum[1]], TextToSpeech.QUEUE_FLUSH, null,null)
                Handler().postDelayed({
                    btn2.setTextColor(BLACK)
                }, 200)
            }
        }
        btn3.setOnClickListener{
            if(moNum == rnum[2]){
                tts!!.speak(moStr[rnum[2]], TextToSpeech.QUEUE_FLUSH, null,null)
                moNum++
                if(moNum == 21) moNum = 0
                Thread.sleep(1000L)
                setNextMoum(bigMoum, pronunciation, btn1, btn2, btn3, btn4, moNum)
                Thread.sleep(1000L)
                tts!!.speak(moStr[moNum], TextToSpeech.QUEUE_FLUSH, null,null)
            }else{
                btn3.setTextColor(RED)
                tts!!.speak(moStr[rnum[2]], TextToSpeech.QUEUE_FLUSH, null,null)
                Handler().postDelayed({
                    btn3.setTextColor(BLACK)
                }, 200)
            }
        }
        btn4.setOnClickListener{
            if(moNum == rnum[3]){
                tts!!.speak(moStr[rnum[3]], TextToSpeech.QUEUE_FLUSH, null,null)
                moNum++
                if(moNum == 21) moNum = 0
                Thread.sleep(1000L)
                setNextMoum(bigMoum, pronunciation, btn1, btn2, btn3, btn4, moNum)
                Thread.sleep(1000L)
                tts!!.speak(moStr[moNum], TextToSpeech.QUEUE_FLUSH, null,null)
            }else{
                btn4.setTextColor(RED)
                tts!!.speak(moStr[rnum[3]], TextToSpeech.QUEUE_FLUSH, null,null)
                Handler().postDelayed({
                    btn4.setTextColor(BLACK)
                }, 200)
            }
        }


    }
}