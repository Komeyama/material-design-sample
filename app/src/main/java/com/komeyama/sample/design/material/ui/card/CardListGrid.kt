package com.komeyama.sample.design.material.ui.card

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.komeyama.sample.design.material.R
import com.komeyama.sample.design.material.databinding.ListItemCardGridBinding
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.databinding.BindableItem
import com.xwray.groupie.databinding.ViewHolder
import kotlinx.android.synthetic.main.fragment_card_list_grid.*
import timber.log.Timber

class CardListGrid: Fragment(R.layout.fragment_card_list_grid) {

    private val items = listOf(
        CardTypeGrid("Card Title1", "sub text 1"),
        CardTypeGrid("Card Title2", "sub text 2"),
        CardTypeGrid("Card Title3", "sub text 3"),
        CardTypeGrid("Card Title4", "sub text 4"),
        CardTypeGrid("Card Title5", "sub text 5"),
        CardTypeGrid("Card Title6", "sub text 6"),
        CardTypeGrid("Card Title7", "sub text 7")
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val groupAdapter = GroupAdapter<ViewHolder<*>>().apply {
            spanCount = 2
        }
        card_grid_recycler_view.apply {
            layoutManager = GridLayoutManager(activity, groupAdapter.spanCount).apply {
                spanSizeLookup = groupAdapter.spanSizeLookup
            }
            adapter = groupAdapter
        }
        groupAdapter.update(items)
    }

    private class CardTypeGrid(private val title:String, private val subTitle:String ): BindableItem<ListItemCardGridBinding>() {

        override fun getLayout() = R.layout.list_item_card_grid

        override fun bind(viewBinding: ListItemCardGridBinding, position: Int) {
            viewBinding.cardTitleText.text = title
            viewBinding.cardSecondaryText.text = subTitle
            viewBinding.mediaImage.setOnClickListener {
                Timber.d("grid card tap: %s", viewBinding.cardTitleText.text)
            }
        }

        override fun getSpanSize(spanCount: Int, position: Int): Int {
            return spanCount / 2
        }
    }
}