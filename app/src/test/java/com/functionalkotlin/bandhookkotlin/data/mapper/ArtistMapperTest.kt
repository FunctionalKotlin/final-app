// Copyright © FunctionalKotlin.com 2018. All rights reserved.

package com.functionalkotlin.bandhookkotlin.data.mapper

import com.functionalkotlin.bandhookkotlin.data.lastfm.model.LastFmArtist
import com.functionalkotlin.bandhookkotlin.data.lastfm.model.LastFmBio
import com.functionalkotlin.bandhookkotlin.data.lastfm.model.LastFmImage
import com.functionalkotlin.bandhookkotlin.data.lastfm.model.LastFmImageType
import io.kotlintest.matchers.haveSize
import io.kotlintest.matchers.should
import io.kotlintest.matchers.shouldBe
import io.kotlintest.matchers.shouldNotBe
import io.kotlintest.specs.StringSpec
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test

class ArtistMapperTest: StringSpec() {
    init {
        val coldplayBio = LastFmBio("British rock band formed in 1996")
        val megaImageUrl = "megaImageOneUrl"
        val smallImageUrl = "smallImageOneUrl"

        val megaLastFmImage = LastFmImage(megaImageUrl, LastFmImageType.MEGA.type)
        val smallLastFmImage = LastFmImage(smallImageUrl, LastFmImageType.SMALL.type)

        val validLastFmArtistWithMegaImage = LastFmArtist(
            "Coldplay", "mbid", "url", listOf(megaLastFmImage, smallLastFmImage), null,
            coldplayBio)
        val validLastFmArtistWithoutMegaImage = LastFmArtist(
            "Him", "mbid", "url", listOf(smallLastFmImage, smallLastFmImage), null, null)
        val invalidLastFmArtist = LastFmArtist(
            "Unknown", null, "url", listOf(megaLastFmImage, smallLastFmImage), null, null)

        val artistsList = listOf(validLastFmArtistWithMegaImage,
            invalidLastFmArtist,
            validLastFmArtistWithoutMegaImage)

        val artistMapper = ArtistMapper()

        "transform valid list returns artist list" {
            val artists = artistMapper.transform(artistsList)

            artists should haveSize(2)
            artists[0].id shouldBe validLastFmArtistWithMegaImage.mbid
            artists[1].id shouldBe validLastFmArtistWithMegaImage.mbid
        }

        "transform artist with mega image return valid artist" {
            val artist = artistMapper.transform(validLastFmArtistWithMegaImage)

            artist?.run {
                id shouldBe validLastFmArtistWithMegaImage.mbid
                name shouldBe validLastFmArtistWithMegaImage.name
                bio shouldBe validLastFmArtistWithMegaImage.bio?.content
                url shouldBe megaImageUrl
            }
        }

        "transform artist without mega image return valid artist" {
            val artist = artistMapper.transform(validLastFmArtistWithoutMegaImage)

            artist?.run {
                id shouldBe validLastFmArtistWithoutMegaImage.mbid
                name shouldBe validLastFmArtistWithoutMegaImage.name
                bio shouldBe validLastFmArtistWithoutMegaImage.bio?.content
                url shouldBe smallImageUrl
            }
        }

        "transform invalid artist returns null" {
            artistMapper.transform(invalidLastFmArtist) shouldBe null
        }
    }

}
