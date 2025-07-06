package com.example.learn.graphql.controller

import com.example.learn.graphql.dto.User
import com.example.learn.graphql.mapper.UserMapper
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Controller

@Controller
class GraphQLController(private val userMapper: UserMapper) {
    @QueryMapping
    fun user(@Argument id: Long): User? {
        return userMapper.findById(id)
    }

    @QueryMapping
    fun users(): List<User> {
        return userMapper.findAll()
    }
}