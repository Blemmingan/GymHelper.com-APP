package ar.edu.itba.example.api.ui.main

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import ar.edu.itba.example.api.R
import ar.edu.itba.example.api.util.getViewModelFactory
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import ar.edu.itba.example.api.data.model.Routine
import kotlinx.coroutines.launch

@Composable
fun homeScreen(navController: NavHostController,
               viewModel: MainViewModel = viewModel(factory = getViewModelFactory())
){
    viewModel.getCurrentUserRoutines()
    var routinesList : List<Routine>? = viewModel.uiState.currentUserRoutines
    val composableScope = rememberCoroutineScope()
    LaunchedEffect(key1 = routinesList, block = {composableScope.launch { routinesList = viewModel.getRoutines() }})


        ScaffoldExample(navController, routinesList)
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaffoldExample(navController: NavHostController, routinesList : List<Routine>?) {
    var presses by remember { mutableIntStateOf(0) }
    val list : List<Int> = listOf(1,2,3,4,5,6,7,8)

    val state = rememberScrollState()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.gymhelp), maxLines = 1, overflow = TextOverflow.Ellipsis)

                },
                navigationIcon = {
                    IconButton(onClick = { /* Open Side Menu */ }) {
                        Icon(
                            imageVector = Icons.Filled.Menu,
                            contentDescription = stringResource(id = R.string.local_desc)
                        )
                    }

                },
                actions = {
                    IconButton(onClick = { /* goto_profile */ }) {
                        Icon(
                            imageVector = Icons.Filled.Person,
                            contentDescription = stringResource(id = R.string.local_desc)
                        )
                    }
                },

                )
        },
        bottomBar = {
            BottomAppBar(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.primary,
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    text = "Bottom app bar",
                )
            }
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { presses++ }) {
                Icon(Icons.Default.Add, contentDescription = "Add")
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxWidth()
                .verticalScroll(state),
            verticalArrangement = Arrangement.spacedBy(16.dp)
            ,
        ) {
            Text(
                modifier = Modifier.padding(8.dp),
                text = stringResource(id = R.string.your_routines),
            )
            if (routinesList != null) {
                for (routine in routinesList){
                    OutlinedCard(
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.surface
                        ),
                        border = BorderStroke(1.dp, Color.Black),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(100.dp),
                    ) {
                        Row (verticalAlignment = Alignment.CenterVertically){
                            routine.name?.let {
                                Text(
                                    text = it,
                                    modifier = Modifier
                                        .padding(16.dp),
                                    textAlign = TextAlign.Center,
                                )
                            }
                            Spacer(Modifier.weight(1f))
                            IconButton(onClick = { navController.navigate("routine/"+routine.id.toString()) }) {
                                Icon(Icons.Outlined.PlayArrow, contentDescription = "Play Routine")
                            }
                        }

                    }
                }
            }
            else{
                Text(text = stringResource(id = R.string.no_routines))
            }
        }
    }
}