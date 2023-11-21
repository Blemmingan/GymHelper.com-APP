package ar.edu.itba.example.api.data.repository

import ar.edu.itba.example.api.data.model.Cycle
import ar.edu.itba.example.api.data.model.CycleExercise
import ar.edu.itba.example.api.data.model.Routine
import ar.edu.itba.example.api.data.network.RoutineRemoteDataSource
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

class RoutineRepository (
    private val remoteDataSource: RoutineRemoteDataSource
) {
    private val routinesMutex = Mutex()
    private var routines: List<Routine> = emptyList()

    suspend fun getRoutines(refresh: Boolean = false): List<Routine> {
        if (refresh || routines.isEmpty()){
            val result = remoteDataSource.getRoutines()
            routinesMutex.withLock {
                this.routines = result.content.map { it.asModel() }
            }
        }

        return routinesMutex.withLock { this.routines }
    }

    suspend fun getRoutine(routineId: Int) : Routine {
        return remoteDataSource.getRoutine(routineId).asModel()
    }

    suspend fun addRoutine(routine: Routine) : Routine {
        val newRoutine = remoteDataSource.addRoutine(routine.asNetworkModel()).asModel()
        routinesMutex.withLock{
            this.routines = emptyList()
        }
        return newRoutine
    }

    suspend fun modifyRoutine(routine: Routine) : Routine {
        val updatedRoutine = remoteDataSource.modifyRoutine(routine.asNetworkModel()).asModel()
        routinesMutex.withLock {
            this.routines = emptyList()
        }
        return updatedRoutine
    }

    suspend fun deleteRoutine(routineId: Int) {
        remoteDataSource.deleteRoutine(routineId)
        routinesMutex.withLock {
            this.routines = emptyList()
        }
    }

    suspend fun getCycles(routineId: Int): List<Cycle> {
        return remoteDataSource.getCycles(routineId).content.map { it.asModel() }
    }

    suspend fun getCycleExercises(cycleId: Int): List<CycleExercise> {
        return remoteDataSource.getCycleExercises(cycleId).content.map {it.asModel()}
    }
}