package com.example.todolist.user.controller.web

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class UserWebController {
    @GetMapping
    fun getMain(): String {
        return "main"
    }

    @GetMapping("signInPage")
    fun getSignIn(): String {
        return "signIn"
    }

    @GetMapping("signUpPage")
    fun getSignUp(): String {
        return "signUp"
    }
}
