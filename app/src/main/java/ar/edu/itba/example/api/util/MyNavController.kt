package ar.edu.itba.example.api.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
//import ar.edu.itba.example.api.ui.main.RoutineDetail
import ar.edu.itba.example.api.ui.main.homeScreen
import ar.edu.itba.example.api.ui.main.loginScreen
import ar.edu.itba.example.api.ui.main.test

@Composable
fun MyNavHost(
    navController: NavHostController = rememberNavController(),
    startDestination: String = "login"
){
    var uri = "http://www.GymHelper.com"
    var secureUri = "https://www.GymHelper.com"

    NavHost(
        navController = navController,
        startDestination = startDestination
    ){
        composable("login"){
            loginScreen(navController)
        }
        composable("test"){
            test()
        }
        composable("home"){
            homeScreen(navController = navController)
        }

    /*    composable(
            "routine/{id}",
            arguments = listOf(navArgument("id") {type = NavType.IntType}),
            deepLinks = listOf(navDeepLink{uriPattern = "$uri/routine?id={id}"}, navDeepLink {uriPattern = "$secureUri/routine?id={id}"})
            ) {
                route -> RoutineDetail(route.arguments?.getInt("id"))
        }*/
    }
}

