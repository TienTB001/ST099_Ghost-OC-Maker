package com.chibimaker.create.avatar.cutechibi.extension

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.Context
import android.view.View
import android.view.ViewGroup

// click
internal fun View.animateScaleEffect(
    scaleDownFactor: Float = 0.9f,
    duration: Long = 100L) {
    val scaleDownX = ObjectAnimator.ofFloat(this, "scaleX", 1f, scaleDownFactor)
    val scaleDownY = ObjectAnimator.ofFloat(this, "scaleY", 1f, scaleDownFactor)
    scaleDownX.duration = duration
    scaleDownY.duration = duration

    val scaleUpX = ObjectAnimator.ofFloat(this, "scaleX", scaleDownFactor, 1f)
    val scaleUpY = ObjectAnimator.ofFloat(this, "scaleY", scaleDownFactor, 1f)
    scaleUpX.duration = duration
    scaleUpY.duration = duration

    val animatorSet = AnimatorSet()
    animatorSet.play(scaleDownX).with(scaleDownY)
    animatorSet.play(scaleUpX).with(scaleUpY).after(scaleDownX)


    animatorSet.start()
}

internal fun View.animateScaleOutEffect(
    scaleUpFactor: Float = 1.1f,
    duration: Long = 100L) {
    val scaleUpX = ObjectAnimator.ofFloat(this, "scaleX", 1f, scaleUpFactor)
    val scaleUpY = ObjectAnimator.ofFloat(this, "scaleY", 1f, scaleUpFactor)
    scaleUpX.duration = duration
    scaleUpY.duration = duration

    val scaleDownX = ObjectAnimator.ofFloat(this, "scaleX", scaleUpFactor, 1f)
    val scaleDownY = ObjectAnimator.ofFloat(this, "scaleY", scaleUpFactor, 1f)
    scaleDownX.duration = duration
    scaleDownY.duration = duration

    val animatorSet = AnimatorSet()
    animatorSet.play(scaleUpX).with(scaleUpY)
    animatorSet.play(scaleDownX).with(scaleDownY).after(scaleUpX)

    animatorSet.start()
}


// Rung ngang
internal fun View.shakeView(duration: Long = 100, repeatCount: Int = 3, shakeDistance: Float = 10f) {
    val animator = ObjectAnimator.ofFloat(this, "translationX", -shakeDistance, shakeDistance)
    animator.duration = duration
    animator.repeatCount = repeatCount
    animator.repeatMode = ObjectAnimator.REVERSE

    // Thêm listener để đặt lại vị trí ban đầu sau khi animation kết thúc
    animator.addListener(object : AnimatorListenerAdapter() {
        override fun onAnimationEnd(animation: Animator) {
            // Đặt lại thuộc tính translationX về 0 để trở lại vị trí ban đầu
            this@shakeView.translationX = 0f
        }
    })

    animator.start()
}

// Phương thức để animate margin top cho một layout
fun animateLift(layout: View, liftBy: Int, duration: Long = 100) {
    val layoutParams = layout.layoutParams as ViewGroup.MarginLayoutParams
    val originalMarginBottom = layoutParams.bottomMargin

    // Animator thay đổi margin bottom
    val animator = ValueAnimator.ofInt(originalMarginBottom, originalMarginBottom + liftBy)
    animator.addUpdateListener { valueAnimator ->
        val margin = valueAnimator.animatedValue as Int
        layoutParams.bottomMargin = margin
        layout.layoutParams = layoutParams
    }
    animator.duration = duration
    animator.start()
}

// Phương thức để reset margin về ban đầu
fun resetPosition(layout: View, context: Context) {
    val layoutParams = layout.layoutParams as ViewGroup.MarginLayoutParams
    layoutParams.bottomMargin = pxToDp(context, 0)
    layout.layoutParams = layoutParams
}
