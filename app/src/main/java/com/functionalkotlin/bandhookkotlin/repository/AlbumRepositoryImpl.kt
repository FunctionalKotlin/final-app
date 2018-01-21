// Copyright © FunctionalKotlin.com 2018. All rights reserved.

package com.functionalkotlin.bandhookkotlin.repository

import com.functionalkotlin.bandhookkotlin.domain.repository.AlbumRepository
import com.functionalkotlin.bandhookkotlin.repository.dataset.AlbumDataSet

class AlbumRepositoryImpl(val albumDataSets: List<AlbumDataSet>) : AlbumRepository {

    override fun getAlbum(id: String) = albumDataSets
            .map { it.requestAlbum(id) }
            .firstOrNull { it != null }

    override fun getTopAlbums(artistId: String?, artistName: String?) = albumDataSets
                .map { it.requestTopAlbums(artistId, artistName) }
                .firstOrNull { it.isNotEmpty() }
                ?: emptyList()
}
