package com.example.todolist.todo.persistent.entity

import com.example.todolist.todo.controller.api.data.UpdateTodoRequest
import com.example.todolist.todo.persistent.entity.data.TodoStatus
import com.example.todolist.todo.persistent.entity.data.TodoStatus.CREATED
import jakarta.persistence.*
import jakarta.persistence.EnumType.STRING
import jakarta.persistence.GenerationType.IDENTITY
import org.hibernate.annotations.DynamicUpdate
import java.time.LocalDateTime
import java.time.LocalDateTime.now

@DynamicUpdate
@Entity
@Table(name = "todo")
class TodoEntity(
    @Id
    @GeneratedValue(strategy = IDENTITY)
    val id: Long = 0,

    @Column(name = "user_id")
    val userId: Long,

    var title: String,

    var memo: String? = null,

    var startedAt: LocalDateTime,

    @Enumerated(STRING)
    var status: TodoStatus = CREATED,

    val createdAt: LocalDateTime = now(),

    var updatedAt: LocalDateTime? = null,

    var deletedAt: LocalDateTime? = null,
) {
    @Transient
    val isDeleted = deletedAt != null

    @Transient
    val isNotDeleted = deletedAt == null

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

    fun userCheck(userId: Long) {
        require(this.userId == userId) { "등록된 아이디와 다른 사용자가 접근했습니다." }
    }
}
