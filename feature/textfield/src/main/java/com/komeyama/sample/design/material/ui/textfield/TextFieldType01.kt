package com.komeyama.sample.design.material.ui.textfield

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_text_field_type01.*
import timber.log.Timber

class TextFieldType01: Fragment(R.layout.fragment_text_field_type01) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        text_field_type01_top.setOnClickListener {
            Timber.d("tap!!")
        }
    }

}