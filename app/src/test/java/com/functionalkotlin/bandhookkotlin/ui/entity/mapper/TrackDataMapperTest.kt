// Copyright © FunctionalHub.com 2018. All rights reserved.

package com.functionalkotlin.bandhookkotlin.ui.entity.mapper

import com.functionalkotlin.bandhookkotlin.domain.entity.Track
import com.functionalkotlin.bandhookkotlin.ui.entity.mapper.track.transform
import io.kotlintest.matchers.shouldBe
import io.kotlintest.specs.StringSpec

class TrackDataMapperTest : StringSpec() {

    init {
        val track = Track("track name", 10)
        val tracks = listOf(track, track)

        "transform track return valid track" {
            transform(1, track).run {
                number shouldBe 1
                name shouldBe track.name
                duration shouldBe track.duration
            }
        }

        "transform tracks return valid list" {
            transform(tracks).run {
                size shouldBe 2
                get(0).run {
                    name shouldBe track.name
                    duration shouldBe track.duration
                }
                get(1).run {
                    name shouldBe track.name
                    duration shouldBe track.duration
                }
            }
        }
    }


}
