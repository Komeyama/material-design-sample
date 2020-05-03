package com.komeyama.sample.design.material.dialog

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.fragment_dialog_select.*

class DialogSelectFragment: Fragment(R.layout.fragment_dialog_select) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dialog_type01.setOnClickListener {
            findNavController().navigate(R.id.action_dialogSelectFragment_to_dialogType01)
        }

        dialog_type02.setOnClickListener {
            showAlertDialogType01()
        }

        dialog_type03.setOnClickListener {
            showAlertDialogType02()
        }

    }

    private fun showAlertDialogType01() {
        MaterialAlertDialogBuilder(context)
            .setTitle(resources.getString(R.string.dialog_title))
            .setMessage(resources.getString(R.string.dialog_supporting_text))
            .setNeutralButton(resources.getString(R.string.dialog_cancel)) { _, _ -> }
            .setNegativeButton(resources.getString(R.string.dialog_decline)) { _, _ -> }
            .setPositiveButton(resources.getString(R.string.dialog_accept)) { _, _ ->}
            .show()
    }

    private fun showAlertDialogType02() {
        MaterialAlertDialogBuilder(context)
            .setMessage(resources.getString(R.string.dialog_discard_draft))
            .setNegativeButton(resources.getString(R.string.dialog_cancel)) { _, _ -> }
            .setPositiveButton(resources.getString(R.string.dialog_accept)) { _, _ ->}
            .show()
    }

}