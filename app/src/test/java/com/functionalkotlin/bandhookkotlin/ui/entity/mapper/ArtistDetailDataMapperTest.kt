// Copyright © FunctionalKotlin.com 2018. All rights reserved.

package com.functionalkotlin.bandhookkotlin.ui.entity.mapper

import com.functionalkotlin.bandhookkotlin.domain.entity.Album
import com.functionalkotlin.bandhookkotlin.domain.entity.Artist
import com.functionalkotlin.bandhookkotlin.ui.entity.mapper.artist.detail.transform
import com.functionalkotlin.bandhookkotlin.ui.entity.mapper.image.title.transformAlbums
import io.kotlintest.matchers.shouldBe
import io.kotlintest.specs.StringSpec

class ArtistDetailDataMapperTest : StringSpec() {

    init {
        val artist = Artist("id", "name", "url", null, listOf(Album("album id", "album name")))

        "transform returns valid album" {
            val artistDetail = transform(artist)

            artistDetail.run {
                id shouldBe artist.id
                name shouldBe artist.name
                url shouldBe artist.url
                albums shouldBe artist.albums?.let(::transformAlbums)
            }
        }
    }
}
