package com.komeyama.sample.design.material.ui.textfield

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_text_filed_select.*

class TextFieldSelectFragment: Fragment(R.layout.fragment_text_filed_select) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        text_type01_button.setOnClickListener {
            findNavController().navigate(R.id.action_textfieldSelectFragment_to_textfieldType01Fragment)
        }
        text_type02_button.setOnClickListener {
            findNavController().navigate(R.id.action_textfieldSelectFragment_to_textfieldType02Fragment)
        }
        text_type03_button.setOnClickListener {
            findNavController().navigate(R.id.action_textfieldSelectFragment_to_textfieldType03Fragment)
        }
    }
}