package com.example.learn.graphql.response

import com.example.learn.graphql.controller.graphql.NodeId
import com.example.learn.graphql.entity.Todo

data class TodoResponse(
    override val id: NodeId,
    val userId: Long,
    val title: String,
    val isCompleted: Boolean
) : Node {
    companion object {
        /**
         * [Todo] エンティティを [TodoResponse] に変換する。
         */
        fun from(todo: Todo): TodoResponse = TodoResponse(
            id = NodeId(todo.javaClass.simpleName, todo.id ?: 0L),
            userId = todo.userId,
            title = todo.title,
            isCompleted = todo.isCompleted
        )
    }
}