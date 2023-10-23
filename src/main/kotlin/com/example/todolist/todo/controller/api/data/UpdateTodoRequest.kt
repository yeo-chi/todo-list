package com.example.todolist.todo.controller.api.data

import java.time.LocalDateTime

data class UpdateTodoRequest(
    val title: String,

    val memo: String?,

    val startedAt: LocalDateTime,
)
