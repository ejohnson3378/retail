package com.company.retail.controllers

import com.company.retail.entities.ErrorResponse
import com.company.retail.exceptions.BadRequestException
import org.slf4j.LoggerFactory
import org.slf4j.Logger
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ErrorController {
  private static final Logger log = LoggerFactory.getLogger(ErrorController)

  @ExceptionHandler
  ResponseEntity<ErrorResponse> genericBadRequestExceptionHandler(BadRequestException ex) {
    log.error(ex.message)
    HttpStatus status = ex.status
    return new ResponseEntity<ErrorResponse>(new ErrorResponse(message: ex.message, status: status.value(), error: ex.error), status)
  }

}
