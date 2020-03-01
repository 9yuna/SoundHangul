package com.example.soundhangul
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_make_word.*
import java.lang.Thread.sleep

import java.util.*

// 0 : ㄴ ㅏ ㅂ ㅣ
// 1 : ㄴ ㅏ ㅁ ㅜ
// 2 : ㄱ ㅠ ㄴ ㅏ
// 3 : ㅇ ㅜ ㅇ ㅠ

class MakeWord : AppCompatActivity() {
    var rnum = intArrayOf(0, 0, 0, 0, 0)
    var wordNum = intArrayOf(0, 0, 0, 0)
    var ja = arrayOf("ㄱ", "ㄴ", "ㄷ", "ㄹ", "ㅁ", "ㅂ", "ㅅ", "ㅇ", "ㅈ", "ㅊ", "ㅋ", "ㅌ", "ㅍ", "ㅎ")
    var mo = arrayOf("ㅏ", "ㅑ", "ㅓ", "ㅕ", "ㅗ","ㅛ","ㅜ", "ㅠ", "ㅡ", "ㅣ", "ㅐ", "ㅒ", "ㅔ", "ㅖ", "ㅘ", "ㅙ", "ㅚ","ㅝ","ㅞ","ㅟ","ㅢ")
    var case=0
    //val words = mapOf(0 to "ㄴ ㅏ ㅂ ㅣ", 1 to "ㄴ ㅏ ㅁ ㅜ", 2 to "ㄱ ㅠ ㄴ ㅏ", 3 to "ㅇ ㅜ ㅇ ㅠ")
    val words = mapOf(0 to "ㅎ ㅗ ㅇ ㅜ", 1 to "ㅇ ㅡ ㅇ ㅟ", 2 to "ㄱ ㅠ ㄴ ㅡ", 3 to "ㅇ ㅜ ㅇ ㅠ")

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
        var str = words.get(case)
        val jamojamo = str!!.split(" ")

        var type = 0 //0 :ja 1:mo
        wordNum[0] = checkIndex(jamojamo[0], 0)
        wordNum[1] = checkIndex(jamojamo[1], 1)
        wordNum[2] = checkIndex(jamojamo[2], 0)
        wordNum[3] = checkIndex(jamojamo[3], 1)
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
        gunaMo2.setVisibility(View.GONE)

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
                gunaMo2.setVisibility(View.VISIBLE)
                firstJa = gunuJa1
                firstMo = gunuMo1
                secondJa = gunuJa2
                secondMo = gunaMo2
            }
        }
    }

    fun jaumRandomButton(hubo: Int){
        var rnd = Random()

        var cnt:Int =0
        var isSame = false
        var yesJa = false

        while(true){
            if(cnt==5) break
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
            var r = rnd.nextInt(5)
            rnum[r] = hubo
        }

        Log.d("yesJa: ", "$yesJa")
        Log.d("isSame: ", "$isSame")

    }
    fun moumRandomButton(hubo: Int){
        var rnd = Random()

        var cnt:Int = 0
        var isSame = false
        var yesMo = false

        while(true){
            if(cnt==5) break
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
            var r = rnd.nextInt(5)
            rnum[r] = hubo
        }
    }
    fun setButton(type: Int, btn1 : Button, btn2 : Button, btn3 : Button, btn4 : Button, btn5:Button, wordIndex: Int){
        if(type %2 == 0){
            jaumRandomButton(wordIndex)
            btn1.text = ja[rnum[0]]
            btn2.text = ja[rnum[1]]
            btn3.text = ja[rnum[2]]
            btn4.text = ja[rnum[3]]
            btn5.setText(ja[rnum[4]])
        }else{
            moumRandomButton(wordIndex)
            btn1.text = mo[rnum[0]]
            btn2.text = mo[rnum[1]]
            btn3.text = mo[rnum[2]]
            btn4.text = mo[rnum[3]]
            btn5.setText(mo[rnum[4]])
        }
    }

    fun setWordCaseRandom(){
        var rnd = Random()
        case = rnd.nextInt(4)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_make_word)

        var btn1 = findViewById<Button>(R.id.button)
        var btn2 = findViewById<Button>(R.id.button2)
        var btn3 = findViewById<Button>(R.id.button3)
        var btn4 = findViewById<Button>(R.id.button4)
        var btn5 = findViewById<Button>(R.id.button5)


//해당 자음 모음 확인하기
        firstJa.setText(ja[wordNum[0]])
        firstMo.setText(mo[wordNum[1]])
        secondJa.setText(ja[wordNum[2]])
        secondMo.setText(mo[wordNum[3]])
        firstJa.setTextColor(Color.rgb(202, 204, 206))
        firstMo.setTextColor(Color.rgb(202, 204, 206))
        secondJa.setTextColor(Color.rgb(202, 204, 206))
        secondMo.setTextColor(Color.rgb(202, 204, 206))


        //버튼 보여주기
        
        var type=0
        setButton(type, btn1, btn2, btn3, btn4, btn5, wordNum[type])

        //올바른 버튼 선택 --> 색 진하게, type 따라 자음 모음 버튼 바꾸기
        //아닌 버튼 --> 버튼 빨갛게 깜빡
        btn1.setOnClickListener {
            if (wordNum[type] == rnum[0]) {
                if (type % 2 == 0) {
                    if (type == 0) firstJa.setTextColor(Color.BLUE)
                    if (type == 2) secondJa.setTextColor(Color.BLUE)
                } else {
                    if (type == 1) firstMo.setTextColor(Color.BLUE)
                    if (type == 3) secondMo.setTextColor(Color.BLUE)
                }
                type += 1
                //   wordIndex += 1

                Thread.sleep(1000L)
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
                    if (type == 0) firstJa.setTextColor(Color.BLUE)
                    if (type == 2) secondJa.setTextColor(Color.BLUE)
                } else {
                    if (type == 1) firstMo.setTextColor(Color.BLUE)
                    if (type == 3) secondMo.setTextColor(Color.BLUE)
                }
                type += 1
                //     wordIndex += 1

                Thread.sleep(1000L)
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
                    if (type == 0) firstJa.setTextColor(Color.BLUE)
                    if (type == 2) secondJa.setTextColor(Color.BLUE)
                } else {
                    if (type == 1) firstMo.setTextColor(Color.BLUE)
                    if (type == 3) secondMo.setTextColor(Color.BLUE)
                }
                type += 1
                //   wordIndex += 1

                Thread.sleep(1000L)
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
                    if (type == 0) firstJa.setTextColor(Color.BLUE)
                    if (type == 2) secondJa.setTextColor(Color.BLUE)
                } else {
                    if (type == 1) firstMo.setTextColor(Color.BLUE)
                    if (type == 3) secondMo.setTextColor(Color.BLUE)
                }
                type += 1
                //    wordIndex += 1

                Thread.sleep(1000L)
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
                    if (type == 0) firstJa.setTextColor(Color.BLUE)
                    if (type == 2) secondJa.setTextColor(Color.BLUE)
                } else {
                    if (type == 1) firstMo.setTextColor(Color.BLUE)
                    if (type == 3) secondMo.setTextColor(Color.BLUE)
                }
                type += 1
                //      wordIndex += 1

                Thread.sleep(1000L)
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