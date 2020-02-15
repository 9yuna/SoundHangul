package com.example.soundhangul

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import androidx.viewpager.widget.ViewPager
import kotlinx.android.synthetic.main.activity_sound_card.*
import com.example.soundhangul.ConsonantPagerAdapter
import kotlinx.android.synthetic.main.activity_consonant_card.*
import kotlinx.android.synthetic.main.activity_sound_card.tabs


class SoundCard : AppCompatActivity(){
    private val consonantList = arrayOf("ㄱ", "ㄴ", "ㄷ", "ㄹ", "ㅁ", "ㅂ", "ㅅ", "ㅇ", "ㅈ", "ㅊ", "ㅋ", "ㅌ", "ㅍ", "ㅎ")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sound_card)

        val adapter = ConsonantPagerAdapter(cardList)
        view_pager.adapter = adapter
        // 탭 레아아웃에 뷰페이저 연결
        tabs.setupWithViewPager(view_pager)

        for(i in 0..13){
            tabs.getTabAt(i)?.setText(consonantList[i])
        }

        /*leftBtn.setOnClickListener {
            view_pager.setCurrentItem(view_pager.currentItem-1, false)
        }
        rightBtn.setOnClickListener {
            view_pager.setCurrentItem(view_pager.currentItem+1,false)
        }*/
    }

    companion object {
        val cardList = arrayOf(
            arrayOf("가", "갸", "거", "겨", "고","교","구","규","그", "기"),
            arrayOf("나", "냐", "너", "녀", "노","뇨","누","뉴","느", "니"),
            arrayOf("다", "댜", "더", "뎌", "도","됴","두","듀","드", "디"),
            arrayOf("라", "랴", "러", "려", "로","료","루","류","르", "리"),
            arrayOf("마", "먀", "머", "며", "모","묘","무","뮤","므", "미"),
            arrayOf("바", "뱌", "버", "벼", "보","뵤","부","뷰","브", "비"),
            arrayOf("사", "샤", "서", "셔", "소","쇼","수","슈","스", "시"),
            arrayOf("아", "야", "어", "여", "오","요","우","유","으", "이"),
            arrayOf("자", "쟈", "저", "져", "조","죠","주","쥬","즈", "즈"),
            arrayOf("차", "챠", "처", "쳐", "초","쵸","추","츄","츠", "치"),
            arrayOf("카", "캬", "커", "켜", "코","쿄","쿠","큐","크", "키"),
            arrayOf("타", "탸", "터", "텨", "토","툐","투","튜","트", "티"),
            arrayOf("파", "퍄", "퍼", "펴", "포","표","푸","퓨","프", "피"),
            arrayOf("하", "햐", "허", "혀", "호","효","후","휴","흐", "히")

        )
    }
}