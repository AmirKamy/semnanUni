package com.semnan.semnanuniversity.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.Navigation
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.semnan.semnanuniversity.screens.FacultiesScreen
import com.semnan.semnanuniversity.screens.FacultyScreen
import com.semnan.semnanuniversity.screens.HomeScreen
import com.semnan.semnanuniversity.screens.WebViewScreen
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Composable
fun Navigation(){
    val navController = rememberNavController()
    NavHost(navController = navController,
        startDestination = Screen.HomeScreen.route ) {

        composable(Screen.HomeScreen.route){
            //here we pass where this should lead us to
            HomeScreen{ route ->
                navController.navigate(route)
            }
        }


        composable(Screen.WebViewScreen.route,
            arguments = Screen.WebViewScreen.navArguments) {
                backStackEntry ->

            WebViewScreen(navController = navController,
                backStackEntry.arguments?.getString("url"))
        }

        composable(Screen.FacultyScreen.route,
            arguments = Screen.FacultyScreen.navArguments){
            backStackEntry ->

            FacultyScreen(jsonFaculty = backStackEntry.arguments?.getString("faculty"))
        }

        composable(Screen.FacultiesScreen.route){
            //here we pass where this should lead us to
            FacultiesScreen { faculty ->
                val encodedFaculty = URLEncoder.encode(faculty, StandardCharsets.UTF_8.toString())
                navController.navigate(Screen.FacultyScreen.createRoute(encodedFaculty))
            }
        }


    }



}