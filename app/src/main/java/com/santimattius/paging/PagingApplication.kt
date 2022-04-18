package com.santimattius.paging

import android.app.Application
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import com.santimattius.paging.di.moduleDefinitions
import com.santimattius.paging.ui.style.PagingComposeTheme
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
            modules(moduleDefinitions)
        }
    }
}

@Composable
fun PagingApplication(content: @Composable () -> Unit) {
    PagingComposeTheme {
        Surface(color = MaterialTheme.colors.background) {
            content()
        }
    }
}