package com.santimattius.moviedb.network.service

import com.santimattius.moviedb.TheMovieDbClientImpl
import com.santimattius.moviedb.network.model.Movie
import com.santimattius.moviedb.network.model.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesService {

    @GET("/{version}/movie/popular")
    suspend fun getPopular(
        @Path("version") version: Int = TheMovieDbClientImpl.DEFAULT_VERSION,
        @Query("page") page: Int
    ): Response<Movie>
}
