// Copyright © FunctionalHub.com 2018. All rights reserved.

package com.functionalkotlin.bandhookkotlin.ui.entity.mapper.artist.detail

import com.functionalkotlin.bandhookkotlin.domain.entity.Artist
import com.functionalkotlin.bandhookkotlin.ui.entity.ArtistDetail
import com.functionalkotlin.bandhookkotlin.ui.entity.mapper.image.title.transformAlbums

fun transform(artist: Artist): ArtistDetail = with(artist) {
    ArtistDetail(id, name, url, bio, albums?.let(::transformAlbums))
}
