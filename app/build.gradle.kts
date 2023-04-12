plugins {
    id(Plugins.androidApplication)
    id(Plugins.kotlinAndroid)
    id("com.google.devtools.ksp")
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

    implementation("androidx.media3:media3-exoplayer:1.0.0")
    implementation("androidx.media3:media3-ui:1.0.0")

    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:${Versions.Network.retrofit}")
    implementation("com.squareup.okhttp3:okhttp:${Versions.Network.okHttp}")
    implementation("com.squareup.retrofit2:converter-gson:${Versions.Network.gson}")

    // Room
    implementation("androidx.room:room-runtime:${Versions.Local.room}")
    annotationProcessor("androidx.room:room-compiler:${Versions.Local.room}")
    ksp("androidx.room:room-compiler:${Versions.Local.room}")
    implementation("androidx.room:room-ktx:${Versions.Local.room}")

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
