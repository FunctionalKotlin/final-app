// Copyright © FunctionalHub.com 2018. All rights reserved.

package com.functionalkotlin.bandhookkotlin.ui.screens.detail

import android.graphics.Color
import android.support.design.widget.AppBarLayout
import android.support.design.widget.AppBarLayout.LayoutParams.SCROLL_FLAG_EXIT_UNTIL_COLLAPSED
import android.support.design.widget.AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL
import android.support.design.widget.CollapsingToolbarLayout
import android.support.design.widget.CollapsingToolbarLayout.LayoutParams
import android.support.design.widget.CollapsingToolbarLayout.LayoutParams.COLLAPSE_MODE_PARALLAX
import android.support.design.widget.CollapsingToolbarLayout.LayoutParams.COLLAPSE_MODE_PIN
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.support.v7.widget.Toolbar
import android.view.Gravity
import android.view.View
import android.widget.ImageView
import com.functionalkotlin.bandhookkotlin.R
import com.functionalkotlin.bandhookkotlin.ui.activity.ActivityAnkoComponent
import com.functionalkotlin.bandhookkotlin.ui.custom.squareImageView
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.appcompat.v7.toolbar
import org.jetbrains.anko.design.collapsingToolbarLayout
import org.jetbrains.anko.design.coordinatorLayout
import org.jetbrains.anko.design.tabLayout
import org.jetbrains.anko.design.themedAppBarLayout
import org.jetbrains.anko.dip
import org.jetbrains.anko.matchParent
import org.jetbrains.anko.support.v4.viewPager
import org.jetbrains.anko.wrapContent

class ArtistLayout : ActivityAnkoComponent<ArtistActivity> {

    override lateinit var toolbar: Toolbar

    lateinit var image: ImageView
    lateinit var collapsingToolbarLayout: CollapsingToolbarLayout
    lateinit var viewPager: ViewPager
    lateinit var tabLayout: TabLayout

    override fun createView(ui: AnkoContext<ArtistActivity>) = with(ui) {

        coordinatorLayout {

            themedAppBarLayout(R.style.ThemeOverlay_AppCompat_Dark_ActionBar) {
                fitsSystemWindows = true

                collapsingToolbarLayout = collapsingToolbarLayout {
                    fitsSystemWindows = true
                    collapsedTitleGravity = Gravity.TOP
                    expandedTitleMarginBottom = dip(60)

                    image = squareImageView {
                        fitsSystemWindows = true
                    }.lparamsC(matchParent) {
                        collapseMode = COLLAPSE_MODE_PARALLAX
                    }

                    toolbar = toolbar {
                        popupTheme = R.style.ThemeOverlay_AppCompat_Light
                        titleMarginTop = dip(16)
                    }.lparamsC(width = matchParent, height = dip(88)) {
                        gravity = Gravity.TOP
                        collapseMode = COLLAPSE_MODE_PIN
                    }

                    tabLayout = tabLayout {
                        setSelectedTabIndicatorColor(Color.WHITE)
                    }.lparamsC(width = matchParent) {
                        gravity = Gravity.BOTTOM
                    }

                }.lparams(width = matchParent) {
                    scrollFlags = SCROLL_FLAG_SCROLL or SCROLL_FLAG_EXIT_UNTIL_COLLAPSED
                }

            }.lparams(width = matchParent)

            viewPager = viewPager {
                id = View.generateViewId()
            }.lparams {
                behavior = AppBarLayout.ScrollingViewBehavior()
            }
        }
    }
}

private fun <T : android.view.View> T.lparamsC(
    width: Int = wrapContent, height: Int = wrapContent, init: LayoutParams.() -> Unit = {}): T {

    val params = LayoutParams(width, height)
    params.init()
    layoutParams = params
    return this
}
