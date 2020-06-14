package com.komeyama.sample.design.material.ui.button

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_button_type01.*

class ButtonType01 : Fragment(R.layout.fragment_button_type01) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        textButton01.setOnClickListener {
            Snackbar.make(view, "text button 01", Snackbar.LENGTH_LONG).show()
        }
        textButton02.setOnClickListener {
            Snackbar.make(view, "text button 02", Snackbar.LENGTH_LONG).show()
        }
        textButton03.setOnClickListener {
            Snackbar.make(view, "text button 03", Snackbar.LENGTH_LONG).show()
        }
    }
}