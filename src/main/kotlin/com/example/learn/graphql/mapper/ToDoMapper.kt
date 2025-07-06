package com.example.learn.graphql.mapper

import com.example.learn.graphql.dto.ToDo
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Options
import org.apache.ibatis.annotations.Param
import org.apache.ibatis.annotations.Select

@Mapper
interface ToDoMapper {
    @Select("SELECT * FROM todos WHERE user_id = #{userId}")
    fun findByUserId(@Param("userId") userId: Long): List<ToDo>

    @Select("SELECT * FROM todos WHERE id = #{id}")
    fun findById(@Param("id") id: Long): ToDo?

    @Select("SELECT * FROM todos")
    fun findAll(): List<ToDo>

    @Insert("INSERT INTO todos (user_id, summary) VALUES (#{userId}, #{summary})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    fun create(todo: ToDo)
}