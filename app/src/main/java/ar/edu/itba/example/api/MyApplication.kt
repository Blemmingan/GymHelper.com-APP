package ar.edu.itba.example.api

import android.app.Application
import ar.edu.itba.example.api.data.network.RoutineRemoteDataSource
import ar.edu.itba.example.api.data.network.SportRemoteDataSource
import ar.edu.itba.example.api.data.network.UserRemoteDataSource
import ar.edu.itba.example.api.data.network.api.RetrofitClient
import ar.edu.itba.example.api.data.repository.RoutineRepository
import ar.edu.itba.example.api.data.repository.SportRepository
import ar.edu.itba.example.api.data.repository.UserRepository
import ar.edu.itba.example.api.util.SessionManager

class MyApplication : Application() {

    private val userRemoteDataSource: UserRemoteDataSource
        get() = UserRemoteDataSource(sessionManager, RetrofitClient.getApiUserService(this))

    private val sportRemoteDataSource: SportRemoteDataSource
        get() = SportRemoteDataSource(RetrofitClient.getApiSportService(this))

    private val routineRemoteDataSource: RoutineRemoteDataSource
        get() = RoutineRemoteDataSource(
            RetrofitClient.getApiRoutineService(this),
            RetrofitClient.getApiCycleService(this),
            RetrofitClient.getApiCycleExerciseService(this),
            RetrofitClient.getApiExerciseService(this),
            RetrofitClient.getApiCategoryService(this)
        )

    val sessionManager: SessionManager
        get() = SessionManager(this)

    val userRepository: UserRepository
        get() = UserRepository(userRemoteDataSource)

    val sportRepository: SportRepository
        get() = SportRepository(sportRemoteDataSource)

    val routineRepository: RoutineRepository
        get() = RoutineRepository(routineRemoteDataSource)
}