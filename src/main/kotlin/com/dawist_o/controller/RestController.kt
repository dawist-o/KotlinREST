package com.dawist_o.controller

import com.dawist_o.dao.Message
import com.dawist_o.service.MessageService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class RestController(val service: MessageService) {
    @GetMapping
    fun getAllMessages() = service.findAll()

    @PostMapping
    fun postMessage(@RequestBody msg: Message) {
        println(msg)
        service.post(msg)
    }
}