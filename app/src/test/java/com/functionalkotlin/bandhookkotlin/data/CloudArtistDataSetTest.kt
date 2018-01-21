// Copyright © FunctionalKotlin.com 2018. All rights reserved.

package com.functionalkotlin.bandhookkotlin.data

import com.functionalkotlin.bandhookkotlin.data.lastfm.LastFmService
import com.functionalkotlin.bandhookkotlin.data.lastfm.model.LastFmAlbumDetail
import com.functionalkotlin.bandhookkotlin.data.lastfm.model.LastFmArtist
import com.functionalkotlin.bandhookkotlin.data.lastfm.model.LastFmArtistList
import com.functionalkotlin.bandhookkotlin.data.lastfm.model.LastFmArtistMatches
import com.functionalkotlin.bandhookkotlin.data.lastfm.model.LastFmResponse
import com.functionalkotlin.bandhookkotlin.data.lastfm.model.LastFmResult
import com.functionalkotlin.bandhookkotlin.data.lastfm.model.LastFmTopAlbums
import com.functionalkotlin.bandhookkotlin.data.lastfm.model.LastFmTracklist
import com.functionalkotlin.bandhookkotlin.data.mapper.artist.transform
import com.functionalkotlin.bandhookkotlin.data.mock.FakeCall
import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import io.kotlintest.matchers.shouldBe
import io.kotlintest.specs.StringSpec
import org.junit.Assert.assertNull
import org.mockito.Mockito.`when`
import org.mockito.Mockito.anyString
import org.mockito.Mockito.verify
import retrofit2.Response

class CloudArtistDataSetTest : StringSpec() {

    init {
        val language = "lang"

        val lastFmAlbumDetail = LastFmAlbumDetail(
            "album name", ARTIST_MBID, "album url", "album artist", "album release", emptyList(),
            LastFmTracklist(emptyList()))

        val lastFmArtist = LastFmArtist("name", ARTIST_MBID, "url", null, null, null)

        val lastFmResponse = lastFmResponse(lastFmArtist, lastFmAlbumDetail)

        val lastFmService = mock<LastFmService> {
            on { requestSimilar(anyString()) }.doReturn(fakeCall(lastFmResponse))
            on { requestArtistInfo(ARTIST_MBID, language) }.doReturn(fakeCall(lastFmResponse))
        }

        val cloudArtistDataSet = CloudArtistDataSet(language, lastFmService)

        "requestRecommendedArtists returns recommended artists" {
            val artists = cloudArtistDataSet.requestRecommendedArtists()

            verify(lastFmService).requestSimilar(cloudArtistDataSet.coldplayMbid)
            artists shouldBe transform(listOf(lastFmArtist))
        }

        "requestArtist should return artist" {
            val artist = cloudArtistDataSet.requestArtist(ARTIST_MBID)

            verify(lastFmService).requestArtistInfo(ARTIST_MBID, language)
            artist shouldBe transform(lastFmArtist)
        }

        "requestArtist should return null if unknown id" {
            val unknownMbid = "unknown"

            val unknownArtistResponse = lastFmResponse(
                LastFmArtist("unknown artist name", null, "unknown artist url"), lastFmAlbumDetail)

            whenever(lastFmService.requestArtistInfo(unknownMbid, language))
                .thenReturn(fakeCall(unknownArtistResponse))

            val artist = cloudArtistDataSet.requestArtist(unknownMbid)

            verify(lastFmService).requestArtistInfo(unknownMbid, language)
            artist shouldBe null
        }
    }

    private fun lastFmResponse(
        lastFmArtist: LastFmArtist, lastFmAlbumDetail: LastFmAlbumDetail): LastFmResponse =
            LastFmResponse(
                LastFmResult(LastFmArtistMatches(emptyList())),
                lastFmArtist, LastFmTopAlbums(emptyList()), LastFmArtistList(listOf(lastFmArtist)),
                lastFmAlbumDetail)

    private fun fakeCall(lastFmResponse: LastFmResponse) =
        FakeCall(Response.success(lastFmResponse), null)
}
