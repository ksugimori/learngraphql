package com.example.learn.graphql.mapper

import com.example.learn.graphql.dto.User
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Options
import org.apache.ibatis.annotations.Param
import org.apache.ibatis.annotations.Select

@Mapper
interface UserMapper {
    @Select("SELECT * FROM users WHERE id = #{id}")
    fun findById(@Param("id") id: Long): User?

    @Select("SELECT * FROM users")
    fun findAll(): List<User>

    @Insert("INSERT INTO users (name) VALUES (#{name})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    fun create(user: User)
}