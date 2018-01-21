// Copyright © FunctionalKotlin.com 2018. All rights reserved.

package com.functionalkotlin.bandhookkotlin.data.lastfm.model

class LastFmTrack(
    val name: String,
    val duration: Int = 0,
    val mbid: String?,
    val url: String?,
    val artist: LastFmArtist)

