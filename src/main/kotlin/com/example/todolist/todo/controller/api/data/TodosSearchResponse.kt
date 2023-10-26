package com.example.todolist.todo.controller.api.data

import com.example.todolist.todo.persistent.entity.TodoEntity
import org.springframework.data.domain.Page

data class TodosSearchResponse(
    val limit: Int,

    val offset: Int,

    val total: Int,

    val todos: List<TodoResponse>,

    val order: Order,
) {
    constructor(todosSearchRequest: TodosSearchRequest, todoEntities: List<TodoEntity>): this (
        limit = todosSearchRequest.limit,
        offset = todosSearchRequest.offset,
        total = todoEntities.size,
        todos = todoEntities.map(::TodoResponse),
        order = todosSearchRequest.order,
    )

    constructor(todosSearchRequest: TodosSearchRequest, todos: Page<TodoEntity>): this (
        limit = todosSearchRequest.limit,
        offset = todosSearchRequest.offset,
        total = todos.totalElements.toInt(),
        todos = todos.content.map(::TodoResponse),
        order = todosSearchRequest.order,
    )
}
