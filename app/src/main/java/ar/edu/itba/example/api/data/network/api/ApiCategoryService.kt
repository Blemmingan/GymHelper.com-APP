package ar.edu.itba.example.api.data.network.api

import android.icu.lang.UCharacterEnums.ECharacterCategory
import ar.edu.itba.example.api.data.network.model.NetworkCategory
import ar.edu.itba.example.api.data.network.model.NetworkPagedContent
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiCategoryService {
    @GET("categories")
    suspend fun getCategories(@Query("size") size: Int = 50): Response<NetworkPagedContent<NetworkCategory>>

    @POST("categories")
    suspend fun addCategory(@Body category: NetworkCategory): Response<NetworkCategory>

    @GET("categories/{categoryId}")
    suspend fun getCategory(@Path("categoryId") category: Int): Response<NetworkCategory>

    @PUT("categories/{categoryId}")
    suspend fun modifyCategory(
        @Path("categoryId") categoryId: Int,
        @Body category: NetworkCategory
    ): Response<NetworkCategory>

    @DELETE("categories/{categoryId}")
    suspend fun deleteCategory(@Path("categoryId") categoryId: Int): Response<Unit>
}