package com.komeyama.sample.design.material.ui.textfield

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.DatePicker
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_text_field_type02.*
import timber.log.Timber

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

        edit_text_birthday.setOnClickListener {
            Timber.d("okok")
            showDataPickerDialog()
        }
    }

    private fun showDataPickerDialog() {
        val view = layoutInflater.inflate(R.layout.datepicker_dialog,null)
        val datePickerDialog: Dialog = AlertDialog.Builder(context)
            .setView(view)
            .setTitle(resources.getString(R.string.dialog_title))
            .setNegativeButton(resources.getString(R.string.dialog_cancel)) { _, _ -> }
            .setPositiveButton(resources.getString(R.string.dialog_ok)) { _, _ -> }
            .create()

        val datePicker: DatePicker = view!!.findViewById(R.id.dialog_date_picker)
        datePicker.init(2000, 1, 1) { _, year, month, day ->
            Timber.d("date picker select date: %s %s %s", year, month + 1, day)
        }

        datePickerDialog.show()
    }
}