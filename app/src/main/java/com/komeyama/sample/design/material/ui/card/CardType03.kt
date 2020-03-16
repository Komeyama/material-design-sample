package com.komeyama.sample.design.material.ui.card

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.komeyama.sample.design.material.R
import com.komeyama.sample.design.material.databinding.ListItemCardBinding
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.databinding.ViewHolder
import com.xwray.groupie.databinding.BindableItem
import kotlinx.android.synthetic.main.fragment_card_type03.*

class CardType03: Fragment(R.layout.fragment_card_type03) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val groupAdapter = GroupAdapter<ViewHolder<*>>()
        card_recycler_view.adapter = groupAdapter
        val items = listOf(
            TextItem("test1"),
            TextItem("test2"),
            TextItem("test3"),
            TextItem("test4"),
            TextItem("test5"),
            TextItem("test6")
        )
        groupAdapter.update(items)
    }
}

class TextItem(val text: String) : BindableItem<ListItemCardBinding>() {
    override fun getLayout() = R.layout.list_item_card

    override fun bind(viewBinding: ListItemCardBinding, position: Int) {
        viewBinding.textView.text = text
    }
}