package com.komeyama.sample.design.material.ui.textfield

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

enum class ValidateType{
    MORE_THAN,
    LESS_THAN
}

fun View.hideKeyboard(view: View) {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(view.windowToken, 0)
}

fun TextInputEditText.validateOfEditTextField(
    textInputLayout: TextInputLayout,
    errorMessage: String,
    validateLength: Int,
    validateType: ValidateType
) {
    val inputEditText = this

    inputEditText.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {}

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            when(validateType) {
                ValidateType.LESS_THAN -> {
                    if (inputEditText.text!!.length < validateLength) {
                        textInputLayout.error = String.format(errorMessage, validateLength)
                    } else {
                        textInputLayout.error = null
                    }
                }
                ValidateType.MORE_THAN -> {
                    if (inputEditText.text!!.length > validateLength) {
                        textInputLayout.error = String.format(errorMessage, validateLength)
                    } else {
                        textInputLayout.error = null
                    }
                }
            }
        }
    })
}