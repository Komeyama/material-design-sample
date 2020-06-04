package com.komeyama.sample.design.material.ui.bottomnavigation.utils

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Canvas
import android.graphics.Path
import android.graphics.RectF
import android.util.AttributeSet
import android.widget.FrameLayout
import com.komeyama.sample.design.material.ui.bottomnavigation.R

class RoundClipFrameLayout(context: Context, attrs: AttributeSet?, defStyleAttr: Int) :
    FrameLayout(context, attrs, defStyleAttr) {
    private val mPath: Path = Path()
    private val mRect = RectF()
    private var mCornerRadius: Int

    constructor(context: Context) : this(context, null) {}
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0) {}

    fun setCornerRadius(radiusPx: Int) {
        if (mCornerRadius != radiusPx) {
            mCornerRadius = radiusPx
            rebuildPath()
            invalidate()
        }
    }

    private fun rebuildPath() {
        mPath.reset()
        mPath.addRoundRect(mRect, mCornerRadius.toFloat(), mCornerRadius.toFloat(), Path.Direction.CW)
        mPath.close()
    }

    override fun onSizeChanged(
        width: Int,
        height: Int,
        oldWidth: Int,
        oldHeight: Int
    ) {
        super.onSizeChanged(width, height, oldWidth, oldHeight)
        mRect[0f, 0f, width.toFloat()] = height.toFloat()
        rebuildPath()
    }

    override fun dispatchDraw(canvas: Canvas) {
        val save: Int = canvas.save()
        canvas.clipPath(mPath)
        super.dispatchDraw(canvas)
        canvas.restoreToCount(save)
    }

    init {
        val ta: TypedArray = context.obtainStyledAttributes(attrs, R.styleable.RoundClipFrameLayout, defStyleAttr, 0)
        mCornerRadius = ta.getDimensionPixelSize(R.styleable.RoundClipFrameLayout_cornerRadius, 0)
        ta.recycle()
    }
}