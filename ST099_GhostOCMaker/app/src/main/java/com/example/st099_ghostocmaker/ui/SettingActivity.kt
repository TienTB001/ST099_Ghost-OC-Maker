package com.example.st099_ghostocmaker.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.st099_ghostocmaker.Language.Language
import com.example.st099_ghostocmaker.MainActivity
import com.example.st099_ghostocmaker.databinding.ActivitySettingBinding
import com.example.st099_ghostocmaker.rate.Rate

class SettingActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySettingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imgBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }


        binding.LNLanguage.setOnClickListener {
            val intent = Intent(this, Language::class.java)
            intent.putExtra("isSetting", true)
            startActivity(intent)
        }

        binding.LNRateUS.setOnClickListener {
            binding.cardView.visibility = android.view.View.VISIBLE
//            val rateDialog = Rate(){
//                binding.cardView.visibility = android.view.View.INVISIBLE
//            }

            val rateDialog = Rate(inVisibleCardView = {binding.cardView.visibility = android.view.View.INVISIBLE})
            rateDialog.show(supportFragmentManager, "RateDialog")

        }

        binding.LnShare.setOnClickListener {
            val intent = Intent(Intent.ACTION_SEND)
            intent.setType("text/plain")


            val packageName = packageName
            val shareMessage = "Try this app! Download now at https://play.google.com/store/apps/details?id=$packageName"

            intent.putExtra(Intent.EXTRA_TEXT, shareMessage)

            startActivity(Intent.createChooser(intent, "Choose Title"))
        }
    }

}