package ar.edu.itba.example.api.util

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDeepLink
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import ar.edu.itba.example.api.R
import ar.edu.itba.example.api.ui.main.MyBottomBar
import ar.edu.itba.example.api.ui.main.MySideBar
import ar.edu.itba.example.api.ui.main.MyTopBar
import ar.edu.itba.example.api.ui.main.RoutineScreen
import ar.edu.itba.example.api.ui.main.homeScreen
import ar.edu.itba.example.api.ui.main.loginScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyNavHost(
    navController: NavHostController = rememberNavController(),
    startDestination: String = "login"
){
    val uri = "http://www.GymHelper.com"
    val secureUri = "https://www.GymHelper.com"


    var showBars by rememberSaveable { mutableStateOf(true) }

    Scaffold(
        topBar = { if (showBars) {MyTopBar(
            title = stringResource(id = R.string.gymhelp),
            showBackButton = true,
            onGoBack = {navController.popBackStack()},
            onGoSettings = {/*navController.navigate("settings")*/},
        )}},
        bottomBar = { if (showBars) {MyBottomBar(
            currentRoute = "home",
            onNavigate = {/*route -> navController.navigate(route)*/}
        )}},

    ){
        /*MySideBar(
            currentRoute = "home",
            onNavigate = {route -> navController.navigate(route)},
            modifier = Modifier.padding(it)
        )*/
        NavHost(
            navController = navController,
            startDestination = startDestination,
            modifier = Modifier.padding(it)
        ){
            composable("login"){
                showBars= false
                loginScreen(navController)
            }
            composable("home"){
                showBars = true
                homeScreen(navController = navController)
            }

            composable(
                "routine/{id}",
                arguments = listOf(navArgument("id") {type = NavType.IntType}),
                deepLinks = listOf(navDeepLink{uriPattern = "$uri/routine/{id}"}, navDeepLink {uriPattern = "$secureUri/routine/{id}"})
            ) {
                    route -> RoutineScreen(
                        routineId = route.arguments?.getInt("id")!!,
                        navController = navController
                    )
            }
        }
    }
}


