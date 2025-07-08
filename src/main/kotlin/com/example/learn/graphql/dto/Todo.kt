package com.example.learn.graphql.dto

data class Todo(
    val id: Long? = null,
    val userId: Long,
    val title: String,
    val description: String?
)
