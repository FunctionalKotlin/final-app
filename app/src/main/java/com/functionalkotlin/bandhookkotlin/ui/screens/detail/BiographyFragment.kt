// Copyright © FunctionalKotlin.com 2018. All rights reserved.

package com.functionalkotlin.bandhookkotlin.ui.screens.detail

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.functionalkotlin.bandhookkotlin.R
import com.functionalkotlin.bandhookkotlin.ui.activity.ViewAnkoComponent
import com.functionalkotlin.bandhookkotlin.ui.util.fromHtml
import com.functionalkotlin.bandhookkotlin.ui.util.setTextAppearanceC
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.backgroundResource
import org.jetbrains.anko.dip
import org.jetbrains.anko.padding
import org.jetbrains.anko.support.v4.nestedScrollView
import org.jetbrains.anko.textView

class BiographyFragment : Fragment() {

    private var component: Component? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        component = container?.let { Component(container) }
        return component?.inflate()
    }

    fun setBiographyText(biographyText: String?) {
        component?.textView?.text = biographyText?.fromHtml()
    }

    fun getBiographyText(): String? = component?.textView?.text?.toString()

    private class Component(override val view: ViewGroup) : ViewAnkoComponent<ViewGroup> {

        lateinit var textView: TextView

        override fun createView(ui: AnkoContext<ViewGroup>): View = with(ui) {
            nestedScrollView {
                textView = textView {
                    backgroundResource = android.R.color.white
                    padding = dip(16)
                    setTextAppearanceC(R.style.TextAppearance_AppCompat_Body1)
                }
            }
        }
    }
}
