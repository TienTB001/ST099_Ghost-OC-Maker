package com.example.st099_ghostocmaker

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.chibimaker.create.avatar.cutechibi.utils.SystemUtils.onSingleClickOut
import com.example.st099_ghostocmaker.databinding.ActivityMainBinding
import com.example.st099_ghostocmaker.ui.CategoryActivity
import com.example.st099_ghostocmaker.ui.SettingActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initWindow()
        setUpListener()
    }

    private fun setUpListener() {
        binding.imgSetting.setOnClickListener {
            val intent = Intent(this, SettingActivity::class.java)
            startActivity(intent)
        }
        binding.createGhost.onSingleClickOut {
            val intent = Intent(this, CategoryActivity::class.java)
            startActivity(intent)
        }
    }


    fun initWindow() {
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or
                View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR or
                View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
    }
}