package com.example.st099_ghostocmaker.intro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.st099_ghostocmaker.databinding.ActivityIntroBinding
import com.example.st099_ghostocmaker.intro.fragment.IntroFragmentAdapter
import com.example.st099_ghostocmaker.ui.PermissionActivity

class IntroActivity : AppCompatActivity() {

    private lateinit var binding: ActivityIntroBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIntroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = IntroFragmentAdapter(this)
        binding.viewPager.adapter = adapter

        binding.dotsIndicator.setViewPager2(binding.viewPager)

        binding.txtNext.setOnClickListener {
            val current = binding.viewPager.currentItem
            if (current < adapter.itemCount - 1) {
                binding.viewPager.currentItem = current + 1
            } else {
                    startActivity(Intent(this, PermissionActivity::class.java))
                    finish()
                }
            }
        }
    }
