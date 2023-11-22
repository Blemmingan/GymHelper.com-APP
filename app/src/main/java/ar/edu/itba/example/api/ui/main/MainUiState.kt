package ar.edu.itba.example.api.ui.main

import ar.edu.itba.example.api.data.model.Cycle
import ar.edu.itba.example.api.data.model.CycleExercise
import ar.edu.itba.example.api.data.model.Error
import ar.edu.itba.example.api.data.model.Routine
import ar.edu.itba.example.api.data.model.Sport
import ar.edu.itba.example.api.data.model.User

data class CycleWithExercises(
    val cycle: Cycle? = null,
    val exercises: List<CycleExercise>? = listOf()
)

data class MainUiState(
    val isAuthenticated: Boolean = false,
    val isFetching: Boolean = false,

    val currentUser: User? = null,
    val currentUserRoutines: List<Routine>? = null,

    val sports: List<Sport>? = null,
    val currentSport: Sport? = null,
    val error: Error? = null,

    val currentRoutine: Routine? = null,
    val currentRoutineDetails: List<CycleWithExercises> = listOf()
)

val MainUiState.canGetCurrentUser: Boolean get() = isAuthenticated
val MainUiState.canGetAllSports: Boolean get() = isAuthenticated
val MainUiState.canGetCurrentSport: Boolean get() = isAuthenticated && currentSport != null
val MainUiState.canAddSport: Boolean get() = isAuthenticated && currentSport == null
val MainUiState.canModifySport: Boolean get() = isAuthenticated && currentSport != null
val MainUiState.canDeleteSport: Boolean get() = canModifySport

