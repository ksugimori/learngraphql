package com.example.learn.graphql.controller.graphql.input

data class UpdateTodoInput(
    val id: String,
    val title: String?,
    val isCompleted: Boolean?,
)
