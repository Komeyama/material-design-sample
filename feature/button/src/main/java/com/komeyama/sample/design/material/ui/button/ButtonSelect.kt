package com.komeyama.sample.design.material.ui.button

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_button_select.*

class ButtonSelect : Fragment(R.layout.fragment_button_select) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        button_select_type01.setOnClickListener {
            findNavController().navigate(R.id.action_buttonSelectFragment_to_buttonType01Fragment)
        }

        button_select_type02.setOnClickListener {
            findNavController().navigate(R.id.action_buttonSelectFragment_to_buttonType02Fragment)
        }
    }
}