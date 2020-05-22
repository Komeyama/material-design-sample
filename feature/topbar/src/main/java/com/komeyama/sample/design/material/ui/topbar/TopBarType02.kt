package com.komeyama.sample.design.material.ui.topbar

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
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

        top_bar_type02_top_toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        top_bar_type02_top_toolbar.inflateMenu(R.menu.top_bar_menu_fragment)
        val searchItem = top_bar_type02_top_toolbar.menu.findItem(R.id.bottom_bar_search_white)
        val searchView = searchItem.actionView as SearchView
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