package com.santimattius.paging.data.datasources.factory

import com.santimattius.moviedb.TheMovieDbClient
import com.santimattius.paging.data.datasources.TvPagingSource

class TvPagingSourceFactory(private val client: TheMovieDbClient) {

    fun create() = TvPagingSource(client)
}