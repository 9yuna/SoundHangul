package com.example.soundhangul

import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {
    abstract fun title(): String
}