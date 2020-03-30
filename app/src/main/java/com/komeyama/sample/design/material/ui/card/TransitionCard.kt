package com.komeyama.sample.design.material.ui.card

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.google.android.material.transition.MaterialContainerTransform
import com.komeyama.sample.design.material.R
import kotlinx.android.synthetic.main.fragment_transition_card.*

class TransitionCard: Fragment(R.layout.fragment_transition_card) {

    private val args: TransitionCardArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = MaterialContainerTransform(requireContext()).apply {
            //duration = 3000L
            //isDrawDebugEnabled = true
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        card_title_text.text =  args.titleName
        view.rootView.transitionName = "transition_card_container"
    }

}