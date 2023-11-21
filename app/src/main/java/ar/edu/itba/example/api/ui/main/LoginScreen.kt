package ar.edu.itba.example.api.ui.main

import android.provider.Settings.Global.getString
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import ar.edu.itba.example.api.R
import ar.edu.itba.example.api.util.getViewModelFactory


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun loginScreen(
    navController: NavHostController,
    viewModel: MainViewModel = viewModel(factory = getViewModelFactory())
){

    var userText by rememberSaveable {
        mutableStateOf("")
    }
    var passwordText by rememberSaveable {
        mutableStateOf("")
    }
    var accepted by rememberSaveable {
        mutableStateOf(false)
    }
    var attemptedLogin by rememberSaveable {
        mutableStateOf(false)
    }
    val uiState = viewModel.uiState

    Column (horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize(1f)){
        Text(text = "GYMHELPER.COM", fontSize = 30.sp, modifier = Modifier.padding(10.dp))
        TextField(value = userText, onValueChange = {userText = it},
            label = { Text(text = "Username")}, singleLine = true, modifier = Modifier.padding(5.dp))
        TextField(value = passwordText, onValueChange = {passwordText = it},
            label = { Text(text = "Password")}, singleLine = true , modifier = Modifier.padding(5.dp) )
        ElevatedButton(onClick = { viewModel.login(userText, passwordText); attemptedLogin = true}) {
            Text(text = "LOGIN")
        }
        if(attemptedLogin){
            LoginDialog(uiState.isAuthenticated) { attemptedLogin = false  }
        }
        if(uiState.isAuthenticated){
            navController.navigate("home")
        }
        ElevatedButton(onClick = { navController.navigate("home") }) {
            Text(text = "DEBUG: GO HOME")
        }

    }
}



@ExperimentalMaterial3Api
@Composable
fun LoginDialog(accepted:Boolean, updateAttempt: (attempted: Boolean) -> Unit) {
    val openDialogue = remember { mutableStateOf(true)}
    if(openDialogue.value){
        AlertDialog(
            onDismissRequest = {
                // Dismiss the dialog when the user clicks outside the dialog or on the back
                // button. If you want to disable that functionality, simply use an empty
                // onDismissRequest.
                openDialogue.value = false
                updateAttempt(false)
            },
            title = {
                if(accepted){
                    Text(text = stringResource(id = R.string.login_success))
                    
                }
                else{
                    Text(text = stringResource(id = R.string.login_fail))
                }

            },
            text = {
                if(accepted){
                    Text(text = stringResource(id = R.string.redirecting))
                }
                else{
                    Text(text = stringResource(id = R.string.retry))
                }

            },

            confirmButton = {
                if(!accepted){
                    TextButton(
                        onClick = {
                            openDialogue.value = false
                            updateAttempt(false)
                        }
                    ) {
                        Text(text = stringResource(id = R.string.OKButton))
                    }
                }
            },

        )

    }
}
