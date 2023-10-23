package com.example.todolist.user.controller.api

import org.springframework.http.HttpStatus.*
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/v1/users")
class UserController {
    @GetMapping("me")
    @ResponseStatus(OK)
    fun getMe() {

    }

    @PostMapping
    @ResponseStatus(CREATED)
    fun signUp() {

    }

    @DeleteMapping
    @ResponseStatus(NO_CONTENT)
    fun leave() {

    }
}
