package com.example.todolist.user.controller.api.data

import com.example.todolist.configuration.jwt.TokenProvider
import com.example.todolist.user.persistent.entity.User

class SignInUserResponse(
    user: User,
    tokenProvider: TokenProvider,
) {
    val id: Long

    val userId: String

    val nickName: String

    val token: String

    init {
        id = user.id
        userId = user.userId
        nickName = user.nickName
        token = tokenProvider.createJwtToken(userSpecification = id.toString())
    }
}
