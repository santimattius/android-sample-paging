package com.santimattius.paging.ui.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.santimattius.paging.R
import kotlinx.coroutines.flow.Flow
import org.koin.androidx.compose.getViewModel

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
@ExperimentalFoundationApi
@Composable
fun TvShowGrid(
    data: Flow<PagingData<PopularTvShowUiModel>>,
    modifier: Modifier = Modifier
) {

    val popularTvShows = data.collectAsLazyPagingItems()

    LazyVerticalGrid(
        columns = GridCells.Adaptive(dimensionResource(R.dimen.item_min_width)),
        contentPadding = PaddingValues(dimensionResource(R.dimen.x_small)),
        modifier = modifier
    ) {
        items(popularTvShows.itemCount) { index ->
            popularTvShows[index]?.let {
                TvShowCard(item = it)
            }

        }
        if (popularTvShows.loadState.append == LoadState.Loading) {
            items(2) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentWidth(Alignment.CenterHorizontally)
                )
                CircularProgressIndicator(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentWidth(Alignment.CenterHorizontally)
                )
            }
        }
    }
    if (popularTvShows.loadState.refresh == LoadState.Loading) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator()
        }
    }
}

@ExperimentalCoilApi
@Composable
fun TvShowCard(item: PopularTvShowUiModel, modifier: Modifier = Modifier) {
    Card(modifier = modifier.padding(dimensionResource(R.dimen.small))) {
        Image(
            painter = rememberImagePainter(data = item.imageUrl),
            contentDescription = item.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.LightGray)
                .aspectRatio(ratio = 0.67f),
        )
    }
}
