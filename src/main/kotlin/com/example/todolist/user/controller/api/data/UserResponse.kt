package com.example.todolist.user.controller.api.data

import com.example.todolist.user.persistent.entity.User

class UserResponse(
    user: User
) {
    val name: String

    val nickName: String

    init {
        name = user.name
        nickName = user.nickName
    }
}
