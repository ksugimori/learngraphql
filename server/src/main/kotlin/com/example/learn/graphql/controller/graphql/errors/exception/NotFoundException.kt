package com.example.learn.graphql.controller.graphql.errors.exception

import graphql.ErrorType

class NotFoundException(
    override val message: String,
    override val cause: Throwable? = null,
) : RuntimeException(message, cause), GraphQlApplicationException {
    override val type: ErrorType = ErrorType.DataFetchingException
}