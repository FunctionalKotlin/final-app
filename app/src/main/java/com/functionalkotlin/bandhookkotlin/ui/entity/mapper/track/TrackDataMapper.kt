// Copyright © FunctionalHub.com 2018. All rights reserved.

package com.functionalkotlin.bandhookkotlin.ui.entity.mapper.track

import com.functionalkotlin.bandhookkotlin.domain.entity.Track
import com.functionalkotlin.bandhookkotlin.ui.entity.TrackDetail

fun transform(number: Int, track: Track): TrackDetail = with(track) {
    TrackDetail(number, name, duration)
}

fun transform(tracks: List<Track>): List<TrackDetail> = tracks.mapIndexed { i, track ->
    transform(i + 1, track)
}
