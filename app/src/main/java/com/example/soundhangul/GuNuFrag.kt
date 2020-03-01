package com.example.soundhangul


import android.graphics.Color
import android.os.Bundle
import android.speech.tts.TextToSpeech
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_practice_consonant.*
import java.util.*

class GuNuFrag : Fragment() {
    var tts: TextToSpeech? = null
    var case=0
    //val words = mapOf(0 to "ㄴ ㅏ ㅂ ㅣ", 1 to "ㄴ ㅏ ㅁ ㅜ", 2 to "ㄱ ㅠ ㄴ ㅏ", 3 to "ㅇ ㅜ ㅇ ㅠ")
    val words = mapOf(0 to "ㅎ ㅗ ㅇ ㅜ", 1 to "ㅇ ㅡ ㅇ ㅟ", 2 to "ㄱ ㅠ ㄴ ㅡ", 3 to "ㅇ ㅜ ㅇ ㅠ")
    var ja = arrayOf("ㄱ", "ㄴ", "ㄷ", "ㄹ", "ㅁ", "ㅂ", "ㅅ", "ㅇ", "ㅈ", "ㅊ", "ㅋ", "ㅌ", "ㅍ", "ㅎ")
    var mo = arrayOf("ㅏ", "ㅑ", "ㅓ", "ㅕ", "ㅗ","ㅛ","ㅜ", "ㅠ", "ㅡ", "ㅣ", "ㅐ", "ㅒ", "ㅔ", "ㅖ", "ㅘ", "ㅙ", "ㅚ","ㅝ","ㅞ","ㅟ","ㅢ")

    var wordNum = intArrayOf(0, 0, 0, 0)

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
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view: View? =  inflater.inflate(R.layout.fragment_gunu_frag, container, false)

        var firstJa =  view!!.findViewById<View>(R.id.ja1) as TextView
        var firstMo =  view!!.findViewById<View>(R.id.mo1) as TextView
        var secondJa = view!!.findViewById<View>(R.id.ja2) as TextView
        var secondMo = view!!.findViewById<View>(R.id.mo2) as TextView

        getRandomWord()
        findWordByCase(case)
        MakeWord().getWord(wordNum)


        firstJa.setText(ja[wordNum[0]])
        firstMo.setText(mo[wordNum[1]])
        secondJa.setText(ja[wordNum[2]])
        secondMo.setText(mo[wordNum[3]])
        firstJa.setTextColor(Color.rgb(202,204,206))
        firstMo.setTextColor(Color.rgb(202,204,206))
        secondJa.setTextColor(Color.rgb(202,204,206))
        secondMo.setTextColor(Color.rgb(202,204,206))


        tts = TextToSpeech(activity, TextToSpeech.OnInitListener{
            if(it == TextToSpeech.SUCCESS) {
                val result: Int = tts!!.setLanguage(Locale.KOREA)
                if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                    Toast.makeText(activity, "이 언어는 지원하지 않습니다.", Toast.LENGTH_SHORT).show()
                } else {
                    tts!!.setPitch(0.7f)
                    tts!!.setSpeechRate(1.2f)
                }
            }
        })
        return view
    }

    fun retWordNum() : IntArray {
        return wordNum
    }
}
