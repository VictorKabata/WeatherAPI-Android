@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.android.kotlin)
    id("com.google.firebase.appdistribution")
    id("com.google.gms.google-services")
}

android {
    namespace = "com.vickbt.weatherapiandroid"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.vickbt.weatherapiandroid"
        minSdk = 24
        targetSdk = 34
        versionCode = if (System.getenv("VERSION_CODE").isNullOrEmpty()) {
            1
        } else {
            System.getenv("VERSION_CODE").toInt()
        }
        versionName = if (System.getenv("VERSION_NAME").isNullOrEmpty()) {
            "1.0.0"
        } else {
            System.getenv("VERSION_NAME")?.toString()
        }

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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

    testOptions {
        packagingOptions {
            jniLibs {
                useLegacyPackaging = true
            }
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources.excludes.add("/META-INF/{AL2.0,LGPL2.1}")
        resources.excludes.add("META-INF/*")
    }
}

dependencies {
    implementation(project(":shared"))

    // Koin-Dependency injection
    implementation(libs.koin.android)
    implementation(libs.koin.androidx.compose)

    implementation(libs.core.ktx)
    implementation(libs.material3)

    implementation(libs.lifecycle.runtime)

    implementation(libs.activity.compose)
    implementation(platform(libs.compose.bom))
    implementation(libs.ui)
    implementation(libs.ui.graphics)
    implementation(libs.ui.tooling.preview)

    implementation(libs.navigation.compose)

    implementation(libs.coil)
    implementation(libs.firebase.analytics)

    testImplementation(libs.androidX.junit)
    testImplementation(libs.android.test.core)
    testImplementation(libs.kotlinX.coroutines.test)
    testImplementation(libs.mockk)
    testImplementation(libs.google.truth)
    testImplementation(libs.roboelectric)

    androidTestImplementation(platform(libs.compose.bom))
    androidTestImplementation(libs.ui.test.junit4)
    androidTestImplementation(libs.mockk.android)

    debugImplementation(libs.ui.tooling)
    debugImplementation(libs.ui.test.manifest)
}
