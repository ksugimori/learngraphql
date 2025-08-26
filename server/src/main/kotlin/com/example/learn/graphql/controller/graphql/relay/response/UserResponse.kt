package com.example.learn.graphql.controller.graphql.relay.response

import com.example.learn.graphql.controller.graphql.relay.Node
import com.example.learn.graphql.controller.graphql.relay.toNodeId
import com.example.learn.graphql.entity.User

data class UserResponse(override val id: String, val name: String) : Node {
    companion object {
        fun from(user: User): UserResponse {
            return UserResponse(
                id = user.id.toNodeId("User"),
                name = user.name,
            )
        }
    }
}
