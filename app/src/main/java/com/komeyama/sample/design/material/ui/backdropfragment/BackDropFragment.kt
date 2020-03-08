package com.komeyama.sample.design.material.ui.backdropfragment

import android.os.Bundle
import android.view.*
import androidx.core.content.ContextCompat
import androidx.core.view.marginTop
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.komeyama.sample.design.material.R
import kotlinx.android.synthetic.main.fragment_backdrop.*
import timber.log.Timber

class BackDropFragment : Fragment(R.layout.fragment_backdrop){

    companion object {
        private var isRecycleViewScrollable = true
        private var topSheetName = SheetItemListName.ITEM_LIST_01.itemListName
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val args = BackDropFragmentArgs.fromBundle(arguments!!)
        Timber.d("backdrop fragment type %s", args.backDropType)

        initToolbar()
        initBackdropUnderSheet()
        initBackDropTopSheet()
    }

    private fun initToolbar() {
        backdrop_toolbar_title.title = topSheetName
        backdrop_toolbar_title.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun initBackdropUnderSheet() {
        val backdropUnderSheetAdapter =
            BackDropRecycleViewUnder.BackDropUnderSheetAdapter(BackDropUnderSheetData().backdropBottomDummyItems,
                BackDropRecycleViewUnder.ItemClick {
                    Timber.d("tap: %s", it)
                    topSheetName = it.itemName
                })
        activity!!.findViewById<RecyclerView>(R.id.backdrop_under_sheet_recycler_view).apply {
            adapter = backdropUnderSheetAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    private fun initBackDropTopSheet() {
        // top sheet recycleView
        val backDropSheetAdapter =
            BackDropRecycleView.BackDropSheetAdapter(BackDropTopSheetData().backdropDummyItems,
                BackDropRecycleView.ItemClick {
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
                it.changeImageResource(R.drawable.ic_close_24dp)
                behavior.state = BottomSheetBehavior.STATE_COLLAPSED
                top_layer_sheet_cover.visibility = View.VISIBLE
                isRecycleViewScrollable = false
                backdrop_toolbar_title.title = getString(R.string.backdrop_toolbar_title_name)
            } else {
                it.changeImageResource(R.drawable.ic_menu_24dp)
                behavior.state = BottomSheetBehavior.STATE_EXPANDED
                top_layer_sheet_cover.visibility = View.GONE
                isRecycleViewScrollable = true
                backdrop_toolbar_title.title = topSheetName
            }
            it.startRotateAnimation()
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