// Copyright © FunctionalKotlin.com 2018. All rights reserved.

package com.functionalkotlin.bandhookkotlin.data.mapper.track

import com.functionalkotlin.bandhookkotlin.data.lastfm.model.LastFmArtist
import com.functionalkotlin.bandhookkotlin.data.lastfm.model.LastFmTrack
import io.kotlintest.matchers.haveSize
import io.kotlintest.matchers.should
import io.kotlintest.matchers.shouldBe
import io.kotlintest.specs.StringSpec
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue

class TrackMapperTest: StringSpec() {
    init {
        val lastFmArtist = LastFmArtist("name", "mbid", "url", emptyList(), null, null)
        val lastFmTrack = LastFmTrack("name", 10, null, "url", lastFmArtist)

        val trackMapper = TrackMapper()

        "transform valid tracks return valid list" {
            val tracks = trackMapper.transform(listOf(lastFmTrack, lastFmTrack))

            tracks should haveSize(2)
            lastFmTrack.run {
                tracks[0].name shouldBe name
                tracks[0].duration shouldBe duration
                tracks[1].name shouldBe name
                tracks[1].duration shouldBe duration
            }
        }

        "transform null list returns empty list" {
            trackMapper.transform(null) should haveSize(0)
        }

        "transform empty list returns empty list" {
            trackMapper.transform(emptyList()) should haveSize(0)
        }

        "transform track returns track" {
            trackMapper.transform(lastFmTrack).run {
                name shouldBe lastFmTrack.name
                duration shouldBe lastFmTrack.duration
            }
        }
    }
}
