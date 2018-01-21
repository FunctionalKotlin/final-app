// Copyright © FunctionalKotlin.com 2018. All rights reserved.

package com.functionalkotlin.bandhookkotlin.ui.entity

import com.functionalkotlin.bandhookkotlin.domain.entity.Track

data class AlbumDetail(val id: String,
                       val name: String,
                       val url: String? = null,
                       val tracks: List<Track> = emptyList())
