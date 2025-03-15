package com.example.st099_ghostocmaker.MyAlbum

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.st099_ghostocmaker.R
import com.example.st099_ghostocmaker.databinding.ActivityMyAlbumBinding

class MyAlbum : AppCompatActivity() {
    private lateinit var binding: ActivityMyAlbumBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyAlbumBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}