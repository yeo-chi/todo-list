package com.example.todolist.todo.controller.api.data

import com.example.todolist.todo.persistent.entity.Todo
import com.example.todolist.todo.persistent.entity.data.TodoStatus
import java.time.LocalDateTime

class TodoResponse(
    todo: Todo,
) {
    val id: Long

    val title: String

    val memo: String

    val startedAt: LocalDateTime

    val status: TodoStatus

    val createdAt: LocalDateTime

    val updatedAt: LocalDateTime?

    init {
        id = todo.id
        title = todo.title
        memo = todo.memo
        startedAt = todo.startedAt
        status = todo.status
        createdAt = todo.createdAt
        updatedAt = todo.updatedAt
    }
}
