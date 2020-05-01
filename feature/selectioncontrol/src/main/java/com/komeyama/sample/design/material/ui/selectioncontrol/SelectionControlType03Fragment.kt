package com.komeyama.sample.design.material.ui.selectioncontrol

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_selection_control_type03.*

class SelectionControlType03Fragment: Fragment(R.layout.fragment_selection_control_type03) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        selection_action_checkbox_top.setOnCheckedChangeListener { _, isChecked ->
            setDetailCheckBoxState(isChecked)
        }
    }

    private fun setDetailCheckBoxState(isChecked: Boolean) {
        selection_action_checkbox_detail_01.isChecked = isChecked
        selection_action_checkbox_detail_02.isChecked = isChecked
        selection_action_checkbox_detail_03.isChecked = isChecked
        selection_action_checkbox_detail_04.isChecked = isChecked
    }
}