package com.vickbt.shared.domain.models

data class ApiError(
    val code: Int,
    val error: String
) : Exception(error)
