package ar.edu.itba.example.api.data.network.api

import ar.edu.itba.example.api.data.model.Routine
import ar.edu.itba.example.api.data.network.model.NetworkCredentials
import ar.edu.itba.example.api.data.network.model.NetworkPagedContent
import ar.edu.itba.example.api.data.network.model.NetworkRoutine
import ar.edu.itba.example.api.data.network.model.NetworkToken
import ar.edu.itba.example.api.data.network.model.NetworkUser
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiUserService {
    @POST("users/login")
    suspend fun login(@Body credentials: NetworkCredentials): Response<NetworkToken>

    @POST("users/logout")
    suspend fun logout(): Response<Unit>

    @GET("users/current")
    suspend fun getCurrentUser(): Response<NetworkUser>

    @GET("users/current/routines")
    suspend fun getCurrentUserRoutines(
        @Query("page") page: Int = 0,
        @Query("size") size: Int = 50,
        @Query("orderBy") orderBy: String = "date",
    ): Response<NetworkPagedContent<NetworkRoutine>>


}
