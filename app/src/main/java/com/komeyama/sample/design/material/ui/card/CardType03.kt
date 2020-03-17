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
import timber.log.Timber

class CardType03: Fragment(R.layout.fragment_card_type03) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val groupAdapter = GroupAdapter<ViewHolder<*>>()
        card_recycler_view.adapter = groupAdapter
        val items = listOf(
            CardItem("Card Title1","sub text 1", View.VISIBLE),
            CardItem("Card Title2","sub text 2", View.GONE),
            CardItem("Card Title3","sub text 3", View.INVISIBLE),
            CardItem("Card Title4","sub text 4", View.VISIBLE),
            CardItem("Card Title5","sub text 5", View.VISIBLE),
            CardItem("Card Title6","sub text 6", View.VISIBLE)
        )
        groupAdapter.update(items)
    }
}

class CardItem(private val titleText: String, private val subTitleText: String, private val button2Visibility: Int) : BindableItem<ListItemCardBinding>() {
    override fun getLayout() = R.layout.list_item_card

    override fun bind(viewBinding: ListItemCardBinding, position: Int) {
        viewBinding.cardTitleText.text = titleText
        viewBinding.cardSecondaryText.text = subTitleText
        viewBinding.cardAction02.visibility = button2Visibility
        viewBinding.cardAction01.setOnClickListener {
            Timber.d("on click button1 position:%s, title:%s ",position, viewBinding.cardTitleText.text)
        }
        viewBinding.cardAction02.setOnClickListener {
            Timber.d("on click button2 position:%s, title:%s ",position, viewBinding.cardSecondaryText.text)
        }
    }
}