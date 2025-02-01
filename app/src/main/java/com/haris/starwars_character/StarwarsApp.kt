package com.haris.starwars_character

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.haris.starwars_character.ui.navigation.Screen
import com.haris.starwars_character.ui.presentation.HomeScreen
import com.haris.starwars_character.ui.presentation.detail.DetailScreen
import java.net.URLDecoder
import java.nio.charset.StandardCharsets

@Composable
fun StarwarsApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,
        modifier = modifier
    ) {
        composable(Screen.Home.route) {
            HomeScreen(
                navigateToDetail = { url ->
                    navController.navigate(Screen.Detail.createRoute(url))
                }
            )
        }
        composable(
            route = Screen.Detail.route,
            arguments = listOf(
                navArgument("url") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val encodedUrl = backStackEntry.arguments?.getString("url")
            val url = encodedUrl?.let { URLDecoder.decode(it, StandardCharsets.UTF_8.toString()) }
            url?.let {
                DetailScreen(
                    url = it,
                    navigateBack = {
                        navController.popBackStack()
                    }
                )
            }
        }
    }
}