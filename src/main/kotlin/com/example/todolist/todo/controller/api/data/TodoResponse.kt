package com.example.todolist.todo.controller.api.data

import com.example.todolist.todo.persistent.entity.Todo
import com.example.todolist.todo.persistent.entity.data.TodoStatus
import java.time.LocalDateTime

data class TodoResponse(
    val id: Long,

    val title: String,

    val memo: String?,

    val startedAt: LocalDateTime,

    val status: TodoStatus,

    val createdAt: LocalDateTime,

    val updatedAt: LocalDateTime?,
) {
    constructor(todo: Todo) : this(
        id = todo.id,
        title = todo.title,
        memo = todo.memo,
        startedAt = todo.startedAt,
        status = todo.status,
        createdAt = todo.createdAt,
        updatedAt = todo.updatedAt,
    )
}
