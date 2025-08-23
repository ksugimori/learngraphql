package com.example.learn.graphql.controller.graphql

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import kotlin.test.assertEquals


class NodeIdTest {

    @ParameterizedTest
    @CsvSource(
        "User, 0, VXNlcjow", // User:0
        "User, 1, VXNlcjox", // User:1
        "User, 2, VXNlcjoy", // User:2
        "User, 999999, VXNlcjo5OTk5OTk=", // User:999999
        "Todo, 0, VG9kbzow", // Todo:0
        "Todo, 1, VG9kbzox", // Todo:1
        "Todo, 123, VG9kbzoxMjM=", // Todo:123
        "Todo, 999999, VG9kbzo5OTk5OTk=" // Todo:999999
    )
    fun `constructor - 正常系`(type: String, id: Long, expected: String) {
        val nodeId = NodeId(type, id)
        assertEquals(expected, nodeId.value)
    }

}