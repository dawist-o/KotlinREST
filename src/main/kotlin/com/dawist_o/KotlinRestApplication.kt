package com.dawist_o

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KotlinRestApplication

fun main(args: Array<String>) {
    runApplication<KotlinRestApplication>(*args)
}
