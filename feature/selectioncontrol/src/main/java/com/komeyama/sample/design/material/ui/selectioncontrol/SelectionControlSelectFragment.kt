package com.komeyama.sample.design.material.ui.selectioncontrol

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_selection_control_select.*

class SelectionControlSelectFragment: Fragment(R.layout.fragment_selection_control_select) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        selection_control_type01.setOnClickListener {
            findNavController().navigate(R.id.action_selectionControlSelectionFragment_to_selectionControlType01Fragment)
        }
        selection_control_type02.setOnClickListener {
            findNavController().navigate(R.id.action_selectionControlSelectionFragment_to_selectionControlType02Fragment)
        }
    }
}