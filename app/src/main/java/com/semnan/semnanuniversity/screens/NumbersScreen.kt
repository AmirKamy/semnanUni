package com.semnan.semnanuniversity.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.semnan.semnanuniversity.R
import com.semnan.semnanuniversity.components.FacultyCard
import com.semnan.semnanuniversity.components.FilterWidget
import com.semnan.semnanuniversity.components.NumberCard
import com.semnan.semnanuniversity.data.model.Number
import com.semnan.semnanuniversity.data.model.NumberFilters
import com.semnan.semnanuniversity.network.Resource
import com.semnan.semnanuniversity.viewmodel.MainViewModel
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun NumbersScreen(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = hiltViewModel(),
) {

    val isFilterVisible = viewModel.isFilterShowing.collectAsState().value

    val jobNameQuery = viewModel.jobNameQuery.collectAsState().value
    val subunitQuery = viewModel.subunitQuery.collectAsState().value
    val numberQuery = viewModel.numberQuery.collectAsState().value
    val addressQuery = viewModel.addressQuery.collectAsState().value

    val numbers: LazyPagingItems<Number> = viewModel.numbersPager.collectAsLazyPagingItems()
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())


    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(stringResource(id = R.string.numbers))
                },
                actions = {
                    IconButton(
                        onClick = {
                            viewModel.toggleIsSearchShowing()
                        },
                    ) {
                        Icon(Icons.Default.Search, contentDescription = "Search products")
                    }
                },
                scrollBehavior = scrollBehavior,
            )
        }
    ) { paddingValues ->

        Box(
            modifier = Modifier.padding(paddingValues)
        ) {

            when (numbers.loadState.refresh) {
                LoadState.Loading -> {

                }

                is LoadState.Error -> {
                    val error = (numbers.loadState.refresh as LoadState.Error).error
                    HandleError(error = error, numbers = numbers)
                }

                else -> {

                    Column {
                        AnimatedVisibility(visible = isFilterVisible) {
                            FilterWidget(
                                NumberFilters(
                                    jobNameQuery,
                                    subunitQuery,
                                    numberQuery,
                                    addressQuery
                                ),
                                onJobNameChanged = { viewModel.setJobNameQuery(it) },
                                onSubunitChanged = { viewModel.setSubunitQuery(it) },
                                onNumberChanged = { viewModel.setNumberQuery(it) },
                                onAddressChanged = { viewModel.setAddressQuery(it) }
                            )
                        }
                        LazyColumn(
                            modifier = Modifier.fillMaxSize(),
                            horizontalAlignment = Alignment.Start,
                            verticalArrangement = Arrangement.Top,
                        ) {
                            items(count = numbers.itemCount) { index ->
                                val number: Number = numbers[index] ?: return@items
                                NumberCard(number)
                            }
                        }
                    }


                }
            }
        }
    }
}

@Composable
fun HandleError(error: Throwable, numbers: LazyPagingItems<Number>) {
    val errorText =
        if (error is IOException) "لطفا اتصال خود به اینترنت را چک کنید" else if (error is HttpException) error.response()
            ?.errorBody().toString() else error.message.toString()
    AlertDialog(
        icon = {
            Icon(Icons.Default.Warning, contentDescription = "Error Icon")
        },
        title = {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "خطا")
            }

        },
        text = {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = errorText)
            }
        },
        onDismissRequest = {

        },
        confirmButton = {
            TextButton(
                onClick = {
                    numbers.refresh()
                }
            ) {
                Text("تلاش دوباره")
            }
        },
    )
}