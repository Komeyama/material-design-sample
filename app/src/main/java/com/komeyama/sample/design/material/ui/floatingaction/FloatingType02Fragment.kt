package com.komeyama.sample.design.material.ui.floatingaction

import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.komeyama.sample.design.material.R
import kotlinx.android.synthetic.main.fragment_floating_type02.*

class FloatingType02Fragment: Fragment(R.layout.fragment_floating_type02) {

    private var isOpenOfFab = false
    private lateinit var fabOpenRotationAnimation: Animation
    private lateinit var fabCloseRotationAnimation: Animation
    private lateinit var fabOpenAnimation: Animation
    private lateinit var fabCloseAnimation: Animation
    private lateinit var fabList:List<FloatingActionButton>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fabOpenRotationAnimation = AnimationUtils.loadAnimation(activity, R.anim.fab_open_rotation)
        fabCloseRotationAnimation = AnimationUtils.loadAnimation(activity, R.anim.fab_close_rotation)
        fabOpenAnimation = AnimationUtils.loadAnimation(activity, R.anim.fab_open)
        fabCloseAnimation = AnimationUtils.loadAnimation(activity, R.anim.fab_close)
        fabList = listOf(floating_type02_01,floating_type02_02,floating_type02_03)

        floating_type02_00.setOnClickListener {
            isOpenOfFab = if (!isOpenOfFab) {
                setMainFabAnimation(floating_type02_00,true)
                setVisibilityOfMiniFab(fabList,true)
                true
            } else {
                setMainFabAnimation(floating_type02_00,false)
                setVisibilityOfMiniFab(fabList,false)
                false
            }
        }
    }

    private fun setMainFabAnimation(fab: FloatingActionButton, isOpen: Boolean) {
        if (isOpen) {
            fab.setImageDrawable(ResourcesCompat.getDrawable(resources,R.drawable.ic_clear_gray_24dp,null))
            fab.startAnimation(fabOpenRotationAnimation)
        } else {
            fab.setImageDrawable(ResourcesCompat.getDrawable(resources, R.drawable.ic_share_gray_24dp,null))
            fab.startAnimation(fabCloseRotationAnimation)
        }
    }

    private fun setVisibilityOfMiniFab(fab: List<FloatingActionButton>, isOpen: Boolean) {
        if (isOpen) {
            fab.forEach{
                it.startAnimation(fabOpenAnimation)
                it.visibility = View.VISIBLE
            }
        } else {
            fab.forEach{
                it.startAnimation(fabCloseAnimation)
                it.visibility = View.INVISIBLE
            }
        }
    }
}