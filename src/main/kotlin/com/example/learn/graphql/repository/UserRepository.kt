package com.example.learn.graphql.repository

import com.example.learn.graphql.entity.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long>