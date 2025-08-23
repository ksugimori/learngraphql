package com.example.learn.graphql.controller.graphql

import java.util.*

/**
 * 文字列を Base64 エンコードする。
 */
fun String.encodeBase64(): String = Base64.getEncoder().encodeToString(this.toByteArray())

/**
 * Base64 エンコードされた文字列をデコードする。
 */
fun String.decodeBase64(): String = String(Base64.getDecoder().decode(this))