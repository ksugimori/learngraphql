package com.example.learn.graphql.controller.graphql.input

data class UpdateTodoInput(
    val id: Long,
    val title: String?,
    val isCompleted: Boolean?,
)
