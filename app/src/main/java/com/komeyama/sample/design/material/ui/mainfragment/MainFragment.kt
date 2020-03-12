package com.komeyama.sample.design.material.ui.mainfragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.komeyama.sample.design.material.R
import com.komeyama.sample.design.material.ui.mainfragment.MainFragmentData.DesignName
import com.komeyama.sample.design.material.ui.mainfragment.MainFragmentRecycleView.*
import timber.log.Timber

class MainFragment : Fragment(R.layout.fragment_main){

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val topListAdapter = TopListAdapter(MainFragmentData.mainItems, ItemClick {
                    Timber.d("tap: %s", it)
                    when (it.designName) {
                        DesignName.APP_BARS_BOTTOM.designName -> {
                            findNavController().navigate(R.id.action_mainFragment_to_bottomBarFragment)
                        }
                        DesignName.BACKDROP.designName -> {
                            findNavController().navigate(R.id.action_mainFragment_to_backDropTypeSelectFragment)
                        }
                        DesignName.CARD.designName -> {
                            findNavController().navigate(R.id.action_mainFragment_to_cardTypeFragment)
                        }
                        DesignName.DIALOG.designName -> {
                            findNavController().navigate(R.id.action_mainFragment_to_dialogSelectFragment)
                        }
                    }
                })

        view.findViewById<RecyclerView>(R.id.top_recycler_view).apply{
            adapter = topListAdapter
            layoutManager = GridLayoutManager(context, 2)
        }
    }
}