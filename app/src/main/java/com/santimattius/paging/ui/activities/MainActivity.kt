package com.santimattius.paging.ui.activities

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.ExperimentalFoundationApi
import com.santimattius.paging.ui.screen.HomeScreen
import com.santimattius.paging.ui.style.PagingComposeTheme
import com.santimattius.paging.ui.viewmodels.PopularTvShowsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

@ExperimentalFoundationApi
class MainActivity : AppCompatActivity() {

    private val viewModel: PopularTvShowsViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PagingComposeTheme {
                HomeScreen(viewModel = viewModel)
            }
        }
    }
}