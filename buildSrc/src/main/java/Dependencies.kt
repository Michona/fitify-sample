object Plugins {
    const val androidApplication = "com.android.application"
    const val androidLibrary = "com.android.library"
    const val javaLibrary = "java-library"
    const val kotlinAndroid = "kotlin-android"
    const val kotlin = "kotlin"
}

object Modules {
    const val app = ":app"
}

object ProjectDependencies {
    const val androidGradlePlugin = "com.android.tools.build:gradle:${Versions.androidGradlePlugin}"
    const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
}

object Versions {
    const val androidGradlePlugin = "7.4.2"
    const val kotlin = "1.8.10"

    object Android {
        const val targetSdk = 33
        const val minSdk = 24

        object Compose {
            const val ui = "1.4.1"
            const val activity = "1.7.0"
            const val navigation = "2.5.3"
        }

        const val lifecycleKtx = "2.6.1"
        const val coreKtx = "1.10.0"
        const val material = "1.4.1"
    }

    object Network {
        const val retrofit = "2.9.0"
        const val okHttp = "4.7.2"
        const val gson = "2.9.0"
    }

    object Local {
        const val room = "2.5.1"
    }

    object Test {
        const val junit = "4.13.2"
        const val mockk = "1.13.4"
    }

    const val koin = "3.4.0"
    const val coroutines = "1.6.4"
}
