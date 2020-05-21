package com.komeyama.sample.design.material.ui.topbar

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.komeyama.sample.design.material.ui.topbar.databinding.TopAppListItemBinding
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.databinding.BindableItem
import com.xwray.groupie.databinding.ViewHolder
import kotlinx.android.synthetic.main.fragment_top_bar_type01.*
import timber.log.Timber

class TopBarType01: Fragment(R.layout.fragment_top_bar_type01) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val groupAdapter = GroupAdapter<ViewHolder<*>>()
        top_bar_type01_recycler_view.adapter = groupAdapter

        val items: MutableList<TopAppType01Item> = mutableListOf()
        for(index in 0 until 20) {
            items.add(TopAppType01Item("Top App Item $index"))
        }

        groupAdapter.update(items)

        top_bar_type01_top_toolbar.inflateMenu(R.menu.top_bar_menu)

        top_bar_type01_top_toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
    }
}

class TopAppType01Item(private val titleText: String) : BindableItem<TopAppListItemBinding>() {
    override fun getLayout() = R.layout.top_app_list_item

    override fun bind(viewBinding: TopAppListItemBinding, position: Int) {
        viewBinding.topAppTitleText.text = titleText
            Timber.d("on click button1(type01) position:%s, title:%s ",position, viewBinding.topAppTitleText.text)
        }
}