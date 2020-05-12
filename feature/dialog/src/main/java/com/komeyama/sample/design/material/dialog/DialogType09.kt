package com.komeyama.sample.design.material.dialog

import android.app.Dialog
import android.os.Bundle
import android.widget.DatePicker
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import timber.log.Timber

class DialogType09 : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val builder = AlertDialog.Builder(activity!!)
        val dialog = activity?.let {
            val view = it.layoutInflater.inflate(R.layout.fragment_dialog_type09,null)

            val datePicker: DatePicker = view.findViewById(R.id.dialog_date_picker)
            datePicker.init(2000, 1, 1) { _, year, month, day ->
                Timber.d("date picker select date: %s %s %s", year, month, day)
            }

            builder
                .setTitle(resources.getString(R.string.dialog_title))
                .setNegativeButton(resources.getString(R.string.dialog_cancel)) { _, _ -> }
                .setPositiveButton(resources.getString(R.string.dialog_accept)) { _, _ -> }
                .setView(view)
                .create()
        }
        dialog?.show()

        return dialog as Dialog
    }

}