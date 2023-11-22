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
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import ar.edu.itba.example.api.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopBar(
    title: String? = null,
    showBackButton: Boolean,
    onGoBack: () -> Unit,
    onGoSettings: () -> Unit
) {
    CenterAlignedTopAppBar(
        title = {if (title!=null) Text(title) },
        modifier = Modifier.background(MaterialTheme.colorScheme.primary),
        navigationIcon = {
            IconButton(onClick = onGoBack){
                Icon(Icons.Filled.ArrowBack, stringResource(id = R.string.backButton))
            }
        },
        actions = {
            IconButton(onClick = onGoSettings){
                Icon(Icons.Filled.Settings, stringResource(id = R.string.settings))
            }
        }

    )
}
