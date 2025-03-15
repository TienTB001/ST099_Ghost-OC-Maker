package com.example.st099_ghostocmaker.ui

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.Manifest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.st099_ghostocmaker.MainActivity
import com.example.st099_ghostocmaker.R
import com.example.st099_ghostocmaker.databinding.ActivityPermissionBinding

class PermissionActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPermissionBinding
    private var isBoolean = true

    // Yêu cầu quyền thông báo
    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            updateImageView(true)
        } else {
            updateImageView(false)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPermissionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Kiểm tra quyền thông báo khi mở app
        updateImageView(hasNotificationPermission())

        // Khi nhấn vào `imageView6`, yêu cầu quyền nếu chưa có
        binding.imageView6.setOnClickListener {
            if (!hasNotificationPermission()) {
                requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
            } else {
                isBoolean = !isBoolean
                updateImageView(isBoolean)
            }
        }

        // Nhấn "Tiếp tục" để mở `MainActivity`
        binding.txtContinue.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }


    private fun hasNotificationPermission(): Boolean {
        return if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU) {
            true // Android 12 trở xuống không cần xin quyền
        } else {
            ContextCompat.checkSelfPermission(
                this, Manifest.permission.POST_NOTIFICATIONS
            ) == PackageManager.PERMISSION_GRANTED
        }
    }


    private fun updateImageView(isGranted: Boolean) {
        if (isGranted) {
            binding.imageView6.setImageResource(R.drawable.switch_on)
        } else {
            binding.imageView6.setImageResource(R.drawable.switch_off)
        }
    }
}