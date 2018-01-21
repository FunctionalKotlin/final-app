// Copyright © FunctionalKotlin.com 2018. All rights reserved.

package com.functionalkotlin.bandhookkotlin.ui.presenter

import com.functionalkotlin.bandhookkotlin.domain.interactor.GetArtistDetailInteractor
import com.functionalkotlin.bandhookkotlin.domain.interactor.GetTopAlbumsInteractor
import com.functionalkotlin.bandhookkotlin.domain.repository.AlbumRepository
import com.functionalkotlin.bandhookkotlin.domain.repository.ArtistRepository
import com.functionalkotlin.bandhookkotlin.ui.entity.ImageTitle
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
    lateinit var artistRepository: ArtistRepository
    @Mock
    lateinit var albumRepository: AlbumRepository

    lateinit var artistDetailInteractor: GetArtistDetailInteractor
    lateinit var topAlbumsInteractor: GetTopAlbumsInteractor

    lateinit var artistPresenter: ArtistPresenter

    private val artistId = "artist id"

    @Before
    fun setUp() {
        artistDetailInteractor = GetArtistDetailInteractor(artistRepository)
        topAlbumsInteractor = GetTopAlbumsInteractor(albumRepository)


        artistPresenter = ArtistPresenter(artistView, artistDetailInteractor, topAlbumsInteractor)
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
