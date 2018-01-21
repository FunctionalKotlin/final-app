// Copyright © FunctionalHub.com 2018. All rights reserved.

package com.functionalkotlin.bandhookkotlin.data.mapper.image

import com.functionalkotlin.bandhookkotlin.data.lastfm.model.LastFmImage
import com.functionalkotlin.bandhookkotlin.data.lastfm.model.LastFmImageType

fun getMainImageUrl(images: List<LastFmImage>?): String? =
    images
        .takeIf { it != null && !it.isEmpty() }
        ?.let { list ->
            list.firstOrNull { it.size == LastFmImageType.MEGA.type }?.url
                ?: list.last().url
        }
