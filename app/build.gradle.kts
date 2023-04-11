plugins {
    id(Plugins.androidApplication)
    id(Plugins.kotlinAndroid)
}

android {
    namespace = "com.michona.fitify"
    compileSdk = Versions.Android.targetSdk

    defaultConfig {
        applicationId = "com.michona.fitify"
        minSdk = Versions.Android.minSdk
        targetSdk = Versions.Android.targetSdk
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.4"
    }

    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation("androidx.core:core-ktx:${Versions.Android.coreKtx}")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:${Versions.Android.lifecycleKtx}")
    implementation("androidx.activity:activity-compose:${Versions.Android.Compose.activity}")
    implementation("androidx.compose.ui:ui:${Versions.Android.Compose.ui}")
    implementation("androidx.compose.ui:ui-tooling-preview:${Versions.Android.Compose.ui}")
    implementation("androidx.compose.material:material:${Versions.Android.material}")
    implementation("androidx.navigation:navigation-compose:${Versions.Android.Compose.navigation}")

    // TODO: add to Dependency
    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.6.1")

    implementation("io.coil-kt:coil-compose:2.3.0")

    implementation("com.jakewharton.timber:timber:5.0.1")



    implementation("io.insert-koin:koin-androidx-compose:${Versions.koin}")
    implementation("io.insert-koin:koin-android:${Versions.koin}")
    implementation("io.insert-koin:koin-core:${Versions.koin}")

    testImplementation("junit:junit:${Versions.Test.junit}")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:${Versions.Android.Compose.ui}")
    debugImplementation("androidx.compose.ui:ui-tooling:${Versions.Android.Compose.ui}")
    debugImplementation("androidx.compose.ui:ui-test-manifest:${Versions.Android.Compose.ui}")
}