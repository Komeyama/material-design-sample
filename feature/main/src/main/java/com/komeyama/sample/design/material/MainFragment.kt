package com.komeyama.sample.design.material

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import timber.log.Timber

class MainFragment : Fragment(R.layout.fragment_main){

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val topListAdapter =
            MainFragmentRecycleView.TopListAdapter(MainFragmentData.mainItems,
                MainFragmentRecycleView.ItemClick {
                    Timber.d("tap: %s", it)
                    when (it.designName) {
                        MainFragmentData.DesignName.APP_BARS_BOTTOM.designName -> {
                            findNavController().navigate(R.id.action_mainFragment_to_bottomBarFragment)
                        }
                        MainFragmentData.DesignName.APP_BARS_TOP.designName -> {
                            findNavController().navigate(R.id.action_mainFragment_to_topBarFragment)
                        }
                        MainFragmentData.DesignName.BACKDROP.designName -> {
                            findNavController().navigate(R.id.action_mainFragment_to_backDropTypeSelectFragment)
                        }
                        MainFragmentData.DesignName.CARD.designName -> {
                            findNavController().navigate(R.id.action_mainFragment_to_cardTypeFragment)
                        }
                        MainFragmentData.DesignName.DIALOG.designName -> {
                            findNavController().navigate(R.id.action_mainFragment_to_dialogSelectFragment)
                        }
                        MainFragmentData.DesignName.FLOATING_ACTION.designName -> {
                            findNavController().navigate(R.id.action_mainFragment_to_floatingSelectFragment)
                        }
                        MainFragmentData.DesignName.TEXT_FIELD.designName -> {
                            findNavController().navigate(R.id.action_mainFragment_to_textfieldSelectFragment)
                        }
                        MainFragmentData.DesignName.SELECTION_CONTROL.designName -> {
                            findNavController().navigate(R.id.action_mainFragment_to_selectionControlSelectionFragment)
                        }
                    }
                })

        view.findViewById<RecyclerView>(R.id.top_recycler_view).apply{
            adapter = topListAdapter
            layoutManager = GridLayoutManager(context, 2)
        }
    }
}