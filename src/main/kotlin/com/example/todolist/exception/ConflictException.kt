package com.example.todolist.exception

import org.springframework.http.HttpStatus.CONFLICT
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(CONFLICT)
class ConflictException(
    override val message: String,
) : RuntimeException()
