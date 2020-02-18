package com.example.soundhangul

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.kakao.sdk.newtoneapi.SpeechRecognizerManager
import com.kakao.sdk.newtoneapi.TextToSpeechClient
import com.kakao.sdk.newtoneapi.TextToSpeechListener
import com.kakao.sdk.newtoneapi.TextToSpeechManager
import kotlinx.android.synthetic.main.activity_sound_card.*
import kotlinx.android.synthetic.main.activity_sound_card.tabs


class SoundCard : AppCompatActivity(){
    private val consonantList = arrayOf("ㄱ", "ㄴ", "ㄷ", "ㄹ", "ㅁ", "ㅂ", "ㅅ", "ㅇ", "ㅈ", "ㅊ", "ㅋ", "ㅌ", "ㅍ", "ㅎ")
    //for tts
    val TAG = "Kakao"
    var ttsClient : TextToSpeechClient? = null
    val NETWORK_STATE_CODE = 0

    fun btnclick(str: String){
        ttsClient?.play(str)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sound_card)

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

        val adapter = ConsonantPagerAdapter(supportFragmentManager)
        view_pager.adapter = adapter
        // 탭 레아아웃에 뷰페이저 연결
        tabs.setupWithViewPager(view_pager)

        for(i in 0..13){
            tabs.getTabAt(i)?.setText(consonantList[i])
        }

        leftBtn.setOnClickListener {
            view_pager.setCurrentItem(view_pager.currentItem-1, false)
            adapter.getItem(view_pager.currentItem-1)
        }
        rightBtn.setOnClickListener {
            view_pager.setCurrentItem(view_pager.currentItem+1, false)
            adapter.getItem(view_pager.currentItem+1)
        }
    }
}