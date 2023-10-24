package com.example.todolist.user.controller.api.data

data class SignUpUserRequest(
    val userId: String,

    val password: String,

    val rePassword: String,

    val name: String,

    val nickName: String,
)
