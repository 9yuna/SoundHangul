package com.example.soundhangul


import android.os.Bundle
import android.speech.tts.TextToSpeech
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import java.util.*

class JaFragment4 : Fragment() {
    var tts: TextToSpeech? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view:View? = inflater.inflate(R.layout.fragment_ja_fragment4, container, false)

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
                if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                    Toast.makeText(activity, "이 언어는 지원하지 않습니다.", Toast.LENGTH_SHORT).show()
                } else {
                    tts!!.setPitch(0.7f)
                    tts!!.setSpeechRate(1.2f)
                }
            }
        })

        Ja1.setOnClickListener {
            tts!!.speak(Ja1.text.toString(), TextToSpeech.QUEUE_FLUSH, null,null)
        }
        Ja2.setOnClickListener {
            tts!!.speak(Ja2.text.toString(), TextToSpeech.QUEUE_FLUSH, null,null)
        }
        Ja3.setOnClickListener {
            tts!!.speak(Ja3.text.toString(), TextToSpeech.QUEUE_FLUSH, null,null)
        }
        Ja4.setOnClickListener {
            tts!!.speak(Ja4.text.toString(), TextToSpeech.QUEUE_FLUSH, null,null)
        }
        Ja5.setOnClickListener {
            tts!!.speak(Ja5.text.toString(), TextToSpeech.QUEUE_FLUSH, null,null)
        }
        Ja6.setOnClickListener {
            tts!!.speak(Ja6.text.toString(), TextToSpeech.QUEUE_FLUSH, null,null)
        }
        Ja7.setOnClickListener {
            tts!!.speak(Ja7.text.toString(), TextToSpeech.QUEUE_FLUSH, null,null)
        }
        Ja8.setOnClickListener {
            tts!!.speak(Ja8.text.toString(), TextToSpeech.QUEUE_FLUSH, null,null)
        }
        Ja9.setOnClickListener {
            tts!!.speak(Ja9.text.toString(), TextToSpeech.QUEUE_FLUSH, null,null)
        }
        Ja10.setOnClickListener {
            tts!!.speak(Ja10.text.toString(), TextToSpeech.QUEUE_FLUSH, null,null)
        }

        return view
    }


}
