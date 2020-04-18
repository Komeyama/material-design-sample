package com.komeyama.sample.design.material

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView

class MainFragmentRecycleView {
    class TopListAdapter(private val items:List<DesignInformation>, private val itemClick: ItemClick): RecyclerView.Adapter<TopListHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopListHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            return TopListHolder(
                layoutInflater.inflate(R.layout.list_item_top, parent, false)
            )
        }

        override fun getItemCount() = items.size

        override fun onBindViewHolder(holder: TopListHolder, position: Int) {
            holder.let {
                it.imageView.setImageResource(items[position].imageResource)
                it.textView.text = items[position].designName
            }

            holder.view.setOnClickListener {
                itemClick.onClick(items[position])
            }
        }
    }

    class TopListHolder(val view: View): RecyclerView.ViewHolder(view) {
        companion object {
            @LayoutRes
            val LAYOUT = R.layout.list_item_top
        }

        val imageView: ImageView = view.findViewById(R.id.design_image)
        val textView: TextView = view.findViewById(R.id.design_name)
    }

    class ItemClick(val item:(DesignInformation) -> Unit) {
        fun onClick(item: DesignInformation) {
            item(item)
        }
    }

    data class DesignInformation(val imageResource:Int, val designName: String)
}