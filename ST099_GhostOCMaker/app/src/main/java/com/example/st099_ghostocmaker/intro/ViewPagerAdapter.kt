package com.example.st099_ghostocmaker.intro

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.st099_ghostocmaker.intro.fragment.Intro1Fragment
import com.example.st099_ghostocmaker.intro.fragment.Intro2Fragment
import com.example.st099_ghostocmaker.intro.fragment.Intro3Fragment

class ViewPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    // Danh sách các fragment bạn muốn hiển thị
    private val fragments = listOf(
        Intro1Fragment(),
        Intro2Fragment(),
        Intro3Fragment()
    )

    override fun getItemCount(): Int = fragments.size

    override fun createFragment(position: Int): Fragment = fragments[position]
}