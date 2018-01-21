// Copyright © FunctionalKotlin.com 2018. All rights reserved.

package com.functionalkotlin.bandhookkotlin.data.mapper.image

import com.functionalkotlin.bandhookkotlin.data.lastfm.model.LastFmImage
import com.functionalkotlin.bandhookkotlin.data.lastfm.model.LastFmImageType
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test

class ImageMapperTest {

    lateinit var imagesWithMegaImage: List<LastFmImage>
    lateinit var imagesWithoutMegaImage: List<LastFmImage>

    lateinit var imageMapper: ImageMapper

    private val megaImageUrl = "mega image url"
    private val smallImageUrl = "small image url"
    private val largeImageUrl = "large image url"

    @Before
    fun setUp() {
        val megaImage = LastFmImage(megaImageUrl, LastFmImageType.MEGA.type)
        val smallImage = LastFmImage(smallImageUrl, LastFmImageType.SMALL.type)
        val largeImage = LastFmImage(largeImageUrl, LastFmImageType.LARGE.type)

        imagesWithMegaImage = listOf(smallImage, megaImage, largeImage)
        imagesWithoutMegaImage = listOf(smallImage, largeImage)

        imageMapper = ImageMapper()
    }

    @Test
    fun testGetMainImageUrl_fromListWithMegaImage() {
        // When
        val mappedUrl = imageMapper.getMainImageUrl(imagesWithMegaImage)

        // Then
        assertEquals(megaImageUrl, mappedUrl)
    }

    @Test
    fun testGetMainImageUrl_fromListWithoutMegaImage() {
        // When
        val mappedUrl = imageMapper.getMainImageUrl(imagesWithoutMegaImage)

        // Then
        assertEquals(largeImageUrl, mappedUrl)
    }

    @Test
    fun testGetMainImageUrl_fromNull() {
        // When
        val mappedUrl = imageMapper.getMainImageUrl(null)

        // Then
        assertNull(mappedUrl)
    }

    @Test
    fun testGetMainImageUrl_fromEmptyList() {
        // When
        val mappedUrl = imageMapper.getMainImageUrl(emptyList())

        // Then
        assertNull(mappedUrl)
    }
}
