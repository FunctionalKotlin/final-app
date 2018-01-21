// Copyright © FunctionalKotlin.com 2018. All rights reserved.

package com.functionalkotlin.bandhookkotlin.ui.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import java.util.LinkedHashMap

class ArtistDetailPagerAdapter(fragmentManager: FragmentManager) :
    FragmentPagerAdapter(fragmentManager) {
    private val fragments = LinkedHashMap<Fragment, String>()

    override fun getItem(position: Int): Fragment? = fragments.keys.elementAt(position)

    override fun getCount(): Int = fragments.keys.size

    fun addFragment(fragment: Fragment, title: String) {
        fragments[fragment] = title
    }

    override fun getPageTitle(position: Int): CharSequence = fragments.values.elementAt(position)
}
