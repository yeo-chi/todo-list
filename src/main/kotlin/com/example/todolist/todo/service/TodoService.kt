package com.example.todolist.todo.service

import com.example.todolist.todo.controller.api.data.TodosSearchRequest
import com.example.todolist.todo.controller.api.data.UpdateTodoRequest
import com.example.todolist.todo.persistent.entity.Todo
import com.example.todolist.todo.persistent.entity.data.TodoStatus
import com.example.todolist.todo.persistent.repository.TodoRepository
import org.springframework.data.domain.Page
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class TodoService(
    private val todoRepository: TodoRepository,
) {
    @Transactional(readOnly = true)
    fun getTodos(userId: Long, todosSearchRequest: TodosSearchRequest): List<Todo> {
        return todoRepository.findAllByUserIdAndDeletedAtIsNull(
            userId = userId,
            sort = todosSearchRequest.getSort(),
        )
    }

    @Transactional(readOnly = true)
    fun getTodosPage(userId: Long, todosSearchRequest: TodosSearchRequest): Page<Todo> {
        return todoRepository.findAllByUserIdAndDeletedAtIsNull(
            userId = userId,
            pageable = todosSearchRequest.getPageable(),
        )
    }

    @Transactional(readOnly = true)
    fun getTodo(id: Long, userId: Long): Todo {
        return todoRepository.findByIdOrNull(id = id)
            ?.also { it.userCheck(userId = userId) }
            ?: throw NoSuchElementException("할 일을 찾을 수 없습니다.")
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
