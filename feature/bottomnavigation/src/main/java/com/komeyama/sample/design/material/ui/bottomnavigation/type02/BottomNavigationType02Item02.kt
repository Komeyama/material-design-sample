package com.komeyama.sample.design.material.ui.bottomnavigation.type02

import android.content.Context
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
        bottom_navigation_type02_item02_pager.adapter = TabAdapter(activity!!.supportFragmentManager, activity!!)
        bottom_navigation_type02_item02_tabLayout.setupWithViewPager(bottom_navigation_type02_item02_pager)
    }
}

class TabAdapter(fm: FragmentManager, private val context: Context): FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT){

    override fun getItem(position: Int): Fragment {
        return when(position){
            0 -> {
                BottomNavigationType02Item02Albums()
            }
            1 ->  {
                BottomNavigationType02Item02Artists()
            }
            else -> {
                BottomNavigationType02Item02Playlists()
            }
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0 -> {
                context.getString(R.string.bottom_navigation_albums)
            }
            1 ->  {
                context.getString(R.string.bottom_navigation_artists)
            }
            else -> {
                context.getString(R.string.bottom_navigation_playlists)
            }
        }
    }

    override fun getCount(): Int {
        return 3
    }
}

class BottomNavigationType02Item02Albums: Fragment(R.layout.fragment_bottom_navigation_type02_item02_albums){}

class BottomNavigationType02Item02Artists: Fragment(R.layout.fragment_bottom_navigation_type02_item02_artists){}

class BottomNavigationType02Item02Playlists: Fragment(R.layout.fragment_bottom_navigation_type02_item02_playlists){}