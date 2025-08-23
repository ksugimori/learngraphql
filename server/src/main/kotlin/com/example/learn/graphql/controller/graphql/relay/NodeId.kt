package com.example.learn.graphql.controller.graphql.relay

@JvmInline
value class NodeId(val value: String) {
    /**
     * 型名とIDからNodeIdを生成するコンストラクタ
     */
    constructor(typeName: String, id: Any) : this("$typeName:$id".encodeBase64())

    val typeName: String
        get() {
            val (typeName, _) = value.decodeBase64().split(":")
            return typeName
        }

    val id: String
        get() {
            val (_, id) = value.decodeBase64().split(":")
            return id
        }

    /**
     * NodeIdをデコードして、IDを Long 型として返す
     */
    fun asLong(): Long = id.toLong()
}
