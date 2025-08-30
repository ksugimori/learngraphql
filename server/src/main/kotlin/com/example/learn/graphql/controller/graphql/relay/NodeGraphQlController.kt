package com.example.learn.graphql.controller.graphql.relay

import com.example.learn.graphql.controller.graphql.TodoGraphQlController
import com.example.learn.graphql.controller.graphql.UserGraphQlController
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Controller

@Controller
class NodeGraphQlController(
    private val userGraphQlController: UserGraphQlController,
    private val todoGraphQlController: TodoGraphQlController,
) {
    @QueryMapping
    fun node(@Argument id: String): Node? {
        val (typeName, _) = try {
            id.decodeNodeId()
        } catch (e: IllegalArgumentException) {
            return null
        }

        return when (typeName) {
            "User" -> userGraphQlController.user(id)
            "Todo" -> todoGraphQlController.todo(id)
            else -> null
        }
    }
}
