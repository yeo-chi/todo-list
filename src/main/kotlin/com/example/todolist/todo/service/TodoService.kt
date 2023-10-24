package com.example.todolist.todo.service

import com.example.todolist.todo.controller.api.data.UpdateTodoRequest
import com.example.todolist.todo.persistent.entity.Todo
import com.example.todolist.todo.persistent.entity.data.TodoStatus
import com.example.todolist.todo.persistent.repository.TodoRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class TodoService(
    private val todoRepository: TodoRepository,
) {
    @Transactional(readOnly = true)
    fun getTodos(userId: Long): List<Todo> {
        return todoRepository.findAllByUserId(userId = userId)
            .filter { it.isNotDeleted() }
    }

    @Transactional(readOnly = true)
    fun getTodo(id: Long, userId: Long): Todo {
        return todoRepository.findByIdOrNull(id = id)
            ?.also { it.userCheck(userId = userId) }
            ?: throw NoSuchElementException()
    }

    @Transactional
    fun createTodo(todo: Todo): Todo {
        return todoRepository.save(todo)
    }

    @Transactional
    fun updateTodo(id: Long, userId: Long, updateTodoRequest: UpdateTodoRequest) {
        getTodo(id = id, userId = userId)
            .apply { update(updateTodoRequest) }
    }

    @Transactional
    fun updateStatusTodo(id: Long, userId: Long, todoStatus: TodoStatus) {
        getTodo(id = id, userId = userId)
            .apply { updateStatus(todoStatus = todoStatus) }
    }

    @Transactional
    fun deleteTodo(id: Long, userId: Long) {
        getTodo(id = id, userId = userId)
            .apply { delete() }
    }
}
