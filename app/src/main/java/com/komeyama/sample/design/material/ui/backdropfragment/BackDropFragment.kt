package com.komeyama.sample.design.material.ui.backdropfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.shape.MaterialShapeDrawable
import com.komeyama.sample.design.material.R
import kotlinx.android.synthetic.main.fragment_backdrop.*

class BackDropFragment : Fragment(){

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_backdrop, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val materialShapeDrawable = MaterialShapeDrawable.createWithElevationOverlay(
            activity,
            resources.getDimension(R.dimen.bottom_sheet_elevation)
        )
        top_layer_sheet.background = materialShapeDrawable
    }
}