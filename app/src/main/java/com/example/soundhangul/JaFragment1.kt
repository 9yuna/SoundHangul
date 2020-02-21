package com.example.soundhangul


import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.kakao.sdk.newtoneapi.SpeechRecognizerManager
import com.kakao.sdk.newtoneapi.TextToSpeechClient
import com.kakao.sdk.newtoneapi.TextToSpeechListener
import com.kakao.sdk.newtoneapi.TextToSpeechManager
import kotlinx.android.synthetic.main.fragment_ja_fragment1.*

class JaFragment1 : Fragment() {

    //for tts
    val TAG = "Kakao"
    var ttsClient : TextToSpeechClient? = null
    val NETWORK_STATE_CODE = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view: View? =  inflater.inflate(R.layout.fragment_ja_fragment1, container, false)

        var Ja1 = view!!.findViewById<View>(R.id.paBtn0) as Button
        var Ja2 = view!!.findViewById<View>(R.id.paBtn1) as Button
        var Ja3 = view!!.findViewById<View>(R.id.paBtn2) as Button
        var Ja4 = view!!.findViewById<View>(R.id.paBtn3) as Button
        var Ja5 = view!!.findViewById<View>(R.id.paBtn4) as Button
        var Ja6 = view!!.findViewById<View>(R.id.paBtn5) as Button
        var Ja7 = view!!.findViewById<View>(R.id.paBtn6) as Button
        var Ja8 = view!!.findViewById<View>(R.id.paBtn7) as Button
        var Ja9 = view!!.findViewById<View>(R.id.paBtn8) as Button
        var Ja10 = view!!.findViewById<View>(R.id.paBtn9) as Button

        SpeechRecognizerManager.getInstance().initializeLibrary(App.instance.context())
        TextToSpeechManager.getInstance().initializeLibrary(App.instance.context())
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
            Log.d("button", Ja1.text.toString())
            ttsClient?.play(Ja1.text.toString())
            SoundCard().btnclick(Ja1.text.toString())
        }
        Ja2.setOnClickListener {
            Log.d("button", Ja2.text.toString())
            ttsClient?.play(Ja2.text.toString())
            SoundCard().btnclick(Ja2.text.toString())
        }
        Ja3.setOnClickListener {
            Log.d("button", Ja3.text.toString())
            ttsClient?.play(Ja3.text.toString())
            SoundCard().btnclick(Ja3.text.toString())
        }
        Ja4.setOnClickListener {
            Log.d("button", Ja4.text.toString())
            ttsClient?.play(Ja4.text.toString())
            SoundCard().btnclick(Ja4.text.toString())
        }
        Ja5.setOnClickListener {
            Log.d("button", Ja5.text.toString())
            ttsClient?.play(Ja5.text.toString())
            SoundCard().btnclick(Ja5.text.toString())
        }
        Ja6.setOnClickListener {
            Log.d("button", Ja6.text.toString())
            ttsClient?.play(Ja6.text.toString())
            SoundCard().btnclick(Ja6.text.toString())
        }
        Ja7.setOnClickListener {
            Log.d("button", Ja7.text.toString())
            ttsClient?.play(Ja7.text.toString())
            SoundCard().btnclick(Ja7.text.toString())
        }
        Ja8.setOnClickListener {
            Log.d("button", Ja8.text.toString())
            ttsClient?.play(Ja8.text.toString())
            SoundCard().btnclick(Ja8.text.toString())
        }
        Ja9.setOnClickListener {
            Log.d("button", Ja9.text.toString())
            ttsClient?.play(Ja9.text.toString())
            SoundCard().btnclick(Ja9.text.toString())
        }
        Ja10.setOnClickListener {
            Log.d("button", Ja10.text.toString())
            ttsClient?.play(Ja10.text.toString())
            SoundCard().btnclick(Ja10.text.toString())
        }

        return view
    }
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when (requestCode) {
            NETWORK_STATE_CODE -> {
                if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(App.instance.context(), "Permission Denied", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(App.instance.context(), "Permission Granted", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        TextToSpeechManager.getInstance().finalizeLibrary()
    }
}
