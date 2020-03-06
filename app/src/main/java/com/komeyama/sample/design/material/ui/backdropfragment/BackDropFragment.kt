package com.komeyama.sample.design.material.ui.backdropfragment

import android.os.Bundle
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.core.content.ContextCompat
import androidx.core.view.marginTop
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.komeyama.sample.design.material.R
import kotlinx.android.synthetic.main.fragment_backdrop.*
import timber.log.Timber

class BackDropFragment : Fragment(R.layout.fragment_backdrop){

    private var isRecycleViewScrollable = true

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val args = BackDropFragmentArgs.fromBundle(arguments!!)
        Timber.d("backdrop fragment type %s", args.backDropType)

        initBackDropTopSheet()

        backdrop_toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun initBackDropTopSheet() {
        // recycleView
        val backDropSheetAdapter = BackDropSheetAdapter(BackDropData().backdropDummyItems, ItemClick {
            Timber.d("tap: %s", it)
        })
        activity!!.findViewById<RecyclerView>(R.id.backdrop_top_sheet_recycler_view).apply {
            adapter = backDropSheetAdapter
            layoutManager = GridLayoutManager(context, 2)
        }

        // sheet shape and height
        top_layer_sheet.createTopSheetMaterialShape(activity!!, R.color.colorWhite)
        top_layer_sheet_cover.createTopSheetMaterialShape(activity!!, R.color.colorWhiteThin)
        setTopSheetHeight()

        // sheet control
        val behavior = BottomSheetBehavior.from(top_layer_sheet)
        behavior.isDraggable = false
        behavior.state = BottomSheetBehavior.STATE_EXPANDED
        top_layer_sheet_cover.visibility = View.GONE
        switch_sheet.setOnClickListener {
            if (behavior.state == BottomSheetBehavior.STATE_EXPANDED){
                behavior.state = BottomSheetBehavior.STATE_COLLAPSED
                top_layer_sheet_cover.visibility = View.VISIBLE
                isRecycleViewScrollable = false
            } else {
                behavior.state = BottomSheetBehavior.STATE_EXPANDED
                top_layer_sheet_cover.visibility = View.GONE
                isRecycleViewScrollable = true
            }
        }
        backdrop_top_sheet_recycler_view.addOnScrollListener(onScrollListener)
        backdrop_top_sheet_recycler_view.addOnItemTouchListener(onTouchListener)
    }

    private val onScrollListener = object: RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            backdrop_sheet_divider.background.setTint(ContextCompat.getColor(activity!!, R.color.colorLightGray))
            if (!recyclerView.canScrollVertically(-1)) {
                backdrop_sheet_divider.background.setTint(ContextCompat.getColor(activity!!, R.color.colorWhite))
            }
        }
    }

    private val onTouchListener = object: RecyclerView.OnItemTouchListener {
        override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
            return !isRecycleViewScrollable
        }
        override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {}
        override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {}
    }

    private fun setTopSheetHeight() {
        val topSheetHeight = getDefaultDisplayHeight(activity!!) - getActionBarHeight(activity!!) - getStatusBarHeight(activity!!)
        top_layer_sheet.setMaterialHeight(topSheetHeight)
        backdrop_top_sheet_recycler_view.setMaterialHeight(topSheetHeight - backdrop_sub_header_name.layoutParams.height - backdrop_sub_header_name.marginTop)
        backdrop_constraint_top.setMaterialHeight(topSheetHeight)
    }
}

class BackDropSheetAdapter(private val items:List<BackDropSheetInformation>, private val itemClick: ItemClick): RecyclerView.Adapter<BackDropSheetListHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BackDropSheetListHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return BackDropSheetListHolder(layoutInflater.inflate(R.layout.list_backdrop_top_sheet_item, parent, false))
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: BackDropSheetListHolder, position: Int) {
        holder.let {
            it.imageView.setImageResource(items[position].imageResource)
            it.textView.text = items[position].itemName
        }

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

    val imageView: ImageView = view.findViewById(R.id.design_image)
    val textView: TextView = view.findViewById(R.id.design_name)
}

class ItemClick(val item:(BackDropSheetInformation) -> Unit) {
    fun onClick(item: BackDropSheetInformation) {
        item(item)
    }
}

data class BackDropSheetInformation(val imageResource:Int, val itemName: String)