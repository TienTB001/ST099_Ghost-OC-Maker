package com.example.st099_ghostocmaker.intro.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.st099_ghostocmaker.databinding.FragmentIntro3Binding

class Intro3Fragment:Fragment(){

    private lateinit var binding: FragmentIntro3Binding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding= FragmentIntro3Binding.inflate(layoutInflater)
        return binding.root
    }
}