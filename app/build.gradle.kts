// Copyright © FunctionalKotlin.com 2018. All rights reserved.

import org.gradle.kotlin.dsl.extra
import org.gradle.kotlin.dsl.getValue
import org.gradle.kotlin.dsl.kotlin
import org.gradle.kotlin.dsl.setValue

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
}

val config = ProjectConfiguration()

android {
    compileSdkVersion(config.android.compileSdkVersion)
    buildToolsVersion(config.android.buildToolsVersion)

    defaultConfig {
        applicationId = config.android.applicationId
        minSdkVersion(config.android.minSdkVersion)
        targetSdkVersion(config.android.targetSdkVersion)
        versionCode = config.android.versionCode
        versionName = config.android.versionName

        testInstrumentationRunner = "android.support.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles("proguard-rules.pro")
        }
    }
}

dependencies {
    compile(config.libs.kotlinStdlib)
    compile(config.libs.appcompat)
    compile(config.libs.recyclerview)
    compile(config.libs.cardview)
    compile(config.libs.palette)
    compile(config.libs.design)
    compile(config.libs.eventbus)
    compile(config.libs.picasso)
    compile(config.libs.okhttp)
    compile(config.libs.okhttpInterceptor)
    compile(config.libs.retrofit)
    compile(config.libs.retrofitGson)
    compile(config.libs.jobqueue)
    compile(config.libs.ankoSdk15)
    compile(config.libs.ankoSupport)
    compile(config.libs.ankoAppcompat)
    compile(config.libs.ankoDesign)
    compile(config.libs.ankoCardview)
    compile(config.libs.ankoRecyclerview)
    compile(config.libs.dagger)
    kapt(config.libs.daggerCompiler)

    testCompile(config.libs.kotlinStdlib)
    testCompile(config.testLibs.junit)
    testCompile(config.testLibs.mockito)

    androidTestCompile(config.libs.kotlinStdlib)
    androidTestCompile(config.testLibs.mockito)
    androidTestCompile(config.testLibs.dexmaker)
    androidTestCompile(config.testLibs.dexmakerMockito)
    androidTestCompile(config.testLibs.annotations)
    androidTestCompile(config.testLibs.espresso)
}