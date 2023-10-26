package com.example.todolist.todo.controller.api

import com.example.todolist.expansion.getIdToLong
import com.example.todolist.todo.controller.api.data.*
import com.example.todolist.todo.persistent.entity.Todo
import com.example.todolist.todo.persistent.entity.data.TodoStatus
import com.example.todolist.todo.service.TodoService
import org.springframework.http.HttpStatus.*
import org.springframework.web.bind.annotation.*
import java.security.Principal

@RestController
@RequestMapping("api/v1/users/me/todos")
class TodoApiController(
    private val todoService: TodoService,
) {
    @GetMapping
    @ResponseStatus(OK)
    private fun getTodos(
        principal: Principal,
        todosSearchRequest: TodosSearchRequest,
    ): TodosSearchResponse {
        if (todosSearchRequest.isAllSearch) {
            return todoService.getTodos(
                userId = principal.getIdToLong(),
                todosSearchRequest = todosSearchRequest,
            ).let {
                TodosSearchResponse(
                    todosSearchRequest = todosSearchRequest,
                    todos = it,
                )
            }
        }

        return todoService.getTodosPage(
            userId = principal.getIdToLong(),
            todosSearchRequest = todosSearchRequest,
        ).let {
            TodosSearchResponse(
                todosSearchRequest = todosSearchRequest,
                todos = it,
            )
        }
    }

    @GetMapping("{id}")
    @ResponseStatus(OK)
    private fun getTodo(
        @PathVariable("id") id: Long,
        principal: Principal,
    ): TodoResponse {
        return todoService.getTodo(
            id = id,
            userId = principal.getIdToLong(),
        ).let(::TodoResponse)
    }

    @PostMapping
    @ResponseStatus(CREATED)
    private fun createTodo(
        principal: Principal,
        @RequestBody createTodoRequest: CreateTodoRequest
    ): TodoResponse {
        return todoService.createTodo(
            todo = Todo.of(
                userId = principal.getIdToLong(),
                createTodoRequest = createTodoRequest,
            ),
        ).let(::TodoResponse)
    }

    @PutMapping("{id}")
    @ResponseStatus(OK)
    private fun updateTodo(
        @PathVariable("id") id: Long,
        principal: Principal,
        @RequestBody updateTodoRequest: UpdateTodoRequest,
    ) {
        return todoService.updateTodo(
            id = id,
            userId = principal.getIdToLong(),
            updateTodoRequest = updateTodoRequest,
        )
    }

    @PatchMapping("{id}")
    @ResponseStatus(OK)
    private fun updateStatusTodo(
        @PathVariable("id") id: Long,
        principal: Principal,
        todoStatus: TodoStatus,
    ) {
        return todoService.updateStatusTodo(
            id = id,
            userId = principal.getIdToLong(),
            todoStatus = todoStatus,
        )
    }

    @DeleteMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    private fun deleteTodo(
        @PathVariable("id") id: Long,
        principal: Principal,
    ) {
        return todoService.deleteTodo(
            id = id,
            userId = principal.getIdToLong(),
        )
    }
}
