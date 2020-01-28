package com.company.retail.exceptions

import org.springframework.http.HttpStatus

class BadRequestException extends RuntimeException {
  BadRequestException(String message = 'Something went wrong processing request',
                      HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR, String error = 'An error has occurred') {
    super(message)
    setMessage(message)
    setStatus(status)
    setError(error)

  }
  String message
  String error
  HttpStatus status
}
