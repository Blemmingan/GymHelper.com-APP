package ar.edu.itba.example.api.ui.main

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun loginScreen(){
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

    Column (horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize(1f)){
        Text(text = "GYMHELPER.COM", fontSize = 30.sp, modifier = Modifier.padding(10.dp))
        TextField(value = userText, onValueChange = {userText = it},
            label = { Text(text = "Username")}, singleLine = true, modifier = Modifier.padding(5.dp))
        TextField(value = passwordText, onValueChange = {passwordText = it},
            label = { Text(text = "Password")}, singleLine = true , modifier = Modifier.padding(5.dp) )
        ElevatedButton(onClick = { accepted = attemptLogin2(userText, passwordText); attemptedLogin = true}) {
            Text(text = "LOGIN")
        }
        if(attemptedLogin){
            LoginDialog(accepted = accepted) { attemptedLogin = false  }

        }


    }
}

fun attemptLogin2(username: String, password:String) :Boolean{
    // Aca se hacen las validaciones
    return username!= "a" && password != "a"
}

@ExperimentalMaterial3Api
@Composable
fun LoginDialog(accepted:Boolean, updateAttempt: (attempted: Boolean) -> Unit) {
    var openDialogue = remember { mutableStateOf(true)}
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
                    Text(text = "Login Successful")
                }
                else{
                    Text(text = "Login Failed")
                }

            },
            text = {
                if(accepted){
                    Text(text = "Redirecting...")
                }
                else{
                    Text(text = "Please try again")
                }

            },

            confirmButton = {
                if(!accepted){
                    TextButton(
                        onClick = {
                            openDialogue.value = false
                        }
                    ) {
                        Text("Retry")
                    }
                }
            },

        )

    }
}
