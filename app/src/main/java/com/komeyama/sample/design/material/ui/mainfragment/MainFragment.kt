package com.komeyama.sample.design.material.ui.mainfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.komeyama.sample.design.material.R
import timber.log.Timber

class MainFragment : Fragment(){
    private var items: List<DesignInformation> = listOf(
        DesignInformation(R.drawable.top_image_bottom_bar, DesignName.APP_BARS_BOTTOM.designName),
        DesignInformation(R.drawable.top_image_backdrop, DesignName.BACKDROP.designName),
        DesignInformation(R.drawable.ic_launcher_foreground, DesignName.COMING_SOON.designName)
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        val root = inflater.inflate(R.layout.fragment_main, container, false)
        val topListAdapter = TopListAdapter(items, ItemClick {
            Timber.d("tap: %s",it)
            when(it.designName){
                DesignName.APP_BARS_BOTTOM.designName -> {
                    findNavController().navigate(R.id.action_mainFragment_to_bottomBarFragment)
                }
                DesignName.BACKDROP.designName -> {
                    findNavController().navigate(R.id.action_mainFragment_to_backDropTypeSelectFragment)
                }
            }
        })
        root.findViewById<RecyclerView>(R.id.top_recycler_view).apply{
            adapter = topListAdapter
            layoutManager = GridLayoutManager(context, 2)
        }

        return root
    }

}

class TopListAdapter(private val items:List<DesignInformation>, private val itemClick: ItemClick): RecyclerView.Adapter<TopListHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopListHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return TopListHolder(layoutInflater.inflate(R.layout.list_item_top, parent, false))
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
    val textView: TextView= view.findViewById(R.id.design_name)
}

class ItemClick(val item:(DesignInformation) -> Unit) {
    fun onClick(item: DesignInformation) {
        item(item)
    }
}

data class DesignInformation(val imageResource:Int, val designName: String)

enum class DesignName(val designName: String){
    APP_BARS_BOTTOM("App bars: bottom"),
    BACKDROP("Backdrop"),
    COMING_SOON("Coming soon")
}