plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.example.applicationtype1"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.applicationtype1"
        minSdk = 24
        targetSdk = 36
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    //noinspection UseTomlInstead
    implementation("androidx.fragment:fragment:1.8.9")
    implementation("androidx.fragment:fragment-ktx:1.8.9")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.9.4")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.9.4")
    implementation ("androidx.compose.ui:ui:1.9.2")
    implementation ("androidx.compose.material3:material3:1.4.0")
    implementation ("androidx.compose.material:material-icons-extended:1.7.8")
    implementation ("androidx.compose.ui:ui-tooling-preview:1.9.2")
    debugImplementation ("androidx.compose.ui:ui-tooling:1.9.2")
    implementation ("androidx.lifecycle:lifecycle-viewmodel-compose:2.9.4")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.10.2")
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    //noinspection UseTomlInstead
    implementation("com.google.android.material:material:1.13.0")
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}