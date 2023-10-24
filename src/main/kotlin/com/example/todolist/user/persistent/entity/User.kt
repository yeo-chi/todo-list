package com.example.todolist.user.persistent.entity

import com.example.todolist.todo.persistent.entity.Todo
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
    val todos: List<Todo> = listOf(),

    val createdAt: LocalDateTime = now(),

    var updatedAt: LocalDateTime? = null,

    var deletedAt: LocalDateTime? = null,
) {
    fun validPassword(password: String): Boolean {
        TODO("암호화 방식까지 정의하여 암호화된 비밀번호와 일치하는지 확인하기")
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
