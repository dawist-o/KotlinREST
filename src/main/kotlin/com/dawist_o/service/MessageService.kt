package com.dawist_o.service

import com.dawist_o.dao.Message
import com.dawist_o.repository.MessageRepository
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Service

@Service
class MessageService(val repository: MessageRepository) {

    fun findAll() = repository.findAll()

    fun post(message: Message) {
        repository.save(message)
    }

    fun findById(id: Long) = repository.findById(id)
}