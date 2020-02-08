package com.example.soundhangul

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_read_sound.*

class ReadSound : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_read_sound)

        val adapter = SamplePagerAdapter(supportFragmentManager)
        viewpager.adapter = adapter

        tab.setupWithViewPager(viewpager)
    }
}