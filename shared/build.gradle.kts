import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget
import com.codingfeline.buildkonfig.compiler.FieldSpec.Type.STRING


plugins {
    alias(libs.plugins.multiplatform)
    // alias(libs.plugins.nativeCocoapod)
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlinX.serialization)
    alias(libs.plugins.buildKonfig)
    // alias(libs.plugins.compose)
}

@OptIn(org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi::class)
kotlin {
    // kotlin.applyDefaultHierarchyTemplate()

    androidTarget()

    val iosTarget: (String, KotlinNativeTarget.() -> Unit) -> KotlinNativeTarget =
        when {
            System.getenv("SDK_NAME")?.startsWith("iphoneos") == true -> ::iosArm64
            System.getenv("NATIVE_ARCH")?.startsWith("arm") == true -> ::iosSimulatorArm64
            else -> ::iosX64
        }
    iosTarget("ios") {}

    sourceSets {
        sourceSets["commonMain"].dependencies {
            api(libs.ktor.core)
            api(libs.ktor.cio)
            api(libs.ktor.contentNegotiation)
            api(libs.ktor.serialization)
            api(libs.ktor.json)
            api(libs.ktor.logging)

            api(libs.coroutines.core)
            api(libs.koin.core)
            api(libs.napier)
            api(libs.kotlinX.dateTime)

            api(libs.multiplatformSettings)
            api(libs.multiplatformSettings.coroutines)
        }

        sourceSets["commonTest"].dependencies {

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
