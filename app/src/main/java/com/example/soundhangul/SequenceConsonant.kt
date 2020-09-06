package com.example.soundhangul

import android.graphics.Color.BLACK
import android.graphics.Color.RED
import android.os.Bundle
import android.os.Handler
import android.speech.tts.TextToSpeech
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import kotlinx.android.synthetic.main.activity_sequence_consonant.*
import java.util.*

class SequenceConsonant : AppCompatActivity() {
    var ja = arrayOf("ㄱ", "ㄴ", "ㄷ", "ㄹ", "ㅁ", "ㅂ", "ㅅ", "ㅇ", "ㅈ", "ㅊ", "ㅋ", "ㅌ", "ㅍ", "ㅎ")
    var str = arrayOf("기역", "니은", "디귿", "리을", "미음", "비읍", "시옷", "이응", "지읒", "치읒", "키읔", "티긑", "피흪", "히흫")
    var rnum = intArrayOf(0, 0, 0, 0)

    var tts: TextToSpeech? = null

    fun findImage(i: Int): Int{
        var s = "ja" + i.toString()
        var drawableResId = resources.getIdentifier(s, "drawable", this.getPackageName())

        return drawableResId
    }

    fun randomButton(hubo: Int){
        var rnd = Random()

        var cnt:Int =0
        var isSame = false
        var yesJa = false

        while(true){
            if(cnt==4) break
            rnum[cnt] = rnd.nextInt(14)
            if(rnum[cnt] == hubo) yesJa = true

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

        if(yesJa == false){
            var r = rnd.nextInt(4)
            rnum[r] = hubo
        }

        Log.d("yesJa: ", "$yesJa")
        Log.d("isSame: ", "$isSame")

    }

    fun setNextJaum(bigJaum: ImageView, pronunciation: TextView, btn1 :Button, btn2 :Button, btn3 :Button, btn4 :Button, jaumNum : Int){
        bigJaum.setImageResource(findImage(jaumNum+1)) //i
        pronunciation.setText(str[jaumNum])   //i-1
        randomButton(jaumNum)
        Log.d("RNUM: ", Arrays.toString(rnum))
        btn1.setText(ja[rnum[0]])
        btn2.setText(ja[rnum[1]])
        btn3.setText(ja[rnum[2]])
        btn4.setText(ja[rnum[3]])
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sequence_consonant)

        val width = resources.displayMetrics.widthPixels
        val height = resources.displayMetrics.heightPixels

        //버튼 사이즈 조절
        BigJaeum.setLayoutParams(ConstraintLayout.LayoutParams(width/4,height/3))
        Pronunciation.setLayoutParams(ConstraintLayout.LayoutParams(width/7,height/4))
        button.setLayoutParams(ConstraintLayout.LayoutParams(width/7,height/6))
        button2.setLayoutParams(ConstraintLayout.LayoutParams(width/7,height/6))
        button3.setLayoutParams(ConstraintLayout.LayoutParams(width/7,height/6))
        button4.setLayoutParams(ConstraintLayout.LayoutParams(width/7,height/6))

        //버튼 위치 조정
        val BigJaeumParams = BigJaeum.layoutParams as ConstraintLayout.LayoutParams
        BigJaeumParams.topToTop = R.id.sequenceConsonant
        BigJaeumParams.topMargin = height/10
        BigJaeumParams.startToStart = R.id.sequenceConsonant
        BigJaeumParams.marginStart = width/5
        BigJaeumParams.bottomToTop = R.id.button
        BigJaeumParams.endToStart = R.id.Pronunciation
        BigJaeumParams.marginEnd = width/13
        BigJaeum.requestLayout()

        val pronunciationParams = Pronunciation.layoutParams as ConstraintLayout.LayoutParams
        pronunciationParams.topToTop = R.id.sequenceConsonant
        pronunciationParams.topMargin = height/8
        pronunciationParams.startToEnd = R.id.BigJaeum
        pronunciationParams.marginStart = width/13
        pronunciationParams.bottomToTop = R.id.button3
        pronunciationParams.bottomMargin = height/13
        pronunciationParams.endToEnd = R.id.sequenceConsonant
        pronunciationParams.marginEnd = width/5
        Pronunciation.requestLayout()

        val buttonParams = button.layoutParams as ConstraintLayout.LayoutParams
        buttonParams.topToBottom = R.id.BigJaeum
        buttonParams.topMargin = height/10
        buttonParams.startToStart = R.id.sequenceConsonant
        buttonParams.marginStart = width/7
        buttonParams.bottomToBottom = R.id.sequenceConsonant
        buttonParams.bottomMargin = height/8
        buttonParams.endToStart = R.id.button2
        buttonParams.marginEnd = width/20
        button.requestLayout()

        val button2Params = button2.layoutParams as ConstraintLayout.LayoutParams
        button2Params.topToBottom = R.id.BigJaeum
        button2Params.topMargin = height/10
        button2Params.startToEnd = R.id.button
        button2Params.marginStart = width/20
        button2Params.bottomToBottom = R.id.sequenceConsonant
        button2Params.bottomMargin = height/8
        button2Params.endToStart = R.id.button3
        button2Params.marginEnd = width/20
        button2.requestLayout()

        val button3Params = button3.layoutParams as ConstraintLayout.LayoutParams
        button3Params.topToBottom = R.id.BigJaeum
        button3Params.topMargin = height/10
        button3Params.startToEnd = R.id.button2
        button3Params.marginStart = width/20
        button3Params.bottomToBottom = R.id.sequenceConsonant
        button3Params.bottomMargin = height/8
        button3Params.endToStart = R.id.button4
        button3Params.marginEnd = width/20
        button3.requestLayout()

        val button4Params = button4.layoutParams as ConstraintLayout.LayoutParams
        button4Params.topToBottom = R.id.BigJaeum
        button4Params.topMargin = height/10
        button4Params.startToEnd = R.id.button3
        button4Params.marginStart = width/20
        button4Params.bottomToBottom = R.id.sequenceConsonant
        button4Params.bottomMargin = height/8
        button4Params.endToEnd = R.id.sequenceConsonant
        button4Params.marginEnd = width/7
        button4.requestLayout()


        var jaumNum: Int = 0

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

        setNextJaum(BigJaeum, Pronunciation, button, button2, button3, button4, jaumNum)
        tts!!.speak(str[jaumNum], TextToSpeech.QUEUE_FLUSH, null,null)
        Log.d("### I: ", "$jaumNum")
        // 올바른 버튼 클릭시 다음 자음으로 넘어가기
        button.setOnClickListener {
            if (jaumNum == rnum[0]) {
                tts!!.speak(str[rnum[0]], TextToSpeech.QUEUE_FLUSH, null,null)
                jaumNum++
                if (jaumNum == 14) jaumNum = 0
                Log.d("### I(1): ", "$jaumNum")
                Thread.sleep(1000L)
                setNextJaum(BigJaeum, Pronunciation, button, button2, button3, button4, jaumNum)
                Thread.sleep(1000L)
                tts!!.speak(str[jaumNum], TextToSpeech.QUEUE_FLUSH, null,null)
            } else {
                Log.d("###not I(1): ", "$jaumNum")
                button.setTextColor(RED)
                tts!!.speak(str[rnum[0]], TextToSpeech.QUEUE_FLUSH, null,null)

                Handler().postDelayed({
                    button.setTextColor(BLACK)
                }, 200)
            }
        }

        button2.setOnClickListener {
            if (jaumNum == rnum[1]) {
                tts!!.speak(str[rnum[1]], TextToSpeech.QUEUE_FLUSH, null,null)
                jaumNum++
                if (jaumNum == 14) jaumNum = 0
                Log.d("### I(2): ", "$jaumNum")
                Thread.sleep(1000L)
                setNextJaum(BigJaeum, Pronunciation, button, button2, button3, button4, jaumNum)
                Thread.sleep(1000L)
                tts!!.speak(str[jaumNum], TextToSpeech.QUEUE_FLUSH, null,null)
            } else {
                Log.d("###not I(2): ", "$jaumNum")
                button2.setTextColor(RED)
                tts!!.speak(str[rnum[0]], TextToSpeech.QUEUE_FLUSH, null,null)
                Handler().postDelayed({
                    button2.setTextColor(BLACK)
                }, 200)
            }
        }
        button3.setOnClickListener {
            if (jaumNum == rnum[2]) {
                tts!!.speak(str[rnum[2]], TextToSpeech.QUEUE_FLUSH, null,null)
                jaumNum++
                if (jaumNum == 14) jaumNum = 0
                Log.d("### I(3): ", "$jaumNum")
                Thread.sleep(1000L)
                setNextJaum(BigJaeum, Pronunciation, button, button2, button3, button4, jaumNum)
                Thread.sleep(1000L)
                tts!!.speak(str[jaumNum], TextToSpeech.QUEUE_FLUSH, null,null)
            } else {
                Log.d("###not I(3): ", "$jaumNum")
                button3.setTextColor(RED)
                tts!!.speak(str[rnum[2]], TextToSpeech.QUEUE_FLUSH, null,null)
                Handler().postDelayed({
                    button3.setTextColor(BLACK)
                }, 200)
            }
        }
        button4.setOnClickListener {
            if (jaumNum == rnum[3]) {
                tts!!.speak(str[rnum[3]], TextToSpeech.QUEUE_FLUSH, null,null)
                jaumNum++
                if (jaumNum == 14) jaumNum = 0
                Log.d("### I(4): ", "$jaumNum")
                Thread.sleep(1000L)
                setNextJaum(BigJaeum, Pronunciation, button, button2, button3, button4, jaumNum)
                Thread.sleep(1000L)
                tts!!.speak(str[jaumNum], TextToSpeech.QUEUE_FLUSH, null,null)
            } else {
                Log.d("###not I(4): ", "$jaumNum")
                button4.setTextColor(RED)
                tts!!.speak(str[rnum[3]], TextToSpeech.QUEUE_FLUSH, null,null)
                Handler().postDelayed({
                    button4.setTextColor(BLACK)
                }, 200)
            }
        }
    }
}
