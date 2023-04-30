package com.example.littlelemon

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun Navigation(navController : NavHostController, menuItems: List<MenuItemRoom>) {

    val sharedPreferences = LocalContext.current.getSharedPreferences("LemonPrefs", Context.MODE_PRIVATE)
    var route by remember { mutableStateOf("") }
    if(sharedPreferences.contains("firstName")){
        route = Home.route
    }else{
        route = Onboarding.route
    }
    NavHost(navController = navController, startDestination = route) {
        composable(Home.route) {
            Home(menuItems, navController)
        }
        composable(Onboarding.route) {
            Onboarding(LocalContext.current, navController)
        }
        composable(Profile.route) {
            Profile(LocalContext.current, navController)
        }
    }
}
