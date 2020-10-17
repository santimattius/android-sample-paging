package com.santimattius.paging.domain.repositories

import androidx.paging.PagingData
import com.santimattius.paging.domain.entities.Tv
import kotlinx.coroutines.flow.Flow

interface TvShowRepository {
    fun fetchPopularTvShows(): Flow<PagingData<Tv>>
}