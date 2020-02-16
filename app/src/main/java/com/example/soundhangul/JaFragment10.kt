package com.example.soundhangul


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import kotlinx.android.synthetic.main.fragment_ja_fragment10.*

class JaFragment10 : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view:View? = inflater.inflate(R.layout.fragment_ja_fragment10, container, false)
        var Ja1 = view!!.findViewById<View>(R.id.paBtn0) as Button
        var Ja2 = view!!.findViewById<View>(R.id.paBtn1) as Button
        var Ja3 = view!!.findViewById<View>(R.id.paBtn3) as Button
        var Ja4 = view!!.findViewById<View>(R.id.paBtn4) as Button
        var Ja5 = view!!.findViewById<View>(R.id.paBtn5) as Button
        var Ja6 = view!!.findViewById<View>(R.id.paBtn6) as Button
        var Ja7 = view!!.findViewById<View>(R.id.paBtn7) as Button
        var Ja8 = view!!.findViewById<View>(R.id.paBtn8) as Button
        var Ja9 = view!!.findViewById<View>(R.id.paBtn9) as Button
        Ja1.setOnClickListener {
            Log.d("button", Ja1.text.toString())
        }
        Ja2.setOnClickListener {
            Log.d("button", Ja2.text.toString())
        }
        Ja3.setOnClickListener {
            Log.d("button", Ja3.text.toString())
        }
        Ja4.setOnClickListener {
            Log.d("button", Ja4.text.toString())
        }
        Ja5.setOnClickListener {
            Log.d("button", Ja5.text.toString())
        }
        Ja6.setOnClickListener {
            Log.d("button", Ja6.text.toString())
        }
        Ja7.setOnClickListener {
            Log.d("button", Ja7.text.toString())
        }
        Ja8.setOnClickListener {
            Log.d("button", Ja8.text.toString())
        }
        Ja9.setOnClickListener {
            Log.d("button", Ja9.text.toString())
        }
        return view
    }


}
