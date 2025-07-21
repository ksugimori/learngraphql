package com.example.learn.graphql.controller.rest

import com.example.learn.graphql.entity.Todo
import com.example.learn.graphql.repository.TodoRepository
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import io.mockk.justRun
import io.mockk.verify
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.delete
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post
import org.springframework.test.web.servlet.put
import kotlin.test.Test

@WebMvcTest(TodoRestController::class)
class TodoRestControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockkBean
    private lateinit var todoRepository: TodoRepository

    @Test
    fun `findAll - 正常系`() {
        every { todoRepository.findAll() } returns listOf(
            Todo(id = 1, userId = 100, title = "ひとつめ", isCompleted = true),
            Todo(id = 2, userId = 100, title = "ふたつめ", isCompleted = false),
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
                        "isCompleted": true
                    },
                    {
                        "id": 2,
                        "userId": 100,
                        "title": "ふたつめ",
                        "isCompleted": false
                    }
                ]
            """.trimIndent()
                )
            }
        }
    }

    @Test
    fun `findById - 正常系`() {
        every { todoRepository.findByIdOrNull(999) } returns Todo(
            id = 999,
            userId = 1,
            title = "かいもの",
            isCompleted = false
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
                        "isCompleted": false
                    }
                ]
            """.trimIndent()
                )
            }
        }
    }

    @Test
    fun `create - 正常系`() {
        // ID には 999 が採番される状態を想定
        every { todoRepository.save(any()) } answers { (args[0] as Todo).copy(id = 999) }

        mockMvc.post("/api/rest/todos") {
            contentType = MediaType.APPLICATION_JSON
            content = """
                {
                    "userId": 1,
                    "title": "新規TODO",
                    "isCompleted": false
                }
            """.trimIndent()
        }.andExpectAll {
            status { isCreated() }
            content {
                json(
                    """
                {
                    "id": 999,
                    "userId": 1,
                    "title": "新規TODO",
                    "isCompleted": false
                }
            """.trimIndent()
                )
            }
        }

        // 引数にわたす際はリクエストの内容そのままなので id = null
        val expectedUser = Todo(id = null, userId = 1, title = "新規TODO", isCompleted = false)
        verify { todoRepository.save(expectedUser) }
    }

    @Test
    fun `update - 正常系`() {
        every { todoRepository.save(any()) } answers { (args[0] as Todo) }

        mockMvc.put("/api/rest/todos/123") {
            contentType = MediaType.APPLICATION_JSON
            content = """
                {
                    "id": 123,
                    "userId": 1,
                    "title": "更新テスト",
                    "isCompleted": true
                }
            """.trimIndent()
        }.andExpectAll {
            status { isOk() }
            content {
                json(
                    """
                {
                    "id": 123,
                    "userId": 1,
                    "title": "更新テスト",
                    "isCompleted": true
                }
            """.trimIndent()
                )
            }
        }

        // 引数にわたす際はリクエストの内容そのままなので id = null
        val expectedUser = Todo(id = 123, userId = 1, title = "更新テスト", isCompleted = true)
        verify { todoRepository.save(expectedUser) }
    }

    @Test
    fun `delete - 正常系`() {
        justRun { todoRepository.deleteById(any()) }

        mockMvc.delete("/api/rest/todos/999").andExpect { status { isNoContent() } }
    }
}