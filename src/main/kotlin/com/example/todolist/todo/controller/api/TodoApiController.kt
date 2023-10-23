package com.example.todolist.todo.controller.api

import org.springframework.http.HttpStatus.*
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/v1/todo")
class TodoApiController {
    @GetMapping
    @ResponseStatus(OK)
    private fun getTodoList() {

    }

    @GetMapping("{id}")
    @ResponseStatus(OK)
    private fun getTodo(@PathVariable("id") id: Long) {

    }

    @PostMapping
    @ResponseStatus(CREATED)
    private fun createTodo() {

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
