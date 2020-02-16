package com.example.soundhangul


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.kakao.sdk.newtoneapi.TextToSpeechClient
import com.kakao.sdk.newtoneapi.TextToSpeechListener
import kotlinx.android.synthetic.main.fragment_ja_fragment14.*

class JaFragment14 : Fragment() {
    //for tts
    val TAG = "Kakao"
    var ttsClient : TextToSpeechClient? = null
    val NETWORK_STATE_CODE = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view:View? = inflater.inflate(R.layout.fragment_ja_fragment14, container, false)
        var Ja1 = view!!.findViewById<View>(R.id.paBtn0) as Button
        var Ja2 = view!!.findViewById<View>(R.id.paBtn1) as Button
        var Ja3 = view!!.findViewById<View>(R.id.paBtn3) as Button
        var Ja4 = view!!.findViewById<View>(R.id.paBtn4) as Button
        var Ja5 = view!!.findViewById<View>(R.id.paBtn5) as Button
        var Ja6 = view!!.findViewById<View>(R.id.paBtn6) as Button
        var Ja7 = view!!.findViewById<View>(R.id.paBtn7) as Button
        var Ja8 = view!!.findViewById<View>(R.id.paBtn8) as Button
        var Ja9 = view!!.findViewById<View>(R.id.paBtn9) as Button
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

        Ja1.setOnClickListener {
            ttsClient?.play(Ja1.text.toString())
            Log.d("button", Ja1.text.toString())
        }
        Ja2.setOnClickListener {
            ttsClient?.play(Ja2.text.toString())
            Log.d("button", Ja2.text.toString())
        }
        Ja3.setOnClickListener {
            ttsClient?.play(Ja3.text.toString())
            Log.d("button", Ja3.text.toString())
        }
        Ja4.setOnClickListener {
            ttsClient?.play(Ja4.text.toString())
            Log.d("button", Ja4.text.toString())
        }
        Ja5.setOnClickListener {
            ttsClient?.play(Ja5.text.toString())
            Log.d("button", Ja5.text.toString())
        }
        Ja6.setOnClickListener {
            ttsClient?.play(Ja6.text.toString())
            Log.d("button", Ja6.text.toString())
        }
        Ja7.setOnClickListener {
            ttsClient?.play(Ja7.text.toString())
            Log.d("button", Ja7.text.toString())
        }
        Ja8.setOnClickListener {
            ttsClient?.play(Ja8.text.toString())
            Log.d("button", Ja8.text.toString())
        }
        Ja9.setOnClickListener {
            ttsClient?.play(Ja9.text.toString())
            Log.d("button", Ja9.text.toString())
        }
        return view
    }


}
