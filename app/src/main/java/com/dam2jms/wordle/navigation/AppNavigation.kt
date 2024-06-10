package com.dam2jms.wordle.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dam2jms.wordle.models.ViewModel
import com.dam2jms.wordle.screens.GameScreen

@Composable
fun appNavigation(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppScreens.GameScreen.route){
        composable(route = AppScreens.GameScreen.route) { GameScreen(navController, mvvm = ViewModel())}
    }
}