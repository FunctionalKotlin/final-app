// Copyright © FunctionalKotlin.com 2018. All rights reserved.

package com.functionalkotlin.bandhookkotlin.ui.presenter

import com.functionalkotlin.bandhookkotlin.domain.interactor.GetRecommendedArtistsInteractor
import com.functionalkotlin.bandhookkotlin.domain.interactor.base.Bus
import com.functionalkotlin.bandhookkotlin.domain.interactor.base.InteractorExecutor
import com.functionalkotlin.bandhookkotlin.domain.interactor.event.ArtistsEvent
import com.functionalkotlin.bandhookkotlin.ui.entity.ImageTitle
import com.functionalkotlin.bandhookkotlin.ui.entity.mapper.image.title.transformArtists
import com.functionalkotlin.bandhookkotlin.ui.view.MainView

class MainPresenter(
    override val view: MainView,
    override val bus: Bus,
    private val recommendedArtistsInteractor: GetRecommendedArtistsInteractor,
    private val interactorExecutor: InteractorExecutor) : Presenter<MainView> {

    override fun onResume() {
        super.onResume()
        interactorExecutor.execute(recommendedArtistsInteractor)
    }

    fun onEvent(event: ArtistsEvent) {
        view.showArtists(transformArtists(event.artists))
    }

    fun onArtistClicked(item: ImageTitle) {
        view.navigateToDetail(item.id)
    }
}
