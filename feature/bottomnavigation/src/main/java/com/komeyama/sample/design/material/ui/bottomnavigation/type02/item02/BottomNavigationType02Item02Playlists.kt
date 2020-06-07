package com.komeyama.sample.design.material.ui.bottomnavigation.type02.item02

import androidx.fragment.app.Fragment
import com.komeyama.sample.design.material.ui.bottomnavigation.BottomNavigationType02
import com.komeyama.sample.design.material.ui.bottomnavigation.R

class BottomNavigationType02Item02Playlists :
    Fragment(R.layout.fragment_bottom_navigation_type02_item02_playlists) {

    /**
     * Todo: refactor
     */
    override fun onResume() {
        super.onResume()
        (parentFragment?.parentFragment?.parentFragment as BottomNavigationType02).initBottomNavigation()
    }
}