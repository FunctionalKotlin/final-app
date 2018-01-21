// Copyright © FunctionalHub.com 2018. All rights reserved.

package com.functionalkotlin.bandhookkotlin.domain.interactor

import com.functionalkotlin.bandhookkotlin.domain.entity.Album
import com.functionalkotlin.bandhookkotlin.domain.entity.AlbumNotFound
import com.functionalkotlin.bandhookkotlin.domain.repository.AlbumRepository
import com.functionalkotlin.bandhookkotlin.functional.AsyncResult

class GetAlbumDetailInteractor(private val albumRepository: AlbumRepository) {

    fun getAlbum(albumId: String): AsyncResult<Album, AlbumNotFound> =
        albumRepository.getAlbum(albumId)

}
