// Copyright © FunctionalKotlin.com 2018. All rights reserved.

package com.functionalkotlin.bandhookkotlin.repository

import com.functionalkotlin.bandhookkotlin.domain.entity.Album
import com.functionalkotlin.bandhookkotlin.domain.entity.Artist
import com.functionalkotlin.bandhookkotlin.domain.repository.AlbumRepository
import com.functionalkotlin.bandhookkotlin.repository.dataset.AlbumDataSet
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class AlbumRepositoryImplTest {

    @Mock
    lateinit var firstAlbumDataSet: AlbumDataSet
    @Mock
    lateinit var secondAlbumDataSet: AlbumDataSet

    lateinit var albumInBothDataSets: Album
    lateinit var albumInSecondDataSet: Album
    lateinit var albumRepository: AlbumRepository

    lateinit var albumsInBothDataSets: List<Album>
    lateinit var albumsInSecondDataSet: List<Album>

    private val albumIdInBothDataSets = "album id"
    private val albumIdInSecondDataSet = "second album id"
    private val artistIdInBothDataSets = "artist id"
    private val artistIdInSecondDataSet = "second artist id"
    private val artistName = "artist name"

    @Before
    fun setUp() {

        albumInBothDataSets = Album(albumIdInBothDataSets, "album name", Artist(artistIdInBothDataSets, artistName), "album url", emptyList())
        albumInSecondDataSet = Album(albumIdInSecondDataSet, "album name", Artist(artistIdInBothDataSets, artistName), "album url", emptyList())

        mockRequestAlbumReturns()

        albumsInBothDataSets = listOf(albumInBothDataSets)
        albumsInSecondDataSet = listOf(albumInSecondDataSet)

        mockRequestTopAlbumsReturns()

        albumRepository = AlbumRepositoryImpl(listOf(firstAlbumDataSet, secondAlbumDataSet))
    }

    private fun mockRequestTopAlbumsReturns() {
        `when`(firstAlbumDataSet.requestTopAlbums(null, artistName)).thenReturn(albumsInBothDataSets)
        `when`(firstAlbumDataSet.requestTopAlbums(artistIdInBothDataSets, null)).thenReturn(albumsInBothDataSets)
        `when`(secondAlbumDataSet.requestTopAlbums(artistIdInSecondDataSet, null)).thenReturn(albumsInSecondDataSet)
    }

    private fun mockRequestAlbumReturns() {
        `when`(firstAlbumDataSet.requestAlbum(albumIdInBothDataSets)).thenReturn(albumInBothDataSets)
        `when`(secondAlbumDataSet.requestAlbum(albumIdInSecondDataSet)).thenReturn(albumInSecondDataSet)
    }

    @Test
    fun testGetAlbum_existingInBothDataSets() {
        // When
        val album = albumRepository.getAlbum(albumIdInBothDataSets)

        // Then
        assertEquals(albumInBothDataSets, album)
    }

    @Test
    fun testGetAlbum_existingOnlyInSecondDataSet() {
        // When
        val album = albumRepository.getAlbum(albumIdInSecondDataSet)

        // Then
        assertEquals(albumInSecondDataSet, album)
    }

    @Test
    fun testGetTopAlbums_withArtistId() {
        // When
        val albums = albumRepository.getTopAlbums(artistIdInBothDataSets, null)

        // Then
        assertEquals(albumsInBothDataSets, albums)
    }

    @Test
    fun testGetTopAlbums_withArtistIdExistingOnlyInSecondDataSet() {
        // When
        val albums = albumRepository.getTopAlbums(artistIdInSecondDataSet, null)

        // Then
        assertEquals(albumsInSecondDataSet, albums)
    }

    @Test
    fun testGetTopAlbums_withArtistName() {
        // When
        val albums = albumRepository.getTopAlbums(null, artistName)

        // Then
        assertEquals(albumsInBothDataSets, albums)
    }

    @Test
    fun testGetTopAlbums_withArtistIdAndArtistName() {
        // When
        val albums = albumRepository.getTopAlbums(null, null)

        // Then
        assertTrue(albums.isEmpty())
    }
}
