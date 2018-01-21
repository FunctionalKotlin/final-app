// Copyright © FunctionalHub.com 2018. All rights reserved.

package com.functionalkotlin.bandhookkotlin.di

import android.content.Context
import com.functionalkotlin.bandhookkotlin.App
import com.functionalkotlin.bandhookkotlin.di.qualifier.ApplicationQualifier
import com.functionalkotlin.bandhookkotlin.di.qualifier.LanguageSelection
import com.squareup.picasso.Picasso
import dagger.Module
import dagger.Provides
import java.util.Locale
import javax.inject.Singleton

@Module
class ApplicationModule(private val app: App) {

    @Provides
    @Singleton
    fun provideApplication(): App = app

    @Provides
    @Singleton
    @ApplicationQualifier
    fun provideApplicationContext(): Context = app

    @Provides
    @Singleton
    fun providePicasso(@ApplicationQualifier context: Context): Picasso =
        Picasso.Builder(context).build()

    @Provides
    @Singleton
    @LanguageSelection
    fun provideLanguageSelection(): String = Locale.getDefault().language
}
