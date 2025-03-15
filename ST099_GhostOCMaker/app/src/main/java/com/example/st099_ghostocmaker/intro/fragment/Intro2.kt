package com.example.st099_ghostocmaker.intro.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.st099_ghostocmaker.R
import com.example.st099_ghostocmaker.databinding.FragmentIntro2Binding

class Intro2 : Fragment() {

    private lateinit var binding: FragmentIntro2Binding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentIntro2Binding.inflate(layoutInflater)
        return binding.root
    }
}