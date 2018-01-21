// Copyright © FunctionalKotlin.com 2018. All rights reserved.

val kotlinVersion = "1.2.10"
val androidGradleVersion = "2.3.3"

// Compile dependencies
val supportVersion = "26.1.0"
val ankoVersion = "0.10.2"
val daggerVersion = "2.11"
val retrofitVersion = "2.3.0"
val okhttpVersion = "3.9.0"
val eventBusVersion = "2.4.1"
val picassoVersion = "2.5.2"
val priorityJobQueueVersion = "2.0.1"
val kotlinxCoroutinesVersion = "0.14.1"

// Unit tests
val mockitoVersion = "2.8.47"
val kotlinTestVersion = "2.0.7"
val mockitoKoltinVersion = "1.5.0"

class ProjectConfiguration {
    val buildPlugins = BuildPlugins()
    val android = Android()
    val libs = Libs()
    val testLibs = TestLibs()
}

class BuildPlugins {
    val androidGradle = "com.android.tools.build:gradle:$androidGradleVersion"
    val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion"
}

class Android {
    val buildToolsVersion = "26.0.2"
    val minSdkVersion = 19
    val targetSdkVersion = 26
    val compileSdkVersion = 26
    val applicationId = "com.functionalkotlin.bandhookkotlin"
    val versionCode = 1
    val versionName = "0.1"

}

class Libs {
    val kotlinStdlib = "org.jetbrains.kotlin:kotlin-stdlib:$kotlinVersion"
    val appcompat = "com.android.support:appcompat-v7:$supportVersion"
    val recyclerview = "com.android.support:recyclerview-v7:$supportVersion"
    val cardview = "com.android.support:cardview-v7:$supportVersion"
    val palette = "com.android.support:palette-v7:$supportVersion"
    val design = "com.android.support:design:$supportVersion"
    val eventbus = "de.greenrobot:eventbus:$eventBusVersion"
    val picasso = "com.squareup.picasso:picasso:$picassoVersion"
    val okhttp = "com.squareup.okhttp3:okhttp:$okhttpVersion"
    val okhttpInterceptor = "com.squareup.okhttp3:logging-interceptor:$okhttpVersion"
    val retrofit = "com.squareup.retrofit2:retrofit:$retrofitVersion"
    val retrofitGson = "com.squareup.retrofit2:converter-gson:$retrofitVersion"
    val jobqueue = "com.birbit:android-priority-jobqueue:$priorityJobQueueVersion"
    val ankoSdk15 = "org.jetbrains.anko:anko-sdk15:$ankoVersion"
    val ankoSupport = "org.jetbrains.anko:anko-support-v4:$ankoVersion"
    val ankoAppcompat = "org.jetbrains.anko:anko-appcompat-v7:$ankoVersion"
    val ankoDesign = "org.jetbrains.anko:anko-design:$ankoVersion"
    val ankoCardview = "org.jetbrains.anko:anko-cardview-v7:$ankoVersion"
    val ankoRecyclerview = "org.jetbrains.anko:anko-recyclerview-v7:$ankoVersion"
    val daggerCompiler = "com.google.dagger:dagger-compiler:$daggerVersion"
    val dagger = "com.google.dagger:dagger:$daggerVersion"
    val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$kotlinxCoroutinesVersion"
    val coroutinesAndroid =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:$kotlinxCoroutinesVersion"
}

class TestLibs {
    val junit = "junit:junit:4.12"
    val mockito = "org.mockito:mockito-core:$mockitoVersion"
    val dexmaker = "com.google.dexmaker:dexmaker:1.2"
    val dexmakerMockito = "com.google.dexmaker:dexmaker-mockito:1.2"
    val annotations = "com.android.support:support-annotations:$supportVersion"
    val espresso = "com.android.support.test.espresso:espresso-core:2.2.2"
    val kotlinTest = "io.kotlintest:kotlintest:$kotlinTestVersion"
    val mockitoKotlin = "com.nhaarman:mockito-kotlin:$mockitoKoltinVersion"
}
