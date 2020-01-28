package com.company.retail.controllers

import com.company.retail.entities.Price
import com.company.retail.entities.Product
import com.company.retail.exceptions.BadRequestException
import com.company.retail.services.ProductService
import org.springframework.http.HttpStatus
import spock.lang.Specification

class ProductControllerTest extends Specification {

  ProductService productService
  ProductController productController
  Product product
  Price price

  def setup() {
    productService = Mock(ProductService)
    productController = new ProductController(
      productService: productService
    )

    price = new Price(
      value: '10',
      currency_code: 'USD'
    )

    product = new Product(
      id: 123456,
      name: 'test',
      current_price: price
    )
  }

  def 'GET /product/{id} will return when ID is valid'() {
    when:
    def response = productController.getProductById('123456')

    then:
    response.statusCode == HttpStatus.OK
    1 * productService.getProductWithPrice(_) >> product
  }

  def 'PUT /product/{id} will return a 201'() {
    when:
    def response = productController.setProductPrice('123456', product)

    then:
    response.statusCode == HttpStatus.CREATED
    1 * productService.setProductPrice(_,_)
  }

}
