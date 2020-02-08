package com.example.soundhangul

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class SamplePagerAdapter : FragmentPagerAdapter {

    private val list: ArrayList<BaseFragment> = ArrayList();

    constructor(fragmentManager: FragmentManager) : super(fragmentManager) {
        list.add(ConsonantFragment())
        list.add(VowelFragment())
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return list[position].title()
    }

    override fun getItem(position: Int): Fragment {
        return list.get(position)
    }

    override fun getCount(): Int {
        return list.size
    }
}