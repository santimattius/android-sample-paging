package com.santimattius.paging.data.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.santimattius.paging.data.datasources.factory.TvPagingSourceFactory
import com.santimattius.paging.domain.Tv
import kotlinx.coroutines.flow.Flow

class TvShowRepository(private val factory: TvPagingSourceFactory) {

    fun fetchPopularTvShows(): Flow<PagingData<Tv>> {
        return Pager(
            initialKey = INITIAL_PAGE,
            config = PagingConfig(
                pageSize = PAGE_SIZE
            ),
            pagingSourceFactory = { factory.create() }
        ).flow
    }

    companion object {
        private const val INITIAL_PAGE = 1
        private const val PAGE_SIZE = 10
    }
}