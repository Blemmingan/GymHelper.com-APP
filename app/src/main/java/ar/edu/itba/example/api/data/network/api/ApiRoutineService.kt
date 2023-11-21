package ar.edu.itba.example.api.data.network.api

import ar.edu.itba.example.api.data.network.model.NetworkRoutine
import ar.edu.itba.example.api.data.network.model.NetworkPagedContent
import ar.edu.itba.example.api.data.network.model.NetworkSport
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiRoutineService {
    @GET("routines")
    suspend fun getRoutines(
        @Query("size") size: Int = 50
    ): Response<NetworkPagedContent<NetworkRoutine>>

    @POST("routines")
    suspend fun addRoutine(@Body routine: NetworkRoutine): Response<NetworkRoutine>

    @GET("routines/{routineId}")
    suspend fun getRoutine(@Path("routineId") routineId: Int): Response<NetworkRoutine>

    @PUT("routines/{routineId}")
    suspend fun modifyRoutine(
        @Path("routineId") routineId: Int,
        @Body routine: NetworkRoutine
    ): Response<NetworkRoutine>

    @DELETE("routines/{rotuineId}")
    suspend fun deleteRoutine(@Path("routineId") routineId: Int): Response<Unit>

}