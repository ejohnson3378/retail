package com.company.retail.entities

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import org.springframework.data.annotation.Id

@JsonIgnoreProperties(ignoreUnknown = true)
class Product {

    String id
    String name
    Price current_price
}
