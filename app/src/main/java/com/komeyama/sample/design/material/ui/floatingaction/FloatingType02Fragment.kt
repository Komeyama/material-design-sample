package com.komeyama.sample.design.material.ui.floatingaction

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.komeyama.sample.design.material.R
import kotlinx.android.synthetic.main.fragment_floating_type02.*
import timber.log.Timber

class FloatingType02Fragment: Fragment(R.layout.fragment_floating_type02) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        floating_type02_00.setOnClickListener {
            Timber.d("tap floating type02")
        }
    }

}