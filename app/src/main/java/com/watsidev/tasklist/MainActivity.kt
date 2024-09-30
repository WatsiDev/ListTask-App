package com.watsidev.tasklist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.AssistChip
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.watsidev.tasklist.ui.theme.TaskListTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TaskListTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
//                    topBar = {
//                        TopAppBar(
//                            title = { Text(text = "Task App") } // Título de la barra superior
//                        )
//                    }
                ) { innerPadding ->
                    TaskList(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CardText(task: String, onDelete: () -> Unit, editTask: () -> Unit, onComplete: () -> Boolean) {
    var checkedBox by remember {
        mutableStateOf(Icons.Filled.Check)
    }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 2.dp, horizontal = 10.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(4.dp))
                .background(color = Color(0xFF303030))
                .height(70.dp)
                .clickable { }
                .combinedClickable(
                    onLongClick = { onDelete() },
                    onClick = { }
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = {
                if (checkedBox == Icons.Filled.Check) {
                    checkedBox = Icons.Filled.CheckCircle
                    onComplete()
                    checkedBox = Icons.Filled.Check
                }
            }) {
                Icon(checkedBox, contentDescription = null)
            }
            Text(
                text = task,
                color = Color.White,
                fontWeight = FontWeight.W400,
                fontSize = 15.sp
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                IconButton(onClick = { onDelete() }) {
                    Icon(imageVector = Icons.Filled.Delete, contentDescription = null)
                }
            }
        }

    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CardTextComplete(
    task: String,
    onDelete: () -> Unit,
    editTask: () -> Unit,
    inComplete: () -> Unit
) {
    var checkedBox by remember {
        mutableStateOf(Icons.Filled.CheckCircle)
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 2.dp, horizontal = 10.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(4.dp))
                .background(color = Color(0xFF303030))
                .height(70.dp)
                .clickable { }
                .combinedClickable(
                    onLongClick = { onDelete() },
                    onClick = { }
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = {
                if (checkedBox == Icons.Filled.CheckCircle) {
                    checkedBox = Icons.Filled.Check
                    inComplete()
                    checkedBox = Icons.Filled.CheckCircle
                }
            }) {
                Icon(checkedBox, contentDescription = null)
            }
            Text(
                text = task,
                color = Color.White,
                fontWeight = FontWeight.W400,
                fontSize = 15.sp
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                IconButton(onClick = { onDelete() }) {
                    Icon(imageVector = Icons.Filled.Delete, contentDescription = null)
                }
            }
        }

    }
}

@Composable
fun TaskList(modifier: Modifier) {
    var TaskAdd = remember {
        mutableStateListOf("Tarea ejemplo.")
    }

    var addTask by remember {
        mutableStateOf("")
    }

    var taskComplete = remember {
        mutableStateListOf("Aqui se añaden las tareas completadas")
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.bgimg),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 40.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextField(
                value = addTask, onValueChange = { addTask = it },
                placeholder = { Text(text = "Añadir tarea...") },
                singleLine = true,
                keyboardActions = KeyboardActions(onDone = null),
                modifier = Modifier
                    .padding(start = 8.dp)
                    .weight(1f)
            )
            FloatingActionButton(
                onClick = {
                    if (addTask.isNotBlank()) {
                        TaskAdd.add(addTask)
                        addTask = ""
                    }
                }, modifier = Modifier
                    .clip(RoundedCornerShape(percent = 100))
                    .padding(8.dp)
            ) {
                Icon(Icons.Filled.Add, contentDescription = null)
            }
        }
        Spacer(modifier = Modifier.padding(4.dp))
        HorizontalDivider()
        Spacer(modifier = Modifier.padding(2.dp))

        LazyColumn(
            state = rememberLazyListState(),
            modifier = Modifier

        ) {
            items(TaskAdd) { item ->
                CardText(
                    task = item,
                    onDelete = { TaskAdd.remove(item) },
                    editTask = { },
                    onComplete = {
                        taskComplete.add(item)
                        TaskAdd.remove(item)
                    })
            }
        }

        AssistChip(onClick = { /*TODO*/ }, label = { Text(text = "Completado ${taskComplete.size}") }, leadingIcon = {
            Icon(
                Icons.Default.KeyboardArrowDown,
                contentDescription = null
            )
        },
            modifier = Modifier
                .padding(8.dp)
        )

        LazyColumn(
            state = rememberLazyListState(),
            modifier = Modifier

        ) {
            items(taskComplete) { item ->
                CardTextComplete(
                    task = item,
                    onDelete = { taskComplete.remove(item) },
                    editTask = { },
                    inComplete = {
                        TaskAdd.add(item)
                        taskComplete.remove(item)
                    })
            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    TaskListTheme {
        TaskList(modifier = Modifier)
    }
}