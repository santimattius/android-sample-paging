package com.santimattius.moviedb.extensions

import retrofit2.Retrofit

internal inline fun <reified T> Retrofit.get(): T {
    return this.create(T::class.java)
}