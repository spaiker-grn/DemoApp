private object Versions {
    const val buildToolsVersion: String = "7.4.1"
    const val kotlin: String = "1.8.10"

    const val coreKtx = "1.9.0"
    const val constraintLayout = "2.1.4"
    const val material = "1.8.0"
    const val appCompat = "1.6.1"
    const val koin = "3.2.2"
    const val navVersion = "2.5.3"
    const val navigation = "2.5.3"
    const val dagger = "2.45"
}

object AndroidSdk {
    const val min = 26
    const val compile = 33
    const val target = 33
    const val buildToolsVersion = "33.0.2"
}

object BuildPlugins {
    const val androidGradlePlugin = "com.android.tools.build:gradle:${Versions.buildToolsVersion}"
    const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    const val navigationSafeArgs = "androidx.navigation.safeargs.kotlin"
    const val navigationSafeArgsGradlePlugin =
        "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.navigation}"
}

object Libraries {
    const val ktxCore = "androidx.core:core-ktx:${Versions.coreKtx}"
    const val material = "com.google.android.material:material:${Versions.material}"
    const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
    const val constraintLayout =
        "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    const val koinAndroid = "io.insert-koin:koin-android:${Versions.koin}"
    const val koinCore = "io.insert-koin:koin-core:${Versions.koin}"
    const val navigationFragmentKtx = "androidx.navigation:navigation-fragment-ktx:${Versions.navVersion}"
    const val navigationUiKtx = "androidx.navigation:navigation-ui-ktx:${Versions.navVersion}"

    const val daggerCore = "com.google.dagger:dagger:${Versions.dagger}"
    const val daggerAnnotation = "com.google.dagger:dagger-compiler:${Versions.dagger}"
}