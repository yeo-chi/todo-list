package com.example.todolist.user.service

import com.example.todolist.user.controller.api.data.SignInUserRequest
import com.example.todolist.user.persistent.entity.User
import com.example.todolist.user.persistent.repository.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserService(
    private val userRepository: UserRepository,
) {
    @Transactional(readOnly = true)
    fun getUser(id: Long): User {
        return userRepository.findByIdOrNull(id = id) ?: throw NoSuchElementException()
    }

    @Transactional
    fun signUp(user: User): User {
        return userRepository.save(user)
    }

    @Transactional(readOnly = true)
    fun signIn(signInUserRequest: SignInUserRequest): User {
        return userRepository.findByUserId(userId = signInUserRequest.userId)
            .also { it.validPassword(signInUserRequest.password) }
    }

    @Transactional
    fun leave(id: Long) {
        getUser(id = id)
            .apply { leave() }
    }
}
