package com.example.todolist.todo.persistent.entity

import com.example.todolist.todo.controller.api.data.CreateTodoRequest
import com.example.todolist.todo.controller.api.data.UpdateTodoRequest
import com.example.todolist.todo.persistent.entity.data.TodoStatus
import com.example.todolist.todo.persistent.entity.data.TodoStatus.CREATED
import jakarta.persistence.*
import jakarta.persistence.GenerationType.IDENTITY
import org.hibernate.annotations.DynamicUpdate
import java.time.LocalDateTime
import java.time.LocalDateTime.now

@DynamicUpdate
@Entity
@Table(name = "todo")
class Todo(
    @Id
    @GeneratedValue(strategy = IDENTITY)
    val id: Long = 0,

    @Column(name = "user_id")
    val userId: Long,

    var title: String,

    var memo: String? = null,

    var startedAt: LocalDateTime,

    var status: TodoStatus = CREATED,

    val createdAt: LocalDateTime = now(),

    var updatedAt: LocalDateTime? = null,

    var deletedAt: LocalDateTime? = null,
) {
    fun update(updateTodoRequest: UpdateTodoRequest) {
        title = updateTodoRequest.title
        memo = updateTodoRequest.memo
        startedAt = updateTodoRequest.startedAt
        updatedAt = now()
    }

    fun updateStatus(todoStatus: TodoStatus) {
        status = todoStatus
        updatedAt = now()
    }

    fun delete() {
        deletedAt = now()
    }

    fun isDeleted() = deletedAt != null

    fun isNotDeleted() = deletedAt == null

    companion object {
        fun of(userId: Long, createTodoRequest: CreateTodoRequest): Todo {
            return Todo(
                userId = userId,
                title = createTodoRequest.title,
                memo = createTodoRequest.memo,
                startedAt = createTodoRequest.startedAt,
            )
        }
    }
}
