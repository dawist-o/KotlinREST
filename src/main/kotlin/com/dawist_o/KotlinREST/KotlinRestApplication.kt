package com.dawist_o.KotlinREST

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
class KotlinRestApplication

fun main(args: Array<String>) {
    runApplication<KotlinRestApplication>(*args)
}

@RestController
class MessageResource {
    @GetMapping
    fun index(): List<Message> = listOf(
        Message(1, "1"),
        Message(2, "2"),
        Message(3, "3"),
        Message(4, "4"),
    )
}

data class Message(val id: Long, val text: String)
