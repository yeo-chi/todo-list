package com.example.todolist.todo.controller.api

import com.example.todolist.todo.controller.api.data.CreateTodoRequest
import com.example.todolist.todo.controller.api.data.TodoResponse
import com.example.todolist.todo.controller.api.data.UpdateTodoRequest
import com.example.todolist.todo.persistent.entity.Todo
import com.example.todolist.todo.persistent.entity.data.TodoStatus
import com.example.todolist.todo.service.TodoService
import org.springframework.http.HttpStatus.*
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/v1/users/me/todo")
class TodoApiController(
    private val todoService: TodoService,
) {
    @GetMapping
    @ResponseStatus(OK)
    private fun getTodoList(): List<TodoResponse> {
        return todoService.getTodos(userId = 1).map(::TodoResponse)
    }

    @GetMapping("{id}")
    @ResponseStatus(OK)
    private fun getTodo(@PathVariable("id") id: Long): TodoResponse {
        return todoService.getTodo(id = id).let(::TodoResponse)
    }

    @PostMapping
    @ResponseStatus(CREATED)
    private fun createTodo(@RequestBody createTodoRequest: CreateTodoRequest): TodoResponse {
        return todoService.createTodo(
            todo = Todo.of(
                userId = 1,
                createTodoRequest = createTodoRequest,
            ),
        ).let(::TodoResponse)
    }

    @PutMapping("{id}")
    @ResponseStatus(OK)
    private fun updateTodo(@PathVariable("id") id: Long, @RequestBody updateTodoRequest: UpdateTodoRequest) {
        return todoService.updateTodo(id = id, updateTodoRequest = updateTodoRequest)
    }

    @PatchMapping("{id}")
    @ResponseStatus(OK)
    private fun updateStatusTodo(@PathVariable("id") id: Long, todoStatus: TodoStatus) {
        return todoService.updateStatusTodo(id = id, todoStatus = todoStatus)
    }

    @DeleteMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    private fun deleteTodo(@PathVariable("id") id: Long) {
        return todoService.deleteTodo(id = id)
    }
}
