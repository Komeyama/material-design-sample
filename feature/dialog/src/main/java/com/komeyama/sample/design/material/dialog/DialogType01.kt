package com.komeyama.sample.design.material.dialog

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.google.android.material.button.MaterialButton
import timber.log.Timber

class DialogType01: DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val builder = AlertDialog.Builder(activity!!)
        val dialog = activity?.let {
            val view = it.layoutInflater.inflate(R.layout.fragment_dialog_type01,null)
            view.findViewById<MaterialButton>(R.id.dialog_type01_ok_button).setOnClickListener {
                Timber.d("tap ok")
            }
            view.findViewById<MaterialButton>(R.id.dialog_type01_no_button).setOnClickListener {
                Timber.d("tap no")
            }
            builder.setView(view).create()
        }

        dialog?.show()
        dialog?.window?.setLayout(
            activity?.resources?.getDimensionPixelSize(R.dimen.dialog_type01_width)!!.toInt(),
            activity?.resources?.getDimensionPixelSize(R.dimen.dialog_type01_height)!!.toInt()
        )

        return dialog as Dialog
    }

}