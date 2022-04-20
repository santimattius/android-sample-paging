package com.santimattius.paging.di

import com.santimattius.moviedb.TheMovieDbClient
import com.santimattius.paging.BuildConfig
import com.santimattius.paging.data.datasources.TvPagingSource
import com.santimattius.paging.data.datasources.factory.TvPagingSourceFactory
import com.santimattius.paging.data.repositories.TvShowRepository
import com.santimattius.paging.ui.screen.PopularTvShowsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module


private const val API_KEY_NAMED = "api_key"
private const val BASE_URL_NAMED = "base_url"

private val theMovieDBModule = module {
    single(named(API_KEY_NAMED)) { BuildConfig.API_KEY }
    single(named(BASE_URL_NAMED)) { "https://api.themoviedb.org" }
    single<TheMovieDbClient> {
        TheMovieDbClient.factory(
            get(named(BASE_URL_NAMED)),
            get(named(API_KEY_NAMED))
        )
    }
}


private val appModule = module {
    factory { TvPagingSource(get()) }
    single { TvPagingSourceFactory(get()) }
}

private val dataModule = module {
    factory<TvShowRepository> { TvShowRepository(get()) }
}

private val ui = module {
    viewModel { PopularTvShowsViewModel(get()) }
}


internal val moduleDefinitions =
    listOf(appModule, dataModule, ui, theMovieDBModule)