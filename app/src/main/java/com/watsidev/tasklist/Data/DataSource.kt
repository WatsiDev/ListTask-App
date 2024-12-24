package com.watsidev.tasklist.Data

import com.watsidev.tasklist.model.Task

object TaskDataSource {
    fun loadTasks(): List<Task> {
        return listOf(
            Task(1,"Example task"),
            Task(2,"Example complete task", true)
        )
    }
}