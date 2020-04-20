package com.komeyama.sample.design.material.ui.textfield

import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import androidx.fragment.app.Fragment

class TextFieldType01: Fragment(R.layout.fragment_text_field_type01) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.setOnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                v.hideKeyboard(v)
                v.requestFocus()
            }
            true
        }
    }
}