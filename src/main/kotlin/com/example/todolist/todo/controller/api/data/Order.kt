package com.example.todolist.todo.controller.api.data

import org.springframework.data.domain.Sort.Direction

enum class Order(val direction: Direction) {
    ASC(Direction.ASC),
    DESC(Direction.DESC),
    ;

    companion object {
        fun of(direction: Direction): Order {
            return when (direction) {
                Direction.ASC -> ASC
                Direction.DESC -> DESC
            }
        }
    }
}
