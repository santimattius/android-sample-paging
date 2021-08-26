package com.santimattius.paging.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import com.santimattius.paging.databinding.FragmentPopularTvShowsBinding
import com.santimattius.paging.ui.adapter.item.PopularTvShowsAdapter
import com.santimattius.paging.ui.adapter.load.PagingStateAdapter
import com.santimattius.paging.ui.viewmodels.PopularTvShowsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class PopularTvShowsFragment : Fragment() {

    private lateinit var viewBinding: FragmentPopularTvShowsBinding
    private val popularTvShowsAdapter: PopularTvShowsAdapter by lazy {
        PopularTvShowsAdapter()
    }

    private val viewModel: PopularTvShowsViewModel by viewModel()


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        viewBinding = FragmentPopularTvShowsBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding.swipeRefreshLayout.setOnRefreshListener {
            popularTvShowsAdapter.refresh()
        }

        //setup loading state listener
        popularTvShowsAdapter.addLoadStateListener {
            val isRefreshing = it.refresh is LoadState.Loading
            viewBinding.popularTvShows.isInvisible = isRefreshing
            viewBinding.progressBar.isVisible = isRefreshing
        }

        // apply paging indicator
        val adapterWithFooter = popularTvShowsAdapter.withLoadStateFooter(
                footer = PagingStateAdapter()
        )

        val gridLayoutManager = GridLayoutManager(context, DEFAULT_SPAN)
        gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                val itemViewType = adapterWithFooter.getItemViewType(position)
                val isItem = itemViewType == 0
                return if (isItem) ITEM_SPAN else DEFAULT_SPAN
            }

        }

        with(viewBinding.popularTvShows) {
            this.layoutManager = gridLayoutManager
            this.adapter = adapterWithFooter
        }

        viewModel.movies.observe(viewLifecycleOwner) {
            viewBinding.swipeRefreshLayout.isRefreshing = false
            popularTvShowsAdapter.submitData(lifecycle, it)
        }

    }

    companion object {
        private const val DEFAULT_SPAN = 2
        private const val ITEM_SPAN = 1
    }

}