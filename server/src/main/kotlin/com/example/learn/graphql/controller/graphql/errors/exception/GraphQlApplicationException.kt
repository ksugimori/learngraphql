package com.example.learn.graphql.controller.graphql.errors.exception

import graphql.ErrorType

interface GraphQlApplicationException {
    val message: String
    val type: ErrorType
}
