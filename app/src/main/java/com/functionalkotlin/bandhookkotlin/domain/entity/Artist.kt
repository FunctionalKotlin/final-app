// Copyright © FunctionalKotlin.com 2018. All rights reserved.

package com.functionalkotlin.bandhookkotlin.domain.entity

data class Artist(
    val id: String,
    val name: String,
    val url: String? = null,
    val bio: String? = null,
    val albums: List<Album>? = null)
