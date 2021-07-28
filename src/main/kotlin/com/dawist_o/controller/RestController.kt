package com.dawist_o.controller

import com.dawist_o.dao.Message
import com.dawist_o.dao.User
import com.dawist_o.service.MessageService
import com.dawist_o.service.UserService
import org.springframework.web.bind.annotation.*
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("kotlin")
class RestController(val messageService: MessageService, val userService: UserService) {
    @GetMapping("/messages")
    fun getAllMessages() = messageService.findAll()

    @PostMapping("/messages")
    fun postMessage(@RequestBody msg: Message) {
        messageService.post(msg)
    }

    @GetMapping("/users")
    fun getAllUsers() = userService.findUsers()

    @PostMapping("/users")
    fun saveUser(@RequestBody user: User) {
        userService.saveUser(user)
    }
}