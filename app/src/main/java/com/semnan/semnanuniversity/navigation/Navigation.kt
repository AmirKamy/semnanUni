package com.semnan.semnanuniversity.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.Navigation
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.semnan.semnanuniversity.screens.FacultiesScreen
import com.semnan.semnanuniversity.screens.FacultyScreen
import com.semnan.semnanuniversity.screens.HomeScreen
import com.semnan.semnanuniversity.screens.MapScreen
import com.semnan.semnanuniversity.screens.NumbersScreen
import com.semnan.semnanuniversity.screens.WebViewScreen
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

    @Composable
    fun Navigation() {
        val navController = rememberNavController()
        NavHost(
            navController = navController,
            startDestination = Screen.HomeScreen.route
        ) {

            composable(Screen.HomeScreen.route) {
                //here we pass where this should lead us to
                HomeScreen { route ->
                    navController.navigate(route)
                }
            }


            composable(
                Screen.WebViewScreen.route,
                arguments = Screen.WebViewScreen.navArguments
            ) { backStackEntry ->

                WebViewScreen(
                    navController = navController,
                    backStackEntry.arguments?.getString("url")
                )
            }

            composable(
                Screen.FacultyScreen.route,
                arguments = Screen.FacultyScreen.navArguments
            ) { backStackEntry ->
                FacultyScreen(
                    faculty = backStackEntry.arguments?.getString("faculty"),
                    comeFrom = backStackEntry.arguments?.getString("come_from")
                ) { route ->
                    navController.navigate(route)
                }
            }

            composable(
                Screen.FacultiesScreen.route,
                arguments = Screen.FacultiesScreen.navArguments
            ) { backStackEntry ->
                FacultiesScreen(comeFrom = backStackEntry.arguments?.getString("come_from")) { faculty, comeFrom ->
                    navController.navigate(Screen.FacultyScreen.createRoute(faculty, comeFrom))
                }
            }

            composable(
                Screen.MapScreen.route,
                arguments = Screen.MapScreen.navArguments
            ) { backStackEntry ->
                MapScreen(imageId = backStackEntry.arguments?.getInt("image_id"))
            }

            composable(Screen.NumberScreen.route) {
                NumbersScreen()
            }


        }


    }