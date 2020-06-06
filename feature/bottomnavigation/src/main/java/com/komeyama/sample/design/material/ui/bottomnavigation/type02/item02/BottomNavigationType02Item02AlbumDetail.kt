package com.komeyama.sample.design.material.ui.bottomnavigation.type02.item02

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.komeyama.sample.design.material.ui.bottomnavigation.R
import timber.log.Timber

class BottomNavigationType02Item02AlbumDetail: Fragment(R.layout.fragment_bottom_navigation_type02_item02_album_detail) {
    private val args: BottomNavigationType02Item02AlbumDetailArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Timber.d("args!!: %s %s %s", args.albumName, args.artistName, args.albumTime)
    }
}