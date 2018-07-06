MyRetail
=====

This is a Java 10 Spring Boot application that when compiled can be executed via simple command:
```
java -jar myretail-1.0.jar
```

It requires a connection to a MongoDB database loaded with products. Connection properties can be found
in [application.properties](https://github.com/Puddnhead/myretail/blob/integration/src/main/resources/application.properties)
and there is some default data available in [products.json](https://github.com/Puddnhead/myretail/blob/integration/src/test/resources/products.json)
that is compatible with the MongoDB import tool.

The application leverages an external API hosted on my website [thistroll.com](https://www.thistroll.com)
and therefore requires an internet connection. The external API can be swapped out via a property
in [application.properties](https://github.com/Puddnhead/myretail/blob/integration/src/main/resources/application.properties)

## REST API

This application exposes two rest endpoints:

```
GET /products/{productId}
```

Returns JSON product object for the given product ID or a 404 Resource Not Found Exception

```
PUT /products/{productId}
```

Updates the price of a product in the DB. This request takes a JSON product object and uses
the product ID of the object rather than the path parameter. It ignores any changes to the
product name. The service returns the updated object if successful or a 404 Resource Not Found
exception if the update fails. Sample JSON:

```$json
{
    "productId": "10000005",
    "name": "United States Senator",
    "price": {
        "value": "75000.00",
        "currency": "USD"
    }
}
```