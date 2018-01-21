// Copyright © FunctionalKotlin.com 2018. All rights reserved.

package com.functionalkotlin.bandhookkotlin.domain.repository

import com.functionalkotlin.bandhookkotlin.domain.entity.Album
import com.functionalkotlin.bandhookkotlin.domain.entity.AlbumNotFound
import com.functionalkotlin.bandhookkotlin.domain.entity.TopAlbumsNotFound
import com.functionalkotlin.bandhookkotlin.functional.AsyncResult

interface AlbumRepository {
    fun getTopAlbums(artistId: String): AsyncResult<List<Album>, TopAlbumsNotFound>
    fun getAlbum(id: String): AsyncResult<Album, AlbumNotFound>
}
