package com.example.learn.graphql.controller.graphql.relay

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import kotlin.test.assertEquals

class NodeIdUtilsTest {

    @ParameterizedTest
    @CsvSource(
        "User, 1, VXNlcjox", // User:1
        "User, 2, VXNlcjoy", // User:2
        "User, 123, VXNlcjoxMjM=", // User:123
        "User, 999999, VXNlcjo5OTk5OTk=", // User:999999
        "Todo, 1, VG9kbzox", // Todo:1
        "Todo, 2, VG9kbzoy", // Todo:2
        "Todo, 123, VG9kbzoxMjM=", // Todo:123
        "Todo, 999999, VG9kbzo5OTk5OTk=", // Todo:999999
    )
    fun `toNodeId - 正常系`(type: String, id: Long, expected: String) {
        assertEquals(expected, id.toNodeId(type))
    }

    @ParameterizedTest
    @CsvSource(
        "VXNlcjox, User, 1", // User:1
        "VXNlcjoy, User, 2", // User:2
        "VXNlcjoxMjM=, User, 123", // User:123
        "VXNlcjo5OTk5OTk=, User, 999999", // User:999999
        "VG9kbzox, Todo, 1", // Todo:1
        "VG9kbzoy, Todo, 2", // Todo:2
        "VG9kbzoxMjM=, Todo, 123", // Todo:123
        "VG9kbzo5OTk5OTk=, Todo, 999999", // Todo:999999
    )
    fun `decodeNodeId - 正常系`(nodeId: String, expectedType: String, expectedId: String) {
        val (type, id) = nodeId.decodeNodeId()
        assertEquals(expectedType, type)
        assertEquals(expectedId, id)
    }
}
