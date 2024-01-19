package com.vickbt.domain.models

data class ApiError(
    val code: Int,
    val error: String
) : Exception(error)
