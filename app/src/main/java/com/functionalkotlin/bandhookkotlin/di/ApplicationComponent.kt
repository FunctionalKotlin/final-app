// Copyright © FunctionalKotlin.com 2018. All rights reserved.

package com.functionalkotlin.bandhookkotlin.di

import com.functionalkotlin.bandhookkotlin.di.subcomponent.album.AlbumActivityComponent
import com.functionalkotlin.bandhookkotlin.di.subcomponent.album.AlbumActivityModule
import com.functionalkotlin.bandhookkotlin.di.subcomponent.detail.ArtistActivityComponent
import com.functionalkotlin.bandhookkotlin.di.subcomponent.detail.ArtistActivityModule
import com.functionalkotlin.bandhookkotlin.di.subcomponent.main.MainActivityComponent
import com.functionalkotlin.bandhookkotlin.di.subcomponent.main.MainActivityModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(
        ApplicationModule::class,
        DataModule::class,
        RepositoryModule::class,
        DomainModule::class
))
interface ApplicationComponent {

    fun plus(module: MainActivityModule): MainActivityComponent
    fun plus(module: ArtistActivityModule): ArtistActivityComponent
    fun plus(module: AlbumActivityModule): AlbumActivityComponent
}
