package com.company.retail.entities

import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash

@RedisHash
class Price {

    @Id
    String id

    long price

    String currency_code
}
