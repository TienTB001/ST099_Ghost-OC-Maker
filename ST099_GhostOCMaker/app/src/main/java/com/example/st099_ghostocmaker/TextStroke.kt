package com.example.st099_ghostocmaker

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Paint
import android.graphics.Shader
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView


class TextStroke @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppCompatTextView(context, attrs, defStyleAttr) {

    var isStrokeEnabled: Boolean = true
    init {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.TextStroke)
        isStrokeEnabled = typedArray.getBoolean(R.styleable.TextStroke_enableStroke, true)
        typedArray.recycle()
    }

    // Màu viền và độ dày viền
    private val strokeColor = Color.WHITE
    private val strokeWidth = 8f

    // Màu gradient (từ trên xuống dưới)
    private val startColor = Color.parseColor("#FA85AF")
    private val endColor = Color.parseColor("#F55B96")

    // Shader để vẽ gradient.
    // Ta sẽ khởi tạo trong onSizeChanged để phù hợp với kích thước TextView thực tế.
    private var gradientShader: LinearGradient? = null

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        // Khi TextView thay đổi kích thước (lần đầu + khi xoay màn hình...), ta tạo shader mới
        if (h > 0) {
            gradientShader = LinearGradient(
                0f, 0f,            // toạ độ bắt đầu (top)
                0f, h.toFloat(),   // toạ độ kết thúc (bottom)
                startColor,
                endColor,
                Shader.TileMode.CLAMP
            )
        }
    }

    override fun onDraw(canvas: Canvas) {
        // Lưu lại shader, style, màu text cũ
        val originalShader = paint.shader
        val originalStyle = paint.style
        val originalColor = currentTextColor

        if (isStrokeEnabled) {
            // 1) Vẽ stroke
            paint.shader = null
            paint.style = Paint.Style.STROKE
            setTextColor(strokeColor)
            paint.strokeWidth = strokeWidth
            super.onDraw(canvas)
        }


        // 2) Vẽ fill (bên trong) với gradient
        paint.style = Paint.Style.FILL
        // Gán shader cho paint
        paint.shader = gradientShader
        super.onDraw(canvas)

        // Phục hồi lại shader, style, màu text cũ
        paint.shader = originalShader
        paint.style = originalStyle
        setTextColor(originalColor)
    }
}
