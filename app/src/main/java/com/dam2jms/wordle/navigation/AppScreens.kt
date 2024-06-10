package com.dam2jms.wordle.navigation

sealed class AppScreens (val route: String){
    object GameScreen: AppScreens(route = "game_screen")
}