package com.komeyama.sample.design.material.ui.bottomnavigation.type02

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.komeyama.sample.design.material.ui.bottomnavigation.R
import com.komeyama.sample.design.material.ui.bottomnavigation.type02.item02.BottomNavigationType02Item02Albums
import com.komeyama.sample.design.material.ui.bottomnavigation.type02.item02.BottomNavigationType02Item02Artists
import com.komeyama.sample.design.material.ui.bottomnavigation.type02.item02.BottomNavigationType02Item02Playlists
import kotlinx.android.synthetic.main.fragment_bottom_navigation_type02_item02.*
import timber.log.Timber

class BottomNavigationType02Item02 : Fragment(R.layout.fragment_bottom_navigation_type02_item02) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // pager
        bottom_navigation_type02_item02_pager.adapter = TabAdapter(childFragmentManager, activity!!)
        bottom_navigation_type02_item02_tabLayout.setupWithViewPager(
            bottom_navigation_type02_item02_pager
        )

        // toolbar & search view
        bottom_navigation_type02_item02_toolbar.inflateMenu(R.menu.bottom_navigation_top_app_menu)
        val searchItem =
            bottom_navigation_type02_item02_toolbar.menu.findItem(R.id.bottom_nav_top_bar_search)
        val searchView = searchItem.actionView as SearchView
        searchView.maxWidth = Integer.MAX_VALUE
        searchView.queryHint = activity!!.getString(R.string.bottom_bar_search)
        val icon: ImageView = searchView.findViewById(androidx.appcompat.R.id.search_button)
        icon.setImageResource(R.drawable.ic_search_black_24dp)

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                Timber.d("query text submit: %s", query)
                searchView.setQuery(null,false)
                searchView.clearFocus()
                searchView.isFocusable = false
                searchView.isIconified = true
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                Timber.d("query text change: %s", newText)
                return false
            }
        })

        searchView.setOnQueryTextFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                bottom_navigation_type02_item02_tabLayout.visibility = View.INVISIBLE
                bottom_navigation_type02_item02_pager.visibility = View.INVISIBLE
            } else {
                bottom_navigation_type02_item02_tabLayout.visibility = View.VISIBLE
                bottom_navigation_type02_item02_pager.visibility = View.VISIBLE
            }
        }

    }
}

class TabAdapter(fm: FragmentManager, private val context: Context) :
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                BottomNavigationType02Item02Albums()
            }
            1 -> {
                BottomNavigationType02Item02Artists()
            }
            else -> {
                BottomNavigationType02Item02Playlists()
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

