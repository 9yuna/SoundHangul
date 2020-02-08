package com.example.soundhangul

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import com.example.soundhangul.PagerAdapter
import kotlinx.android.synthetic.main.activity_sound_card.*

class SoundCard : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sound_card)

        // 어댑터 생성 <어댑터는 여러 프래그먼트를 만들고 관리함>
        val Adapter = PagerAdapter(supportFragmentManager)

        // 뷰 페이저에 어댑터 연결
        view_pager.adapter = Adapter

        // 탭 레아아웃에 뷰페이저 연결
        tabs.setupWithViewPager(view_pager)

        // 탭뷰 각각 이름 만들기
        val feel=arrayOf("자음","모")
        for(i in 0..1)
            tabs.getTabAt(i)?.setText(feel[i])
    }
}