package com.watsidev.tasklist.viewmodel

import androidx.lifecycle.ViewModel
import com.watsidev.tasklist.Data.TaskDataSource
import com.watsidev.tasklist.model.Task
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map

class TaskViewModel : ViewModel() {
    private val _tasks = MutableStateFlow(TaskDataSource.loadTasks())
    val tasks: StateFlow<List<Task>> = _tasks

    val incompleteTasks: Flow<List<Task>> = _tasks.map { tasks ->
        tasks.filter { !it.isCompleted }
    }

    val completedTasks: Flow<List<Task>> = _tasks.map { tasks ->
        tasks.filter { it.isCompleted }
    }

    val completedTaskCount: Flow<Int> = _tasks.map { tasks ->
        tasks.count { it.isCompleted }
    }

    private val _showCompleted = MutableStateFlow(true)
    val showCompleted: StateFlow<Boolean> = _showCompleted

    fun toggleShowCompleted(){
        _showCompleted.value = !_showCompleted.value
    }

    fun addTask(description: String) {
        val newTask = Task(
            id = (_tasks.value.maxOfOrNull { it.id } ?: 0) + 1,
            description = description
        )
        _tasks.value += newTask
    }

    fun toggleTaskCompletion(taskId: Int) {
        _tasks.value = _tasks.value.map { task ->
            if (task.id == taskId) task.copy(isCompleted = !task.isCompleted) else task
        }
    }

    fun deleteTask(taskId: Int) {
        _tasks.value = _tasks.value.filter { it.id != taskId }
    }
}