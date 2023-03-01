plugins {
    androidLibraryBaseConvention
    id(BuildPlugins.navigationSafeArgs)
}

android {
    namespace = "com.example.usersprofiles"
}

dependencies {
    implementation(project(":uiCommon"))
}