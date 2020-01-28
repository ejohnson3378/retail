package com.company.retail.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestTemplate

import java.time.Duration


@Configuration
class RestConfig {

  @Autowired
  RestTemplateBuilder restTemplateBuilder

  @Bean
  RestTemplate webRestTemplate() {
    return restTemplateBuilder
      .setConnectTimeout(Duration.ofMillis(10000)) //could be externally configured
      .setReadTimeout(Duration.ofMillis(10000))
      .build()
  }

}
