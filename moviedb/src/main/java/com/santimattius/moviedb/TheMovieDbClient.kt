package com.santimattius.moviedb

import com.santimattius.moviedb.network.model.Movie
import com.santimattius.moviedb.network.model.Response
import com.santimattius.moviedb.network.model.Tv

interface TheMovieDbClient {

    suspend fun getMoviePopular(page: Int): Response<Movie>

    suspend fun getTvPopular(page: Int): Response<Tv>

    companion object {

        fun factory(baseUrl: String, key: String): TheMovieDbClient =
            TheMovieDbClientImpl(baseUrl, key)
    }
}