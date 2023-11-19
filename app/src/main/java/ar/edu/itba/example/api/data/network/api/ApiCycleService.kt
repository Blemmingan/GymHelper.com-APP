package ar.edu.itba.example.api.data.network.api

import ar.edu.itba.example.api.data.network.model.NetworkCycle
import ar.edu.itba.example.api.data.network.model.NetworkPagedContent
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiCycleService {
    @GET("routines/{routineId}/cycles")
    suspend fun getCycles(
        @Path("routineId") routineId: Int,
        @Query("size") size: Int = 50
    ): Response<NetworkPagedContent<NetworkCycle>>

    @POST("routines/{routineId}/cycles")
    suspend fun addCycle(
        @Path("routineId") routineId: Int,
        @Body cycle: NetworkCycle
    ): Response<NetworkCycle>

    @GET("routines/{routineId}/cycles/{cycleId}")
    suspend fun getCycle(
        @Path("routineId") routineId: Int,
        @Path("cycleId") cycleId: Int,
    ): Response<NetworkCycle>

    @PUT("routines/{routineId}/cycles/{cycleId}")
    suspend fun modifyCycle(
        @Path("routineId") routineId: Int,
        @Path("cycleId") cycleId: Int,
        @Body cycle: NetworkCycle
    ): Response<NetworkCycle>

    @DELETE("routines/{routineId}/cycles/{cycleId}")
    suspend fun deleteCycle(
        @Path("routineId") routineId: Int,
        @Path("cycleId") cycleId: Int
    ): Response<Unit>
}