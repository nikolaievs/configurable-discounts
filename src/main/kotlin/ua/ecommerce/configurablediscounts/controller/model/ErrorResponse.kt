package ua.ecommerce.configurablediscounts.controller.model

data class ErrorResponse(
    val status: Int,
    val code: String,
    val message: String
)