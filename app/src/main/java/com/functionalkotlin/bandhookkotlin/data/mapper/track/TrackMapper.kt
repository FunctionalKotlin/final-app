// Copyright © FunctionalHub.com 2018. All rights reserved.

package com.functionalkotlin.bandhookkotlin.data.mapper.track

import com.functionalkotlin.bandhookkotlin.data.lastfm.model.LastFmTrack
import com.functionalkotlin.bandhookkotlin.domain.entity.Track

fun transform(tracks: List<LastFmTrack>?): List<Track> = tracks?.map(::transform) ?: emptyList()

fun transform(track: LastFmTrack) = Track(track.name, track.duration)
