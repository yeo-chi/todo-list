package com.example.todolist.user.controller.api.data

import com.example.todolist.user.persistent.entity.UserEntity

class UserResponse(
    userEntity: UserEntity
) {
    val name: String

    val nickName: String

    init {
        name = userEntity.name
        nickName = userEntity.nickName
    }
}
