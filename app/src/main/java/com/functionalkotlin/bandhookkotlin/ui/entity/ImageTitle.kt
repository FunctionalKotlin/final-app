// Copyright © FunctionalKotlin.com 2018. All rights reserved.

package com.functionalkotlin.bandhookkotlin.ui.entity

data class ImageTitle(val id: String, val name: String, private val rawUrl: String? = null) {
    // Picasso handles nulls nicely, but not empty strings
    val url: String? = if ("" == rawUrl) null else rawUrl
}
