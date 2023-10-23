package com.example.todolist.todo.controller.api

import com.example.todolist.todo.controller.api.data.TodoResponse
import org.springframework.http.HttpStatus.*
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/v1/users/me/todo")
class TodoApiController {
    @GetMapping
    @ResponseStatus(OK)
    private fun getTodoList(): List<TodoResponse> {

    }

    @GetMapping("{id}")
    @ResponseStatus(OK)
    private fun getTodo(@PathVariable("id") id: Long): TodoResponse {

    }

    @PostMapping
    @ResponseStatus(CREATED)
    private fun createTodo(): TodoResponse {

    }

    @PutMapping
    @ResponseStatus(OK)
    private fun updateTodo() {

    }

    @DeleteMapping
    @ResponseStatus(NO_CONTENT)
    private fun deleteTodo() {

    }
}