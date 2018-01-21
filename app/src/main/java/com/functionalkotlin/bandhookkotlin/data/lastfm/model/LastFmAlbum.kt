// Copyright © FunctionalKotlin.com 2018. All rights reserved.

package com.functionalkotlin.bandhookkotlin.data.lastfm.model

import com.google.gson.annotations.SerializedName

class LastFmAlbum(
    val name: String,
    val mbid: String?,
    val url: String,
    val artist: LastFmArtist,
    @SerializedName("image")
    val images: List<LastFmImage>,
    val tracks: LastFmTracklist?)

