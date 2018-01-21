// Copyright © FunctionalKotlin.com 2018. All rights reserved.

package com.functionalkotlin.bandhookkotlin.ui.entity.mapper

import com.functionalkotlin.bandhookkotlin.domain.entity.Album
import com.functionalkotlin.bandhookkotlin.domain.entity.Artist
import com.functionalkotlin.bandhookkotlin.ui.entity.mapper.image.title.transformAlbums
import com.functionalkotlin.bandhookkotlin.ui.entity.mapper.image.title.transformArtists
import io.kotlintest.matchers.shouldBe
import io.kotlintest.specs.StringSpec

class ImageTitleDataMapperTest : StringSpec() {

    init {

        "transformArtists return valid list" {
            val artist0 = Artist("artist id", "artist name", "artist url")
            val artist1 = Artist("artist id", "artist name")
            val artist2 = Artist("artist id", "artist name", "")

            val imageTitles = transformArtists(listOf(artist0, artist1, artist2))

            imageTitles[0].run {
                id shouldBe artist0.id
                name shouldBe artist0.name
                url shouldBe artist0.url
            }

            imageTitles[1].run {
                id shouldBe artist1.id
                name shouldBe artist1.name
                url shouldBe artist1.url
            }

            imageTitles[2].run {
                id shouldBe artist2.id
                name shouldBe artist2.name
                url shouldBe null
            }
        }

        "transformAlbums return valid list" {
            val album0 = Album("album id", "album name", null, "album url", emptyList())
            val album1 = Album("album id", "album name", null, null, emptyList())
            val album2 = Album("album id", "album name", null, "", emptyList())

            val imageTitles = transformAlbums(listOf(album0, album1, album2))

            imageTitles[0].run {
                id shouldBe album0.id
                name shouldBe album0.name
                url shouldBe album0.url
            }

            imageTitles[1].run {
                id shouldBe album1.id
                name shouldBe album1.name
                url shouldBe album1.url
            }

            imageTitles[2].run {
                id shouldBe album2.id
                name shouldBe album2.name
                url shouldBe null
            }
        }
    }
}
