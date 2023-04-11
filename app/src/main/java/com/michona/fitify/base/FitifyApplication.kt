package com.michona.fitify.base

import android.app.Application
import coil.ImageLoader
import coil.ImageLoaderFactory
import com.michona.fitify.BuildConfig
import com.michona.fitify.appModule
import com.michona.fitify.domain.local.localModule
import com.michona.fitify.domain.remote.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

class FitifyApplication : Application(), ImageLoaderFactory {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())

        startKoin {
            if (BuildConfig.DEBUG) androidLogger()
            androidContext(this@FitifyApplication)
            modules(appModule, networkModule, localModule)
        }
    }

    override fun newImageLoader(): ImageLoader {
        return ImageLoader.Builder(this)
            .crossfade(true)
            .build()
    }
}
