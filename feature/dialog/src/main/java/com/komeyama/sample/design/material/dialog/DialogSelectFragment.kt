package com.komeyama.sample.design.material.dialog

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.NumberPicker
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.fragment_dialog_selection.*
import timber.log.Timber
import java.util.*

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

        dialog_type06.setOnClickListener {
            findNavController().navigate(R.id.action_dialogSelectFragment_to_dialogType06)
        }

        dialog_type07.setOnClickListener {
            findNavController().navigate(R.id.action_dialogSelectFragment_to_dialogType07)
        }

        dialog_type08.setOnClickListener {
            showDataPickerDialog()
        }

        dialog_type09.setOnClickListener {
            findNavController().navigate(R.id.action_dialogSelectFragment_to_dialogType09)
        }

        dialog_type10.setOnClickListener {
            showNumberPickerDialog()
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

    private fun showDataPickerDialog() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val datePickerDialog = DatePickerDialog(activity!!, R.style.DatePickerDialogTheme,
            DatePickerDialog.OnDateSetListener { _, year, month, day ->
            Timber.d("date picker select date: %s %s %s", year, month, day)
        },year, month, day)

        datePickerDialog.setOnShowListener {
            datePickerDialog.getButton(AlertDialog.BUTTON_NEGATIVE)
                .setTextColor(ContextCompat.getColor(activity!!, R.color.colorPrimary))
            datePickerDialog.getButton(AlertDialog.BUTTON_NEGATIVE).background.setTint(
                ContextCompat.getColor(
                    activity!!,
                    R.color.colorWhiteThin00
                )
            )
            datePickerDialog.getButton(AlertDialog.BUTTON_POSITIVE)
                .setTextColor(ContextCompat.getColor(activity!!, R.color.colorPrimary))
            datePickerDialog.getButton(AlertDialog.BUTTON_POSITIVE).background.setTint(
                ContextCompat.getColor(
                    activity!!,
                    R.color.colorWhiteThin00
                )
            )
        }

        datePickerDialog.show()
    }

    private fun showNumberPickerDialog() {
        val view = layoutInflater.inflate(R.layout.number_picker_dialog,null)
        val datePickerDialog: Dialog = AlertDialog.Builder(context)
            .setView(view)
            .setTitle(resources.getString(R.string.dialog_title))
            .setNegativeButton(resources.getString(R.string.dialog_cancel)) { _, _ -> }
            .setPositiveButton(resources.getString(R.string.dialog_ok)) { _, _ -> }
            .create()

        val np: NumberPicker = view.findViewById(R.id.dialog_number_picker)
        np.minValue = 0
        np.maxValue = 100
        np.value = 50

        datePickerDialog.show()
    }

}