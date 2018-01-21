// Copyright © FunctionalKotlin.com 2018. All rights reserved.

package com.functionalkotlin.bandhookkotlin.ui.view

import com.functionalkotlin.bandhookkotlin.ui.entity.AlbumDetail

interface AlbumView : PresentationView {
    fun showAlbum(albumDetail: AlbumDetail?)
}
