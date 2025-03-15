package com.example.st099_ghostocmaker.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.st099_ghostocmaker.databinding.ActivityMyAlbumBinding

class MyAlbumActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMyAlbumBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyAlbumBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}