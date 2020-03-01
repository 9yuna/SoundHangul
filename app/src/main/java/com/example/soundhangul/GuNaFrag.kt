package com.example.soundhangul


import android.os.Bundle
import android.speech.tts.TextToSpeech
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import java.util.*

class GuNaFrag : Fragment() {
    var tts: TextToSpeech? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view: View? =  inflater.inflate(R.layout.fragment_guna_frag, container, false)



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

}
