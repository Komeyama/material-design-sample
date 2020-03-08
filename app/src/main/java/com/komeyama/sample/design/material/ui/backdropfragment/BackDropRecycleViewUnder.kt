package com.komeyama.sample.design.material.ui.backdropfragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.annotation.LayoutRes
import androidx.core.content.ContextCompat
import androidx.core.view.children
import androidx.recyclerview.widget.RecyclerView
import com.komeyama.sample.design.material.R
import kotlinx.android.synthetic.main.list_backdrop_under_sheet_item.view.*

class BackDropRecycleViewUnder {
    class BackDropUnderSheetAdapter(
        private val items:List<BackDropUnderSheetInformation>,
        private val itemClick: ItemClick
    ): RecyclerView.Adapter<BackDropUnderSheetListHolder>() {
        private var mParent: ViewGroup? = null

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BackDropUnderSheetListHolder {
            mParent = parent
            val layoutInflater = LayoutInflater.from(parent.context)
            return BackDropUnderSheetListHolder(layoutInflater.inflate(R.layout.list_backdrop_under_sheet_item, parent, false))
        }

        override fun getItemCount() = items.size

        override fun onBindViewHolder(holder: BackDropUnderSheetListHolder, position: Int) {
            holder.button.text = items[position].itemName
            holder.button.setOnClickListener {
                mParent?.children?.forEach { c ->
                    c.backdrop_under_design_button?.background?.setTint(ContextCompat.getColor(holder.view.context, R.color.colorPrimary))
                }
                itemClick.onClick(items[position], it)
            }
        }

        fun setButtonColor(name: String) {
            mParent?.children?.forEach{
                if (it.backdrop_under_design_button?.text == name) {
                    it.backdrop_under_design_button?.background?.setTint(ContextCompat.getColor(mParent?.context!!, R.color.colorWhiteThin32))
                }
            }
        }
    }

    class BackDropUnderSheetListHolder(val view: View): RecyclerView.ViewHolder(view) {
        companion object {
            @LayoutRes
            val LAYOUT = R.layout.list_backdrop_under_sheet_item
        }
        val button: Button = view.findViewById(R.id.backdrop_under_design_button)
    }

    class ItemClick(val item:(BackDropUnderSheetInformation, v: View) -> Unit) {
        fun onClick(item: BackDropUnderSheetInformation, v: View) {
            item(item, v)
        }
    }

    data class BackDropUnderSheetInformation(val itemName: String)
}