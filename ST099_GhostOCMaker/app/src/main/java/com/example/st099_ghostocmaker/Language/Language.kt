package com.example.st099_ghostocmaker.Language

import android.content.Intent
import android.graphics.LinearGradient
import android.graphics.Shader
import android.os.Bundle
import android.util.Log
import android.view.ViewTreeObserver
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.st099_ghostocmaker.R
import com.example.st099_ghostocmaker.Ultils.StoreSharedPreferences
import com.example.st099_ghostocmaker.databinding.ActivityLanguageBinding
import com.example.st099_ghostocmaker.intro.Intro

class Language : AppCompatActivity() {

    private lateinit var binding: ActivityLanguageBinding
    private lateinit var languageAdapter: LanguageAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLanguageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val isSetting = intent.getBooleanExtra("isSetting", false)

        binding.imgCheck.viewTreeObserver.addOnPreDrawListener(object :
            ViewTreeObserver.OnPreDrawListener {
            override fun onPreDraw(): Boolean {
                binding.imgCheck.viewTreeObserver.removeOnPreDrawListener(this)

                binding.imgCheck.setColorFilter(
                    ContextCompat.getColor(
                        this@Language,
                        R.color.gradient_start
                    )
                )
                binding.imgCheck.invalidate()
                return true
            }
        })

        val languageNames = resources.getStringArray(R.array.language_names)
        val languageCodes = resources.getStringArray(R.array.language_codes)
        val languageFlags = resources.obtainTypedArray(R.array.language_flags)

        val languageList = mutableListOf<LanguageModel>()
        for (i in languageNames.indices) {
            languageList.add(
                LanguageModel(
                    languageNames[i],
                    languageCodes[i],
                    languageFlags.getResourceId(i, 0)
                )
            )
        }
        languageFlags.recycle() // Giải phóng bộ nhớ

        languageAdapter = LanguageAdapter(languageList) { selectedLanguage ->
            saveLanguageSelection(selectedLanguage)
        }

        binding.rcvLanguage.apply {
            layoutManager = LinearLayoutManager(this@Language)
            adapter = languageAdapter
        }

        binding.imgCheck.setOnClickListener {
            if (languageList.any { it.isSelected }) {
                val selectedLanguage = languageList.first { it.isSelected }
                saveLanguageSelection(selectedLanguage)
                StoreSharedPreferences.saveToPreferences(this, "hasLanguage", "true")
                if (isSetting) {
                    finish()
                } else {
                    val intent = Intent(this, Intro::class.java)
                    startActivity(intent)
                    finish()
                }
            }
        }
    }

    private fun saveLanguageSelection(language: LanguageModel) {
        val sharedPref = getSharedPreferences("AppPreferences", MODE_PRIVATE)
        sharedPref.edit().putString("SELECTED_LANGUAGE", language.code).apply()


    }
}



