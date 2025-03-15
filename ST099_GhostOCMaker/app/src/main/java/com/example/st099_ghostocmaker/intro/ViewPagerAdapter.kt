package com.example.st099_ghostocmaker.intro

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.st099_ghostocmaker.intro.fragment.Intro1
import com.example.st099_ghostocmaker.intro.fragment.Intro2
import com.example.st099_ghostocmaker.intro.fragment.Intro3

class ViewPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    // Danh sách các fragment bạn muốn hiển thị
    private val fragments = listOf(
        Intro1(),
        Intro2(),
        Intro3()
    )

    override fun getItemCount(): Int = fragments.size

    override fun createFragment(position: Int): Fragment = fragments[position]
}