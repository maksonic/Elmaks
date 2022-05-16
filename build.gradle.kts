buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath(Build.ToolsGradle.init)
        classpath(Build.HiltGradle.init)
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${Config.kotlinVersion}")
        classpath(Build.KotlinSerialization.init)
    }
}

task<Delete>("clean") {
    delete(rootProject.buildDir)
}