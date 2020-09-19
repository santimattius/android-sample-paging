package com.santimattius.paging.ui.adapter.item

import androidx.recyclerview.widget.DiffUtil

object TvDiff : DiffUtil.ItemCallback<PopularTvShowUiModel>() {
    override fun areItemsTheSame(
        oldItem: PopularTvShowUiModel,
        newItem: PopularTvShowUiModel
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: PopularTvShowUiModel,
        newItem: PopularTvShowUiModel
    ): Boolean {
        return oldItem == newItem
    }

}