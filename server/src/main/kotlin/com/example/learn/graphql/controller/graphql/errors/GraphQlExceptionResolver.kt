package com.example.learn.graphql.controller.graphql.errors

import com.example.learn.graphql.controller.graphql.errors.exception.NotFoundException
import graphql.GraphQLError
import graphql.GraphqlErrorBuilder
import org.springframework.graphql.data.method.annotation.GraphQlExceptionHandler
import org.springframework.web.bind.annotation.ControllerAdvice


@ControllerAdvice
class GraphQlExceptionResolver {

    @GraphQlExceptionHandler(NotFoundException::class)
    fun resolveToSingleError(ex: NotFoundException): GraphQLError {
        return GraphqlErrorBuilder.newError()
            .errorType(ex.type)
            .message(ex.message)
            .build()
    }
}