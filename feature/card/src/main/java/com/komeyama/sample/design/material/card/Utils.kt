package com.komeyama.sample.design.material.card

import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import android.widget.ImageView
import kotlinx.coroutines.*

private const val initialAnimationTime = 100L

fun ImageView.startFavoriteAnimation(scaleValue: Float, color: Int) {
    val view = this
    GlobalScope.launch(Dispatchers.Main) {
        val shrink = GlobalScope.async(Dispatchers.Main) {
            view.startAnimation(
                scaleAnimationAsCenter(
                    1.0f, scaleValue, 1.0f,scaleValue, initialAnimationTime
                )
            )
            delay(initialAnimationTime)
        }
        shrink.await()

        view.setImageResource(color)
        view.startAnimation(
            scaleAnimationAsCenter(
                scaleValue, 1.0f, scaleValue,1.0f,250)
        )
    }
}

fun scaleAnimationAsCenter(fromX: Float,toX: Float,fromY: Float,toY: Float, duration: Long): Animation {
    val scaleAnimation = ScaleAnimation(
        fromX, toX, fromY,toY, Animation.RELATIVE_TO_SELF,
        0.5f, Animation.RELATIVE_TO_SELF,0.5f)
    scaleAnimation.duration = duration
    scaleAnimation.repeatCount = 0
    scaleAnimation.fillAfter = true
    return scaleAnimation
}