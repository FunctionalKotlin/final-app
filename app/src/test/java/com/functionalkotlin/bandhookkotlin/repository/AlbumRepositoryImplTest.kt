// Copyright © FunctionalKotlin.com 2018. All rights reserved.

package com.functionalkotlin.bandhookkotlin.repository

import com.functionalkotlin.bandhookkotlin.domain.entity.Album
import com.functionalkotlin.bandhookkotlin.domain.entity.AlbumNotFound
import com.functionalkotlin.bandhookkotlin.domain.entity.Artist
import com.functionalkotlin.bandhookkotlin.domain.entity.TopAlbumsNotFound
import com.functionalkotlin.bandhookkotlin.functional.asError
import com.functionalkotlin.bandhookkotlin.functional.result
import com.functionalkotlin.bandhookkotlin.repository.dataset.AlbumDataSet
import com.functionalkotlin.bandhookkotlin.util.asFailure
import com.functionalkotlin.bandhookkotlin.util.asSuccess
import com.nhaarman.mockito_kotlin.mock
import io.kotlintest.matchers.shouldBe
import io.kotlintest.specs.StringSpec

class AlbumRepositoryImplTest : StringSpec() {

    init {
        val albumInBothDataSets = Album(
            ALBUM_ID_BOTH, "name", Artist(ARTIST_ID_BOTH, "name"), "url",
            emptyList())

        val albumInSecondDataSet = Album(
            ALBUM_ID_SECOND, "name", Artist(ARTIST_ID_BOTH, "name"), "url",
            emptyList())

        val albumsInBothDataSets = listOf(albumInBothDataSets)
        val albumsInSecondDataSet = listOf(albumInSecondDataSet)

        val firstAlbumDataSet = mock<AlbumDataSet> {
            on { requestTopAlbums("") }.thenReturn(TopAlbumsNotFound("").asError())
            on { requestTopAlbums(ARTIST_ID_SECOND) }.thenReturn(TopAlbumsNotFound("").asError())
            on { requestTopAlbums(ARTIST_ID_BOTH) }.thenReturn(albumsInBothDataSets.result())
            on { requestAlbum(ALBUM_ID_BOTH) }.thenReturn(albumInBothDataSets.result())
            on { requestAlbum(ALBUM_ID_SECOND) }
                .thenReturn(AlbumNotFound(ALBUM_ID_SECOND).asError())
        }

        val secondAlbumDataSet = mock<AlbumDataSet> {
            on { requestTopAlbums("") }.thenReturn(TopAlbumsNotFound("").asError())
            on { requestTopAlbums(ARTIST_ID_SECOND) }.thenReturn(albumsInSecondDataSet.result())
            on { requestAlbum(ALBUM_ID_SECOND) }.thenReturn(albumInSecondDataSet.result())
        }

        val albumRepository = AlbumRepositoryImpl(listOf(firstAlbumDataSet, secondAlbumDataSet))

        "getAlbum in both data sets returns valid album" {
            albumRepository.getAlbum(ALBUM_ID_BOTH)
                .asSuccess { shouldBe(albumInBothDataSets) }
        }

        "getAlbum in second data set returns album" {
            albumRepository.getAlbum(ALBUM_ID_SECOND)
                .asSuccess { shouldBe(albumInSecondDataSet) }
        }

        "getTopAlbums in both data sets returns valid list" {
            albumRepository.getTopAlbums(ARTIST_ID_BOTH)
                .asSuccess { shouldBe(albumsInBothDataSets) }
        }

        "getTopAlbums in second data set returns valid list" {
            albumRepository.getTopAlbums(ARTIST_ID_SECOND)
                .asSuccess { shouldBe(albumsInSecondDataSet) }
        }

        "getTopAlbums with empty string returns error" {
            albumRepository.getTopAlbums("")
                .asFailure { shouldBe(TopAlbumsNotFound("")) }
        }

    }
}
