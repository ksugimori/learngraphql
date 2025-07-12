package com.example.learn.graphql.controller.rest

import com.example.learn.graphql.entity.User
import com.example.learn.graphql.repository.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/api/rest/users")
@RestController
class UserRestController(private val userRepository: UserRepository) {
    @GetMapping
    fun findAll(): List<User> {
        return userRepository.findAll()
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody user: User): User {
        return userRepository.save(user)
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): User? {
        return userRepository.findByIdOrNull(id)
    }
}