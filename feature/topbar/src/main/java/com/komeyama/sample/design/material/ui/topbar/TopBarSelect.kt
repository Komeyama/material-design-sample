package com.komeyama.sample.design.material.ui.topbar

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_top_bar_select.*

class TopBarSelect: Fragment(R.layout.fragment_top_bar_select) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        top_bar_type01.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_topBarType01)
        }
    }
}