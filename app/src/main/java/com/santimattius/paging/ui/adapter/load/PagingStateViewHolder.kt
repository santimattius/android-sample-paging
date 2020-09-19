package com.santimattius.paging.ui.adapter.load

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.santimattius.paging.databinding.ItemPagingStateBinding

class PagingStateViewHolder(private val viewBinding: ItemPagingStateBinding) :
    RecyclerView.ViewHolder(viewBinding.root) {

    fun bind(loadState: LoadState) {
        viewBinding.progressBar.isVisible = loadState is LoadState.Loading
    }

    companion object {

        fun from(parent: ViewGroup): PagingStateViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val viewBinding = ItemPagingStateBinding.inflate(inflater, parent, false)
            return PagingStateViewHolder(viewBinding)
        }
    }
}