package com.komeyama.sample.design.material.dialog

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.fragment_dialog_selection.*

class DialogSelectFragment : Fragment(R.layout.fragment_dialog_selection) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dialog_type01.setOnClickListener {
            showAlertDialogType01()
        }

        dialog_type02.setOnClickListener {
            showAlertDialogType02()
        }

        dialog_type03.setOnClickListener {
            showConfirmationDialog()
        }

        dialog_type04.setOnClickListener {
            showMultiChoiceDialog()
        }

        dialog_type05.setOnClickListener {
            findNavController().navigate(R.id.action_dialogSelectFragment_to_dialogType05)
        }

        dialog_ios_like.setOnClickListener {
            findNavController().navigate(R.id.action_dialogSelectFragment_to_dialogType01)
        }
    }

    private fun showAlertDialogType01() {
        MaterialAlertDialogBuilder(context)
            .setTitle(resources.getString(R.string.dialog_title))
            .setMessage(resources.getString(R.string.dialog_supporting_text))
            .setNeutralButton(resources.getString(R.string.dialog_cancel)) { _, _ -> }
            .setNegativeButton(resources.getString(R.string.dialog_decline)) { _, _ -> }
            .setPositiveButton(resources.getString(R.string.dialog_accept)) { _, _ -> }
            .show()
    }

    private fun showAlertDialogType02() {
        MaterialAlertDialogBuilder(context)
            .setMessage(resources.getString(R.string.dialog_discard_draft))
            .setNegativeButton(resources.getString(R.string.dialog_cancel)) { _, _ -> }
            .setPositiveButton(resources.getString(R.string.dialog_accept)) { _, _ -> }
            .show()
    }

    private fun showConfirmationDialog() {
        val items = arrayOf("Item01", "Item02", "Item03")
        val dialog = MaterialAlertDialogBuilder(context, R.style.ConfirmationDialogTheme)
            .setTitle(resources.getString(R.string.dialog_title))
            .setSingleChoiceItems(items, 0) { _, _ -> }
            .setNeutralButton(resources.getString(R.string.dialog_cancel)) { _, _ -> }
            .setPositiveButton(resources.getString(R.string.dialog_ok)) { _, _ -> }
            .create()

        dialog.setOnShowListener {
            dialog.getButton(AlertDialog.BUTTON_NEUTRAL)
                .setTextColor(ContextCompat.getColor(activity!!, R.color.colorBlue))
            dialog.getButton(AlertDialog.BUTTON_NEUTRAL).background.setTint(
                ContextCompat.getColor(
                    activity!!,
                    R.color.colorWhiteThin00
                )
            )
            dialog.getButton(AlertDialog.BUTTON_POSITIVE)
                .setTextColor(ContextCompat.getColor(activity!!, R.color.colorBlue))
            dialog.getButton(AlertDialog.BUTTON_POSITIVE).background.setTint(
                ContextCompat.getColor(
                    activity!!,
                    R.color.colorWhiteThin00
                )
            )
        }
        dialog.show()
    }

    private fun showMultiChoiceDialog() {
        val items = arrayOf("Item01", "Item02", "Item03")
        val checkedItems = booleanArrayOf(false,true,true)
        val dialog = MaterialAlertDialogBuilder(context, R.style.ConfirmationDialogTheme)
            .setTitle(resources.getString(R.string.dialog_title))
            .setMultiChoiceItems(items, checkedItems) { _, _, _ -> }
            .setNeutralButton(resources.getString(R.string.dialog_cancel)) { _, _ -> }
            .setPositiveButton(resources.getString(R.string.dialog_ok)) { _, _ -> }
            .create()

        dialog.setOnShowListener {
            dialog.getButton(AlertDialog.BUTTON_NEUTRAL)
                .setTextColor(ContextCompat.getColor(activity!!, R.color.colorBlue))
            dialog.getButton(AlertDialog.BUTTON_NEUTRAL).background.setTint(
                ContextCompat.getColor(
                    activity!!,
                    R.color.colorWhiteThin00
                )
            )
            dialog.getButton(AlertDialog.BUTTON_POSITIVE)
                .setTextColor(ContextCompat.getColor(activity!!, R.color.colorBlue))
            dialog.getButton(AlertDialog.BUTTON_POSITIVE).background.setTint(
                ContextCompat.getColor(
                    activity!!,
                    R.color.colorWhiteThin00
                )
            )
        }
        dialog.show()
    }



}