package com.example.soundhangul

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class WordCaseAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm){
    // 가나 가누 구나 구누
    val PAGE_MAX_CNT = 4
    val context : Context? = null

    override fun getCount(): Int {
        return PAGE_MAX_CNT
    }

    override fun getItem(position: Int): Fragment? {
        val wfrag = when(position){
            0 -> GaNaFrag()
            1 -> GaNuFrag()
            2 -> GuNaFrag()
            3 -> GuNuFrag()
            else -> GaNaFrag()
        }
        return wfrag
    }

}