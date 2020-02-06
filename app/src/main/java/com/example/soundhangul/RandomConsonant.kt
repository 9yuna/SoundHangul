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

class RandomConsonant : AppCompatActivity() {
    var ja = arrayOf("ㄱ", "ㄴ", "ㄷ", "ㄹ", "ㅁ", "ㅂ", "ㅅ", "ㅇ", "ㅈ", "ㅊ", "ㅋ", "ㅌ", "ㅍ", "ㅎ")
    var str = arrayOf("기역", "니은", "디귿", "리을", "미음", "비읍", "시옷", "이응", "지읒", "치읒", "키읔", "티긑", "피흪", "히흫")
    var rnum = intArrayOf(0, 0, 0, 0)

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

    fun setNextJaum(bigJaum: ImageView, pronunciation: TextView, btn1 : Button, btn2 : Button, btn3 : Button, btn4 : Button, jaumNum : Int){
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

        var btn1 = findViewById(R.id.button) as Button
        var btn2 = findViewById(R.id.button2) as Button
        var btn3 = findViewById(R.id.button3) as Button
        var btn4 = findViewById(R.id.button4) as Button
        var bigJaum = findViewById(R.id.BigJaeum) as ImageView
        var pronunciation = findViewById(R.id.Pronunciation) as TextView


        var rnd = Random()
        var jaumNum:Int = rnd.nextInt(14)
        var nextJaumNum:Int = 987654321

        // 이미지랑 발음 보여주기
        setNextJaum(bigJaum, pronunciation, btn1, btn2, btn3, btn4, jaumNum)
        Log.d("### I: ", "$jaumNum")
        // 올바른 버튼 클릭시 다음 자음으로 넘어가기
        btn1.setOnClickListener{
            if(jaumNum == rnum[0]){
                nextJaumNum = rnd.nextInt(14)
                while(nextJaumNum == jaumNum){
                    nextJaumNum = rnd.nextInt(14)
                    Log.d("while(jaumNum): ", "$jaumNum")
                    Log.d("while(nextJaumNum): ", "$nextJaumNum")
                }
                jaumNum = nextJaumNum

                Log.d("### I(1): ", "$jaumNum")
                setNextJaum(bigJaum, pronunciation, btn1, btn2, btn3, btn4, jaumNum)
            }else{
                Log.d("###not I(1): ", "$jaumNum")
                btn1.setTextColor(Color.RED)

                Handler().postDelayed({
                    btn1.setTextColor(Color.BLACK)
                }, 200)
            }
        }

        btn2.setOnClickListener{
            if(jaumNum == rnum[1]){
                nextJaumNum = rnd.nextInt(14)
                while(nextJaumNum == jaumNum){
                    nextJaumNum = rnd.nextInt(14)
                    Log.d("while(jaumNum): ", "$jaumNum")
                    Log.d("while(nextJaumNum): ", "$nextJaumNum")
                }
                jaumNum = nextJaumNum

                Log.d("### I(2): ", "$jaumNum")
                setNextJaum(bigJaum, pronunciation, btn1, btn2, btn3, btn4, jaumNum)
            }else{
                Log.d("###not I(2): ", "$jaumNum")
                btn2.setTextColor(Color.RED)

                Handler().postDelayed({
                    btn2.setTextColor(Color.BLACK)
                }, 200)
            }
        }
        btn3.setOnClickListener{
            if(jaumNum == rnum[2]){
                nextJaumNum = rnd.nextInt(14)
                while(nextJaumNum == jaumNum){
                    nextJaumNum = rnd.nextInt(14)
                    Log.d("while(jaumNum): ", "$jaumNum")
                    Log.d("while(nextJaumNum): ", "$nextJaumNum")
                }
                jaumNum = nextJaumNum

                Log.d("### I(3): ", "$jaumNum")
                setNextJaum(bigJaum, pronunciation, btn1, btn2, btn3, btn4, jaumNum)
            }else{
                Log.d("###not I(3): ", "$jaumNum")
                btn3.setTextColor(Color.RED)

                Handler().postDelayed({
                    btn3.setTextColor(Color.BLACK)
                }, 200)
            }
        }
        btn4.setOnClickListener{
            if(jaumNum == rnum[3]){
                nextJaumNum = rnd.nextInt(14)
                while(nextJaumNum == jaumNum){
                    nextJaumNum = rnd.nextInt(14)
                    Log.d("while(jaumNum): ", "$jaumNum")
                    Log.d("while(nextJaumNum): ", "$nextJaumNum")
                }
                jaumNum = nextJaumNum

                Log.d("### I(4): ", "$jaumNum")
                setNextJaum(bigJaum, pronunciation, btn1, btn2, btn3, btn4, jaumNum)
            }else{
                Log.d("###not I(4): ", "$jaumNum")
                btn4.setTextColor(Color.RED)

                Handler().postDelayed({
                    btn4.setTextColor(Color.BLACK)
                }, 200)
            }
        }
    }
}