// Copyright © FunctionalHub.com 2018. All rights reserved.

package com.functionalkotlin.bandhookkotlin.ui.presenter

import com.functionalkotlin.bandhookkotlin.domain.interactor.GetRecommendedArtistsInteractor
import com.functionalkotlin.bandhookkotlin.functional.fold
import com.functionalkotlin.bandhookkotlin.functional.runAsync
import com.functionalkotlin.bandhookkotlin.ui.entity.ImageTitle
import com.functionalkotlin.bandhookkotlin.ui.entity.mapper.image.title.transformArtists
import com.functionalkotlin.bandhookkotlin.ui.presenter.base.Presenter
import com.functionalkotlin.bandhookkotlin.ui.view.MainView

class MainPresenter(
    override val view: MainView,
    private val recommendedArtistsInteractor: GetRecommendedArtistsInteractor) :
        Presenter<MainView> {

    suspend override fun onResume() {
        super.onResume()

        recommendedArtistsInteractor.getRecommendedArtists().runAsync {
            it.fold(
                onSuccess = { view.showArtists(transformArtists(it)) },
                onError = { })
        }
    }

    fun onArtistClicked(item: ImageTitle) {
        view.navigateToDetail(item.id)
    }
}
