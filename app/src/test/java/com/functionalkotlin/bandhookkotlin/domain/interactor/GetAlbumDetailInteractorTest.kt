// Copyright © FunctionalKotlin.com 2018. All rights reserved.

package com.functionalkotlin.bandhookkotlin.domain.interactor

import com.functionalkotlin.bandhookkotlin.domain.entity.Album
import com.functionalkotlin.bandhookkotlin.domain.entity.Artist
import com.functionalkotlin.bandhookkotlin.domain.repository.AlbumRepository
import com.functionalkotlin.bandhookkotlin.functional.result
import com.functionalkotlin.bandhookkotlin.util.asSuccess
import com.nhaarman.mockito_kotlin.mock
import io.kotlintest.matchers.shouldBe
import io.kotlintest.specs.StringSpec

class GetAlbumDetailInteractorTest : StringSpec() {

    init {
        val albumRepository = mock<AlbumRepository> {
            val result = Album(
                ALBUM_ID, "name", Artist("id", "name", null, null, null), "url", emptyList()
            ).result()

            on { getAlbum(ALBUM_ID) }.thenReturn(result)
        }

        val interactor = GetAlbumDetailInteractor(albumRepository)

        "getAlbum should return valid album" {
            val asyncResult = interactor.getAlbum(ALBUM_ID)

            asyncResult.asSuccess { id shouldBe ALBUM_ID }
        }
    }

}
