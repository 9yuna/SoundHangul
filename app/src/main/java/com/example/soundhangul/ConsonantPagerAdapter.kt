package com.example.soundhangul

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter


class ConsonantPagerAdapter(fm: FragmentManager): FragmentPagerAdapter(fm) {
    val PAGE_MAX_CNT = 14
    var context: Context? = null
    private val consonantList = arrayOf("ㄱ", "ㄴ", "ㄷ", "ㄹ", "ㅁ", "ㅂ", "ㅅ", "ㅇ", "ㅈ", "ㅊ", "ㅋ", "ㅌ", "ㅍ", "ㅎ")

    override fun getCount(): Int {
        return PAGE_MAX_CNT
    }

    override fun getItem(position: Int): Fragment? {
        val fragment = when(position){
            0 -> JaFragment1()
            1 -> JaFragment2()
            2 -> JaFragment3()
            3 -> JaFragment4()
            4 -> JaFragment5()
            5 -> JaFragment6()
            6 -> JaFragment7()
            7 -> JaFragment8()
            8 -> JaFragment9()
            9 -> JaFragment10()
            10 -> JaFragment11()
            11 -> JaFragment12()
            12 -> JaFragment13()
            13 -> JaFragment14()
            else -> JaFragment1()
        }
        return fragment
    }

    override fun getPageTitle(position: Int): CharSequence? {
        val title = consonantList[position]
        return title
    }
}