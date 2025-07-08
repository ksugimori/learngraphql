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
    fun selectByUserId(@Param("userId") userId: Long): List<Todo>

    @Select("SELECT * FROM todos WHERE id = #{id}")
    fun selectById(@Param("id") id: Long): Todo?

    @Select("SELECT * FROM todos")
    fun selectAll(): List<Todo>

    @Insert("INSERT INTO todos (user_id, title, description) VALUES (#{userId}, #{title}, #{description})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    fun insert(todo: Todo)
}