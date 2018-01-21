// Copyright © FunctionalKotlin.com 2018. All rights reserved.

import org.gradle.kotlin.dsl.*

plugins {
    id("io.gitlab.arturbosch.detekt").version("1.0.0.RC3")
}

buildscript {

    val config = ProjectConfiguration()

    repositories {
        jcenter()
        google()
    }

    dependencies {
        classpath(config.buildPlugins.androidGradle)
        classpath(config.buildPlugins.kotlinGradlePlugin)
    }
}

detekt {
    version = "1.0.0.RC3"
    profile("main", Action {
        input = "$projectDir"
        config = "$projectDir/detekt.yml"
        filters = ".*test.*,.*/resources/.*,.*/tmp/.*,.*/.idea/.*"
    })
}

allprojects {
    repositories {
        jcenter()
        google()
    }
}
