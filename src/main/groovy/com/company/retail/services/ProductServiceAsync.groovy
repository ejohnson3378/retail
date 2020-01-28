package com.company.retail.services

import com.company.retail.entities.Product
import com.company.retail.exceptions.DownstreamFailureException
import com.fasterxml.jackson.databind.ObjectMapper
import org.slf4j.LoggerFactory
import org.slf4j.Logger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service
import org.springframework.web.client.HttpClientErrorException
import org.springframework.web.client.HttpServerErrorException
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.UriComponentsBuilder

import java.util.concurrent.CompletableFuture

@Service
class ProductServiceAsync {

  private static final Logger log = LoggerFactory.getLogger(ProductServiceAsync)

  @Value('${product.url}')
  String url

  @Value('${product.exclusions}')
  String exclusions

  @Autowired
  RestTemplate restTemplate

  @Autowired
  ObjectMapper mapper = new ObjectMapper()

  @Async
  CompletableFuture<Product> requestProductDetails(String id) {
    Product fetchedProduct = new Product()
    try {
      UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl("${url}${id}")
        .queryParam('exclusions', exclusions)
      log.warn("requesting product information")
      ResponseEntity<Map> response = restTemplate.exchange(uriBuilder.toUriString(), HttpMethod.GET, null, Map)
      fetchedProduct.id = id
      fetchedProduct.name = response.body.get('product').item.product_description.title
    } catch (HttpClientErrorException | HttpServerErrorException e) {
      log.error("unable to retrieve product information, error: " + e.toString())
      throw new DownstreamFailureException("could not get product information from ${url}", e.getStatusCode(), ProductServiceAsync)
    }
    return CompletableFuture.completedFuture(fetchedProduct)
  }
}
