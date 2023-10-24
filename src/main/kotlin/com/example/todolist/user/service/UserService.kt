package com.example.todolist.user.service

import com.example.todolist.user.controller.api.data.SignInUserRequest
import com.example.todolist.user.controller.api.data.SignUpUserRequest
import com.example.todolist.user.persistent.entity.User
import com.example.todolist.user.persistent.repository.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserService(
    private val userRepository: UserRepository,

    private val passwordEncoder: PasswordEncoder,
) {
    @Transactional(readOnly = true)
    fun getUser(id: Long): User {
        return userRepository.findByIdOrNull(id = id) ?: throw NoSuchElementException()
    }

    @Transactional
    fun signUp(signUpUserRequest: SignUpUserRequest): User {
        return userRepository.save(
            User.of(
                signUpUserRequest = signUpUserRequest,
                passwordEncoder = passwordEncoder,
            ),
        )
    }

    @Transactional(readOnly = true)
    fun signIn(signInUserRequest: SignInUserRequest): User {
        return userRepository.findByUserId(userId = signInUserRequest.userId)
            .also { it.validPassword(password = signInUserRequest.password, passwordEncoder = passwordEncoder) }
    }

    @Transactional
    fun leave(id: Long) {
        getUser(id = id)
            .apply { leave() }
    }
}
