// Copyright © FunctionalKotlin.com 2018. All rights reserved.

package com.functionalkotlin.bandhookkotlin.repository.dataset

import com.functionalkotlin.bandhookkotlin.domain.entity.Album

interface AlbumDataSet {

    fun requestTopAlbums(artistId: String?, artistName: String?): List<Album>
    fun requestAlbum(mbid: String): Album?

}
