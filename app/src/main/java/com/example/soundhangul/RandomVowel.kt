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

class RandomVowel : AppCompatActivity() {
    var mo = arrayOf("ㅏ", "ㅑ", "ㅓ", "ㅕ", "ㅗ","ㅛ","ㅜ", "ㅠ", "ㅡ", "ㅣ", "ㅐ", "ㅒ", "ㅔ", "ㅖ", "ㅘ", "ㅙ", "ㅚ","ㅝ","ㅞ","ㅟ","ㅢ")
    var moStr = arrayOf("아", "야", "어", "여", "오","요","우", "유", "으", "이", "애", "얘", "에", "예", "와", "왜", "외","워","웨","위","의")
    var rnum = intArrayOf(0, 0, 0, 0)

    fun findImage(i: Int): Int{
        var s = "mo" + i.toString()

        var drawableResId = resources.getIdentifier(s, "drawable", this.getPackageName())
        //imageView.setImageResource(drawableResId)
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
        bigMoum.setImageResource(findImage(moNum+1)) //i
        pronunciation.setText(moStr[moNum])   //i-1
        randomButton(moNum)
        btn1.setText(mo[rnum[0]])
        btn2.setText(mo[rnum[1]])
        btn3.setText(mo[rnum[2]])
        btn4.setText(mo[rnum[3]])
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ramdom_vowel)

        var btn1 = findViewById(R.id.answer1_btn) as Button
        var btn2 = findViewById(R.id.answer2_btn) as Button
        var btn3 = findViewById(R.id.answer3_btn) as Button
        var btn4 = findViewById(R.id.answer4_btn) as Button
        var bigMoum = findViewById(R.id.problem_img) as ImageView
        var pronunciation = findViewById(R.id.Pronunciation) as TextView
        var rnd = Random()

        var moNum:Int = rnd.nextInt(20)
        var nextmo:Int = rnd.nextInt(20)

        // 이미지랑 발음 보여주기
        setNextMoum(bigMoum, pronunciation, btn1, btn2, btn3, btn4, moNum)
        Log.d("### I: ", "$moNum")
        // 올바른 버튼 클릭시 다음 자음으로 넘어가기
        btn1.setOnClickListener{
            if(moNum == rnum[0]){
                nextmo = rnd.nextInt(20)
                while(nextmo == moNum){
                    nextmo = rnd.nextInt(20)
                }
                moNum = nextmo
                Log.d("### I(1): ", "$nextmo")
                setNextMoum(bigMoum, pronunciation, btn1, btn2, btn3, btn4, nextmo)
            }else{
                Log.d("###not I(1): ", "$moNum")
                btn1.setTextColor(Color.RED)

                Handler().postDelayed({
                    btn1.setTextColor(Color.BLACK)
                }, 200)
            }
        }

        btn2.setOnClickListener{
            if(moNum == rnum[1]){
                nextmo = rnd.nextInt(20)
                while(nextmo == moNum){
                    nextmo = rnd.nextInt(20)
                }
                moNum = nextmo
                Log.d("### I(1): ", "$nextmo")
                setNextMoum(bigMoum, pronunciation, btn1, btn2, btn3, btn4, nextmo)
            }else{
                Log.d("###not I(2): ", "$moNum")
                btn2.setTextColor(Color.RED)

                Handler().postDelayed({
                    btn2.setTextColor(Color.BLACK)
                }, 200)
            }
        }
        btn3.setOnClickListener{
            if(moNum == rnum[2]){
                nextmo = rnd.nextInt(20)
                while(nextmo == moNum){
                    nextmo = rnd.nextInt(20)
                }
                moNum = nextmo
                Log.d("### I(1): ", "$nextmo")
                setNextMoum(bigMoum, pronunciation, btn1, btn2, btn3, btn4, nextmo)
            }else{
                Log.d("###not I(3): ", "$moNum")
                btn3.setTextColor(Color.RED)

                Handler().postDelayed({
                    btn3.setTextColor(Color.BLACK)
                }, 200)
            }
        }
        btn4.setOnClickListener{
            if(moNum == rnum[3]){
                nextmo = rnd.nextInt(20)
                while(nextmo == moNum){
                    nextmo = rnd.nextInt(20)
                }
                moNum = nextmo
                Log.d("### I(1): ", "$nextmo")
                setNextMoum(bigMoum, pronunciation, btn1, btn2, btn3, btn4, nextmo)
            }else{
                Log.d("###not I(4): ", "$moNum")
                btn4.setTextColor(Color.RED)

                Handler().postDelayed({
                    btn4.setTextColor(Color.BLACK)
                }, 200)
            }
        }


    }
}