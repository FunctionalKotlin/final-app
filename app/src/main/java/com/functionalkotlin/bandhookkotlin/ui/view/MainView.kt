// Copyright © FunctionalHub.com 2018. All rights reserved.

package com.functionalkotlin.bandhookkotlin.ui.view

import com.functionalkotlin.bandhookkotlin.ui.entity.ImageTitle

interface MainView : PresentationView {
    fun showArtists(artists: List<ImageTitle>)
    fun navigateToDetail(id: String)
}
