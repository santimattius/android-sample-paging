package com.santimattius.moviedb.network.service

import com.santimattius.moviedb.TheMovieDbClientImpl
import com.santimattius.moviedb.network.model.Response
import com.santimattius.moviedb.network.model.Tv
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TvService {

    @GET("/{version}/tv/popular")
    suspend fun getPopular(
        @Path("version") version: Int = TheMovieDbClientImpl.DEFAULT_VERSION,
        @Query("page") page: Int
    ): Response<Tv>
}
