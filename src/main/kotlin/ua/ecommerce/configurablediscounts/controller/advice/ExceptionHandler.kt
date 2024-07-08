package ua.ecommerce.configurablediscounts.controller.advice

import org.springframework.http.HttpStatus.NOT_FOUND
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import ua.ecommerce.configurablediscounts.controller.model.ErrorResponse
import ua.ecommerce.configurablediscounts.exception.NotFoundException

@ControllerAdvice
class ExceptionHandler {

    @ExceptionHandler(NotFoundException::class)
    fun handleNotFoundException(
        e: NotFoundException
    ): ResponseEntity<ErrorResponse> = ResponseEntity
        .status(NOT_FOUND)
        .body(ErrorResponse(NOT_FOUND.value(), "resource_not_found", e.message))
}