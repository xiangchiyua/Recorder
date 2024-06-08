plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.recorder"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.recorder"
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    buildFeatures {
        viewBinding = true
    }
}


dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.constraintlayout)
    implementation(libs.lifecycle.livedata.ktx)
    implementation(libs.lifecycle.viewmodel.ktx)
    implementation(libs.navigation.fragment)
    implementation(libs.navigation.ui)
    implementation(libs.gridlayout)
    implementation("com.github.AAChartModel:AAChartCore-Kotlin:7.2.0")
    implementation("com.squareup.retrofit2:retrofit:2.4.0")
    implementation("com.squareup.retrofit2:converter-gson:2.4.0")
    implementation(files("libs/commons-net-3.11.0.jar"))
    implementation(libs.firebase.crashlytics.buildtools)
    implementation(libs.json)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}