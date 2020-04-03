package com.komeyama.sample.design.material.ui.card

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
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

    private var previewViewHolder: RecyclerView.ViewHolder?? = null

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

        val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN or ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT,
            ItemTouchHelper.UP or ItemTouchHelper.DOWN) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                val fromPosition = viewHolder.adapterPosition
                val toPosition = target.adapterPosition
                card_grid_recycler_view.adapter?.notifyItemMoved(fromPosition, toPosition)

                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {}

            override fun onSelectedChanged(viewHolder: RecyclerView.ViewHolder?, actionState: Int) {
                super.onSelectedChanged(viewHolder, actionState)
                when(actionState) {
                    2 -> {
                        previewViewHolder = viewHolder
                        viewHolder?.itemView?.alpha = 0.5f
                    }
                    else -> previewViewHolder?.itemView?.alpha = 1f
                }
            }

        })
        itemTouchHelper.attachToRecyclerView(card_grid_recycler_view)
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