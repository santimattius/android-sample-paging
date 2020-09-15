package com.santimattius.paging.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.santimattius.paging.data.repositories.TvShowRepository
import com.santimattius.paging.ui.adapter.item.PopularTvShowUiModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class PopularTvShowsViewModel(private val repository: TvShowRepository) : ViewModel() {

    private val _movies = MutableLiveData<PagingData<PopularTvShowUiModel>>()
    val movies: LiveData<PagingData<PopularTvShowUiModel>>
        get() = _movies

    init {
        fetch()
    }

    fun fetch() {
        viewModelScope.launch {
            repository.fetchPopularTvShows()
                .cachedIn(viewModelScope)
                .map { pagingList ->
                    pagingList.map {
                        PopularTvShowUiModel(
                            it.id,
                            "https://image.tmdb.org/t/p/w500${it.posterPath}"
                        )
                    }
                }.collectLatest {
                    _movies.postValue(it)
                }
        }
    }

}