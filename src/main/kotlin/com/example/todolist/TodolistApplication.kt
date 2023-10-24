package com.example.todolist

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@SpringBootApplication
@ConfigurationPropertiesScan(basePackages = ["com.example.todolist.configuration.jwt"])
class TodolistApplication

fun main(args: Array<String>) {
    runApplication<TodolistApplication>(*args)
}
