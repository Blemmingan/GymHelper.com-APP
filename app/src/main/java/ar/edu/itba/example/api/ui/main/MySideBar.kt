package ar.edu.itba.example.api.ui.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailDefaults
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ar.edu.itba.example.api.R
import kotlinx.coroutines.launch

@Composable
fun MyNavigationRail(
  currentRoute: String,
  onNavigate: (route: String) -> Unit,
  modifier: Modifier = Modifier
) {
    NavigationRail(
        containerColor = MaterialTheme.colorScheme.primary,
        contentColor = Color.White
    ) {

        NavigationRailItem (
            icon = {Icon(Icons.Filled.Home, contentDescription = stringResource(id = R.string.home))},
            label = {Text(stringResource(id = R.string.home))},
            onClick = {onNavigate("home")},
            selected = currentRoute == "home",
        )
        NavigationRailItem (
            icon = {Icon(Icons.Filled.Favorite, contentDescription = stringResource(id = R.string.favs))},
            label = {Text(stringResource(id = R.string.favs))},
            onClick = {onNavigate("favs")},
            selected = currentRoute == "favs"
        )
        NavigationRailItem(
            icon = {Icon(Icons.Filled.Settings, contentDescription = stringResource(id = R.string.settings))},
            label = {Text(stringResource(id =R.string.settings))},
            onClick = {onNavigate("settings")},
            selected = currentRoute == "settings"
        )
    }
}