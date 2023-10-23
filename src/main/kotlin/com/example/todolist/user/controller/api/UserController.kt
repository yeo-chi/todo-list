package com.example.todolist.user.controller.api

import com.example.todolist.user.controller.api.data.SignInUserRequest
import com.example.todolist.user.controller.api.data.UserResponse
import com.example.todolist.user.service.UserService
import org.springframework.http.HttpStatus.*
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/v1/users")
class UserController(
    private val userService: UserService,
) {
    @GetMapping("me")
    @ResponseStatus(OK)
    fun getMe(): UserResponse {

    }

    @PostMapping
    @ResponseStatus(CREATED)
    fun signUp(): String {

    }

    @PostMapping("signIn")
    @ResponseStatus(OK)
    fun signIn(@RequestBody signInUserRequest: SignInUserRequest): String {

    }

    @DeleteMapping("me")
    @ResponseStatus(NO_CONTENT)
    fun leave() {

    }
}
