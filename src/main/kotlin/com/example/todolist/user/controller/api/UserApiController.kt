package com.example.todolist.user.controller.api

import com.example.todolist.configuration.jwt.TokenProvider
import com.example.todolist.expansion.getIdToLong
import com.example.todolist.user.controller.api.data.*
import com.example.todolist.user.service.UserService
import org.springframework.http.HttpStatus.*
import org.springframework.web.bind.annotation.*
import java.security.Principal

@RestController
@RequestMapping("api/v1/users")
class UserApiController(
    private val userService: UserService,

    private val tokenProvider: TokenProvider,
) {
    @GetMapping("me")
    @ResponseStatus(OK)
    fun getMe(principal: Principal): UserResponse {
        return userService.getUser(id = principal.getIdToLong()).let(::UserResponse)
    }

    @PostMapping
    @ResponseStatus(CREATED)
    fun signUp(@RequestBody signUpUserRequest: SignUpUserRequest): SignUpUserResponse {
        userService.checkAlreadyUserId(userId = signUpUserRequest.userId)
        userService.checkAlreadyNickName(nickName = signUpUserRequest.nickName)

        return SignUpUserResponse(
            userEntity = userService.signUp(signUpUserRequest = signUpUserRequest),
            tokenProvider = tokenProvider,
        )
    }

    @PostMapping("signIn")
    @ResponseStatus(OK)
    fun signIn(@RequestBody signInUserRequest: SignInUserRequest): SignInUserResponse {
        return SignInUserResponse(
            userEntity = userService.signIn(signInUserRequest = signInUserRequest),
            tokenProvider = tokenProvider,
        )
    }

    @DeleteMapping("me")
    @ResponseStatus(NO_CONTENT)
    fun leave(principal: Principal) {
        userService.leave(id = principal.getIdToLong())
    }
}
