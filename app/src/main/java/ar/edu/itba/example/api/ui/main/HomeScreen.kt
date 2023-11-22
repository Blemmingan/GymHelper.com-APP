package ar.edu.itba.example.api.ui.main


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import ar.edu.itba.example.api.R
import ar.edu.itba.example.api.util.getViewModelFactory
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import ar.edu.itba.example.api.data.model.Routine
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(navController: NavHostController,
               viewModel: MainViewModel = viewModel(factory = getViewModelFactory())
){
    viewModel.getCurrentUserRoutines()
    var routinesList : List<Routine>? = viewModel.uiState.currentUserRoutines
    val composableScope = rememberCoroutineScope()
    LaunchedEffect(key1 = routinesList, block = {composableScope.launch { routinesList = viewModel.getRoutines() }})

    RoutineList(navController, routinesList)
}

@Composable
fun RoutineList(navController: NavHostController, routinesList : List<Routine>?) {

    val state = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(state),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(
                modifier = Modifier.padding(8.dp),
                text = stringResource(id = R.string.your_routines),
                textAlign = TextAlign.Center
            )
        }
        
            if (routinesList != null) {
                for (routine in routinesList) {
                    OutlinedCard(
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.surface
                        ),
                        border = BorderStroke(1.dp, Color.Black),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(100.dp),
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
                            routine.name?.let {
                                Text(
                                    text = it,
                                    modifier = Modifier
                                        .padding(16.dp),
                                    textAlign = TextAlign.Center,
                                )
                            }
                            Spacer(Modifier.weight(1f))
                            Column {
                                IconButton(onClick = { navController.navigate("routine/" + routine.id.toString()) }) {
                                    Icon(
                                        Icons.Outlined.PlayArrow,
                                        contentDescription = "Play Routine",
                                        Modifier.fillMaxSize()
                                    )
                                }

                                IconButton(onClick = { /*Favoritear*/ }) {
                                    Icon(
                                        Icons.Outlined.Star,
                                        contentDescription = "favourite",
                                    )
                                }
                            }

                        }

                    }
                }
            } else {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                    Text(
                        text = stringResource(id = R.string.no_routines),
                        textAlign = TextAlign.Center,
                        fontStyle = FontStyle.Italic
                    )
                }

            }
        }
    }