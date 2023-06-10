plugins {
    id(libs.plugins.android.application.get().pluginId)
    id(libs.plugins.kotlin.android.core.get().pluginId)
    id(libs.plugins.kotlin.android.extensions.get().pluginId)
    id(libs.plugins.gms.oss.licenses.plugin.get().pluginId)
    kotlin("kapt")
    alias(libs.plugins.dagger.hilt.android.core)
}

android {
    compileSdkVersion(33)

    defaultConfig {
        applicationId = "net.twinte.android"
        minSdkVersion(23)
        targetSdkVersion (31)
        versionCode = 19
        versionName = "2.0.3"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled  = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
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

    // Hilt
    implementation(libs.hilt.android.core)
    kapt(libs.hilt.android.compiler)
    implementation(libs.hilt.work)

    implementation(libs.androidx.lifecycle.viewmodel.ktx)

    // Mocking interfaces for testing
    testImplementation(libs.mockk)

    // Running test with suspend functions
    testImplementation(libs.kotlinx.coroutines.test)
}

kapt {
    correctErrorTypes = true
}