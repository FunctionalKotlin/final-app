// Copyright © FunctionalKotlin.com 2018. All rights reserved.

package com.functionalkotlin.bandhookkotlin.ui.entity.mapper.artist.detail

import com.functionalkotlin.bandhookkotlin.domain.entity.Artist
import com.functionalkotlin.bandhookkotlin.ui.entity.ArtistDetail
import com.functionalkotlin.bandhookkotlin.ui.entity.ImageTitle

class ArtistDetailDataMapper {

    fun transform(artist: Artist) = ArtistDetail(
        artist.id,
        artist.name,
        artist.url,
        artist.bio,
        artist.albums?.map { ImageTitle(it.id, it.name, it.url) })
}
