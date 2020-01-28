package com.company.retail.entities

import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash

@RedisHash
class Price {

  @Id
  @JsonIgnore
  String id

  String value

  String currency_code
}
