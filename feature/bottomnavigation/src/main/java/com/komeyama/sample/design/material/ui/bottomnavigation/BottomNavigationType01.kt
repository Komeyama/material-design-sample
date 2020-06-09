package com.komeyama.sample.design.material.ui.bottomnavigation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import kotlinx.android.synthetic.main.fragment_bottom_navigation_type01.*
import kotlin.properties.Delegates

class BottomNavigationType01 : Fragment(R.layout.fragment_bottom_navigation_type01){

    var initNavHeight by Delegates.notNull<Int>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController = activity!!.findNavController(R.id.bottom_nav_view_type01_nav_host_fragment)
        NavigationUI.setupWithNavController(bottom_navigation_view_type01, navController)

        setBadgeNumber(R.id.bottomNavigationType01FragmentItem01,1)
        setBadgeNumber(R.id.bottomNavigationType01FragmentItem02,3)
        setBadgeNumber(R.id.bottomNavigationType01FragmentItem03,10)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            bottom_navigation_view_type01.removeBadge(destination.id)
        }

        initNavHeight = bottom_navigation_view_type01.height
    }

    private fun setBadgeNumber(menuId: Int, setNumber: Int) {
        val badge = bottom_navigation_view_type01.getOrCreateBadge(menuId)
        badge.isVisible = true
        badge.number = setNumber
    }
}