package com.komeyama.sample.design.material.ui.backdropfragment

import android.content.res.ColorStateList
import android.graphics.Rect
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.TypedValue
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
import com.google.android.material.shape.CornerFamily
import com.google.android.material.shape.MaterialShapeDrawable
import com.google.android.material.shape.ShapeAppearanceModel
import com.komeyama.sample.design.material.R
import kotlinx.android.synthetic.main.fragment_backdrop.*
import kotlinx.android.synthetic.main.fragment_bottm_bar.*
import timber.log.Timber

class BackDropFragment : Fragment(){

    private var isRecycleViewScrollable = true

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        val args = BackDropFragmentArgs.fromBundle(arguments!!)
        Timber.d("backdrop fragment type %s", args.backDropType)

        val root = inflater.inflate(R.layout.fragment_backdrop, container, false)
        val backDropSheetAdapter = BackDropSheetAdapter(BackDropData().backdropDummyItems, ItemClick {
            Timber.d("tap: %s", it)
        })
        root.findViewById<RecyclerView>(R.id.backdrop_top_sheet_recycler_view).apply{
            adapter = backDropSheetAdapter
            layoutManager = GridLayoutManager(context, 2)
        }

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initBackDropTopSheet()
        backdrop_toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun initBackDropTopSheet() {
        val shapeAppearanceModel = ShapeAppearanceModel.Builder().setTopLeftCorner(
            CornerFamily.ROUNDED,
            resources.getDimension(R.dimen.backdrop_sheet_corner_radius)
        ).build()

        val topLayerSheetMaterialShapeDrawable = MaterialShapeDrawable.createWithElevationOverlay(
            activity,
            resources.getDimension(R.dimen.bottom_sheet_elevation)
        ).apply {
            setShapeAppearanceModel(shapeAppearanceModel)
        }
        top_layer_sheet.background = topLayerSheetMaterialShapeDrawable

        val topLayerSheetCoverMaterialShapeDrawable = MaterialShapeDrawable.createWithElevationOverlay(
            activity,
            resources.getDimension(R.dimen.bottom_sheet_elevation)
        ).apply {
            setShapeAppearanceModel(shapeAppearanceModel)
        }
        topLayerSheetCoverMaterialShapeDrawable.fillColor = ColorStateList.valueOf(activity!!.getColor(R.color.colorWhiteThin))
        top_layer_sheet_cover.background = topLayerSheetCoverMaterialShapeDrawable

        val behavior = BottomSheetBehavior.from(top_layer_sheet)
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

        behavior.isDraggable = false

        setTopSheetHeight()
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
        override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {}

        override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
            return !isRecycleViewScrollable
        }

        override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {}

    }

    private fun setTopSheetHeight() {
        val topSheetHeight = getDefaultDisplayHeight() - getActionBarHeight() - getStatusBarHeight()
        val topLayerSheetParams = top_layer_sheet.layoutParams
        topLayerSheetParams.height = topSheetHeight

        val backDropRecyclerViewParams = backdrop_top_sheet_recycler_view.layoutParams
        backDropRecyclerViewParams.height = topSheetHeight - backdrop_sub_header_name.layoutParams.height - backdrop_sub_header_name.marginTop

        val backDropConstraintTopParams = backdrop_constraint_top.layoutParams
        backDropConstraintTopParams.height = topSheetHeight
    }

    private fun getDefaultDisplayHeight(): Int {
        val dm = DisplayMetrics()
        activity?.windowManager?.defaultDisplay?.getMetrics(dm)
        return dm.heightPixels
    }

    private fun getActionBarHeight(): Int {
        var actionBarHeight = 0
        val tv = TypedValue()
        if (activity?.theme!!.resolveAttribute(android.R.attr.actionBarSize, tv, true))
        {
            actionBarHeight = TypedValue.complexToDimensionPixelSize(tv.data, resources.displayMetrics)
        }
        return actionBarHeight
    }

    private fun getStatusBarHeight(): Int {
        val rect = Rect()
        val window: Window = activity!!.window
        window.decorView.getWindowVisibleDisplayFrame(rect)
        return rect.top
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