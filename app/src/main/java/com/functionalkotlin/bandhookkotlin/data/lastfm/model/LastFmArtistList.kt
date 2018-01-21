// Copyright © FunctionalHub.com 2018. All rights reserved.

package com.functionalkotlin.bandhookkotlin.data.lastfm.model

import com.google.gson.annotations.SerializedName

class LastFmArtistList(@SerializedName("artist") val artists: List<LastFmArtist>)
