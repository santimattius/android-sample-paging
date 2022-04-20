package com.santimattius.paging.data.data

import com.santimattius.paging.domain.Tv
import com.santimattius.moviedb.network.model.Tv as NetworkTv

fun NetworkTv.asDomainModel(): Tv {
    return Tv(
        id,
        firstAirDate,
        overview,
        originalLanguage,
        genreIds,
        poster,
        originCountry,
        backdropPath,
        originalName,
        popularity,
        voteAverage,
        name,
        voteCount
    )
}
