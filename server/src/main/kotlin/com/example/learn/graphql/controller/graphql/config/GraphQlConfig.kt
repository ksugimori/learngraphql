package com.example.learn.graphql.controller.graphql.config

import com.example.learn.graphql.controller.graphql.relay.response.TodoResponse
import com.example.learn.graphql.controller.graphql.relay.response.UserResponse
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.graphql.execution.RuntimeWiringConfigurer

@Configuration
class GraphQlConfig {

    // TODO GlobalId スカラー型を作成してみる

    @Bean
    fun runtimeWiringConfigurer(): RuntimeWiringConfigurer {
        return RuntimeWiringConfigurer { builder ->
            builder.type("Node") { typeWiring ->
                typeWiring.typeResolver { env ->
                    val obj = env.getObject<Any>()
                    when (obj) {
                        is UserResponse -> env.schema.getObjectType("User")
                        is TodoResponse -> env.schema.getObjectType("Todo")
                        else -> null
                    }
                }
            }
        }
    }
}