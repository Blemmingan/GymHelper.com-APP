package ar.edu.itba.example.api.ui.main


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import ar.edu.itba.example.api.data.model.Routine
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(navController: NavHostController,
               viewModel: MainViewModel = viewModel(factory = getViewModelFactory())
){

    var orderBy by remember { mutableStateOf("date") }
    viewModel.getRoutines(orderBy = orderBy)


    Column {
        val displayedOrderList = listOf(
            stringResource(id = R.string.orderByDate),
            stringResource(id = R.string.orderByScore),
            stringResource(id = R.string.orderByDifficulty),
            stringResource(id = R.string.orderByCategory),
            stringResource(id = R.string.orderByName)
        )
        val orderList = listOf("date", "score", "difficulty", "category", "name")

        MyDropDownMenu(
            label = stringResource(id = R.string.orderBy),
            elements = displayedOrderList,
            selectedText = displayedOrderList.elementAtOrNull(orderList.indexOfFirst { it == orderBy }) ?: "",
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White, CircleShape)
        ) { selectedString ->
            val idx = displayedOrderList.indexOf(selectedString)
            orderBy = if (idx >= 0) orderList[idx] else "name"
        }
        RoutineList(navController, viewModel.uiState)
    }

}

@Composable
fun RoutineList(navController: NavHostController,uiState: MainUiState){

    val routinesList = uiState.currentUserRoutines

    val state = rememberScrollState()


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(state),
        verticalArrangement = Arrangement.spacedBy(6.dp),
    ) {
            if (!routinesList.isNullOrEmpty()) {
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
                                Column {
                                    Text(
                                        text = it,
                                        modifier = Modifier
                                            .padding(16.dp),
                                        textAlign = TextAlign.Center,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 20.sp
                                    )

                                    Row(
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically,
                                        modifier = Modifier.padding(5.dp)
                                    ) {
                                        Text(
                                            text = if (routine.score == null || routine.score == 0f) " - " else routine.score.toString(),
                                            modifier = Modifier.padding(horizontal = 5.dp)
                                        )

                                        Icon(
                                            imageVector = Icons.Filled.Star,
                                            contentDescription = "routineScore",
                                        )
                                    }
                                }
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


