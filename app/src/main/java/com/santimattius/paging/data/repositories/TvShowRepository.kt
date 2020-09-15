package com.santimattius.paging.data.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.santimattius.paging.data.source.TvPagingSource
import com.santimattius.paging.domain.Tv
import kotlinx.coroutines.flow.Flow

class TvShowRepository(private val dataSource: TvPagingSource) {

    fun fetchPopularTvShows(): Flow<PagingData<Tv>> {
        return Pager(
            initialKey = 1,
            config = PagingConfig(
                pageSize = 10
            ),
            pagingSourceFactory = { dataSource }
        ).flow
    }
}