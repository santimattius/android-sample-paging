package com.santimattius.paging.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.santimattius.paging.domain.usescases.GetPopularTvShows
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PopularTvShowsViewModel(private val getPopularTvShows: GetPopularTvShows) : ViewModel() {

    val tvShows: Flow<PagingData<PopularTvShowUiModel>>
        get() = getPopularTvShows()
            .cachedIn(viewModelScope)
            .map { pagingList ->
                pagingList.map {
                    PopularTvShowUiModel(
                        id = it.id,
                        title = it.name,
                        imageUrl = "$BASE_IMAGE_URL${it.posterPath}"
                    )
                }
            }

    companion object {
        private const val BASE_IMAGE_URL = "https://image.tmdb.org/t/p/w500"
    }
}