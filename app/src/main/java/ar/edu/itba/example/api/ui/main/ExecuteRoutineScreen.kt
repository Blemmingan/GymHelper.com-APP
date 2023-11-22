package ar.edu.itba.example.api.ui.main




import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.OutlinedCard
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import ar.edu.itba.example.api.R
import ar.edu.itba.example.api.util.getViewModelFactory


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExecuteRoutineScreen(
    viewModel: MainViewModel = viewModel(factory = getViewModelFactory()),
    routineId: Int,
    navController: NavHostController
){
    viewModel.getRoutine(routineId)
    viewModel.getCycles(routineId)
    if(viewModel.uiState.currentRoutineDetails.isNotEmpty()){
        Scaffold(
            topBar = { viewModel.uiState.currentRoutine?.name?.let { ExecuteTopBar(routineName = it, navController = navController) } },
        ) { padding ->
            ExecuteMainScreen(
                modifier = Modifier.padding(padding),
                cyclesWithExercise = viewModel.uiState.currentRoutineDetails
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExecuteTopBar(
    routineName: String,
    navController: NavHostController
){
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = routineName,
                modifier = Modifier
                    .fillMaxWidth(),
                textAlign = TextAlign.Center)
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary
        ),
        navigationIcon = {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = null,
                )
            }
        },
    )
}

@Composable
fun ExecuteMainScreen(
    modifier: Modifier = Modifier,
    cyclesWithExercise: List<CycleWithExercises>
){
    var exerciseDetail by remember { mutableStateOf(true) }
    var cycleIndex by remember { mutableIntStateOf(0) }
    var exerciseIndex by remember { mutableIntStateOf(0) }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = modifier
            .background(MaterialTheme.colorScheme.secondary)
            .fillMaxSize()
    ){
        Spacer(Modifier.weight(0.15f, true))
        cyclesWithExercise[cycleIndex].cycle?.name?.let {
            Text(
                text = it,
                fontSize = 30.sp,
                color = MaterialTheme.colorScheme.onPrimary
            )
        }
        if(exerciseDetail){
            Spacer(Modifier.weight(0.20f, true))
            cyclesWithExercise[cycleIndex].exercises?.get(exerciseIndex)?.exercise?.name?.let {
                Text(
                    text = it,
                    fontSize = 40.sp,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }
            Spacer(Modifier.weight(0.20f, true))
            cyclesWithExercise[cycleIndex].exercises?.get(exerciseIndex)?.exercise?.detail?.let {
                Text(
                    text = it,
                    fontSize = 25.sp,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }
            Spacer(Modifier.weight(0.3f, true))
            Text(
                text = cyclesWithExercise[cycleIndex].exercises?.get(exerciseIndex)?.repetitions.toString(),
                fontSize = 30.sp,
                color = MaterialTheme.colorScheme.onPrimary
            )
            Spacer(Modifier.weight(0.1f, true))
            Text(
                text = cyclesWithExercise[cycleIndex].exercises?.get(exerciseIndex)?.duration.toString(),
                fontSize = 30.sp,
                color = MaterialTheme.colorScheme.onPrimary
            )
            Spacer(Modifier.weight(0.4f, true))
            IconButton(
                onClick = { /*Pause timer*/ },
                ) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_pause_24),
                    contentDescription = null,
                    modifier = Modifier.size(100.dp)
                )
            }
        } else{
            Spacer(Modifier.weight(0.10f, true))
            Text(
                text = stringResource(id = R.string.current_exercise),
                fontSize = 40.sp,
                color = MaterialTheme.colorScheme.onPrimary
            )
            Spacer(Modifier.weight(0.1f, true))
            OutlinedCard (
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                ),
                border = BorderStroke(1.dp, Color.Black),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp),
            ){
                cyclesWithExercise[cycleIndex].exercises?.get(exerciseIndex)?.exercise?.name?.let {
                    Text(
                        text = it,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 35.dp)
                    )
                }
            }
            Spacer(Modifier.weight(0.10f, true))
            Text(
                text = stringResource(id = R.string.next_exercises),
                fontSize = 40.sp,
                color = MaterialTheme.colorScheme.onPrimary
            )
            Spacer(Modifier.weight(0.1f, true))

            var i = exerciseIndex + 1
            var j = cycleIndex + 1

            while (j < cyclesWithExercise.size && i < cyclesWithExercise[cycleIndex].exercises?.size!!){
                OutlinedCard(
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.surface
                        ),
                        border = BorderStroke(1.dp, Color.Black),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(100.dp),
                    ) {
                    cyclesWithExercise[j].exercises?.get(i)?.exercise?.name?.let {
                        Text(
                            text = it,
                            fontSize = 20.sp,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 35.dp)
                        )
                    }
                }
                i++
                j++
            }

        }
        Spacer(Modifier.weight(1f, true))


        if(cycleIndex + 1 < cyclesWithExercise.size && exerciseIndex + 1 < cyclesWithExercise[cycleIndex].exercises?.size!!) {
            Button(
                onClick = {
                    if(exerciseIndex + 1 < cyclesWithExercise[cycleIndex].exercises?.size!!){
                        cycleIndex++
                        exerciseIndex = 0
                    } else {
                        exerciseIndex++
                    }
                },
                modifier = Modifier.size(300.dp, 100.dp),
            ) {
                Text(
                    text = stringResource(id = R.string.next_exercise),
                    fontSize = 30.sp,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }
        }

        Spacer(Modifier.weight(1f, true))
        Button(
            onClick = { exerciseDetail = !exerciseDetail },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = if(exerciseDetail) stringResource(id = R.string.view_all_exercises) else stringResource(id = R.string.view_detailed_exercise),
                fontSize = 15.sp,
                color = MaterialTheme.colorScheme.onPrimary
            )
        }
    }
}

