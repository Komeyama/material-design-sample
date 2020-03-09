package com.komeyama.sample.design.material.ui.backdropfragment

import android.os.Bundle
import android.os.Handler
import android.view.*
import android.widget.FrameLayout
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
import kotlinx.android.synthetic.main.list_backdrop_under_sheet_item.view.*
import timber.log.Timber

class BackDropFragment : Fragment(R.layout.fragment_backdrop){

    companion object {
        private var topSheetName = SheetItemListName.ITEM_LIST_01.itemListName
        private var mBackdropUnderSheetAdapter: BackDropRecycleViewUnder.BackDropUnderSheetAdapter? = null
        private const val rippleTime = 400L
        private var initBehaviorHeight = 0
        private var detailInformationHeight = 0
        private const val topSheetStandardPeekHeight = 2/3F
        private const val topSheetDetailPeekHeight = 2/5F
        private var statusBarHeight = 0
    }

    private var isRecycleViewScrollable = true

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
        mBackdropUnderSheetAdapter = BackDropRecycleViewUnder.BackDropUnderSheetAdapter(
                BackDropUnderSheetData().backdropBottomDummyItems,
                BackDropRecycleViewUnder.ItemClick { it, v ->
                    Timber.d("tap: %s", it)
                    Handler().postDelayed(
                        {v.backdrop_under_design_button.background.setTint(ContextCompat.getColor(activity!!, R.color.colorWhiteThin32)) },
                        rippleTime)
                    topSheetName = it.itemName
                }
        )
        activity!!.findViewById<RecyclerView>(R.id.backdrop_under_sheet_recycler_view).apply {
            adapter = mBackdropUnderSheetAdapter
            layoutManager = LinearLayoutManager(context)
        }

        activity!!.findViewById<RecyclerView>(R.id.backdrop_under_sheet_recycler_view).adapter
    }

    private fun initBackDropTopSheet() {
        // top sheet recycleView
        val backDropSheetAdapter = BackDropRecycleView.BackDropSheetAdapter(
                BackDropTopSheetData().backdropDummyItems,
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
        val behavior: BottomSheetBehavior<FrameLayout>  = BottomSheetBehavior.from(top_layer_sheet)
        initBehaviorHeight = (getDefaultDisplayHeight(activity!!) * topSheetStandardPeekHeight).toInt()
        backdrop_under_sheet_recycler_view.layoutParams.height = getDefaultDisplayHeight(activity!!) - getActionBarHeight(activity!!) - statusBarHeight - initBehaviorHeight
        detailInformationHeight = (getDefaultDisplayHeight(activity!!) * topSheetDetailPeekHeight).toInt()
        scrollView.layoutParams.height =  getDefaultDisplayHeight(activity!!) - getActionBarHeight(activity!!) - statusBarHeight - detailInformationHeight
        behavior.peekHeight = initBehaviorHeight
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
                mBackdropUnderSheetAdapter?.setButtonColor(topSheetName)
                switch_under_sheet.visibility = View.VISIBLE
            } else {
                it.changeImageResource(R.drawable.ic_menu_24dp)
                behavior.state = BottomSheetBehavior.STATE_EXPANDED
                top_layer_sheet_cover.visibility = View.GONE
                isRecycleViewScrollable = true
                backdrop_toolbar_title.title = topSheetName
                switch_under_sheet.visibility = View.GONE
                behavior.peekHeight = initBehaviorHeight
                setVisibilityOfUnderSheet(recycleViewVisibility=View.VISIBLE,detailViewVisibility=View.INVISIBLE)
            }
            it.startRotateAnimation()
        }

        switch_under_sheet.setOnClickListener {
            if (scrollView.visibility == View.INVISIBLE) {
                behavior.changePeekHeight(detailInformationHeight)
                setVisibilityOfUnderSheet(recycleViewVisibility=View.INVISIBLE,detailViewVisibility=View.VISIBLE)
            } else {
                behavior.changePeekHeight(initBehaviorHeight)
                setVisibilityOfUnderSheet(recycleViewVisibility= View.VISIBLE, detailViewVisibility=View.INVISIBLE)
            }
        }

        backdrop_top_sheet_recycler_view.addOnScrollListener(onScrollListener)
        backdrop_top_sheet_recycler_view.addOnItemTouchListener(onTouchListener)
    }

    private fun setVisibilityOfUnderSheet(recycleViewVisibility: Int, detailViewVisibility: Int) {
        backdrop_under_sheet_recycler_view.visibility = recycleViewVisibility
        scrollView.visibility = detailViewVisibility
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
        if (statusBarHeight == 0) {
            statusBarHeight = getStatusBarHeight(activity!!)
        }
        val topSheetHeight = getDefaultDisplayHeight(activity!!) - getActionBarHeight(activity!!) - statusBarHeight
        top_layer_sheet.setMaterialHeight(topSheetHeight)
        backdrop_top_sheet_recycler_view.setMaterialHeight(topSheetHeight - backdrop_sub_header_name.layoutParams.height - backdrop_sub_header_name.marginTop)
        backdrop_constraint_top.setMaterialHeight(topSheetHeight)
    }
}