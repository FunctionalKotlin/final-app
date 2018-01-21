// Copyright © FunctionalKotlin.com 2018. All rights reserved.

package com.functionalkotlin.bandhookkotlin.domain.interactor

import com.functionalkotlin.bandhookkotlin.domain.entity.Album
import com.functionalkotlin.bandhookkotlin.domain.entity.TopAlbumsNotFound
import com.functionalkotlin.bandhookkotlin.domain.repository.AlbumRepository
import com.functionalkotlin.bandhookkotlin.functional.AsyncResult

class GetTopAlbumsInteractor(private val albumRepository: AlbumRepository) {

    fun getTopAlbums(artistId: String): AsyncResult<List<Album>, TopAlbumsNotFound> =
        albumRepository.getTopAlbums(artistId)

}
