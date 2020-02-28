package com.komeyama.sample.design.material.ui.backdropfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.LayoutRes
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

    private var items: List<BackDropSheetInformation> = listOf(
        BackDropSheetInformation(R.drawable.ic_launcher_background, SheetItemName.ITEM_01.designName),
        BackDropSheetInformation(R.drawable.ic_launcher_foreground, SheetItemName.ITEM_02.designName),
        BackDropSheetInformation(R.drawable.ic_launcher_foreground, SheetItemName.ITEM_03.designName),
        BackDropSheetInformation(R.drawable.ic_launcher_background, SheetItemName.ITEM_04.designName),
        BackDropSheetInformation(R.drawable.ic_launcher_background, SheetItemName.ITEM_05.designName),
        BackDropSheetInformation(R.drawable.ic_launcher_foreground, SheetItemName.ITEM_06.designName),
        BackDropSheetInformation(R.drawable.ic_launcher_foreground, SheetItemName.ITEM_07.designName),
        BackDropSheetInformation(R.drawable.ic_launcher_background, SheetItemName.ITEM_08.designName),
        BackDropSheetInformation(R.drawable.ic_launcher_background, SheetItemName.ITEM_09.designName),
        BackDropSheetInformation(R.drawable.ic_launcher_background, SheetItemName.ITEM_10.designName),
        BackDropSheetInformation(R.drawable.ic_launcher_foreground, SheetItemName.ITEM_11.designName),
        BackDropSheetInformation(R.drawable.ic_launcher_foreground, SheetItemName.ITEM_12.designName)
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val root = inflater.inflate(R.layout.fragment_backdrop, container, false)
        val backDropSheetAdapter = BackDropSheetAdapter(items, ItemClick {
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

enum class SheetItemName(val designName: String){
    ITEM_01("item_1"),
    ITEM_02("item_2"),
    ITEM_03("item_3"),
    ITEM_04("item_4"),
    ITEM_05("item_5"),
    ITEM_06("item_6"),
    ITEM_07("item_7"),
    ITEM_08("item_8"),
    ITEM_09("item_9"),
    ITEM_10("item_10"),
    ITEM_11("item_11"),
    ITEM_12("item_12")
}