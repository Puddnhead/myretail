package com.myretail.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

/**
 * Product POJO
 *
 * Created by MVW on 7/2/2018.
 */
public class Product {

    /**
     * Product id
     */
    private String productId;

    /**
     * Product name
     */
    private String name;

    /**
     * Product price
     */
    private Price price;

    @JsonCreator
    public Product(@JsonProperty("productId") String productId, @JsonProperty("name") String name,
                   @JsonProperty("price") Price price) {
        this.productId = productId;
        this.name = name;
        this.price = price;
    }

    public String getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public Price getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(productId, product.productId) &&
                Objects.equals(name, product.name) &&
                Objects.equals(price, product.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, name, price);
    }
}
