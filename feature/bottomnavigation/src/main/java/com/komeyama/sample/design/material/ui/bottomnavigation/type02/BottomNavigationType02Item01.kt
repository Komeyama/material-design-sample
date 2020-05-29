package com.komeyama.sample.design.material.ui.bottomnavigation.type02

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.komeyama.sample.design.material.ui.bottomnavigation.R
import com.komeyama.sample.design.material.ui.bottomnavigation.databinding.BottomNavType02ItemBinding
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.databinding.BindableItem
import com.xwray.groupie.databinding.ViewHolder
import kotlinx.android.synthetic.main.fragment_bottom_navigation_type02_item01.*
import timber.log.Timber

class BottomNavigationType02Item01: Fragment(R.layout.fragment_bottom_navigation_type02_item01) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val groupAdapter = GroupAdapter<ViewHolder<*>>()
        bottom_nav_type02_item01_recycler_view.adapter = groupAdapter
        val items: MutableList<BottomNavType02Item> = mutableListOf()
        for(index in 0 until 20) {
            Timber.d("bottom type01: %s", index)
            items.add(BottomNavType02Item("Bottom Navigation Type02 Item $index"))
        }
        groupAdapter.update(items)
    }
}

class BottomNavType02Item(private val titleText: String) : BindableItem<BottomNavType02ItemBinding>() {
    override fun getLayout() = R.layout.bottom_nav_type02_item

    override fun bind(viewBinding: BottomNavType02ItemBinding, position: Int) {
        viewBinding.bottomNavType02Item01TitleText.text = titleText
        Timber.d("on click button1(type02) position:%s, title:%s ",position, viewBinding.bottomNavType02Item01TitleText.text)
    }
}