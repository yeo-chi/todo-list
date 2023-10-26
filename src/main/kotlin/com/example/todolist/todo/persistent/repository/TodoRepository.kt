package com.example.todolist.todo.persistent.repository

import com.example.todolist.todo.persistent.entity.Todo
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TodoRepository : JpaRepository<Todo, Long> {
    fun findAllByUserIdAndDeletedAtIsNull(userId: Long, sort: Sort): List<Todo>
    fun findAllByUserIdAndDeletedAtIsNull(userId: Long, pageable: Pageable): Page<Todo>
}
