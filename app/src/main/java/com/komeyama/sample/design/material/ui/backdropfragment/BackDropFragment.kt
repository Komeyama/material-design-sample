package com.komeyama.sample.design.material.ui.backdropfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.shape.CornerFamily
import com.google.android.material.shape.MaterialShapeDrawable
import com.google.android.material.shape.ShapeAppearanceModel
import com.komeyama.sample.design.material.R
import kotlinx.android.synthetic.main.fragment_backdrop.*
import timber.log.Timber

class BackDropFragment : Fragment(){

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
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
    }

    private fun initBackDropTopSheet() {
        val shapeAppearanceModel = ShapeAppearanceModel.Builder().setTopLeftCorner(
            CornerFamily.ROUNDED,
            resources.getDimension(R.dimen.backdrop_sheet_corner_radius)
        ).build()

        val materialShapeDrawable = MaterialShapeDrawable.createWithElevationOverlay(
            activity,
            resources.getDimension(R.dimen.bottom_sheet_elevation)
        ).apply {
            setShapeAppearanceModel(shapeAppearanceModel)
        }
        top_layer_sheet.background = materialShapeDrawable

        val behavior = BottomSheetBehavior.from(top_layer_sheet)
        sheet_operation_button.setOnClickListener {
            if (behavior.state == BottomSheetBehavior.STATE_EXPANDED){
                behavior.setState(BottomSheetBehavior.STATE_COLLAPSED)
            } else {
                behavior.setState(BottomSheetBehavior.STATE_EXPANDED)
            }
        }
        behavior.addBottomSheetCallback(bottomSheetCallback)

        backdrop_top_sheet_recycler_view.addOnScrollListener(onScrollListener)
    }

    private val bottomSheetCallback = object : BottomSheetBehavior.BottomSheetCallback() {
        override fun onSlide(bottomSheet: View, slideOffset: Float) {}

        override fun onStateChanged(bottomSheet: View, newState: Int) {
            when(newState) {
                BottomSheetBehavior.STATE_EXPANDED -> sheet_operation_button.setImageResource(R.drawable.ic_arrow_drop_down_24dp)
                BottomSheetBehavior.STATE_COLLAPSED -> sheet_operation_button.setImageResource(R.drawable.ic_arrow_drop_up_24dp)
                else -> sheet_operation_button.setImageDrawable(null)
            }
        }
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