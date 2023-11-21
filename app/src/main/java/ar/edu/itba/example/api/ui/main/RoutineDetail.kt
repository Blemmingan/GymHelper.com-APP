package ar.edu.itba.example.api.ui.main

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import ar.edu.itba.example.api.R
import ar.edu.itba.example.api.data.model.Cycle
import ar.edu.itba.example.api.data.model.CycleExercise
import ar.edu.itba.example.api.data.model.Exercise
import ar.edu.itba.example.api.data.model.Routine
import ar.edu.itba.example.api.util.getViewModelFactory
import java.io.StringReader


@Composable
fun RoutineScreen(
    viewModel: MainViewModel = viewModel(factory = getViewModelFactory()),
    routineId: Int
) {
    //falta ver que este logueado
    viewModel.getRoutine(routineId)
    if (viewModel.uiState.currentRoutine==null){
        Text("rutina inexistente")
    } else if (viewModel.uiState.error == null) {
        viewModel.getCycles(routineId)
        if (viewModel.uiState.error == null) {
            RoutineDetail(viewModel)
        } else {
            Text("cycle")
        }
    } else {
        Text("error")
    }

}

@Composable
fun RoutineDetail(
    viewModel: MainViewModel,
) {
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
    ){
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.DarkGray)
        ) {
            Text(
                text = viewModel.uiState.currentRoutine?.name?:"err",
                modifier = Modifier.padding(vertical = 18.dp),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
        viewModel.uiState.currentRoutineDetails.forEach{cycleWithExercises -> CycleView(cycleWithExercises.cycle!!, cycleWithExercises.exercises!!) }
        /*if (viewModel.uiState.isFetching){
            CircularProgressIndicator(
                color = MaterialTheme.colorScheme.secondary,
                modifier = Modifier.padding(top = 10.dp)
            )
        } else if (viewModel.uiState.currentRoutineDetails.isEmpty()){
            //no cycles
            Text("no cycles")
        }*/ //TODO: ver como hacer que se vaya el indicador de progreso
    }

}

@Composable
fun CycleView(cycle: Cycle, exercises: List<CycleExercise>){
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .background(MaterialTheme.colorScheme.primary)
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxHeight()
        ) {
            Text(
                text = cycle.name!!,
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(10.dp)
            )
        }

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxHeight()
                .widthIn(min = 140.dp)
                .background(Color.DarkGray)
        ) {
            Text(text = stringResource(R.string.repetitions,cycle.repetitions!!),
                color = Color.White,
                modifier = Modifier.padding(10.dp))
        }
    }
    if (exercises.isEmpty()) {
        Text(
            text = stringResource(id = R.string.noExercises),
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 10.dp, bottom = 20.dp)
        )

    } else {
        for(exercise in exercises){
            ExerciseView(exercise = exercise)
        }
    }


}

@Composable
fun ExerciseView(exercise: CycleExercise) {
    Column(
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.Start,
    ) {
        Text(
            text = exercise.exercise?.name!!,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (exercise.duration!! > 0) {
                Text(
                    text = stringResource(R.string.duration,exercise.duration!!),
                    color = Color.Gray
                )
            }

            if (exercise.repetitions!! > 0) {
                if (exercise.duration!! > 0) {
                    Text(
                        text = " - ",
                        color = Color.Gray
                    )
                }

                Text(
                    text = stringResource(R.string.duration,exercise.repetitions!!),
                    color = Color.Gray
                )
            }
        }
    }
}



