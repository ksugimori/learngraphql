package com.example.learn.graphql.controller.graphql.relay.response

import com.example.learn.graphql.controller.graphql.relay.encodeBase64
import com.example.learn.graphql.entity.User

data class UserResponse(val id: String, val name: String) {
    companion object {
        fun from(user: User): UserResponse {
            val userId = requireNotNull(user.id) { "user.id is null!!" }

            return UserResponse(
                id = "User:$userId".encodeBase64(),
                name = user.name,
            )
        }
    }
}
