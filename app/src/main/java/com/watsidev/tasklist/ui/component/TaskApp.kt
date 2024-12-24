package com.watsidev.tasklist.ui.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.AssistChip
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.watsidev.tasklist.viewmodel.TaskViewModel


@Composable
fun TaskApp(viewModel: TaskViewModel = viewModel(), modifier: Modifier = Modifier) {
    val incompleteTasks by viewModel.incompleteTasks.collectAsState(emptyList())
    val completedTasks by viewModel.completedTasks.collectAsState(emptyList())
    val completedTaskCount by viewModel.completedTaskCount.collectAsState(0)
    val showCompleted by viewModel.showCompleted.collectAsState()

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        item {
            AddTask(onAddTask = { viewModel.addTask(it) })
        }

        items(incompleteTasks) { task ->
            TaskItem(
                task = task,
                onToggleCompleted = { viewModel.toggleTaskCompletion(it) },
                onDelete = { viewModel.deleteTask(it) }
            )
        }
        if (incompleteTasks.isNotEmpty() && completedTasks.isNotEmpty()) {
            item {
                Spacer(modifier = Modifier.height(16.dp))
                HorizontalDivider()
            }
        }
        item {
            AssistChip(
                onClick = { viewModel.toggleShowCompleted() },
                label = {
                    Icon(
                        imageVector = if (showCompleted) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                        contentDescription = null
                    )
                    Text(text = "Completado ($completedTaskCount)", color = Color.White)
                }
            )
        }
        item {
            AnimatedVisibility(
                visible = showCompleted,
                enter = expandVertically() + fadeIn(),
                exit = shrinkVertically() + fadeOut()
            ) {
                Column {
                    completedTasks.forEach { task ->
                        TaskItem(
                            task = task,
                            onToggleCompleted = { viewModel.toggleTaskCompletion(it) },
                            onDelete = { viewModel.deleteTask(it) }
                        )
                    }
                }
            }
        }
    }
}

