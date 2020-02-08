package com.example.soundhangul


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class ConsonantFragement : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_consonant, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    companion object {
        private const val num = "consonant"
        @JvmStatic
        fun newInstance(Number: Int): ConsonantFragement {
            return ConsonantFragement().apply {
                arguments = Bundle().apply {
                    putInt(num, Number)
                }
            }
        }
    }

}
