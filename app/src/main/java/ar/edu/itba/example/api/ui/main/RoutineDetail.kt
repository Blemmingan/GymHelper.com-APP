package ar.edu.itba.example.api.ui.main

import android.content.Context
import android.content.Intent
import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import ar.edu.itba.example.api.R
import ar.edu.itba.example.api.data.model.Cycle
import ar.edu.itba.example.api.data.model.CycleExercise
import ar.edu.itba.example.api.util.getViewModelFactory


@Composable
fun RoutineScreen(
    viewModel: MainViewModel = viewModel(factory = getViewModelFactory()),
    routineId: Int,
    navController: NavHostController
) {
    if(!viewModel.uiState.isAuthenticated){
        navController.navigate("login")
    }
    else{
        viewModel.getRoutine(routineId)
        if (viewModel.uiState.currentRoutine==null){
            Text(stringResource(id = R.string.routine_fail))
        } else if (viewModel.uiState.error == null) {
            viewModel.getCycles(routineId)
            if (viewModel.uiState.error == null) {
                RoutineDetail(
                    viewModel = viewModel,
                    navController = navController,
                    routineId = routineId
                )
            } else {
                Text(stringResource(id = R.string.cycle))
            }
        } else {
            Text(stringResource(id = R.string.error))
        }
    }


}

@Composable
fun RoutineDetail(
    viewModel: MainViewModel,
    context: Context = LocalContext.current as ComponentActivity,
    navController: NavHostController,
    routineId: Int
) {
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = viewModel.uiState.currentRoutine?.name ?: "",
            fontSize = 40.sp,
            textAlign = TextAlign.Center
        )

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
                .requiredHeight(40.dp)
        ) {
            Surface(
                modifier = Modifier
                    .padding(end = 5.dp)
                    .fillMaxHeight(),
                tonalElevation = 3.dp
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(5.dp)
                ) {
                    Text(
                        text = if (viewModel.uiState.currentRoutine?.score == null || viewModel.uiState.currentRoutine?.score == 0f) " - " else viewModel.uiState.currentRoutine?.score.toString(),
                        modifier = Modifier.padding(horizontal = 5.dp)
                    )

                    Icon(
                        imageVector = Icons.Filled.Star,
                        contentDescription = "routineScore",
                    )
                }
            }
            Surface(
                modifier = Modifier
                    .padding(start = 5.dp)
                    .fillMaxHeight(),
                tonalElevation = 3.dp,
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.padding(5.dp)
                ) {
                    val difficulty: String? = getDifString(viewModel.uiState.currentRoutine?.difficulty)
                    Text(
                        text = difficulty?: stringResource(id = R.string.unspecified),
                        modifier = Modifier.padding(horizontal = 5.dp)
                    )
                }
            }
        }
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp)
        ) {

            IconButton(onClick = {
                navController.navigate("routine/execution/$routineId")
            }
            ) {
                Icon(
                    imageVector = Icons.Filled.PlayArrow,
                    contentDescription = "execute",
                )
            }
            IconButton(onClick = {
                val link =
                    "http://www.GymHelper.com/routine/${viewModel.uiState.currentRoutine?.id}"
                val intent = Intent(Intent.ACTION_SEND)
                intent.type = "text/plain"
                intent.putExtra(Intent.EXTRA_TEXT, link)
                context.startActivity(Intent.createChooser(intent, null))
            }
            ) {
                Icon(
                    imageVector = Icons.Filled.Share,
                    contentDescription = stringResource(id = R.string.share),
                )
            }

        }

        if (viewModel.uiState.currentRoutine?.detail != null) {
            Divider(
                color = MaterialTheme.colorScheme.secondary,
                modifier = Modifier
                    .padding(vertical = 10.dp)
                    .fillMaxWidth(0.9f)
            )
            Text(
                text = viewModel.uiState.currentRoutine?.detail?:"",
                textAlign = TextAlign.Left,
                modifier = Modifier.padding(vertical = 10.dp)
            )
        }

        Divider(
            color = MaterialTheme.colorScheme.secondary,
            modifier = Modifier
                .padding(vertical = 10.dp)
                .fillMaxWidth(0.9f)
        )

        viewModel.uiState.currentRoutineDetails.forEach{cycleWithExercises -> CycleView(cycleWithExercises.cycle!!, cycleWithExercises.exercises!!) }
        if (viewModel.uiState.currentRoutineDetails.isEmpty()){
            Text(
                text = stringResource(id = R.string.noCycles),
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 10.dp, bottom = 20.dp)
            )
        }
    }

}

@Composable
fun CycleView(cycle: Cycle, exercises: List<CycleExercise>){
    Column() {
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
                Text(
                    text = stringResource(R.string.repetitions, cycle.repetitions!!),
                    color = Color.White,
                    modifier = Modifier.padding(10.dp)
                )
            }
        }
        if (exercises.isEmpty()) {
            Text(
                text = stringResource(id = R.string.noExercises),
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 10.dp, bottom = 20.dp)
            )

        } else {
            for (exercise in exercises) {
                ExerciseView(exercise = exercise)
            }
        }
    }


}

@Composable
fun ExerciseView(exercise: CycleExercise) {
    Column(
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

            if (exercise.repetitions!! > 0 && exercise.duration!! > 0) {
                Text(
                    text = " - ",
                    color = Color.Gray
                )

                Text(
                    text = stringResource(R.string.repetitions,exercise.repetitions!!),
                    color = Color.Gray
                )
            }
        }
    }
}

@Composable
fun getDifString(str: String?): String?{
    when(str){
        null -> return null
        "rookie" -> return stringResource(id = R.string.rookie)
        "beginner" -> return stringResource(id = R.string.begginner)
        "intermediate" -> return stringResource(id = R.string.intermediate)
        "advanced" -> return stringResource(id = R.string.advanced)
        "expert" -> return stringResource(id = R.string.expert)
    }
    return null
}

