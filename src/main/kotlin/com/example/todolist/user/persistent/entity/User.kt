package com.example.todolist.user.persistent.entity

import com.example.todolist.todo.persistent.entity.Todo
import jakarta.persistence.*
import jakarta.persistence.FetchType.LAZY
import jakarta.persistence.GenerationType.IDENTITY
import org.hibernate.annotations.DynamicUpdate
import java.time.LocalDateTime
import java.time.LocalDateTime.now

@DynamicUpdate
@Entity
@Table(name = "user")
class User(
    @Id
    @GeneratedValue(strategy = IDENTITY)
    val id: Long = 0,

    val userId: String,

    val password: String,

    val name: String,

    val nickName: String,

    @OneToMany(fetch = LAZY)
    @JoinColumn(name = "user_id")
    val todos: List<Todo>,

    val createdAt: LocalDateTime = now(),

    var updatedAt: LocalDateTime? = null,

    var deletedAt: LocalDateTime? = null,
) {
    fun leave() {
        deletedAt = now()
    }

    fun isLeave() = deletedAt != null
}
