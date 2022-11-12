// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "7.3.1" apply false
    id("com.android.library") version "7.3.1" apply false
    id("org.jetbrains.kotlin.android") version "1.7.20" apply false
    id("com.google.dagger.hilt.android") version "2.44" apply false
}

tasks.create<Delete>("clean") {
    group = "build"
    delete(rootProject.buildDir)
}

project.ext {
    set("compileSdk", 32)
    set("minSdk", 21)
    set("targetSdk", 32)
}