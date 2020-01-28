## Retail

### Getting Started

This is the myRetail API that combines noSQL price data with a live API.  It is built on [SpringBoot](https://spring.io/projects/spring-boot) + Groovy for easy coding

### Dev Setup

install the noSQL DB

```brew intsall redis```

Start Redis

````redis-server````

Clone the code

```git clone https://github.com/ejohnson3378/retail.git```

Start the App

```./gradlew bootRun```

If you don't have permission to execute, run `chmod +x gradlew` to give yourself execute rights

You might want to load Redis with price data by using the PUT endpoint. Use the example below for the correct PUT body.

```shell script
curl -X PUT \
     http://localhost:8080/product/13860428/ \
     -H 'cache-control: no-cache' \
     -H 'content-type: application/json' \
     -H 'postman-token: 7900533e-a8b6-a336-2185-c34e6d9414a1' \
     -d '{"value":77.75,"currency_code":"USD"}'
```

Retrieve your price data that is now associated with a product name

```shell script
curl -X GET \
  http://localhost:8080/product/13860428/ \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' \
  -H 'postman-token: a802efda-cbc4-a74d-30b1-c151ccf80d33'
```

### Testing

Testing with [Spock](http://spockframework.org/)

```./gradlew test```

### Configuration

 There is an `application-dev.yml` file in `src/main/resources` that contains the product URL and exclusions. 
 This application is only set to boot in the dev profile


