// Copyright © FunctionalHub.com 2018. All rights reserved.

package com.functionalkotlin.bandhookkotlin.data.lastfm.model

import com.google.gson.annotations.SerializedName

class LastFmTopAlbums(@SerializedName("album") val albums: List<LastFmAlbum>)

