// Copyright © FunctionalKotlin.com 2018. All rights reserved.

package com.functionalkotlin.bandhookkotlin.ui.presenter

import com.functionalkotlin.bandhookkotlin.domain.entity.Artist
import com.functionalkotlin.bandhookkotlin.domain.interactor.GetArtistDetailInteractor
import com.functionalkotlin.bandhookkotlin.domain.interactor.GetTopAlbumsInteractor
import com.functionalkotlin.bandhookkotlin.domain.interactor.base.Bus
import com.functionalkotlin.bandhookkotlin.domain.interactor.base.InteractorExecutor
import com.functionalkotlin.bandhookkotlin.domain.interactor.event.ArtistDetailEvent
import com.functionalkotlin.bandhookkotlin.domain.repository.AlbumRepository
import com.functionalkotlin.bandhookkotlin.domain.repository.ArtistRepository
import com.functionalkotlin.bandhookkotlin.ui.entity.ImageTitle
import com.functionalkotlin.bandhookkotlin.ui.entity.mapper.ArtistDetailDataMapper
import com.functionalkotlin.bandhookkotlin.ui.entity.mapper.ImageTitleDataMapper
import com.functionalkotlin.bandhookkotlin.ui.view.ArtistView
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ArtistPresenterTest {

    @Mock
    lateinit var artistView: ArtistView
    @Mock
    lateinit var bus: Bus
    @Mock
    lateinit var artistRepository: ArtistRepository
    @Mock
    lateinit var albumRepository: AlbumRepository
    @Mock
    lateinit var interactorExecutor: InteractorExecutor

    lateinit var artistDetailInteractor: GetArtistDetailInteractor
    lateinit var topAlbumsInteractor: GetTopAlbumsInteractor
    lateinit var artistDetailMapper: ArtistDetailDataMapper
    lateinit var albumsMapper: ImageTitleDataMapper

    lateinit var artistPresenter: ArtistPresenter

    private val artistId = "artist id"

    @Before
    fun setUp() {
        artistDetailInteractor = GetArtistDetailInteractor(artistRepository)
        topAlbumsInteractor = GetTopAlbumsInteractor(albumRepository)
        artistDetailMapper = ArtistDetailDataMapper()
        albumsMapper = ImageTitleDataMapper()


        artistPresenter = ArtistPresenter(artistView, bus, artistDetailInteractor, topAlbumsInteractor,
            interactorExecutor, artistDetailMapper, albumsMapper)
    }

    @Test
    fun testOnArtistDetailEvent() {
        // Given
        val artistDetailEvent = ArtistDetailEvent(Artist("artist id", "artist name"))
        val desiredArtist = artistDetailMapper.transform(artistDetailEvent.artist)

        // When
        artistPresenter.onEvent(artistDetailEvent)

        // Then
        verify(artistView).showArtist(desiredArtist)
    }

    @Test
    fun testOnPause() {
        // When
        artistPresenter.onPause()

        // Then
        verify(bus).unregister(artistPresenter)
    }

    @Test
    fun testOnResume() {
        // When
        artistPresenter.onResume()

        // Then
        verify(bus).register(artistPresenter)
    }

    @Test
    fun testOnAlbumClicked() {
        // Given
        val imageId = "image id"

        // When
        artistPresenter.onAlbumClicked(ImageTitle(imageId, "image name", null))

        // Then
        verify(artistView).navigateToAlbum(imageId)
    }
}
