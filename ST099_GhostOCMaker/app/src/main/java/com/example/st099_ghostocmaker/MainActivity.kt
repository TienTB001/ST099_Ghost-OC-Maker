package com.example.st099_ghostocmaker

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.example.st099_ghostocmaker.Ultils.DataLocal.dataGhost
import com.example.st099_ghostocmaker.databinding.ActivityMainBinding
import com.example.st099_ghostocmaker.tien.ChibiModel
import com.example.st099_ghostocmaker.tien.DataLocal.dataChibi
import com.example.st099_ghostocmaker.ui.SettingActivity
import com.google.gson.Gson
import java.io.File

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initWindow()

        binding.imgSetting.setOnClickListener {
            val intent = Intent(this, SettingActivity::class.java)
            startActivity(intent)
        }
        Log.d("hung", "onCreate: dataGhost" + dataGhost(this))

        val ghostData = dataGhost(this)
        val gson = Gson()
        val jsonString1 = gson.toJson(ghostData)
        Log.d("tien1", "data: $jsonString1")

        val sharedPref = getSharedPreferences("jsondata", Context.MODE_PRIVATE)

// Bắt đầu edit, lưu chuỗi JSON
        sharedPref.edit().apply {
            putString("ghost_data", jsonString1)
            apply() // hoặc commit()
        }


        val chibiData = dataChibi(this)
        val jsonString2 = gson.toJson(chibiData)
        Log.d("tien2", "data: $jsonString2")


// Bắt đầu edit, lưu chuỗi JSON
        sharedPref.edit().apply {
            putString("chibi_data", jsonString2)
            apply() // hoặc commit()
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