package com.example.todolist.todo.persistent.entity

import com.example.todolist.todo.persistent.entity.data.TodoStatus
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType.IDENTITY
import jakarta.persistence.Id
import jakarta.persistence.Table
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

    val userId: Long,

    val title: String,

    val memo: String,

    val startedAt: LocalDateTime,

    var status: TodoStatus,

    val createdAt: LocalDateTime = now(),

    var updatedAt: LocalDateTime? = null,

    var deletedAt: LocalDateTime? = null,
) {
    fun updateStatus(status: TodoStatus) {
        this.status = status
        this.updatedAt = now()
    }

    fun delete() {
        this.deletedAt = now()
    }
}
