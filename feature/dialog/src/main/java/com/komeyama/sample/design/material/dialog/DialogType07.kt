package com.komeyama.sample.design.material.dialog

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.DialogFragment
import kotlinx.android.synthetic.main.fragment_dialog_type07.*

class DialogType07 : DialogFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.AppTheme_FullScreenDialog);
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        isCancelable = false
        return inflater.inflate(R.layout.fragment_dialog_type07, container, false)
    }

    override fun onStart() {
        super.onStart()
        val dialog: Dialog? = dialog
        if (dialog != null) {
            val width = ViewGroup.LayoutParams.MATCH_PARENT
            val height = ViewGroup.LayoutParams.MATCH_PARENT
            dialog.window!!.setLayout(width, height)
            dialog.window!!.setWindowAnimations(R.style.AppTheme_FullScreenDialogSlide)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setBackButtonAnimation()
        fullscreen_dialog_toolbar.inflateMenu(R.menu.fullscreen_dialog_menu)
        fullscreen_dialog_toolbar.setOnMenuItemClickListener {item: MenuItem? ->
            when (item!!.itemId) {
                R.id.fullscreen_action_save -> {
                    dismiss()
                }
            }
            true
        }
        fullscreen_dialog_back_button.setOnClickListener { dismiss() }
    }

    private fun setBackButtonAnimation() {
        val dialogBackButtonRotationAnimation = AnimationUtils.loadAnimation(activity, R.anim.dialog_back_button_rotation)
        fullscreen_dialog_back_button.startAnimation(dialogBackButtonRotationAnimation)
    }

}