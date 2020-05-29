package com.komeyama.sample.design.material.ui.bottomnavigation.type01

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.komeyama.sample.design.material.ui.bottomnavigation.R
import com.komeyama.sample.design.material.ui.bottomnavigation.databinding.BottomNavType01ItemBinding
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.databinding.BindableItem
import com.xwray.groupie.databinding.ViewHolder
import kotlinx.android.synthetic.main.fragment_bottom_navigation_type01_item01.*
import timber.log.Timber

class BottomNavigationType01Item01: Fragment(R.layout.fragment_bottom_navigation_type01_item01) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val groupAdapter = GroupAdapter<ViewHolder<*>>()
        bottom_nav_type01_item01_recycler_view.adapter = groupAdapter
        val items: MutableList<BottomNavType01Item> = mutableListOf()
        for(index in 0 until 20) {
            Timber.d("bottom type01: %s", index)
            items.add(BottomNavType01Item("Bottom Navigation Item $index"))
        }
        groupAdapter.update(items)
    }
}

class BottomNavType01Item(private val titleText: String) : BindableItem<BottomNavType01ItemBinding>() {
    override fun getLayout() = R.layout.bottom_nav_type01_item

    override fun bind(viewBinding: BottomNavType01ItemBinding, position: Int) {
        viewBinding.bottomNavType01Item01TitleText.text = titleText
        Timber.d("on click button1(type01) position:%s, title:%s ",position, viewBinding.bottomNavType01Item01TitleText.text)
    }
}