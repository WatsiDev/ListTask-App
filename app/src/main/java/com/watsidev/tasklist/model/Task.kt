package com.watsidev.tasklist.model

data class Task(
    val id: Int,
    val description: String,
    val isCompleted: Boolean = false
)
