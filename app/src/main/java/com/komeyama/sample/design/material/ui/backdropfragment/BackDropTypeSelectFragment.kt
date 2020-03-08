package com.komeyama.sample.design.material.ui.backdropfragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.komeyama.sample.design.material.R
import kotlinx.android.synthetic.main.fragment_backdrop_select.*

class BackDropTypeSelectFragment: Fragment(R.layout.fragment_backdrop_select) {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        backdrop_type01_button.setOnClickListener {
            navigateBackDropFragment(BackDropType.TYPE_01)
        }
        backdrop_type_toolbar.setNavigationOnClickListener{
            findNavController().navigateUp()
        }
    }

    private fun navigateBackDropFragment(backDropType: BackDropType){
        val navDirections = when(backDropType) {
            BackDropType.TYPE_01 -> {
                BackDropTypeSelectFragmentDirections.actionBackDropTypeSelectFragmentToBackDropFragment(
                    backDropType = BackDropType.TYPE_01.backDropTypeName
                )
            }
        }
        findNavController().navigate(navDirections)
    }

}