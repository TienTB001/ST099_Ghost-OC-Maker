package com.example.st099_ghostocmaker.intro.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.st099_ghostocmaker.databinding.FragmentIntro1Binding

class Intro1 : Fragment() {

    private lateinit var binding: FragmentIntro1Binding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentIntro1Binding.inflate(layoutInflater)
        return binding.root
    }
}
