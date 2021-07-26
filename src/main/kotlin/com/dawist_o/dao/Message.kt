package com.dawist_o.dao

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table


@Table("messages")
data class Message(
    @Id
    val id: Long?,
    val msg: String
)
