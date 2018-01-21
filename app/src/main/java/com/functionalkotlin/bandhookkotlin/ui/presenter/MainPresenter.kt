// Copyright © FunctionalKotlin.com 2018. All rights reserved.

package com.functionalkotlin.bandhookkotlin.ui.presenter

import com.functionalkotlin.bandhookkotlin.domain.interactor.GetRecommendedArtistsInteractor
import com.functionalkotlin.bandhookkotlin.domain.interactor.base.Bus
import com.functionalkotlin.bandhookkotlin.domain.interactor.base.InteractorExecutor
import com.functionalkotlin.bandhookkotlin.domain.interactor.event.ArtistsEvent
import com.functionalkotlin.bandhookkotlin.ui.entity.ImageTitle
import com.functionalkotlin.bandhookkotlin.ui.entity.mapper.image.title.ImageTitleDataMapper
import com.functionalkotlin.bandhookkotlin.ui.view.MainView

class MainPresenter(
    override val view: MainView,
    override val bus: Bus,
    val recommendedArtistsInteractor: GetRecommendedArtistsInteractor,
    val interactorExecutor: InteractorExecutor,
    val mapper: ImageTitleDataMapper) : Presenter<MainView> {

    override fun onResume() {
        super.onResume()
        interactorExecutor.execute(recommendedArtistsInteractor)
    }

    fun onEvent(event: ArtistsEvent) {
        view.showArtists(mapper.transformArtists(event.artists))
    }

    fun onArtistClicked(item: ImageTitle) {
        view.navigateToDetail(item.id)
    }
}
