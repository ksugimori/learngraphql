package com.example.learn.graphql.controller.graphql

@JvmInline
value class NodeId(val value: String) {
    /**
     * 型名とIDからNodeIdを生成するコンストラクタ
     */
    constructor(typeName: String, id: Any) : this("$typeName:$id".encodeBase64())

    /**
     * NodeIdをデコードして、IDを Long 型として返す
     */
    fun asLong(): Long {
        val (_, id) = value.decodeBase64().split(":")
        return requireNotNull(id.toLongOrNull()) { "Invalid NodeId: $value" }
    }
}
