// Copyright © FunctionalKotlin.com 2018. All rights reserved.

package com.functionalkotlin.bandhookkotlin.repository

import com.functionalkotlin.bandhookkotlin.domain.entity.Album
import com.functionalkotlin.bandhookkotlin.domain.entity.AlbumNotFound
import com.functionalkotlin.bandhookkotlin.domain.entity.Artist
import com.functionalkotlin.bandhookkotlin.domain.entity.TopAlbumsNotFound
import com.functionalkotlin.bandhookkotlin.domain.repository.AlbumRepository
import com.functionalkotlin.bandhookkotlin.functional.Failure
import com.functionalkotlin.bandhookkotlin.functional.Success
import com.functionalkotlin.bandhookkotlin.functional.asError
import com.functionalkotlin.bandhookkotlin.functional.isFailure
import com.functionalkotlin.bandhookkotlin.functional.isSuccess
import com.functionalkotlin.bandhookkotlin.functional.result
import com.functionalkotlin.bandhookkotlin.functional.runSync
import com.functionalkotlin.bandhookkotlin.repository.dataset.AlbumDataSet
import io.kotlintest.matchers.shouldBe
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
        `when`(firstAlbumDataSet.requestTopAlbums("")).thenReturn(TopAlbumsNotFound("").asError())
        `when`(firstAlbumDataSet.requestTopAlbums(artistIdInBothDataSets)).thenReturn(albumsInBothDataSets.result())
        `when`(firstAlbumDataSet.requestTopAlbums(artistIdInSecondDataSet)).thenReturn(TopAlbumsNotFound("").asError())
        `when`(secondAlbumDataSet.requestTopAlbums("")).thenReturn(TopAlbumsNotFound("").asError())
        `when`(secondAlbumDataSet.requestTopAlbums(artistIdInSecondDataSet)).thenReturn(albumsInSecondDataSet.result())
    }

    private fun mockRequestAlbumReturns() {
        `when`(firstAlbumDataSet.requestAlbum(albumIdInBothDataSets)).thenReturn(albumInBothDataSets.result())
        `when`(firstAlbumDataSet.requestAlbum(albumIdInSecondDataSet)).thenReturn(AlbumNotFound(albumIdInSecondDataSet).asError())
        `when`(secondAlbumDataSet.requestAlbum(albumIdInSecondDataSet)).thenReturn(albumInSecondDataSet.result())
    }

    @Test
    fun testGetAlbum_existingInBothDataSets() {
        // When
        val album = albumRepository.getAlbum(albumIdInBothDataSets).runSync()

        // Then
        album.isSuccess() shouldBe true
        (album as Success).value shouldBe albumInBothDataSets
    }

    @Test
    fun testGetAlbum_existingOnlyInSecondDataSet() {
        // When
        val album = albumRepository.getAlbum(albumIdInSecondDataSet).runSync()

        // Then
        album.isSuccess() shouldBe true
        (album as Success).value shouldBe albumInSecondDataSet
    }

    @Test
    fun testGetTopAlbums_withArtistId() {
        val result = albumRepository.getTopAlbums(artistIdInBothDataSets).runSync()

        result.isSuccess() shouldBe true
        (result as Success).value shouldBe albumsInBothDataSets
    }

    @Test
    fun testGetTopAlbums_withArtistIdExistingOnlyInSecondDataSet() {
        val result = albumRepository.getTopAlbums(artistIdInSecondDataSet).runSync()

        result.isSuccess() shouldBe true
        (result as Success).value shouldBe albumsInSecondDataSet
    }

    @Test
    fun testGetTopAlbums_withArtistIdAndArtistName() {
        val result = albumRepository.getTopAlbums("").runSync()

        result.isFailure() shouldBe true
        (result as Failure).error shouldBe TopAlbumsNotFound("")
    }
}
