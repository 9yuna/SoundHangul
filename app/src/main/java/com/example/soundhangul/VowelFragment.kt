package com.example.soundhangul

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class VowelFragment : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.activity_vowel_fragment, container, false)

        return view
    }

    override fun title(): String {
        return "Second"
    }
}