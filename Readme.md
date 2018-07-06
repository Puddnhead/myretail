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