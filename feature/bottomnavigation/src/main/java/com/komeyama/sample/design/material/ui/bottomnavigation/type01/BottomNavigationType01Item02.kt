package com.komeyama.sample.design.material.ui.bottomnavigation.type01

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.komeyama.sample.design.material.ui.bottomnavigation.R
import com.komeyama.sample.design.material.ui.bottomnavigation.type01.item01.BottomNavigationType01Item02Albums
import com.komeyama.sample.design.material.ui.bottomnavigation.type01.item01.BottomNavigationType01Item02Artists
import com.komeyama.sample.design.material.ui.bottomnavigation.type01.item01.BottomNavigationType01Item02Playlists
import kotlinx.android.synthetic.main.fragment_bottom_navigation_type01_item02.*

class BottomNavigationType01Item02: Fragment(R.layout.fragment_bottom_navigation_type01_item02) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // pager
        bottom_navigation_type01_item02_pager.adapter = Type01TabAdapter(childFragmentManager, activity!!)
        bottom_navigation_type01_item02_tabLayout.setupWithViewPager(
            bottom_navigation_type01_item02_pager
        )
    }
}

class Type01TabAdapter(fm: FragmentManager, private val context: Context) :
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                BottomNavigationType01Item02Albums()
            }
            1 -> {
                BottomNavigationType01Item02Artists()
            }
            else -> {
                BottomNavigationType01Item02Playlists()
            }
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> {
                context.getString(R.string.bottom_navigation_albums)
            }
            1 -> {
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