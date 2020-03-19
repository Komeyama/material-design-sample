package com.komeyama.sample.design.material.ui.card

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.komeyama.sample.design.material.R
import kotlinx.android.synthetic.main.fragment_card_type04.*

class CardType04: Fragment(R.layout.fragment_card_type04) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        expand_control_button.setOnClickListener {
            if (text_filed_constraint.visibility == View.GONE) {
                card_top.layoutParams.height = activity?.resources?.getDimensionPixelSize(R.dimen.card_top_height_expand)!!.toInt()
                text_filed_constraint.visibility = View.VISIBLE
            } else {
                card_top.layoutParams.height = activity?.resources?.getDimensionPixelSize(R.dimen.card_top_height_reduce)!!.toInt()
                text_filed_constraint.visibility = View.GONE
            }

        }
    }

}