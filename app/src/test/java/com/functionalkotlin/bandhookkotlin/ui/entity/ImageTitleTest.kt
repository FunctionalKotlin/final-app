// Copyright © FunctionalKotlin.com 2018. All rights reserved.

package com.functionalkotlin.bandhookkotlin.ui.entity

import io.kotlintest.matchers.shouldBe
import io.kotlintest.matchers.shouldNotBe
import io.kotlintest.specs.StringSpec

class ImageTitleTest : StringSpec() {

    init {
        "init with empty url returns null" {
            ImageTitle("id", "name", "").url shouldBe null
        }

        "init with null url returns null" {
            ImageTitle("id", "name", null).url shouldBe null
        }

        "init with non empty url returns non null url" {
            ImageTitle("id", "name", "url").url shouldNotBe null
        }
    }

}
