package com.rhytham.jetspot.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.rhytham.jetspot.screens.BlogpostScreen
import com.rhytham.jetspot.screens.HomeScreen
import com.rhytham.jetspot.screens.ManageBlogScreen
import com.rhytham.jetspot.screens.UpsertpostScreen

@Composable
fun SetupNavGraph(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(route = Screen.Home.route) {
            HomeScreen(navController)
        }

        composable(
            route = Screen.Blog.route,
            arguments = listOf(navArgument("blog_post") {
                type = NavType.StringType
            })
        ) {
            BlogpostScreen(
                navHostController = navController,
                post = it.arguments?.getString("blog_post").toString()
            )
        }

        composable(
            route = Screen.EditPost.route,
            arguments = listOf(navArgument("blog_post") {
                type = NavType.StringType
            })
        ) {
            UpsertpostScreen(
                post = it.arguments?.getString("blog_post"),
                navHostController = navController
            )
        }

        composable(route = Screen.ManageBlog.route){
            ManageBlogScreen(navController = navController)
        }

    }

}