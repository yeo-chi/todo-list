package com.example.todolist.todo.controller.api.data

import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort

data class TodosSearchRequest(
    val limit: Int,

    val offset: Int,

    val order: Order,
) {
    @JsonIgnore
    val isAllSearch = limit == -1

    @JsonIgnore
    fun getSort() = Sort.by(order.direction, "startedAt")

    @JsonIgnore
    fun getPageable() = PageRequest.of(offset, limit, order.direction, "startedAt")
}
