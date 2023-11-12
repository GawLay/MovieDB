@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.android.jetbrain.kotlin)
    alias(libs.plugins.android.jetbrain.kotlin.kapt)

}

android {
    namespace = "com.example.main"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.main"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation(project(":data"))
    implementation(project(":domain"))
    implementation(project(":utility"))
    implementation(libs.glide)
    implementation(libs.bundles.lifecycle)
    implementation(libs.bundles.coroutine)
    //    appcompat", "constraintLayout", "material", "coreKtx", "fragmentKtx", "ssp", "sdp
    implementation(libs.bundles.support)
    implementation(libs.recyclerview)
    implementation(libs.bundles.network)
    implementation(libs.bundles.koins)
}