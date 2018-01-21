// Copyright © FunctionalKotlin.com 2018. All rights reserved.

package com.functionalkotlin.bandhookkotlin.domain.entity

data class Album(
    val id: String, val name: String, val artist: Artist? = null, val url: String? = null,
    val tracks: List<Track> = emptyList())

