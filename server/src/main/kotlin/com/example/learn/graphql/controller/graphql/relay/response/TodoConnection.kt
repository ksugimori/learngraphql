package com.example.learn.graphql.controller.graphql.relay.response

import com.example.learn.graphql.controller.graphql.relay.connection.Connection
import com.example.learn.graphql.controller.graphql.relay.connection.Edge
import com.example.learn.graphql.controller.graphql.relay.connection.PageInfo

data class TodoConnection(
    val totalCount: Int = 0,
    override val edges: List<Edge<TodoResponse>>,
    override val pageInfo: PageInfo,
) : Connection<TodoResponse>
