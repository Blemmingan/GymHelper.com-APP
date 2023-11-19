package ar.edu.itba.example.api.data.network.api

import ar.edu.itba.example.api.data.network.model.NetworkExercise
import ar.edu.itba.example.api.data.network.model.NetworkPagedContent
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiExerciseService {
    @GET("exercises")
    suspend fun getExercises(@Query("size") size: Int = 50): Response<NetworkPagedContent<NetworkExercise>>

    @POST("exercises")
    suspend fun addExercise(@Body exercise: NetworkExercise): Response<NetworkExercise>

    @GET("exercises/{exerciseId}")
    suspend fun getExercise(@Path("exerciseId") exerciseId: Int): Response<NetworkExercise>

    @PUT("exercises/{exerciseId}")
    suspend fun modifyExercise(
        @Path("exerciseId") exerciseId: Int,
        @Body exercise: NetworkExercise
    ): Response<NetworkExercise>

    @DELETE("exercises/{exerciseId}")
    suspend fun deleteExercise(@Path("exerciseId") exerciseId: Int): Response<Unit>
}