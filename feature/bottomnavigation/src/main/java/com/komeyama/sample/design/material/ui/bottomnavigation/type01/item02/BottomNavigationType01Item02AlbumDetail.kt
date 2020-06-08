package com.komeyama.sample.design.material.ui.bottomnavigation.type01.item02

import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.transition.MaterialContainerTransform
import com.komeyama.sample.design.material.ui.bottomnavigation.R
import kotlinx.android.synthetic.main.fragment_bottom_navigation_type01_item02_album_detail.*

class BottomNavigationType01Item02AlbumDetail:
    Fragment(R.layout.fragment_bottom_navigation_type01_item02_album_detail) {

    private val args: BottomNavigationType01Item02AlbumDetailArgs by navArgs()
    private val marqueeStartHandler = Handler()
    private lateinit var marqueeStartRunnable: Runnable
    private val marqueeStartTime: Long = 2000L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = MaterialContainerTransform(requireContext()).apply {
            //            duration = 3000L
//            isDrawDebugEnabled = true
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.rootView.transitionName = "transition_album_container"

        // set title text
        val title = args.albumName + " - " + args.artistName
        album_detail_type01_toolbar_title.text = title
        marqueeStartRunnable = Runnable { album_detail_type01_toolbar_title.isSelected = true }
        marqueeStartHandler.postDelayed(marqueeStartRunnable, marqueeStartTime)
        album_detail_type01_toolbar.setNavigationOnClickListener {
            it.findNavController().navigateUp()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        marqueeStartHandler.removeCallbacks(marqueeStartRunnable)
    }
}