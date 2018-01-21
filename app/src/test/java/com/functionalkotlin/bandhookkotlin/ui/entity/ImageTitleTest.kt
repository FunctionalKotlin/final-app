// Copyright © FunctionalKotlin.com 2018. All rights reserved.

package com.functionalkotlin.bandhookkotlin.ui.entity

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class ImageTitleTest {

    @Test
    fun testInitWithEmptyUrl() {
        // When
        val imageTitle = ImageTitle("id", "name", "")

        // Then
        assertNull(imageTitle.url)
    }

    @Test
    fun testInitWithNullUrl() {
        // When
        val imageTitle = ImageTitle("id", "name", null)

        // Then
        assertNull(imageTitle.url)
    }

    @Test
    fun testInitWithNotEmptyUrl() {
        // When
        val imageTitle = ImageTitle("id", "name", "url")

        // Then
        assertEquals("url", imageTitle.url)
    }
}
