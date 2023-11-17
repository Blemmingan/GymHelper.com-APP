package ar.edu.itba.example.api.ui.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextRange

@Composable
fun test(){
    Surface(modifier = Modifier.fillMaxSize()) {
        Text(text = "You are in the test!")
    }
}