package com.komeyama.sample.design.material.ui.dialog

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.komeyama.sample.design.material.R
import kotlinx.android.synthetic.main.fragment_dialog_select.*

class DialogSelectFragment: Fragment(R.layout.fragment_dialog_select) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dialog_type01.setOnClickListener {

        }

        dialog_type02.setOnClickListener {

        }

        dialog_type03.setOnClickListener {

        }

    }

}