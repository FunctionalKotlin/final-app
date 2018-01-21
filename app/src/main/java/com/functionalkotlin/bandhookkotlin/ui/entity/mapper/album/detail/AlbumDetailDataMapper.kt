// Copyright © FunctionalKotlin.com 2018. All rights reserved.

package com.functionalkotlin.bandhookkotlin.ui.entity.mapper.album.detail

import com.functionalkotlin.bandhookkotlin.domain.entity.Album
import com.functionalkotlin.bandhookkotlin.ui.entity.AlbumDetail

fun transform(album: Album?): AlbumDetail? = album?.let {
    AlbumDetail(it.id, it.name, it.url, it.tracks)
}
