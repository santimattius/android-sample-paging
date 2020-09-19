package com.santimattius.paging

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class PagingApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initDI()
    }

    private fun Application.initDI() {
        startKoin {
            androidContext(this@initDI)
            modules(com.santimattius.paging.di.modules)
        }
    }
}