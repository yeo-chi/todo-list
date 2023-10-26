package com.example.todolist.exception

import org.springframework.http.HttpStatus.NOT_FOUND
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@RestControllerAdvice
class RestExceptionHandler : ResponseEntityExceptionHandler() {
    @ExceptionHandler(NoSuchElementException::class)
    @ResponseStatus(NOT_FOUND)
    fun noSuchElementException(e: NoSuchElementException) = ResponseEntity
        .status(HttpStatusCode.valueOf(404))
        .body(e.message)
}
