package com.santimattius.paging.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.santimattius.paging.domain.usescases.GetPopularTvShows
import com.santimattius.paging.ui.adapter.item.PopularTvShowUiModel
import kotlinx.coroutines.flow.map

class PopularTvShowsViewModel(private val getPopularTvShows: GetPopularTvShows) : ViewModel() {

    val movies: LiveData<PagingData<PopularTvShowUiModel>>
        get() = getPopularTvShows()
            .cachedIn(viewModelScope)
            .map { pagingList ->
                pagingList.map {
                    PopularTvShowUiModel(
                        id = it.id,
                        imageUrl = "$BASE_IMAGE_URL${it.posterPath}"
                    )
                }
            }.asLiveData()

    companion object {
        private const val BASE_IMAGE_URL = "https://image.tmdb.org/t/p/w500"
    }

}