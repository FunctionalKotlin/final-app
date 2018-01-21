// Copyright © FunctionalKotlin.com 2018. All rights reserved.

package com.functionalkotlin.bandhookkotlin.ui.screens

import android.view.View
import com.functionalkotlin.bandhookkotlin.R
import com.functionalkotlin.bandhookkotlin.ui.custom.AutofitRecyclerView
import com.functionalkotlin.bandhookkotlin.ui.custom.PaddingItemDecoration
import org.jetbrains.anko.dimen
import org.jetbrains.anko.dip
import org.jetbrains.anko.horizontalPadding
import org.jetbrains.anko.verticalPadding

fun AutofitRecyclerView.style() {
    clipToPadding = false
    columnWidth = dimen(R.dimen.column_width)
    scrollBarStyle = View.SCROLLBARS_OUTSIDE_OVERLAY
    horizontalPadding = dimen(R.dimen.recycler_spacing)
    verticalPadding = dip(2)
    addItemDecoration(PaddingItemDecoration(dip(2)))
}
