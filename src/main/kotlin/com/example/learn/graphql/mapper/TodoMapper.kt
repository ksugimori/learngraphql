package com.example.learn.graphql.mapper

import com.example.learn.graphql.dto.Todo
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Options
import org.apache.ibatis.annotations.Param
import org.apache.ibatis.annotations.Select

@Mapper
interface TodoMapper {
    @Select("SELECT * FROM todos WHERE user_id = #{userId}")
    fun findByUserId(@Param("userId") userId: Long): List<Todo>

    @Select("SELECT * FROM todos WHERE id = #{id}")
    fun findById(@Param("id") id: Long): Todo?

    @Select("SELECT * FROM todos")
    fun findAll(): List<Todo>

    @Insert("INSERT INTO todos (user_id, title, description) VALUES (#{userId}, #{title}, #{description})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    fun create(todo: Todo)
}