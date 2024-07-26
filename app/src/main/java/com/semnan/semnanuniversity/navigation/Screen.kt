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


    data object FacultyScreen : Screen(
        route = "faculty_screen/{faculty}",
        navArguments = listOf(navArgument("faculty") {
            type = NavType.StringType
        })
    ) {
        fun createRoute(faculty: String) = "faculty_screen/${faculty}"
    }

    data object FacultiesScreen : Screen("faculties_screen")

}