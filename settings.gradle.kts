pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "Elmaks"
include (":app")
include(":core")
include(":data")
include(":domain")
include(":feature:main")
include(":feature:details")
include(":shared")
include(":navigation:api")
include(":navigation:impl")
