package com.example.todolist.user.controller.api

import com.example.todolist.configuration.jwt.TokenProvider
import com.example.todolist.user.controller.api.data.*
import com.example.todolist.user.persistent.entity.User
import com.example.todolist.user.service.UserService
import org.springframework.http.HttpStatus.*
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/v1/users")
class UserApiController(
    private val userService: UserService,

    private val tokenProvider: TokenProvider,
) {
    @GetMapping("me")
    @ResponseStatus(OK)
    fun getMe(): UserResponse {
        return userService.getUser(id = 1).let(::UserResponse)
    }

    @PostMapping
    @ResponseStatus(CREATED)
    fun signUp(@RequestBody signUpUserRequest: SignUpUserRequest): SignUpUserResponse {
        return SignUpUserResponse(
            user = userService.signUp(signUpUserRequest = signUpUserRequest),
            tokenProvider = tokenProvider,
        )
    }

    @PostMapping("signIn")
    @ResponseStatus(OK)
    fun signIn(@RequestBody signInUserRequest: SignInUserRequest): SignInUserResponse {
        return SignInUserResponse(
            user = userService.signIn(signInUserRequest = signInUserRequest),
            tokenProvider = tokenProvider,
        )
    }

    @DeleteMapping("me")
    @ResponseStatus(NO_CONTENT)
    fun leave() {
        userService.leave(id = 1)
    }
}
