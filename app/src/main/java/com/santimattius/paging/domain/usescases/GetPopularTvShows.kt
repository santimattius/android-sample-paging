package com.santimattius.paging.domain.usescases

import com.santimattius.paging.domain.repositories.TvShowRepository

class GetPopularTvShows(private val repository: TvShowRepository) {

    operator fun invoke() = repository.fetchPopularTvShows()
}