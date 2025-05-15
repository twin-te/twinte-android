plugins {
    id(libs.plugins.android.application.get().pluginId)
    id(libs.plugins.kotlin.android.core.get().pluginId)
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
        versionCode = 24
        versionName = "2.1.4"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
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
    implementation(fileTree("dir" to "libs", "include" to listOf("*.jar")))
    implementation(libs.kotlin.stdlib)
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
    implementation(libs.gms.play.services.auth)
    implementation(libs.squareup.okhttp3)
    implementation(libs.gms.play.services.oss.licenses)
    implementation(libs.gson)
    testImplementation(libs.junit.core)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.androidx.test.espresso.core)

    // grpc
    implementation(libs.grpc.grpc.android)
    implementation(libs.grpc.grpc.okhttp)
    implementation(libs.grpc.grpc.kotlin.stub)
    implementation(libs.grpc.grpc.protobuf.lite)
    implementation(libs.google.protobuf.javalite)

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
