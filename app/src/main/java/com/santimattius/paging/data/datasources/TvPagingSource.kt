package com.santimattius.paging.data.datasources

import androidx.paging.PagingSource
import com.santimattius.moviedb.TheMovieDbClient
import com.santimattius.paging.data.data.asDomainModel
import com.santimattius.paging.domain.entities.Tv
import kotlinx.coroutines.delay

class TvPagingSource(private val client: TheMovieDbClient) : PagingSource<Int, Tv>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Tv> {
        return try {
            val nextPage = params.key ?: 1
            val response = client.getTvPopular(nextPage)
            delay(2000) //delay to show paging indicator
            LoadResult.Page(
                data = response.results.map { it.asDomainModel() },
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = response.page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}