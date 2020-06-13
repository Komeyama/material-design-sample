package com.komeyama.sample.design.material.ui.button

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_button_type02.*
import timber.log.Timber

class ButtonType02 : Fragment(R.layout.fragment_button_type02) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        button_type02_toggle.addOnButtonCheckedListener { toggleButton, checkedId, isChecked ->
            Timber.d("toggle !: %s %s %s", toggleButton.checkedButtonIds, checkedId, isChecked)
            Snackbar.make(view, "button$checkedId : $isChecked", Snackbar.LENGTH_LONG).show()
        }

    }
}