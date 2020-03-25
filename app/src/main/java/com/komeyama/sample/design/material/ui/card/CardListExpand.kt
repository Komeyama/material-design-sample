package com.komeyama.sample.design.material.ui.card

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.komeyama.sample.design.material.R
import com.komeyama.sample.design.material.databinding.ListItemExpandableCardBinding
import com.komeyama.sample.design.material.databinding.ListItemSimpleCardBinding
import com.xwray.groupie.*
import com.xwray.groupie.databinding.BindableItem
import kotlinx.android.synthetic.main.fragment_card_list_expand.*

class CardListExpand: Fragment(R.layout.fragment_card_list_expand) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val groupAdapter = GroupAdapter<ViewHolder>().apply {
            spanCount = 3
        }
        expand_recycler_view.adapter = groupAdapter

        ExpandableGroup(ExpandableCardItem("expand test01"), false).apply {
            add(Section(SimpleCardItem("test01")))
            groupAdapter.add(this)
        }
        ExpandableGroup(ExpandableCardItem("expand test02"), false).apply {
            add(Section(SimpleCardItem("test02")))
            groupAdapter.add(this)
        }
        ExpandableGroup(ExpandableCardItem("expand test03"), false).apply {
            add(Section(SimpleCardItem("test03")))
            groupAdapter.add(this)
        }
    }
}

class SimpleCardItem(private val text: String) : BindableItem<ListItemSimpleCardBinding>(){

    override fun bind(viewHolder: ListItemSimpleCardBinding, position: Int) {
        viewHolder.itemSimpleCardText.text = text
    }

    override fun getLayout() = R.layout.list_item_simple_card
}

class ExpandableCardItem(private val title: String) : BindableItem<ListItemExpandableCardBinding>(title.hashCode().toLong()) , ExpandableItem {

    private lateinit var expandableGroup: ExpandableGroup

    override fun bind(viewBinding: ListItemExpandableCardBinding, position: Int) {
        viewBinding.itemExpandableCardTitle.text = title

        viewBinding.itemExpandableCard.setOnClickListener {
            expandableGroup.onToggleExpanded()
        }
    }

    override fun getLayout() = R.layout.list_item_expandable_card

    override fun setExpandableGroup(onToggleListener: ExpandableGroup) {
        expandableGroup = onToggleListener
    }
}