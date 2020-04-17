package com.komeyama.sample.design.material.card

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.komeyama.sample.design.material.card.databinding.ListItemCardHorizontalBinding
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.databinding.BindableItem
import com.xwray.groupie.databinding.ViewHolder
import kotlinx.android.synthetic.main.fragment_card_list_horizontal.*
import timber.log.Timber

class CardListHorizontal: Fragment(R.layout.fragment_card_list_horizontal) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val groupAdapter = GroupAdapter<ViewHolder<*>>()
        card_horizontal_recycler_view.adapter = groupAdapter
        val items = listOf(
            CardTypeHorizontal("Card Title1","sub text 1"),
            CardTypeHorizontal("Card Title2","sub text 2"),
            CardTypeHorizontal("Card Title3","sub text 3"),
            CardTypeHorizontal("Card Title4","sub text 4"),
            CardTypeHorizontal("Card Title5","sub text 5")
        )
        groupAdapter.update(items)
    }
}

class CardTypeHorizontal(private val title:String, private val subTitle:String ): BindableItem<ListItemCardHorizontalBinding>() {

    override fun getLayout() = R.layout.list_item_card_horizontal

    override fun bind(viewBinding: ListItemCardHorizontalBinding, position: Int) {
        viewBinding.cardTitleText.text = title
        viewBinding.cardSecondaryText.text = subTitle

        viewBinding.mediaImage.setOnClickListener {
            Timber.d("horizontal card tap: %s", viewBinding.cardTitleText.text)
        }
    }
}