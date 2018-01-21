// Copyright © FunctionalHub.com 2018. All rights reserved.

package com.functionalkotlin.bandhookkotlin.ui.entity

data class ImageTitle(val id: String, val name: String, private val rawUrl: String? = null) {

    val url: String? = rawUrl?.takeUnless(""::equals)

}
