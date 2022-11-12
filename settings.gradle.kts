enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
enableFeaturePreview("VERSION_CATALOGS")

pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }

    resolutionStrategy {
        eachPlugin {
            when (requested.id.id) {
                "dagger.hilt.android.plugin" -> {
                    useModule("com.google.dagger:hilt-android-gradle-plugin:2.44")
                }
            }
        }
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }

    versionCatalogs {
        create("deps") {
            from(files("deps.versions.toml"))
        }
    }
}

rootProject.name = "KTBaseProject"

listOf(
    "ShowCase"
).map {
    "LayerShell:$it"
}.forEach {
    include(it)
}

listOf(
    "Common",
    "Network"
).map {
    "LayerInfra:$it"
}.forEach {
    include(it)
}