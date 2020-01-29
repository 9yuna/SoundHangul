package com.example.soundhangul

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_practice_vowel.*
import java.util.*


class PracticeVowel : AppCompatActivity() {

    val vowel_list = listOf("","ㅏ","ㅑ","ㅓ","ㅕ","ㅗ","ㅛ","ㅜ","ㅠ","ㅡ","","ㅐ","ㅒ","ㅔ","ㅔ","ㅘ","ㅙ","ㅚ","ㅝ","ㅞ","ㅟ","ㅢ")
    var current_num = 1

    fun setImgBnt(currnet: Int){
        var file_name: String = "mo"+currnet
        var file_id: Int = resources.getIdentifier(file_name, "drawable", "com.example.soundhangul")
        problem_img.setImageDrawable(resources.getDrawable(file_id))

        var cnt: Int = 0
        var setting_done = mutableListOf<Boolean>(false, false, false)

        while(cnt < 3) {
            var random: Int = Random().nextInt(setting_done.size)
            var vowel_random: Int = Random().nextInt(vowel_list.size) + 1

            if(setting_done[random] == false) {
                setting_done[random] = true
                cnt++
                when (random) {
                    0 -> {
                        answer1_btn.setText(vowel_list[vowel_random])
                    }
                    1 -> {
                        answer2_btn.setText(vowel_list[vowel_random])
                    }
                    2 -> {
                        answer3_btn.setText(vowel_list[currnet])
                    }

                }
            }
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_practice_vowel)

        current_num = 1
        setImgBnt(current_num)

        answer1_btn.setOnClickListener{
            Toast.makeText(this,"정답이 아닙니다.", Toast.LENGTH_SHORT).show()
        }
        answer2_btn.setOnClickListener {
            Toast.makeText(this,"정답이 아닙니다.", Toast.LENGTH_SHORT).show()
        }
        answer3_btn.setOnClickListener{
            Toast.makeText(this,"정답입니다!", Toast.LENGTH_SHORT).show()
            current_num++
            setImgBnt(current_num)
        }

    }

}