package com.komeyama.sample.design.material.ui.backdrop

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView

class BackDropRecycleView {
    class BackDropSheetAdapter(
        private val items:List<BackDropTopSheetInformation>,
        private val itemClick: ItemClick): RecyclerView.Adapter<BackDropSheetListHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BackDropSheetListHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            return BackDropSheetListHolder(layoutInflater.inflate(R.layout.list_backdrop_top_sheet_item, parent, false))
        }

        override fun getItemCount() = items.size

        override fun onBindViewHolder(holder: BackDropSheetListHolder, position: Int) {
            holder.imageView.setImageResource(items[position].imageResource)
            holder.textView.text = items[position].itemName
            holder.view.setOnClickListener {
                itemClick.onClick(items[position])
            }
        }
    }

    class BackDropSheetListHolder(val view: View): RecyclerView.ViewHolder(view) {
        companion object {
            @LayoutRes
            val LAYOUT = R.layout.list_backdrop_top_sheet_item
        }
        val imageView: ImageView = view.findViewById(R.id.backdrop_top_design_image)
        val textView: TextView = view.findViewById(R.id.backdrop_top_design_name)
    }

    class ItemClick(val item:(BackDropTopSheetInformation) -> Unit) {
        fun onClick(item: BackDropTopSheetInformation) {
            item(item)
        }
    }

    data class BackDropTopSheetInformation(val imageResource: Int,val itemName: String)
}

