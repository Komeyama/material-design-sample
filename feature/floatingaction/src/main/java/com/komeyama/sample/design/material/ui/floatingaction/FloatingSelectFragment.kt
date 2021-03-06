package com.komeyama.sample.design.material.ui.floatingaction

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_floating_select.*

class FloatingSelectFragment: Fragment(R.layout.fragment_floating_select) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        floating_type01.setOnClickListener {
            findNavController().navigate(R.id.action_floatingSelectFragment_to_floatingType01Fragment)
        }
        floating_type02.setOnClickListener {
            findNavController().navigate(R.id.action_floatingSelectFragment_to_floatingType02Fragment)
        }
        floating_type03.setOnClickListener {
            findNavController().navigate(R.id.action_floatingSelectFragment_to_floatingType03Fragment)
        }
    }

}