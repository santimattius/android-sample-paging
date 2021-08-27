package com.santimattius.paging.ui.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyGridScope
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.santimattius.paging.R
import com.santimattius.paging.ui.state.ErrorItem
import com.santimattius.paging.ui.state.LoadingItem
import com.santimattius.paging.ui.viewmodels.PopularTvShowUiModel
import com.santimattius.paging.ui.viewmodels.PopularTvShowsViewModel
import kotlinx.coroutines.flow.Flow

@ExperimentalFoundationApi
@Composable
fun HomeScreen(viewModel: PopularTvShowsViewModel) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(id = R.string.app_name)) }
            )
        },
        content = {
            TvShowGrid(data = viewModel.tvShows)
        }
    )
}

@ExperimentalCoilApi
@ExperimentalFoundationApi
@Composable
fun TvShowGrid(data: Flow<PagingData<PopularTvShowUiModel>>) {

    val popularTvShows = data.collectAsLazyPagingItems()

    LazyVerticalGrid(cells = GridCells.Fixed(count = 2)) {
        items(popularTvShows.itemCount) { index ->
            popularTvShows[index]?.let {
                Image(
                    painter = rememberImagePainter(
                        data = it.imageUrl,
                        builder = {
                            placeholder(R.drawable.ic_photo)
                        }
                    ),
                    contentDescription = it.title,
                    modifier = Modifier
                        .padding(all = 6.dp)
                        .aspectRatio(ratio = 0.67f),
                    contentScale = ContentScale.Crop
                )
            }
            LazyGridState(popularTvShows)
        }
    }
}

@ExperimentalFoundationApi
@Composable
private fun LazyGridScope.LazyGridState(popularTvShows: LazyPagingItems<PopularTvShowUiModel>) {
    with(popularTvShows) {
        when {
            loadState.refresh is LoadState.Loading -> {
                item { LoadingItem() }
                item { LoadingItem() }
            }
            loadState.append is LoadState.Loading -> {
                item { LoadingItem() }
                item { LoadingItem() }
            }
            loadState.refresh is LoadState.Error -> {
                val e = popularTvShows.loadState.refresh as LoadState.Error
                item {
                    ErrorItem(
                        message = e.error.localizedMessage!!,
                        modifier = Modifier.fillParentMaxSize(),
                        onClickRetry = { retry() }
                    )
                }
            }
            loadState.append is LoadState.Error -> {
                val e = popularTvShows.loadState.append as LoadState.Error
                item {
                    ErrorItem(
                        message = e.error.localizedMessage!!,
                        onClickRetry = { retry() }
                    )
                }
            }
        }
    }
}