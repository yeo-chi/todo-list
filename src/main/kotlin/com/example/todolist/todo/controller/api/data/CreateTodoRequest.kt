package com.example.todolist.todo.controller.api.data

import com.example.todolist.todo.persistent.entity.TodoEntity
import com.fasterxml.jackson.annotation.JsonIgnore
import java.time.LocalDateTime

data class CreateTodoRequest(
    val title: String,

    val memo: String?,

    val startedAt: LocalDateTime,
) {
    @JsonIgnore
    fun toEntity(userId: Long): TodoEntity {
        return TodoEntity(
            userId = userId,
            title = title,
            memo = memo,
            startedAt = startedAt,
        )
    }
}
