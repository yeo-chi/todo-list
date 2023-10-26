package com.example.todolist.user.persistent.repository

import com.example.todolist.user.persistent.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<UserEntity, Long> {
    fun findByIdAndDeletedAtIsNull(id: Long): UserEntity?

    fun findByUserIdAndDeletedAtIsNull(userId: String): UserEntity?

    fun findByNickName(nickName: String): UserEntity?
}
