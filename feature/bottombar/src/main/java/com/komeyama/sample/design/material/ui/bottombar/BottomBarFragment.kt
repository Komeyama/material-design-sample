package com.komeyama.sample.design.material.ui.bottombar

import android.os.Bundle
import android.view.*
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.bottomappbar.BottomAppBar.FAB_ANIMATION_MODE_SCALE
import com.google.android.material.bottomappbar.BottomAppBar.FAB_ANIMATION_MODE_SLIDE
import kotlinx.android.synthetic.main.fragment_bottm_bar.*
import timber.log.Timber

class BottomBarFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val root = inflater.inflate(R.layout.fragment_bottm_bar, container, false)
        val userListAdapter = BottomBarRecycleView.UserListAdapter(BottomBarData().bottomBarItems,
            BottomBarRecycleView.UserClick {
                Timber.d("tap: %s", it)
            })
        root.findViewById<RecyclerView>(R.id.bottom_recycler_view).apply{
            adapter = userListAdapter
            layoutManager = LinearLayoutManager(context)
        }

        return root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        bottom_bar.replaceMenu(R.menu.bottom_bar_menu)
        bottom_bar.setNavigationOnClickListener{
            Timber.d("tap navigation button")
        }

        bottom_bar.setOnMenuItemClickListener { item ->
            when(item.itemId) {
                R.id.bottom_bar_serch -> {
                    Timber.d("tap bottom bar search button")
                    true
                }
                R.id.bottom_bar_edit -> {
                    Timber.d("tap bottom more edit button")
                    true
                }
                else -> true
            }
        }

        bottom_fab.setOnClickListener {
            Timber.d("tap bottom fab button")
        }

        bottom_bar_fab_position_change.setOnClickListener {
            if(bottom_bar.fabAlignmentMode == BottomAppBar.FAB_ALIGNMENT_MODE_CENTER){
                bottom_bar.fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_END
                bottom_bar_fab_position_change.text = getString(R.string.bottom_bar_fab_position_button_center)
            } else {
                bottom_bar.fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_CENTER
                bottom_bar_fab_position_change.text = getString(R.string.bottom_bar_fab_position_button_end)
            }
        }

        val background = bottom_bar_fab_position_change_animation.background
        bottom_bar_fab_position_change_animation.setOnClickListener {
            if(bottom_bar.fabAnimationMode == FAB_ANIMATION_MODE_SCALE) {
                bottom_bar_fab_position_change_animation.setTextColor(ContextCompat.getColor(activity!!, R.color.colorWhiteThin))
                bottom_bar.fabAnimationMode = FAB_ANIMATION_MODE_SLIDE
                background.setTint(ContextCompat.getColor(activity!!, R.color.colorPrimaryDarkThin))
                bottom_bar_fab_position_change_animation.text = getString(R.string.bottom_bar_fab_position_animation_button_slide)
            } else {
                bottom_bar_fab_position_change_animation.setTextColor(ContextCompat.getColor(activity!!, R.color.colorWhite))
                bottom_bar.fabAnimationMode = FAB_ANIMATION_MODE_SCALE
                background.setTint(ContextCompat.getColor(activity!!, R.color.colorPrimaryDark))
                bottom_bar_fab_position_change_animation.text = getString(R.string.bottom_bar_fab_position_animation_button_scale)
            }
        }

        bottom_fragment_top_toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
    }
}