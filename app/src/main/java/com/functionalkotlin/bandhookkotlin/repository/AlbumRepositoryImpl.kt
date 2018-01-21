// Copyright © FunctionalKotlin.com 2018. All rights reserved.

package com.functionalkotlin.bandhookkotlin.repository

import com.functionalkotlin.bandhookkotlin.domain.entity.Album
import com.functionalkotlin.bandhookkotlin.domain.entity.AlbumNotFound
import com.functionalkotlin.bandhookkotlin.domain.entity.TopAlbumsNotFound
import com.functionalkotlin.bandhookkotlin.domain.repository.AlbumRepository
import com.functionalkotlin.bandhookkotlin.functional.AsyncResult
import com.functionalkotlin.bandhookkotlin.functional.asError
import com.functionalkotlin.bandhookkotlin.functional.firstSuccessIn
import com.functionalkotlin.bandhookkotlin.repository.dataset.AlbumDataSet

class AlbumRepositoryImpl(val albumDataSets: List<AlbumDataSet>) : AlbumRepository {

    override fun getAlbum(id: String): AsyncResult<Album, AlbumNotFound> =
        firstSuccessIn(
            list = albumDataSets,
            f = { it.requestAlbum(id) },
            acc = AlbumNotFound(id).asError())

    override fun getTopAlbums(artistId: String): AsyncResult<List<Album>, TopAlbumsNotFound> =
        firstSuccessIn(
            list = albumDataSets,
            f = { it.requestTopAlbums(artistId) },
            acc = TopAlbumsNotFound(artistId).asError())

}
