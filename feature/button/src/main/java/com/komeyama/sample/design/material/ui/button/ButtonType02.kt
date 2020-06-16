package com.komeyama.sample.design.material.ui.button

import android.os.Bundle
import android.view.View
import androidx.core.view.forEach
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_button_type02.*
import timber.log.Timber

class ButtonType02 : Fragment(R.layout.fragment_button_type02) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        button_type02_toggle_01.addOnButtonCheckedListener { toggleButton, checkedId, isChecked ->
            Timber.d("toggle01: %s %s %s", toggleButton.checkedButtonIds, checkedId, isChecked)
            Snackbar.make(view, "button$checkedId : $isChecked", Snackbar.LENGTH_LONG).show()
        }
        button_type02_toggle_02.addOnButtonCheckedListener { toggleButton, checkedId, isChecked ->
            Timber.d("toggle02: %s %s %s", toggleButton.checkedButtonIds, checkedId, isChecked)
            Snackbar.make(view, "button$checkedId : $isChecked", Snackbar.LENGTH_LONG).show()
        }
        button_enabled_toggle_type02.setOnCheckedChangeListener { _, isChecked ->
            setButtonEnabled(isChecked)
        }
    }

    private fun setButtonEnabled(isEnabled: Boolean) {
        button_type02_toggle_01.forEach {
            it.isEnabled = isEnabled
        }
        button_type02_toggle_02.forEach {
            it.isEnabled = isEnabled
        }
    }
}