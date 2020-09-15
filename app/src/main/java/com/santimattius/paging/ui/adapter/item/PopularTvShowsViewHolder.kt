package com.santimattius.paging.ui.adapter.item

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.santimattius.paging.databinding.ItemPopularTvShowBinding
import com.santimattius.paging.ui.setImageUrl

class PopularTvShowsViewHolder(private val viewBinding: ItemPopularTvShowBinding) :
    RecyclerView.ViewHolder(viewBinding.root) {


    fun bind(item: PopularTvShowUiModel) {
        viewBinding.imageTvShow.setImageUrl(item.imageUrl)
    }

    companion object {

        fun from(parent: ViewGroup): PopularTvShowsViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val viewBinding = ItemPopularTvShowBinding.inflate(inflater, parent, false)
            return PopularTvShowsViewHolder(viewBinding)
        }
    }
}