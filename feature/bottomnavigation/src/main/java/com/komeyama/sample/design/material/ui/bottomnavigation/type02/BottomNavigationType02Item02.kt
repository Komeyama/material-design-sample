package com.komeyama.sample.design.material.ui.bottomnavigation.type02

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.komeyama.sample.design.material.ui.bottomnavigation.R
import kotlinx.android.synthetic.main.fragment_bottom_navigation_type02_item02.*

class BottomNavigationType02Item02: Fragment(R.layout.fragment_bottom_navigation_type02_item02) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bottom_navigation_type02_item02_pager.adapter = TabAdapter(activity!!.supportFragmentManager)
        bottom_navigation_type02_item02_tabLayout.setupWithViewPager(bottom_navigation_type02_item02_pager)
    }
}

class TabAdapter(fm: FragmentManager): FragmentPagerAdapter(fm){

    override fun getItem(position: Int): Fragment {
        return when(position){
            0 -> {
                BottomNavigationType02Item02Tab01()
            }
            else ->  {
                BottomNavigationType02Item02Tab02()
            }
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0 -> {
                "tab_01"
            }
            else ->  {
                "tab_02"
            }
        }
    }

    override fun getCount(): Int {
        return 2
    }
}

class BottomNavigationType02Item02Tab01: Fragment(R.layout.fragment_bottom_navigation_type02_item02_tab01){}

class BottomNavigationType02Item02Tab02: Fragment(R.layout.fragment_bottom_navigation_type02_item02_tab02){}