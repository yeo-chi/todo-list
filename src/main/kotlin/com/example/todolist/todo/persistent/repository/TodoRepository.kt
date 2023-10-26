package com.example.todolist.todo.persistent.repository

import com.example.todolist.todo.persistent.entity.TodoEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TodoRepository : JpaRepository<TodoEntity, Long> {
    fun findAllByUserIdAndDeletedAtIsNull(userId: Long, sort: Sort): List<TodoEntity>
    fun findAllByUserIdAndDeletedAtIsNull(userId: Long, pageable: Pageable): Page<TodoEntity>
}
