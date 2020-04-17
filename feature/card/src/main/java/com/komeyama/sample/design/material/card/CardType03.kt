package com.komeyama.sample.design.material.card

import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.Transformation
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_card_type03.*

class CardType03: Fragment(R.layout.fragment_card_type03) {

    private var originalHeight:Int = 0
    private val customDuration = 500L

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setInitializeOfCard()

        expand_control_button.setOnClickListener {
            expandable_text.clearAnimation()
            if(expandable_text.height > 0) {
                expandable_text.startAnimation(CustomAnimation(expandable_text, 0, originalHeight, customDuration))
            } else{
                expandable_text.startAnimation(CustomAnimation(expandable_text, originalHeight, 0,customDuration))
            }
        }
    }

    private fun setInitializeOfCard() {
        expandable_text.measure(0,0)
        originalHeight = expandable_text.measuredHeight
        expandable_text.layoutParams.height = 0
    }

    private class CustomAnimation(var view: View, private val endHeight: Int, var startHeight: Int, customDuration: Long): Animation() {

        init {
            duration = customDuration
        }

        override fun applyTransformation(interpolatedTime: Float, t: Transformation?) {
            val newHeight = (startHeight + (endHeight - startHeight) * interpolatedTime).toInt()
            view.layoutParams.height = newHeight
            view.requestLayout()
        }
    }
}