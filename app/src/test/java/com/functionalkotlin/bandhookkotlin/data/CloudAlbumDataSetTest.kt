// Copyright © FunctionalHub.com 2018. All rights reserved.

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
import com.functionalkotlin.bandhookkotlin.domain.entity.AlbumNotFound
import com.functionalkotlin.bandhookkotlin.domain.entity.TopAlbumsNotFound
import com.functionalkotlin.bandhookkotlin.util.asFailure
import com.functionalkotlin.bandhookkotlin.util.asSuccess
import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.never
import com.nhaarman.mockito_kotlin.whenever
import io.kotlintest.matchers.shouldBe
import io.kotlintest.specs.StringSpec
import org.mockito.Mockito.anyString
import org.mockito.Mockito.verify
import retrofit2.Response

class CloudAlbumDataSetTest : StringSpec() {
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
            val asyncResult = cloudAlbumDataSet.requestAlbum(ALBUM_MBID)

            verify(lastFmService).requestAlbum(ALBUM_MBID)
            asyncResult.asSuccess { shouldBe(transform(lastFmResponse.album)) }
        }

        "requestAlbum with unknown mbid returns null" {
            whenever(lastFmService.requestAlbum(ALBUM_MBID))
                .thenReturn(fakeCall(lastFmResponse(unknownAlbumDetail)))

            val asyncResult = cloudAlbumDataSet.requestAlbum(ALBUM_MBID)

            verify(lastFmService).requestAlbum(ALBUM_MBID)
            asyncResult.asFailure { shouldBe(AlbumNotFound(ALBUM_MBID)) }
        }

        "requestTopAlbums with valid artist mbid returns valid list" {
            val asyncResult = cloudAlbumDataSet.requestTopAlbums(ARTIST_MBID)

            asyncResult.asSuccess { shouldBe(transform(lastFmResponse.topAlbums.albums)) }
            verify(lastFmService).requestAlbums(ARTIST_MBID, "")
        }

        "requestTopAlbums with invalid arguments returns error" {
            val asyncResult = cloudAlbumDataSet.requestTopAlbums("")

            verify(lastFmService, never()).requestAlbums(anyString(), anyString())
            asyncResult.asFailure { shouldBe(TopAlbumsNotFound("")) }
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
