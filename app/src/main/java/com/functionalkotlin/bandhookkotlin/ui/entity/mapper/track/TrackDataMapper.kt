// Copyright © FunctionalKotlin.com 2018. All rights reserved.

package com.functionalkotlin.bandhookkotlin.ui.entity.mapper.track

import com.functionalkotlin.bandhookkotlin.domain.entity.Track
import com.functionalkotlin.bandhookkotlin.ui.entity.TrackDetail

class TrackDataMapper {

    fun transform(number: Int, domainTrack: Track) =
        TrackDetail(number, domainTrack.name, domainTrack.duration)

    fun transform(domainTrack: List<Track>): List<TrackDetail> =
        domainTrack.mapIndexed { index, track -> transform(index + 1, track) }

}
