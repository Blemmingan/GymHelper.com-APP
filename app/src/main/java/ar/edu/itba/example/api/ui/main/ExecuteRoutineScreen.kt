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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.OutlinedCard
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import ar.edu.itba.example.api.R



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun executeRoutineScreen(
    routineId: Int,
){
    //Api
    Scaffold(
        topBar = { executeTopBar("Nombre Rutina") },
    ) { padding ->
        executeMainScreen(Modifier.padding(padding))
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun executeTopBar(routineName: String){
    CenterAlignedTopAppBar(
        title = {
            Text(text = routineName,
                modifier = Modifier
                    .fillMaxWidth(),
                textAlign = TextAlign.Center)
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary
        ),
        navigationIcon = {
            IconButton(onClick = { /* goBack */ }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = null,
                )
            }
        },
    )
}

@Composable
fun executeMainScreen(
    modifier: Modifier = Modifier
){
    var exerciseDetail by remember { mutableStateOf(true) }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = modifier
            .background(MaterialTheme.colorScheme.secondary)
            .fillMaxSize()
    ){
        Spacer(Modifier.weight(0.15f, true))
        Text(
            text = "Ciclo",
            fontSize = 30.sp,
            color = MaterialTheme.colorScheme.onPrimary
        )
        if(exerciseDetail){
            Spacer(Modifier.weight(0.20f, true))
            Text(
                text = "Nombre Ejercicio",
                fontSize = 40.sp,
                color = MaterialTheme.colorScheme.onPrimary
            )
            Spacer(Modifier.weight(0.20f, true))
            Text(
                text = "Una larga decripcion de lo que trata dicho ejercicio y como debe realizarce",
                fontSize = 25.sp,
                color = MaterialTheme.colorScheme.onPrimary
            )
            Spacer(Modifier.weight(0.3f, true))
            Text(
                text = "Repeticiones",
                fontSize = 30.sp,
                color = MaterialTheme.colorScheme.onPrimary
            )
            Spacer(Modifier.weight(0.1f, true))
            Text(
                text = "Tiempo",
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
                text = "Ejercicio Actual",
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
                Text(
                    text = "Nombre ejercicio actual",
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 35.dp)
                )
            }
            Spacer(Modifier.weight(0.10f, true))
            Text(
                text = "Siguientes ejercicios",
                fontSize = 40.sp,
                color = MaterialTheme.colorScheme.onPrimary
            )
            Spacer(Modifier.weight(0.1f, true))
            /*
            if (exerciseList != null) {
                for (exercise in ExerciseList){
                    OutlinedCard(
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.surface
                        ),
                        border = BorderStroke(1.dp, Color.Black),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(100.dp),
                    ) {
                        Text(
                            text = "Nombre siguientes ejercicios",
                            fontSize = 20.sp,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 35.dp)
                        )
                    }
                }
            }
            */
        }
        Spacer(Modifier.weight(1f, true))

        Button(
            onClick = { /*Next exercise*/ },
            modifier = Modifier.size(300.dp, 100.dp),
        ) {
            Text(
                text = "Siguiente Ejercicio",
                fontSize = 30.sp,
                color = MaterialTheme.colorScheme.onPrimary
            )
        }

        Spacer(Modifier.weight(1f, true))
        Button(
            onClick = { exerciseDetail = !exerciseDetail },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = if(exerciseDetail) "Ver ejercicios" else "Ver ejercicio en detalle",
                fontSize = 15.sp,
                color = MaterialTheme.colorScheme.onPrimary
            )
        }
    }
}

@Preview()
@Composable
fun topBarPreview() {
    executeTopBar("Nombre Rutina")
}

@Preview()
@Composable
fun executeRoutineScreenPreview() {
    executeRoutineScreen(routineId = 1)
}

