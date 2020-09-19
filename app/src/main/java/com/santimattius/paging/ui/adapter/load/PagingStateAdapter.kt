package com.santimattius.paging.ui.adapter.load

import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter

class PagingStateAdapter : LoadStateAdapter<PagingStateViewHolder>() {


    override fun onBindViewHolder(holder: PagingStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): PagingStateViewHolder {
        return PagingStateViewHolder.from(parent)
    }
}


