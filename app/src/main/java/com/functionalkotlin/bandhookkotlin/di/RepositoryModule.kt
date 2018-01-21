// Copyright © FunctionalHub.com 2018. All rights reserved.

package com.functionalkotlin.bandhookkotlin.di

import com.functionalkotlin.bandhookkotlin.data.CloudAlbumDataSet
import com.functionalkotlin.bandhookkotlin.data.CloudArtistDataSet
import com.functionalkotlin.bandhookkotlin.data.lastfm.LastFmService
import com.functionalkotlin.bandhookkotlin.di.qualifier.LanguageSelection
import com.functionalkotlin.bandhookkotlin.domain.repository.AlbumRepository
import com.functionalkotlin.bandhookkotlin.domain.repository.ArtistRepository
import com.functionalkotlin.bandhookkotlin.repository.AlbumRepositoryImpl
import com.functionalkotlin.bandhookkotlin.repository.ArtistRepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideArtistRepo(@LanguageSelection language: String, lastFmService: LastFmService):
        ArtistRepository = ArtistRepositoryImpl(listOf(CloudArtistDataSet(language, lastFmService)))

    @Provides
    @Singleton
    fun provideAlbumRepo(lastFmService: LastFmService): AlbumRepository =
        AlbumRepositoryImpl(listOf(CloudAlbumDataSet(lastFmService)))
}
