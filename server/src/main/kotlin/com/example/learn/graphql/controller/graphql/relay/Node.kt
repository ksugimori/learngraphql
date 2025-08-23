package com.example.learn.graphql.controller.graphql.relay

/**
 * GraphQL の Node インターフェース。
 */
interface Node {
    /**
     * Node の一意な ID。
     * これは、Relay の仕様に従い、グローバルに一意な識別子として使用されます。
     */
    val id: NodeId
}
