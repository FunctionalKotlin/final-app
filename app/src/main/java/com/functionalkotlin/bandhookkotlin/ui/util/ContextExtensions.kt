// Copyright © FunctionalHub.com 2018. All rights reserved.

package com.functionalkotlin.bandhookkotlin.ui.util

import android.app.Activity
import android.content.Intent
import android.support.v4.app.ActivityCompat
import android.support.v4.app.ActivityOptionsCompat
import android.view.View

inline fun <reified T : Activity> Activity.navigate(
    id: String, sharedView: View? = null, transitionName: String? = null) {

    val intent = Intent(this, T::class.java)
    intent.putExtra("id", id)

    var options: ActivityOptionsCompat? = null

    if (sharedView != null && transitionName != null) {
        options = ActivityOptionsCompat.makeSceneTransitionAnimation(
            this, sharedView, transitionName)
    }

    ActivityCompat.startActivity(this, intent, options?.toBundle())
}

fun Activity.getNavigationId(): String {
    val intent = intent
    return intent.getStringExtra("id")
}

