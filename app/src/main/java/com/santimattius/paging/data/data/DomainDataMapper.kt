package com.santimattius.paging.data.data

import com.santimattius.paging.domain.entities.Tv
import com.santimattius.moviedb.network.model.Tv as NetworkTv


fun NetworkTv.asDomainModel(): Tv {
    return Tv(
        id,
        firstAirDate,
        overview,
        originalLanguage,
        genreIds,
        posterPath,
        originCountry,
        backdropPath,
        originalName,
        popularity,
        voteAverage,
        name,
        voteCount
    )
}
