package com.example.todolist.user.persistent.entity

import com.example.todolist.todo.persistent.entity.TodoEntity
import com.example.todolist.user.controller.api.data.SignUpUserRequest
import jakarta.persistence.*
import jakarta.persistence.FetchType.LAZY
import jakarta.persistence.GenerationType.IDENTITY
import org.apache.commons.lang3.StringUtils
import org.hibernate.annotations.DynamicUpdate
import org.springframework.security.crypto.password.PasswordEncoder
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
    val todoEntities: List<TodoEntity> = listOf(),

    val createdAt: LocalDateTime = now(),

    var updatedAt: LocalDateTime? = null,

    var deletedAt: LocalDateTime? = null,
) {
    fun validPassword(password: String, passwordEncoder: PasswordEncoder) {
        check(passwordEncoder.matches(password, this.password)) { "비밀번호가 일치하지 않습니다" }
    }

    fun leave() {
        deletedAt = now()
    }

    fun isLeave() = deletedAt != null

    companion object {
        fun of(signUpUserRequest: SignUpUserRequest, passwordEncoder: PasswordEncoder): User {
            return with(signUpUserRequest) {
                require(StringUtils.equals(password, rePassword)) { "비밀번호가 일치하지 않습니다." }

                User(
                    userId = userId,
                    password = passwordEncoder.encode(password),
                    name = name,
                    nickName = nickName,
                )
            }
        }
    }
}
