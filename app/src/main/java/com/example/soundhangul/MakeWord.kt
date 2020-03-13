package com.example.soundhangul
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import kotlinx.android.synthetic.main.activity_make_word.*
import java.lang.Thread.sleep

import java.util.*

class MakeWord : AppCompatActivity() {
    var rnum = intArrayOf(0, 0, 0, 0, 0)
    var wordNum = intArrayOf(0, 0, 0, 0)
    var ja = arrayOf("ㄱ", "ㄴ", "ㄷ", "ㄹ", "ㅁ", "ㅂ", "ㅅ", "ㅇ", "ㅈ", "ㅊ", "ㅋ", "ㅌ", "ㅍ", "ㅎ")
    var mo = arrayOf("ㅏ", "ㅑ", "ㅓ", "ㅕ", "ㅗ","ㅛ","ㅜ", "ㅠ", "ㅡ", "ㅣ", "ㅐ", "ㅒ", "ㅔ", "ㅖ", "ㅘ", "ㅙ", "ㅚ","ㅝ","ㅞ","ㅟ","ㅢ")
    var case=0
    var wordList = arrayOf(arrayOf("ㄴ ㅏ ㅂ ㅣ 나비", "ㅅ ㅏ ㅈ ㅏ 사자"), arrayOf("ㄴ ㅏ ㅁ ㅜ 나무", "ㅇ ㅕ ㅇ ㅜ 여우", "ㅋ ㅏ ㄷ ㅡ 카드"), arrayOf("ㄱ ㅡ ㄴ ㅔ 그네", "ㅂ ㅗ ㄹ ㅏ 보라", "ㅇ ㅗ ㅇ ㅣ 오이"), arrayOf("ㅇ ㅜ ㅇ ㅠ 우유", "ㅎ ㅗ ㅇ ㅜ 호우", "ㅍ ㅗ ㄷ ㅗ 포도"))
    var tts: TextToSpeech? = null
    var wordTTS: String = ""

    lateinit var firstJa:TextView
    lateinit var firstMo:TextView
    lateinit var secondJa:TextView
    lateinit var secondMo:TextView

