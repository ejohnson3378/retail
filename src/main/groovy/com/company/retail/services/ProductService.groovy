package com.company.retail.services

import com.company.retail.entities.Price
import com.company.retail.entities.Product
import com.company.retail.exceptions.BadRequestException
import com.company.retail.repositories.PriceRepository
import org.slf4j.LoggerFactory
import org.slf4j.Logger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service

@Service
class ProductService {

  private static final Logger log = LoggerFactory.getLogger(ProductService)

  @Autowired
  PriceRepository priceRepository

  @Autowired
  ProductServiceAsync productServiceAsync

  Product getProductWithPrice(String id) {
    if(!id) throw new BadRequestException(message: "invalid id", status: HttpStatus.BAD_REQUEST)
    Price currPrice
    try {
      currPrice = priceRepository.findById(id).get()
    } catch(NoSuchElementException e) {
      log.error("could not find price for id ${id}")
      throw new BadRequestException(message: "could not find price for id ${id}", status: HttpStatus.NOT_FOUND, error: "REDIS KEY ${id} NOT FOUND")
    }
    Product currProduct = productServiceAsync.requestProductDetails(id).get()
    currProduct.current_price = currPrice
    log.info("price ${currPrice.value} has been retrieved")
    return currProduct
  }

  void setProductPrice(String id, Product product) {
    if (!product ||
      !product.current_price ||
      !product.current_price.value || product.current_price.currency_code) throw new BadRequestException(message: "product must contain price and currency code",
      status: HttpStatus.BAD_REQUEST)
    Price newPrice = new Price(
      id: id,
      value: product.current_price.value,
      currency_code: product.current_price.currency_code
    )
    priceRepository.save(newPrice)
    log.info("new price ${newPrice.value} has been set for item ${newPrice.id}")
  }
}
