package com.company.retail.services

import com.company.retail.entities.Price
import com.company.retail.entities.Product
import com.company.retail.exceptions.BadRequestException
import com.company.retail.repositories.PriceRepository
import spock.lang.Specification

class ProductServiceTest extends Specification {
  ProductService productService
  ProductServiceAsync productServiceAsync
  PriceRepository priceRepository
  Product product
  Product asyncProduct
  Price price

  def setup() {

    priceRepository = Mock(PriceRepository)
    productServiceAsync = Mock(ProductServiceAsync)

    productService = new ProductService(
      priceRepository: priceRepository,
      productServiceAsync: productServiceAsync
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

    asyncProduct = new Product(
      id: 123456,
      name: 'testAsync',
      current_price: null
    )
  }

  def 'service fails when ID is not valid'(){
    when:
    productService.getProductWithPrice('')

    then:
    thrown(BadRequestException)
  }

  def 'service fails with ID is not found'() {
    when:
    productService.getProductWithPrice('123456')

    then:
    priceRepository.findById('123456') >> {throw new NoSuchElementException('not found')}
    thrown(BadRequestException)
  }
}
