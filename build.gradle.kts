// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
    id("com.google.dagger.hilt.android") version("2.51") apply false
}

buildscript {
    dependencies {
        //classpath("com.android.tools.build:gradle:7.4.2")
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.51")
       // classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.8.10")
       // classpath ("org.jetbrains.kotlin:kotlin-gradle-plugin:$1.7.20")
    }
}
