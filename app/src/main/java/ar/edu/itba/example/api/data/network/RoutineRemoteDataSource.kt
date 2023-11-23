package ar.edu.itba.example.api.data.network

import ar.edu.itba.example.api.data.network.api.ApiCategoryService
import ar.edu.itba.example.api.data.network.api.ApiCycleExerciseService
import ar.edu.itba.example.api.data.network.api.ApiCycleService
import ar.edu.itba.example.api.data.network.api.ApiExerciseService
import ar.edu.itba.example.api.data.network.api.ApiRoutineService
import ar.edu.itba.example.api.data.network.model.NetworkCategory
import ar.edu.itba.example.api.data.network.model.NetworkCycle
import ar.edu.itba.example.api.data.network.model.NetworkCycleExercise
import ar.edu.itba.example.api.data.network.model.NetworkExercise
import ar.edu.itba.example.api.data.network.model.NetworkPagedContent
import ar.edu.itba.example.api.data.network.model.NetworkRoutine
import ar.edu.itba.example.api.data.network.model.NetworkSport

class RoutineRemoteDataSource(
    private val apiRoutineService: ApiRoutineService,
    private val apiCycleService: ApiCycleService,
    private val apiCycleExerciseService: ApiCycleExerciseService,
    private val apiExerciseService: ApiExerciseService,
    private val apiCategoryService: ApiCategoryService
) : RemoteDataSource() {

    suspend fun getRoutines(
        page: Int = 0,
        size: Int = 50,
        orderBy: String = "date"
    ): NetworkPagedContent<NetworkRoutine> {
        return handleApiResponse {
            apiRoutineService.getRoutines(page, size, orderBy)
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

    suspend fun deleteRoutine(routineId: Int) {
        handleApiResponse {
            apiRoutineService.deleteRoutine(routineId)
        }
    }

    suspend fun getFavourites(): NetworkPagedContent<NetworkRoutine>{
        return handleApiResponse {
            apiRoutineService.getFavourites()
        }
    }

    suspend fun addFavourite(routineId: Int){
        handleApiResponse {
            apiRoutineService.addFavourite(routineId)
        }
    }

    suspend fun removeFavourite(routineId: Int){
        handleApiResponse {
            apiRoutineService.deleteFavourite(routineId)
        }
    }

    /////////////////////////////////////////////////////////////////////////////////////
    suspend fun getCycles(routineId: Int): NetworkPagedContent<NetworkCycle> {
        return handleApiResponse {
            apiCycleService.getCycles(routineId = routineId)
        }
    }

    suspend fun getCycle(routineId: Int, cycleId: Int): NetworkCycle {
        return handleApiResponse {
            apiCycleService.getCycle(routineId, cycleId)
        }
    }

    suspend fun addCycle(routineId: Int, cycle: NetworkCycle): NetworkCycle {
        return handleApiResponse {
            apiCycleService.addCycle(routineId, cycle)
        }
    }

    suspend fun modifyCycle(routineId: Int, cycleId: Int, cycle: NetworkCycle): NetworkCycle {
        return handleApiResponse {
            apiCycleService.modifyCycle(routineId, cycleId, cycle)
        }
    }

    suspend fun deleteCycle(routineId: Int, cycleId: Int) {
        handleApiResponse {
            apiCycleService.deleteCycle(routineId, cycleId)
        }
    }

    //////////////////////////////////////////////////////////////////////////////////
    suspend fun getCycleExercises(cycleId: Int): NetworkPagedContent<NetworkCycleExercise> {
        return handleApiResponse {
            apiCycleExerciseService.getCycleExercises(cycleId)
        }
    }

    suspend fun getCycleExercise(
        cycleId: Int,
        exerciseId: Int
    ): NetworkCycleExercise {
        return handleApiResponse {
            apiCycleExerciseService.getCycleExercise(cycleId, exerciseId)
        }
    }

    suspend fun addCycleExercise(
        cycleId: Int,
        exerciseId: Int,
        cycleExercise: NetworkCycleExercise
    ): NetworkCycleExercise {
        return handleApiResponse {
            apiCycleExerciseService.addCycleExercise(cycleId, exerciseId, cycleExercise)
        }
    }

    suspend fun modifyCycleExercise(
        cycleId: Int,
        exerciseId: Int,
        cycleExercise: NetworkCycleExercise
    ): NetworkCycleExercise {
        return handleApiResponse {
            apiCycleExerciseService.modifyCycleExercise(cycleId, exerciseId, cycleExercise)
        }
    }

    suspend fun deleteCycleExercise(
        cycleId: Int,
        exerciseId: Int
    ) {
        handleApiResponse {
            apiCycleExerciseService.deleteCycleExercise(cycleId, exerciseId)
        }
    }

    //////////////////////////////////////////////////////////////////////////////////
    suspend fun getExercises(): NetworkPagedContent<NetworkExercise> {
        return handleApiResponse {
            apiExerciseService.getExercises()
        }
    }

    suspend fun getExercise(
        exerciseId: Int
    ): NetworkExercise {
        return handleApiResponse {
            apiExerciseService.getExercise(exerciseId)
        }
    }

    suspend fun addExercise(
        exercise: NetworkExercise
    ): NetworkExercise {
        return handleApiResponse {
            apiExerciseService.addExercise(exercise)
        }
    }

    suspend fun modifyExercise(
        exerciseId: Int,
        exercise: NetworkExercise
    ): NetworkExercise {
        return handleApiResponse {
            apiExerciseService.modifyExercise(exerciseId, exercise)
        }
    }

    suspend fun deleteExercise(
        exerciseId: Int
    ) {
        handleApiResponse {
            apiExerciseService.deleteExercise(exerciseId)
        }
    }
///////////////////////////////////////////////////////////////////////////////////
suspend fun getCategories(): NetworkPagedContent<NetworkCategory> {
    return handleApiResponse {
        apiCategoryService.getCategories()
    }
}

    suspend fun getCategory(
        categoryId: Int
    ): NetworkCategory {
        return handleApiResponse {
            apiCategoryService.getCategory(categoryId)
        }
    }
    suspend fun addCategory(
        category: NetworkCategory
    ): NetworkCategory {
        return handleApiResponse {
            apiCategoryService.addCategory(category)
        }
    }

    suspend fun modifyCategory(
        categoryId: Int,
        category: NetworkCategory
    ): NetworkCategory {
        return handleApiResponse {
            apiCategoryService.modifyCategory(categoryId, category)
        }
    }

    suspend fun deleteCategory(
        categoryId: Int
    ){
        handleApiResponse {
            apiCategoryService.deleteCategory(categoryId)
        }
    }
}

