// Copyright © FunctionalHub.com 2018. All rights reserved.

package com.functionalkotlin.bandhookkotlin.ui.view

import com.functionalkotlin.bandhookkotlin.domain.entity.ArtistNotFound
import com.functionalkotlin.bandhookkotlin.domain.entity.TopAlbumsNotFound
import com.functionalkotlin.bandhookkotlin.ui.entity.ArtistDetail
import com.functionalkotlin.bandhookkotlin.ui.entity.ImageTitle

interface ArtistView : PresentationView {
    fun showArtist(artistDetail: ArtistDetail)
    fun showAlbums(albums: List<ImageTitle>)
    fun showAlbumsNotFound(e: TopAlbumsNotFound)
    fun showArtistNotFound(it: ArtistNotFound)
    fun navigateToAlbum(albumId: String)
}
