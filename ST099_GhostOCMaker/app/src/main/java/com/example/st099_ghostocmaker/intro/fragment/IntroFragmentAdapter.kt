package com.example.st099_ghostocmaker.intro.fragment

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class IntroFragmentAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    // Danh sách các fragment bạn muốn hiển thị
    private val fragments = listOf(
        Intro1Fragment(),
        Intro2Fragment(),
        Intro3Fragment()
    )

    override fun getItemCount(): Int = fragments.size

    override fun createFragment(position: Int): Fragment = fragments[position]
}