// Copyright © FunctionalKotlin.com 2018. All rights reserved.

package com.functionalkotlin.bandhookkotlin.ui.entity.mapper

import com.functionalkotlin.bandhookkotlin.domain.entity.Track
import com.functionalkotlin.bandhookkotlin.ui.entity.mapper.track.TrackDataMapper
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class TrackDataMapperTest {

    lateinit var track: Track
    lateinit var tracks: List<Track>

    lateinit var trackDataMapper: TrackDataMapper

    @Before
    fun setUp() {

        track = Track("track name", 10)
        tracks = listOf(track, track)

        trackDataMapper = TrackDataMapper()
    }

    @Test
    fun testTransformTrack() {
        // When
        val transformedTrack = trackDataMapper.transform(1, track)

        // Then
        assertEquals(1, transformedTrack.number)
        assertEquals(track.name, transformedTrack.name)
        assertEquals(track.duration, transformedTrack.duration)
    }

    @Test
    fun testTransformTracks() {
        // When
        val transformedTracks = trackDataMapper.transform(tracks)

        // Then
        assertEquals(2, transformedTracks.size)
        assertEquals(track.name, transformedTracks[0].name)
        assertEquals(track.name, transformedTracks[1].name)
        assertEquals(track.duration, transformedTracks[0].duration)
        assertEquals(track.duration, transformedTracks[1].duration)
    }
}
