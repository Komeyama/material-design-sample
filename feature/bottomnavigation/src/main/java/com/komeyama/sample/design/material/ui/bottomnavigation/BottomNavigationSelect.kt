package com.komeyama.sample.design.material.ui.bottomnavigation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_bottom_navigation_select.*

class BottomNavigationSelect: Fragment(R.layout.fragment_bottom_navigation_select) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bottom_navigation_type01_button.setOnClickListener {
            findNavController().navigate(R.id.action_bottomNavigationSelectFragment_to_bottomNavigationType01Fragment)
        }
        bottom_navigation_type02_button.setOnClickListener {
            findNavController().navigate(R.id.action_bottomNavigationSelectFragment_to_bottomNavigationType02Fragment)
        }
    }
}