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
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.OutlinedCard
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import ar.edu.itba.example.api.R
import ar.edu.itba.example.api.util.getViewModelFactory
import kotlinx.coroutines.delay


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
                cyclesWithExercise = viewModel.uiState.currentRoutineDetails,
                navController = navController
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
    cyclesWithExercise: List<CycleWithExercises>,
    navController: NavHostController,
){
    var exerciseDetail by rememberSaveable { mutableStateOf(true) }
    var cycleIndex by rememberSaveable { mutableIntStateOf(0) }
    var exerciseIndex by rememberSaveable { mutableIntStateOf(0) }
    val hasTimer = (cyclesWithExercise[cycleIndex].exercises?.get(exerciseIndex)?.duration != 0)
    var isPaused by rememberSaveable { mutableStateOf(true) }
    var timeLeft by rememberSaveable { mutableStateOf(cyclesWithExercise[cycleIndex].exercises?.get(exerciseIndex)?.duration) }

    LaunchedEffect(key1 = timeLeft, key2 = isPaused) {
        while (timeLeft!! > 0 && !isPaused) {
            delay(1000L)
            timeLeft = timeLeft!! - 1
        }
    }


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

            if(cyclesWithExercise[cycleIndex].exercises?.get(exerciseIndex)?.exercise?.detail != cyclesWithExercise[cycleIndex].exercises?.get(exerciseIndex)?.exercise?.name) {
                cyclesWithExercise[cycleIndex].exercises?.get(exerciseIndex)?.exercise?.detail?.let {
                    Text(
                        text = it,
                        fontSize = 25.sp,
                        color = MaterialTheme.colorScheme.onPrimary,
                        textAlign = TextAlign.Center
                    )
                }
            }

            Spacer(Modifier.weight(0.3f, true))

            if(cyclesWithExercise[cycleIndex].exercises?.get(exerciseIndex)?.repetitions != 0) {
                Text(
                    text = stringResource(R.string.repetitions,
                        cyclesWithExercise[cycleIndex].exercises?.get(exerciseIndex)?.repetitions
                            ?: 0
                    ),
                    fontSize = 30.sp,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }

            Spacer(Modifier.weight(0.1f, true))
            if(hasTimer) {
                Text(
                    text = if(timeLeft != 0) "$timeLeft s" else stringResource(id = R.string.timer_finished),
                    fontSize = 30.sp,
                    color = MaterialTheme.colorScheme.onPrimary
                )

                Spacer(Modifier.weight(0.4f, true))

                IconButton(
                    onClick = { isPaused = !isPaused },
                ) {
                    if (isPaused) {
                        Icon(
                            imageVector = Icons.Filled.PlayArrow,
                            contentDescription = null,
                            modifier = Modifier.size(1000.dp)
                        )
                    } else {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_pause_24),
                            contentDescription = null,
                            modifier = Modifier.size(1000.dp)
                        )
                    }
                }
            }
        } else {
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
            var j = cycleIndex
            var total = 0
            if(i >= cyclesWithExercise[cycleIndex].exercises?.size!!){
                j++
                i = 0
            }



            while (j < cyclesWithExercise.size && total < 3){
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
                if(i + 1 < cyclesWithExercise[j].exercises?.size!!){
                    i++
                } else {
                    j++
                    i = 0
                }
                total++
            }

        }

        Spacer(Modifier.weight(1f, true))

        if(cycleIndex + 1 < cyclesWithExercise.size || exerciseIndex + 1 < cyclesWithExercise[cycleIndex].exercises?.size!!) {
            Button(
                onClick = {
                    if(exerciseIndex + 1 < cyclesWithExercise[cycleIndex].exercises?.size!!){
                        exerciseIndex++
                    } else {
                        cycleIndex++
                        exerciseIndex = 0
                    }
                    timeLeft = cyclesWithExercise[cycleIndex].exercises?.get(exerciseIndex)?.duration
                    isPaused = true
                },
                modifier = Modifier.size(250.dp, 100.dp),
            ) {
                Text(
                    text = stringResource(id = R.string.next_exercise),
                    fontSize = 30.sp,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }
        } else{
            Button(
                onClick = { navController.popBackStack() },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Red,
                ),
                modifier = Modifier.size(250.dp, 100.dp)
            ){
                Text(
                    text = stringResource(id = R.string.end_routine),
                    fontSize = 30.sp,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }
        }

        Spacer(Modifier.weight(0.1f, true))

        Button(
            onClick = { exerciseDetail = !exerciseDetail },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = if(exerciseDetail) stringResource(id = R.string.view_all_exercises) else stringResource(id = R.string.view_detailed_exercise),
                fontSize = 20.sp,
                color = MaterialTheme.colorScheme.onPrimary
            )
        }
    }
}