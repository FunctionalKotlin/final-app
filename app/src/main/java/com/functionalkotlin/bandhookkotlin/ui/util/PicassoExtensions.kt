// Copyright © FunctionalHub.com 2018. All rights reserved.

package com.functionalkotlin.bandhookkotlin.ui.util

import android.widget.ImageView
import com.squareup.picasso.Callback
import com.squareup.picasso.RequestCreator

fun RequestCreator.into(target: ImageView, callback: () -> Unit) {
    into(target, object : Callback.EmptyCallback() {
        override fun onSuccess() {
            callback()
        }
    })
}
