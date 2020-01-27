package com.company.retail.entities

import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash

@RedisHash
class Product {
    @Id
    String id
    String name
    Price current_price
}
