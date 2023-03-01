plugins {
    id ("com.android.application")
    kotlin ("android")
}

android {
    namespace = "com.example.demoapp"
    compileSdk = AndroidSdk.compile

    defaultConfig {
        applicationId = "com.example.demoapp"
        minSdk = AndroidSdk.min
        targetSdk = AndroidSdk.target
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles (
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation (Libraries.ktxCore)
    implementation (Libraries.appCompat)
    implementation (Libraries.material)
    implementation (Libraries.constraintLayout)
    implementation (Libraries.koinCore)
    implementation (Libraries.koinAndroid)
    implementation (Libraries.navigationFragmentKtx)
    implementation (Libraries.navigationUiKtx)

    implementation(project(":uiCommon"))
    implementation(project(":uiUsersProfiles"))
}