package com.company.retail.entities

class ErrorResponse {

  ErrorResponse(
    String error = 'Something went wrong',
    Integer status = 500,
    String message = 'Not provided'
  ){
    this.timestamp = (new Date()).getTime()
    this.status = status
    this.message = message
    this.error = error
  }

  long timestamp
  String error
  Integer status
  String message
}
