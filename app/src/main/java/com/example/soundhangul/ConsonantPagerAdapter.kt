package com.example.soundhangul

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.view.isVisible
import androidx.viewpager.widget.PagerAdapter


class ConsonantPagerAdapter(private val list: Array<Array<String>>): PagerAdapter() {
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val inflater = LayoutInflater.from(container.context)
        val view = inflater.inflate(R.layout.activity_consonant_card, container, false)
        val btnLeft: Button = view.findViewById(R.id.leftBtn)
        val btnRight: Button = view.findViewById(R.id.rightBtn)
        val btnList = arrayListOf<Button>()

        for(i in 0..10){
            val idString = "paBtn$i"
            val buttonID = view.resources.getIdentifier(idString, "id","com.example.soundhangul")
            btnList.add(view.findViewById(buttonID))
        }

        for (i in 0..9){
            btnList[i].setText(list[position][i])
            Log.d("position", btnList[i].toString() + list[position][i])
        }

        if(position == 0){
            btnLeft.isVisible = false
        }
        if(position == 13){
            btnRight.isVisible = false
        }

        container.addView(view)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, obj: Any) {
        container.removeView(obj as View?)
    }

    override fun isViewFromObject(view: View, obj: Any): Boolean {
        return view == obj
    }

    override fun getCount(): Int {
        return list.size
    }
}