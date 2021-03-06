package com.example.soundhangul

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import kotlinx.android.synthetic.main.activity_sound_card.*
import kotlinx.android.synthetic.main.activity_sound_card.tabs


class SoundCard : AppCompatActivity(){
    private val consonantList = arrayOf("ㄱ", "ㄴ", "ㄷ", "ㄹ", "ㅁ", "ㅂ", "ㅅ", "ㅇ", "ㅈ", "ㅊ", "ㅋ", "ㅌ", "ㅍ", "ㅎ")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sound_card)

        val width = resources.displayMetrics.widthPixels
        val height = resources.displayMetrics.heightPixels

        //버튼 사이즈 조절
        leftBtn.setLayoutParams(ConstraintLayout.LayoutParams(width/13,height/5))
        rightBtn.setLayoutParams(ConstraintLayout.LayoutParams(width/13,height/5))
        view_pager.setLayoutParams(ConstraintLayout.LayoutParams(width*2/3,height/2))
        tabs.setLayoutParams(ConstraintLayout.LayoutParams(width/1,height/8))


        //버튼 위치 조정
        val leftButtonParams = leftBtn.layoutParams as ConstraintLayout.LayoutParams
        leftButtonParams.topToTop = R.id.tabs
        leftButtonParams.topMargin = height/4
        leftButtonParams.startToStart = R.id.soundcard
        leftButtonParams.marginStart = width/10
        leftButtonParams.bottomToBottom = R.id.soundcard
        leftButtonParams.bottomMargin = height/4
        leftButtonParams.endToStart = R.id.view_pager
        leftButtonParams.marginEnd = width/30
        leftBtn.requestLayout()

        val viewPagerParams = view_pager.layoutParams as ConstraintLayout.LayoutParams
        viewPagerParams.topToTop = R.id.tabs
        viewPagerParams.topMargin = height/5
        viewPagerParams.startToEnd = R.id.leftBtn
        viewPagerParams.marginStart = width/30
        viewPagerParams.bottomToBottom = R.id.soundcard
        viewPagerParams.bottomMargin = height/5
        viewPagerParams.endToStart = R.id.rightBtn
        viewPagerParams.marginEnd = width/30
        view_pager.requestLayout()

        val rightButtonParams = rightBtn.layoutParams as ConstraintLayout.LayoutParams
        rightButtonParams.topToTop = R.id.tabs
        rightButtonParams.topMargin = height/4
        rightButtonParams.startToEnd = R.id.view_pager
        rightButtonParams.marginStart = width/30
        rightButtonParams.endToEnd = R.id.soundcard
        rightButtonParams.marginEnd = width/10
        rightButtonParams.bottomToBottom = R.id.soundcard
        rightButtonParams.bottomMargin = height/4
        rightBtn.requestLayout()

        val adapter = ConsonantPagerAdapter(supportFragmentManager)
        view_pager.adapter = adapter
        tabs.setupWithViewPager(view_pager)

        for(i in 0..13){
            tabs.getTabAt(i)?.setText(consonantList[i])
        }

        pageButton(view_pager.currentItem)


        leftBtn.setOnClickListener {
            view_pager.setCurrentItem(view_pager.currentItem-1, false)
            adapter.getItem(view_pager.currentItem-1)
            pageButton(view_pager.currentItem)
        }
        rightBtn.setOnClickListener {
            view_pager.setCurrentItem(view_pager.currentItem+1, false)
            adapter.getItem(view_pager.currentItem+1)
            pageButton(view_pager.currentItem)
        }
    }

    fun pageButton(pageItem: Int) {
        if(pageItem == 0 ){
            leftBtn.visibility = View.INVISIBLE
        } else if(pageItem == 13){
            rightBtn.visibility = View.INVISIBLE
        } else {
            leftBtn.visibility = View.VISIBLE
            rightBtn.visibility = View.VISIBLE
        }
    }
}