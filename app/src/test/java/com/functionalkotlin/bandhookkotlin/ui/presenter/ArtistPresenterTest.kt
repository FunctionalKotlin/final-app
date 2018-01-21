// Copyright © FunctionalKotlin.com 2018. All rights reserved.

package com.functionalkotlin.bandhookkotlin.ui.presenter

import com.functionalkotlin.bandhookkotlin.domain.entity.Album
import com.functionalkotlin.bandhookkotlin.domain.entity.Artist
import com.functionalkotlin.bandhookkotlin.domain.entity.ArtistNotFound
import com.functionalkotlin.bandhookkotlin.domain.entity.TopAlbumsNotFound
import com.functionalkotlin.bandhookkotlin.domain.interactor.GetArtistDetailInteractor
import com.functionalkotlin.bandhookkotlin.domain.interactor.GetTopAlbumsInteractor
import com.functionalkotlin.bandhookkotlin.functional.asError
import com.functionalkotlin.bandhookkotlin.functional.result
import com.functionalkotlin.bandhookkotlin.ui.entity.ImageTitle
import com.functionalkotlin.bandhookkotlin.ui.entity.mapper.artist.detail.transform
import com.functionalkotlin.bandhookkotlin.ui.entity.mapper.image.title.transformAlbums
import com.functionalkotlin.bandhookkotlin.ui.view.ArtistView
import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import io.kotlintest.specs.StringSpec
import kotlinx.coroutines.experimental.runBlocking
import org.mockito.Mockito.verify

class ArtistPresenterTest : StringSpec() {

    init {
        val albums = listOf(Album("id", "name", null, null, emptyList()))
        val artist = Artist("id", "name", null, null, emptyList())

        val artistView = mock<ArtistView>()
        val artistDetailInteractor = mock<GetArtistDetailInteractor> {
            on { getArtist(ARTIST_ID) } doReturn artist.result()
            on { getArtist("") } doReturn ArtistNotFound("").asError()
        }
        val topAlbumsInteractor = mock<GetTopAlbumsInteractor> {
            on { getTopAlbums(ARTIST_ID) } doReturn albums.result()
            on { getTopAlbums("") } doReturn TopAlbumsNotFound("").asError()
        }

        val artistPresenter = ArtistPresenter(
            artistView, artistDetailInteractor, topAlbumsInteractor)

        "onAlbumClicked should rely on view" {
            artistPresenter.onAlbumClicked(ImageTitle(IMAGE_ID, "name", null))

            verify(artistView).navigateToAlbum(IMAGE_ID)
        }

        "init with valid artist ID calls interactor and view" {
            runBlocking { artistPresenter.init(ARTIST_ID) }

            verify(artistDetailInteractor).getArtist(ARTIST_ID)
            verify(artistView).showArtist(transform(artist))
            verify(topAlbumsInteractor).getTopAlbums(ARTIST_ID)
            verify(artistView).showAlbums(transformAlbums(albums))
        }

        "init with non valid artist ID calls interactor and view" {
            runBlocking { artistPresenter.init("") }

            verify(artistDetailInteractor).getArtist("")
            verify(artistView).showArtistNotFound(ArtistNotFound(""))
            verify(topAlbumsInteractor).getTopAlbums("")
            verify(artistView).showAlbumsNotFound(TopAlbumsNotFound(""))
        }
    }


}
