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
        route = "faculty_screen/{faculty}/{come_from}",
        navArguments = listOf(navArgument("faculty") {
            type = NavType.StringType
        }, navArgument("come_from") {
            type = NavType.StringType
        }
            /*
           This argument specifies that we should load items related to faculties or MoavenatHa.
           pass MainItemEnums.Faculties.name for Faculty Screen or MainItemEnums.MoavenatHa.name for MoavenatHa
             */
        )
    ) {
        fun createRoute(faculty: String, comeFrom: String) = "faculty_screen/${faculty}/${comeFrom}"
    }

    data object FacultiesScreen : Screen(
        route = "faculties_screen/{come_from}",
        navArguments = listOf(navArgument("come_from") {
            type = NavType.StringType
        })
    ) {
        fun createRoute(comeFrom: String) = "faculties_screen/${comeFrom}"
    }

    data object MapScreen : Screen(
        route = "map_screen/{image_id}",
        navArguments = listOf(navArgument("image_id") {
            type = NavType.IntType
        })
    ) {
        fun createRoute(imageId: Int) = "map_screen/${imageId}"
    }

    data object NumberScreen : Screen(route = "number_screen"){
        fun createRoute() = "number_screen"
    }


}