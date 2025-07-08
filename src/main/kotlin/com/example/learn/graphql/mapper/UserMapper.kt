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
    fun selectById(@Param("id") id: Long): User?

    @Select("SELECT * FROM users")
    fun selectAll(): List<User>

    @Insert("INSERT INTO users (name) VALUES (#{name})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    fun insert(user: User)
}