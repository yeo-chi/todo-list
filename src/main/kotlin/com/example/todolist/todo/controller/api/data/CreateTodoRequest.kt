package com.example.todolist.todo.controller.api.data

import java.time.LocalDateTime

data class CreateTodoRequest(
    var userId: Long = 0,

    val title: String,

    val memo: String?,

    val startedAt: LocalDateTime,
)
