package ar.edu.itba.example.api.data.network

import ar.edu.itba.example.api.data.network.api.ApiRoutineService
import ar.edu.itba.example.api.data.network.model.NetworkPagedContent
import ar.edu.itba.example.api.data.network.model.NetworkRoutine
import ar.edu.itba.example.api.data.network.model.NetworkSport

class RoutineRemoteDataSource(
    private val apiRoutineService: ApiRoutineService
) : RemoteDataSource() {

    suspend fun getRoutines(): NetworkPagedContent<NetworkRoutine> {
        return handleApiResponse {
            apiRoutineService.getRoutines()
        }
    }

    suspend fun getRoutine(routineId: Int): NetworkRoutine {
        return handleApiResponse {
            apiRoutineService.getRoutine(routineId)
        }
    }
    suspend fun addRoutine(routine: NetworkRoutine): NetworkRoutine {
        return handleApiResponse {
            apiRoutineService.addRoutine(routine)
        }
    }

    suspend fun modifyRoutine(routine: NetworkRoutine): NetworkRoutine {
        return handleApiResponse {
            apiRoutineService.modifyRoutine(routine.id!!, routine)
        }
    }

    suspend fun deleteRoutine(routineId: Int){
        handleApiResponse {
            apiRoutineService.deleteRoutine(routineId)
        }
    }
}