package com.komeyama.sample.design.material.ui.textfield

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_text_field_type02.*

class TextFieldType02 : Fragment(R.layout.fragment_text_field_type02) {

    private val areas = arrayOf<String?>("010", "020", "030", "040", "050", "060", "070", "080", "090")
    private val state = arrayOf<String?>("StateA","StateB","StateC","StateD","StateE")

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val areaAdapter: ArrayAdapter<String?> = ArrayAdapter(
            context!!,
            R.layout.phone_list,
            areas
        )
        phone_exposed_dropdown.setAdapter(areaAdapter)

        val stateAdapter: ArrayAdapter<String?> = ArrayAdapter(
            context!!,
            R.layout.phone_list,
            state
        )
        state_exposed_dropdown.setAdapter(stateAdapter)
    }
}