package ar.edu.itba.example.api.data.network.api


import ar.edu.itba.example.api.data.network.model.NetworkCycleExercise
import ar.edu.itba.example.api.data.network.model.NetworkPagedContent
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiCycleExerciseService {
    @GET("cycles/{cycleId}/exercises")
    suspend fun getCycleExercises(
        @Path("cycleId") cycleId: Int,
        @Query("size") size: Int = 50
    ): Response<NetworkPagedContent<NetworkCycleExercise>>

    @POST("cycles/{cycleId}/exercises/{exerciseId}")
    suspend fun addCycleExercise(
        @Path("cycleId") cycleId: Int,
        @Path("exerciseId") exerciseId: Int,
        @Body cycleExercise: NetworkCycleExercise
    ): Response<NetworkCycleExercise>

    @GET("cycles/{cycleId}/exercises/{exerciseId}")
    suspend fun getCycleExercise(
        @Path("cycleId") cycleId: Int,
        @Path("exerciseId") exerciseId: Int,
    ): Response<NetworkCycleExercise>

    @PUT("cycles/{cycleId}/exercises/{exerciseId}")
    suspend fun modifyCycleExercise(
        @Path("cycleId") cycleId: Int,
        @Path("exerciseId") exerciseId: Int,
        @Body cycleExercise: NetworkCycleExercise
    ): Response<NetworkCycleExercise>

    @DELETE("cycles/{cycleId}/exercises/{exerciseId}")
    suspend fun deleteCycleExercise(
        @Path("cycleId") cycleId: Int,
        @Path("exerciseId") exerciseId: Int,
    ): Response<Unit>
}