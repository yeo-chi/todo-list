package com.example.todolist.user.controller.api

import com.example.todolist.user.controller.api.data.SignInUserRequest
import com.example.todolist.user.controller.api.data.SignUpUserRequest
import com.example.todolist.user.controller.api.data.UserResponse
import com.example.todolist.user.persistent.entity.User
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
        return userService.getUser(id = 1).let(::UserResponse)
    }

    @PostMapping
    @ResponseStatus(CREATED)
    fun signUp(@RequestBody signUpUserRequest: SignUpUserRequest): String {
        userService.signUp(
            user = User.of(signUpUserRequest = signUpUserRequest),
        )

        TODO("JWT 를 이용하여 토큰을 발급해야함")
    }

    @PostMapping("signIn")
    @ResponseStatus(OK)
    fun signIn(@RequestBody signInUserRequest: SignInUserRequest): String {
        userService.signIn(signInUserRequest = signInUserRequest)

        TODO("JWT 를 이용하여 토큰을 발급해야함")
    }

    @DeleteMapping("me")
    @ResponseStatus(NO_CONTENT)
    fun leave() {
        userService.leave(id = 1)
    }
}
