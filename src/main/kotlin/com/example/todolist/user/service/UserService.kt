package com.example.todolist.user.service

import com.example.todolist.user.persistent.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository,
) {

}