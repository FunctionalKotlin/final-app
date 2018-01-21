// Copyright © FunctionalKotlin.com 2018. All rights reserved.

package com.functionalkotlin.bandhookkotlin.domain.interactor

import com.functionalkotlin.bandhookkotlin.domain.entity.Album
import com.functionalkotlin.bandhookkotlin.domain.entity.Artist
import com.functionalkotlin.bandhookkotlin.domain.repository.AlbumRepository
import com.functionalkotlin.bandhookkotlin.functional.Success
import com.functionalkotlin.bandhookkotlin.functional.isSuccess
import com.functionalkotlin.bandhookkotlin.functional.result
import com.functionalkotlin.bandhookkotlin.functional.runSync
import io.kotlintest.matchers.shouldBe
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

        `when`(albumRepository.getTopAlbums(artistId)).thenReturn(listOf(album).result())

        getTopAlbumsInteractor = GetTopAlbumsInteractor(albumRepository)
    }

    @Test
    fun testInvoke_withArtistId() {
        val result = getTopAlbumsInteractor.getTopAlbums(artistId).runSync()

        result.isSuccess() shouldBe true
        (result as Success).value[0] shouldBe album
    }
}
