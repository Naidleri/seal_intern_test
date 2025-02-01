package com.haris.starwars_character

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.haris.starwars_character.ui.navigation.Screen
import com.haris.starwars_character.ui.presentation.HomeScreen

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
            HomeScreen()
        }
        composable(Screen.Detail.route) { backStackEntry ->
            val peopleId = backStackEntry.arguments?.getString("peopleId")?.toIntOrNull()
            peopleId?.let {
                /*DetailScreen(peopleId = it, navController = navController)*/
            }
        }
    }
}