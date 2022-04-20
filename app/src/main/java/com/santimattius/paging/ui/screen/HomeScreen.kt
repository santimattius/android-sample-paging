package com.santimattius.paging.ui.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import coil.annotation.ExperimentalCoilApi
import coil.compose.SubcomposeAsyncImage
import com.santimattius.paging.R
import com.santimattius.paging.ui.components.LoadingImageEffect
import com.santimattius.paging.ui.components.LoadingShimmerEffect
import kotlinx.coroutines.flow.Flow
import org.koin.androidx.compose.getViewModel

private const val SPAN_COUNT = 2

@ExperimentalCoilApi
@ExperimentalFoundationApi
@Composable
fun HomeRoute(
    viewModel: PopularTvShowsViewModel = getViewModel()
) {
    HomeScreen(viewModel.tvShows)
}

@ExperimentalCoilApi
@ExperimentalFoundationApi
@Composable
private fun HomeScreen(data: Flow<PagingData<PopularTvShowUiModel>>) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(id = R.string.app_name)) }
            )
        },
        content = { padding ->
            TvShowGrid(
                data = data,
                modifier = Modifier.padding(padding)
            )
        }
    )
}

@ExperimentalCoilApi
@Composable
fun TvShowGrid(
    data: Flow<PagingData<PopularTvShowUiModel>>,
    modifier: Modifier = Modifier
) {

    val popularTvShows = data.collectAsLazyPagingItems()

    LazyVerticalGrid(
        columns = GridCells.Fixed(SPAN_COUNT),
        contentPadding = PaddingValues(dimensionResource(R.dimen.x_small)),
        modifier = modifier
    ) {
        items(popularTvShows.itemCount) { index ->
            popularTvShows[index]?.let {
                TvShowCard(item = it)
            }

        }
        if (popularTvShows.loadState.append == LoadState.Loading) {
            items(SPAN_COUNT) {
                LoadingShimmerEffect()
            }
        }
    }
    if (popularTvShows.loadState.refresh == LoadState.Loading) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(SPAN_COUNT),
            contentPadding = PaddingValues(dimensionResource(R.dimen.x_small)),
            modifier = modifier
        ) {
            items(3 * SPAN_COUNT) {
                LoadingShimmerEffect()
            }
        }
    }
}

@ExperimentalCoilApi
@Composable
fun TvShowCard(item: PopularTvShowUiModel, modifier: Modifier = Modifier) {
    Card(modifier = modifier.padding(dimensionResource(R.dimen.small))) {
        SubcomposeAsyncImage(
            model = item.imageUrl,
            loading = {
                LoadingImageEffect()
            },
            contentDescription = item.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(ratio = 0.67f),
        )
    }
}
