package com.company.retail.exceptions

import org.springframework.http.HttpStatus

class DownstreamFailureException extends BadRequestException {
  DownstreamFailureException(String message, HttpStatus status, Class service) {
    super("${service.name} call failed with status ${status.value()}", HttpStatus.BAD_GATEWAY)
    this.message = message
    this.status = status

  }
  String message
  HttpStatus status
}
