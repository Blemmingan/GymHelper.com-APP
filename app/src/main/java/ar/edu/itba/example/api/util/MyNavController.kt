package ar.edu.itba.example.api.util

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ar.edu.itba.example.api.ui.main.loginScreen
import ar.edu.itba.example.api.ui.main.test

@Composable
fun MyNavHost(navController: NavHostController = rememberNavController(), startDestination: String = "login"){

    var uri = "http://www.GymHelper.com"
    var secureUri = "https://www.GymHelper.com"
    NavHost(navController = navController, startDestination = startDestination){
        composable("login"){
            loginScreen(navController)
        }
        composable("test"){
            test()
        }
    }
}