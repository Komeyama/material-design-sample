package com.komeyama.sample.design.material.ui.card

import android.content.Context
import android.content.res.Resources
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.Transformation
import android.widget.RelativeLayout
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.komeyama.sample.design.material.R
import com.komeyama.sample.design.material.databinding.ListItemCardType01Binding
import com.komeyama.sample.design.material.databinding.ListItemCardType02Binding
import com.komeyama.sample.design.material.databinding.ListItemCardType03Binding
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.databinding.ViewHolder
import com.xwray.groupie.databinding.BindableItem
import kotlinx.android.synthetic.main.fragment_card_list.*
import timber.log.Timber

class CardList: Fragment(R.layout.fragment_card_list) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val groupAdapter = GroupAdapter<ViewHolder<*>>()
        card_recycler_view.adapter = groupAdapter
        val items = listOf(
            CardType01Item("Card Title1","sub text 1", View.VISIBLE) { transitionFragment() },
            CardType01Item("Card Title2","sub text 2", View.GONE){ transitionFragment() },
            CardType03Item("Card Title3","sub text 3"),
            CardType01Item("Card Title4","sub text 4", View.VISIBLE){ transitionFragment() },
            CardType02Item("Card Title5","sub text 5", resources),
            CardType03Item("Card Title6","sub text 6"),
            CardType01Item("Card Title7","sub text 7", View.VISIBLE){ transitionFragment() }
        )
        groupAdapter.update(items)

    }

    private fun transitionFragment() {
        findNavController().navigate(R.id.action_cardList_to_transitionCard)
    }
}

class CardType01Item(private val titleText: String, private val subTitleText: String, private val button2Visibility: Int, private val transition :() -> Unit ) : BindableItem<ListItemCardType01Binding>() {
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
        viewBinding.mediaImage.setOnClickListener {
            transition()
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

class CardType03Item(private val titleText: String, private val secondaryText: String): BindableItem<ListItemCardType03Binding>() {

    private var originalHeight:Int = 0
    private val customDuration = 500L

    override fun getLayout() = R.layout.list_item_card_type03

    override fun bind(viewBinding: ListItemCardType03Binding, position: Int) {
        viewBinding.cardTitleText.text = titleText
        viewBinding.cardSecondaryText.text = secondaryText

        setInitializeOfCard(viewBinding.expandableText)

        viewBinding.expandControlButton.setOnClickListener {
            viewBinding.expandableText.clearAnimation()
            if(viewBinding.expandableText.height > 0) {
                viewBinding.expandableText.startAnimation(
                    CustomAnimation(
                        viewBinding.expandableText,
                        0,
                        originalHeight,
                        customDuration
                    )
                )
            } else{
                viewBinding.expandableText.startAnimation(
                    CustomAnimation(
                        viewBinding.expandableText,
                        originalHeight,
                        0,
                        customDuration
                    )
                )
            }
        }
    }

    private fun setInitializeOfCard(textView: RelativeLayout) {
        textView.measure(0,0)
        originalHeight = textView.measuredHeight
        textView.layoutParams.height = 0
    }

    private class CustomAnimation(var view: View, private val endHeight: Int, var startHeight: Int, customDuration: Long): Animation() {

        init {
            duration = customDuration
        }

        override fun applyTransformation(interpolatedTime: Float, t: Transformation?) {
            val newHeight = (startHeight + (endHeight - startHeight) * interpolatedTime).toInt()
            view.layoutParams.height = newHeight
            view.requestLayout()
        }
    }
}
