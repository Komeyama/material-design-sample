package com.komeyama.sample.design.material.ui.card

import android.animation.LayoutTransition
import android.content.Context
import android.content.res.Resources
import android.os.Bundle
import android.view.View
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import com.komeyama.sample.design.material.R
import com.komeyama.sample.design.material.databinding.ListItemCardType01Binding
import com.komeyama.sample.design.material.databinding.ListItemCardType02Binding
import com.komeyama.sample.design.material.databinding.ListItemCardType04Binding
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.databinding.ViewHolder
import com.xwray.groupie.databinding.BindableItem
import kotlinx.android.synthetic.main.fragment_card_type03.*
import kotlinx.android.synthetic.main.fragment_card_type04.*
import timber.log.Timber

class CardType03: Fragment(R.layout.fragment_card_type03) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val groupAdapter = GroupAdapter<ViewHolder<*>>()
        card_recycler_view.adapter = groupAdapter
        val items = listOf(
            CardType01Item("Card Title1","sub text 1", View.VISIBLE),
            CardType01Item("Card Title2","sub text 2", View.GONE),
            CardType01Item("Card Title3","sub text 3", View.INVISIBLE),
            CardType04Item(resources),
            CardType01Item("Card Title4","sub text 4", View.VISIBLE),
            CardType02Item("Card Title5","sub text 5", resources),
            CardType01Item("Card Title6","sub text 6", View.VISIBLE)
        )
        groupAdapter.update(items)
    }
}

class CardType01Item(private val titleText: String, private val subTitleText: String, private val button2Visibility: Int) : BindableItem<ListItemCardType01Binding>() {
    override fun getLayout() = R.layout.list_item_card_type01

    override fun bind(viewBinding: ListItemCardType01Binding, position: Int) {
        viewBinding.cardTitleText.text = titleText
        viewBinding.cardSecondaryText.text = subTitleText
        viewBinding.cardAction02.visibility = button2Visibility
        viewBinding.cardAction01.setOnClickListener {
            Timber.d("on click button1(type01) position:%s, title:%s ",position, viewBinding.cardTitleText.text)
        }
        viewBinding.cardAction02.setOnClickListener {
            Timber.d("on click button2(type02) position:%s, title:%s ",position, viewBinding.cardSecondaryText.text)
        }
    }
}

class CardType02Item(private val titleText: String, private val subTitleText: String, private val resources: Resources) : BindableItem<ListItemCardType02Binding>() {

    override fun getLayout() = R.layout.list_item_card_type02

    override fun bind(viewBinding: ListItemCardType02Binding, position: Int) {
        viewBinding.cardFavorite.setOnClickListener {
            if (viewBinding.cardFavorite.drawable.constantState == ResourcesCompat.getDrawable(resources, R.drawable.ic_favorite_pink_24dp, null)?.constantState) {
                viewBinding.cardFavorite.startFavoriteAnimation(1.2f, R.drawable.ic_favorite_24dp)
            } else {
                viewBinding.cardFavorite.startFavoriteAnimation(0.2f, R.drawable.ic_favorite_pink_24dp)
            }
        }

        viewBinding.headerTitleText.text = titleText
        viewBinding.subheadTitleText.text = subTitleText
        viewBinding.cardAction01.setOnClickListener {
            Timber.d("on click button1(type02) position:%s, title:%s ",position, viewBinding.headerTitleText.text)
        }
        viewBinding.cardAction02.setOnClickListener {
            Timber.d("on click button2(type02) position:%s, title:%s ",position, viewBinding.subheadTitleText.text)
        }
    }
}

class CardType04Item(private val resources: Resources) : BindableItem<ListItemCardType04Binding>() {

    override fun getLayout() = R.layout.list_item_card_type04

    override fun bind(viewBinding: ListItemCardType04Binding, position: Int) {
        viewBinding.expandControlButton.setOnClickListener {
            if (viewBinding.textFiledConstraint.layoutParams.height == resources?.getDimensionPixelSize(R.dimen.card_text_field_height_reduce)!!.toInt()) {
                setCardTopHeight(viewBinding, R.dimen.card_top_height_expand,550,resources)
                setTextFiledConstraintHeight(viewBinding,R.dimen.card_text_field_height_expand,550,resources)
            } else {
                setCardTopHeight(viewBinding, R.dimen.card_top_height_reduce,550,resources)
                setTextFiledConstraintHeight(viewBinding,R.dimen.card_text_field_height_reduce,550,resources)
            }
        }
    }

    private fun setCardTopHeight(viewBinding: ListItemCardType04Binding, heightId:Int, duration: Long, resources: Resources) {
        val transition = viewBinding.cardTop.layoutTransition
        viewBinding.cardTop.layoutTransition.enableTransitionType(LayoutTransition.CHANGING)
        transition.setDuration(duration)

        viewBinding.cardTop.layoutParams.height = resources?.getDimensionPixelSize(heightId)!!.toInt()
    }

    private fun setTextFiledConstraintHeight(viewBinding: ListItemCardType04Binding, heightId: Int, duration: Long, resources: Resources) {
        val transition = viewBinding.cardTop.layoutTransition
        viewBinding.textFiledConstraint.layoutTransition.enableTransitionType(LayoutTransition.CHANGING)
        transition.setDuration(duration)

        viewBinding.textFiledConstraint.layoutParams.height = resources?.getDimensionPixelSize(heightId)!!.toInt()
        val constraintSet = ConstraintSet()
        constraintSet.clone(viewBinding.textFiledConstraint)
        constraintSet.applyTo(viewBinding.textFiledConstraint)
    }
}
