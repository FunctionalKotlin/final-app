// Copyright © FunctionalKotlin.com 2018. All rights reserved.

package com.functionalkotlin.bandhookkotlin.data.mapper.image

import com.functionalkotlin.bandhookkotlin.data.lastfm.model.LastFmImage
import com.functionalkotlin.bandhookkotlin.data.lastfm.model.LastFmImageType
import io.kotlintest.matchers.shouldBe
import io.kotlintest.specs.StringSpec

class ImageMapperTest : StringSpec() {
    init {
        val megaImage = LastFmImage("mega", LastFmImageType.MEGA.type)
        val largeImage = LastFmImage("large", LastFmImageType.LARGE.type)
        val smallImage = LastFmImage("small", LastFmImageType.SMALL.type)

        val imagesWithMegaImage = listOf(smallImage, megaImage, largeImage)
        val imagesWithoutMegaImage = listOf(smallImage, largeImage)

        "getMainImageUrl from list with mega image returns mega image url" {
            getMainImageUrl(imagesWithMegaImage) shouldBe "mega"
        }

        "getMainImageUrl from list without mega image returns last image url" {
            getMainImageUrl(imagesWithoutMegaImage) shouldBe "large"
        }

        "getMainImageUrl from null list returns null" {
            getMainImageUrl(null) shouldBe null
        }

        "getMainImageUrl from empty list returns null" {
            getMainImageUrl(emptyList()) shouldBe null
        }
    }
}
