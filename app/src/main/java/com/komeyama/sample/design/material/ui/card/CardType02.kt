package com.komeyama.sample.design.material.ui.card

import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import android.widget.ImageView
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import com.komeyama.sample.design.material.R
import kotlinx.android.synthetic.main.fragment_card_type02.*
import kotlinx.coroutines.*

class CardType02: Fragment(R.layout.fragment_card_type02){

    private val shrinkTime = 100L

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        card_favorite.setOnClickListener {
            card_favorite.startFavoriteAnimation()
        }
    }

    private fun ImageView.startFavoriteAnimation() {
        val view = this
        GlobalScope.launch {
            val shrink = GlobalScope.async {
                view.startAnimation(
                    scaleAnimationAsCenter(
                    1.0f, 0.2f, 1.0f,0.2f, shrinkTime
                    )
                )
                delay(shrinkTime)
            }
            shrink.await()
            view.changeColor()
            view.startAnimation(
                scaleAnimationAsCenter(
                0.2f, 1.0f, 0.2f,1.0f,250)
            )
        }
    }

    private fun scaleAnimationAsCenter(fromX: Float,toX: Float,fromY: Float,toY: Float, duration: Long): Animation {
        val scaleAnimation = ScaleAnimation(
            fromX, toX, fromY,toY, Animation.RELATIVE_TO_SELF,
            0.5f, Animation.RELATIVE_TO_SELF,0.5f)
        scaleAnimation.duration = duration
        scaleAnimation.repeatCount = 0
        scaleAnimation.fillAfter = true
        return scaleAnimation
    }

    private fun ImageView.changeColor() {
        if (this.drawable.constantState == ResourcesCompat.getDrawable(resources, R.drawable.ic_favorite_pink_24dp, null)?.constantState) {
            this.setImageResource(R.drawable.ic_favorite_24dp)
        } else {
            this.setImageResource(R.drawable.ic_favorite_pink_24dp)
        }
    }
}