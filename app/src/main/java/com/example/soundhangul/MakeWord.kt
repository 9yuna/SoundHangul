package com.example.soundhangul
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

import java.util.*

class MakeWord : AppCompatActivity() {
    //{'나', '비', ㅓㅏㅑ'' 나비 나라 가나 아이
    var jaWord = arrayOf("ㄴ", "ㅂ", "ㅁ", "ㄹ")
    var moWord = arrayOf("ㅏ", "ㅣ", "ㅣ", "ㅐ" )
    var rnum = intArrayOf(0, 0, 0, 0, 0)
    var word = intArrayOf(0, 0, 0, 0, 0)
    var ja = arrayOf("ㄱ", "ㄴ", "ㄷ", "ㄹ", "ㅁ", "ㅂ", "ㅅ", "ㅇ", "ㅈ", "ㅊ", "ㅋ", "ㅌ", "ㅍ", "ㅎ")
    var mo = arrayOf("ㅏ", "ㅑ", "ㅓ", "ㅕ", "ㅗ","ㅛ","ㅜ", "ㅠ", "ㅡ", "ㅣ", "ㅐ", "ㅒ", "ㅔ", "ㅖ", "ㅘ", "ㅙ", "ㅚ","ㅝ","ㅞ","ㅟ","ㅢ")

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_make_word)

        var btn1 = findViewById<Button>(R.id.button)
        var btn2 = findViewById<Button>(R.id.button2)
        var btn3 = findViewById<Button>(R.id.button3)
        var btn4 = findViewById<Button>(R.id.button4)
        var btn5 = findViewById<Button>(R.id.button5)

        var firstJa = findViewById<TextView>(R.id.ja1)
        var firstMo = findViewById<TextView>(R.id.mo1)
        var secondJa = findViewById<TextView>(R.id.ja2)
        var secondMo = findViewById<TextView>(R.id.mo2)

        //해당 자음 모음 확인하기
        var type = 0
        word[0] = checkIndex(jaWord[0], 0)
        word[1] = checkIndex(moWord[0], 1)
        word[2] = checkIndex(jaWord[1], 0)
        word[3] = checkIndex(moWord[1], 1)

        //회색 글씨 띄우기
        firstJa.setText(ja[word[0]])
        firstMo.setText(mo[word[1]])
        secondJa.setText(ja[word[2]])
        secondMo.setText(mo[word[3]])
        firstJa.setTextColor(Color.rgb(202,204,206))
        firstMo.setTextColor(Color.rgb(202,204,206))
        secondJa.setTextColor(Color.rgb(202,204,206))
        secondMo.setTextColor(Color.rgb(202,204,206))

        //버튼 보여주기
       // var wordIndex = 0
        setButton(type, btn1, btn2, btn3, btn4, btn5, word[type])

            //올바른 버튼 선택 --> 색 진하게, type 따라 자음 모음 버튼 바꾸기
            //아닌 버튼 --> 버튼 빨갛게 깜빡
            btn1.setOnClickListener{
                if(word[type] == rnum[0]){
                    if(type % 2 ==0) {
                        if (type == 0) firstJa.setTextColor(Color.BLUE)
                        if (type == 2) secondJa.setTextColor(Color.BLUE)
                    }else{
                        if(type == 1) firstMo.setTextColor(Color.BLUE)
                        if(type == 3) secondMo.setTextColor(Color.BLUE)
                    }
                    type += 1
                 //   wordIndex += 1

                    Thread.sleep(1000L)
                    setButton(type, btn1, btn2, btn3, btn4, btn5, word[type])
                    Log.d("type", type.toString())
                    Log.d("wordtype", word[type].toString())
                    //Thread.sleep(1000L)
                    //ttsClient?.play(str[jaumNum])
                }else{
                    btn1.setTextColor(Color.RED)
                    //ttsClient?.play(str[rnum[0]])
                    Handler().postDelayed({
                        btn1.setTextColor(Color.BLACK)
                    }, 200)
                }
            }

            btn2.setOnClickListener{
                if(word[type] == rnum[1]){
                    if(type % 2 ==0) {
                        if (type == 0) firstJa.setTextColor(Color.BLUE)
                        if (type == 2) secondJa.setTextColor(Color.BLUE)
                    }else{
                        if(type == 1) firstMo.setTextColor(Color.BLUE)
                        if(type == 3) secondMo.setTextColor(Color.BLUE)
                    }
                    type += 1
               //     wordIndex += 1

                    Thread.sleep(1000L)
                    setButton(type, btn1, btn2, btn3, btn4, btn5, word[type])
                    Log.d("type", type.toString())
                    Log.d("wordtype", word[type].toString())
                    //Thread.sleep(1000L)
                    //ttsClient?.play(str[jaumNum])
                }else{
                    btn2.setTextColor(Color.RED)
                    //ttsClient?.play(str[rnum[0]])
                    Handler().postDelayed({
                        btn2.setTextColor(Color.BLACK)
                    }, 200)
                }
            }

            btn3.setOnClickListener{
                if(word[type] == rnum[2]){
                    if(type % 2 ==0) {
                        if (type == 0) firstJa.setTextColor(Color.BLUE)
                        if (type == 2) secondJa.setTextColor(Color.BLUE)
                    }else{
                        if(type == 1) firstMo.setTextColor(Color.BLUE)
                        if(type == 3) secondMo.setTextColor(Color.BLUE)
                    }
                    type += 1
                 //   wordIndex += 1

                    Thread.sleep(1000L)
                    setButton(type, btn1, btn2, btn3, btn4, btn5, word[type])
                    Log.d("type", type.toString())
                    Log.d("wordtype", word[type].toString())
                    //Thread.sleep(1000L)
                    //ttsClient?.play(str[jaumNum])
                }else{
                    btn3.setTextColor(Color.RED)
                    //ttsClient?.play(str[rnum[0]])
                    Handler().postDelayed({
                        btn3.setTextColor(Color.BLACK)
                    }, 200)
                }
            }

            btn4.setOnClickListener{
                if(word[type] == rnum[3]){
                    if(type % 2 ==0) {
                        if (type == 0) firstJa.setTextColor(Color.BLUE)
                        if (type == 2) secondJa.setTextColor(Color.BLUE)
                    }else{
                        if(type == 1) firstMo.setTextColor(Color.BLUE)
                        if(type == 3) secondMo.setTextColor(Color.BLUE)
                    }
                    type += 1
                //    wordIndex += 1

                    Thread.sleep(1000L)
                    setButton(type, btn1, btn2, btn3, btn4, btn5, word[type])
                    Log.d("type", type.toString())
                    Log.d("wordtype", word[type].toString())
                    //Thread.sleep(1000L)
                    //ttsClient?.play(str[jaumNum])
                }else{
                    btn4.setTextColor(Color.RED)
                    //ttsClient?.play(str[rnum[0]])
                    Handler().postDelayed({
                        btn4.setTextColor(Color.BLACK)
                    }, 200)
                }
            }

            btn5.setOnClickListener{
                if(word[type] == rnum[4]){
                    if(type % 2 ==0) {
                        if (type == 0) firstJa.setTextColor(Color.BLUE)
                        if (type == 2) secondJa.setTextColor(Color.BLUE)
                    }else{
                        if(type == 1) firstMo.setTextColor(Color.BLUE)
                        if(type == 3) secondMo.setTextColor(Color.BLUE)
                    }
                    type += 1
                        //      wordIndex += 1

                    Thread.sleep(1000L)
                    setButton(type, btn1, btn2, btn3, btn4, btn5, word[type])
                    Log.d("type", type.toString())
                    Log.d("wordtype", word[type].toString())
                    //Thread.sleep(1000L)
                    //ttsClient?.play(str[jaumNum])
                }else{
                    btn5.setTextColor(Color.RED)
                    //ttsClient?.play(str[rnum[0]])
                    Handler().postDelayed({
                        btn5.setTextColor(Color.BLACK)
                    }, 200)
                }
            }
    }

}