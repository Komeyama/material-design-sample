package com.komeyama.sample.design.material.ui.backdropfragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.komeyama.sample.design.material.R

class BackDropRecycleViewUnder {
    class BackDropUnderSheetAdapter(
        private val items:List<BackDropUnderSheetInformation>,
        private val itemClick: ItemClick): RecyclerView.Adapter<BackDropUnderSheetListHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BackDropUnderSheetListHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            return BackDropUnderSheetListHolder(layoutInflater.inflate(R.layout.list_backdrop_under_sheet_item, parent, false))
        }

        override fun getItemCount() = items.size

        override fun onBindViewHolder(holder: BackDropUnderSheetListHolder, position: Int) {
            holder.button.text = items[position].itemName
            holder.button.setOnClickListener {
                itemClick.onClick(items[position])
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

    class ItemClick(val item:(BackDropUnderSheetInformation) -> Unit) {
        fun onClick(item: BackDropUnderSheetInformation) {
            item(item)
        }
    }

    data class BackDropUnderSheetInformation(val itemName: String)
}