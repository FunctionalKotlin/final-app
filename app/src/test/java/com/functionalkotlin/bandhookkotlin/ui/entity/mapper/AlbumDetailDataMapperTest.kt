// Copyright © FunctionalKotlin.com 2018. All rights reserved.

package com.functionalkotlin.bandhookkotlin.ui.entity.mapper

import com.functionalkotlin.bandhookkotlin.domain.entity.Album
import com.functionalkotlin.bandhookkotlin.domain.entity.Artist
import com.functionalkotlin.bandhookkotlin.ui.entity.mapper.album.detail.AlbumDetailDataMapper
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test

class AlbumDetailDataMapperTest {

    lateinit var album: Album

    lateinit var albumDetailDataMapper: AlbumDetailDataMapper

    @Before
    fun setUp() {
        album = Album("album id", "album name", Artist("artist id", "artist name"), "album url", emptyList())

        albumDetailDataMapper = AlbumDetailDataMapper()
    }

    @Test
    fun testTransform() {
        // When
        val transformedAlbum = albumDetailDataMapper.transform(album)

        // Then
        assertNotNull(transformedAlbum)
        assertEquals(album.id, transformedAlbum?.id)
        assertEquals(album.name, transformedAlbum?.name)
        assertEquals(album.url, transformedAlbum?.url)
        assertEquals(album.tracks, transformedAlbum?.tracks)
    }

    @Test
    fun testTransform_null() {
        // When
        val transformedAlbum = albumDetailDataMapper.transform(null)

        // Then
        assertNull(transformedAlbum)
    }
}
