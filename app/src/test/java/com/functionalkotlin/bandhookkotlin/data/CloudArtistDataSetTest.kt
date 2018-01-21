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
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response

@RunWith(MockitoJUnitRunner::class)
class CloudArtistDataSetTest {

    @Mock
    lateinit var lastFmService: LastFmService

    lateinit var lastFmResponse: LastFmResponse
    lateinit var recommendedArtistList: List<LastFmArtist>
    lateinit var lastFmArtist: LastFmArtist

    lateinit var cloudArtistDataSet: CloudArtistDataSet

    private val artistMbid = "artist mbid"
    private val language = "lang"

    private val lastFmAlbumDetail = LastFmAlbumDetail("album name", artistMbid, "album url", "album artist", "album release",
            emptyList(), LastFmTracklist(emptyList()))

    @Before
    fun setUp() {
        lastFmArtist = LastFmArtist("artist name", artistMbid, "artist url", null, null, null)
        recommendedArtistList = listOf(lastFmArtist)

        lastFmResponse = LastFmResponse(LastFmResult(LastFmArtistMatches(emptyList())),
                lastFmArtist, LastFmTopAlbums(emptyList()), LastFmArtistList(recommendedArtistList),
                lastFmAlbumDetail)

        cloudArtistDataSet = CloudArtistDataSet(language, lastFmService)

        `when`(lastFmService.requestSimilar(cloudArtistDataSet.coldplayMbid)).thenReturn(FakeCall(Response.success(lastFmResponse), null))
        `when`(lastFmService.requestArtistInfo(artistMbid, language)).thenReturn(FakeCall(Response.success(lastFmResponse), null))
    }

    @Test
    fun testRequestRecommendedArtists() {
        // When
        val recommendedArtists = cloudArtistDataSet.requestRecommendedArtists()

        // Then
        verify(lastFmService).requestSimilar(cloudArtistDataSet.coldplayMbid)
        assertEquals(transform(recommendedArtistList), recommendedArtists)
    }

    @Test
    fun testRequestArtist() {
        // When
        val requestedArtist = cloudArtistDataSet.requestArtist(artistMbid)

        // Then
        verify(lastFmService).requestArtistInfo(artistMbid, language)
        assertEquals(transform(lastFmArtist), requestedArtist)
    }

    @Test
    fun testRequestArtist_unknownId() {
        // Given
        val unknownArtisMbid = "unknown artist mbid"
        val unknownArtistResponse = LastFmResponse(LastFmResult(LastFmArtistMatches(emptyList())),
                LastFmArtist("unknown artist name", null, "unknown artist url"),
                LastFmTopAlbums(emptyList()),
                LastFmArtistList(emptyList()), lastFmAlbumDetail)
        `when`(lastFmService.requestArtistInfo(unknownArtisMbid, language)).thenReturn(FakeCall(Response.success(unknownArtistResponse), null))

        // When
        val requestedArtist = cloudArtistDataSet.requestArtist(unknownArtisMbid)

        // Then
        verify(lastFmService).requestArtistInfo(unknownArtisMbid, language)
        assertNull(requestedArtist)
    }
}
