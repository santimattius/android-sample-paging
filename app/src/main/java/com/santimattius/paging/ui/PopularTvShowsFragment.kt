package com.santimattius.paging.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.santimattius.paging.databinding.FragmentPopularTvShowsBinding
import com.santimattius.paging.ui.adapter.item.PopularTvShowsAdapter
import com.santimattius.paging.ui.adapter.load.PagingStateAdapter
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.scope.viewModel
import org.koin.androidx.scope.lifecycleScope as KoinScope

class PopularTvShowsFragment : Fragment() {

    private lateinit var viewBinding: FragmentPopularTvShowsBinding
    private val popularTvShowsAdapter: PopularTvShowsAdapter by lazy {
        PopularTvShowsAdapter()
    }
    private val viewModel: PopularTvShowsViewModel by KoinScope.viewModel(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewBinding = FragmentPopularTvShowsBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.fetch()
        }
        val adapterWithFooter = popularTvShowsAdapter.withLoadStateFooter(
            footer = PagingStateAdapter()
        )

        val gridLayoutManager = GridLayoutManager(context, 2)
        gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                val itemViewType = adapterWithFooter.getItemViewType(position)
                return if (itemViewType == 0) {
                    1
                } else {
                    2
                }
            }

        }

        with(viewBinding.popularTvShows) {
            this.layoutManager = gridLayoutManager
            this.adapter = adapterWithFooter
        }

        viewModel.movies.observe(viewLifecycleOwner) {
            viewBinding.swipeRefreshLayout.isRefreshing = false
            lifecycleScope.launch {
                popularTvShowsAdapter.submitData(it)
            }
        }

    }

}