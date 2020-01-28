package com.company.retail

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.scheduling.annotation.EnableAsync
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor

import java.util.concurrent.Executor

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages = "com.company.retail")
@EnableAsync
class RetailApplication {

  static void main(String[] args) {
    SpringApplication.run(RetailApplication, args)
  }

  @Bean
  Executor taskExecutor() {
    ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor()
    executor.setCorePoolSize(10)
    executor.setMaxPoolSize(10)
    executor.setQueueCapacity(500)
    executor.setThreadNamePrefix('ProductCall-')
    executor.initialize()
    return executor
  }

}