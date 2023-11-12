@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.com.android.library)
    alias(libs.plugins.android.jetbrain.kotlin)
    alias(libs.plugins.android.jetbrain.kotlin.kapt)

}

android {
    namespace = "com.example.data"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":utility"))
    implementation(libs.bundles.lifecycle)
    implementation(libs.bundles.coroutine)

    implementation(libs.roomRuntime)
    kapt(libs.roomCompiler)
    implementation (libs.roomKtx)
//    kapt(libs.room.persistance)
    //    appcompat", "constraintLayout", "material", "coreKtx", "fragmentKtx", "ssp", "sdp
    implementation(libs.bundles.support)
    implementation(libs.recyclerview)
    implementation(libs.bundles.network)
    implementation(libs.bundles.koins)
}