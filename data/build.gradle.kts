plugins {
    androidLibrary()
    kotlinAndroid()
    kotlinSerialization()
    hilt()
    kapt()
    ksp()

}

android {
    compileSdk = Config.compileSdk

    defaultConfig {
        minSdk = Config.minSdk
        targetSdk = Config.targetSdk
        testInstrumentationRunner = Config.androidJunitRunner
    }

    buildTypes {
        getByName(Build.Type.CURRENT) {
            isMinifyEnabled = true

            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = Config.javaVersion
        targetCompatibility = Config.javaVersion
    }
    kotlinOptions {
        jvmTarget = Config.jvmTarget
    }
}

dependencies {
    implementation(project(Module.CORE))
    implementation(project(Module.DOMAIN))
    implementation(Lib.JetBrains.Coroutines.ANDROID)
    implementation(Lib.JetBrains.Serialization.JSON)
    implementation(Lib.Dagger.HILT)
    kapt(Lib.Dagger.COMPILER)
    implementation(Lib.Room.KTX)
    implementation(Lib.Room.RUNTIME)
    ksp(Lib.Room.COMPILER)

}