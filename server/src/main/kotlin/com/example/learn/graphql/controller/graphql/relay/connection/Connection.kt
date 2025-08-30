package com.example.learn.graphql.controller.graphql.relay.connection

import com.example.learn.graphql.controller.graphql.relay.Node

interface Connection<T : Node> {
    val edges: List<Edge<T>>
    val pageInfo: PageInfo
}

interface Edge<T : Node> {
    val cursor: String
    val node: T

    companion object {
        fun <T : Node> of(node: T): Edge<T> = DefaultEdge(node)
    }
}

data class DefaultEdge<T : Node>(override val node: T) : Edge<T> {
    override val cursor: String = node.id
}

data class PageInfo(
    val hasNextPage: Boolean,
    val hasPreviousPage: Boolean,
    val startCursor: String?,
    val endCursor: String?,
) {
    companion object {
        fun empty() = PageInfo(
            hasNextPage = false,
            hasPreviousPage = false,
            startCursor = null,
            endCursor = null,
        )
    }
}
