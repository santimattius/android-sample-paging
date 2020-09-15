package com.santimattius.moviedb

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.santimattius.moviedb.extensions.get
import com.santimattius.moviedb.interceptors.RequestInterceptor
import com.santimattius.moviedb.network.model.Movie
import com.santimattius.moviedb.network.model.Response
import com.santimattius.moviedb.network.model.Tv
import com.santimattius.moviedb.network.service.MoviesService
import com.santimattius.moviedb.network.service.TvService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

internal class TheMovieDbClientImpl(baseUrl: String, apiKey: String) : TheMovieDbClient {

    companion object {
        const val DEFAULT_VERSION = 3
    }

    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val client = OkHttpClient().newBuilder()
        .addInterceptor(RequestInterceptor(apiKey))
        .build()

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    }

    private val tvService: TvService by lazy { create() }
    private val movieService: MoviesService by lazy { create() }

    private inline fun <reified T> create(): T {
        return retrofit.get()
    }

    override suspend fun getTvPopular(page: Int): Response<Tv> {
        return tvService.getPopular(version = DEFAULT_VERSION, page = page)
    }

    override suspend fun getMoviePopular(page: Int): Response<Movie> {
        return movieService.getPopular(version = DEFAULT_VERSION, page = page)
    }
}


