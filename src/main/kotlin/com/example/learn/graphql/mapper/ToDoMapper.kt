package com.example.learn.graphql.mapper

import com.example.learn.graphql.dto.ToDo
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param
import org.apache.ibatis.annotations.Select

@Mapper
interface ToDoMapper {
    @Select("SELECT * FROM todos WHERE user_id = #{userId}")
    fun findByUserId(@Param("userId") userId: Long): List<ToDo>
}