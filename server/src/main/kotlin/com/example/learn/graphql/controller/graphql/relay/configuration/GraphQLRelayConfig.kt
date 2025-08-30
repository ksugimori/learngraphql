package com.example.learn.graphql.controller.graphql.relay.configuration

import com.example.learn.graphql.controller.graphql.relay.response.TodoResponse
import com.example.learn.graphql.controller.graphql.relay.response.UserResponse
import graphql.schema.idl.TypeRuntimeWiring
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.graphql.execution.RuntimeWiringConfigurer

@Configuration
class GraphQLRelayConfig {

    @Bean
    fun runtimeWiringConfigurer(): RuntimeWiringConfigurer {
        return RuntimeWiringConfigurer { builder ->
            builder.type("Node") { resolveNodeType(it) }
        }
    }

    private fun resolveNodeType(typeWiring: TypeRuntimeWiring.Builder?): TypeRuntimeWiring.Builder? {
        return typeWiring?.typeResolver { typeResolver ->
            when (val obj = typeResolver?.getObject<Any>()) {
                is UserResponse -> typeResolver.schema.getObjectType("User")
                is TodoResponse -> typeResolver.schema.getObjectType("Todo")
                null -> null
                else -> throw IllegalArgumentException("Unknown Node type: ${obj.let { it::class.simpleName }}")
            }
        }
    }
}
