package ar.edu.itba.example.api.ui.main

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import ar.edu.itba.example.api.R

@Composable
fun MyBottomBar(
    currentRoute: String,
    onNavigate: (route: String) -> Unit
) {
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.primary,
        contentColor = Color.Black
    ) {
        NavigationBarItem (
            icon = {Icon(Icons.Filled.Home, contentDescription = stringResource(id = R.string.home))},
            label = {Text(stringResource(id = R.string.home))},
            onClick = {onNavigate("home")},
            selected = currentRoute == "home"
        )
        NavigationBarItem (
            icon = {Icon(Icons.Filled.Favorite, contentDescription = stringResource(id = R.string.favs))},
            label = {Text(stringResource(id = R.string.favs))},
            onClick = {onNavigate("favs")},
            selected = currentRoute == "favs"
        )
    }
}