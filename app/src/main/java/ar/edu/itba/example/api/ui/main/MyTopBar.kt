package ar.edu.itba.example.api.ui.main

import androidx.compose.foundation.background
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import ar.edu.itba.example.api.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopBar(
    title: String? = null,
    onGoBack: () -> Unit,
    onGoSettings: () -> Unit,
    showBackButton: Boolean = true,
    showSettingsButton: Boolean = true,
) {
    CenterAlignedTopAppBar(
        title = {if (title!=null) Text(title) },
        modifier = Modifier.background(MaterialTheme.colorScheme.primary),
        navigationIcon = { if (showBackButton) {
            IconButton(onClick = onGoBack) {
                Icon(Icons.Filled.ArrowBack, stringResource(id = R.string.backButton))
            }
        }
        },
        actions = {if (showSettingsButton) {
            IconButton(onClick = onGoSettings) {
                Icon(Icons.Filled.Settings, stringResource(id = R.string.settings))
            }
        }
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(titleContentColor = Color.White, containerColor =  MaterialTheme.colorScheme.primary)

    )
}
