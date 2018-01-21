// Copyright © FunctionalKotlin.com 2018. All rights reserved.

package com.functionalkotlin.bandhookkotlin.data

import com.functionalkotlin.bandhookkotlin.data.lastfm.LastFmService
import com.functionalkotlin.bandhookkotlin.data.lastfm.model.LastFmAlbum
import com.functionalkotlin.bandhookkotlin.data.lastfm.model.LastFmAlbumDetail
import com.functionalkotlin.bandhookkotlin.data.lastfm.model.LastFmArtist
import com.functionalkotlin.bandhookkotlin.data.lastfm.model.LastFmArtistList
import com.functionalkotlin.bandhookkotlin.data.lastfm.model.LastFmArtistMatches
import com.functionalkotlin.bandhookkotlin.data.lastfm.model.LastFmResponse
import com.functionalkotlin.bandhookkotlin.data.lastfm.model.LastFmResult
import com.functionalkotlin.bandhookkotlin.data.lastfm.model.LastFmTopAlbums
import com.functionalkotlin.bandhookkotlin.data.lastfm.model.LastFmTracklist
import com.functionalkotlin.bandhookkotlin.data.mapper.album.transform
import com.functionalkotlin.bandhookkotlin.data.mock.FakeCall
import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.never
import com.nhaarman.mockito_kotlin.whenever
import io.kotlintest.matchers.beEmpty
import io.kotlintest.matchers.should
import io.kotlintest.matchers.shouldBe
import io.kotlintest.specs.StringSpec
import org.mockito.Mockito.anyString
import org.mockito.Mockito.verify
import retrofit2.Response

const val ALBUM_MBID = "mbid"
const val ARTIST_MBID = "mbid"
const val ARTIST_NAME = "name"

class CloudAlbumDataSetTest: StringSpec() {
    init {
        val knownAlbumDetail = LastFmAlbumDetail(
            "name", ALBUM_MBID, "url", "artist", "date", emptyList(), LastFmTracklist(emptyList()))

        val unknownAlbumDetail = LastFmAlbumDetail(
            "", null, "", "", "", emptyList(), LastFmTracklist(emptyList()))

        val lastFmResponse = lastFmResponse(knownAlbumDetail)

        val lastFmService = mock<LastFmService> {
            on { requestAlbum(ALBUM_MBID) } doReturn fakeCall(lastFmResponse)
            on { requestAlbums(anyString(), anyString()) } doReturn fakeCall(lastFmResponse)
        }

        val cloudAlbumDataSet = CloudAlbumDataSet(lastFmService)

        "requestAlbum with valid mbid returns valid album" {
            val album = cloudAlbumDataSet.requestAlbum(ALBUM_MBID)

            verify(lastFmService).requestAlbum(ALBUM_MBID)
            album shouldBe transform(lastFmResponse.album)
        }

        "requestAlbum with unknown mbid returns null" {
            whenever(lastFmService.requestAlbum(ALBUM_MBID))
                .thenReturn(fakeCall(lastFmResponse(unknownAlbumDetail)))

            val album = cloudAlbumDataSet.requestAlbum(ALBUM_MBID)

            verify(lastFmService).requestAlbum(ALBUM_MBID)
            album shouldBe null
        }

        "requestTopAlbums with valid artist mbid returns valid list" {
            val albums = cloudAlbumDataSet.requestTopAlbums(ARTIST_MBID, null)

            verify(lastFmService).requestAlbums(ARTIST_MBID, "")
            albums shouldBe transform(lastFmResponse.topAlbums.albums)
        }

        "requestTopAlbums with valid artist name returns valid list" {
            val albums = cloudAlbumDataSet.requestTopAlbums(null, ARTIST_NAME)

            verify(lastFmService).requestAlbums("", ARTIST_NAME)
            albums shouldBe transform(lastFmResponse.topAlbums.albums)
        }

        "requestTopAlbums with valid artist mbid and name returns valid list" {
            val albums = cloudAlbumDataSet.requestTopAlbums(ARTIST_MBID, ARTIST_NAME)

            verify(lastFmService).requestAlbums(ARTIST_MBID, ARTIST_NAME)
            albums shouldBe transform(lastFmResponse.topAlbums.albums)
        }

        "requestTopAlbums with invalid arguments returns empty list" {
            val albums = cloudAlbumDataSet.requestTopAlbums(null, null)

            verify(lastFmService, never()).requestAlbums(anyString(), anyString())
            albums should beEmpty()
        }
    }

    private fun lastFmResponse(lastFmAlbumDetail: LastFmAlbumDetail): LastFmResponse {
        val artist = LastFmArtist(ARTIST_NAME, ARTIST_MBID, "url", emptyList(), null, null)

        val topAlbums = LastFmTopAlbums(listOf(LastFmAlbum(
            "name", ALBUM_MBID, "url", artist, emptyList(), LastFmTracklist(emptyList()))))

        return LastFmResponse(
            LastFmResult(LastFmArtistMatches(emptyList())), artist, topAlbums,
            LastFmArtistList(emptyList()), lastFmAlbumDetail)
    }

    private fun fakeCall(lastFmResponse: LastFmResponse) =
        FakeCall(Response.success(lastFmResponse), null)

}
