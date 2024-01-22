package com.vickbt.shared.data.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiErrorDto(
    @SerialName("code")
    val code: Int,

    @SerialName("message")
    val message: String
)
