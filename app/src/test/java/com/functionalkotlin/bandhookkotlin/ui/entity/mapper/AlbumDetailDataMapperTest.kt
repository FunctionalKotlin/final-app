// Copyright © FunctionalKotlin.com 2018. All rights reserved.

package com.functionalkotlin.bandhookkotlin.ui.entity.mapper

import com.functionalkotlin.bandhookkotlin.domain.entity.Album
import com.functionalkotlin.bandhookkotlin.domain.entity.Artist
import com.functionalkotlin.bandhookkotlin.ui.entity.mapper.album.detail.transform
import io.kotlintest.matchers.shouldBe
import io.kotlintest.matchers.shouldNotBe
import io.kotlintest.specs.StringSpec

class AlbumDetailDataMapperTest : StringSpec() {

    init {
        val album = Album("id", "name", Artist("artist id", "artist name"), "url", emptyList())

        "transform returns valid album" {
            val albumDetail = transform(album)

            albumDetail shouldNotBe null

            albumDetail?.run {
                id shouldBe album.id
                name shouldBe album.name
                url shouldBe  album.url
                tracks shouldBe album.tracks
            }
        }

        "transform null returns null" {
            transform(null) shouldBe null
        }
    }

}
