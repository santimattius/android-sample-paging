package com.santimattius.paging.domain

data class Tv(
    val id: Int,
    val firstAirDate: String,
    val overview: String,
    val originalLanguage: String,
    val genreIds: List<Int>,
    val posterPath: String?,
    val originCountry: List<String>,
    val backdropPath: String?,
    val originalName: String,
    val popularity: Double,
    val voteAverage: Double,
    val name: String,
    val voteCount: Int
)