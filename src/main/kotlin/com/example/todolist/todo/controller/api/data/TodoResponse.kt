package com.example.todolist.todo.controller.api.data

import com.example.todolist.todo.persistent.entity.TodoEntity
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
    constructor(todoEntity: TodoEntity) : this(
        id = todoEntity.id,
        title = todoEntity.title,
        memo = todoEntity.memo,
        startedAt = todoEntity.startedAt,
        status = todoEntity.status,
        createdAt = todoEntity.createdAt,
        updatedAt = todoEntity.updatedAt,
    )
}
