package com.komeyama.sample.design.material.ui.bottombarfragment

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.komeyama.sample.design.material.R
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
        return inflater.inflate(R.layout.fragment_bottm_bar, container, false)
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
                R.id.bottom_bar_more_vert -> {
                    Timber.d("tap bottom more vert button")
                    true
                }
                else -> true
            }
        }
        bottom_fab.setOnClickListener {
            Timber.d("tap bottom fab button")
        }
    }
}
