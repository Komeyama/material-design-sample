package com.komeyama.sample.design.material.ui.bottomnavigation

import android.os.Bundle
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.komeyama.sample.design.material.ui.bottomnavigation.type02.BottomNavigationBehavior
import kotlinx.android.synthetic.main.fragment_bottom_navigation_type02.*

class BottomNavigationType02: Fragment(R.layout.fragment_bottom_navigation_type02) {

    lateinit var bottomNavigationBehavior: BottomNavigationBehavior

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController = activity!!.findNavController(R.id.bottom_nav_view_type02_nav_host_fragment)
        NavigationUI.setupWithNavController(bottom_navigation_view_type02, navController)

        val layoutParams: CoordinatorLayout.LayoutParams = bottom_navigation_view_type02.layoutParams as CoordinatorLayout.LayoutParams
        bottomNavigationBehavior = BottomNavigationBehavior(activity!!)
        layoutParams.behavior = bottomNavigationBehavior
    }

    /**
     * Todo: refactor
     */
    fun initBottomNavigation() {
        bottomNavigationBehavior.scrolledDown = true
        bottomNavigationBehavior.scrolledUp = true
        bottom_navigation_view_type02.animate().translationY(0f)
    }

    fun hideBottomNavigation() {
        bottomNavigationBehavior.scrolledDown = true
        bottomNavigationBehavior.scrolledUp = true
        bottom_navigation_view_type02.animate().translationY(bottom_navigation_view_type02.height.toFloat())
    }

}