package com.komeyama.sample.design.material.ui.bottomnavigation.type02.item02

import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.komeyama.sample.design.material.ui.bottomnavigation.R
import kotlinx.android.synthetic.main.fragment_bottom_navigation_type02_item02_album_detail.*

class BottomNavigationType02Item02AlbumDetail :
    Fragment(R.layout.fragment_bottom_navigation_type02_item02_album_detail) {

    private val args: BottomNavigationType02Item02AlbumDetailArgs by navArgs()
    private val marqueeStartTime: Long = 2000L

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // set title text
        val title = args.albumName + " - " + args.artistName
        album_detail_toolbar_title.text = title
        val handler = Handler()
        handler.postDelayed({ album_detail_toolbar_title.isSelected = true }, marqueeStartTime)
        album_detail_toolbar.setNavigationOnClickListener {
            it.findNavController().navigateUp()
        }

    }
}