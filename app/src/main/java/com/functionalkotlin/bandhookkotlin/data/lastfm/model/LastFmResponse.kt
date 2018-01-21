// Copyright © FunctionalKotlin.com 2018. All rights reserved.

package com.functionalkotlin.bandhookkotlin.data.lastfm.model

import com.google.gson.annotations.SerializedName

class LastFmResponse(
    val results: LastFmResult,
    val artist: LastFmArtist,
    @SerializedName("topalbums")
    val topAlbums: LastFmTopAlbums,
    @SerializedName("similarartists")
    val similarArtists: LastFmArtistList,
    val album: LastFmAlbumDetail)
