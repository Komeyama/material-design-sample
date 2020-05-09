package com.komeyama.sample.design.material.dialog

import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment

class DialogType05 : DialogFragment(){

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val builder = AlertDialog.Builder(activity!!)
        val dialog = activity?.let {
            val view = it.layoutInflater.inflate(R.layout.fragment_dialog_type05,null)
            setRadioItems(view)
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

    private fun setRadioItems(v: View) {
        val radioGroup: RadioGroup = v.findViewById(R.id.selection_action_radio_group)
        val itemList: List<String> = listOf("item1", "item2", "item3", "item4", "item5", "item6", "item7")
        itemList.forEachIndexed { index, item ->
            val radioButton = RadioButton(context)
            radioButton.text = item
            radioButton.buttonTintList = ContextCompat.getColorStateList(activity!!, R.color.colorPrimary)
            radioButton.setPadding(
                activity?.resources?.getDimensionPixelSize(R.dimen.dialog_type05_padding_start)!!.toInt(),
                activity?.resources?.getDimensionPixelSize(R.dimen.dialog_type05_padding_row)!!.toInt(),
                0,
                activity?.resources?.getDimensionPixelSize(R.dimen.dialog_type05_padding_row)!!.toInt())
            radioGroup.addView(radioButton)
            if(index == 0){
                radioButton.isChecked = true
            }
        }
    }

}