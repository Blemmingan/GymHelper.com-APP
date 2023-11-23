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
import ar.edu.itba.example.api.ui.main.SettingsScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyNavHost(
    navController: NavHostController = rememberNavController(),
    startDestination: String = "home"
){
    val uri = "http://www.GymHelper.com"
    val secureUri = "https://www.GymHelper.com"

    var currentRoute by rememberSaveable {
        mutableStateOf("login")
    }
    var title by rememberSaveable {
        mutableStateOf("login")
    }
    var showBackButton by rememberSaveable {
        mutableStateOf(false)
    }
    var showSettingsButton by rememberSaveable {
        mutableStateOf(true)
    }

    var showTopBar by rememberSaveable { mutableStateOf(true) }
    var showBottomBar by rememberSaveable { mutableStateOf(true) }

    Scaffold(
        topBar = { if (showTopBar) {MyTopBar(
            title = title,
            showBackButton = showBackButton,
            onGoBack = {navController.navigateUp()},
            onGoSettings = {navController.navigate("settings")},
            showSettingsButton = showSettingsButton
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
                title = stringResource(id = R.string.login)
                showTopBar= false
                showBottomBar = false
                showSettingsButton = false
                currentRoute = "login"
                LoginScreen(navController)
            }
            composable("home"){
                route -> title = stringResource(id = R.string.home)
                showTopBar = true
                showBottomBar = true
                showBackButton = false
                showSettingsButton = true
                currentRoute = "home"
                HomeScreen(navController = navController)
            }

            composable(
                "routine/{id}",
                arguments = listOf(navArgument("id") {type = NavType.IntType}),
                deepLinks = listOf(navDeepLink{uriPattern = "$uri/routine/{id}"}, navDeepLink {uriPattern = "$secureUri/routine/{id}"})
            ) {
                    route -> title = stringResource(id = R.string.routine)
                        showTopBar = true
                        showBottomBar = false
                        showBackButton = true
                        showSettingsButton = true
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
                    route -> title = stringResource(id = R.string.execution)
                    showTopBar = false
                    showBottomBar = false
                    showBackButton = true
                    showSettingsButton = false
                    currentRoute = "routine/execution"
                        ExecuteRoutineScreen(
                            routineId = route.arguments?.getInt("id")!!,
                            navController = navController
                    )
            }
            
            composable(
                "favs"
            ) {
                title = stringResource(id = R.string.favs)
                currentRoute = "favs"
                showTopBar = true
                showBottomBar = true
                showBackButton = false
                showSettingsButton = true
                FavourtiteRoutines(navController = navController)
            }

            composable(
                "settings"
            ) {
                title = stringResource(id = R.string.settings)
                currentRoute = "settings"
                showTopBar = true
                showBottomBar = false
                showBackButton = true
                showSettingsButton = false
                SettingsScreen(navController = navController)
            }
        }
    }
}


