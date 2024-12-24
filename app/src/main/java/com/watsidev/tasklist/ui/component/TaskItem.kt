package com.watsidev.tasklist.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.watsidev.tasklist.model.Task


@Composable
fun TaskItem(
    task: Task,
    onToggleCompleted: (Int) -> Unit,
    onDelete: (Int) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(65.dp)
            .clip(shape = RoundedCornerShape(4.dp))
            .background(Color(0xFF50514F).copy(alpha = .5f)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = task.isCompleted,
            onCheckedChange = { onToggleCompleted(task.id) }
        )
        Text(
            text = task.description,
            color = Color.White,
            modifier = Modifier.weight(1f),
            style = if (task.isCompleted) MaterialTheme.typography.bodyMedium.copy(
                textDecoration = TextDecoration.LineThrough
            ) else MaterialTheme.typography.bodyMedium
        )
        IconButton(onClick = { onDelete(task.id) }) {
            Icon(Icons.Default.Delete, contentDescription = "Delete Task")
        }
    }
    Spacer(modifier = Modifier.padding(4.dp))
}