    fun checkIndex(jamo: String, type:Int): Int{
        var realIndex=0

        if(type % 2 ==0){ // ja
            for(i in 0..ja.size-1){
                if(ja[i] == jamo){
                    realIndex = i
                    break
                }
            }
        }else{ //mo
            for(j in 0..mo.size-1){
                if(mo[j] == jamo){
                    realIndex = j
                    break;
                }
            }
        }
        return realIndex
    }
    fun findWordByCase(case: Int){
        var rnd = Random()
        var size = wordList[case].size
        var wordListNum = rnd.nextInt(size)
        val jamojamo = wordList[case][wordListNum].split(" ")

        wordNum[0] = checkIndex(jamojamo[0], 0)
        wordNum[1] = checkIndex(jamojamo[1], 1)
        wordNum[2] = checkIndex(jamojamo[2], 0)
        wordNum[3] = checkIndex(jamojamo[3], 1)
        wordTTS = jamojamo[4]
        Log.d("findWordByCase WordNum",wordNum[0].toString()+wordNum[1].toString()+wordNum[2].toString()+wordNum[3].toString())
    }
    fun getRandomWord(){
        var rnd = Random()
        case = rnd.nextInt(4)

        ganaJa1.setVisibility(View.GONE)
        ganaMo1.setVisibility(View.GONE)
        ganaJa2.setVisibility(View.GONE)
        ganaMo2.setVisibility(View.GONE)

        ganuJa1.setVisibility(View.GONE)
        ganuMo1.setVisibility(View.GONE)
        ganuJa2.setVisibility(View.GONE)
        ganuMo2.setVisibility(View.GONE)

        gunaJa1.setVisibility(View.GONE)
        gunaMo1.setVisibility(View.GONE)
        gunaJa2.setVisibility(View.GONE)
        gunaMo2.setVisibility(View.GONE)

        gunuJa1.setVisibility(View.GONE)
        gunuMo1.setVisibility(View.GONE)
        gunuJa2.setVisibility(View.GONE)
        gunuMo2.setVisibility(View.GONE)

        when(case){
            0 -> {
                ganaJa1.setVisibility(View.VISIBLE)
                ganaMo1.setVisibility(View.VISIBLE)
                ganaJa2.setVisibility(View.VISIBLE)
                ganaMo2.setVisibility(View.VISIBLE)
                firstJa = ganaJa1
                firstMo = ganaMo1
                secondJa = ganaJa2
                secondMo = ganaMo2
            }
            1 -> {
                ganuJa1.setVisibility(View.VISIBLE)
                ganuMo1.setVisibility(View.VISIBLE)
                ganuJa2.setVisibility(View.VISIBLE)
                ganuMo2.setVisibility(View.VISIBLE)
                firstJa = ganuJa1
                firstMo = ganuMo1
                secondJa = ganuJa2
                secondMo = ganuMo2
            }
            2 -> {
                gunaJa1.setVisibility(View.VISIBLE)
                gunaMo1.setVisibility(View.VISIBLE)
                gunaJa2.setVisibility(View.VISIBLE)
                gunaMo2.setVisibility(View.VISIBLE)
                firstJa = gunaJa1
                firstMo = gunaMo1
                secondJa = gunaJa2
                secondMo = gunaMo2
            }
            3 -> {
                gunuJa1.setVisibility(View.VISIBLE)
                gunuMo1.setVisibility(View.VISIBLE)
                gunuJa2.setVisibility(View.VISIBLE)
                gunuMo2.setVisibility(View.VISIBLE)
                firstJa = gunuJa1
                firstMo = gunuMo1
                secondJa = gunuJa2
                secondMo = gunuMo2
            }
        }

        findWordByCase(case)

        //해당 자음 모음 확인하기
        firstJa.setText(ja[wordNum[0]])
        firstMo.setText(mo[wordNum[1]])
        secondJa.setText(ja[wordNum[2]])
        secondMo.setText(mo[wordNum[3]])
        firstJa.setTextColor(Color.rgb(202, 204, 206))
        firstMo.setTextColor(Color.rgb(202, 204, 206))
        secondJa.setTextColor(Color.rgb(202, 204, 206))
        secondMo.setTextColor(Color.rgb(202, 204, 206))
    }

