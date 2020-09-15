package com.santimattius.paging.ui.adapter.item

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter

class PopularTvShowsAdapter :
    PagingDataAdapter<PopularTvShowUiModel, PopularTvShowsViewHolder>(TvDiff) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularTvShowsViewHolder {
        return PopularTvShowsViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: PopularTvShowsViewHolder, position: Int) {
        val item = getItem(position) ?: return
        holder.bind(item)
    }
}

