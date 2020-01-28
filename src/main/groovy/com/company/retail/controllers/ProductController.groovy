package com.company.retail.controllers

import com.company.retail.entities.Price
import com.company.retail.entities.Product
import com.company.retail.services.ProductService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

import javax.validation.Valid

@RestController
@RequestMapping('/product')
class ProductController {

  @Autowired
  ProductService productService

  @GetMapping('/{id}')
  ResponseEntity<Product> getProductById(@PathVariable String id) {
    return new ResponseEntity<Product>(productService.getProductWithPrice(id), HttpStatus.OK)
  }

  @PutMapping('/{id}')
  ResponseEntity setProductPrice(@PathVariable String id, @RequestBody Product product) {
    return new ResponseEntity(productService.setProductPrice(id, product), HttpStatus.CREATED)
  }
}
