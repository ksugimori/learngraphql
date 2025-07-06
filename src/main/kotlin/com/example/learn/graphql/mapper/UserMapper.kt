package com.example.learn.graphql.mapper

import com.example.learn.graphql.dto.User
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param
import org.apache.ibatis.annotations.Select

@Mapper
interface UserMapper {
    @Select("SELECT * FROM users WHERE id = #{id}")
    fun findById(@Param("id") id: Long): User?

    @Select("SELECT * FROM users")
    fun findAll(): List<User>
}