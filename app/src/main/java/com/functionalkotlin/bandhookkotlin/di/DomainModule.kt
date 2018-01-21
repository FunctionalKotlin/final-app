// Copyright © FunctionalKotlin.com 2018. All rights reserved.

package com.functionalkotlin.bandhookkotlin.di

import com.functionalkotlin.bandhookkotlin.domain.interactor.GetAlbumDetailInteractor
import com.functionalkotlin.bandhookkotlin.domain.interactor.GetArtistDetailInteractor
import com.functionalkotlin.bandhookkotlin.domain.interactor.GetRecommendedArtistsInteractor
import com.functionalkotlin.bandhookkotlin.domain.interactor.GetTopAlbumsInteractor
import com.functionalkotlin.bandhookkotlin.domain.repository.AlbumRepository
import com.functionalkotlin.bandhookkotlin.domain.repository.ArtistRepository
import dagger.Module
import dagger.Provides

@Module
class DomainModule {

    @Provides
    fun provideRecommendedArtistsInteractor(artistRepository: ArtistRepository)
            = GetRecommendedArtistsInteractor(artistRepository)

    @Provides
    fun provideArtistDetailInteractor(artistRepository: ArtistRepository)
            = GetArtistDetailInteractor(artistRepository)

    @Provides
    fun provideTopAlbumsInteractor(albumRepository: AlbumRepository)
            = GetTopAlbumsInteractor(albumRepository)

    @Provides
    fun provideAlbumsDetailInteractor(albumRepository: AlbumRepository)
            = GetAlbumDetailInteractor(albumRepository)
}
