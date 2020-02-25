package com.example.soundhangul

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_sound_card.*
import kotlinx.android.synthetic.main.activity_sound_card.tabs


class SoundCard : AppCompatActivity(){
    private val consonantList = arrayOf("ㄱ", "ㄴ", "ㄷ", "ㄹ", "ㅁ", "ㅂ", "ㅅ", "ㅇ", "ㅈ", "ㅊ", "ㅋ", "ㅌ", "ㅍ", "ㅎ")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sound_card)

        val adapter = ConsonantPagerAdapter(supportFragmentManager)
        view_pager.adapter = adapter
        tabs.setupWithViewPager(view_pager)

        for(i in 0..13){
            tabs.getTabAt(i)?.setText(consonantList[i])
        }

        leftBtn.setOnClickListener {
            view_pager.setCurrentItem(view_pager.currentItem-1, false)
            adapter.getItem(view_pager.currentItem-1)
        }
        rightBtn.setOnClickListener {
            view_pager.setCurrentItem(view_pager.currentItem+1, false)
            adapter.getItem(view_pager.currentItem+1)
        }
    }

}