// Copyright © FunctionalKotlin.com 2018. All rights reserved.

package com.functionalkotlin.bandhookkotlin.di.subcomponent.main

import com.functionalkotlin.bandhookkotlin.di.ActivityModule
import com.functionalkotlin.bandhookkotlin.di.scope.ActivityScope
import com.functionalkotlin.bandhookkotlin.domain.interactor.GetRecommendedArtistsInteractor
import com.functionalkotlin.bandhookkotlin.ui.presenter.MainPresenter
import com.functionalkotlin.bandhookkotlin.ui.screens.main.MainActivity
import com.functionalkotlin.bandhookkotlin.ui.view.MainView
import dagger.Module
import dagger.Provides

@Module
class MainActivityModule(activity: MainActivity) : ActivityModule(activity) {

    @Provides
    @ActivityScope
    fun provideMainView(): MainView = activity as MainView

    @Provides
    @ActivityScope
    fun provideMainPresenter(
        view: MainView, recommendedArtistsInteractor: GetRecommendedArtistsInteractor) =
        MainPresenter(view, recommendedArtistsInteractor)
}
