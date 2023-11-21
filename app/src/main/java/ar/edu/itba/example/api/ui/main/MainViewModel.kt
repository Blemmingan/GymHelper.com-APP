package ar.edu.itba.example.api.ui.main

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.edu.itba.example.api.data.DataSourceException
import ar.edu.itba.example.api.data.model.CycleExercise
import ar.edu.itba.example.api.data.model.Error
import ar.edu.itba.example.api.data.model.Routine
import ar.edu.itba.example.api.data.repository.RoutineRepository
import ar.edu.itba.example.api.data.repository.SportRepository
import ar.edu.itba.example.api.data.repository.UserRepository
import ar.edu.itba.example.api.util.SessionManager
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MainViewModel(
    sessionManager: SessionManager,
    private val userRepository: UserRepository,
    private val sportRepository: SportRepository,
    private val routineRepository: RoutineRepository,
) : ViewModel() {

    var uiState by mutableStateOf(MainUiState(isAuthenticated = sessionManager.loadAuthToken() != null))
        private set

    fun login(username: String, password: String) = runOnViewModelScope(
        { userRepository.login(username, password) },
        { state, _ -> state.copy(isAuthenticated = true) }
    )

    fun logout() = runOnViewModelScope(
        { userRepository.logout() },
        { state, _ ->
            state.copy(
                isAuthenticated = false,
                currentUser = null,
                currentSport = null,
                sports = null
            )
        }
    )

    fun getCurrentUser() = runOnViewModelScope(
        { userRepository.getCurrentUser(uiState.currentUser == null) },
        { state, response -> state.copy(currentUser = response) }
    )

    fun getCurrentUserRoutines(
        page: Int = 0,
        size: Int = 50,
        orderBy: String = "date"
    ) = runOnViewModelScope(
        { userRepository.getCurrentUserRoutines(page, size, orderBy)},
        { state, response -> state.copy(currentUserRoutines = response)}
    )

    private fun <R> runOnViewModelScope(
        block: suspend () -> R,
        updateState: (MainUiState, R) -> MainUiState
    ): Job = viewModelScope.launch {
        uiState = uiState.copy(isFetching = true, error = null)
        runCatching {
            block()
        }.onSuccess { response ->
            uiState = updateState(uiState, response).copy(isFetching = false)
        }.onFailure { e ->
            uiState = uiState.copy(isFetching = false, error = handleError(e))
        }
    }

    private fun handleError(e: Throwable): Error {
        return if (e is DataSourceException) {
            Error(e.code, e.message ?: "", e.details)
        } else {
            Error(null, e.message ?: "", null)
        }
    }

    fun getRoutine(routineId: Int) = runOnViewModelScope(
        {routineRepository.getRoutine(routineId)},
        {state, response -> state.copy(currentRoutine = response)}
    )

    suspend fun getRoutines(): List<Routine> {
        return routineRepository.getRoutines(false)
    }

    fun getCycles(routineId: Int) = runOnViewModelScope(
        {getCyclesAux(routineId) },
        {state, response -> state.copy(currentRoutineDetails = response) }
    )

    private suspend fun getCyclesAux(routineId: Int): List<CycleWithExercises> {
        val cycles = routineRepository.getCycles(routineId)
        val newList: MutableList<CycleWithExercises> = mutableListOf()
        for (cycle in cycles) {
            val exercises: List<CycleExercise> = routineRepository.getCycleExercises(cycle.id!!)
            newList.add(CycleWithExercises(cycle = cycle, exercises = exercises))
        }
        return newList
    }
}

