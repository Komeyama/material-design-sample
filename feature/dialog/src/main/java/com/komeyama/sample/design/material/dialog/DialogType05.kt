package com.komeyama.sample.design.material.dialog

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

class DialogType05 : DialogFragment(){

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val builder = AlertDialog.Builder(activity!!)
        val dialog = activity?.let {
            val view = it.layoutInflater.inflate(R.layout.fragment_dialog_type05,null)
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