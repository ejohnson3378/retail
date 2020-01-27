package com.company.retail.controllers

import com.company.retail.entities.Price
import com.company.retail.services.ProductService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping('/product')
class ProductController {

    @Autowired
    ProductService productService

    @GetMapping('/{id}')
    ResponseEntity<Price> getProductById(@PathVariable String id){
        return new ResponseEntity<Price>(productService.getProductWithPrice(id),HttpStatus.OK)
    }

    @PutMapping('/{id}/{price}')
    ResponseEntity setProductPrice(@PathVariable String id, @PathVariable long price){
        return new ResponseEntity(productService.setProductPrice(id, price),HttpStatus.ACCEPTED)
    }
}
