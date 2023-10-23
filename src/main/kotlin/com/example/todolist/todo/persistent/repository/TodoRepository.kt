package com.example.todolist.todo.persistent.repository

import com.example.todolist.todo.persistent.entity.Todo
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TodoRepository : JpaRepository<Todo, Long> {
    fun findAllByUserId(userId: Long): List<Todo>
}
