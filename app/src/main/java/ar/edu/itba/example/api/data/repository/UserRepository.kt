package ar.edu.itba.example.api.data.repository

import ar.edu.itba.example.api.data.model.Routine
import ar.edu.itba.example.api.data.model.User
import ar.edu.itba.example.api.data.network.UserRemoteDataSource
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

class UserRepository(
    private val remoteDataSource: UserRemoteDataSource
) {

    // Mutex to make writes to cached values thread-safe.
    private val currentUserMutex = Mutex()
    // Cache of the current user got from the network.
    private var currentUser: User? = null

    suspend fun login(username: String, password: String) {
        remoteDataSource.login(username, password)
    }

    suspend fun logout() {
        remoteDataSource.logout()
    }

    suspend fun getCurrentUser(refresh: Boolean) : User? {
        if (refresh || currentUser == null) {
            val result = remoteDataSource.getCurrentUser()
            // Thread-safe write to latestNews
            currentUserMutex.withLock {
                this.currentUser = result.asModel()
            }
        }

        return currentUserMutex.withLock { this.currentUser }
    }

    suspend fun getCurrentUserRoutines(
        page: Int = 0,
        size: Int = 50,
        orderBy: String = "date"
    ): List<Routine> {
        return remoteDataSource.getCurrentUserRoutines(page, size, orderBy).content.map {it.asModel()}
    }
}