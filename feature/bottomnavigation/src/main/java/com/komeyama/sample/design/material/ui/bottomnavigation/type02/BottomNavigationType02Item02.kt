package com.komeyama.sample.design.material.ui.bottomnavigation.type02

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.recyclerview.widget.RecyclerView
import com.komeyama.sample.design.material.ui.bottomnavigation.R
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.databinding.ViewHolder
import kotlinx.android.synthetic.main.fragment_bottom_navigation_type02_item02.*
import kotlinx.android.synthetic.main.fragment_bottom_navigation_type02_item02_albums.*
import timber.log.Timber

class BottomNavigationType02Item02: Fragment(R.layout.fragment_bottom_navigation_type02_item02) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bottom_navigation_type02_item02_pager.adapter = TabAdapter(childFragmentManager, activity!!)
        bottom_navigation_type02_item02_tabLayout.setupWithViewPager(bottom_navigation_type02_item02_pager)

        bottom_navigation_type02_item02_toolbar.inflateMenu(R.menu.bottom_navigation_type02_top_app_menu)
        val searchItem = bottom_navigation_type02_item02_toolbar.menu.findItem(R.id.bottom_nav_type02_top_bar_search)
        val searchView = searchItem.actionView as SearchView
        val icon: ImageView = searchView.findViewById(androidx.appcompat.R.id.search_button)
        icon.setImageResource(R.drawable.ic_search_black_24dp)

        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchView.clearFocus()
                Timber.d("query text submit: %s", query)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                Timber.d("query text change: %s", newText)
                return false
            }
        })

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

class BottomNavigationType02Item02Albums: Fragment(R.layout.fragment_bottom_navigation_type02_item02_albums){

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val groupAdapter = GroupAdapter<ViewHolder<*>>()
        bottom_nav_type02_item02_recycler_view.adapter = groupAdapter
        val items: MutableList<BottomNavType02Item> = mutableListOf()
        for(index in 0 until 20) {
            Timber.d("bottom type02: %s", index)
            items.add(BottomNavType02Item("Bottom Navigation Type02 Item $index"))
        }
        groupAdapter.update(items)
        bottom_nav_type02_item02_recycler_view.addOnScrollListener(onScrollListener)
    }

    private val onScrollListener = object: RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
        }
    }
}

class BottomNavigationType02Item02Artists: Fragment(R.layout.fragment_bottom_navigation_type02_item02_artists){}

class BottomNavigationType02Item02Playlists: Fragment(R.layout.fragment_bottom_navigation_type02_item02_playlists){}