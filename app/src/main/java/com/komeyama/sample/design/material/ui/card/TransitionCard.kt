package com.komeyama.sample.design.material.ui.card

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.material.transition.MaterialContainerTransform
import com.komeyama.sample.design.material.R

class TransitionCard: Fragment(R.layout.fragment_transition_card) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = MaterialContainerTransform(requireContext()).apply {
            //duration = 3000L
            //isDrawDebugEnabled = true
        }


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.rootView.transitionName = "transition_card_container"
    }

}