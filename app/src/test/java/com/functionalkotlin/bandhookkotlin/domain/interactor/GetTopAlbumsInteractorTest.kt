// Copyright © FunctionalHub.com 2018. All rights reserved.

package com.functionalkotlin.bandhookkotlin.domain.interactor

import com.functionalkotlin.bandhookkotlin.domain.entity.Album
import com.functionalkotlin.bandhookkotlin.domain.entity.Artist
import com.functionalkotlin.bandhookkotlin.domain.repository.AlbumRepository
import com.functionalkotlin.bandhookkotlin.functional.result
import com.functionalkotlin.bandhookkotlin.util.asSuccess
import com.nhaarman.mockito_kotlin.mock
import io.kotlintest.matchers.shouldBe
import io.kotlintest.specs.StringSpec

class GetTopAlbumsInteractorTest : StringSpec() {

    init {
        val album = Album(
            ALBUM_ID, "name", Artist("id", "name", null, null, null), "url", emptyList())

        val albumRepository = mock<AlbumRepository> {
            on { getTopAlbums(ALBUM_ID) }.thenReturn(listOf(album).result())
        }

        val interactor = GetTopAlbumsInteractor(albumRepository)

        "getTopAlbums should return valid list" {
            val asyncResult = interactor.getTopAlbums(ALBUM_ID)

            asyncResult.asSuccess { get(0) shouldBe album }
        }
    }
}
