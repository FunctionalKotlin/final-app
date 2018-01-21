// Copyright © FunctionalKotlin.com 2018. All rights reserved.

package com.functionalkotlin.bandhookkotlin.domain.interactor

import com.functionalkotlin.bandhookkotlin.domain.entity.Album
import com.functionalkotlin.bandhookkotlin.domain.entity.Artist
import com.functionalkotlin.bandhookkotlin.domain.interactor.event.TopAlbumsEvent
import com.functionalkotlin.bandhookkotlin.domain.repository.AlbumRepository
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetTopAlbumsInteractorTest {

    @Mock
    lateinit var albumRepository: AlbumRepository

    lateinit var album: Album

    lateinit var getTopAlbumsInteractor: GetTopAlbumsInteractor

    private val artistId = "artist id"

    @Before
    fun setUp() {
        album = Album("album id", "Album name", Artist("artist id", "artist name"), null, emptyList())

        `when`(albumRepository.getTopAlbums(artistId)).thenReturn(listOf(album))

        getTopAlbumsInteractor = GetTopAlbumsInteractor(albumRepository)
    }

    @Test
    fun testInvoke_withArtistId() {
        // Given
        getTopAlbumsInteractor.artistId = artistId

        // When
        val event = getTopAlbumsInteractor.invoke()

        // Then
        Assert.assertEquals(TopAlbumsEvent::class.java, event.javaClass)
        Assert.assertEquals(album, (event as TopAlbumsEvent).topAlbums[0])
    }

    @Test(expected = IllegalStateException::class)
    fun testInvoke_withoutData() {
        // When
        getTopAlbumsInteractor.invoke()

        // Then expected illegal state exception
    }
}
