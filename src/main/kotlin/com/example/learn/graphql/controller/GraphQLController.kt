package com.example.learn.graphql.controller

import com.example.learn.graphql.domain.user.User
import com.example.learn.graphql.domain.user.UserRepository
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Controller

@Controller
class GraphQLController(private val userRepository: UserRepository) {
    @QueryMapping
    fun user(@Argument id: Long): User? {
        return userRepository.findById(id)
    }

    @QueryMapping
    fun users(): List<User> {
        return userRepository.findAll()
    }
}