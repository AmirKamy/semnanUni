package com.semnan.semnanuniversity.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.semnan.semnanuniversity.R
import com.semnan.semnanuniversity.components.ImageCard
import com.semnan.semnanuniversity.navigation.Screen
import com.semnan.semnanuniversity.viewmodel.MainViewModel
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = hiltViewModel(),
    onMainItemClick: (String) -> Unit = {}
) {

    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            HomeTopAppBar(
                scrollBehavior = scrollBehavior,
                onMainItemClick = onMainItemClick
            )
        }
    ) { contentPadding ->
        HomePagerScreen(
            onPlantClick = onMainItemClick,
            Modifier.padding(top = contentPadding.calculateTopPadding()),
            viewModel
        )
    }


}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomePagerScreen(
    onPlantClick: (String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = hiltViewModel()
) {

    MyGrid(modifier = modifier, viewModel, onPlantClick)


}

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
private fun HomeTopAppBar(
    scrollBehavior: TopAppBarScrollBehavior,
    modifier: Modifier = Modifier,
    onMainItemClick: (String) -> Unit
) {
    TopAppBar(
        title = {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(end = 2.dp, bottom = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Button(onClick = { goToWebViewScreen("https://my.semnan.ac.ir/", onMainItemClick) },
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                    contentPadding = PaddingValues(
                        start = 10.dp,
                        top = 0.dp,
                        end = 10.dp,
                        bottom = 0.dp
                    ),
                    modifier = Modifier.padding(top = 2.dp)
                ) {
                    Text(text = "ورود به سامانه مای", modifier = Modifier, style = TextStyle(color = MaterialTheme.colorScheme.onPrimary, fontSize = 12.sp))
                    Icon(
                        painter = painterResource(id = R.drawable.profile),
                        contentDescription = "My",
                        modifier = Modifier
                            .width(20.dp)
                            .height(20.dp)
                            .clickable {
                                goToWebViewScreen("https://my.semnan.ac.ir/", onMainItemClick)
                            }
                    )
                }
                

                Text(
                    modifier = Modifier.padding(10.dp),
                    text = stringResource(id = R.string.semnan_uni),
                    style = MaterialTheme.typography.titleLarge
                )
            }
        },
        modifier = modifier,
        scrollBehavior = scrollBehavior
    )
}


@Composable
fun MyGrid(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = hiltViewModel(),
    onItemClick: (String) -> Unit
) {
    val mainItems = viewModel.mainItems.collectAsState().value
    viewModel.getMainItems()

    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Fixed(2)
    ) {
        items(mainItems) { item ->
            ImageCard(item) { itemId ->
                navigateToNewPage(itemId, onItemClick)
            }
        }
    }
}

fun navigateToNewPage(itemId: String, onItemClick: (String) -> Unit) {
    when (itemId) {
        "ostad_ha" -> {
            goToWebViewScreen("https://profile.semnan.ac.ir/", onItemClick)
        }
        "tour" -> {
            goToWebViewScreen("https://semnan.ac.ir/uploads/1/2019/tour/index.html", onItemClick)
        }
        "reshte_ha" -> {
            goToWebViewScreen("https://liststudyfields.semnan.ac.ir/", onItemClick)
        }
        "news" -> {
            goToWebViewScreen("https://semnan.ac.ir/%D9%87%D9%85%D9%87-%D8%A7%D8%AE%D8%A8%D8%A7%D8%B1", onItemClick)
        }
        "shmare_ha" -> {
            goToWebViewScreen("https://118.semnan.ac.ir/", onItemClick)
        }
        "dastavard" -> {
            goToWebViewScreen("https://semnan.ac.ir/%D8%A7%D9%81%D8%AA%D8%AE%D8%A7%D8%B1%D8%A7%D8%AA-%D8%AF%D8%A7%D9%86%D8%B4%DA%AF%D8%A7%D9%87", onItemClick)
        }
        "book" -> {
            goToWebViewScreen("https://simorgh.semnan.ac.ir/", onItemClick)
        }
        "intro_to_semnan_uni" -> {
            goToWebViewScreen("https://semnan.ac.ir/history", onItemClick)
        }
        "intro_to_semnan_city" -> {
            goToWebViewScreen("https://semnan.ac.ir/%D8%AF%D8%B1%D8%A8%D8%A7%D8%B1%D9%87-%D8%B4%D9%87%D8%B1-%D8%B3%D9%85%D9%86%D8%A7%D9%86", onItemClick)
        }
        "daneshkade_ha" -> {
            onItemClick(Screen.FacultiesScreen.route)
        }



    }
}

private fun goToWebViewScreen(url: String, onItemClick: (String) -> Unit){
    val encodedUrl = URLEncoder.encode(url, StandardCharsets.UTF_8.toString())
    onItemClick(Screen.WebViewScreen.createRoute(encodedUrl))
}
