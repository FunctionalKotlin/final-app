// Copyright © FunctionalHub.com 2018. All rights reserved.

package com.functionalkotlin.bandhookkotlin.ui.custom

import android.content.Context
import android.util.AttributeSet
import android.view.ViewManager
import android.widget.ImageView
import org.jetbrains.anko.custom.ankoView

class SquareImageView : ImageView {

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context, attrs, defStyleAttr)

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val width = measuredWidth
        setMeasuredDimension(width, width)
    }
}

inline fun ViewManager.squareImageView(theme: Int = 0, init: SquareImageView.() -> Unit) =
    ankoView(::SquareImageView, theme, init)

