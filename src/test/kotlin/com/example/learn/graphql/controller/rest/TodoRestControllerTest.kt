package com.example.learn.graphql.controller.rest

import com.example.learn.graphql.entity.Todo
import com.example.learn.graphql.repository.TodoRepository
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import kotlin.test.Test

@WebMvcTest(TodoRestController::class)
class TodoRestControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockkBean
    private lateinit var todoMapper: TodoRepository

    @Test
    fun `findAll - 正常系`() {
        every { todoMapper.findAll() } returns listOf(
            Todo(id = 1, userId = 100, title = "ひとつめ", description = "ひとつめの詳細"),
            Todo(id = 2, userId = 100, title = "ふたつめ", description = "ふたつめの詳細"),
        )

        mockMvc.get("/api/rest/todos").andExpectAll {
            status { isOk() }
            content {
                json(
                    """
                [
                    {
                        "id": 1,
                        "userId": 100,
                        "title": "ひとつめ",
                        "description": "ひとつめの詳細"
                    },
                    {
                        "id": 2,
                        "userId": 100,
                        "title": "ふたつめ",
                        "description": "ふたつめの詳細"
                    }
                ]
            """.trimIndent()
                )
            }
        }
    }

    @Test
    fun `findById - 正常系`() {
        every { todoMapper.findByIdOrNull(999) } returns Todo(
            id = 999,
            userId = 1,
            title = "かいもの",
            description = "えんぴつを買う"
        )


        mockMvc.get("/api/rest/todos/999").andExpectAll {
            status { isOk() }
            content {
                json(
                    """
                    {
                        "id": 999,
                        "userId": 1,
                        "title": "かいもの",
                        "description": "えんぴつを買う"
                    }
                ]
            """.trimIndent()
                )
            }
        }
    }
}