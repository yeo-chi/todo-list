package com.example.todolist.expansion

import java.security.Principal

fun Principal.getIdToLong() = this.name.toLong()
