package com.company.retail.services

import com.company.retail.entities.Price
import com.company.retail.entities.Product
import com.company.retail.repositories.PriceRepository
import com.sun.org.slf4j.internal.LoggerFactory
import com.sun.org.slf4j.internal.Logger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ProductService {

    private static final Logger log = LoggerFactory.getLogger(ProductService)

    @Autowired
    PriceRepository priceRepository

    @Autowired
    ProductServiceAsync productServiceAsync

    Product getProductWithPrice(String id){
        Price currPrice  = priceRepository.findById(id).get()
        Product currProduct = productServiceAsync.requestProductDetails(id).get()
        currProduct.current_price = currPrice
        log.warn("price ${currPrice.value} has been retrieved")
        return currProduct
    }

    void setProductPrice(String id, Product product){
        Price newPrice = new Price(
                id: id,
                value: product.current_price.value,
                currency_code: product.current_price.currency_code
        )
        priceRepository.save(newPrice)
        log.warn("new price ${newPrice.value} has been set for item ${newPrice.id}")
    }
}
