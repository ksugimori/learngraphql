package com.example.learn.graphql.controller.graphql.relay.response

import com.example.learn.graphql.controller.graphql.relay.Node
import com.example.learn.graphql.controller.graphql.relay.encodeBase64
import com.example.learn.graphql.entity.Todo

data class TodoResponse(
    override val id: String,
    val userId: Long,
    val title: String,
    val isCompleted: Boolean,
) : Node {
    companion object {
        /**
         * [Todo] エンティティを [TodoResponse] に変換する。
         */
        fun from(todo: Todo): TodoResponse = TodoResponse(
            id = "Todo:${todo.id}".encodeBase64(),
            userId = todo.userId,
            title = todo.title,
            isCompleted = todo.isCompleted,
        )
    }
}
