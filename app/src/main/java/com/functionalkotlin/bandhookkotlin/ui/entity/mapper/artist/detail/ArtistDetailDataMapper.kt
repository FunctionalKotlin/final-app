// Copyright © FunctionalKotlin.com 2018. All rights reserved.

package com.functionalkotlin.bandhookkotlin.ui.entity.mapper.artist.detail

import com.functionalkotlin.bandhookkotlin.domain.entity.Artist
import com.functionalkotlin.bandhookkotlin.ui.entity.ArtistDetail
import com.functionalkotlin.bandhookkotlin.ui.entity.ImageTitle

fun transform(artist: Artist): ArtistDetail = with(artist) {
    ArtistDetail(id, name, url, bio, albums?.map { ImageTitle(it.id, it.name, it.url) })
}
