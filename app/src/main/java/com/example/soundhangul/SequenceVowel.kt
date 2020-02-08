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
import com.kakao.sdk.newtoneapi.SpeechRecognizerManager
import com.kakao.sdk.newtoneapi.TextToSpeechClient
import com.kakao.sdk.newtoneapi.TextToSpeechListener
import com.kakao.sdk.newtoneapi.TextToSpeechManager
import java.util.*

class SequenceVowel : AppCompatActivity() {
    var mo = arrayOf("ㅏ", "ㅑ", "ㅓ", "ㅕ", "ㅗ","ㅛ","ㅜ", "ㅠ", "ㅡ", "ㅣ", "ㅐ", "ㅒ", "ㅔ", "ㅖ", "ㅘ", "ㅙ", "ㅚ","ㅝ","ㅞ","ㅟ","ㅢ")
    var moStr = arrayOf("아", "야", "어", "여", "오","요","우", "유", "으", "이", "애", "얘", "에", "예", "와", "왜", "외","워","웨","위","의")
    var rnum = intArrayOf(0, 0, 0, 0)

    //for tts
    val TAG = "Kakao"
    var ttsClient : TextToSpeechClient? = null
    val NETWORK_STATE_CODE = 0

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

    fun setNextMoum(bigMoum: ImageView, pronunciation: TextView, btn1 :Button, btn2 :Button, btn3 :Button, btn4 :Button, moNum : Int){
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
        setContentView(R.layout.activity_sequence_vowel)

        var btn1 = findViewById(R.id.answer1_btn) as Button
        var btn2 = findViewById(R.id.answer2_btn) as Button
        var btn3 = findViewById(R.id.answer3_btn) as Button
        var btn4 = findViewById(R.id.answer4_btn) as Button
        var bigMoum = findViewById(R.id.problem_img) as ImageView
        var pronunciation = findViewById(R.id.Pronunciation) as TextView

        var moNum:Int = 0
        SpeechRecognizerManager.getInstance().initializeLibrary(this)
        TextToSpeechManager.getInstance().initializeLibrary(this)
        ttsClient = TextToSpeechClient.Builder()
            .setSpeechMode(TextToSpeechClient.NEWTONE_TALK_1)     // 음성합성방식
            .setSpeechSpeed(1.0)            // 발음 속도(0.5~4.0)
            .setSpeechVoice(TextToSpeechClient.VOICE_WOMAN_READ_CALM)  //TTS 음색 모드 설정(여성 차분한 낭독체)
            .setListener(object : TextToSpeechListener {
                //아래 두개의 메소드만 구현해 주면 된다. 음성합성이 종료될 때 호출된다.
                override fun onFinished() {
                    val intSentSize = ttsClient?.getSentDataSize()      //세션 중에 전송한 데이터 사이즈
                    val intRecvSize = ttsClient?.getReceivedDataSize()  //세션 중에 전송받은 데이터 사이즈

                    val strInacctiveText = "handleFinished() SentSize : $intSentSize  RecvSize : $intRecvSize"

                    Log.i(TAG, strInacctiveText)
                }

                override fun onError(code: Int, message: String?) {
                    Log.d(TAG, code.toString())
                }
            })
            .build()
        //----------

        // 이미지랑 발음 보여주기
        setNextMoum(bigMoum, pronunciation, btn1, btn2, btn3, btn4, moNum)
        Log.d("### I: ", "$moNum")
        // 올바른 버튼 클릭시 다음 자음으로 넘어가기
        btn1.setOnClickListener{
            if(moNum == rnum[0]){
                ttsClient?.play(moStr[rnum[0]])
                moNum++
                if(moNum == 21) moNum = 0;
                Log.d("### I(1): ", "$moNum")
                Thread.sleep(1000L)
                setNextMoum(bigMoum, pronunciation, btn1, btn2, btn3, btn4, moNum)
                Thread.sleep(1000L)
                ttsClient?.play(moStr[moNum])
            }else{
                Log.d("###not I(1): ", "$moNum")
                btn1.setTextColor(RED)
                ttsClient?.play(moStr[rnum[0]])
                Handler().postDelayed({
                    btn1.setTextColor(BLACK)
                }, 200)
            }
        }

        btn2.setOnClickListener{
            if(moNum == rnum[1]){
                ttsClient?.play(moStr[rnum[1]])
                moNum++
                if(moNum == 21) moNum = 0;
                Log.d("### I(2): ", "$moNum")
                Thread.sleep(1000L)
                setNextMoum(bigMoum, pronunciation, btn1, btn2, btn3, btn4, moNum)
                Thread.sleep(1000L)
                ttsClient?.play(moStr[moNum])
            }else{
                Log.d("###not I(2): ", "$moNum")
                btn2.setTextColor(RED)
                ttsClient?.play(moStr[rnum[1]])
                Handler().postDelayed({
                    btn2.setTextColor(BLACK)
                }, 200)
            }
        }
        btn3.setOnClickListener{
            if(moNum == rnum[2]){
                ttsClient?.play(moStr[rnum[2]])
                moNum++
                if(moNum == 21) moNum = 0;
                Log.d("### I(3): ", "$moNum")
                Thread.sleep(1000L)
                setNextMoum(bigMoum, pronunciation, btn1, btn2, btn3, btn4, moNum)
                Thread.sleep(1000L)
                ttsClient?.play(moStr[moNum])
            }else{
                Log.d("###not I(3): ", "$moNum")
                btn3.setTextColor(RED)
                ttsClient?.play(moStr[rnum[2]])
                Handler().postDelayed({
                    btn3.setTextColor(BLACK)
                }, 200)
            }
        }
        btn4.setOnClickListener{
            if(moNum == rnum[3]){
                ttsClient?.play(moStr[rnum[3]])
                moNum++
                if(moNum == 21) moNum = 0;
                Log.d("### I(4): ", "$moNum")
                Thread.sleep(1000L)
                setNextMoum(bigMoum, pronunciation, btn1, btn2, btn3, btn4, moNum)
                Thread.sleep(1000L)
                ttsClient?.play(moStr[moNum])
            }else{
                Log.d("###not I(4): ", "$moNum")
                btn4.setTextColor(RED)
                ttsClient?.play(moStr[rnum[3]])
                Handler().postDelayed({
                    btn4.setTextColor(BLACK)
                }, 200)
            }
        }


    }
}