    fun jaumRandomButton(hubo: Int){
        var rnd = Random()
        Log.d("jaumRandomButton hubo",hubo.toString())

        var cnt:Int = 0
        var isSame = false
        var yesJa = false

        while(true){
            if(cnt==5) break
            rnum[cnt] = rnd.nextInt(14)
            if(rnum[cnt] == hubo){
                yesJa = true
                Log.d("rnum[cnt] == hubo", cnt.toString())
            }

            for (j: Int in 0..cnt-1) {
                if (rnum[cnt] == rnum[j]) {
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
            var r = rnd.nextInt(5)
            rnum[r] = hubo
            Log.d("yesJa == false ", r.toString() + " "+hubo.toString())
        }

        Log.d("yesJa: ", "$yesJa")
        Log.d("isSame: ", "$isSame")

    }
    fun moumRandomButton(hubo: Int){
        var rnd = Random()
        Log.d("moumRandomButton hubo",hubo.toString())

        var cnt:Int = 0
        var isSame = false
        var yesMo = false

        while(true){
            if(cnt==5) break
            rnum[cnt] = rnd.nextInt(20)
            if(rnum[cnt] == hubo) {
                yesMo = true
                Log.d("rnum[cnt] == hubo", cnt.toString())
            }
            for (j: Int in 0..cnt-1) {
                if (rnum[cnt] == rnum[j]) {
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
            var r = rnd.nextInt(5)
            rnum[r] = hubo
            Log.d("yesMo == false ", r.toString() + " "+hubo.toString())
        }
        Log.d("yesMo: ", "$yesMo")
        Log.d("isSame: ", "$isSame")
    }
    fun setButton(type: Int, btn1 : Button, btn2 : Button, btn3 : Button, btn4 : Button, btn5:Button, wordIndex: Int){
        if(type %2 == 0){
            jaumRandomButton(wordIndex)
            Log.d("setButton 자음", rnum[0].toString()+ " " + rnum[1].toString()+ " "+rnum[2].toString()+ " "+rnum[3].toString()+ " "+rnum[4].toString())
            btn1.text = ja[rnum[0]]
            btn2.text = ja[rnum[1]]
            btn3.text = ja[rnum[2]]
            btn4.text = ja[rnum[3]]
            btn5.text = ja[rnum[4]]
        }else{
            moumRandomButton(wordIndex)
            Log.d("setButton 모음", rnum[0].toString()+ " " + rnum[1].toString()+ " "+rnum[2].toString()+ " "+rnum[3].toString()+ " "+rnum[4].toString())
            btn1.text = mo[rnum[0]]
            btn2.text = mo[rnum[1]]
            btn3.text = mo[rnum[2]]
            btn4.text = mo[rnum[3]]
            btn5.text = mo[rnum[4]]
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_make_word)

        val width = resources.displayMetrics.widthPixels
        val height = resources.displayMetrics.heightPixels


/*        ganaJa1.setLayoutParams(ConstraintLayout.LayoutParams(width/6,height/4))
        ganaJa2.setLayoutParams(ConstraintLayout.LayoutParams(width/6,height/4))
        ganaMo1.setLayoutParams(ConstraintLayout.LayoutParams(width/8,height/3))
        ganaMo2.setLayoutParams(ConstraintLayout.LayoutParams(width/8,height/3))

        btn1.setLayoutParams(ConstraintLayout.LayoutParams(width/7,height/5))
        btn2.setLayoutParams(ConstraintLayout.LayoutParams(width/7,height/5))
        btn3.setLayoutParams(ConstraintLayout.LayoutParams(width/7,height/5))
        btn4.setLayoutParams(ConstraintLayout.LayoutParams(width/7,height/5))
        btn5.setLayoutParams(ConstraintLayout.LayoutParams(width/7,height/5))*/

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

        getRandomWord()


        //버튼 보여주기
        var type=0 // 짝수 : 자음 , 홀수 : 모음

        setButton(type, btn1, btn2, btn3, btn4, btn5, wordNum[type])

        //올바른 버튼 선택 --> 색 진하게, type 따라 자음 모음 버튼 바꾸기
        //아닌 버튼 --> 버튼 빨갛게 깜빡
        btn1.setOnClickListener {
            if (wordNum[type] == rnum[0]) {
                if (type % 2 == 0) {
                    if (type == 0){
                        tts!!.speak(firstJa.text.toString(), TextToSpeech.QUEUE_FLUSH, null,null)
                        firstJa.setTextColor(Color.BLUE)
                    }
                    if (type == 2){
                        tts!!.speak(secondJa.text.toString(), TextToSpeech.QUEUE_FLUSH, null,null)
                        secondJa.setTextColor(Color.BLUE)
                    }
                } else {
                    if (type == 1){
                        tts!!.speak(firstMo.text.toString(), TextToSpeech.QUEUE_FLUSH, null,null)
                        firstMo.setTextColor(Color.BLUE)
                    }
                    if (type == 3){
                        tts!!.speak(secondMo.text.toString(), TextToSpeech.QUEUE_FLUSH, null,null)
                        secondMo.setTextColor(Color.BLUE)
                    }
                }
                type += 1

                Thread.sleep(1000L)
                if(type == 4){
                    Log.d("Button", wordTTS)
                    tts!!.speak(wordTTS, TextToSpeech.QUEUE_FLUSH,null,null)
                    Thread.sleep(1000L)
                    // 다음 단어
                    type = 0
                    getRandomWord()
                }
                setButton(type, btn1, btn2, btn3, btn4, btn5, wordNum[type])
                Log.d("type", type.toString())
                Log.d("wordtype", wordNum[type].toString())
                //Thread.sleep(1000L)
                //ttsClient?.play(str[jaumNum])
            } else {
                btn1.setTextColor(Color.RED)
                //ttsClient?.play(str[rnum[0]])
                Handler().postDelayed({
                    btn1.setTextColor(Color.BLACK)
                }, 200)
            }
        }

        btn2.setOnClickListener {
            if (wordNum[type] == rnum[1]) {
                if (type % 2 == 0) {
                    if (type == 0){
                        tts!!.speak(firstJa.text.toString(), TextToSpeech.QUEUE_FLUSH, null,null)
                        firstJa.setTextColor(Color.BLUE)
                    }
                    if (type == 2){
                        tts!!.speak(secondJa.text.toString(), TextToSpeech.QUEUE_FLUSH, null,null)
                        secondJa.setTextColor(Color.BLUE)
                    }
                } else {
                    if (type == 1){
                        tts!!.speak(firstMo.text.toString(), TextToSpeech.QUEUE_FLUSH, null,null)
                        firstMo.setTextColor(Color.BLUE)
                    }
                    if (type == 3){
                        tts!!.speak(secondMo.text.toString(), TextToSpeech.QUEUE_FLUSH, null,null)
                        secondMo.setTextColor(Color.BLUE)
                    }
                }
                type += 1

                Thread.sleep(1000L)
                if(type == 4){
                    Log.d("Button", wordTTS)
                    tts!!.speak(wordTTS, TextToSpeech.QUEUE_FLUSH,null,null)
                    Thread.sleep(1000L)
                    // 다음 단어
                    type = 0
                    getRandomWord()
                }
                setButton(type, btn1, btn2, btn3, btn4, btn5, wordNum[type])
                Log.d("type", type.toString())
                Log.d("wordtype", wordNum[type].toString())
                //Thread.sleep(1000L)
                //ttsClient?.play(str[jaumNum])
            } else {
                btn2.setTextColor(Color.RED)
                //ttsClient?.play(str[rnum[0]])
                Handler().postDelayed({
                    btn2.setTextColor(Color.BLACK)
                }, 200)
            }
        }

        btn3.setOnClickListener {
            if (wordNum[type] == rnum[2]) {
                if (type % 2 == 0) {
                    if (type == 0){
                        tts!!.speak(firstJa.text.toString(), TextToSpeech.QUEUE_FLUSH, null,null)
                        firstJa.setTextColor(Color.BLUE)
                    }
                    if (type == 2){
                        tts!!.speak(secondJa.text.toString(), TextToSpeech.QUEUE_FLUSH, null,null)
                        secondJa.setTextColor(Color.BLUE)
                    }
                } else {
                    if (type == 1){
                        tts!!.speak(firstMo.text.toString(), TextToSpeech.QUEUE_FLUSH, null,null)
                        firstMo.setTextColor(Color.BLUE)
                    }
                    if (type == 3){
                        tts!!.speak(secondMo.text.toString(), TextToSpeech.QUEUE_FLUSH, null,null)
                        secondMo.setTextColor(Color.BLUE)
                    }
                }
                type += 1

                Thread.sleep(1000L)
                if(type == 4){
                    Log.d("Button", wordTTS)
                    tts!!.speak(wordTTS, TextToSpeech.QUEUE_FLUSH,null,null)
                    Thread.sleep(1000L)
                    // 다음 단어
                    type = 0
                    getRandomWord()
                }
                setButton(type, btn1, btn2, btn3, btn4, btn5, wordNum[type])
                Log.d("type", type.toString())
                Log.d("wordtype", wordNum[type].toString())
                //Thread.sleep(1000L)
                //ttsClient?.play(str[jaumNum])
            } else {
                btn3.setTextColor(Color.RED)
                //ttsClient?.play(str[rnum[0]])
                Handler().postDelayed({
                    btn3.setTextColor(Color.BLACK)
                }, 200)
            }
        }

        btn4.setOnClickListener {
            if (wordNum[type] == rnum[3]) {
                if (type % 2 == 0) {
                    if (type == 0){
                        tts!!.speak(firstJa.text.toString(), TextToSpeech.QUEUE_FLUSH, null,null)
                        firstJa.setTextColor(Color.BLUE)
                    }
                    if (type == 2){
                        tts!!.speak(secondJa.text.toString(), TextToSpeech.QUEUE_FLUSH, null,null)
                        secondJa.setTextColor(Color.BLUE)
                    }
                } else {
                    if (type == 1){
                        tts!!.speak(firstMo.text.toString(), TextToSpeech.QUEUE_FLUSH, null,null)
                        firstMo.setTextColor(Color.BLUE)
                    }
                    if (type == 3){
                        tts!!.speak(secondMo.text.toString(), TextToSpeech.QUEUE_FLUSH, null,null)
                        secondMo.setTextColor(Color.BLUE)
                    }
                }
                type += 1

                Thread.sleep(1000L)
                if(type == 4){
                    Log.d("Button", wordTTS)
                    tts!!.speak(wordTTS, TextToSpeech.QUEUE_FLUSH,null,null)
                    Thread.sleep(1000L)
                    // 다음 단어
                    type = 0
                    getRandomWord()
                }
                setButton(type, btn1, btn2, btn3, btn4, btn5, wordNum[type])
                Log.d("type", type.toString())
                Log.d("wordtype", wordNum[type].toString())
                //Thread.sleep(1000L)
                //ttsClient?.play(str[jaumNum])
            } else {
                btn4.setTextColor(Color.RED)
                //ttsClient?.play(str[rnum[0]])
                Handler().postDelayed({
                    btn4.setTextColor(Color.BLACK)
                }, 200)
            }
        }

        btn5.setOnClickListener {
            if (wordNum[type] == rnum[4]) {
                if (type % 2 == 0) {
                    if (type == 0){
                        tts!!.speak(firstJa.text.toString(), TextToSpeech.QUEUE_FLUSH, null,null)
                        firstJa.setTextColor(Color.BLUE)
                    }
                    if (type == 2){
                        tts!!.speak(secondJa.text.toString(), TextToSpeech.QUEUE_FLUSH, null,null)
                        secondJa.setTextColor(Color.BLUE)
                    }
                } else {
                    if (type == 1){
                        tts!!.speak(firstMo.text.toString(), TextToSpeech.QUEUE_FLUSH, null,null)
                        firstMo.setTextColor(Color.BLUE)
                    }
                    if (type == 3){
                        tts!!.speak(secondMo.text.toString(), TextToSpeech.QUEUE_FLUSH, null,null)
                        secondMo.setTextColor(Color.BLUE)
                    }
                }
                type += 1

                Thread.sleep(1000L)
                if(type == 4){
                    Log.d("Button", wordTTS)
                    tts!!.speak(wordTTS, TextToSpeech.QUEUE_FLUSH,null,null)
                    Thread.sleep(1000L)
                    // 다음 단어
                    type = 0
                    getRandomWord()
                }
                setButton(type, btn1, btn2, btn3, btn4, btn5, wordNum[type])
                Log.d("type", type.toString())
                Log.d("wordtype", wordNum[type].toString())
                //Thread.sleep(1000L)
                //ttsClient?.play(str[jaumNum])
            } else {
                btn5.setTextColor(Color.RED)
                //ttsClient?.play(str[rnum[0]])
                Handler().postDelayed({
                    btn5.setTextColor(Color.BLACK)
                }, 200)
            }
        }
    }


}