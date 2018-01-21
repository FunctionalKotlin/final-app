// Copyright © FunctionalKotlin.com 2018. All rights reserved.

package com.functionalkotlin.bandhookkotlin.ui.activity

import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import org.jetbrains.anko.AnkoComponent

@Suppress("AddVarianceModifier")
interface ActivityAnkoComponent<T : AppCompatActivity> : AnkoComponent<T> {
    val toolbar: Toolbar
}
