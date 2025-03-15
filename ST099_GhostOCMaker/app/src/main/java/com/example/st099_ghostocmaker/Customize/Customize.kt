package com.example.st099_ghostocmaker.Customize

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.st099_ghostocmaker.R
import com.example.st099_ghostocmaker.databinding.ActivityCustomizeBinding

class Customize : AppCompatActivity() {
    private lateinit var binding: ActivityCustomizeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCustomizeBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}