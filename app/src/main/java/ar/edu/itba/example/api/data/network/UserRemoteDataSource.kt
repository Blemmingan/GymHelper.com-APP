package ar.edu.itba.example.api.data.network

import ar.edu.itba.example.api.data.network.api.ApiUserService
import ar.edu.itba.example.api.data.network.model.NetworkCredentials
import ar.edu.itba.example.api.data.network.model.NetworkPagedContent
import ar.edu.itba.example.api.data.network.model.NetworkRoutine
import ar.edu.itba.example.api.data.network.model.NetworkUser
import ar.edu.itba.example.api.util.SessionManager

class UserRemoteDataSource(
    private val sessionManager: SessionManager,
    private val apiUserService: ApiUserService
) : RemoteDataSource() {

    suspend fun login(username: String, password: String) {
        val response = handleApiResponse {
            apiUserService.login(NetworkCredentials(username, password))
        }
        sessionManager.saveAuthToken(response.token)
    }

    suspend fun logout() {
        handleApiResponse { apiUserService.logout() }
        sessionManager.removeAuthToken()
    }

    suspend fun getCurrentUser(): NetworkUser {
        return handleApiResponse { apiUserService.getCurrentUser() }
    }

    suspend fun getCurrentUserRoutines(
        page: Int = 0,
        size: Int = 50,
        orderBy: String = "date"
    ): NetworkPagedContent<NetworkRoutine> {
        return handleApiResponse{
            apiUserService.getCurrentUserRoutines(page, size, orderBy)
        }
    }
}