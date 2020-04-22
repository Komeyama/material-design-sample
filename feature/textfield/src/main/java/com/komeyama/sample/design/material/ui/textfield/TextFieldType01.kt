package com.komeyama.sample.design.material.ui.textfield

import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_text_field_type01.*

class TextFieldType01 : Fragment(R.layout.fragment_text_field_type01) {

    companion object {
        private const val EDIT_TEXT_PASSWORD_OUTLINE = 8
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.setOnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                v.hideKeyboard(v)
                v.requestFocus()
            }
            true
        }

        edit_text_password_outline.validateOfEditTextField(
            text_input_password_outline,
            "Password must contain at least %s characters.",
            EDIT_TEXT_PASSWORD_OUTLINE,
            ValidateType.LESS_THAN
        )
        edit_text_password_filled.validateOfEditTextField(
            text_input_password_filled,
            "Password must contain at least %s characters.",
            EDIT_TEXT_PASSWORD_OUTLINE,
            ValidateType.LESS_THAN
        )
        edit_text_account_name_filled.validateOfEditTextField(
            text_input_account_name_filled,
            "Account name must be less than %s characters.",
            text_input_account_name_filled.counterMaxLength,
            ValidateType.MORE_THAN
        )
    }
}