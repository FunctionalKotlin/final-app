// Copyright © FunctionalKotlin.com 2018. All rights reserved.

package com.functionalkotlin.bandhookkotlin.ui.view

import com.functionalkotlin.bandhookkotlin.ui.entity.ArtistDetail
import com.functionalkotlin.bandhookkotlin.ui.entity.ImageTitle

interface ArtistView : PresentationView {
    fun showArtist(artistDetail: ArtistDetail)

    fun showAlbums(albums: List<ImageTitle>)

    fun navigateToAlbum(albumId: String)
}
