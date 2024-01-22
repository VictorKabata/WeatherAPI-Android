import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties
import com.codingfeline.buildkonfig.compiler.FieldSpec.Type.STRING


plugins {
    alias(libs.plugins.multiplatform)
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlinX.serialization)
    alias(libs.plugins.buildKonfig)
    // alias(libs.plugins.compose)
}

@OptIn(org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi::class)
kotlin {
    androidTarget()

    sourceSets {
        sourceSets["commonMain"].dependencies {
            implementation(libs.ktor.core)
            implementation(libs.ktor.cio)
            implementation(libs.ktor.contentNegotiation)
            implementation(libs.ktor.serialization)
            implementation(libs.ktor.json)
            implementation(libs.ktor.logging)

            api(libs.coroutines.core)
            api(libs.koin.core)
            api(libs.napier)
            api(libs.kotlinX.dateTime)

            implementation(libs.multiplatformSettings)
            implementation(libs.multiplatformSettings.coroutines)
        }

        sourceSets["commonTest"].dependencies {
            implementation(kotlin("test"))
            implementation(libs.kotlinX.coroutines.test)
            implementation(libs.mockative)
            implementation(libs.ktor.mock)
        }

        sourceSets["androidMain"].dependencies {
            implementation(libs.play.services.location)
            implementation(libs.play.services.maps)
        }

        sourceSets["androidUnitTest"].dependencies {
        }
    }
}

android {
    compileSdk = 34
    defaultConfig {
        minSdk = 24
    }
    namespace = "com.vickbt.shared"

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/androidMain/res")
    sourceSets["main"].resources.srcDirs("src/commonMain/resources")
}

buildkonfig {
    packageName = "com.vickbt.shared"

    defaultConfigs {
        buildConfigField(
            STRING,
            "API_KEY",
            gradleLocalProperties(rootDir).getProperty("api_key") ?: ""
        )
    }
}
