// Copyright © FunctionalKotlin.com 2018. All rights reserved.

package com.functionalkotlin.bandhookkotlin.data.lastfm.model

import com.google.gson.annotations.SerializedName

class LastFmArtist(
    val name: String,
    val mbid: String?,
    val url: String,
    @SerializedName("image")
    val images: List<LastFmImage>? = null,
    val similar: LastFmSimilar? = null,
    val bio: LastFmBio? = null)

