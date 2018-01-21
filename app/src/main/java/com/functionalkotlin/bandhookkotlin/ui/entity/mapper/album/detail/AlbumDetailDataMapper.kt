// Copyright © FunctionalKotlin.com 2018. All rights reserved.

package com.functionalkotlin.bandhookkotlin.ui.entity.mapper.album.detail

import com.functionalkotlin.bandhookkotlin.domain.entity.Album
import com.functionalkotlin.bandhookkotlin.ui.entity.AlbumDetail

class AlbumDetailDataMapper {

    fun transform(album: Album?) = album?.let {
        AlbumDetail(
            album.id,
            album.name,
            album.url,
            album.tracks)
    }
}
