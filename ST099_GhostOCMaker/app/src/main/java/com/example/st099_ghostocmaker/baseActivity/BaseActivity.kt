package com.example.st099_ghostocmaker.baseActivity

import android.Manifest
import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Paint
import android.graphics.Shader
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.TextView
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.viewbinding.ViewBinding
import com.chibimaker.create.avatar.cutechibi.custom.text.StrokeTextView
import com.chibimaker.create.avatar.cutechibi.extension.select
import com.chibimaker.create.avatar.cutechibi.utils.SystemUtils

abstract class BaseActivity<T : ViewBinding> : AppCompatActivity() {
    companion object {
        const val PERMISSION_REQUEST_CODE = 112
    }

    lateinit var binding: T

    protected abstract fun setViewBinding(): T

    protected abstract fun initView()

    protected abstract fun viewListener()

    protected abstract fun dataObservable()

    protected abstract fun initText()

    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initWindow()
        SystemUtils.setLocale(this)
        binding = setViewBinding()
        setContentView(binding.root)
        initView()
        viewListener()
        dataObservable()
        initText()
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

    override fun onResume() {
        super.onResume()
        initWindow()
    }

    fun getNotificationPermission() {
        try {
            if (Build.VERSION.SDK_INT > 32) {
                Log.d("TAG", "getNotificationPermission: request")
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                    PERMISSION_REQUEST_CODE
                )
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
//        if (hasFocus) {
//            fullScreenImmersive(window)
//        }
    }

    fun fullScreenImmersive(window: Window?) {
        window?.let {
            fullScreenImmersive(it.decorView)
        }
    }

    fun fullScreenImmersive(view: View) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            val uiOptions =
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
            view.systemUiVisibility = uiOptions
        }
    }

    fun setGradientHeightTextColor(textView: TextView, startColor: Int, endColor: Int) {
        val paint = textView.paint
        val height = textView.textSize
        val textShader = LinearGradient(
            0f, 0f, 0f, height,
            intArrayOf(
                startColor,
                endColor
            ),
            null, Shader.TileMode.CLAMP
        )
        textView.paint.shader = textShader
    }

    fun setGradientWidthTextColor(textView: TextView, startColor: Int, endColor: Int) {
        val paint = textView.paint
        val width = paint.measureText(textView.text.toString())
        val textShader = LinearGradient(
            0f, 0f, width, textView.textSize,
            intArrayOf(
                startColor,
                endColor
            ),
            null, Shader.TileMode.CLAMP
        )

        textView.paint.shader = textShader
    }
    fun setGradientStrokeText(textGradient: TextView, textStroke: StrokeTextView){
        setGradientHeightTextColor(textGradient, Color.parseColor("#045F62"), Color.parseColor("#8BBA1A"))
        textStroke.setStroke(2.5f, Color.WHITE, Paint.Join.ROUND, 5f)
        textStroke.select()
        textGradient.select()
    }
    fun dpToPx(dp: Int): Int {
        return (dp * this.resources.displayMetrics.density).toInt()
    }
}