package ar.edu.itba.example.api.ui.main

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import ar.edu.itba.example.api.data.model.Routine
import ar.edu.itba.example.api.util.getViewModelFactory

@Composable
fun FavourtiteRoutines(navController: NavHostController,
                       viewModel: MainViewModel = viewModel(factory = getViewModelFactory())
){
    viewModel.getFavourites()
    var routinesList : List<Routine>? = viewModel.uiState.currentUserRoutines
   // RoutineList(navController, routinesList, false)
}