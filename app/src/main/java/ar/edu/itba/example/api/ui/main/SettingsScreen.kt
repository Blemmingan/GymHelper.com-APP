package ar.edu.itba.example.api.ui.main

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.sharp.AccountCircle
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import ar.edu.itba.example.api.util.getViewModelFactory
import ar.edu.itba.example.api.R

@Composable
fun SettingsScreen(
    navController: NavHostController,
    viewModel: MainViewModel = viewModel(factory = getViewModelFactory())
){
    viewModel.getCurrentUser()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(6.dp),
    ){

        Icon(
            imageVector = Icons.Sharp.AccountCircle,
            contentDescription = "Account",
            modifier = Modifier
                .size(200.dp)
                .align(Alignment.CenterHorizontally)
        )

        viewModel.uiState.currentUser?.let {
            Text(
                text = it.username,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }

        viewModel.uiState.currentUser?.let {
            Text(
                text = it.email,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }

        OutlinedCard(
            modifier = Modifier
                .height(56.dp)
                .padding(horizontal = 32.dp),
            border = BorderStroke(1.dp, Color.Gray),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
            shape = RoundedCornerShape(8.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = stringResource(id = R.string.logout),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
                IconButton(
                    onClick = {
                        viewModel.logout()
                        navController.navigate("login")
                              },
                    modifier = Modifier.align(Alignment.CenterVertically)
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowForward,
                        contentDescription = "Setting Action"
                    )
                }
            }
        }
    }
}