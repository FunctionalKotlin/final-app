// Copyright © FunctionalHub.com 2018. All rights reserved.

package com.functionalkotlin.bandhookkotlin.data.lastfm

import com.functionalkotlin.bandhookkotlin.data.lastfm.model.LastFmResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface LastFmService {

    @GET("/2.0/?method=artist.getinfo")
    fun requestArtistInfo(@Query("mbid") id: String, @Query("lang") language: String):
        Call<LastFmResponse>

    @GET("/2.0/?method=artist.gettopalbums")
    fun requestAlbums(@Query("mbid") id: String, @Query("artist") artist: String):
        Call<LastFmResponse>

    @GET("/2.0/?method=artist.getsimilar")
    fun requestSimilar(@Query("mbid") id: String): Call<LastFmResponse>

    @GET("/2.0/?method=album.getInfo")
    fun requestAlbum(@Query("mbid") id: String): Call<LastFmResponse>
}
