// Copyright © FunctionalHub.com 2018. All rights reserved.

package com.functionalkotlin.bandhookkotlin.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.widget.TextView
import com.functionalkotlin.bandhookkotlin.ui.activity.ViewAnkoComponent
import com.functionalkotlin.bandhookkotlin.ui.entity.TrackDetail
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.applyRecursively
import org.jetbrains.anko.dip
import org.jetbrains.anko.horizontalPadding
import org.jetbrains.anko.linearLayout
import org.jetbrains.anko.matchParent
import org.jetbrains.anko.padding
import org.jetbrains.anko.textView

class TracksAdapter : BaseAdapter<TrackDetail, TracksAdapter.Component>() {

    private val timeStampPattern = "%d:%02d"
    private val timeSystemBaseNumber = 60

    override val bind: Component.(item: TrackDetail) -> Unit = { item ->
        number.text = item.number.toString()
        name.text = item.name
        length.text = secondsToTrackDurationString(item)
    }

    private fun secondsToTrackDurationString(item: TrackDetail): String {
        val fullMinutes = item.duration / timeSystemBaseNumber
        val restSeconds = item.duration % timeSystemBaseNumber
        return String.format(timeStampPattern, fullMinutes, restSeconds)
    }

    override fun onCreateComponent(parent: RecyclerView) = Component(parent)

    class Component(override val view: RecyclerView) : ViewAnkoComponent<RecyclerView> {

        lateinit var number: TextView
        lateinit var name: TextView
        lateinit var length: TextView

        override fun createView(ui: AnkoContext<RecyclerView>) = with(ui) {

            linearLayout {

                lparams(width = matchParent)

                padding = dip(16)
                weightSum = 1f

                number = textView {
                    minEms = 2
                    gravity = Gravity.END
                }

                name = textView {
                    horizontalPadding = dip(16)
                }.lparams(width = 0) {
                    weight = 1f
                }

                length = textView()

            }.applyRecursively { view ->
                when (view) {
                    is TextView -> view.textSize = 16f
                }
            }

        }
    }
}
