// Copyright © FunctionalKotlin.com 2018. All rights reserved.

package com.functionalkotlin.bandhookkotlin.domain.interactor

import com.functionalkotlin.bandhookkotlin.domain.entity.Album
import com.functionalkotlin.bandhookkotlin.domain.entity.AlbumNotFound
import com.functionalkotlin.bandhookkotlin.domain.entity.Artist
import com.functionalkotlin.bandhookkotlin.domain.interactor.event.AlbumEvent
import com.functionalkotlin.bandhookkotlin.domain.repository.AlbumRepository
import com.functionalkotlin.bandhookkotlin.functional.*
import io.kotlintest.matchers.shouldBe
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetAlbumDetailInteractorTest {

    @Mock
    lateinit var albumRepository: AlbumRepository

    lateinit var getAlbumDetailInteractor: GetAlbumDetailInteractor

    private val albumId = "album id"

    @Before
    fun setUp() {
        `when`(albumRepository.getAlbum(albumId)).thenReturn(Album("album id", "album name",
                Artist("artist id", "artist name", null, null, null), "album url", emptyList()).result())

        getAlbumDetailInteractor = GetAlbumDetailInteractor(albumRepository)
    }

    @Test
    fun testInvoke_withId() {
        val result = getAlbumDetailInteractor.getAlbum(albumId).runSync()

        result.isSuccess() shouldBe true
        (result as Success).value.id shouldBe albumId
    }
}
