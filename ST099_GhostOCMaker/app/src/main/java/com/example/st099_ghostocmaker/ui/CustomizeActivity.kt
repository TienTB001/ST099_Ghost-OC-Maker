package com.example.st099_ghostocmaker.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.st099_ghostocmaker.databinding.ActivityCustomizeBinding

class CustomizeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCustomizeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCustomizeBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}