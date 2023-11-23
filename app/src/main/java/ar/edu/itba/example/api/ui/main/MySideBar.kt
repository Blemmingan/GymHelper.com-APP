package ar.edu.itba.example.api.ui.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ar.edu.itba.example.api.R
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MySideBar(
  currentRoute: String,
  onNavigate: (route: String) -> Unit,
  modifier: Modifier = Modifier
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    ModalNavigationDrawer(
        modifier = modifier,
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Spacer(Modifier.height(12.dp))
                NavigationDrawerItem(
                    icon = {Icon(Icons.Filled.Home, "home")},
                    label = { Text(stringResource(id = R.string.home)) },
                    selected = currentRoute=="home",
                    onClick = {
                        scope.launch { drawerState.close() }
                        onNavigate("home")
                    }
                )
                NavigationDrawerItem(
                    icon = {Icon(Icons.Filled.Favorite, "favourites")},
                    label = { Text(stringResource(id = R.string.favs)) },
                    selected = currentRoute=="favs",
                    onClick = {
                        scope.launch { drawerState.close() }
                        onNavigate("favs")
                    }
                )
                Divider()
                Spacer(modifier = Modifier.fillMaxWidth())
                NavigationDrawerItem(
                    icon = {Icon(Icons.Filled.Favorite, "settings")},
                    label = { Text(stringResource(id = R.string.settings)) },
                    selected = currentRoute=="settings",
                    onClick = {
                        scope.launch { drawerState.close() }
                        onNavigate("settings")
                    }
                )
            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            IconButton(onClick = { scope.launch {drawerState.open()} }) {
                Icon(Icons.Filled.Menu, "menu")
            }
        }
    }
}