// Copyright © FunctionalKotlin.com 2018. All rights reserved.

package com.functionalkotlin.bandhookkotlin.ui.fragment

import com.functionalkotlin.bandhookkotlin.ui.presenter.AlbumsPresenter

interface AlbumsFragmentContainer {
    fun getAlbumsPresenter(): AlbumsPresenter
}
