package com.example.learn.graphql.controller.rest

import com.example.learn.graphql.entity.User
import com.example.learn.graphql.repository.UserRepository
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import io.mockk.verify
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post
import kotlin.test.Test

@WebMvcTest(UserRestController::class)
class UserRestControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockkBean
    private lateinit var userRepository: UserRepository

    @Test
    fun `findAll - 正常系`() {
        every { userRepository.findAll() } returns listOf(
            User(id = 1, name = "ひとりめ"),
            User(id = 2, name = "ふたりめ"),
        )

        mockMvc.get("/api/rest/users").andExpectAll {
            status { isOk() }
            content {
                json(
                    """
                [
                    {
                        "id": 1,
                        "name": "ひとりめ"
                    },
                    {
                        "id": 2,
                        "name": "ふたりめ"
                    }
                ]
            """.trimIndent()
                )
            }
        }
    }

    @Test
    fun `findById - 正常系`() {
        every { userRepository.findByIdOrNull(999) } returns User(id = 999, name = "テスト太郎")


        mockMvc.get("/api/rest/users/999").andExpectAll {
            status { isOk() }
            content { json("""{ "id": 999, "name": "テスト太郎" }""") }
        }
    }

    @Test
    fun `create - 正常系`() {
        // ID には 999 が採番される状態を想定
        every { userRepository.save(any()) } answers { (args[0] as User).copy(id = 999) }

        mockMvc.post("/api/rest/users") {
            contentType = MediaType.APPLICATION_JSON
            content = """{ "name": "新規ユーザー" }"""
        }.andExpectAll {
            status { isCreated() }
            content {
                json(
                    """
                {
                    "id": 999,
                    "name": "新規ユーザー"
                }
            """.trimIndent()
                )
            }
        }

        // 引数にわたす際はリクエストの内容そのままなので id = null
        val expectedUser = User(id = null, name = "新規ユーザー")
        verify { userRepository.save(expectedUser) }
    }
}