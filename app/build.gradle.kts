plugins {
    id(libs.plugins.android.application.get().pluginId)
    id(libs.plugins.kotlin.android.core.get().pluginId)
    alias(libs.plugins.kotlin.compose)
    id(libs.plugins.gms.oss.licenses.plugin.get().pluginId)
    alias(libs.plugins.ksp)
    alias(libs.plugins.dagger.hilt.android.core)
}

android {
    compileSdk = 35

    defaultConfig {
        applicationId = "net.twinte.android"
        minSdk = 24
        targetSdk = 35
        versionCode = 27
        versionName = "4.0.2"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        compose = true
        viewBinding = true
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled  = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    namespace = "net.twinte.android"
}

dependencies {
    val composeBom = platform(libs.androidx.compose.bom)

    implementation(fileTree("dir" to "libs", "include" to listOf("*.jar")))
    implementation(composeBom)
    implementation(libs.kotlin.stdlib)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.compose.foundation)
    implementation(libs.androidx.compose.foundation.layout)
    implementation(libs.androidx.compose.material)
    implementation(libs.androidx.compose.material.icons.extended)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.viewbinding)
    implementation(libs.androidx.credentials)
    implementation(libs.androidx.credentials.play.services.auth)
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.work.runtime.ktx)
    implementation(libs.android.material)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    implementation(libs.androidx.preference.ktx)
    implementation(libs.androidx.webkit)
    implementation(libs.squareup.okhttp3)
    implementation(libs.gms.play.services.oss.licenses)
    implementation(libs.gson)
    implementation(libs.googleid)
    testImplementation(libs.junit.core)
    androidTestImplementation(composeBom)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.androidx.test.espresso.core)

    // gRPC, Connect
    implementation(libs.androidx.recyclerview)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.connect.kotlin.okhttp)
    // JavaLite specific dependencies
    implementation(libs.connect.kotlin.google.javalite.ext)
    implementation(libs.protobuf.javalite)

    // Hilt
    implementation(libs.hilt.android.core)
    ksp(libs.hilt.android.compiler)
    implementation(libs.hilt.work)

    implementation(libs.androidx.lifecycle.viewmodel.ktx)

    // Mocking interfaces for testing
    testImplementation(libs.mockk)

    // Running test with suspend functions
    testImplementation(libs.kotlinx.coroutines.test)
}
