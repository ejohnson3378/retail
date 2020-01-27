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

    Price getProductWithPrice(String id){
        Price currPrice  = priceRepository.findById(id).get()
        log.warn("price ${currPrice.price} has been retrieved")
        return currPrice
    }

    void setProductPrice(String id, long price){
        Price newPrice = new Price(
                id: id,
                price: price,
                currency_code: 'USD'
        )
        priceRepository.save(newPrice)
        log.warn("new price ${newPrice.price} has been set for item ${newPrice.id}")
    }
}
