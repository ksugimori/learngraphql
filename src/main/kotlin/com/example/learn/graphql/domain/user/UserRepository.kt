package com.example.learn.graphql.domain.user

import org.springframework.stereotype.Repository

@Repository
class UserRepository {
    // TODO H2 に移行
    private val data = listOf(
        User(id = 1L, name = "Alice"),
        User(id = 2L, name = "Bob"),
        User(id = 3L, name = "Charlie"),
    )

    fun findById(id: Long): User? = data.find { it.id == id }

    fun findAll(): List<User> = data

}