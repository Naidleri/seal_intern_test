package com.haris.starwars_character.ui.navigation

sealed class Screen  (val route : String)  {
    object Home : Screen("home")
    object Detail : Screen("detail/{url}") {
        fun createRoute(url: String) = "detail/$url"
    }
}