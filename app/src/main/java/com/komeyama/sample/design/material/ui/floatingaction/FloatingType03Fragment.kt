package com.komeyama.sample.design.material.ui.floatingaction

import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.komeyama.sample.design.material.R
import kotlinx.android.synthetic.main.fragment_floating_type03.*

class FloatingType03Fragment: Fragment(R.layout.fragment_floating_type03) {

    private var isOpenOfFab = false
    private lateinit var fabOpenRotationAnimation: Animation
    private lateinit var fabCloseRotationAnimation: Animation
    private lateinit var fabList: List<FloatingActionButton>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fabOpenRotationAnimation = AnimationUtils.loadAnimation(activity, R.anim.fab_open_rotation)
        fabCloseRotationAnimation = AnimationUtils.loadAnimation(activity, R.anim.fab_close_rotation)
        fabList = listOf(floating_type03_01,floating_type03_02,floating_type03_03)

        floating_type03_00.setOnClickListener {
            isOpenOfFab = if (!isOpenOfFab) {
                setMainFabAnimation(floating_type03_00,true)
                setVisibilityOfMiniFab(fabList,true)
                true
            } else {
                setMainFabAnimation(floating_type03_00,false)
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
            fab.forEachIndexed{ index, it ->
                val animation = AnimationUtils.loadAnimation(activity, R.anim.fab_open)
                animation.duration = 200L + 70L * index.toLong()
                it.startAnimation(animation)
                it.visibility = View.VISIBLE
            }
        } else {
            fab.reversed().forEachIndexed{ index, it ->
                val animation = AnimationUtils.loadAnimation(activity, R.anim.fab_close)
                animation.duration = 200L + 70L * index.toLong()
                it.startAnimation(animation)
                it.visibility = View.INVISIBLE
            }
        }
    }
}