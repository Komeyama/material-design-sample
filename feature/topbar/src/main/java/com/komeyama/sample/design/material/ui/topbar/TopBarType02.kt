package com.komeyama.sample.design.material.ui.topbar

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.material.appbar.AppBarLayout
import com.komeyama.sample.design.material.ui.topbar.databinding.TopAppListItemBinding
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.databinding.BindableItem
import com.xwray.groupie.databinding.ViewHolder
import kotlinx.android.synthetic.main.fragment_top_bar_type02.*
import timber.log.Timber

class TopBarType02 : Fragment(R.layout.fragment_top_bar_type02) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val groupAdapter = GroupAdapter<ViewHolder<*>>()
        top_bar_type02_recycler_view.adapter = groupAdapter

        val items: MutableList<TopAppType02Item> = mutableListOf()
        for (index in 0 until 20) {
            items.add(TopAppType02Item("Top App Item $index"))
        }

        groupAdapter.update(items)

        val toolBarHeight = top_bar_typ02_toolbar.height
        AppBarLayout.OnOffsetChangedListener { _, verticalOffset ->
            when (verticalOffset) {
                0 -> {}
                toolBarHeight -> {}
                else -> {}
            }
        }
    }
}

class TopAppType02Item(private val titleText: String) : BindableItem<TopAppListItemBinding>() {
    override fun getLayout() = R.layout.top_app_list_item

    override fun bind(viewBinding: TopAppListItemBinding, position: Int) {
        viewBinding.topAppTitleText.text = titleText
        Timber.d(
            "on click button1(type02) position:%s, title:%s ",
            position,
            viewBinding.topAppTitleText.text
        )
    }
}