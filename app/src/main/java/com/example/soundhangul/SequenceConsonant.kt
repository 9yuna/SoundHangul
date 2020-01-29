package com.example.soundhangul

import android.graphics.Color.BLACK
import android.graphics.Color.RED
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class SequenceConsonant : AppCompatActivity() {
    var ja = arrayOf("ㄱ", "ㄴ", "ㄷ", "ㄹ", "ㅁ", "ㅂ", "ㅅ", "ㅇ", "ㅈ", "ㅊ", "ㅋ", "ㅌ", "ㅍ", "ㅎ")
    var str = arrayOf("기역", "니은", "디귿", "리을", "미음", "비읍", "시옷", "이응", "지읒", "치읒", "키읔", "티긑", "피흪", "히흫")
    var rnum = intArrayOf(0, 0, 0, 0)

    fun findImage(i: Int): Int{
        var s = "ja" + i.toString()

        var drawableResId = resources.getIdentifier(s, "drawable", this.getPackageName())
        //imageView.setImageResource(drawableResId)
        return drawableResId
    }

    fun randomButton(hubo: Int){
        var rnd = Random()

        var i:Int =0
        var isSame = false
        var yesJa = false

        while(true){
            if(i==4) break
            rnum[i] = rnd.nextInt(14)
            if(rnum[i] == hubo) yesJa = true

            for (j: Int in 0..i-1) {
                if (rnum[i] == rnum[j]) {
                    i--
                    isSame = true
                    break
                }
            }

            if(isSame == false)
                i++
            else
                isSame = false
        }

        if(yesJa == false){
            var r = rnd.nextInt(4)
            rnum[r] = hubo
        }


    }

    fun setNextJaum(BigJaum: ImageView, Pronunciation: TextView, btn1 :Button, btn2 :Button, btn3 :Button, btn4 :Button, i : Int){
        BigJaum.setImageResource(findImage(i+1)) //i
        Pronunciation.setText(str[i])   //i-1
        randomButton(i)
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
        var BigJaum = findViewById(R.id.BigJaeum) as ImageView
        var Pronunciation = findViewById(R.id.Pronunciation) as TextView

        var i:Int = 0

            // 이미지랑 발음 보여주기
            setNextJaum(BigJaum, Pronunciation, btn1, btn2, btn3, btn4, i)
            Log.d("### I: ", "$i")
            // 올바른 버튼 클릭시 다음 자음으로 넘어가기
            btn1.setOnClickListener{
                if(i == rnum[0] && i != 13){
                    i++
                    Log.d("### I(1): ", "$i")
                    setNextJaum(BigJaum, Pronunciation, btn1, btn2, btn3, btn4, i)
                }else{
                    Log.d("###not I(1): ", "$i")
                    btn1.setTextColor(RED)

                    Handler().postDelayed({
                        btn1.setTextColor(BLACK)
                    }, 200)
                }
            }

            btn2.setOnClickListener{
                if(i == rnum[1]&& i != 13){
                    i++
                    Log.d("### I(2): ", "$i")
                    setNextJaum(BigJaum, Pronunciation, btn1, btn2, btn3, btn4, i)
                }else{
                    Log.d("###not I(2): ", "$i")
                    btn2.setTextColor(RED)

                    Handler().postDelayed({
                        btn2.setTextColor(BLACK)
                    }, 200)
                }
            }
            btn3.setOnClickListener{
                if(i == rnum[2]&& i != 13){
                    i++
                    Log.d("### I(3): ", "$i")
                    setNextJaum(BigJaum, Pronunciation, btn1, btn2, btn3, btn4, i)
                }else{
                    Log.d("###not I(3): ", "$i")
                    btn3.setTextColor(RED)

                    Handler().postDelayed({
                        btn3.setTextColor(BLACK)
                    }, 200)
                }
            }
            btn4.setOnClickListener{
                if(i == rnum[3]&& i != 13){
                    i++
                    Log.d("### I(4): ", "$i")
                    setNextJaum(BigJaum, Pronunciation, btn1, btn2, btn3, btn4, i)
                }else{
                    Log.d("###not I(4): ", "$i")
                    btn4.setTextColor(RED)

                    Handler().postDelayed({
                        btn4.setTextColor(BLACK)
                    }, 200)
                }
            }

            
        }
}