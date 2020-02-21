package com.example.soundhangul


import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.speech.tts.TextToSpeech
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
import java.util.*

class JaFragment1 : Fragment() {
    var tts: TextToSpeech? = null
    //for tts
    val TAG = "Kakao"
 //   var ttsClient : TextToSpeechClient? = null
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

        tts = TextToSpeech(activity, TextToSpeech.OnInitListener{
            if(it == TextToSpeech.SUCCESS) {
                val result: Int = tts!!.setLanguage(Locale.KOREA)
                //언어 데이터가 없거나 혹은 언어가 지원하지 않으면…
                //언어 데이터가 없거나 혹은 언어가 지원하지 않으면…
                if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                    Toast.makeText(activity, "이 언어는 지원하지 않습니다.", Toast.LENGTH_SHORT).show()
                } else {
                    //음성 톤
                    tts!!.setPitch(0.7f)
                    //읽는 속도
                    tts!!.setSpeechRate(1.2f)
                }
            }
        })

        Ja1.setOnClickListener {
            Log.d("button", Ja1.text.toString())
            //    ttsClient?.play(Ja1.text.toString())
            tts!!.speak(Ja1.text.toString(), TextToSpeech.QUEUE_FLUSH, null,null)
            // Log.d("btnClick", tmp)
        }
        Ja2.setOnClickListener {
            Log.d("button", Ja2.text.toString())
        //    ttsClient?.play(Ja2.text.toString())
            tts!!.speak(Ja2.text.toString(), TextToSpeech.QUEUE_FLUSH, null,null)
        }
        Ja3.setOnClickListener {
            Log.d("button", Ja3.text.toString())
            tts!!.speak(Ja3.text.toString(), TextToSpeech.QUEUE_FLUSH, null,null)
       //     ttsClient?.play(Ja3.text.toString())
        }
        Ja4.setOnClickListener {
            Log.d("button", Ja4.text.toString())
        //    ttsClient?.play(Ja4.text.toString())

        }
        Ja5.setOnClickListener {
            Log.d("button", Ja5.text.toString())
           // ttsClient?.play(Ja5.text.toString())

        }
        Ja6.setOnClickListener {
            Log.d("button", Ja6.text.toString())
         //   ttsClient?.play(Ja6.text.toString())

        }
        Ja7.setOnClickListener {
            Log.d("button", Ja7.text.toString())
          //  ttsClient?.play(Ja7.text.toString())

        }
        Ja8.setOnClickListener {
            Log.d("button", Ja8.text.toString())
          //  ttsClient?.play(Ja8.text.toString())

        }
        Ja9.setOnClickListener {
            Log.d("button", Ja9.text.toString())
        //   ttsClient?.play(Ja9.text.toString())

        }
        Ja10.setOnClickListener {
            Log.d("button", Ja10.text.toString())
       //     ttsClient?.play(Ja10.text.toString())

        }

        return view
    }

}
