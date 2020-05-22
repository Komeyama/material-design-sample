package com.komeyama.sample.design.material.ui.topbar

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.komeyama.sample.design.material.ui.topbar.databinding.TopAppListItemBinding
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.databinding.BindableItem
import com.xwray.groupie.databinding.ViewHolder
import kotlinx.android.synthetic.main.fragment_top_bar_type03.*
import timber.log.Timber

class TopBarType03 : Fragment(R.layout.fragment_top_bar_type03) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val groupAdapter = GroupAdapter<ViewHolder<*>>()
        top_bar_type03_recycler_view.adapter = groupAdapter

        val items: MutableList<TopAppType03Item> = mutableListOf()
        for(index in 0 until 20) {
            items.add(TopAppType03Item("Top App Item $index"))
        }

        groupAdapter.update(items)

        top_bar_type03_recycler_view.addOnScrollListener(onScrollListener)
        top_bar_type03_top_toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        top_bar_type03_top_toolbar.inflateMenu(R.menu.top_bar_menu_black)
        val searchItem = top_bar_type03_top_toolbar.menu.findItem(R.id.bottom_bar_serch)
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

    private val onScrollListener = object: RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            top_bar_type03_top_toolbar.elevation = activity?.resources?.getDimensionPixelSize(R.dimen.top_bar_type03_elevation)!!.toFloat()
            if (!recyclerView.canScrollVertically(-1)) {
                top_bar_type03_top_toolbar.elevation = 0f
            }
        }
    }
}

class TopAppType03Item(private val titleText: String) : BindableItem<TopAppListItemBinding>() {
    override fun getLayout() = R.layout.top_app_list_item

    override fun bind(viewBinding: TopAppListItemBinding, position: Int) {
        viewBinding.topAppTitleText.text = titleText
        Timber.d("on click button1(type03) position:%s, title:%s ",position, viewBinding.topAppTitleText.text)
    }
}