package com.komeyama.sample.design.material.card

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.komeyama.sample.design.material.card.databinding.ListItemConversionBinding
import com.komeyama.sample.design.material.card.databinding.ListItemMarketingBinding
import com.komeyama.sample.design.material.card.databinding.ListItemSalesBinding
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.databinding.BindableItem
import com.xwray.groupie.databinding.ViewHolder
import kotlinx.android.synthetic.main.fragment_card_list_dashboard.*

class CardListDashboard : Fragment(R.layout.fragment_card_list_dashboard) {

    private val items = listOf(
        CardType1("Marketing", "123.4 M"),
        CardType2("Conversion", "537", "+22% of target"),
        CardType2("Conversion", "432.1 M","+12.3% of target"),
        CardType3("Sales", "345.8 M", "+11% of target"),
        CardType1("Users", "45.5 M"),
        CardType3("Avg. session", "4:53 H", "+56.6% of target"),
        CardType1("Sessions", "23,242"),
        CardType1("Marketing", "123.4 M"),
        CardType2("Conversion", "537", "+22% of target"),
        CardType2("Conversion", "432.1 M","+12.3% of target"),
        CardType3("Sales", "345.8 M", "+11% of target"),
        CardType1("Users", "45.5 M"),
        CardType3("Avg. session", "4:53 H", "+56.6% of target"),
        CardType1("Sessions", "23,242")
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val groupAdapter = GroupAdapter<ViewHolder<*>>()
        card_dashboard_recycler_view.apply {
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            adapter = groupAdapter
        }
        groupAdapter.update(items)
    }

    private class CardType1(private val title:String, private val subTitle:String ): BindableItem<ListItemMarketingBinding>() {

        override fun getLayout() = R.layout.list_item_marketing

        override fun bind(viewBinding: ListItemMarketingBinding, position: Int) {
            viewBinding.cardTitleText.text = title
            viewBinding.cardSecondaryText.text = subTitle
        }
    }

    private class CardType2(private val title:String, private val text1:String, private val text2:String ): BindableItem<ListItemConversionBinding>() {

        override fun getLayout() = R.layout.list_item_conversion

        override fun bind(viewBinding: ListItemConversionBinding, position: Int) {
            viewBinding.cardTitleText.text = title
            viewBinding.cardSecondaryText1.text = text1
            viewBinding.cardSecondaryText2.text = text2
        }
    }

    private class CardType3(private val title:String, private val text1: String, private val text2: String): BindableItem<ListItemSalesBinding>() {

        override fun getLayout() = R.layout.list_item_sales

        override fun bind(viewBinding: ListItemSalesBinding, position: Int) {
            viewBinding.cardTitleText.text = title
            viewBinding.cardSecondaryText1.text = text1
            viewBinding.cardSecondaryText2.text = text2
        }
    }

}