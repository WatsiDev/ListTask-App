package com.watsidev.tasklist.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AddTask(onAddTask: (String) -> Unit) {
    var text by remember { mutableStateOf("") }
    val shape = RoundedCornerShape(8.dp)
    val borderColor = Color.White

    Row(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        TextField(
            value = text,
            onValueChange = { text = it },
            modifier = Modifier
                .weight(1f)
                .height(56.dp),
            placeholder = { Text("Nueva tarea", color = borderColor.copy(alpha = 0.7f)) },
            shape = shape,
            colors = TextFieldDefaults.colors(
                focusedTextColor = borderColor,
                unfocusedContainerColor = Color.Transparent,
                focusedContainerColor = Color.Transparent,
                focusedIndicatorColor = borderColor,
                unfocusedIndicatorColor = borderColor,
                cursorColor = borderColor,
            )
        )
        Button(
            onClick = {
                if (text.isNotBlank()) {
                    onAddTask(text)
                    text = ""
                }
            },
            modifier = Modifier
                .height(56.dp),
            shape = shape,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFAC296D),
                contentColor = borderColor
            )
        ) {
            Text("+", fontSize = 24.sp)
        }
    }
}
