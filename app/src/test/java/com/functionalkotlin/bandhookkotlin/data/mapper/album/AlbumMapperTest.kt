// Copyright © FunctionalKotlin.com 2018. All rights reserved.

package com.functionalkotlin.bandhookkotlin.data.mapper.album

import com.functionalkotlin.bandhookkotlin.data.lastfm.model.LastFmAlbum
import com.functionalkotlin.bandhookkotlin.data.lastfm.model.LastFmAlbumDetail
import com.functionalkotlin.bandhookkotlin.data.lastfm.model.LastFmArtist
import com.functionalkotlin.bandhookkotlin.data.lastfm.model.LastFmImage
import com.functionalkotlin.bandhookkotlin.data.lastfm.model.LastFmImageType
import com.functionalkotlin.bandhookkotlin.data.lastfm.model.LastFmTrack
import com.functionalkotlin.bandhookkotlin.data.lastfm.model.LastFmTracklist
import io.kotlintest.matchers.beEmpty
import io.kotlintest.matchers.haveSize
import io.kotlintest.matchers.should
import io.kotlintest.matchers.shouldBe
import io.kotlintest.matchers.shouldNotBe
import io.kotlintest.specs.StringSpec

class AlbumMapperTest : StringSpec() {
    init {
        val lastFmArtist = LastFmArtist(
            "name", "artist mbid", "artist url", emptyList(), null, null)
        val lastFmImages = listOf(
            LastFmImage("image url", LastFmImageType.MEGA.type))
        val lastFmTrackList = LastFmTracklist(
            listOf(LastFmTrack("track name", 10, null, null, lastFmArtist)))

        val lastFmAlbum = LastFmAlbum(
            "name", "mbid", "url", lastFmArtist, lastFmImages, lastFmTrackList)
        val lastFmAlbumWithoutId = LastFmAlbum(
            "name", null, "url", lastFmArtist, lastFmImages, lastFmTrackList)
        val lastFmAlbumWithoutImages = LastFmAlbum(
            "name", "mbid", "url", lastFmArtist, emptyList(), lastFmTrackList)

        "transform invalid albums returns empty list" {
            val albums = listOf(lastFmAlbumWithoutId, lastFmAlbumWithoutImages)

            transform(albums) should beEmpty()
        }

        "transform valid albums returns valid list" {
            val albums = transform(listOf(lastFmAlbum, lastFmAlbum))

            albums should haveSize(2)
            albums[0].id shouldBe lastFmAlbum.mbid
            albums[1].id shouldBe lastFmAlbum.mbid
        }

        "transform empty albums returns empty list" {
            transform(emptyList()) should beEmpty()
        }

        "transform albums valid and invalid should only transform valid" {
            val albums = transform(
                listOf(lastFmAlbum, lastFmAlbumWithoutId, lastFmAlbumWithoutImages))

            albums should haveSize(1)
            albums[0].id shouldBe lastFmAlbum.mbid
        }

        "transform album detail should return valid album" {
            val albumDetail = LastFmAlbumDetail(
                "name", "mbid", "url", "name", "album release date", lastFmImages, lastFmTrackList)

            val album = transform(albumDetail)

            album?.run {
                id shouldBe albumDetail.mbid
                name shouldBe albumDetail.name
                url shouldNotBe null
                artist?.name shouldBe albumDetail.artist
                tracks[0].name shouldBe albumDetail.tracks.tracks[0].name
                tracks[0].duration shouldBe albumDetail.tracks.tracks[0].duration
            }
        }

        "transform invalid album detail should return null album" {
            val albumDetail = LastFmAlbumDetail(
                "name", null, "url", "name", "album release date", lastFmImages, lastFmTrackList)

            transform(albumDetail) shouldBe null
        }

        "transform valid album should return valid album" {
            val album = transform(lastFmAlbum)

            album?.run {
                id shouldBe lastFmAlbum.mbid
                name shouldBe lastFmAlbum.name
                url shouldNotBe null
                artist?.name shouldBe lastFmAlbum.artist.name
                artist?.id shouldBe lastFmAlbum.artist.mbid
                tracks[0].name shouldBe lastFmAlbum.tracks?.tracks?.get(0)?.name
                tracks[0].duration shouldBe lastFmAlbum.tracks?.tracks?.get(0)?.duration
            }
        }

        "transform invalid album should return null" {
            transform(lastFmAlbumWithoutId) shouldBe null
        }
    }
}
