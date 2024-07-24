package com.semnan.semnanuniversity.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument


sealed class Screen(
    val route: String,
    val navArguments: List<NamedNavArgument> = emptyList()
) {

    data object HomeScreen : Screen("home")

    data object WebViewScreen : Screen(
        route = "webviewscreen/{url}",
        navArguments = listOf(navArgument("url") {
            type = NavType.StringType
        })
    ) {
        fun createRoute(url: String) = "webviewscreen/${url}"
    }

}