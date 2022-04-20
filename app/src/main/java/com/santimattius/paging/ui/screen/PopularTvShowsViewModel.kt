package com.santimattius.paging.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.santimattius.paging.data.repositories.TvShowRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PopularTvShowsViewModel(private val repository: TvShowRepository) : ViewModel() {

    val tvShows: Flow<PagingData<PopularTvShowUiModel>>
        get() = repository.fetchPopularTvShows()
            .cachedIn(viewModelScope)
            .map { pagingList ->
                pagingList.map {
                    PopularTvShowUiModel(
                        id = it.id,
                        title = it.name,
                        imageUrl = it.poster
                    )
                }
            }
}