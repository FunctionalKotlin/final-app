// Copyright © FunctionalKotlin.com 2018. All rights reserved.

package com.functionalkotlin.bandhookkotlin.ui.entity.mapper.image.title

import com.functionalkotlin.bandhookkotlin.domain.entity.Album
import com.functionalkotlin.bandhookkotlin.domain.entity.Artist
import com.functionalkotlin.bandhookkotlin.ui.entity.ImageTitle

class ImageTitleDataMapper {

    fun transformArtists(artists: List<Artist>): List<ImageTitle> = artists.map {
        ImageTitle(it.id, it.name, it.url)
    }

    fun transformAlbums(albums: List<Album>): List<ImageTitle> = albums.map {
        ImageTitle(it.id, it.name, it.url)
    }
}
