package com.komeyama.sample.design.material.ui.card

import android.animation.LayoutTransition
import android.os.Bundle
import android.view.View
import androidx.constraintlayout.widget.ConstraintSet
import androidx.fragment.app.Fragment
import com.komeyama.sample.design.material.R
import kotlinx.android.synthetic.main.fragment_card_type04.*

class CardType04: Fragment(R.layout.fragment_card_type04) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setCardTopHeight(R.dimen.card_top_height_reduce,0)
        setTextFiledConstraintHeight(R.dimen.card_text_field_height_reduce,0)

        expand_control_button.setOnClickListener {
            if (text_filed_constraint.layoutParams.height == activity?.resources?.getDimensionPixelSize(R.dimen.card_text_field_height_reduce)!!.toInt()) {
                setCardTopHeight(R.dimen.card_top_height_expand,250)
                setTextFiledConstraintHeight(R.dimen.card_text_field_height_expand,250)
            } else {
                setCardTopHeight(R.dimen.card_top_height_reduce,250)
                setTextFiledConstraintHeight(R.dimen.card_text_field_height_reduce,250)
            }
        }
    }

    private fun setCardTopHeight(heightId:Int, duration: Long) {
        val transition = card_top.layoutTransition
        card_top.layoutTransition.enableTransitionType(LayoutTransition.CHANGING)
        transition.setDuration(duration)

        card_top.layoutParams.height = activity?.resources?.getDimensionPixelSize(heightId)!!.toInt()
    }

    private fun setTextFiledConstraintHeight(heightId: Int, duration: Long) {
        val transition = text_filed_constraint.layoutTransition
        text_filed_constraint.layoutTransition.enableTransitionType(LayoutTransition.CHANGING)
        transition.setDuration(duration)

        text_filed_constraint.layoutParams.height = activity?.resources?.getDimensionPixelSize(heightId)!!.toInt()
        val constraintSet = ConstraintSet()
        constraintSet.clone(text_filed_constraint)
        constraintSet.applyTo(text_filed_constraint)
    }
}