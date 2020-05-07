package com.komeyama.sample.design.material.dialog

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.RecyclerView
import com.komeyama.sample.design.material.dialog.databinding.ListItemDialogBinding
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.databinding.BindableItem
import com.xwray.groupie.databinding.ViewHolder

class DialogType06 : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val builder = AlertDialog.Builder(activity!!)
        val dialog = activity?.let {

            val view = it.layoutInflater.inflate(R.layout.fragment_dialog_type06, null)
            val groupAdapter = GroupAdapter<ViewHolder<*>>()
            val recyclerView: RecyclerView = view!!.findViewById(R.id.dialog_recycler_view)

            recyclerView.adapter = groupAdapter
            val items = listOf(
                DialogItem(R.drawable.ic_person_black_24dp, "item01@gmail.com"),
                DialogItem(R.drawable.ic_person_black_24dp, "item02@gmail.com"),
                DialogItem(R.drawable.ic_add_gray_24dp, "item03@gmail.com")
            )
            groupAdapter.update(items)

            builder
                .setTitle(resources.getString(R.string.dialog_title))
                .setView(view)
                .create()
        }
        dialog?.show()

        return dialog as Dialog
    }
}

class DialogItem(private val thumbnail: Int, private val email: String) : BindableItem<ListItemDialogBinding>() {
    override fun getLayout() = R.layout.list_item_dialog

    override fun bind(viewBinding: ListItemDialogBinding, position: Int) {
        viewBinding.dialogItemHeaderThumbnail.setImageResource(thumbnail)
        viewBinding.dialogAccountMail.text = email
    }
}