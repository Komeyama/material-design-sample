package com.komeyama.sample.design.material.ui.bottomnavigation.type02

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.AppBarLayout
import com.komeyama.sample.design.material.ui.bottomnavigation.R
import com.komeyama.sample.design.material.ui.bottomnavigation.databinding.BottomNavType02ItemBinding
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.databinding.BindableItem
import com.xwray.groupie.databinding.ViewHolder
import kotlinx.android.synthetic.main.fragment_bottom_navigation_type02_item01.*
import timber.log.Timber

class BottomNavigationType02Item01: Fragment(R.layout.fragment_bottom_navigation_type02_item01) {

    /**
     * Because the initial movement of the animation is slowed down by the slight change in the scrolling
     */
    var scrolledUp = true
    var scrolledDown = true

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val groupAdapter = GroupAdapter<ViewHolder<*>>()
        bottom_nav_type02_item01_recycler_view.adapter = groupAdapter
        val items: MutableList<BottomNavType02Item> = mutableListOf()
        for(index in 0 until 20) {
            Timber.d("bottom type02: %s", index)
            items.add(BottomNavType02Item("Bottom Navigation Type02 Item $index"))
        }
        groupAdapter.update(items)

        bottom_nav_type02_item01_recycler_view.addOnScrollListener(onScrollListener)
    }

    private val onScrollListener = object: RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)

            if (dy < 0) {
                if (scrolledUp) {
                    scrolledUp = false
                    scrolledDown = true
                    showAppBarLayoutAndMoveRecyclerView(bottom_navigation_type01_item01_appbar,bottom_nav_type02_item01_recycler_view)
                }

            } else if (dy > 0) {
                if (scrolledDown) {
                    scrolledUp = true
                    scrolledDown = false
                    hideAppBarLayoutAndMoveRecyclerView(bottom_navigation_type01_item01_appbar,bottom_nav_type02_item01_recycler_view)
                }
            }
        }
    }

    private fun hideAppBarLayoutAndMoveRecyclerView(view: AppBarLayout, recyclerView: RecyclerView) {
        view.animate().translationY(-1f * view.height.toFloat())
        recyclerView.animate().translationY(-1f * view.height.toFloat())
    }

    private fun showAppBarLayoutAndMoveRecyclerView(view: AppBarLayout, recyclerView: RecyclerView) {
        view.animate().translationY(0f)
        recyclerView.animate().translationY(0f)
    }
}

class BottomNavType02Item(private val titleText: String) : BindableItem<BottomNavType02ItemBinding>() {
    override fun getLayout() = R.layout.bottom_nav_type02_item

    override fun bind(viewBinding: BottomNavType02ItemBinding, position: Int) {
        viewBinding.bottomNavType02Item01TitleText.text = titleText
        Timber.d("on click button1(type02) position:%s, title:%s ",position, viewBinding.bottomNavType02Item01TitleText.text)
    }
}