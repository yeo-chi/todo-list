package com.example.todolist.user.service

import com.example.todolist.user.controller.api.data.SignInUserRequest
import com.example.todolist.user.controller.api.data.SignUpUserRequest
import com.example.todolist.user.persistent.entity.UserEntity
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
    fun getUser(id: Long): UserEntity {
        return userRepository.findByIdOrNull(id = id) ?: throw NoSuchElementException("회원을 찾을 수 없습니다.")
    }

    @Transactional
    fun signUp(signUpUserRequest: SignUpUserRequest): UserEntity {
        return userRepository.save(
            UserEntity.of(
                signUpUserRequest = signUpUserRequest,
                passwordEncoder = passwordEncoder,
            ),
        )
    }

    @Transactional(readOnly = true)
    fun signIn(signInUserRequest: SignInUserRequest): UserEntity {
        return userRepository.findByUserId(userId = signInUserRequest.userId)
            ?.also { it.validPassword(password = signInUserRequest.password, passwordEncoder = passwordEncoder) }
            ?: throw NoSuchElementException("아이디를 찾을 수 없습니다.")
    }

    @Transactional
    fun leave(id: Long) {
        getUser(id = id)
            .apply { leave() }
    }
}
