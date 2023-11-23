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
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import ar.edu.itba.example.api.R
import ar.edu.itba.example.api.ui.main.ExecuteRoutineScreen
import ar.edu.itba.example.api.ui.main.FavourtiteRoutines
import ar.edu.itba.example.api.ui.main.HomeScreen
import ar.edu.itba.example.api.ui.main.LoginScreen
import ar.edu.itba.example.api.ui.main.MyBottomBar
import ar.edu.itba.example.api.ui.main.MyTopBar
import ar.edu.itba.example.api.ui.main.RoutineScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyNavHost(
    navController: NavHostController = rememberNavController(),
    startDestination: String = "login"
){
    val uri = "http://www.GymHelper.com"
    val secureUri = "https://www.GymHelper.com"

    var currentRoute by rememberSaveable {
        mutableStateOf("login")
    }

    var showTopBar by rememberSaveable { mutableStateOf(true) }
    var showBottomBar by rememberSaveable { mutableStateOf(true) }

    Scaffold(
        topBar = { if (showTopBar) {MyTopBar(
            title = stringResource(id = R.string.gymhelp),
            showBackButton = true,
            onGoBack = {navController.popBackStack()},
            onGoSettings = {/*navController.navigate("settings")*/},
        )}},
        bottomBar = { if (showBottomBar) {MyBottomBar(
            currentRoute = currentRoute,
            onNavigate = {route -> navController.navigate(route)}
        )}},

    ){
        NavHost(
            navController = navController,
            startDestination = startDestination,
            modifier = Modifier.padding(it)
        ){
            composable("login"){
                showTopBar= false
                showBottomBar = false
                currentRoute = "login"
                LoginScreen(navController)
            }
            composable("home"){

                route -> showTopBar = true
                showBottomBar = true
                currentRoute = "home"
                HomeScreen(navController = navController)
            }

            composable(
                "routine/{id}",
                arguments = listOf(navArgument("id") {type = NavType.IntType}),
                deepLinks = listOf(navDeepLink{uriPattern = "$uri/routine/{id}"}, navDeepLink {uriPattern = "$secureUri/routine/{id}"})
            ) {
                    route -> showTopBar = true
                        showBottomBar = false
                        currentRoute = "routine"
                        RoutineScreen(
                        routineId = route.arguments?.getInt("id")!!,
                        navController = navController
                    )
            }

            composable(
                "routine/execution/{id}",
                arguments = listOf(navArgument("id") {type = NavType.IntType}),
            ) {
                    route -> showTopBar = false
                    showBottomBar = false
                currentRoute = "routine/execution"
                        ExecuteRoutineScreen(
                            routineId = route.arguments?.getInt("id")!!,
                            navController = navController
                    )
            }
            
            composable(
                "favs"
            ) {
                currentRoute = "favs"
                showTopBar = true
                showBottomBar = false
                FavourtiteRoutines(navController = navController)
            }
        }
    }
}


