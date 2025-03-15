package com.example.st099_ghostocmaker.intro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.st099_ghostocmaker.databinding.ActivityIntroBinding
import com.example.st099_ghostocmaker.intro.fragment.IntroFragmentAdapter
import com.example.st099_ghostocmaker.permission.Permission

class Intro : AppCompatActivity() {

    private lateinit var binding: ActivityIntroBinding
    private var pressedLastPage = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIntroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Khởi tạo adapter với 3 fragment
        val adapter = IntroFragmentAdapter(this)
        binding.viewPager.adapter = adapter

        // Nếu bạn dùng DotsIndicator, kết nối nó với viewPager
        binding.dotsIndicator.setViewPager2(binding.viewPager)

        binding.txtNext.setOnClickListener {
            val current = binding.viewPager.currentItem
            // Trang cuối có index = adapter.itemCount - 1
            if (current < adapter.itemCount - 1) {
                // Nếu chưa ở trang cuối => chuyển sang trang kế
                binding.viewPager.currentItem = current + 1
            } else {
                // Đã ở trang cuối
                if (pressedLastPage) {
                    // Nếu đã nhấn Next một lần ở trang cuối, lần này chuyển màn hình
                    startActivity(Intent(this, Permission::class.java))
                    finish()
                } else {
                    // Lần đầu nhấn Next ở trang cuối => chỉ đánh dấu, chưa chuyển
                    pressedLastPage = true
                }
            }
        }
    }
}
