package ar.edu.itba.example.api.util

import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSavedStateRegistryOwner
import ar.edu.itba.example.api.MyApplication

@Composable
fun getViewModelFactory(defaultArgs: Bundle? = null): ViewModelFactory {
    val application = (LocalContext.current.applicationContext as MyApplication)
    val sessionManager = application.sessionManager
    val userRepository = application.userRepository
    val sportRepository = application.sportRepository
    val routineRepository = application.routineRepository
    return ViewModelFactory(
        sessionManager,
        userRepository,
        sportRepository,
        routineRepository,
        LocalSavedStateRegistryOwner.current,
        defaultArgs
    )
}