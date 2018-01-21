// Copyright © FunctionalHub.com 2018. All rights reserved.

package com.functionalkotlin.bandhookkotlin.di

import android.content.Context
import android.support.v7.app.AppCompatActivity
import com.functionalkotlin.bandhookkotlin.di.scope.ActivityScope
import dagger.Module
import dagger.Provides

@Module
abstract class ActivityModule(protected val activity: AppCompatActivity) {

    @Provides
    @ActivityScope
    fun provideActivity(): AppCompatActivity = activity

    @Provides
    @ActivityScope
    fun provideActivityContext(): Context = activity.baseContext
}
