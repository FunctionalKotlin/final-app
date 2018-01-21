// Copyright © FunctionalKotlin.com 2018. All rights reserved.

package com.functionalkotlin.bandhookkotlin.data.lastfm.model

import com.google.gson.annotations.SerializedName

class LastFmSimilar(@SerializedName("artist") var artists: List<LastFmArtist>)

