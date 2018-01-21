// Copyright © FunctionalKotlin.com 2018. All rights reserved.

package com.functionalkotlin.bandhookkotlin.ui.entity

data class ArtistDetail(val id: String,
                        val name: String,
                        val url: String? = null,
                        val bio: String? = null,
                        val albums: List<ImageTitle>? = null)
