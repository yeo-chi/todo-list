package com.example.todolist.user.controller.api.data

import com.example.todolist.configuration.jwt.TokenProvider
import com.example.todolist.user.persistent.entity.UserEntity

class SignUpUserResponse(
    userEntity: UserEntity,
    tokenProvider: TokenProvider,
) {
    val id: Long

    val userId: String

    val nickName: String

    val token: String

    init {
        id = userEntity.id
        userId = userEntity.userId
        nickName = userEntity.nickName
        token = tokenProvider.createJwtToken(userSpecification = id.toString())
    }
}
