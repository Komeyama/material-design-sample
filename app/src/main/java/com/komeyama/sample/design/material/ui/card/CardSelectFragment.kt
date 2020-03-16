package com.komeyama.sample.design.material.ui.card

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.komeyama.sample.design.material.R
import kotlinx.android.synthetic.main.fragment_card_select.*

class CardSelectFragment: Fragment(R.layout.fragment_card_select) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        card_type01.setOnClickListener {
            findNavController().navigate(R.id.action_cardTypeFragment_to_cardType01)
        }
        card_type02.setOnClickListener {
            findNavController().navigate(R.id.action_cardTypeFragment_to_cardType02)
        }
        card_type03.setOnClickListener {
            findNavController().navigate(R.id.action_cardTypeFragment_to_cardType03)
        }
    }
}