package com.semnan.semnanuniversity.screens

import android.widget.ProgressBar
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.semnan.semnanuniversity.R
import com.semnan.semnanuniversity.components.SearchBox
import com.semnan.semnanuniversity.components.NumberCard
import com.semnan.semnanuniversity.data.model.Number
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
                        if (isFilterVisible)
                            Icon(Icons.Default.Clear, contentDescription = "Clear search")
                        else
                            Icon(Icons.Default.Search, contentDescription = "Search products")
                    }
                },
                scrollBehavior = scrollBehavior,
            )
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier.padding(paddingValues)
        ) {

            AnimatedVisibility(visible = isFilterVisible) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterHorizontally)
                        .padding(top = 6.dp)
                ) {
                    SearchBox(
                        jobNameQuery,
                        onValueChanged = { viewModel.setJobNameQuery(it) },
                        isFilterVisible = isFilterVisible,
                        label = "جستجو بر اساس نام شغل",
                        placeholder = "کارشناس فناوری اطلاعات"
                    )
                    SearchBox(
                        subunitQuery,
                        onValueChanged = { viewModel.setSubunitQuery(it) },
                        isFilterVisible = isFilterVisible,
                        label = "جستجو بر اساس نام زیر واحد",
                        placeholder = "کارشناس فناوری اطلاعات"
                    )
                    SearchBox(
                        numberQuery,
                        onValueChanged = { viewModel.setNumberQuery(it) },
                        isFilterVisible = isFilterVisible,
                        label = "جستجو بر اساس شماره",
                        placeholder = "35566006"
                    )
                    SearchBox(
                        addressQuery,
                        onValueChanged = { viewModel.setAddressQuery(it) },
                        isFilterVisible = isFilterVisible,
                        label = "جستجو بر اساس آدرس",
                        placeholder = "پردیس 1"
                    )
                }
            }

            if (numbers.loadState.append is LoadState.Loading){
                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.BottomCenter) {
                    CircularProgressIndicator()
                }
            }

            when {
                numbers.loadState.refresh is LoadState.Loading -> {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator()
                    }
                }

                numbers.loadState.refresh is LoadState.Error -> {
                    val error = (numbers.loadState.refresh as LoadState.Error).error
                    HandleError(error = error, numbers = numbers)
                }

                else -> {
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