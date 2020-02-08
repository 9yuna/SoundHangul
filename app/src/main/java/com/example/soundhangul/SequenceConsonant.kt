package com.example.soundhangul

import android.content.pm.PackageManager
import android.graphics.Color.BLACK
import android.graphics.Color.RED
import android.os.Bundle
import android.os.Handler
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.kakao.sdk.newtoneapi.SpeechRecognizerManager
import com.kakao.sdk.newtoneapi.TextToSpeechClient
import com.kakao.sdk.newtoneapi.TextToSpeechListener
import com.kakao.sdk.newtoneapi.TextToSpeechManager
import java.util.*
import java.util.jar.Manifest

class SequenceConsonant : AppCompatActivity() {
    var ja = arrayOf("ㄱ", "ㄴ", "ㄷ", "ㄹ", "ㅁ", "ㅂ", "ㅅ", "ㅇ", "ㅈ", "ㅊ", "ㅋ", "ㅌ", "ㅍ", "ㅎ")
    var str = arrayOf("기역", "니은", "디귿", "리을", "미음", "비읍", "시옷", "이응", "지읒", "치읒", "키읔", "티긑", "피흪", "히흫")
    var rnum = intArrayOf(0, 0, 0, 0)

    //for tts
    val TAG = "Kakao"
    var ttsClient : TextToSpeechClient? = null
    val NETWORK_STATE_CODE = 0

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

        var btn1 = findViewById(R.id.button) as Button
        var btn2 = findViewById(R.id.button2) as Button
        var btn3 = findViewById(R.id.button3) as Button
        var btn4 = findViewById(R.id.button4) as Button
        var bigJaum = findViewById(R.id.BigJaeum) as ImageView
        var pronunciation = findViewById(R.id.Pronunciation) as TextView

            var jaumNum: Int = 0
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
            setNextJaum(bigJaum, pronunciation, btn1, btn2, btn3, btn4, jaumNum)
            ttsClient?.play(str[jaumNum])
            Log.d("### I: ", "$jaumNum")
            // 올바른 버튼 클릭시 다음 자음으로 넘어가기
            btn1.setOnClickListener {
                if (jaumNum == rnum[0]) {
                    ttsClient?.play(str[rnum[0]])
                    jaumNum++
                    if (jaumNum == 14) jaumNum = 0
                    Log.d("### I(1): ", "$jaumNum")
                    Thread.sleep(1000L)
                    setNextJaum(bigJaum, pronunciation, btn1, btn2, btn3, btn4, jaumNum)
                    Thread.sleep(1000L)
                    ttsClient?.play(str[jaumNum])
                } else {
                    Log.d("###not I(1): ", "$jaumNum")
                    btn1.setTextColor(RED)
                    ttsClient?.play(str[rnum[0]])

                    Handler().postDelayed({
                        btn1.setTextColor(BLACK)
                    }, 200)
                }
            }

            btn2.setOnClickListener {
                if (jaumNum == rnum[1]) {
                    ttsClient?.play(str[rnum[1]])
                    jaumNum++
                    if (jaumNum == 14) jaumNum = 0
                    Log.d("### I(2): ", "$jaumNum")
                    Thread.sleep(1000L)
                    setNextJaum(bigJaum, pronunciation, btn1, btn2, btn3, btn4, jaumNum)
                    Thread.sleep(1000L)
                    ttsClient?.play(str[jaumNum])
                } else {
                    Log.d("###not I(2): ", "$jaumNum")
                    btn2.setTextColor(RED)
                    ttsClient?.play(str[rnum[1]])
                    Handler().postDelayed({
                        btn2.setTextColor(BLACK)
                    }, 200)
                }
            }
            btn3.setOnClickListener {
                if (jaumNum == rnum[2]) {
                    ttsClient?.play(str[rnum[2]])
                    jaumNum++
                    if (jaumNum == 14) jaumNum = 0
                    Log.d("### I(3): ", "$jaumNum")
                    Thread.sleep(1000L)
                    setNextJaum(bigJaum, pronunciation, btn1, btn2, btn3, btn4, jaumNum)
                    Thread.sleep(1000L)
                    ttsClient?.play(str[jaumNum])
                } else {
                    Log.d("###not I(3): ", "$jaumNum")
                    btn3.setTextColor(RED)
                    ttsClient?.play(str[rnum[2]])
                    Handler().postDelayed({
                        btn3.setTextColor(BLACK)
                    }, 200)
                }
            }
            btn4.setOnClickListener {
                if (jaumNum == rnum[3]) {
                    ttsClient?.play(str[rnum[3]])
                    jaumNum++
                    if (jaumNum == 14) jaumNum = 0
                    Log.d("### I(4): ", "$jaumNum")
                    Thread.sleep(1000L)
                    setNextJaum(bigJaum, pronunciation, btn1, btn2, btn3, btn4, jaumNum)
                    Thread.sleep(1000L)
                    ttsClient?.play(str[jaumNum])
                } else {
                    Log.d("###not I(4): ", "$jaumNum")
                    btn4.setTextColor(RED)
                    ttsClient?.play(str[rnum[3]])
                    Handler().postDelayed({
                        btn4.setTextColor(BLACK)
                    }, 200)
                }
            }
        //}
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when (requestCode) {
            NETWORK_STATE_CODE -> {
                if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        TextToSpeechManager.getInstance().finalizeLibrary()
    }
}
