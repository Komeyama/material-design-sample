package com.komeyama.sample.design.material.ui.backdrop

import android.app.Activity
import android.content.res.ColorStateList
import android.graphics.Rect
import android.util.DisplayMetrics
import android.util.TypedValue
import android.view.View
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import android.widget.FrameLayout
import android.widget.ImageView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.shape.CornerFamily
import com.google.android.material.shape.MaterialShapeDrawable
import com.google.android.material.shape.ShapeAppearanceModel
import timber.log.Timber

fun View.createTopSheetMaterialShape(activity: Activity, color: Int) {
    val shapeAppearanceModel = ShapeAppearanceModel.Builder().setTopLeftCorner(
        CornerFamily.ROUNDED,
        resources.getDimension(R.dimen.backdrop_sheet_corner_radius)
    ).build()

    val materialShapeDrawable = MaterialShapeDrawable.createWithElevationOverlay(
        activity,
        resources.getDimension(R.dimen.bottom_sheet_elevation)
    ).apply {
        setShapeAppearanceModel(shapeAppearanceModel)
    }
    materialShapeDrawable.fillColor = ColorStateList.valueOf(activity.getColor(color))
    this.background = materialShapeDrawable
}

fun View.setMaterialHeight(height: Int) {
    this.layoutParams.height = height
}

fun View.startRotateAnimation() {
    Timber.d("start animation")
    val rotateAnimation = RotateAnimation(
        0f, 180f,
        Animation.RELATIVE_TO_SELF, 0.5f,
        Animation.RELATIVE_TO_SELF, 0.5f)
    rotateAnimation.duration = 250
    rotateAnimation.repeatCount = 0
    rotateAnimation.fillAfter = true
    this.startAnimation(rotateAnimation)
}

fun View.changeImageResource(resource: Int) {
    (this as ImageView).setImageResource(resource)
}

fun BottomSheetBehavior<FrameLayout>.changePeekHeight(height: Int) {
    this.state = BottomSheetBehavior.STATE_EXPANDED
    this.peekHeight = height
    this.state = BottomSheetBehavior.STATE_COLLAPSED
}

fun getDefaultDisplayHeight(activity: Activity): Int {
    val dm = DisplayMetrics()
    activity.windowManager?.defaultDisplay?.getMetrics(dm)
    return dm.heightPixels
}

fun getActionBarHeight(activity: Activity): Int {
    val tv = TypedValue()
    return if (activity.theme!!.resolveAttribute(android.R.attr.actionBarSize, tv, true))  TypedValue.complexToDimensionPixelSize(tv.data, activity.resources.displayMetrics) else 0
}

fun getStatusBarHeight(activity: Activity): Int {
    val rect = Rect()
    activity.window.decorView.getWindowVisibleDisplayFrame(rect)
    return rect.top
}
