package com.example.learn.graphql.controller.rest

import com.example.learn.graphql.dto.User
import com.example.learn.graphql.mapper.UserMapper
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/api/rest/users")
@RestController
class UserRestController(private val userMapper: UserMapper) {
    @GetMapping
    fun findAll(): List<User> {
        return userMapper.findAll()
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): User? {
        return userMapper.findById(id)
    }
}