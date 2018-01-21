// Copyright © FunctionalKotlin.com 2018. All rights reserved.

package com.functionalkotlin.bandhookkotlin.domain.repository

import com.functionalkotlin.bandhookkotlin.domain.entity.Album

interface AlbumRepository {
    fun getTopAlbums(artistId: String?, artistName: String?): List<Album>
    fun getAlbum(id: String): Album?
}
