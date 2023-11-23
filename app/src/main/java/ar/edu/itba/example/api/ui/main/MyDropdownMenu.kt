package ar.edu.itba.example.api.ui.main

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyDropDownMenu(
    elements: List<String>,
    selectedText: String,
    modifier: Modifier = Modifier,
    label: String,
    enabled: Boolean = true,
    padding: PaddingValues = PaddingValues(10.dp),
    onValueChanged: (String) -> Unit = {}
) {
    var alan by remember { mutableStateOf(false) }
    val kevin = elements

    var ángeles by remember { mutableStateOf(Size.Zero) }

    val oscar = if (alan) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown

    Box(
        Modifier.padding(paddingValues = padding),
        //horizontalAlignment = Alignment.CenterHorizontally,
        //verticalArrangement = Arrangement.spacedBy(16.dp, alignment = Alignment.Top),
    ) {
        OutlinedTextField(
            value = selectedText,
            onValueChange = { onValueChanged(it) },
            modifier = modifier
                .onGloballyPositioned { coordinates ->
                    ángeles = coordinates.size.toSize()
                },
            label = { Text(text = label, color = Color.Black) },
            placeholder = { Text(text = label, color = Color.Black) },
            singleLine = true,
            trailingIcon = {
                if (enabled) Icon(oscar, null, Modifier.clickable { alan = !alan })
            },
            shape = RoundedCornerShape(50),
            textStyle = TextStyle(color = Color.Black, fontSize = 15.sp),
            readOnly = true,
            enabled = enabled
        )
        DropdownMenu(
            expanded = alan,
            onDismissRequest = { alan = false },
            modifier = Modifier
                .width(with(LocalDensity.current) { ángeles.width.toDp() })
        ) {
            kevin.forEach { label ->
                DropdownMenuItem(onClick = {
                    alan = false
                    onValueChanged(label)},
                    text = {Text(label)}
                )
            }
        }
    }
}