package com.example.learn.graphql.controller.rest

import com.example.learn.graphql.dto.Todo
import com.example.learn.graphql.repository.TodoRepository
import org.mockito.kotlin.whenever
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.context.bean.override.mockito.MockitoBean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import java.util.*
import kotlin.test.Test

@WebMvcTest(TodoRestController::class)
class TodoRestControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockitoBean
    private lateinit var todoMapper: TodoRepository

    @Test
    fun `findAll - 正常系`() {
        whenever(todoMapper.findAll()).thenReturn(
            listOf(
                Todo(id = 1, userId = 100, title = "ひとつめ", description = "ひとつめの詳細"),
                Todo(id = 2, userId = 100, title = "ふたつめ", description = "ふたつめの詳細"),
            )
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
        whenever(todoMapper.findById(999)).thenReturn(
            Optional.of(
                Todo(id = 999, userId = 1, title = "かいもの", description = "えんぴつを買う"),
            )
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