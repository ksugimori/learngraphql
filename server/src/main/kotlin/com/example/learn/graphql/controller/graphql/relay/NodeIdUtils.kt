package com.example.learn.graphql.controller.graphql.relay

import java.util.*

/**
 * 文字列を Base64 エンコードする。
 */
private fun String.encodeBase64(): String = Base64.getEncoder().encodeToString(this.toByteArray())

/**
 * Base64 エンコードされた文字列をデコードする。
 */
private fun String.decodeBase64(): String = String(Base64.getDecoder().decode(this))

/**
 * Relay の Node ID を生成する。
 * @param type ノードのタイプ（例: "User", "Todo"）
 * @return Base64 エンコードされたノード ID
 */
fun Any?.toNodeId(type: String): String = "$type:$this".encodeBase64()

/**
 * Relay の Node ID をデコードする。
 * @return Pair(タイプ, ID)
 * @throws IllegalArgumentException 無効なフォーマットの場合
 */
fun String.decodeNodeId(): Pair<String, String> {
    val decoded = this.decodeBase64()
    val parts = decoded.split(":", limit = 2)
    require(parts.size == 2) { "Invalid node ID format" }
    return parts[0] to parts[1]
}

/**
 * Relay の Node ID をデコードし、ID 部分を Long 型として返す。
 * @return ID 部分の Long 値
 * @throws IllegalArgumentException 無効なフォーマットの場合
 * @throws NumberFormatException ID 部分が Long に変換できない場合
 */
fun String.decodeNodeIdAsLong(): Long = this.decodeNodeId().second.toLong()
