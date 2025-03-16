package com.example.st099_ghostocmaker.ui.Splash

import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.os.Looper
import android.view.animation.LinearInterpolator
import androidx.appcompat.app.AppCompatActivity
import com.example.st099_ghostocmaker.Language.LanguageActivity
import com.example.st099_ghostocmaker.MainActivity
import com.example.st099_ghostocmaker.Ultils.StoreSharedPreferences
import com.example.st099_ghostocmaker.databinding.ActivitySplashBinding
import com.example.st099_ghostocmaker.intro.IntroActivity

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // Tạo hiệu ứng quay 360 độ
        val rotationAnimator = ObjectAnimator.ofFloat(binding.imgLoad, "rotation", 0f, 360f).apply {
            duration = 2000 // Quay trong 2 giây
            interpolator = LinearInterpolator() // Quay đều
            repeatCount = ObjectAnimator.INFINITE // Quay liên tục
        }
        rotationAnimator.start()

        // Dừng quay và chuyển màn hình sau 2 giây
        android.os.Handler(Looper.getMainLooper()).postDelayed({
            rotationAnimator.cancel() // Dừng quay
            binding.imgLoad.rotation = 0f // Đặt lại góc về 0

            // Chuyển sang màn hình LanguageActivity

            if (StoreSharedPreferences.readFromPreferences(this, "hasLanguage").equals("true")) {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                val intent = Intent(this, LanguageActivity::class.java)
                startActivity(intent)
                finish() // Đóng SplashActivity
            }

        }, 0) // Trì hoãn 2 giây
    }
}